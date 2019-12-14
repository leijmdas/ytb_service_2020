package ytb.log.utils;




import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Administrator on 2015/6/25.
 * 随机数相关方法
 */
final public class RndUtils
{
    /**
     * 定义随机数
     */
    private static final  Random random = new Random();

    /**
     * 得到一个全球唯一识别码
     * @return
     */
    public static String getGuid()
    {
        return getGuid(true);
    }

    /**
     * 得到一个全球唯一识别码
     * @param isClear 是否清除字符串中的“-”连接符
     * @return
     */
    public static String getGuid(boolean isClear)
    {
        UUID uuid = UUID.randomUUID();
        if(!isClear) {
            return uuid.toString();
        }
        else
        {
            return uuid.toString().replace("-","");
        }
    }


    /**
     * 得到随机数
     * @return
     */
    public static int getRandNum()
    {
        return  Math.abs( random.nextInt());
    }

    /**
     * 得到特定范围内的随机数
     * @return
     */
    public static int getRandNum(int range)
    {
        if(range == 0) range = 10;

        return  Math.abs( random.nextInt() % range);
    }

    /**
     * 得到特定区间的随机数
     * @param min
     * @param max
     * @return
     */
    public static int getRandNum(int min,int max)
    {
        return  Math.abs( random.nextInt(max)%(max-min+1) + min);
    }

    /**
     * 得到特定随机数字符串
     * @return
     */
    public static  String getRandString()
    {
        return getRandString("");
    }

    /**
     * 得到特定随机数字符串
     * @return
     */
    public static  String getRandString(String prex)
    {
        return getRandString("", 1000000);
    }

    /**
     * 得到特定随机数字符串
     * @return
     */
    public static  String getRandString(String prex,int range)
    {
        return prex
                + Date2String(new java.util.Date(),"yyyyMMddHHmmssSSS" )
                + getRandNum(range);
    }

    public static String  Date2String(Date date, String partten)
    {
        SimpleDateFormat df = new SimpleDateFormat(partten);
        return 	df.format(date);
    }

    /**
     * 获取验证码
     * @return
     */
    public static  String getValidateCode()
    {
        return getValidateCode(6,true);
    }


    //static final String RAND_CHAR1 = "0,1,2,3,4,5,6,7,8,9";
    //static final String RAND_CHAR2 = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,0,1,2,3,4,5,6,7,8,9";

    static final String[] RAND_CHAR1 = "0,1,2,3,4,5,6,7,8,9".split("\\,");
    static final String[] RAND_CHAR2 = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split("\\,");

    /**
     * 获取验证码
     * @return
     */
    public static  String getValidateCode(int len ,boolean isNumber)
    {
        String value = "";
        int maxLen = 0 ;

        if(isNumber )
        {
            maxLen = RAND_CHAR1.length;
        }
        else{
            maxLen = RAND_CHAR2.length;
        }

        for (int  i = 0; i < len; i++)
        {
            value += RAND_CHAR1[ getRandNum (maxLen)];
        }
        return value;
    }


    /**
     * 测试方法
     * @param Args
     */
//    public static void main(String [] Args)
//    {
//        System.out.println(getGuid(true));
//        System.out.println(getGuid(false));
//    }
}
