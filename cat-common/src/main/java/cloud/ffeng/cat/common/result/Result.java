package cloud.ffeng.cat.common.result;

import cloud.ffeng.cat.common.constants.ResultStatusConstants;
import cloud.ffeng.cat.common.support.TraceSupport;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private Integer status;
    private String code;
    private String message;
    private String traceId;
    private T data;

    public static <T> Result<T> success(T data) {
        return of(ResultStatusConstants.SUCCESS.getStatus(), ResultStatusConstants.SUCCESS.getCode(), null, data);
    }

    public static <T> Result<T> success(T data, String message) {
        return of(ResultStatusConstants.SUCCESS.getStatus(), ResultStatusConstants.SUCCESS.getCode(), message, data);
    }

    public static <T> Result<T> paramError(String message) {
        return of(ResultStatusConstants.BAD_REQUEST.getStatus(), ResultStatusConstants.BAD_REQUEST.getCode(), message, null);
    }

    public static <T> Result<T> systemError(String message) {
        return of(ResultStatusConstants.INTERNAL_SERVER_ERROR.getStatus(), ResultStatusConstants.INTERNAL_SERVER_ERROR.getCode(), message, null);
    }

    public static <T> Result<T> systemError(String code, String message) {
        return of(ResultStatusConstants.INTERNAL_SERVER_ERROR.getStatus(), code, message, null);
    }

    public static <T> Result<T> of(ResultStatus resultStatus, String message, T data) {
        return of(resultStatus.getStatus(), resultStatus.getCode(), message, data);
    }

    public static <T> Result<T> of(ResultStatus resultStatus, T data) {
        return of(resultStatus.getStatus(), resultStatus.getCode(), null, data);
    }

    public static <T> Result<T> of(Integer status, String code, String message, T data) {
        Result<T> result = new Result<>();
        result.setMessage(message);
        result.setStatus(status);
        result.setCode(code);
        result.setTraceId(TraceSupport.getTraceId());
        result.setData(data);
        return result;
    }

}
