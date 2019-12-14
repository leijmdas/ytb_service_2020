package ytb.bangbang.service;

import ytb.bangbang.model.Record_GroupModel;

import java.util.List;
import java.util.Map;

public interface RecordGroupService {

    /**
     * 群聊-聊天记录
     */

    List<Record_GroupModel> getRecordGroupList(Map<String,Object> map);

    /**
     * 删除群聊记录
     * @param recordId
     * @return
     */
    int delGroupFile(int recordId);

    int getFromUserById(int recordId);

}
