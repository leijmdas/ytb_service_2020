package ytb.manager.templateexcel.model.xls;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @Author lxz
 * @Date 2018/11/9 19:36
 * @Description 自定义单元格合并数据类
 */
public class CellRangeAddressCustom extends CellRangeAddress {

    //是否读取过
    private boolean read;

    public CellRangeAddressCustom(int firstRow, int lastRow, int firstCol, int lastCol) {
        super(firstRow, lastRow, firstCol, lastCol);
    }

    public CellRangeAddressCustom(RecordInputStream in) {
        super(in);
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    //占几行
    public int getRowSpan() {
        return this.getLastRow() - this.getFirstRow() + 1;
    }

    //占几列
    public int getColumnSpan() {
        return this.getLastColumn() - this.getFirstColumn() + 1;
    }
}
