package ytb.manager.sysuser.model;


import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * Package: ytb.manager.sysuser.model
 * Author: ZCS
 * Date: Created in 2018/8/22 10:53
 */
public class LeftMenuModel {

    private int menuId = 0 ;
    private String menuName = "";
    private int parentId = 0;
    private String menuUrl = "";
    private String menuCode= "";
    private List<LeftMenuModel> children= Lists.newArrayList();

    public boolean equals(Object arg0){
        if(!(arg0 instanceof LeftMenuModel)){
            return false;
        }
        LeftMenuModel sysMenuModel = (LeftMenuModel)arg0;

        return sysMenuModel.menuId==this.menuId;

    }
    //覆盖Object里的hashCode方法
    public int hashCode() {
        return menuId;//返回名字的哈希码。
    }


    public List<LeftMenuModel> getChildren() {
        return children;
    }

    public void setChildren(List<LeftMenuModel> children) {
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



}
