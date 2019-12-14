package ytb.bangbang.service.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.bangbang.dao.BBDocumentDao;
import ytb.bangbang.model.BBDocument;
import ytb.bangbang.service.BBDocumentService;
import ytb.bangbang.util.MyBatisUtil;

public class BBDocumentServiceImpl implements BBDocumentService {

    @Override
    public int addRecord(BBDocument document) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            BBDocumentDao dao=session.getMapper(BBDocumentDao.class);
            return dao.addRecord(document);
        }
    }

    @Override
    public BBDocument getDocumentInfo(int documentId) {
        try (SqlSession session = MyBatisUtil.getSession()) {
            BBDocumentDao dao=session.getMapper(BBDocumentDao.class);
            return dao.getDocumentInfo(documentId);
        }
    }
}
