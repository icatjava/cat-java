package cloud.ffeng.cat.common.util;

import java.util.UUID;

/**
 * uuid util
 *
 * @author cat-feng
 */
public final class UuidUtil {

    /**
     * generate uuid exclude '-'
     *
     * @return uuid
     */
    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
