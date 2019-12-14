package ytb.account.wallet.service.sq.external.project.impl;


import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.ProjectTradeMapper;
import ytb.account.wallet.model.ProjectTrade;
import ytb.account.wallet.model.ProjectTradeExample;
import ytb.account.wallet.service.sq.external.project.ProjectTradeService;
import ytb.account.wallet.tool.MyBatisUtil;

import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class ProjectTradeServiceImpl implements ProjectTradeService {

    @Override
    public long countByExample(ProjectTradeExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(ProjectTradeExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer projectTradeId) {
        return 0;
    }

    @Override
    public int insert(ProjectTrade record) {
        return 0;
    }

    @Override
    public int insertSelective(ProjectTrade record) {
        return 0;
    }

    @Override
    public List<ProjectTrade> selectByExample(ProjectTradeExample example) {
        SqlSession sq = MyBatisUtil.getSession();

        try {

            ProjectTradeMapper accountTradeMapper = sq.getMapper(ProjectTradeMapper.class);
            List<ProjectTrade> data = accountTradeMapper.selectByExample(example);
            return data;
        } finally {
            sq.close();

        }
    }

    @Override
    public ProjectTrade selectByPrimaryKey(Integer projectTradeId) {
        return null;
    }

    @Override
    public int updateByExampleSelective(ProjectTrade record, ProjectTradeExample example) {
        return 0;
    }

    @Override
    public int updateByExample(ProjectTrade record, ProjectTradeExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(ProjectTrade record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ProjectTrade record) {
        return 0;
    }
}
