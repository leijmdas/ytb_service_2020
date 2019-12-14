package ytb.bangbang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.TalkInviteStatus;
import ytb.bangbang.model.TalkInviteStatusExample;

public interface TalkInviteStatusMapper {
    long countByExample(TalkInviteStatusExample example);

    int deleteByExample(TalkInviteStatusExample example);

    int deleteByPrimaryKey(Integer inviteId);

    int insert(TalkInviteStatus record);

    int insertSelective(TalkInviteStatus record);

    List<TalkInviteStatus> selectByExample(TalkInviteStatusExample example);

    TalkInviteStatus selectByPrimaryKey(Integer inviteId);

    int updateByExampleSelective(@Param("record") TalkInviteStatus record, @Param("example") TalkInviteStatusExample example);

    int updateByExample(@Param("record") TalkInviteStatus record, @Param("example") TalkInviteStatusExample example);

    int updateByPrimaryKeySelective(TalkInviteStatus record);

    int updateByPrimaryKey(TalkInviteStatus record);
}