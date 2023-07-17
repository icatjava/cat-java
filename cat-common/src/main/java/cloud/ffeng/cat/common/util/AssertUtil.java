package cloud.ffeng.cat.common.util;

import cloud.ffeng.cat.common.exception.BizException;
import cloud.ffeng.cat.common.result.ResultStatus;

import java.util.Collection;
import java.util.Objects;

/**
 * assertion util
 *
 * @author cat-feng
 */
public class AssertUtil {

    /**
     * assert flag is true. if not true, throw #{@link BizException}
     *
     * @param flag    flag
     * @param status  status
     * @param message notice message
     */
    public static void isTrue(boolean flag, ResultStatus status, String message) {
        if (!flag) {
            throw BizException.of(status, message);
        }
    }

    /**
     * assert obj is null. if not true, throw #{@link BizException}
     *
     * @param obj     obj
     * @param status  status
     * @param message notice message
     */
    public static void isNull(Object obj, ResultStatus status, String message) {
        if (Objects.nonNull(obj)) {
            throw BizException.of(status, message);
        }
    }

    /**
     * assert obj is nonnull. if not true, throw #{@link BizException}
     *
     * @param obj     obj
     * @param status  status
     * @param message notice message
     */
    public static void nonNull(Object obj, ResultStatus status, String message) {
        if (Objects.isNull(obj)) {
            throw BizException.of(status, message);
        }
    }

    /**
     * assert seq is blank. if not true, throw #{@link BizException}
     *
     * @param seq     seq
     * @param status  status
     * @param message notice message
     */
    public static void isBlank(String seq, ResultStatus status, String message) {
        if (seq == null || "".equals(seq.trim())) {
            return;
        }
        throw BizException.of(status, message);
    }

    /**
     * assert seq is not blank. if not true, throw #{@link BizException}
     *
     * @param seq     seq
     * @param status  status
     * @param message notice message
     */
    public static void isNotBlank(String seq, ResultStatus status, String message) {
        if (seq == null || "".equals(seq.trim())) {
            throw BizException.of(status, message);
        }
    }

    /**
     * assert collection is empty. if not true, throw #{@link BizException}
     *
     * @param collection collection
     * @param status     status
     * @param message    notice message
     */
    public static void isEmpty(Collection<?> collection, ResultStatus status, String message) {
        if (collection == null || collection.size() == 0) {
            return;
        }
        throw BizException.of(status, message);
    }

    /**
     * assert collection is not empty. if not true, throw #{@link BizException}
     *
     * @param collection collection
     * @param status     status
     * @param message    notice message
     */
    public static void isNotEmpty(Collection<?> collection, ResultStatus status, String message) {
        if (collection == null || collection.size() == 0) {
            throw BizException.of(status, message);
        }
    }
}
