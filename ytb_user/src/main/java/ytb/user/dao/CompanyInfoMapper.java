package ytb.user.dao;

import org.apache.ibatis.annotations.Param;
import ytb.user.model.CompanyInfoModel;

import java.util.Map;

/**
 * Package: ytb.user.dao
 * Author: ZCS
 * Date: Created in 2018/9/7 16:03
 */
public interface CompanyInfoMapper {

    //获取公司信息
    CompanyInfoModel getCompanyInfoById(int companyId);

    CompanyInfoModel getCompanyInfoByName(String companyName);

    CompanyInfoModel getCompanyInfoByCompanyId(Integer getCompanyInfoByCompanyId);


    int insertSelective(CompanyInfoModel companyInfoModel);

    //添加公司信息
    void addCompanyInfo(CompanyInfoModel companyInfoModel);

    //修改公司信息
    void updateCompanyInfo(CompanyInfoModel companyInfoModel);

    //设置公司理念
    void updateCompanyIdea(@Param("companyId") int companyId, @Param("companyIdea") String companyIdea);

    void setCompanyEmpEdu(Map<String, Object> map);

    //获取公司学员占比
    Map<String,Object> getCompanyEmpEdu(Integer companyId);
}
