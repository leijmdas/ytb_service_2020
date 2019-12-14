package ytb.common.basic.config.dao;


import ytb.common.basic.config.model.Dict_ErrorCode;

import java.util.List;

public interface ErrorCodeMapper {

    List<Dict_ErrorCode> getErrorMsg();

}
