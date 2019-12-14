package ytb.manager.template.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.manager.context.MyBatisUtil;
import ytb.manager.template.dao.ProjConfigMapper;
import ytb.manager.template.model.Dict_Prj_Config;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 *  author: leijming
 *  2018- 12 - 1
* */
public class ProjConfigServiceImpl implements ProjConfigMapper {

    //获取项目配置项逻辑
    public Collection<Dict_Prj_Config> getProjConfig(int projectType) {
        List<Dict_Prj_Config> g = selectProjConfig(0);
        List<Dict_Prj_Config> p = selectProjConfig(projectType);
        Map<Integer, Dict_Prj_Config> m = new LinkedHashMap<>();
        //全局
        for (Dict_Prj_Config c : g) {
            m.put(c.getValueType(), c);
        }
        //项目分类
        for (Dict_Prj_Config c : p) {
            m.put(c.getValueType(), c);
        }
        return m.values();
    }

    //页面显示配置项逻辑
    /*
    projectType=0全局
    projectType>0项目分类定制
    * */
    public List<Dict_Prj_Config> selectProjConfig(int projectType) {
        SqlSession session = MyBatisUtil.getSession(true);
        try {
            ProjConfigMapper pcm = session.getMapper(ProjConfigMapper.class);
            return pcm.selectProjConfig(projectType);
        } finally {
            session.close();
        }
    }

    @Override
    public void addProjConfig(Dict_Prj_Config c) {
        SqlSession session = MyBatisUtil.getSession(true);
        try {
            ProjConfigMapper pcm = session.getMapper(ProjConfigMapper.class);
            pcm.addProjConfig(c);
        } finally {
            session.close();
        }
    }

    @Override
    public void modifyProjConfig(Dict_Prj_Config c) {
        SqlSession session = MyBatisUtil.getSession(true);
        try {
            ProjConfigMapper pcm = session.getMapper(ProjConfigMapper.class);
            pcm.modifyProjConfig(c);
        } finally {
            session.close();
        }
    }

    @Override
    public void delProjConfig(int configId) {
        SqlSession session = MyBatisUtil.getSession(true);
        try {
            ProjConfigMapper pcm = session.getMapper(ProjConfigMapper.class);
            pcm.delProjConfig(configId);
        } finally {
            session.close();
        }
    }


}
