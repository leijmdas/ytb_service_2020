package ytb.manager.tagtable.service.impl;

import ytb.common.utils.YtbSql;
import ytb.common.basic.safecontext.model.LoginSsoJson;
import ytb.common.ytblog.YtbLog;
import ytb.manager.tagtable.model.business.VwareaSalaryModel;
import ytb.manager.userCredit.model.DictCreditModel;
import ytb.manager.userCredit.service.impl.DictCreditModelServiceImpl;
import ytb.common.context.model.YtbError;
import ytb.common.context.model.Ytb_Model;

import java.math.BigDecimal;
import java.util.*;

public class SelectProjectMember {
    public static class DayPayInfoResult extends Ytb_Model {
        public DayPayInfoResult(DayPayInfo dayPayInfo) {
            day_pay = dayPayInfo.getDayPay();
            area_id = dayPayInfo.getAreaId();
            area_name = dayPayInfo.getAreaName();
            user_id = dayPayInfo.getUserId();
            user_name = dayPayInfo.getUserName();
        }

        float day_pay;
        int area_id;
        String area_name;
        int user_id;
        String user_name;

        String credit_grade;
        public float getDay_pay() {
            return day_pay;
        }

        public void setDay_pay(float day_pay) {
            this.day_pay = day_pay;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getCredit_grade() {
            return credit_grade;
        }

        public void setCredit_grade(String credit_grade) {
            this.credit_grade = credit_grade;
        }



    }

    static class DayPayInfo extends Ytb_Model {
        float dayPay;

        int areaId;
        String areaName;

        int userId;
        String userName;
        //int companyId
        String creditGrade;

        public float getDayPay() {
            return dayPay;
        }

        public void setDayPay(float dayPay) {
            this.dayPay = dayPay;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCreditGrade() {
            return creditGrade;
        }

        public void setCreditGrade(String creditGrade) {
            this.creditGrade = creditGrade;
        }

    }

    public static Collection<DayPayInfoResult>  selectList(int projectId, int documentId, LoginSsoJson loginSsoJson) {

        Integer userId = loginSsoJson.isUserManager() ? 78 : loginSsoJson.getUserId();

        List<DayPayInfo> dayPayInfos = dbSelectList(projectId, documentId, userId);

        dayPayInfos = buildAreaDayPay(dayPayInfos);
        Map<Integer,DayPayInfoResult>  payInfoResultMap = new HashMap<>();
        for (DayPayInfo dayPayInfo : dayPayInfos) {
            DayPayInfoResult result = new DayPayInfoResult(dayPayInfo);
            payInfoResultMap.put(result.getUser_id(),result);
        }
        return payInfoResultMap.values();
    }

    //workplan_id
    static List<DayPayInfo> dbSelectList(int projectId, int documentId, int userId) {

        StringBuilder sqlPM = new StringBuilder();
        sqlPM.append(" select 0 as day_pay, a.area_id, a.user_id, city_id,credit_grade,");
        sqlPM.append(" concat(a.nick_name,'(' ,ytb_project.fnReturnInviteStatusName(s.status),')') as user_name ");
        sqlPM.append(" from ytb_user.vw_user_info_credit a  ");
        sqlPM.append(" inner join ytb_project.talk_invite_status s using(user_id) ");
        sqlPM.append(" where ifnull(s.project_id," + projectId + ")=").append(projectId);
        sqlPM.append(" and ifnull(s.document_id," + documentId + ")=").append(documentId);

        YtbLog.logDebug(sqlPM);
        List<DayPayInfo> dayPayInfos = YtbSql.selectList(sqlPM,DayPayInfo.class);

        StringBuilder sqlPB = new StringBuilder();
        sqlPB.append(" select 0 as day_pay, area_id, user_id,city_id,credit_grade,");
        sqlPB.append(" nick_name as user_name ");
        sqlPB.append(" from ytb_user.vw_user_info_credit a  ");
        sqlPB.append(" where user_id=").append(userId);
        YtbLog.logDebug(sqlPB);
        dayPayInfos.addAll(YtbSql.selectList(sqlPB,DayPayInfo.class));
        return dayPayInfos;

    }

    static int findCredit_q(List<DictCreditModel> creditModels, String creditGrade) {
        for (DictCreditModel m : creditModels) {
            if (m.getCreditGrade().equalsIgnoreCase(creditGrade)) {
                return m.getCreditQ();
            }

        }
        return 4;
    }

    static VwareaSalaryModel findVwareaSalaryModel(List<VwareaSalaryModel> lst, int area_id) {
        for (VwareaSalaryModel m : lst) {
            if (m.getAreaId() == area_id) {
                return m;
            }

        }
        return new VwareaSalaryModel();
    }

    static List<DayPayInfo> buildAreaDayPay(List<DayPayInfo> dayPayInfos) {

        List<DictCreditModel> creditModels = new DictCreditModelServiceImpl().selectList();
        List<VwareaSalaryModel> salaryModels = new VwareaSalaryModelServiceImpl().selectList();
        for ( DayPayInfo  dayPayInfo : dayPayInfos) {
            String credit = dayPayInfo.getCreditGrade();
            int area_id = dayPayInfo.getAreaId();
            int credit_q = findCredit_q(creditModels, credit);
            VwareaSalaryModel vm = findVwareaSalaryModel(salaryModels, area_id);

            String area_name = vm.getName();
            BigDecimal b = vm.getDayPay().multiply(new BigDecimal(credit_q));
            dayPayInfo.setAreaName(area_name);
            if(BigDecimal.ZERO.compareTo(BigDecimal.valueOf(b.floatValue()))==0){
                throw new YtbError(YtbError.CODE_DEFINE_ERROR,"日薪为0，系统错误！");
            }
            dayPayInfo.setDayPay(b.floatValue()); // m.remove("credit_grade");
        }
        return dayPayInfos;
    }
}
