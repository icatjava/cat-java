package cloud.ffeng.cat.error.web;


import cloud.ffeng.cat.common.exception.BizException;
import cloud.ffeng.cat.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;


@Slf4j
@RestControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(Throwable.class)
    public Result<?> handle(Throwable e) {
        if (e instanceof BizException) {
            if (((BizException) e).isSystemError()) {
                log.error("[Exception Handler] {}, print stack: {}", ((BizException) e).logString(), printStack(e.getStackTrace()));
            } else {
                log.warn("[Exception Handler] {}, print stack: {}", ((BizException) e).logString(), printStack(e.getStackTrace()));
            }
            return ((BizException) e).toResult();
        }
        if (e instanceof MethodArgumentNotValidException) {
            return paramHandle(((MethodArgumentNotValidException) e).getBindingResult());
        }
        if (e instanceof BindException) {
            return paramHandle(((BindException) e).getBindingResult());
        }
        if (e instanceof ConstraintViolationException) {
            String message = ((ConstraintViolationException) e).getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .reduce((a, b) -> a + "; " + b)
                    .orElse("请求参数错误，参数校验不通过！");
            return Result.paramError(message);
        }

        log.error("[Exception Handler] exception stack trace: ", e);
        return Result.systemError("请求处理异常，请稍后重试！");
    }

    private String printStack(StackTraceElement[] stackTrace) {
        if (Objects.isNull(stackTrace)) {
            return "none";
        }
        if (stackTrace.length < 4) {
            return Stream.of(stackTrace).map(Objects::toString).reduce((a, b) -> a + " | " + b).orElse("none");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 4; i++) {
            sb.append(" | ").append(stackTrace[i].toString());
        }
        return sb.toString();
    }

    public Result<?> paramHandle(BindingResult bindingResult) {
        String message = Optional.of(bindingResult).map(BindingResult::getAllErrors)
                .filter(errors -> !CollectionUtils.isEmpty(errors))
                .map(errors -> errors.stream().map(ObjectError::getDefaultMessage).reduce((a, b) -> a + "; " + b).orElse("参数校验失败，请确认参数的正确性后重试！"))
                .orElse(null);
        String className = Objects.nonNull(bindingResult.getTarget()) ? bindingResult.getTarget().getClass().getSimpleName() : Objects.toString(bindingResult.getTarget());

        log.warn("[Exception Handler] Param handle, target: {}, message: {}.", className, message);
        return Result.paramError(message);
    }
}
