package ytb.manager.templateexcel.analysis;

import ytb.manager.templateexcel.model.tag.impl.TagEnum;
import ytb.common.context.model.YtbError;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LXZ
 */
public class TagAnalysisBuildFactory {

    private static Map<String, AbstractTagAnalysis> analysisMap = new ConcurrentHashMap<>();

    static {
        analysisMap.put(TagEnum.TAG_TEXT, new TagTextAnalysis());
        analysisMap.put(TagEnum.TAG_TEXT_BOX, new TagTextBoxAnalysis());
        analysisMap.put(TagEnum.TAG_RADIO, new TagRadioAnalysis());
        analysisMap.put(TagEnum.TAG_TABLE_PARAM_RADIO,new TagTableParamRadioAnalysis());
        analysisMap.put(TagEnum.TAG_TABLE_PARAM_CHECK,new TagTableParamCheckAnalysis());
        analysisMap.put(TagEnum.TAG_CHECK, new TagCheckAnalysis());
        analysisMap.put(TagEnum.TAG_FILE, new TagFileAnalysis());
        analysisMap.put(TagEnum.TAG_TABLE, new TagTableAnalysis());
        analysisMap.put(TagEnum.TAG_TABLE_SUM, new TagTableSumAnalysis());
        analysisMap.put(TagEnum.TAG_TABLE_PARAM, new TagTableParamAnalysis());
        analysisMap.put(TagEnum.TAG_TITLE, new TagTitleAnalysis());
        analysisMap.put(TagEnum.TAG_TITLE_RED, new TagTitleRedAnalysis());
        analysisMap.put(TagEnum.TAG_TABLE_REF, new TagTableRefAnalysis());
        analysisMap.put(TagEnum.TAG_TABLE_PARAM_REF, new TagTableParamRefAnalysis());
        analysisMap.put(TagEnum.TAG_TABLE_TEXT_REF, new TagTableTextRefAnalysis());
        analysisMap.put(TagEnum.TAG_DOCUMENT, new TagDocumentAnalysis());
        analysisMap.put(TagEnum.TAG_DOCUMENT_REF, new TagDocumentRefAnalysis());
        analysisMap.put(TagEnum.TAG_VERSION, new TagVersionAnalysis());
    }

    static AbstractTagAnalysis getAbstractTagAnalysis(String tagType) {
        tagType = tagType.split(":")[0];
        if (tagType.contains("@")) {
            tagType = tagType.split("@")[0];
        }
        AbstractTagAnalysis abstractTagAnalysis = analysisMap.get(tagType);
        if (abstractTagAnalysis == null) {
            throw new YtbError(YtbError.CODE_UNKNOWN_ERROR, "not found tag analysis");
        }
        return abstractTagAnalysis;
    }

}
