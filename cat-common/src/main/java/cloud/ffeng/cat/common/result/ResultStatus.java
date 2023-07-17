package cloud.ffeng.cat.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * result status
 *
 * @author cat-feng
 */
@Getter
@AllArgsConstructor
public class ResultStatus {

    /**
     * string code
     */
    private final String code;
    /**
     * int status
     */
    private final Integer status;

    /**
     * string message
     */
    @Setter
    private String message;
}
