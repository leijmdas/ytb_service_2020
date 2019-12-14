package ytb.bangbang.dao;

import org.apache.ibatis.annotations.Param;
import ytb.bangbang.model.BBDocument;

public interface BBDocumentDao {

    int addRecord(BBDocument document);

    BBDocument getDocumentInfo(@Param("documentId") int documentId);
}
