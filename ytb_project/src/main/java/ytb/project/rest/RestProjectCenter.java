package ytb.project.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ytb.project.context.UserProjectContext;
import ytb.project.rest.impl.*;

import ytb.common.context.rest.IRestProcess;
import ytb.common.RestMessage.MsgHandler;
import ytb.common.RestMessage.MsgRequest;
import ytb.common.RestMessage.MsgResponse;
import ytb.common.context.model.YtbError;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*rest base class*/
@RestController
@RequestMapping("/rest") //@Scope("prototype")
public class RestProjectCenter implements IRestProcess {


    //项目管理接口
    @PostMapping(value = "projectCenter",produces = {"Application/json;charset=UTF-8"})
    //@ResponseBody
    public String restProject(@RequestBody String data, HttpServletRequest request,
                    HttpServletResponse response) {
        return new MsgHandler(new UserProjectContext()).parseRequest(this, data, request, response);

    }

    public   MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if (handler.getUserContext().isUserManager() && !handler.getUserContext().isTest()) {
            throw new YtbError(YtbError.CODE_DEFINE_ERROR,"是管理中心用户，不能使用项目中心的接口!");
        }

        MsgRequest req=handler.req;
        if (req.cmdtype.equals("projectRelease")) {
            return new ProjectRelease().process(req, handler);
        }
        else if (req.cmdtype.equals("projectFlow")) {

            return new ProjectFlow().process(req, handler);

        }

        else if (req.cmdtype.equals("projectFlowChange")) {//项目变更

            return new ProjectFlowChange().process(req, handler);

        }
        else if (req.cmdtype.equals("projectFlowStop")) {//项目终止

            return new ProjectFlowStop().process(req, handler);

        }

        else if (req.cmdtype.equals("projectFile")) {

            //return new ProjectFile().process(req, handler);
        }else if(req.cmdtype.equals("companyOrUserProManager")){

            return new CompanyProjectManager().process(req,handler);
        }else if(req.cmdtype.equals("supplierCommodity")){
           //搜索供应商商品
            return new SupplierCommodity().process(req,handler);
        }else if(req.cmdtype.equals("purchaseGoods")){
            //搜索采购商商品
            return new PurchaseGoods().process(req,handler);
        }else if(req.cmdtype.equals("companyOrUnite")){
            //搜索公司/单位
            //return new CompanyOrUnite().process(req,handler);
        }

        throw new YtbError(YtbError.CODE_INVALID_REST);

    }


}
