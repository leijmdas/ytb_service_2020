package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.GroupChatFileDao;
import ytb.bangbang.model.GroupChatFile;
import ytb.bangbang.service.GroupChatFileService;
import ytb.bangbang.util.MyBatisUtil;

import java.util.List;
import java.util.Map;

public class GroupChatFileServiceImpl implements GroupChatFileService {

    @Override
    public int addFile(GroupChatFile file) {
        try (SqlSession session = MyBatisUtil.getSession()){
            GroupChatFileDao dao=session.getMapper(GroupChatFileDao.class);
            return dao.addFile(file);
        }
    }

    @Override
    public int delFile(Integer id) {
        try (SqlSession session = MyBatisUtil.getSession()){
            GroupChatFileDao dao=session.getMapper(GroupChatFileDao.class);
            return dao.delFile(id);
        }
    }

    @Override
    public List<GroupChatFile> getFilelist(Map<String, Object> map) {
        try (SqlSession session = MyBatisUtil.getSession()){
            GroupChatFileDao dao=session.getMapper(GroupChatFileDao.class);
            return dao.getFilelist(map);
        }
    }
}
