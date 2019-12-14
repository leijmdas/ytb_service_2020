package ytb.bangbang.service;

import ytb.bangbang.model.BBDocument;

public interface BBDocumentService {

    int addRecord(BBDocument document);

    BBDocument getDocumentInfo(int documentId);

}
