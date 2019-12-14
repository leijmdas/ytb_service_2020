package ytb.common.context.service;


import ytb.common.basic.config.model.Dict_ErrorCode;

public interface IYtbContext {

    //通过错误英文标识返回错误信息
    Dict_ErrorCode getError_msg(String error_code);

    //系统配置表dict_config(db:ytb_manager)
    String getConfig_value(String config_item);


}
