package ytb.manager.metadata.model;

import java.util.List;

/**
 * Created by ZYB on 2018/9/14.
 */
public class MetaDataFieldDto {
    public Sys_MetaDataDictModel getMetaDataDictModel() {
        return metaDataDictModel;
    }

    public void setMetaDataDictModel(Sys_MetaDataDictModel metaDataDictModel) {
        this.metaDataDictModel = metaDataDictModel;
    }

    private Sys_MetaDataDictModel metaDataDictModel;
    private List<Sys_MetaDataFieldModel> fieldModelList;

    public List<Sys_MetaDataFieldModel> getFieldModelList() {
        return fieldModelList;
    }

    public void setFieldModelList(List<Sys_MetaDataFieldModel> fieldModelList) {
        this.fieldModelList = fieldModelList;
    }
}
