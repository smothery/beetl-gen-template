package panzer.vor.generate.common.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/19.
 */
public final class PageInfo<X> {

    public static final Long FIRST_PAGE = 1L;

    public static final Long DEFAULT_PAGE_SIZE = 10L;

    private Long page = 1L;
    private Long size = DEFAULT_PAGE_SIZE;
    private Long totalRow = 0L;
    private Long totalPage = 0L;
    private Long runtime = 0L;// 执行时间
    private boolean autoCount = true;
    private List<X> result = Collections.emptyList();
    private Long _start = 0L;

    /**
     * 存储一些扩展数据的值
     */
    private Map<String, Object> ext = new HashMap<String, Object>();

    /**
     * @param page
     */
    public PageInfo(Long page) {
        this(page, DEFAULT_PAGE_SIZE);
    }

    /**
     * @param page 当前页码
     * @param size 页面大小
     */
    public PageInfo(Long page, Long size) {
        this._start = System.currentTimeMillis();
        this.size = size;
        this.page = page;

        /* 设置每页的记录数量,低于1时自动调整为DEFAULT_PAGE_SIZE */
        if (this.size < 1)
            this.size = DEFAULT_PAGE_SIZE;

        /* 开始页面小于1自动调整到第1页 */
        if (this.page < 1)
            this.page = FIRST_PAGE;

    }

    public void setAutoCount(boolean autoCount) {
        this.autoCount = autoCount;
    }

    public boolean isAutoCount() {
        return this.autoCount;
    }

    /**
     * 根据page和size计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    public Long getFirst() {
        Long first = ((this.page - 1) * this.size) + 1;
        if (first < 0) {
            first = 0L;
        }
        return first;
    }

    /**
     * 获取页面大小
     *
     * @return
     */
    public Long getSize() {
        return this.size;
    }

    /**
     * 是否有下一页
     *
     * @return
     */
    public boolean hasNext() {
        return (page + 1 <= totalPage);
    }

    /**
     * 是否有上一页
     *
     * @return
     */
    public boolean hasPrev() {
        return (page - 1 >= 1);
    }

    /**
     * 获取下一页的页码
     *
     * @return
     */
    public Long getNextPage() {
        if (this.hasNext())
            return page + 1;
        return page;
    }

    /**
     * 获取当前页
     *
     * @return
     */
    public Long getPage() {
        return this.page;
    }

    /**
     * 获取上一页的页码
     *
     * @return
     */
    public Long getPrevPage() {
        if (this.hasPrev())
            return page - 1;
        return page;
    }

    /**
     * 总页数
     *
     * @return
     */
    public Long getTotalPage() {
        return this.totalPage;
    }

    /**
     * 设置总记录数
     *
     * @param totalRow
     */
    public void setTotalRow(Long totalRow) {
        this.totalRow = totalRow;
        this.totalPage = (Long) (this.totalRow / (long) this.size);
        if (this.totalRow % (long) this.size > 0) {
            this.totalPage++;
        }
        /* 开始页面大于总页数自动调整到尾页 */
        if (this.page > this.totalPage)
            this.page = this.totalPage;
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public Long getTotalRow() {
        return this.totalRow;
    }

    /**
     * 获取结果集
     *
     * @return
     */
    public List<X> getResult() {
        return result;
    }

    /**
     * 设置结果集
     *
     * @param result
     */
    public void setResult(List<X> result) {
        this.result = result;
        if (this.runtime == 0) {
            this.runtime = System.currentTimeMillis() - _start;
        }
    }

    public Long getRuntime() {
        return runtime;
    }

    public void setRuntime(Long runtime) {
        this.runtime = runtime;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }

    public Map<String, Object> attr(String key, Object value) {
        ext.put(key, value);
        return ext;
    }

    public Map<String, Object> attr(Map<String, Object> map) {
        ext.putAll(map);
        return ext;
    }

    @SuppressWarnings("unchecked")
    public <T> T attr(String key) {
        return (T) ext.get(key);
    }

}

