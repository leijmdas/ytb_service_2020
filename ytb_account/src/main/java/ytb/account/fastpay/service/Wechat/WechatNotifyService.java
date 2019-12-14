package ytb.account.fastpay.service.Wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ytb.account.fastpay.model.wechat.WechatPayNotifyResult;
import ytb.account.fastpay.util.XmlUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/3/26
 */
public class WechatNotifyService {




    public WechatPayNotifyResult notifyPay(String request) {
        XmlUtils xmlUtil = new XmlUtils();
        WechatPayNotifyResult wechatPayResult = new WechatPayNotifyResult();
        try {
            Map<String, String> Xmldata = null;

            try {
                Xmldata = XmlAnalysis(request);
                wechatPayResult = JSONObject.parseObject(JSON.toJSONString(Xmldata),
                        WechatPayNotifyResult.class);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
        }
        return wechatPayResult;

    }

    public Map<String, String> XmlAnalysis(String Xmldata) throws ParserConfigurationException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        StringReader stringReader = new StringReader(Xmldata);
        InputSource inputSource = new InputSource(stringReader);
        Document doc = null;
        try {
            doc = dBuilder.parse(inputSource);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> container = new LinkedHashMap<String, String>();
        NodeList list = doc.getElementsByTagName("*");
        for (int i = 1; i < list.getLength(); i++) {
            container.put(list.item(i).getNodeName(), list.item(i).getTextContent());
            /*container.forEach((key,value)->System.out.printf("%s = %s\n",key,value));*/
        }
        return container;
    }

}
