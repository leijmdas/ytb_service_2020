package ytb.manager.charges.rest.server;


import ytb.manager.charges.model.ServiceFee;
import ytb.manager.charges.model.ServiceFeeExample;

import ytb.manager.charges.pojo.Paging;
import ytb.manager.charges.service.impl.ServiceFeeServiceImpl;
import ytb.common.utils.pageutil.PageUtils;

import java.util.List;

public class ServiceFeeServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;


    public List<ServiceFee> getServiceFee(ServiceFee data) {

//传入getProjectType  将筛选ProjectType类型的数据 同理


        ServiceFeeServiceImpl serviceFeeService = new ServiceFeeServiceImpl();
        ServiceFeeExample serviceFeeExample = new ServiceFeeExample();
        ServiceFeeExample.Criteria criteria = serviceFeeExample.createCriteria();

        if (data.getFeeId() != null) {
            criteria.andFeeIdEqualTo(data.getFeeId());
        }

        if (data.getProjectType() != null) {
            criteria.andProjectTypeEqualTo(data.getProjectType());
        }


        List<ServiceFee> list = serviceFeeService.selectByExample(serviceFeeExample);
        return list;


    }

    public Boolean insertServiceFee(List<ServiceFee> serviceFee) {


        ServiceFeeServiceImpl serviceFeeService = new ServiceFeeServiceImpl();

        int sta = serviceFeeService.insertSelectiveList(serviceFee);


        if (sta > 0) {

            return true;

        } else {
            return false;
        }


    }

    public Boolean deleteServiceFee(Integer feeId) {


        ServiceFeeServiceImpl serviceFeeService = new ServiceFeeServiceImpl();

        int sta = serviceFeeService.deleteByPrimaryKey(feeId);

        if (sta > 0) {

            return true;

        } else {
            return false;
        }


    }

    public Boolean updateServiceFee(ServiceFee data) {
/*
        ServiceFee data = JSONObject.parseObject(req.msgBody.toString(), ServiceFee.class);
*/
        ServiceFeeServiceImpl serviceFeeService = new ServiceFeeServiceImpl();

        int sta = serviceFeeService.updateByPrimaryKeySelective(data);


        if (sta > 0) {

            return true;

        } else {
            return false;
        }

    }

    public PageUtils getServiceFeePage(ServiceFee data, Paging paging) {
        PageUtils pageUtil = null;
        ServiceFeeServiceImpl serviceFeeService = new ServiceFeeServiceImpl();
        ServiceFeeExample serviceFeeExample = new ServiceFeeExample();
        ServiceFeeExample.Criteria criteria = serviceFeeExample.createCriteria();


        if (data.getFeeId() != null) {
            criteria.andFeeIdEqualTo(data.getFeeId());
        }

        if (data.getProjectType() != null) {
            criteria.andProjectTypeEqualTo(data.getProjectType());
        }

        if (paging != null) {
            serviceFeeExample.setOrderByClause(paging.getToOrder() + "  " + paging.getOrderBy() + " ");
        }


        if (paging.getEndTime() != null && paging.getEndTime() != null) {
            criteria.andCreateTimeBetween(paging.getStartTime(), paging.getEndTime());
        }


        List<ServiceFee> list = serviceFeeService.selectByExample(serviceFeeExample);

        if (paging != null) {

            Integer pageSize = paging.getPageSize();
            Integer currPage = paging.getCurrPage();


            Integer firstIndex = (currPage - 1) * pageSize;
            //到第几条数据结束
//            Integer lastIndex = currPage * pageSize;
            Integer lastIndex =  paging.getPageSize();
            serviceFeeExample.setOffset(firstIndex);
            serviceFeeExample.setLimit(lastIndex);
            pageUtil = new PageUtils(list, list.size(), pageSize, currPage);
            list = serviceFeeService.selectByExample(serviceFeeExample);
            pageUtil.setList(list);
        }


        return pageUtil;
    }
}

