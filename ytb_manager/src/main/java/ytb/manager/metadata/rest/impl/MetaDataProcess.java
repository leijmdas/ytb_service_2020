package ytb.manager.metadata.rest.impl;

import ytb.manager.metadata.service.server.DictDataTypeServer;
import ytb.manager.metadata.service.server.MetadataDictServer;
import ytb.manager.metadata.service.server.MetadataFieldServer;
import ytb.manager.metadata.service.server.TableServer;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

/**
 * Package: ytb.manager.metadata.rest.impl
 * Author: XZW
 * Date: Created in 2018/8/23 18:11
 */
public class MetaDataProcess {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, RestHandler handler) {

        if (req.cmd.equals("getCachedTableList")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.getCachedTableList(req, handler);
        } else
        if (req.cmd.equals("updateFieldOrder")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.updateFieldOrder(req, handler);
        } else if (req.cmd.equals("getDictList")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.getDictList(req, handler);
        }else if (req.cmd.equals("checkDict")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.checkDict(req, handler);
        }
        //获取元数据子表中的field default value lsit
        else if (req.cmd.equals("getDictFieldsDefaultValueList")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.getDictFieldsDefaultValueList(req, handler);
        }//获取主表中的所有记录
        else if (req.cmd.equals("getDictListAndField")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.getDictListAndField(req, handler);
        }   else if (req.cmd.equals("getDictTableAndField")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.getDictTableAndField(req, handler);

        }

        //通过元数据类型查询出所有的记录
        else if (req.cmd.equals("getDictListByType")) {

            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.getDictListByType(req, handler);

        }
        //
        else if (req.cmd.equals("getDictListAndFieldByType")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.getDictListAndFieldByType(req, handler);

        }
        //getDictAndFieldByType
        else if (req.cmd.equals("getDictAndFieldByType")) {

            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.getDictAndFieldByType(req, handler);

        } else if (req.cmd.equals("getDictMetadataName")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.getDictMetadataName(req, handler);


        } else if (req.cmd.equals("getDictAndFieldByMetadataName")) {

            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.getDictAndFieldByMetadataName(req, handler);

        }

        //通过主表主键查询
        else if (req.cmd.equals("getDictById")) {

            MetadataDictServer metadataDictServer = new MetadataDictServer();

            return metadataDictServer.getDictById(req, handler);
        }


        //通过主键删除字段
        else if (req.cmd.equals("deleteDictById")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.deleteDictById(req, handler);
        }


        //向dict_datatype表中增加一条数据
        else if (req.cmd.equals("addDictDataType")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return  metadataDictServer.addDictDataType(req, handler);
        }

        //根据主键修改dict_datatype表中的数据
        else if (req.cmd.equals("updateDictDataTypeById")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return   metadataDictServer.updateDictDataTypeById(req, handler);
        }

        //根据主键删除dict_datatype表的数据
        else if (req.cmd.equals("deleteDictDataTypeById")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.deleteDictDataTypeById(req, handler);
        }


        //获取subsys_dict表中的所有数据
        else if (req.cmd.equals("getSubSysDictList")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return  metadataDictServer.getSubSysDictList(req, handler);
        }


        /*dict  */
        else if (req.cmd.equals("dictByUpdateByKey")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return  metadataDictServer.dictByUpdateByKey(req, handler);
        }

        /*dict  */
        else if (req.cmd.equals("dictByInsertSelective")) {

            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.dictByInsertSelective(req, handler);

        } else if (req.cmd.equals("copyMaster")) {

            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.copyMaster(req.getMsgBody().getInteger("metadataId"), handler);

        }else if (req.cmd.equals("dpMaster")) {

            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return metadataDictServer.dpMaster(req.getMsgBody().getInteger("metadataId"), handler);

        }
        /*dict  */
        else if (req.cmd.equals("dictDeleteByKey")) {

            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return  metadataDictServer.dictDeleteByKey(req, handler);


        }
        //dictBySelectByKey
        else if (req.cmd.equals("dictBySelectByKey")) {
            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return  metadataDictServer.dictBySelectByKey(req, handler);

        }
        //dictByUpdate
        else if (req.cmd.equals("dictByUpdate")) {

            MetadataDictServer metadataDictServer = new MetadataDictServer();
            return  metadataDictServer.dictByUpdate(req, handler);


        }
        //通过主表主键查询对应的字段信息
        else if (req.cmd.equals("getFieldListByDictId")) {

            MetadataFieldServer metadataFieldServer = new MetadataFieldServer();

            return metadataFieldServer.getFieldListByDictId(req, handler);
        }      //通过主键删除字段
        else if (req.cmd.equals("deleteFieldById")) {
            MetadataFieldServer metadataFieldServer = new MetadataFieldServer();
            return metadataFieldServer.deleteFieldById(req, handler);
        }
        //fieldByUpdateByKey
        else if (req.cmd.equals("fieldByUpdateByKey")) {

            MetadataFieldServer metadataFieldServer = new MetadataFieldServer();
            return metadataFieldServer.fieldByUpdateByKey(req, handler);

        } else if (req.cmd.equals("fieldByInsertSelective")) {

            MetadataFieldServer metadataFieldServer = new MetadataFieldServer();
            return metadataFieldServer.fieldByInsertSelective(req, handler);


        } else if (req.cmd.equals("fieldByDeleteByKey")) {

            MetadataFieldServer metadataFieldServer = new MetadataFieldServer();
            return    metadataFieldServer.fieldByDeleteByKey(req, handler);


        } else if (req.cmd.equals("fieldBySelectByKey")) {

            MetadataFieldServer metadataFieldServer = new MetadataFieldServer();
            return  metadataFieldServer.fieldBySelectByKey(req, handler);


        } else if (req.cmd.equals("fieldByDeleteByMetadataId")) {

            MetadataFieldServer metadataFieldServer = new MetadataFieldServer();
            return metadataFieldServer.fieldByDeleteByMetadataId(req, handler);

        }

        //通过主键id创建数据表
        else if (req.cmd.equals("makeTableByDictId")) {

            TableServer tableServer = new TableServer();
            return tableServer.makeTableByDictId(req, handler);

        } else if (req.cmd.equals("selectByTableByLimitOrder")) {

            TableServer tableServer = new TableServer();
            return tableServer.selectByTableByLimitOrder(req, handler);

        } else if (req.cmd.equals("selectByTableByLimit")) {

            TableServer tableServer = new TableServer();
            return tableServer.selectByTableByLimit(req, handler);

        } else if (req.cmd.equals("selectByTable")) {

            TableServer tableServer = new TableServer();
            return tableServer.selectByTable(req, handler);
        }
        //获取dict_datatype表中的所有数据
        else if (req.cmd.equals("getDictDataTypeList")) {

            DictDataTypeServer dictDataTypeServer = new DictDataTypeServer();
            return dictDataTypeServer.getDictDataTypeList(req, handler);

        }
        //获取dict_datatype表中的所有数据
        else if (req.cmd.equals("getDictDataTypeById")) {

            DictDataTypeServer dictDataTypeServer = new DictDataTypeServer();
            return dictDataTypeServer.getDictDataTypeById(req, handler);

        }
        //获取dict_datatype表中的所有数据
        else if (req.cmd.equals("addDictDataType")) {

            DictDataTypeServer dictDataTypeServer = new DictDataTypeServer();
            return dictDataTypeServer.addDictDataType(req, handler);

        }

        //获取dict_datatype表中的所有数据
        else if (req.cmd.equals("updateDictDataTypeById")) {

            DictDataTypeServer dictDataTypeServer = new DictDataTypeServer();
            return dictDataTypeServer.updateDictDataTypeById(req, handler);

        }  else if (req.cmd.equals("deleteDictDataTypeById")) {
            DictDataTypeServer dictDataTypeServer = new DictDataTypeServer();
            return dictDataTypeServer.deleteDictDataTypeById(req, handler);
        }
        else if (req.cmd.equals("selectDatatype")) {
            DictDataTypeServer dictDataTypeServer = new DictDataTypeServer();
            return dictDataTypeServer.selectDatatype(req, handler);
        }


        throw new YtbError(YtbError.CODE_INVALID_REST);


    }


}
