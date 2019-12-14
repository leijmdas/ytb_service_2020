package ytb.project.service.invoice;

import org.apache.ibatis.annotations.Param;
import ytb.project.model.*;

import java.util.List;

public interface InvoiceService {
    long countByExample(InvoiceExample example);

    int deleteByExample(InvoiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Invoice record);

    int insertSelective(Invoice record);

    Boolean insertAllSelective(Invoice record, List<InvoiceInfo> list, InvoiceInfoProject project);

    List<Invoice> selectByExample(InvoiceExample example);

    InvoicePojo selectInvoiceAllByKey(Integer id);

    Invoice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    int updateByExample(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    int updateByPrimaryKeySelective(Invoice record);

    int updateByPrimaryKey(Invoice record);

    List<Invoice> selectAll();

    List<InvoicePojo> selectAllAndInfo();
}