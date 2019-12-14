package ytb.log.notify.dao;

import org.apache.ibatis.annotations.Param;
import ytb.log.notify.model.SystemNoticeModel;

import java.util.List;
import java.util.Map;

/**
 * Package: ytb.log.notify.dao
 * Author: ZCS
 * Date: Created in 2018/11/22 14:01
 */
public interface SystemNoticeMapper {

    //获取列表数据
    List<Map<String,Object>>  getMessageList(Map<String,Object> map);

    //获取总条数
    int getMessageTotal(@Param("title") String title,@Param("type") int type);


    //新增系统通知
    Integer addMessageInfo(SystemNoticeModel systemNoticeModel);
    //获取系统通知ById
    SystemNoticeModel getMessageById(Integer id);

    //删除系统通知
    void deleteSysNotices(Integer id);

    //修改系统通知
    void updateSysNotices(SystemNoticeModel systemNoticeModel);

    //查询未读的系统的通知
   Integer getUnReadSysNoticesNumber(Integer userId);

   //获取用户系统通知列表
   List<SystemNoticeModel>  getUserSysNotices(Map<String,Object> map);
}
