package panzer.vor.generate.common.model;

import com.gitee.easyopen.doc.annotation.ApiDocField;

import java.io.Serializable;

/**
 * @outhor luozc
 * @create 2018-07-09
 */
public class ApiBaseResponseBody<T> implements Serializable {
    @ApiDocField(description = "返回码，仅为0表示成功")
    private T errcode;
    @ApiDocField(description = "返回码描述")
    private String errmsg;
    @ApiDocField(description = "特殊描述")
    private String subMsg;

    public T getErrcode() {
        return errcode;
    }

    public void setErrcode(T errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public boolean isSuccess() {
        return !(errcode == null) && ("0".equals(errcode + ""));
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }
}
