package ytb.manager.templateexcel.service.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.context.ManagerSrvContext;
import ytb.manager.tagtable.model.DBTagTable;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.manager.template.model.Dict_document;
import ytb.common.context.model.YtbError;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName CheckTempletInfo
 * @Description deal with Template Check
 * @Author qsy
 * @Date 2019/4/17 18:21
 **/
public class CheckTemplateTableUnique extends CheckTemplateJobRepeatServiceImpl {


    public void check(int documentId) throws IOException {
        //json
        Dict_document dict_document = ManagerSrvContext.getInst().getTemplateDocumentService().getDocumentBy(documentId);
        TemplateDocumentInfo info = JSONObject.parseObject(new String(dict_document.getDocument(),
                "UTF-8"), TemplateDocumentInfo.class);
        check(info);
    }

    String makeUniqueKey(String[] checkFields, DBTagTable.DBTagValue row) {
        String key = "";
        for (String ukey : checkFields) {
            key += findCellText(ukey, row) + " ";
        }
        return key;
    }


    /**
     * @return void
     * @Author qsy
     * @Description CheckTemplate
     * @Date 18:29 2019/4/17
     * @Param [documentId]
     **/

    public void check(TemplateDocumentInfo info) throws IOException {

        //标签关联表数据 ,不包括参数引用表！
        for (String tableName : info.listTagTableName(false)) {

            DBTagTable tagTable = DBTagTable.parseTagTable(info, tableName);
            String[] checkUniqueKeys= findUniqueKeys(tagTable.getTableDict().getTableName());
            if (checkUniqueKeys.length == 0) {
                continue;
            }
            Map<String, Integer> checkMap = new HashMap<>();
            for (DBTagTable.DBTagValue rowValue : tagTable.getValue()) { //行
                String ukey = makeUniqueKey(checkUniqueKeys, rowValue);
                if(ukey.trim().isEmpty()){
                    continue;
                }
                if (checkMap.containsKey(ukey)) {
                    throw new YtbError(YtbError.CODE_USER_ERROR, ukey + " 有重复");
                } else {
                    checkMap.put(ukey, 1);
                }
            }
        }

    }


}
