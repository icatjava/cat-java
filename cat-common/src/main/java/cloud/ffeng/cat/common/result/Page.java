package cloud.ffeng.cat.common.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * page info
 *
 * @param <T> data type
 */
@Data
public class Page<T> implements Serializable {

    /**
     * page no
     */
    private Integer pageNum;

    /**
     * page size
     */
    private Integer pageSize;

    /**
     * total size
     */
    private Long totalSize;

    /**
     * current page size.
     */
    private Integer currPageSize;

    /**
     * data list
     */
    private List<T> dataList;

}
