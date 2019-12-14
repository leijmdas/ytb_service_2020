package ytb.project.service.invoice;

import org.apache.ibatis.annotations.Param;
import ytb.project.model.*;


import java.util.List;

public interface InvoiceInfoProjectService {
    long countByExample(InvoiceInfoProjectExample example);

    int deleteByExample(InvoiceInfoProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InvoiceInfoProject record);

    int insertSelective(InvoiceInfoProject record);

    List<InvoiceInfoProject> selectByExample(InvoiceInfoProjectExample example);

    InvoiceInfoProject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InvoiceInfoProject record, @Param("example") InvoiceInfoProjectExample example);

    int updateByExample(@Param("record") InvoiceInfoProject record, @Param("example") InvoiceInfoProjectExample example);

    int updateByPrimaryKeySelective(InvoiceInfoProject record);

    int updateByPrimaryKey(InvoiceInfoProject record);

}