package cloud.ffeng.cat.common.util;

import java.util.function.Supplier;

/**
 * func util
 *
 * @author cat-feng
 */
public final class FuncUtil {

    /**
     * if flag is true, exec supplier and return T
     *
     * @param flag     flag
     * @param supplier supplier
     * @param <T>      data type
     * @return T
     */
    public static <T> T ifTrueThen(boolean flag, Supplier<T> supplier) {
        if (flag) {
            return supplier.get();
        }
        return null;
    }

    /**
     * if flag is true, exec runnable.
     *
     * @param flag     flag
     * @param runnable runnable
     */
    public static void ifTrueThen(boolean flag, Runnable runnable) {
        if (flag) {
            runnable.run();
        }
    }

}
