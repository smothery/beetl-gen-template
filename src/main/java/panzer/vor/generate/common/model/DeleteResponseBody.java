package panzer.vor.generate.common.model;

import com.gitee.easyopen.doc.annotation.ApiDocField;

import java.io.Serializable;

/**
 * @outhor luozc
 * @create 2019-03-20
 */
public class DeleteResponseBody implements Serializable{
    @ApiDocField(description = "删除时间")
    private Integer deleteAt;

    public Integer getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Integer deleteAt) {
        this.deleteAt = deleteAt;
    }
}
