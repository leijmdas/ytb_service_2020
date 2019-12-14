package ytb.manager.templateexcel.model.tag.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxz 2018/11/1 14:15
 */
public class TagEnum {

    public static final String TAG_TEXT = "tagText";

    public static final String TAG_TEXT_BOX = "tagTextBox";

    public static final String TAG_RADIO = "tagRadio";

    public static final String TAG_TABLE_PARAM_RADIO = "tagTableParamRadio";

    public static final String TAG_CHECK = "tagCheck";

    public static final String TAG_TABLE_PARAM_CHECK = "tagTableParamCheck";

    public static final String TAG_FILE = "tagFile";

    public static final String TAG_TABLE = "tagTable";

    public static final String TAG_TABLE_SUM = "tagTableSum";

    //暂时废除
    public static final String TAG_TABLE_IRREGULAR = "tagTableIrregular";

    public static final String TAG_TABLE_PARAM = "tagTableParam";

    public static final String TAG_TITLE = "tagTitle";

    public static final String TAG_TITLE_RED = "tagTitleRed";

    public static final String TAG_TABLE_REF = "tagTableRef";

    public static final String TAG_TABLE_PARAM_REF = "tagTableParamRef";

    public static final String TAG_TABLE_TEXT_REF = "tagTableTextRef";

    public static final String TAG_DOCUMENT = "tagDocument";

    public static final String TAG_DOCUMENT_REF = "tagDocumentRef";

    public static final String TAG_VERSION = "tagVersion";

    private static List<String> multiRowTagTypeList = new ArrayList<>();

    static {
        multiRowTagTypeList.add(TagEnum.TAG_TABLE);
        multiRowTagTypeList.add(TagEnum.TAG_TABLE_REF);
        multiRowTagTypeList.add(TagEnum.TAG_TABLE_IRREGULAR);
    }

    public static boolean isMultiRowTagType(final String tagType) {
        return multiRowTagTypeList.contains(tagType);
    }

}
