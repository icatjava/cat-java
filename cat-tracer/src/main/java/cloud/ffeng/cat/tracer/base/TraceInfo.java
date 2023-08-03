package cloud.ffeng.cat.tracer.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Trace Info
 *
 * @author cat-feng
 */
@Data
public class TraceInfo implements Serializable {

    private String traceId;

    private List<Integer> invokeIdChain;

}
