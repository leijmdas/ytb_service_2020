package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.GroupApplyInfoDao;
import ytb.bangbang.model.Group_Apply_InfoModel;
import ytb.bangbang.service.GroupApplyInfoService;
import ytb.bangbang.util.MyBatisUtil;

public class GroupApplyInfoServiceImpl implements GroupApplyInfoService {
    @Override
    public int AddRecord(Group_Apply_InfoModel group_apply_infoModel) {
        try(SqlSession session=MyBatisUtil.getSession()) {
            GroupApplyInfoDao dao=session.getMapper(GroupApplyInfoDao.class);
            return  dao.AddRecord(group_apply_infoModel);
        }
    }

    @Override
    public int setIsAgree(Group_Apply_InfoModel group_apply_infoModel) {
        try(SqlSession session=MyBatisUtil.getSession()) {
            GroupApplyInfoDao dao=session.getMapper(GroupApplyInfoDao.class);
            return  dao.setIsAgree(group_apply_infoModel);
        }
    }

    @Override
    public Group_Apply_InfoModel getUserApplyGroupInfo(int inviteId) {
        try(SqlSession session=MyBatisUtil.getSession()) {
            GroupApplyInfoDao dao=session.getMapper(GroupApplyInfoDao.class);
            return  dao.getUserApplyGroupInfo(inviteId);
        }
    }

    @Override
    public Integer getApplyTypeById(int id) {
        try (SqlSession session=MyBatisUtil.getSession()) {
            GroupApplyInfoDao dao=session.getMapper(GroupApplyInfoDao.class);
            return dao.getApplyTypeById(id);
        }
    }

    @Override
    public int changeIsAgree(int inviteStatus, int id) {
        try (SqlSession session=MyBatisUtil.getSession()) {
            GroupApplyInfoDao dao=session.getMapper(GroupApplyInfoDao.class);
            return dao.changeIsAgree(inviteStatus,id);
        }
    }
}
