package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.SingleChatFileDao;
import ytb.bangbang.model.SingleChatFile;
import ytb.bangbang.service.SingleChatFileService;
import ytb.bangbang.util.MyBatisUtil;

import java.util.List;
import java.util.Map;

public class SingleChatFileServiceImpl implements SingleChatFileService {

    @Override
    public int addFile(SingleChatFile file) {
        try (SqlSession session = MyBatisUtil.getSession()){
            SingleChatFileDao dao=session.getMapper(SingleChatFileDao.class);
            return dao.addFile(file);
        }
    }

    @Override
    public int delFile(Integer id) {
        try (SqlSession session = MyBatisUtil.getSession()){
            SingleChatFileDao dao=session.getMapper(SingleChatFileDao.class);
            return dao.delFile(id);
        }
    }

    @Override
    public List<SingleChatFile> getFilelist(Map<String, Object> map) {
        try (SqlSession session = MyBatisUtil.getSession()){
            SingleChatFileDao dao=session.getMapper(SingleChatFileDao.class);
            return dao.getFilelist(map);
        }
    }
}
