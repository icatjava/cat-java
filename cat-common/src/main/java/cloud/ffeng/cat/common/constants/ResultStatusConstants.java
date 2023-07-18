package cloud.ffeng.cat.common.constants;

import cloud.ffeng.cat.common.result.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Result Status Constants
 *
 * @author cat-feng
 */
@Getter
@AllArgsConstructor
public final class ResultStatusConstants {
    /**
     * Success
     */
    public static ResultStatus SUCCESS = new ResultStatus("SUCCESS", 200, "Success");
    /**
     * Bad request
     */
    public static ResultStatus BAD_REQUEST = new ResultStatus("BAD_REQUEST", 400, "Bad request");
    /**
     * Unauthorized
     */
    public static ResultStatus UNAUTHORIZED = new ResultStatus("UNAUTHORIZED", 401, "Unauthorized");
    /**
     * Forbidden
     */
    public static ResultStatus FORBIDDEN = new ResultStatus("FORBIDDEN", 403, "Forbidden");
    /**
     * Not found
     */
    public static ResultStatus NOT_FOUND = new ResultStatus("NOT_FOUND", 404, "Not found");
    /**
     * Internal server error
     */
    public static ResultStatus INTERNAL_SERVER_ERROR = new ResultStatus("INTERNAL_SERVER_ERROR", 500, "Internal server error");
    /**
     * Bad gateway
     */
    public static ResultStatus BAD_GATEWAY = new ResultStatus("BAD_GATEWAY", 502, "Bad gateway");
    /**
     * Service unavailable
     */
    public static ResultStatus SERVICE_UNAVAILABLE = new ResultStatus("SERVICE_UNAVAILABLE", 503, "Service unavailable");
    /**
     * Gateway timeout
     */
    public static ResultStatus GATEWAY_TIMEOUT = new ResultStatus("GATEWAY_TIMEOUT", 504, "Gateway timeout");
    /**
     * Biz error
     */
    public static ResultStatus BIZ_ERROR = new ResultStatus("BIZ_ERROR", 600, "Biz error");

}
