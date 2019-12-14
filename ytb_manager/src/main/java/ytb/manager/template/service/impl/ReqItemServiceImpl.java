package ytb.manager.template.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import ytb.manager.context.MyBatisUtil;
import ytb.common.context.service.impl.YtbContext;
import ytb.manager.template.dao.ReqItemMapper;
import ytb.manager.template.model.Dict_Req_Item;
import ytb.manager.template.service.ReqItemService;

import java.util.List;

@Service
public class ReqItemServiceImpl implements ReqItemService {

    @Override
    public void removeListByTemplateId(int templateId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            ReqItemMapper reqItemMapper = session.getMapper(ReqItemMapper.class);
            reqItemMapper.deleteListByTemplateId(templateId);
        }
    }

    @Override
    public void add(Dict_Req_Item reqItem) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            ReqItemMapper reqItemMapper = session.getMapper(ReqItemMapper.class);
            reqItemMapper.insert(reqItem);
        }
    }

    @Override
    public List<Dict_Req_Item> queryListByTemplateId(int templateId) {
        SqlSession session = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(false);
        try {
            ReqItemMapper reqItemMapper = session.getMapper(ReqItemMapper.class);
            List<Dict_Req_Item> reqItems = reqItemMapper.selectListByTemplateId(templateId);
            return reqItems;
        } catch (Exception e) {
            session.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void modifyReqItem(Dict_Req_Item reqItem) {
        SqlSession session = YtbContext.getYtb_context().getSqlSessionBuilder().getSession_manager(true);
        try {
            ReqItemMapper mapper = session.getMapper(ReqItemMapper.class);
            mapper.updateReqItem(reqItem);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
