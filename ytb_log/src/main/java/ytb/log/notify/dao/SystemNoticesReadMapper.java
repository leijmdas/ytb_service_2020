package ytb.log.notify.dao;

import org.apache.ibatis.annotations.Param;
import ytb.log.notify.model.SystemNoticesReadModel;

import java.util.List;

/**
 * Package: ytb.log.notify.dao
 * Author: ZCS
 * Date: Created in 2018/11/24 11:07
 */
public interface SystemNoticesReadMapper {

    //新增用户系统消息记录
    Integer addSysNoticesReadInfo(SystemNoticesReadModel systemNoticesReadModel);

    //标识系统已读
    void signSysMarkRead(@Param("list") List list,@Param("userId") Integer userID);

    //标记单个删除
    void deleteOneSysNotices(@Param("id") Integer id,@Param("userId") Integer userId);

    //批量新增记录
    void addSysNoticesReadInfoByList(List<SystemNoticesReadModel> list);
}
