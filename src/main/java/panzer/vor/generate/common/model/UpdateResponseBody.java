package panzer.vor.generate.common.model;

import com.gitee.easyopen.doc.annotation.ApiDocField;

import java.io.Serializable;

/**
 * @outhor luozc
 * @create 2019-03-20
 */
public class UpdateResponseBody implements Serializable{
    @ApiDocField(description = "更新时间")
    private Integer updateAt;

    public Integer getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Integer updateAt) {
        this.updateAt = updateAt;
    }
}
