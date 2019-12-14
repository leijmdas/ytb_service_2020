package ytb.manager.templateexcel.model.xls;

import org.apache.poi.ss.usermodel.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Excel一行数据
 * Author LXZ
 * Date 2018/9/4 18:28
 */
public class ExcelRowData {

    private int rowIndex;

    private List<String> list = new ArrayList<>();

    //Excel行
    private Row row;

    //合并区域列表
    private List<CellRangeAddressCustom> mergedRegions;

    private final DataFormatter formatter = new DataFormatter();

    public ExcelRowData() {

    }
    /**
     * @Author qsy
     * @Description 获取单元格注释信息
     * @Date 14:30 2019/4/10
     * @Param [index]
     * @return org.apache.poi.ss.usermodel.Comment
     **/
    public Comment getCellComment(int index){
        return this.getRow().getCell(index).getCellComment();
    }
    private void addCellValue(String value) {
        list.add(value);
    }

    public String getFirstCellValue() {
        return getCellValue(0);
    }

    public String getTwoCellValue() {
        if (list.size() > 1) {
            return getCellValue(1);
        }
        return null;
    }

    public String getCellValue(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }


    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
        int lastCellNum = row.getLastCellNum();
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = row.getCell(i);
            if (cell == null) {
                addCellValue("");
                continue;
            }
            String val = null;
            if (cell.getCellTypeEnum() == CellType.NUMERIC) {
//                boolean isDate = DateUtil.isCellDateFormatted(cell);
                String dataFormatString = cell.getCellStyle().getDataFormatString();
                if ("m/d/yy".equals(dataFormatString)) {//日期
                    val = new SimpleDateFormat("yyyy-M-d").format(cell.getDateCellValue());
                } /*else if (dataFormatString.equals("0%")) {//百分比
                    val = cell.getNumericCellValue() + "";
                } else if (dataFormatString.equals("\"￥\"#,##0;\"￥\"\\-#,##0")) {//人民币
                    val = cell.getNumericCellValue() + "";
                } else {//普通数值
                    val = cell.getNumericCellValue() + "";
                    *//*String formatCellValue = formatter.formatCellValue(cell);
                    if (formatCellValue.startsWith("￥") || formatCellValue.startsWith("¥")) {//人民币
                        double numericCellValue = cell.getNumericCellValue();
                        addCellValue(numericCellValue + "");
                        continue;
                    }*//*
                }*/ else {//百分比 人名币 普通数值
                    val = cell.getNumericCellValue() + "";
                }
            }
            if (val != null) {
                addCellValue(val);
            } else {
                addCellValue(formatter.formatCellValue(cell));
            }
        }
    }

    public List<CellRangeAddressCustom> getMergedRegions() {
        return mergedRegions;
    }

    public void setMergedRegions(List<CellRangeAddressCustom> mergedRegions) {
        this.mergedRegions = mergedRegions;
    }

    /**
     * 获取第i个单元格的合并对象
     *
     * @param i 第i个单元格
     * @return CellRangeAddress
     */
    public CellRangeAddressCustom getCellRangeAddressCustom(int i) {
        Cell cell = this.row.getCell(i);
        if (cell == null) {
            return null;
        }
        int rowIndex = cell.getRowIndex();
        int columnIndex = cell.getColumnIndex();
        for (CellRangeAddressCustom cellRangeAddressCustom : this.mergedRegions) {
            boolean inRange = cellRangeAddressCustom.isInRange(rowIndex, columnIndex);
            if (inRange) {
                return cellRangeAddressCustom;
            }
        }
        return null;
    }

    /**
     * 获取第i个单元格的下一个单元格内容所跳过的格子数
     *
     * @param i 第i个单元格
     * @return int
     */
    public int getCellSkipNum(int i) {
        CellRangeAddressCustom cellRangeAddressCustom = this.getCellRangeAddressCustom(i);
        if (cellRangeAddressCustom != null) {
            return cellRangeAddressCustom.getLastColumn() - i;
        }
        return 0;
    }

    /**
     * 获取第i个单元格占几行
     *
     * @param i 第i个单元格
     * @return int
     */
    public int getCellRowSpan(int i) {
        CellRangeAddressCustom cellRangeAddressCustom = this.getCellRangeAddressCustom(i);
        if (cellRangeAddressCustom != null) {
            return cellRangeAddressCustom.getLastRow() - cellRangeAddressCustom.getFirstRow() + 1;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "ExcelRowData{" +
                "list=" + list +
                '}';
    }
}
