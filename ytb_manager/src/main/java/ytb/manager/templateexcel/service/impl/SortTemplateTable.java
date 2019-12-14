package ytb.manager.templateexcel.service.impl;

import com.alibaba.fastjson.JSONObject;
import ytb.manager.context.ManagerSrvContext;
import ytb.manager.tagtable.model.DBTagTable;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.manager.template.model.Dict_document;
import java.io.IOException;
import java.util.*;


/**
 * @ClassName SortFieldInfo
 * @Description TODO
 * @Author qsy
 * @Date 2019/4/19 10:09
 **/
public class SortTemplateTable extends TemplateDocumentServiceImpl{

    public Map<String, DBTagTable> sort(int documentId,String ascDesc) throws IOException {
        //json
        Dict_document dict_document = ManagerSrvContext.getInst().getTemplateDocumentService().getDocumentBy(documentId);
        TemplateDocumentInfo info = JSONObject.parseObject(new String(dict_document.getDocument(),
                "UTF-8"), TemplateDocumentInfo.class);
        return sort(info,ascDesc);
    }

    /**
     * @Author qsy
     * @Description
     * @Date 10:12 2019/4/19
     * @Param [documentId]
     * @return void
     **/
    public Map<String, DBTagTable> sort(TemplateDocumentInfo info, String ascDesc) throws IOException {
        Map<String, DBTagTable> tagTableMap = new LinkedHashMap<>();

        //标签关联表数据
        for (String tableName : info.listTagTableName(false)) {
            DBTagTable tagTable = DBTagTable.parseTagTable(info, tableName);

            String[] sortFields = findSortKeys(tagTable.getTableDict().getTableName());
            if (sortFields.length == 0) {
                continue;
            }
            sortRows(sortFields, tagTable.getValue(), ascDesc);
            tagTableMap.put(tableName,tagTable);
            //log(tagTable);
            //只是显示，保存未做，TemplateDocumentInfo save DBTagTable:外部再保存！

        }
        return tagTableMap;
    }

    void log(DBTagTable tagTable) {
        for (DBTagTable.DBTagValue rowValue : tagTable.getValue()) {
            for (DBTagTable.TagField v : rowValue.getItems()) {
                if (v.getRefField() != null || v.getRefField() != null)
                     System.out.println(v);
            }
            System.out.println("\r\n");
        }

    }

    public void sortRows(String[] sortFields, List<DBTagTable.DBTagValue> rows, String ascDesc) {
        Collections.sort(rows, new Comparator<DBTagTable.DBTagValue>() {
            @Override
            public int compare(DBTagTable.DBTagValue a, DBTagTable.DBTagValue b) {

                for (String sortField : sortFields) {
                    int cret = a.findRefValeOrText(sortField).compareTo(b.findRefValeOrText(sortField));
                    if (cret != 0) {
                        return ascDesc.equals("asc") ? cret : -cret;
                    }
                }
                return 0;
            }
        });
    }

}
