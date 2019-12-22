package ytb.common.test.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.basic.config.model.Dict_ConfigModel;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hongld")
public final class DemoController {

	@RequestMapping(value = "/select", produces = {"application/json;charset=UTF-8"})
	public MsgResponse select() {

		return  new MsgResponse();//configMapper.selectList();
	}


	@RequestMapping(value = "/selectList", produces = {"application/json;charset=UTF-8"})
	public MsgResponse selectList() {

		return  new MsgResponse();//configMapper.selectList();
	}


	//	 @Autowired
//	 ConfigMapper configMapper;
//
	 @RequestMapping(value = "test", produces = {"application/json;charset=UTF-8"})
	public String test(@RequestBody String r) {
		System.out.println(r);
		return  r;
	}

	@RequestMapping(value = "getConfig", produces = {"application/json;charset=UTF-8"})
	public List<Dict_ConfigModel> getConfig(@RequestBody String r) {
		System.out.println(r);
		return null;//configMapper.getConfig();
	}

//	curl -X POST -H "Content-Type: application/json" -d "{\"sender\":\
//			"891a9c1f3d1a4575871eaa2f2e44b85e\",\"recipient\":\"mymymyacount\",\"amount\":5}
//			" "http://localhost/service/getConfig"

}
