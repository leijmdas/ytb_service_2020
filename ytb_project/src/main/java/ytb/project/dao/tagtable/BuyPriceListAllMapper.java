package ytb.project.dao.tagtable;


import org.apache.ibatis.annotations.Param;
import ytb.project.model.tagtable.BuyPriceListAllModel;

public interface BuyPriceListAllMapper {


    BuyPriceListAllModel select(@Param("projectId") int projectId, @Param("documentId") int documentId);

}
