package ytb.account.wallet.service.sq.external.project.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.ProjectMapper;
import ytb.account.wallet.model.*;
import ytb.account.wallet.service.sq.external.project.ProjectService;
import ytb.account.wallet.tool.MyBatisUtil;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class ProjectServiceImpl implements ProjectService {


    @Override
    public long countByExample(ProjectExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(ProjectExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer projectId) {
        return 0;
    }

    @Override
    public int insert(Project record) {
        return 0;
    }

    @Override
    public int insertSelective(Project record) {
        return 0;
    }

    @Override
    public List<Project> selectByExample(ProjectExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            ProjectMapper accountTradeMapper = sq.getMapper(ProjectMapper.class);
            List<Project> data = accountTradeMapper.selectByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public Project selectByPrimaryKey(Integer projectId) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            ProjectMapper accountTradeMapper = sq.getMapper(ProjectMapper.class);
            Project data = accountTradeMapper.selectByPrimaryKey(projectId);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public int updateByExampleSelective(Project record, ProjectExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Project record, ProjectExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Project record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Project record) {
        return 0;
    }
}
