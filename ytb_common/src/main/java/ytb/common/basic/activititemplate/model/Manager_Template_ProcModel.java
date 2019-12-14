package ytb.common.basic.activititemplate.model;

import java.util.Date;


public class Manager_Template_ProcModel {

    //流程模板ID
    private int proc_id ;
    //流程模板KEY
    private String proc_code ;
    //流程模板文件名称
    private String proc_file ;
    //模板内容
    private byte[] proc_content ;
    //流程模板类型
    private int proc_type;
    //状态
    private int status ;
    //测试人
    private int test_by;
    //测试结果
    private int test_result;
    //测试时间
    private  Date  test_time =  new Date() ;

    public int getProc_id() {
        return proc_id;
    }

    public void setProc_id(int proc_id) {
        this.proc_id = proc_id;
    }

    public String getProc_code() {
        return proc_code;
    }

    public void setProc_code(String proc_code) {
        this.proc_code = proc_code;
    }

    public String getProc_file() {
        return proc_file;
    }

    public void setProc_file(String proc_file) {
        this.proc_file = proc_file;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTest_by() {
        return test_by;
    }

    public void setTest_by(int test_by) {
        this.test_by = test_by;
    }

    public int getTest_result() {
        return test_result;
    }

    public void setTest_result(int test_result) {
        this.test_result = test_result;
    }

    public Date getTest_time() {
        return test_time;
    }

    public void setTest_time(Date test_time) {
        this.test_time = test_time;
    }

    public byte[] getProc_content() {
        return proc_content;
    }

    public void setProc_content(byte[] proc_content) {
        this.proc_content = proc_content;
    }

    public int getProc_type() {
        return proc_type;
    }

    public void setProc_type(int proc_type) {
        this.proc_type = proc_type;
    }
}
