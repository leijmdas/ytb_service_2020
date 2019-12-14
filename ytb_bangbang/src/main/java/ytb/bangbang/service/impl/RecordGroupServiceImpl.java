package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.RecodGroupDao;
import ytb.bangbang.model.Record_GroupModel;
import ytb.bangbang.service.RecordGroupService;
import ytb.bangbang.util.MyBatisUtil;

import java.util.List;
import java.util.Map;

public class RecordGroupServiceImpl implements RecordGroupService {

    @Override
    public List<Record_GroupModel> getRecordGroupList(Map<String,Object> map) {
        try (SqlSession session = MyBatisUtil.getSession()){
            RecodGroupDao dao=session.getMapper(RecodGroupDao.class);
            return dao.getRecordGroupList(map);
        }
    }

    @Override
    public int delGroupFile(int recordId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            RecodGroupDao dao=session.getMapper(RecodGroupDao.class);
            return dao.delGroupFile(recordId);
        }
    }

    @Override
    public int getFromUserById(int recordId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            RecodGroupDao dao=session.getMapper(RecodGroupDao.class);
            return dao.getFromUserById(recordId);
        }
    }
}
