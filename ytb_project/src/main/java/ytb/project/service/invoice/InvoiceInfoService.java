package ytb.project.service.invoice;

import org.apache.ibatis.annotations.Param;
import ytb.project.model.InvoiceInfo;
import ytb.project.model.InvoiceInfoExample;

import java.util.List;

public interface InvoiceInfoService {
    long countByExample(InvoiceInfoExample example);

    int deleteByExample(InvoiceInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InvoiceInfo record);

    int insertSelective(InvoiceInfo record);

    List<InvoiceInfo> selectByExample(InvoiceInfoExample example);

    InvoiceInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InvoiceInfo record, @Param("example") InvoiceInfoExample example);

    int updateByExample(@Param("record") InvoiceInfo record, @Param("example") InvoiceInfoExample example);

    int updateByPrimaryKeySelective(InvoiceInfo record);

    int updateByPrimaryKey(InvoiceInfo record);
}