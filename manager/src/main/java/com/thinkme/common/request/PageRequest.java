package com.thinkme.common.request;

import lombok.Data;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2017/11/28 上午11:51
 */
@Data
public class PageRequest extends Request {
    /**
     * 分页每页大小
     */
    private int pageSize;

    /**
     * 分页起始页， 从1开始计数
     */
    private int pageNum;

    /**
     * 总条数
     */
    private long total = 0L;
}
