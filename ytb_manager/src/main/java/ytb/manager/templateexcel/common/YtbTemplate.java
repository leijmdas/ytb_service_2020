package ytb.manager.templateexcel.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @ClassName YtbTemplate
 * @Description TODO
 * @Author 123
 * @Date 2019/4/13 14:05
 **/
public class YtbTemplate {
    //单选框选中值
    public static final int TAG_RADIO_CHECKED_TEXT_VALUE = 1;
    //单选框未选中值
    public static final int TAG_RADIO_UNCHECKED_TEXT_VALUE = 0;
    //设置value数据
    public static final List<String> numberTypeList = new ArrayList<String>() {
        {
            add("BIT");
            add("TINYINT");
            add("SMALLINT");
            add("INTEGER");
            add("BIGINT");
            add("MONEY");
            add("PCT");
            add("DECIMAL");
        }
    };


    public static final Pattern ptNumber = Pattern.compile("^[0-9]+(\\.)?[0-9]+$");

    public static final String CHECK_JOB_MAX_NUMBER_MSG = "岗位个数超过";

    public static final String WORK_JOB_ID_DISC = "工作岗位ID：";

    public static final int UNCHECK_WORK_JOB_ID = 0;

    public static final int UNCHECK_WORK_JOB_NUMBER = 0;
}
