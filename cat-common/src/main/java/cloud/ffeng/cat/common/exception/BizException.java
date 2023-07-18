package cloud.ffeng.cat.common.exception;


import cloud.ffeng.cat.common.result.Result;
import cloud.ffeng.cat.common.result.ResultStatus;
import cloud.ffeng.cat.common.support.TraceContext;
import lombok.Getter;

import java.util.Objects;

import static cloud.ffeng.cat.common.constants.ResultStatusConstants.*;

/**
 * Business exception.
 *
 * @author cat-feng
 */
@Getter
public class BizException extends RuntimeException {
    /**
     * Status
     */
    private final Integer status;
    /**
     * Code
     */
    private final String code;
    /**
     * Trace id
     */
    private final String traceId;

    /**
     * Biz Exception
     *
     * @param status  status
     * @param code    code
     * @param message message
     * @param cause   cause
     */
    public BizException(Integer status, String code, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.code = code;
        this.traceId = TraceContext.getTraceId();
    }

    /**
     * is param error
     *
     * @return boolean
     */
    public boolean isParamError() {
        return Objects.equals(BAD_REQUEST.getStatus(), this.status);
    }


    /**
     * is system error
     *
     * @return boolean
     */
    public boolean isSystemError() {
        return Objects.equals(INTERNAL_SERVER_ERROR.getStatus(), this.status);
    }

    /**
     * is forbidden error
     *
     * @return boolean
     */
    public boolean isForbiddenError() {
        return Objects.equals(FORBIDDEN.getStatus(), this.status);
    }

    /**
     * is unauthorized error
     *
     * @return boolean
     */
    public boolean isUnauthorizedError() {
        return Objects.equals(UNAUTHORIZED.getStatus(), this.status);
    }

    /**
     * Param error
     *
     * @param message message
     * @param cause   cause
     * @return BizException
     */
    public static BizException paramError(String message, Throwable cause) {
        return new BizException(BAD_REQUEST.getStatus(), BAD_REQUEST.getCode(), message, cause);
    }

    /**
     * Param error
     *
     * @param message message
     * @return BizException
     */
    public static BizException paramError(String message) {
        return paramError(message, null);
    }

    /**
     * System error
     *
     * @param message message
     * @param cause   cause
     * @return BizException
     */
    public static BizException systemError(String message, Throwable cause) {
        return new BizException(INTERNAL_SERVER_ERROR.getStatus(), INTERNAL_SERVER_ERROR.getCode(), message, cause);
    }

    /**
     * System error
     *
     * @param message message
     * @return BizException
     */
    public static BizException systemError(String message) {
        return systemError(message, null);
    }

    /**
     * Business error
     *
     * @param message message
     * @param cause   cause
     * @return BizException
     */
    public static BizException bizError(String message, Throwable cause) {
        return new BizException(BIZ_ERROR.getStatus(), BIZ_ERROR.getCode(), message, cause);
    }

    /**
     * Business error
     *
     * @param message message
     * @return BizException
     */
    public static BizException bizError(String message) {
        return bizError(message, null);
    }

    /**
     * build BizException
     *
     * @param status  status
     * @param message message
     * @param cause   cause
     * @return BizException
     */
    public static BizException of(ResultStatus status, String message, Throwable cause) {
        return new BizException(status.getStatus(), status.getCode(), message, cause);
    }

    /**
     * build BizException
     *
     * @param status  status
     * @param message message
     * @return BizException
     */
    public static BizException of(ResultStatus status, String message) {
        return new BizException(status.getStatus(), status.getCode(), message, null);
    }

    /**
     * build BizException
     *
     * @param status  status
     * @param code    code
     * @param message message
     * @return BizException
     */
    public static BizException of(ResultStatus status, String code, String message) {
        return new BizException(status.getStatus(), status.getCode(), message, null);
    }

    /**
     * build BizException
     *
     * @param status  status
     * @param code    code
     * @param message message
     * @param cause   cause
     * @return BizException
     */
    public static BizException of(ResultStatus status, String code, String message, Throwable cause) {
        return new BizException(status.getStatus(), code, message, cause);
    }

    /**
     * Transfer to Result.
     *
     * @return Result
     */
    public Result<?> toResult() {
        Result<?> result = new Result<>();
        result.setStatus(this.getStatus());
        result.setCode(this.getCode());
        result.setMessage(this.getMessage());
        result.setTraceId(this.getTraceId());
        result.setData(null);
        return result;
    }

    /**
     * log String
     *
     * @return log string
     */
    public String logString() {
        String causeInfo = Objects.nonNull(this.getCause()) ? "[ " + this.getCause().getClass().getSimpleName() + " - " + this.getCause().getMessage() + " ]" : "none";
        return String.format("status: %d, code: %s, message: %s. cause: %s", this.status, this.code, this.getMessage(), causeInfo);
    }
}
