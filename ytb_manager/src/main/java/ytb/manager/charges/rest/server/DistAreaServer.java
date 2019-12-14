package ytb.manager.charges.rest.server;


import ytb.manager.charges.model.DictArea;
import ytb.manager.charges.model.DictAreaExample;
import ytb.manager.charges.model.DictAreaSalary;
import ytb.manager.charges.model.DictAreaSalaryExample;
import ytb.manager.charges.service.impl.DictAreaSalaryServiceImpl;
import ytb.manager.charges.service.impl.DictAreaServiceImpl;
import ytb.common.context.rest.RestHandler;
import ytb.common.RestMessage.MsgResponse;

import java.util.List;

public class DistAreaServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse select(DictArea dictArea, RestHandler handler) {


        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    public List<DictArea> getDistArea(DictArea data, RestHandler handler) {


        DictAreaExample example = new DictAreaExample();

        DictAreaExample.Criteria criteria = example.createCriteria();

        if (data.getAreaId() != null) {
            criteria.andAreaIdEqualTo(data.getAreaId());
        }
        if (data.getParentId() != null) {
            criteria.andParentIdEqualTo(data.getParentId());
        }
        if (data.getDepthType() != null) {
            criteria.andDepthTypeEqualTo(data.getDepthType());
        }
        if (data.getCode() != null) {
            criteria.andCodeEqualTo(data.getCode());
        }
        if (data.getName() != null) {
            criteria.andNameEqualTo(data.getName());
        }


        DictAreaServiceImpl dictAreaService = new DictAreaServiceImpl();
        List<DictArea> list = dictAreaService.selectByExample(example);
        return list;
//        msgBody = "{\"list\":" + JSONArray.toJSONString(list) + "}";
//
//
//        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public boolean deleteDistArea(DictArea data, RestHandler handler) {

        try {
            DictAreaServiceImpl dictAreaService = new DictAreaServiceImpl();

            DictAreaExample dictAreaExample = new DictAreaExample() ;
            DictAreaExample.Criteria criteria = dictAreaExample.createCriteria();
             criteria.andParentIdEqualTo(data.getAreaId());




            DictAreaSalaryServiceImpl dictAreaSalaryService = new DictAreaSalaryServiceImpl();
            DictAreaSalaryExample dictAreaSalaryExample = new DictAreaSalaryExample();
            dictAreaSalaryExample.createCriteria().andAreaIdEqualTo(data.getAreaId());

              List<DictAreaSalary> dictAreaSalaries=   dictAreaSalaryService.selectByExample(dictAreaSalaryExample);
             List<DictArea> dictAreas = dictAreaService.selectByExample(dictAreaExample);

            if (dictAreas.size()>0 || dictAreaSalaries.size()>0){
                return false;
            }else {

                int sta = dictAreaService.deleteByPrimaryKey(data.getAreaId());

                if (sta > 0) {
                    return true;
                } else {
                    return false;
                }
            }


        } catch (Exception e) {
            return false;
        }

    }

    public boolean updateDistArea(DictArea data, RestHandler handler) {

        try {
            DictAreaServiceImpl dictAreaService = new DictAreaServiceImpl();
            int sta = dictAreaService.updateByPrimaryKeySelective(data);

            if (sta > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertDistArea(DictArea data, RestHandler handler) {

        try {


            DictAreaServiceImpl dictAreaService = new DictAreaServiceImpl();
            int sta = dictAreaService.insertSelective(data);

            if (sta > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


}
