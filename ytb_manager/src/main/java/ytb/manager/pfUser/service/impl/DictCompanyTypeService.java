package ytb.manager.pfUser.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.pfUser.dao.DictCompanyTypeMapper;
import ytb.manager.pfUser.model.Dict_CompanyType;

import java.util.ArrayList;
import java.util.List;

public class DictCompanyTypeService {


    public List<Dict_CompanyType> getDictCompanyTypeList() {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            DictCompanyTypeMapper m = s.getMapper(DictCompanyTypeMapper.class);
            return m.getDictCompanyTypeList();
        } finally {
            s.close();
        }
    }

    public List<Dict_CompanyType> getDictCompanyTypeListByID(int companyType) {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            DictCompanyTypeMapper m = s.getMapper(DictCompanyTypeMapper.class);
            Dict_CompanyType dct = new Dict_CompanyType();
            dct.setCompanyType(companyType);
            return m.getDictCompanyTypeListByID(dct);
        } finally {
            s.close();
        }
    }

    public void insertDictCompanyType (Dict_CompanyType dct) {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            DictCompanyTypeMapper m = s.getMapper(DictCompanyTypeMapper.class);
            m.insertDictCompanyType(dct);
        } finally {
            s.close();
        }
    }

    public void updateDictCompanyType(Dict_CompanyType dct) {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            DictCompanyTypeMapper m = s.getMapper(DictCompanyTypeMapper.class);
            m.updateDictCompanyType(dct);
        } finally {
            s.close();
        }
    }

    public void deleteDictCompanyType(int companyType) {
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            DictCompanyTypeMapper m = s.getMapper(DictCompanyTypeMapper.class);
            m.deleteDictCompanyType(companyType);
        } finally {
            s.close();
        }
    }


    public List<Dict_CompanyType> getDictCompanyTypeListByParentID(int parentId){
        SqlSession s = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            DictCompanyTypeMapper m = s.getMapper(DictCompanyTypeMapper.class);
            return m.getDictCompanyTypeListByParentID(parentId);
        } finally {
            s.close();
        }
    }


    public List<Dict_CompanyType> selectTree() {
        List<Dict_CompanyType> lst = getDictCompanyTypeList();
        List<Dict_CompanyType> retlst = findSubLst(lst, 0);
        for (Dict_CompanyType tag : retlst) {
            List<Dict_CompanyType> subLst = findSubLst(lst, tag.getCompanyType());
            tag.setChildren(subLst);
        }
        return retlst;
    }

    List<Dict_CompanyType> findSubLst(List<Dict_CompanyType> lst, int pid) {
        List<Dict_CompanyType> retlst = new ArrayList<>();
        for (Dict_CompanyType tag : lst) {
            if (tag.getParentId() == pid) {
                retlst.add(tag);
            }
        }
        return retlst;
    }


}
