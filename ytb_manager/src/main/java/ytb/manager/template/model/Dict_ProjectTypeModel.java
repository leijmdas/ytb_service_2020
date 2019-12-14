package ytb.manager.template.model;

import ytb.common.context.model.Ytb_Model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dict_ProjectTypeModel extends Ytb_Model {

    //0－－草稿，
    public static final int ProjectType_DRAFT = 0;
    //1－－发布
    public static final int ProjectType_PULISH = 1;
    //2－－停止发布
    public static final int ProjectType_STOP_PUBLISH = 2;
    //---------项目分类类型
    //TEST
    public static int PT_TEST = 0;
    //开发设计
    public static int PT_DEV_DESIGN = 1;
    //采购类型
    public static int PT_PURCHASE = 2;
    //加工类型
    public static int PT_PROCESSING = 3;
    //咨询类
    public static int PT_INQUERY = 4;
    //毕业设计
    public static int PT_FinishSchool = 5;
    //项目分类标识
    private int projectTypeId;
    //项目大为
    private int projectType;

    private int parentId;
    private int repositoryId;

    private String title;
    private int depth;
    private int state;
    private int orderNo;


    private Date createTime;
    private int createBy;

    private float feeRate;//服务费率
    private float taxRate;//普票税率
    private float staxRate;//专票税率

    private float pRate;//个人用户毛利率
    private float cRate;//公司用户毛利率

    private String parentName;
    public  boolean isPublish (){
        return  ProjectType_PULISH==state;
    }

    public boolean NotPurchaseProcessing() {
        return !isPurchaseProcessing();
    }

    public boolean isPurchaseProcessing() {
        return projectType == PT_PURCHASE || projectType == PT_PROCESSING;
    }

    public boolean isPurchase() {
        return projectType == PT_PURCHASE;

    }

    public boolean isProcessing() {
        return projectType == PT_PROCESSING;
    }

    private transient List<Dict_ProjectTypeModel> children = new ArrayList<>();

    public int getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(int projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(int repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getProjectType() {
        return projectType;
    }

    public void setProjectType(int projectType) {
        this.projectType = projectType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public float getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(float feeRate) {
        this.feeRate = feeRate;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(float taxRate) {
        this.taxRate = taxRate;
    }

    public float getStaxRate() {
        return staxRate;
    }

    public void setStaxRate(float staxRate) {
        this.staxRate = staxRate;
    }

    public float getpRate() {
        return pRate;
    }

    public void setpRate(float pRate) {
        this.pRate = pRate;
    }

    public float getcRate() {
        return cRate;
    }

    public void setcRate(float cRate) {
        this.cRate = cRate;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<Dict_ProjectTypeModel> getChildren() {
        return children;
    }

    public void setChildren(List<Dict_ProjectTypeModel> children) {
        this.children = children;
    }
}
