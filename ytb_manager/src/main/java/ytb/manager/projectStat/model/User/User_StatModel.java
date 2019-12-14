package ytb.manager.projectStat.model.User;

import ytb.common.utils.YtbSql;import ytb.common.utils.YtbUtils;

import java.util.Date;

public class User_StatModel {
    int id;
    String name;
    int number1;
    int number2;

    //1--项目分类 2地区 3 公司类型
    short statType;

    public short getStatType() {
        return statType;
    }

    public void setStatType(short statType) {
        this.statType = statType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public String toString(){
       return  YtbUtils.toJSONStringPretty(this);
    }
}
