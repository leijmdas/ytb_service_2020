package ytb.project.dao;


import org.apache.ibatis.annotations.Param;
import ytb.project.model.ShipmentNumberModel;
import ytb.project.view.model.ProjectTaskViewModel.ViewShipmentNumberModel;

import java.util.List;
import java.util.Map;

public interface ShipmentNumberMapper {


    void addShipmnetNumber(ShipmentNumberModel shipmentNumber);

    List<Map<String, Object>> selectShipNumber(@Param("projectId") int projectId, @Param("phase") int phase, @Param("userId") int userId);

    List<ViewShipmentNumberModel> selectViewShipmentNumberModel(@Param("projectId") int projectId, @Param("phase") int phase, @Param("userId") int userId);

}
