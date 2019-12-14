package kunshan.service;


import com.alibaba.fastjson.JSONObject;
import ytb.common.test.demo.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DemoServiceImpl implements DemoService {
    public static class Student0 implements Serializable {
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

        String name="demo";
        int age;

        public String toString(){
           return JSONObject.toJSONString(this);
        }
    }

    public List<String> getPermissions(Long id) {
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        demo.add(JSONObject.toJSONString(new Student()));
        demo.add( new Student().toString() );
        return demo;
    }
    @Override
    public JSONObject runAgent(JSONObject req) {
        return req;
    }

    @Override
    public JSONObject exec(JSONObject req) {
        return req;
    }


//    public static void main(String[] args) throws IOException {
//
//        System.out.print(new Student());
//
//
//    }
}