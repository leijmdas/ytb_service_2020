package ytb.activiti.rest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ytb.activiti.rest.impl.activitiManager;
import ytb.activiti.rest.impl.activitiService;
import ytb.activiti.rest.impl.uploadActiviti;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/*rest  base class*/
@RestController
@RequestMapping("/rest")
@Scope("prototype")
public class RestActiviti extends RestHandler {

	//工作流接口
	@PostMapping(value = "activiti") //	@ResponseBody
	public String restActiviti(@RequestBody String data) {
		try {
			parseRequest(data);
			resp = process();
		} catch (YtbError e) {
			e.printStackTrace();
			resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
		} catch (Exception e) {
			e.printStackTrace();
			YtbError ye = new YtbError(YtbError.CODE_UNKNOWN_ERROR);
			resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
		}

		return resp.toJSONString();

	}

	@PostMapping(value = "/uploadActivitiNew")
	@ResponseBody
	public String uploadTemplate(HttpServletRequest request, HttpServletResponse response,
								 String msgBody, @RequestPart("file") MultipartFile file)
			throws IllegalStateException, IOException {
		try {
			parseRequest(msgBody);
			new uploadActiviti().uploadTemplateModify(this.req,file);

		} catch (YtbError e) {
			e.printStackTrace();
			resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
		} catch (Exception e) {
			e.printStackTrace();
			YtbError ye=new YtbError(YtbError.CODE_UNKNOWN_ERROR);
			resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
		}

		return JSONObject.toJSONString(resp, SerializerFeature.WriteMapNullValue);
	}

	//项目模板管理接口
	@PostMapping(value = "uploadActiviti")
	@ResponseBody
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {
			new uploadActiviti().uploadTemplate(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected MsgResponse process() throws UnsupportedEncodingException {

		if (req.cmdtype.equals("manager")) {
			return new activitiManager().process(req, this);
		} else if (req.cmdtype.equals("service")) {
			return new activitiService().process(req, this);
		}

		throw new YtbError(YtbError.CODE_INVALID_REST);
	}

	
}
