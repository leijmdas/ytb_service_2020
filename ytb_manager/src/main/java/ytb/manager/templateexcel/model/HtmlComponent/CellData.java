package ytb.manager.templateexcel.model.HtmlComponent;


import ytb.manager.metadata.model.MetadataField;

/**
 * 标签关联表
 * <p>
 * 表格中单元格的数据
 */
public class CellData {

    //字段名
    private String fieldName;

    //描述
    private String fieldMemo;

    //数据类型
    private String fieldType;

    //默认值
    private String fieldDefault;

    //来源
    private int fieldSrc;

    //关联字段名
    private String refField;

    //显示字段名
    private String refDisplayid;

    //关联对(e.g http://xxx)
    private String refObject;

    //关联对象参数(e.g {xxx:xxx})
    //关联公式 sum(phase1_day,phase2_day,phase3_day,phase4_day,phase5_day,phase6_day)
    private String refParameter;

    //是否只读
    private boolean fieldReadonly;

    //显示大小
    private int fieldDisplaysize;

    //显示格式 百分比 人民币
    private String fieldFormat;

    //关联条件
    private String refFilter;

    //是否是计算字段
    private boolean fieldIscal;

    //显示控件
    private int fieldComponent;

    //按钮动作
    private String refPool;

    //内容
    private String text = "";

    //提示内容
    private String placeholder;

    //可编辑
    private boolean edit;

    //占几行
    private int rowSpan;

    //占几列
    private int colSpan;

    //是否显示
    private boolean hide;

    //是否是合并单元格
    private boolean isMergeCell;

    //tagTableParam 页面展示字段描述
    private String fieldNameDesc;

    private TextBox textBox;

    private int refValue;

    //hint  单元格批注
    private String hint;

    public CellData() {
    }

    public CellData(MetadataField metadataField) {
        injectMetadataField(metadataField);
    }

    public void injectMetadataField(MetadataField metadataField) {
        this.fieldName = metadataField.getFieldName();
        this.fieldType = metadataField.getFieldType();
        this.fieldSrc = metadataField.getFieldSrc();
        this.refField = metadataField.getRefField();
        this.refDisplayid = metadataField.getRefDisplayid();
        this.refObject = metadataField.getRefObject();
        this.refParameter = metadataField.getRefParameter();
        this.fieldDefault = metadataField.getFieldDefault();
        this.fieldReadonly = metadataField.getFieldReadonly();
        this.fieldDisplaysize = metadataField.getFieldDisplaysize();
        this.fieldFormat = metadataField.getFieldFormat();
        this.refFilter = metadataField.getRefFilter();
        this.fieldIscal = metadataField.getFieldIscal();
        this.fieldComponent = metadataField.getFieldComponent();
        this.refPool = metadataField.getRefPool();
    }

    public int getRefValue() {
        return refValue;
    }

    public void setRefValue(int refValue) {
        this.refValue = refValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldMemo() {
        return fieldMemo;
    }

    public void setFieldMemo(String fieldMemo) {
        this.fieldMemo = fieldMemo;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldDefault() {
        return fieldDefault;
    }

    public void setFieldDefault(String fieldDefault) {
        this.fieldDefault = fieldDefault;
    }

    public int getFieldSrc() {
        return fieldSrc;
    }

    public void setFieldSrc(int fieldSrc) {
        this.fieldSrc = fieldSrc;
    }

    public String getRefField() {
        return refField;
    }

    public void setRefField(String refField) {
        this.refField = refField;
    }

    public String getRefDisplayid() {
        return refDisplayid;
    }

    public void setRefDisplayid(String refDisplayid) {
        this.refDisplayid = refDisplayid;
    }

    public String getRefObject() {
        return refObject;
    }

    public void setRefObject(String refObject) {
        this.refObject = refObject;
    }

    public String getRefParameter() {
        return refParameter;
    }

    public void setRefParameter(String refParameter) {
        this.refParameter = refParameter;
    }

    public boolean isFieldReadonly() {
        return fieldReadonly;
    }

    public void setFieldReadonly(boolean fieldReadonly) {
        this.fieldReadonly = fieldReadonly;
    }

    public int getFieldDisplaysize() {
        return fieldDisplaysize;
    }

    public void setFieldDisplaysize(int fieldDisplaysize) {
        this.fieldDisplaysize = fieldDisplaysize;
    }

    public String getFieldFormat() {
        return fieldFormat;
    }

    public void setFieldFormat(String fieldFormat) {
        this.fieldFormat = fieldFormat;
    }

    public String getRefFilter() {
        return refFilter;
    }

    public void setRefFilter(String refFilter) {
        this.refFilter = refFilter;
    }

    public boolean isFieldIscal() {
        return fieldIscal;
    }

    public void setFieldIscal(boolean fieldIscal) {
        this.fieldIscal = fieldIscal;
    }

    public int getFieldComponent() {
        return fieldComponent;
    }

    public void setFieldComponent(int fieldComponent) {
        this.fieldComponent = fieldComponent;
    }

    public String getRefPool() {
        return refPool;
    }

    public void setRefPool(String refPool) {
        this.refPool = refPool;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public int getColSpan() {
        return colSpan;
    }

    public void setColSpan(int colSpan) {
        this.colSpan = colSpan;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public boolean isMergeCell() {
        return isMergeCell;
    }

    public void setMergeCell(boolean mergeCell) {
        isMergeCell = mergeCell;
    }

    public String getFieldNameDesc() {
        return fieldNameDesc;
    }

    public void setFieldNameDesc(String fieldNameDesc) {
        this.fieldNameDesc = fieldNameDesc;
    }

    public TextBox getTextBox() {
        return textBox;
    }

    public void setTextBox(TextBox textBox) {
        this.textBox = textBox;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
