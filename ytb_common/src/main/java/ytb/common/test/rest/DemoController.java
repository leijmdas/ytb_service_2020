package ytb.common.test.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.common.basic.config.dao.ConfigMapper;
import ytb.common.basic.config.model.Dict_ConfigModel;

import java.util.List;

@RestController
@RequestMapping("/service")
public final class DemoController {

	//@Autowired
	//ConfigMapper configMapper;
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

	@PostMapping(value = "selectList", produces = {"application/json;charset=UTF-8"})
	public List<Dict_ConfigModel> selectList() {

		return null;// configMapper.selectList();
	}


//	curl -X POST -H "Content-Type: application/json" -d "{\"sender\":\
//			"891a9c1f3d1a4575871eaa2f2e44b85e\",\"recipient\":\"mymymyacount\",\"amount\":5}
//			" "http://localhost/service/getConfig"

}
