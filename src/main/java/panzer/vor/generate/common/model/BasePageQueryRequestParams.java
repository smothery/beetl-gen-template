package panzer.vor.generate.common.model;

import com.gitee.easyopen.doc.annotation.ApiDocField;

public class BasePageQueryRequestParams {
    @ApiDocField(description = "第几页", required = true, example = "1")
    private Long page;
    @ApiDocField(description = "每页几条数据", required = true, example = "10")
    private Long size;

    private String keyword;
    private String status;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
