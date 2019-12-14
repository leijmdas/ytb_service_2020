package ytb.user.model;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.user.model
 * Author: ZCS
 * Date: Created in 2018/12/4 14:57
 */
public class PurchModel {
    private String head ="";
    private List<Map<String,Object>> dataList;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }
}
