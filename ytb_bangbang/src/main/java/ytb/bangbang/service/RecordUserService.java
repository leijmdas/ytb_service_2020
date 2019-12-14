package ytb.bangbang.service;

import ytb.bangbang.model.Record_UserModel;

import java.util.List;
import java.util.Map;

public interface RecordUserService {
    /**
     * 获取单聊-聊天记录
     * @return
     */
    List<Record_UserModel> getUserRecords(Map<String,Object> map);

    /**
     * 删除聊天记录
     * @param recordId
     * @return
     */
    int delSingFile(int recordId);

}
