package ytb.log.utils;




import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


import java.util.HashMap;
import java.util.Map;

/**
 * YTB_log的Aliyun工具类
 * Package: ytb.activiti.tools
 * Author: lzz
 */
public class Aliyun
{

    static final String singname = "阿里云短信测试专用";              //后续修改成实际使用的签名
    static final String product = "Dysmsapi";                   //产品名称:云通信短信API产品,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";       //产品域名,开发者无需替换
    static final String accessKeyId = "LTAIZjeVTeI8ONrd";           // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeySecret = "Cgnq6uq8Iy73vl49Uo4vMHjNalWcD2";

    /**
     * 阿里云短信发送接口
     * @param phone
     * @param tempcode
     * @param code
     * @return
     * @throws ClientException
     */
    public boolean sendSmsCode(String phone,String code,String tempcode)
    {
        boolean result =false;

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        try {

            //初始化acsClient,暂不支持region化，组装请求对象-具体描述见控制台-文档部分内容
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);

            request.setPhoneNumbers(phone);                 //必填:待发送手机号
            request.setSignName(singname);                  //必填:短信签名-可在短信控制台中找到
            request.setTemplateCode(tempcode);              //必填:短信模板-可在短信控制台中找到
            Map<String, Object> map = new HashMap<>();      //需要把map转换成json字符串,可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            map.put("name", phone);
            map.put("code", code);
            request.setTemplateParam(JsonUtils.getJson(map));

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                return result=true;
            }
            System.out.println(sendSmsResponse.getCode()+"\t \t"+result);
        }
        catch(ClientException e)
        {

        }
        catch(Exception e)
        {

        }
        return result;

    }

    public boolean sendSms(String phone,String template_param,String tempcode){

        boolean result =false;

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        try {

            //初始化acsClient,暂不支持region化，组装请求对象-具体描述见控制台-文档部分内容
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);

            request.setPhoneNumbers(phone);                 //必填:待发送手机号
            request.setSignName(singname);                  //必填:短信签名-可在短信控制台中找到
            request.setTemplateCode(tempcode);              //必填:短信模板-可在短信控制台中找到
            JSONObject jsonObject = JSONObject.parseObject(template_param);
            Map<String, Object> map = new HashMap<>();      //需要把map转换成json字符串,可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            map.put("name", phone);
            if(tempcode.equals("SMS_143860203")){
                String mtname = (String)jsonObject.get("mtname");
                map.put("mtname", mtname);

            }else if(tempcode.equals("SMS_148590993")){

                String projectName = (String)jsonObject.get("params1");
                map.put("projectName", projectName);
            }

            request.setTemplateParam(JsonUtils.getJson(map));

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                //请求成功
                return result=true;
            }
            System.out.println(sendSmsResponse.getCode()+"\t"+"fjjfkidfjfpjff"+"\t"+result);
        }
        catch(ClientException e)
        {

        }
        catch(Exception e)
        {

        }

        return result;
    }
}
