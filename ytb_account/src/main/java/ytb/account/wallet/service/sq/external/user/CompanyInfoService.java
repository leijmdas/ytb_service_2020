package ytb.account.wallet.service.sq.external.user;

import org.apache.ibatis.annotations.Param;
import ytb.account.wallet.model.CompanyInfo;
import ytb.account.wallet.model.CompanyInfoExample;

import java.util.List;

public interface CompanyInfoService {
    long countByExample(CompanyInfoExample example);

    int deleteByExample(CompanyInfoExample example);

    int deleteByPrimaryKey(Integer companyId);

    int insert(CompanyInfo record);

    int insertSelective(CompanyInfo record);

    List<CompanyInfo> selectByExample(CompanyInfoExample example);

    CompanyInfo selectByPrimaryKey(Integer companyId);

    int updateByExampleSelective(@Param("record") CompanyInfo record, @Param("example") CompanyInfoExample example);

    int updateByExample(@Param("record") CompanyInfo record, @Param("example") CompanyInfoExample example);

    int updateByPrimaryKeySelective(CompanyInfo record);

    int updateByPrimaryKey(CompanyInfo record);
}