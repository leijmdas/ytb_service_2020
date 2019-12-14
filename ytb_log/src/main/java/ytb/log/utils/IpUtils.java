package ytb.log.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class IpUtils {

    private static Logger logger = LoggerFactory.getLogger(IpUtils.class);

    public static String getAddrByIp(String ip) {
        String addr = null;
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=";
        url = url + ip;
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            InputStream inputStream = connection.getInputStream();
            Map<String, Object> ret = JSON.parseObject(inputStream, Map.class);
            if (ret != null) {
                Map<String, String> m = (Map<String, String>) ret.get("data");
                addr = m.get("country");
                addr = addr + " " + m.get("region");
                addr = addr + " " + m.get("city");

            }
        } catch (Exception e) {
            logger.warn("根据ip获取地址信息失败:", ip);
        }

        if (StringUtils.isBlank(addr)) {
            addr = "未知地址";
        }
        return addr;
    }

    public static void main(String args[]) {

        String re = getAddrByIp("113.87.122.148");
        System.out.println(re);

    }
}



