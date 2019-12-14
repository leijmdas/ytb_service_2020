package ytb.project.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.project.model.InvoiceInfoProject;
import ytb.project.model.InvoiceInfoProjectExample;

public interface InvoiceInfoProjectMapper {
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