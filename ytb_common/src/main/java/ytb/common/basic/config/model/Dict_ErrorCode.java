package ytb.common.basic.config.model;

import com.alibaba.excel.annotation.ExcelProperty;
import ytb.common.context.model.Ytb_Model;

public class Dict_ErrorCode extends Ytb_Model {



    @ExcelProperty(value = "errorId", index = 0)
    private int errorId;

    @ExcelProperty(value = "errorCode", index = 1)
    private String errorCode;

    @ExcelProperty(value = "remark", index = 2)
    private String remark;

    @ExcelProperty(value = "remarkChina", index = 3)
    private String remarkChina;

    @ExcelProperty(value = "scene", index = 4)
    private String scene;

    @ExcelProperty(value = "explain", index = 5)
    private String explain;


    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemarkChina() {
        return remarkChina;
    }

    public void setRemarkChina(String remarkChina) {
        this.remarkChina = remarkChina;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
