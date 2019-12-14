package ytb.common.basic.config.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ytb.common.basic.config.model.SubSysDictModel;

public interface SelectSubSysMapper {
    @Delete("delete from subsys_dict where subsys_id=#{subsysId}")
    void delete(@Param("subsysId") int subsysId);

    @Select("select * from subsys_dict where subsys_id=#{subsysId}")
    SubSysDictModel selectSubsys(@Param("subsysId") int subsysId);

//    @Select("<script>"
//            + "SELECT "
//            + "a.id as 'id',a.create_date as 'createDate',a.content as 'content',"
//            + "a.parent_id as 'parentId',a.first_comment_id as 'firstCommentId',"
//            + "b.id as 'fromUser.id',b.realname as 'fromUser.realname',b.avatar as 'fromUser.avatar',"
//            + "c.id as 'toUser.id',c.realname as 'toUser.realname',c.avatar as 'toUser.avatar' "
//            + "FROM t_demand_comment a "
//            + "LEFT JOIN t_user b ON b.id = a.from_uid "
//            + "LEFT JOIN t_user c ON c.id = a.to_uid "
//            + "WHERE a.demand_id = #{demandId} "
//            + "ORDER BY a.create_date ASC "
//            + "<if test='startNo!=null and pageSize != null '>"
//            + "LIMIT #{startNo},#{pageSize}"
//            + "</if>"
//            + "</script>")
//    public List<DemandComment> listDemandComment(@Param("demandId") Long demandId,
//　　　　　　　　　　　　　　　　　　　　　　　　　　　　　@Param("startNo") Integer pageNo,
//　　　　　　　　　　　　　　　　　　　　　　　　　　　　　@Param("pageSize") Integer pageSize);

}
