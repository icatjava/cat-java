package cloud.ffeng.cat.tracer;

import cloud.ffeng.cat.common.constants.TracerConstants;
import cloud.ffeng.cat.common.util.UuidUtil;
import cloud.ffeng.cat.tracer.base.TraceInfo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cat-feng
 */
public class TracerContext {

    /**
     * Trace thread data
     */
    public static final ThreadLocal<TraceInfo> TRACE_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * init trace info
     */
    public void initTrace() {
        String traceId = generateTraceId();
        TraceInfo traceInfo = new TraceInfo();
        traceInfo.setTraceId(traceId);
        List<Integer> invokeIdList = new LinkedList<>();
        invokeIdList.add(1);
        traceInfo.setInvokeIdChain(invokeIdList);
        TRACE_THREAD_LOCAL.set(traceInfo);
    }

    public String getTraceId() {
        return "";
    }

    public TraceInfo getTraceInfo() {
        return TRACE_THREAD_LOCAL.get();
    }

    public void httpTrace(HttpServletRequest request) {
        String traceId = getTraceId();
        if (StringUtils.isNotBlank(traceId)) {
            return;
        }
        traceId = request.getHeader(TracerConstants.TRANCE_ID);
        if (StringUtils.isNotBlank(traceId)) {
            return;
        }
        initTrace();
    }

    public String generateTraceId() {
        return UuidUtil.generateUuid();
    }

}
