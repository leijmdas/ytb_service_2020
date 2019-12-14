package ytb.manager.templateexcel.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ytb.manager.tagtable.service.impl.DocumentParser.TemplateDocumentInfo;
import ytb.manager.template.model.Dict_document;
import ytb.manager.templateexcel.model.tag.TagTable;
import ytb.manager.templateexcel.model.xls.TemplateDocumentModel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 文档模板内容操作service
 * Author LXZ
 * Date 2018/9/4 13:30
 */
public interface TemplateDocumentService {


    Dict_document getDocumentBy(int documentId);

    void addDocument(Dict_document document);

    int modifyDocument(Dict_document document);

    void removeDocumentBy(int documentId);

    boolean parseExcel(int templateId) throws IOException;

    void modifyDocHeader(int documentId, String k, Object v);


    JSONObject getDict_document(int documentId) throws IOException;

    /**
     * 修改json中参数的值
     *
     * @param tdInfo 模板文档数据对象
     * @param param  参数数据
     * @return 模板文档数据对象
     */
    TemplateDocumentInfo innerModifyJsonParam(TemplateDocumentInfo tdInfo, Map<String, Object> param);

    /**
     * 构建tagTable 数据来源与某个接口,该接口返回完整表格数据,不是单列数据
     *
     * @param documentId
     */
    //void buildTagTableSrcFromRest(int projectId, int documentId);

    /**
     * 文档内部 表格引用处理
     * <p>
     * tagTableRef表格上配置了表名信息 通过表名查元数据字典获取数据 再构建当前表格数据
     *
     * @param documentId 文档资源ID
     */
    void tagTableRefHandler(int projectId, int documentId) throws UnsupportedEncodingException;

    /**
     * 修改工作组计划书文档 工作组任务表
     * @param projectId 项目标识
     * @param documentId 文档标识
     * @param list 岗位列表 来自需求说明书导出的岗位列表
     */
    void modifyWorkplanWorkJob (int projectId, int documentId, List<Map<String, Object>> list);

    void initWorkplanWorkJobByReq(byte[] document, int projectId, int documentId, List<Map<String, Object>> list);

    /**
     * 获取 文档中的目录表数据
     *
     * @param projectId
     * @param documentId
     */
    JSONArray getDocumentDirList(int projectId, int documentId);

    JSONArray getDocumentDirList(Dict_document documentBy, int projectId, int documentId);
}
