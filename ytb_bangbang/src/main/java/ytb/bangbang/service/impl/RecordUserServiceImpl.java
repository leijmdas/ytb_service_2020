package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.RecordUserDao;
import ytb.bangbang.model.Record_UserModel;
import ytb.bangbang.service.RecordUserService;
import ytb.bangbang.util.MyBatisUtil;

import java.util.List;
import java.util.Map;

public class RecordUserServiceImpl implements RecordUserService {
    @Override
    public List<Record_UserModel> getUserRecords(Map<String,Object> map) {
        try (SqlSession session = MyBatisUtil.getSession()){
            RecordUserDao dao=session.getMapper(RecordUserDao.class);
            return dao.getUserRecords(map);
        }
    }

    @Override
    public int delSingFile(int recordId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            RecordUserDao dao=session.getMapper(RecordUserDao.class);
            return dao.delSingFile(recordId);
        }
    }
}
