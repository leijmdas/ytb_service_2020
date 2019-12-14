package ytb.user.rest.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.codec.digest.DigestUtils;
import ytb.common.basic.safecontext.service.SafeContext;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.utils.pageutil.Query;
import ytb.common.basic.safecontext.model.LoginSso;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.manager.pfUser.service.impl.DictCompanyTypeService;
import ytb.user.context.UserSrvContext;
import ytb.user.model.*;
import ytb.user.service.CompanyCenterService;
import ytb.user.service.UserCenterService;
import ytb.user.service.impl.UserCenterServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyCenter {
    static UserSrvContext getInst() {
        return UserSrvContext.getInst();
    }

    int retcode = 0;
    String retmsg = "成功";
    String msgBody ="{}";

    public MsgResponse process(MsgRequest req, RestHandler handler,HttpServletRequest request) {
        CompanyCenterService companyCenterService = getInst().getCompanyCenterService();

        //获取公司荣誉
        if(req.cmd.equals("getCompanyHonorListById")){
            int honorType = req.msgBody.getInteger("honorType");
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();
            if(companyId == null ||companyId.equals("")){
                retcode=1;
                retmsg="请求失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }

            List<CompanyHonorModel> list = companyCenterService.getCompanyHonorListById(companyId,honorType);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //添加公司荣誉
        else if(req.cmd.equals("saveOrUpdateCompanyHonor")){

            JSONArray json = req.msgBody.getJSONArray("json");

            companyCenterService.saveOrUpdateCompanyHonor(json,req.token);
        }

        //删除公司荣誉
        else if(req.cmd.equals("deleteCompanyHonor")){
            int honorId = req.msgBody.getInteger("honorId");
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();
            companyCenterService.deleteCompanyHonor(honorId,companyId);
        }

        //修改公司荣誉
        else if(req.cmd.equals("updateCompanyHonor")){
            CompanyHonorModel companyHonorModel = req.msgBody.toJavaObject(req.msgBody,CompanyHonorModel.class);
            companyCenterService.updateCompanyHonor(companyHonorModel);
        }

        //获取已上市产品
        else if(req.cmd.equals("getCompanyProductListById")){
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();
            if(companyId == null ||companyId.equals("")){
                retcode = 1;
                retmsg = "请求失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
            List<CompanyProductModel> list = companyCenterService.getCompanyProductListById(companyId);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //新增公司已上市产品
        else if(req.cmd.equals("saveOrUpdateCompanyProduct")){
            JSONArray json = req.msgBody.getJSONArray("json");
            companyCenterService.saveOrUpdateCompanyProduct(json,req.token);
        }

        //删除以上市产品
        else if(req.cmd.equals("deleteCompanyProduct")){
            int productId = req.msgBody.getInteger("productId");
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();
            if(companyId == null ||companyId.equals("")){
                retcode = 1;
                retmsg ="请求失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
            companyCenterService.deleteCompanyProduct(productId,companyId);
        }

        // 修改已上市产品
        else if(req.cmd.equals("updateCompanyProduct")){
            CompanyProductModel companyProjectModel = req.msgBody.toJavaObject(req.msgBody,CompanyProductModel.class);
            companyCenterService.updateCompanyProduct(companyProjectModel);
        }

        //获取公司信息
        else if(req.cmd.equals("getCompanyInfoById")){
            Integer companyId =getLoginSsoJson(req.token).getCompanyId();

            CompanyInfoModel companyInfoModel = companyCenterService.getCompanyInfoById(companyId);
            msgBody="{\"list\":"+JSON.toJSON(companyInfoModel).toString()+"}";
        }

        //添加公司信息
        else if(req.cmd.equals("addCompanyInfo")){
            CompanyInfoModel companyInfoModel = req.msgBody.toJavaObject(req.msgBody,CompanyInfoModel.class);
            companyCenterService.addCompanyInfo(companyInfoModel);

        }

        //修改公司信息
        else if(req.cmd.equals("updateCompanyInfo")){
            CompanyInfoModel companyInfoModel = req.msgBody.toJavaObject(req.msgBody,CompanyInfoModel.class);
            companyCenterService.updateCompanyInfo(companyInfoModel);
        }


        //获取公司环境照片
        else if(req.cmd.equals("getCompanyEnvListById")){
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();

            List<CompanyEnvModel> list = companyCenterService.getCompanyEnvListById(companyId);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //添加公司单位环境照片
        else if(req.cmd.equals("addCompanyEnv")){
           String lifeImgId = req.msgBody.getString("lifeImgId");
            String workImgId = req.msgBody.getString("workImgId");

            companyCenterService.addCompanyEnv(lifeImgId,workImgId,req.token);

        }

        //删除公司单位环境照片
        else if(req.cmd.equals("deleteCompanyEnv")){
            int companyId = req.msgBody.getInteger("companyId");
            int envId = req.msgBody.getInteger("envId");
            companyCenterService.deleteCompanyEnv(companyId,envId);
        }

        //修改公司单位环境照片
        else if(req.cmd.equals("updateCompanyEnv")){
            CompanyEnvModel companyEnvModel = req.msgBody.toJavaObject(req.msgBody,CompanyEnvModel.class);
            companyCenterService.updateCompanyEnv(companyEnvModel);

        }

        //获取公司设备列表
        else if(req.cmd.equals("getCompanyDeviceListById")){
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();

            if(companyId == null ||companyId.equals("")){
                retcode=1;
                retmsg="请求失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
            List<CompanyDeviceModel> list = companyCenterService.getCompanyDeviceListById(companyId);

            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //新增公司主要设备
        else if(req.cmd.equals("saveOrUpdateCompDevice")){

             JSONArray json = req.msgBody.getJSONArray("json");
             companyCenterService.saveOrUpdateCompanyDevice(json,req.token);
        }

        //删除公司主要设备
        else if(req.cmd.equals("deleteCompanyDevice")){
            Integer deviceId = req.msgBody.getInteger("deviceId");
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();
            companyCenterService.deleteCompanyDevice(companyId,deviceId);

        }

        //获取平台外合作项目
        else if(req.cmd.equals("getCompanyProject")){
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();

            if(companyId == null ||companyId.equals("")){
                retcode=1;
                retmsg="请求失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }

           List<CompanyProjectModel> list = companyCenterService.getCompanyProjectListById(companyId);

            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";

        }

        //新增平台外合作项目
        else if(req.cmd.equals("saveOrUpdateCompanyProject")){
            JSONArray json = req.msgBody.getJSONArray("json");

            companyCenterService.saveOrUpdateCompanyProject(json,req.token);
        }

        //删除平台外合作项目
        else if(req.cmd.equals("deleteCompanyProject")){
            Integer projectId = req.msgBody.getInteger("projectId");
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();

            if(companyId == null ||companyId.equals("")){
                retcode=1;
                retmsg="请求失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
            companyCenterService.deleteCompanyProject(companyId,projectId);

        }



        //设置公司理念
        else if(req.cmd.equals("updateCompanyIdea")){
            String companyIdea = req.msgBody.getString("companyIdea");
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();
            if(companyId == null ||companyId.equals("")){
                retcode=1;
                retmsg="请求失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
            companyCenterService.updateCompanyIdea(companyId,companyIdea);
        }


        //设置公司员工学历占比
        else if(req.cmd.equals("setCompanyEmpEdu")){
            Integer graduateNumber = req.msgBody.getInteger("graduateNumber");
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();

            Integer collegeNumber = req.msgBody.getInteger("collegeNumber");
            Integer otherNumber = req.msgBody.getInteger("otherNumber");

            Map<String,Object> map = new HashMap<>();
            map.put("graduateNumber",graduateNumber);
            map.put("companyId",companyId);
            map.put("collegeNumber",collegeNumber);
            map.put("otherNumber",otherNumber);
            companyCenterService.setCompanyEmpEdu(map);
        }

        //获取公司学员占比
        else if(req.cmd.equals("getCompanyEmpEdu")){
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();
            msgBody="{\"list\":"+ JSONObject.toJSONString(companyCenterService.getCompanyEmpEdu(companyId), SerializerFeature.WriteMapNullValue)+"}";
        }


        //新增公司近三年收入
        else if(req.cmd.equals("addCompanyIncome")){
            JSONArray json = req.msgBody.getJSONArray("json");
            companyCenterService.addCompanyIncome(json,req.token);
        }

        //查看近三年年收入
        else if(req.cmd.equals("selectCompanyIncomeList")){
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();

            List<CompanyIncomeModel> list = companyCenterService.getCompanyIncomeListById(companyId);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //获取公司专利
        else if(req.cmd.equals("getCompanyPatentList")){
            Integer patentType = req.msgBody.getInteger("patentType");
            Integer companyId = getLoginSsoJson(req.token).getCompanyId();

            if(companyId == null ||companyId.equals("")){
                retcode=1;
                retmsg="请求失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
            List<CompanyPatentModel> list = companyCenterService.getCompanyPatentListById(companyId,patentType);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }


        //删除公司专利
        else if(req.cmd.equals("deleteCompanyPatent")){
            Integer patentId = req.msgBody.getInteger("patentId");
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();

            if(companyId == null ||companyId.equals("")){
                retcode=1;
                retmsg="请求失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
            companyCenterService.deleteCompanyPatent(companyId,patentId);
        }

        //设置公司专利
        else if(req.cmd.equals("saveOrUpdateCompanyPatent")){

            JSONArray json = req.msgBody.getJSONArray("json");

            companyCenterService.saveOrUpdateCompanyPatent(json,req.token);
        }



        //0在职 1离职,获取公司员工
        else if(req.cmd.equals("getCompanyEmpListByParam")){
            Integer empType = req.msgBody.getInteger("empType");
            String nickName = req.msgBody.getString("nickName");
            Integer companyId =  getLoginSsoJson(req.token).getCompanyUserId();

            if(companyId == null ||companyId.equals("")){
                retcode=1;
                retmsg="请求失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }

            List<Map<String,Object>> list = companyCenterService.getCompanyEmpList(companyId,empType,nickName);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }


        //添加公司员工
        else if(req.cmd.equals("addCompanyEmployees")){

            String phone = req.msgBody.getString("mobile");
            String nickName = req.msgBody.getString("nickName");
            String ip = "mysql.kunlong.com" /*YtbContext.getYtb_context().getIpUtils().getIpAddr(request)*/;
            String idCard = req.msgBody.getString("idCard");
            String password = DigestUtils.md5Hex(req.msgBody.getString("password"));//MD5加密

           int returnId = companyCenterService.addCompanyEmployees( nickName, phone, idCard,  password, ip,req.token);
            if(returnId <=0){
                retcode=1;
                retmsg="邀请好友失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
            msgBody="{\"returnId\":"+returnId+"}";
        }

        //查询公司员工(不含在职、离职，查所有)
        else if(req.cmd.equals("getCompanyEmpList")){
            Integer companyId =getLoginSsoJson(req.token).getCompanyUserId();

            if(companyId == null ||companyId.equals("")){
                retcode=1;
                retmsg="请求失败";
                return handler.buildMsg(retcode, retmsg, msgBody);
            }
            List<CompanyEmployeesModel>  list = companyCenterService.getCompanyEmployeesListById(companyId);
            msgBody="{\"list\":"+ JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }

        //删除员工
        else if(req.cmd.equals("removeEmp")){
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();

            Integer empId = req.msgBody.getInteger("empId");
            Integer userId = req.msgBody.getInteger("userId");

            companyCenterService.deleteCompanyEmp(empId,userId);
        }

        //设置员工备注
        else if(req.cmd.equals("setEmpRemark")){
            Integer empId = req.msgBody.getInteger("empId");
            String remark = req.msgBody.getString("remark");
            CompanyEmployeesModel employeesModel = new CompanyEmployeesModel();
            employeesModel.setRemark(remark);
            employeesModel.setEmployeeId(empId);

            companyCenterService.updateCompanyEmp(employeesModel);
        }



        //新增公司收件地址
        else if(req.cmd.equals("addCompanyAddress")){
            UserAddressModel userAddressModel = new UserAddressModel();
            UserCenterService userCenterService = new UserCenterServiceImpl();

            String area = req.msgBody.getString("area");
            String zipCode = req.msgBody.getString("zipCode");
            String address = req.msgBody.getString("address");
            String phone = req.msgBody.getString("phone");
            String name = req.msgBody.getString("name");
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();
            Integer userId =  getLoginSsoJson(req.token).getUserId();
            if(getLoginSsoJson(req.token).getUserType() ==1){
                companyId = 0;
            }else{
                userId = 0;
            }

            userAddressModel.setCompanyId(companyId);
            userAddressModel.setUserId(userId);
            userAddressModel.setAddress(address);
            userAddressModel.setName(name);
            userAddressModel.setPhone(phone);
            userAddressModel.setArea(area);
            userAddressModel.setZipCode(zipCode);

            int retuanVal = userCenterService.addUserAddress(userAddressModel);

            msgBody="{\"returnId\":"+retuanVal+"}";
        }

        //查询公司收件地址表
        else if(req.cmd.equals("getCompanyAddr")){

            Map params = new HashMap();


            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();
            Integer userId =  getLoginSsoJson(req.token).getUserId();

            if(getLoginSsoJson(req.token).getUserType() ==1){
                companyId = null;
            }else{
                userId = null;
            }

            params.put("page",req.msgBody.getInteger("currPage"));
            params.put("limit",req.msgBody.getInteger("pageSize"));
            params.put("companyId",companyId);
            params.put("userId",userId);
            UserCenterService userCenterService = new UserCenterServiceImpl();

            Query query = new Query(params);
            List<UserAddressModel> list = userCenterService.getUserAddressListById(query);

            int total = userCenterService.queryUserAddressTotal(query);
            PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
            msgBody="{\"list\":"+ JSONObject.toJSONString(pageUtil, SerializerFeature.WriteMapNullValue)+"}";
        }

        //删除公司收件地址
        else if(req.cmd.equals("deleteCompanyAddr")){
            Integer addressId = req.msgBody.getInteger("addressId");
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();
            Integer userId =  getLoginSsoJson(req.token).getUserId();

            if(getLoginSsoJson(req.token).getUserType() ==1){
                companyId = null;
            }else{
                userId = null;
            }


            UserCenterService userCenterService = new UserCenterServiceImpl();
            userCenterService.deleteUserAddress(addressId,userId,companyId);
        }


        //修改公司收件地址
        else if(req.cmd.equals("updateCompanyAddr")){
            UserAddressModel userAddressModel = req.msgBody.toJavaObject(req.msgBody,UserAddressModel.class);
            UserCenterService userCenterService = new UserCenterServiceImpl();
            userCenterService.updateUserAddress(userAddressModel);

        }

        //设置默认收件地址
        else if(req.cmd.equals("setDefaultAddr")){
            //只允许有条默认的收件地址
            Integer addressId = req.msgBody.getInteger("addressId");
            Integer companyId = req.msgBody.getInteger("companyId");
            boolean isDefault = req.msgBody.getBoolean("isDefault");

            UserCenterService userCenterService = new UserCenterServiceImpl();
            userCenterService.setDefaultAddr(addressId,companyId,isDefault);

        }

        //获取公司专业标签
        else if(req.cmd.equals("getCompanyTag")){

            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();
            msgBody="{\"list\":"+ JSONObject.toJSONString(companyCenterService.getCompanyTag(companyId), SerializerFeature.WriteMapNullValue)+"}";

        }

        //新增公司专业能力标签
        else if(req.cmd.equals("addCompanyTag")){
            CompanyTagModel userTagModel = req.msgBody.toJavaObject(req.msgBody,CompanyTagModel.class);
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();
            userTagModel.setCompanyId(companyId);

            companyCenterService.addCompanyTag(userTagModel);

        }

        //删除专业能力标签
        else if(req.cmd.equals("deleteCompanyTag")){
            Integer tagId = req.msgBody.getInteger("tagId");
            Integer companyId =  getLoginSsoJson(req.token).getCompanyId();

            companyCenterService.deleteCompanyTagById(tagId,companyId);

        }



        //公司详细信息资料
        else if(req.cmd.equals("getCompanyBaseInformation")){

            Map<String,Object> returnMap = new HashMap<>();

            Integer companyId = req.msgBody.getInteger("companyId");
            try{

                //公司基本信息
                CompanyInfoModel companyInfoModel = companyCenterService.getCompanyInfoById(companyId);

                //单位年收入
                List<CompanyIncomeModel> companyIncome = companyCenterService.getCompanyIncomeListById(companyId);

                //公司主要设备
                List<CompanyDeviceModel> companyDevice = companyCenterService.getCompanyDeviceListById(companyId);

                //已上市产品
                List<CompanyProductModel> companyProductList = companyCenterService.getCompanyProductListById(companyId);

                //平台外合作项目
                List<CompanyProjectModel> companyPlaPro = companyCenterService.getCompanyProjectListById(companyId);

                //单位环境照片
                List<CompanyEnvModel> CompanyEnvList = companyCenterService.getCompanyEnvListById(companyId);

                //公司专利
                Map<String,Object>  patentList = companyCenterService.getCompanyPatent(companyId);

                //获得荣誉
                Map<String,Object> honorList = companyCenterService.getAllCompanyHonorList(companyId);



                returnMap.put("companyInfo",companyInfoModel);//基本信息
                returnMap.put("companyIncomeList",companyIncome);//收入
                returnMap.put("companyDeviceList",companyDevice);//主要设备
                returnMap.put("companyProductList",companyProductList);//已上市产品
                returnMap.put("companyPlaProList",companyPlaPro);//
                returnMap.put("CompanyEnvList",CompanyEnvList);
                returnMap.put("sqfmList",patentList.get("sqfmList"));//授权发明
                returnMap.put("sqsyList",patentList.get("sqsyList"));//授权实用
                returnMap.put("sqwgList",patentList.get("sqwgList"));//授权外观
                returnMap.put("sqrjList",patentList.get("sqrjList"));//软件著作

                returnMap.put("awadList",honorList.get("awadList"));//获得的奖励
                returnMap.put("unitAuthList",honorList.get("unitAuthList"));//单位资质认证
                returnMap.put("proAuthList",honorList.get("proAuthList"));//产品认证

                msgBody = "{\"list\":"+JSONObject.toJSONString(returnMap, SerializerFeature.WriteMapNullValue)+"}";
            }catch (Exception e){
                e.printStackTrace();
                throw new YtbError(YtbError.CODE_INVALID_REST);
            }
        }

        //获取公司类别
        else if(req.cmd.equals("getCompanyTypeList")){
            Integer parentId = req.msgBody.getInteger("parentId");

            List list = new DictCompanyTypeService().getDictCompanyTypeListByParentID(parentId);

            msgBody = "{\"list\":"+JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue)+"}";
        }





        else{
            retcode=1;
            retmsg=req.cmd+"在"+req.cmdtype+"中不存在";
            return handler.buildMsg(retcode, retmsg, msgBody);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    private LoginSsoJson getLoginSsoJson(String token){
        LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);

        LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
        return loginSsoJson;
    }
}
