package ytb.common.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ytb.common.context.model.Ytb_Model;
import ytb.common.test.rest.impl.PrjType;

/**
 * 学生实体类
 * Created by ASUS on 2018/5/4
 */

//@Scope("prototype")
@Primary
@Repository
public class Student extends Ytb_Model implements IStudent {
    private String name;
    private  int age=1;

    @Autowired
    PrjType prjType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public void setPrjType(PrjType prjType) {
        this.prjType = prjType;
    }

    public PrjType getPrjType() {
        return prjType;
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
}
