package ytb.manager.charges.rest.server;


import com.alibaba.fastjson.JSONObject;
import ytb.manager.charges.model.DictArea;
import ytb.manager.charges.model.DictAreaExample;
import ytb.manager.charges.model.DictAreaSalary;
import ytb.manager.charges.model.DictAreaSalaryExample;
import ytb.manager.charges.pojo.Paging;
import ytb.manager.charges.service.impl.DictAreaSalaryServiceImpl;
import ytb.manager.charges.service.impl.DictAreaServiceImpl;
import ytb.common.utils.pageutil.PageUtils;
import ytb.common.context.rest.RestHandler;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DistAreaSalaryServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

   /* public MsgResponse select(MsgRequest req, RestHandler handler, HttpServletRequest request, HttpServletResponse response) {


        return handler.buildMsg(retcode, retmsg, msgBody);
    }*/

    public List<DictAreaSalary> getDistAreaSalary(DictAreaSalary data, RestHandler handler) {


        DictAreaSalaryExample example = new DictAreaSalaryExample();

        DictAreaSalaryExample.Criteria criteria = example.createCriteria();

        if (data.getSalaryId() != null) {
            criteria.andSalaryIdEqualTo(data.getSalaryId());
        }

        if (data.getAreaId() != null) {
            criteria.andAreaIdEqualTo(data.getAreaId());
        }

        if (data.getStatus() != null) {
            criteria.andStatusEqualTo(data.getStatus());
        }

        DictAreaSalaryServiceImpl dictAreaSalaryService = new DictAreaSalaryServiceImpl();

        List<DictAreaSalary> list = dictAreaSalaryService.selectByExample(example);
        return list;

    }


    public PageUtils getDistAreaSalaryPage(DictAreaSalary data, Paging paging) {

        /* if (req.msgBody.getInteger("pageSize") != null & req.msgBody.getInteger("currPage") != null) {*/
//        DictAreaSalary data = JSONObject.parseObject(req.msgBody.toString(), DictAreaSalary.class);
        DictAreaSalaryExample example = new DictAreaSalaryExample();

        DictAreaSalaryExample.Criteria criteria = example.createCriteria();

        if (data.getSalaryId() != null) {
            criteria.andSalaryIdEqualTo(data.getSalaryId());
        }

        if (data.getAreaId() != null) {
            criteria.andAreaIdEqualTo(data.getAreaId());
        }

        if (data.getStatus() != null) {
            criteria.andStatusEqualTo(data.getStatus());
        }


        if (paging != null) {

            if (
                    paging.getToOrder().equals("desc") || paging.getToOrder().equals("asc")) {
                example.setOrderByClause(paging.getToOrder() + "  " + paging.getOrderBy() + " ");
            }

        }

        DictAreaSalaryServiceImpl dictAreaSalaryService = new DictAreaSalaryServiceImpl();

//主表
        List<DictAreaSalary> list = dictAreaSalaryService.selectByExample(example);

       /* Integer pageSize = req.msgBody.getInteger("pageSize");
        Integer currPage = req.msgBody.getInteger("currPage");
*/

        Integer firstIndex = (paging.getCurrPage() - 1) * paging.getPageSize();
        //到第几条数据结束
//        Integer lastIndex = paging.getCurrPage() * paging.getPageSize();
        Integer lastIndex =  paging.getPageSize();
        example.setOffset(firstIndex);
        example.setLimit(lastIndex);
        PageUtils pageUtil = new PageUtils(list, list.size(), paging.getPageSize(), paging.getCurrPage());
        list = dictAreaSalaryService.selectByExample(example);

//子表
        DictAreaServiceImpl dictAreaService = new DictAreaServiceImpl();
        for (int i = 0; i < list.size(); i++) {
            DictArea dictArea = dictAreaService.selectByPrimaryKey(list.get(i).getAreaId());
            if (dictArea != null) {
                list.get(i).setDictArea(dictArea);
            } else {
                list.get(i).setDictArea(null);
            }
        }
        pageUtil.setList(list);

//        } else {
//            retcode = 1;
//            retmsg = "失败";
//        }


        return pageUtil;
    }


    public int deleteAreaSalary(Integer salaryId) {


        DictAreaSalaryServiceImpl dictAreaSalaryService = new DictAreaSalaryServiceImpl();

        int sta = dictAreaSalaryService.deleteByPrimaryKey(salaryId);


        return sta;
    }


    public int insertAreaSalary(DictAreaSalary data) {


        Date date = new Date();
        data.setAuditTime(date);
        data.setCreateTime(date);
        DictAreaSalaryServiceImpl dictAreaSalaryService = new DictAreaSalaryServiceImpl();

        int sta = dictAreaSalaryService.insertSelective(data);


        return sta;
    }


    public int updateDistAreaSalary(DictAreaSalary data) {


        DictAreaSalaryServiceImpl dictAreaSalaryService = new DictAreaSalaryServiceImpl();

        int sta = dictAreaSalaryService.updateByPrimaryKeySelective(data);


        return sta;
    }


    public PageUtils getDistAreaSalaryByName(DictArea data, Paging paging) {
        /*   if (req.msgBody.getInteger("pageSize") != null & req.msgBody.getInteger("currPage") != null) {*/


        //DictArea data = JSONObject.parseObject(req.msgBody.toString(), DictArea.class);


        DictAreaExample example = new DictAreaExample();

        DictAreaExample.Criteria criteria = example.createCriteria();


        if (data.getAreaId() != null) {
            criteria.andAreaIdEqualTo(data.getAreaId());
        }

        if (data.getName() != null) {
            criteria.andNameEqualTo(data.getName());
        }

/*
            if (req.msgBody.getString("toOrder") != null & req.msgBody.getString("orderBy") != null) {
                if (
                        req.msgBody.getString("orderBy").equals("desc") || req.msgBody.getString("orderBy").equals("asc")) {

                    example.setOrderByClause(req.msgBody.getString("toOrder") + "  " + req.msgBody.getString("orderBy") + " ");
                }
            }

*/
        DictAreaServiceImpl dictAreaService = new DictAreaServiceImpl();
//主表
        List<DictArea> list = dictAreaService.selectByExample(example);

          /*  Integer pageSize = req.msgBody.getInteger("pageSize");
            Integer currPage = req.msgBody.getInteger("currPage");


            Integer firstIndex = (currPage - 1) * pageSize;
            //到第几条数据结束
            Integer lastIndex = currPage * pageSize;

            example.setOffset(firstIndex);
            example.setLimit(lastIndex);
            PageUtils pageUtil = new PageUtils(list, list.size(), pageSize, currPage);
            list = dictAreaService.selectByExample(example);
            pageUtil.setList(list);*/
//子表

        List<Object> toAcList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            DictAreaSalaryServiceImpl dictAreaSalaryService = new DictAreaSalaryServiceImpl();
            DictAreaSalaryExample dictAreaExample = new DictAreaSalaryExample();
            dictAreaExample.createCriteria().andAreaIdEqualTo(list.get(i).getAreaId());
            List<DictAreaSalary> dictArea = dictAreaSalaryService.selectByExample(dictAreaExample);
            if (dictArea != null) {
                list.get(i).setDictAreaSalaryList(dictArea);

                for (int j = 0; j < dictArea.size(); j++) {
                    DictArea onData = dictAreaService.selectByPrimaryKey(dictArea.get(j).getAreaId());
                    if (onData != null) {
                        dictArea.get(j).setDictArea(onData);
                        toAcList.add(j, dictArea.get(i));
                    } else {
                        return null;
                    }
                }


            } else {
                list.get(i).setDictAreaSalaryList(null);
            }


        }
        PageUtils pageUtil = new PageUtils(toAcList, toAcList.size(), 0, 0);

        msgBody = "{\"list\":" + JSONObject.toJSON(pageUtil) + "}";


        return pageUtil;
    }
}
