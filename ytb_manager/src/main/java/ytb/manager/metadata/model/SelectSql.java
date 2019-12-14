package ytb.manager.metadata.model;

import org.apache.poi.util.StringUtil;
import ytb.common.context.model.Ytb_ModelSkipNull;

/**
 * Created by ZYB on 2018/9/13 14:43
 */
public class SelectSql extends Ytb_ModelSkipNull {


    private String table;


    private String sWhere;

    private String orderTo;

    private String orderBy;

    private String limitFirstIndex ;

    private String limitpageSize ;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getsWhere() {
        return sWhere;
    }

    public void setsWhere(String sWhere) {
        this.sWhere = sWhere;
    }
    public String getOrderTo() {
        return orderTo;
    }

    public String buildOrderby(){
        if(orderBy==null){
            orderBy="asc";
        }
        if(orderTo!=null &&!orderTo.trim().isEmpty())
        {
            String sbTo[] = orderTo.split(",");
            String sbBy[] = orderBy.split(",");
            for(int i=0;i<sbTo.length;i++){
                sbTo[i]=sbTo[i]+ " "+(i<sbBy.length?sbBy[i]:sbBy[sbBy.length-1]);
            }
            return StringUtil.join(sbTo,",");
        }
        return "";
    }
    public void setOrderTo(String orderTo) {
        this.orderTo = orderTo;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getLimitFirstIndex() {
        return limitFirstIndex;
    }

    public void setLimitFirstIndex(String limitFirstIndex) {
        this.limitFirstIndex = limitFirstIndex;
    }

    public String getLimitpageSize() {
        return limitpageSize;
    }

    public void setLimitpageSize(String limitpageSize) {
        this.limitpageSize = limitpageSize;
    }
}
