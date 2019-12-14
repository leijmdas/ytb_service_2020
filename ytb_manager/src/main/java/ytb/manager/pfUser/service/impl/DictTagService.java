package ytb.manager.pfUser.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.pfUser.dao.DictTagMapper;
import ytb.manager.pfUser.model.Dict_Tag;

import java.util.ArrayList;
import java.util.List;

public class DictTagService {


    public List<Dict_Tag> getDictTagList(int tagType) {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            DictTagMapper dictTagMapper = s.getMapper(DictTagMapper.class);
            return dictTagMapper.getDictTagList(tagType);
        } finally {
            s.close();
        }
    }

    public List<Dict_Tag> getDictTagListByID(int tagType, int tagId) {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            DictTagMapper dictTagMapper = s.getMapper(DictTagMapper.class);
            Dict_Tag dtag = new Dict_Tag();
            dtag.setTagId(tagId);
            dtag.setTagType(tagType);
            return dictTagMapper.getDictTagListByID(dtag);
        } finally {
            s.close();
        }
    }

    public void insertDictTag(Dict_Tag dtag) {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            DictTagMapper dictTagMapper = s.getMapper(DictTagMapper.class);
            dictTagMapper.insertDictTag(dtag);
        } finally {
            s.close();
        }
    }

    public void updateDictTag(Dict_Tag dtag) {
        SqlSession s = YtbContext.getSqlSessionBuilder().getSession_manager(true);
        try {
            DictTagMapper dictTagMapper = s.getMapper(DictTagMapper.class);
            dictTagMapper.updateDictTag(dtag);
        } finally {
            s.close();
        }
    }

    public void deleteDictTag(int tagId) {
        SqlSession s = YtbContext.getSqlSessionBuilder().getSession_manager(true);
        try {
            DictTagMapper dictTagMapper = s.getMapper(DictTagMapper.class);
            dictTagMapper.deleteDictTag(tagId);
        } finally {
            s.close();
        }
    }

    public List<Dict_Tag> selectTree(int tagType) {
        List<Dict_Tag> lst = getDictTagList(tagType);
        List<Dict_Tag> retlst = findSubLst(lst, 0);

        for (Dict_Tag tag : retlst) {
            List<Dict_Tag> subLst = findSubLst(lst, tag.getTagId());
            tag.setChildren(subLst);
        }

        return retlst;
    }

    List<Dict_Tag> findSubLst(List<Dict_Tag> lst, int pid) {
        List<Dict_Tag> retlst = new ArrayList<>();
        for (Dict_Tag tag : lst) {
            if (tag.getParentId() == pid) {
                retlst.add(tag);
            }
        }
        return retlst;
    }


}
