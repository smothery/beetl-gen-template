package panzer.vor.generate.common.model;

import com.gitee.easyopen.doc.annotation.ApiDocField;

import java.io.Serializable;

/**
 * @outhor luozc
 * @create 2019-03-20
 */
public class CreateResponseBody<T> implements Serializable {
    @ApiDocField(description = "创建时间")
    private Integer createAt;
    @ApiDocField(description = "id")
    private T id;

    public Integer getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Integer createAt) {
        this.createAt = createAt;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
