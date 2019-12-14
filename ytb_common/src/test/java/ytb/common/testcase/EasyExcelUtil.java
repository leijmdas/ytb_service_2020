package ytb.common.testcase;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import ytb.common.basic.config.model.Dict_ConfigModel;
import ytb.common.basic.config.model.Dict_ErrorCode;
import ytb.common.basic.config.service.ConfigDAOService;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.service.SafeContext;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyExcelUtil {
 /*   static List<ExcelPropertyIndexModel> getData() {
        List<ExcelPropertyIndexModel> list = new ArrayList<>();
        ExcelPropertyIndexModel model = new ExcelPropertyIndexModel();

        list.add(model);
        return list;
    }*/

    public static class C<T extends BaseRowModel> {
        void get(T t) {
            System.out.println(t.getClass());
        }

        public void save2Exls(T t, List<? extends BaseRowModel> data, String
                fname) throws FileNotFoundException {
            ConfigDAOService configDAOService = new ConfigDAOService();
            //List<Dict_ConfigModel> dict_configModels = configDAOService.getDictConfig();
            try (OutputStream out = new FileOutputStream(fname)) {

                ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
                //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
                Sheet sheet = new Sheet(1, 0, t.getClass());
                sheet.setSheetName("停车场帐单");
                Map columnWidth = new HashMap();
                columnWidth.put(0, 5000);
                columnWidth.put(1, 5000);
                columnWidth.put(2, 5000);
                columnWidth.put(3, 5000);
                columnWidth.put(4, 5000);
                sheet.setColumnWidthMap(columnWidth);
                writer.write(data, sheet);

                writer.finish();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // System.out.println(dict_configModels);
        }
    }

    static List<List<String>> getDatas() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        List<List<String>> lst = new ArrayList<>();
        lst.add(list);
        return lst;
    }

    public static void save2Exls0(Class<? extends BaseRowModel> clazz, List<?
            extends BaseRowModel> data, String fname) throws IOException {
        try (OutputStream out = new FileOutputStream(fname)) {

            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet = new Sheet(1, 0, clazz);
//            sheet.setSheetName("停车场帐单");
//            Map columnWidth = new HashMap();
//            columnWidth.put(0, 5000);
//            columnWidth.put(1, 5000);
//            columnWidth.put(2, 5000);
//            columnWidth.put(3, 5000);
//            columnWidth.put(4, 5000);
//            sheet.setColumnWidthMap(columnWidth);
            writer.write(data, sheet);

            writer.finish();
        }

    }

    public static void save2Exls(String fname, Class<? extends BaseRowModel> clazz, List<? extends BaseRowModel> data) throws IOException {
        try (OutputStream out = new FileOutputStream(fname)) {

            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1, 0, clazz);
            writer.write(data, sheet);
            writer.finish();
        }

    }

    /**
     * 创建目录
     * @param destDirName   目标目录名
     * @return 目录创建成功放回true，否则返回false
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已存在！");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建目标目录
        if (dir.mkdir()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }

    /**
     * 创建单个文件
     * @param destFileName    目标文件名
     * @return 创建成功，返回true，否则返回false
     */
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        // 判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            // 如果目标文件所在的文件夹不存在，则创建父文件夹
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在的目录失败！");
                return false;
            }
        }
        // 创建目标文件
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                System.out.println("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out
                    .println("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }

    public static String createTempFile(String prefix, String suffix,
                                        String dirName) {
        File tempFile = null;
        if (dirName == null) {
            try {
                // 在默认文件夹下创建临时文件
                tempFile = File.createTempFile(prefix, suffix);
                // 返回临时文件的路径
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("创建临时文件失败!" + e.getMessage());
                return null;
            }
        } else {
            File dir = new File(dirName);
            // 如果临时文件所在目录不存在，首先创建
            if (!dir.exists()) {
                if (createDir(dirName)) {
                    System.out.println("创建临时文件失败，不能创建临时文件所在的目录！");
                    return null;
                }
            }
            try {
                // 在指定目录下创建临时文件
                tempFile = File.createTempFile(prefix, suffix, dir);
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("创建临时文件失败!" + e.getMessage());
                return null;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ConfigDAOService configDAOService = new ConfigDAOService();
        List<Dict_ConfigModel> dict_configModels = configDAOService.getDictConfig();
//        try(OutputStream out = new FileOutputStream("C:/test6.xlsx")){
//
//            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
//            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
//            Sheet sheet1 = new Sheet(1, 0,Dict_ConfigModel.class);
//            Map columnWidth = new HashMap();
//            columnWidth.put(0, 5000);
//            columnWidth.put(1, 5000);
//            columnWidth.put(2, 5000);
//            columnWidth.put(3, 5000);
//            columnWidth.put(4, 5000);
//            sheet1.setColumnWidthMap(columnWidth);
//            sheet1.setSheetName("test");
////          for(int i=0;i<10;i++) {
////              writer.write0(getDatas(), sheet1);
////              writer.write(getData(), sheet1);
////              writer.write0(getDatas(), sheet1);
////          }
//            writer.write(dict_configModels, sheet1);
//
//            writer.finish();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //save2Exls(Dict_ConfigModel.class,dict_configModels,"c:/停止场201902帐单.xlsx");
        List<Dict_ErrorCode> errorCodes = configDAOService.getDictErrorCode();
        save2Exls("c:/Dict_ErrorCode1.xlsx", Dict_ErrorCode.class, errorCodes);
        System.out.println(errorCodes);
        String s=createTempFile("Export",".xlsx","c:/");
        System.out.println(s);
        //save2Exls(s, Dict_ErrorCode.class, errorCodes);
        LoginSso loginSso= SafeContext.getLog_sso("f419b596e19b4b768b7e210ec6904c96");
        List<LoginSso> loginSsos=new ArrayList<>();
        loginSsos.add (loginSso);
        loginSsos.add (loginSso);
        loginSsos.add (loginSso);
        loginSsos.add (loginSso);
        loginSsos.add (loginSso);
        save2Exls(s, LoginSso.class, loginSsos);


    }
}
