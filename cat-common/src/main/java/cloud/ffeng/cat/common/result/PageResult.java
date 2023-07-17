package cloud.ffeng.cat.common.result;

import cloud.ffeng.cat.common.constants.ResultStatusConstants;
import cloud.ffeng.cat.common.support.TraceContext;

import java.util.List;

/**
 * page result
 *
 * @param <T> data type
 */
public class PageResult<T> extends Result<Page<T>> {

    /**
     * build a page result.
     *
     * @param pageNum   page no
     * @param pageSize  page size
     * @param totalSize total size
     * @param dataList  data list
     * @param <T>       data type
     * @return page result
     */
    public static <T> PageResult<T> page(int pageNum, int pageSize, long totalSize, List<T> dataList) {
        assert pageNum > 0;
        assert pageSize > 0;
        assert totalSize > 0;

        PageResult<T> pageResult = new PageResult<>();
        Page<T> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotalSize(totalSize);
        page.setCurrPageSize(dataList == null ? 0 : dataList.size());
        page.setDataList(dataList);
        pageResult.setData(page);

        pageResult.setStatus(ResultStatusConstants.SUCCESS.getStatus());
        pageResult.setCode(ResultStatusConstants.SUCCESS.getCode());
        pageResult.setMessage(ResultStatusConstants.SUCCESS.getMessage());
        pageResult.setTraceId(TraceContext.getTraceId());
        return pageResult;
    }

}
