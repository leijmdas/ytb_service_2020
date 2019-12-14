package ytb.manager.sysuser.model;


import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * Package: ytb.manager.sysuser.model
 * Author: ZCS
 * Date: Created in 2018/8/22 10:53
 */
public class Sys_MenuModel implements  Comparable<Sys_MenuModel>{

    private int menuId = 0 ;
    private String menuName = "";
    private int parentId = 0;
    private int menuType = 0;
    private String menuUrl = "";
    private String menuCode= "";
    private int orderNum = 0;
    private String parentName = "";
    private int createBy = 1;
    private Date createTime = new Date();
    private  boolean isSelect = false;
    private List<Sys_MenuModel> children= Lists.newArrayList();

    public boolean equals(Object arg0){
        if(!(arg0 instanceof Sys_MenuModel)){
            return false;
        }
        Sys_MenuModel sysMenuModel = (Sys_MenuModel)arg0;

        return sysMenuModel.menuId==this.menuId;

    }
    //覆盖Object里的hashCode方法
    public int hashCode() {
        return menuId;//返回名字的哈希码。
    }

    public boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<Sys_MenuModel> getChildren() {
        return children;
    }

    public void setChildren(List<Sys_MenuModel> children) {
        this.children = children;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getMenuType() {
        return menuType;
    }

    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public int compareTo(Sys_MenuModel o) {
        return o.menuId-menuId;
    }
}
