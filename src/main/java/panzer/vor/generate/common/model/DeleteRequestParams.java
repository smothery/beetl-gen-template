package panzer.vor.generate.common.model;

import com.gitee.easyopen.doc.annotation.ApiDocField;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @outhor luozc
 * @create 2019-03-20
 */
public class DeleteRequestParams<T> {
    @NotNull(message = "记录id列表不能为空")
    @Size(min = 1)
    @ApiDocField(description = "记录id列表")
    private List<T> ids;

    public List<T> getIds() {
        return ids;
    }

    public void setIds(List<T> ids) {
        this.ids = ids;
    }
}
