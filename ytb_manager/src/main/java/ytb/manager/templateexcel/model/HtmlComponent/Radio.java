package ytb.manager.templateexcel.model.HtmlComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * LXZ
 */
public class Radio extends Metadata {

    private String desc = "";

    private boolean checked = false;

    //显示关联的key集合
    private List<String> refTagKey = new ArrayList<>();

    //显示关联到此按钮的tag的refString,也是指当前按钮在Excel中的位置"sheet"$E15 后续页面可以根据refString找到此按钮
    private String refString;

    /**
     * 需求因子数据
     */

    //需求因子编号
    private String reqItemNo;

    //需求因子描述
    private String reqItemDesc;

    //需求因子ID
    private int reqItemId;

    public Radio() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<String> getRefTagKey() {
        return refTagKey;
    }

    public void setRefTagKey(List<String> refTagKey) {
        this.refTagKey = refTagKey;
    }

    public String getReqItemNo() {
        return reqItemNo;
    }

    public void setReqItemNo(String reqItemNo) {
        this.reqItemNo = reqItemNo;
    }

    public String getReqItemDesc() {
        return reqItemDesc;
    }

    public void setReqItemDesc(String reqItemDesc) {
        this.reqItemDesc = reqItemDesc;
    }

    public int getReqItemId() {
        return reqItemId;
    }

    public void setReqItemId(int reqItemId) {
        this.reqItemId = reqItemId;
    }

    public String getRefString() {
        return refString;
    }

    public void setRefString(String refString) {
        this.refString = refString;
    }
}
