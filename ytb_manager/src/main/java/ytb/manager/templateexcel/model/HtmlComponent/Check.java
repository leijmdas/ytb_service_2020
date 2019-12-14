package ytb.manager.templateexcel.model.HtmlComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * LXZ
 */
public class Check extends Metadata {

    private String desc = "";

    private boolean checked = false;

    //显示关联的key集合
    private List<String> refTagKey = new ArrayList<>();

    public Check() {
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
}
