package ytb.project.dao.tagtable;


import org.apache.ibatis.annotations.Param;
import ytb.project.model.tagtable.ProcessListPriceAllModel;

public interface ProcessPriceListAllMapper {


    ProcessListPriceAllModel selectProcessListPriceAll(@Param("projectId") int projectId, @Param("documentId") int documentId);

}
