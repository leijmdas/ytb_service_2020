package ytb.account.wallet.rest.impl;

import ytb.manager.charges.pojo.Paging;
import ytb.common.utils.pageutil.PageUtils;

import ytb.account.wallet.model.*;
import ytb.account.wallet.pojo.AccountTradeProjectPojo;
import ytb.account.wallet.pojo.UserInfoByTrade;
import ytb.account.wallet.service.sq.basics.AccountUserInnerService;
import ytb.account.wallet.service.sq.basics.impl.AccountTradeServiceImpl;
import ytb.account.wallet.service.sq.basics.impl.AccountUserInnerServiceImpl;
import ytb.account.wallet.service.sq.external.project.ProjectService;
import ytb.account.wallet.service.sq.external.project.ProjectTradeService;
import ytb.account.wallet.service.sq.external.project.impl.ProjectServiceImpl;
import ytb.account.wallet.service.sq.external.project.impl.ProjectTradeServiceImpl;
import ytb.account.wallet.service.sq.external.user.CompanyInfoService;
import ytb.account.wallet.service.sq.external.user.UserInfoService;
import ytb.account.wallet.service.sq.external.user.impl.CompanyInfoServiceImpl;
import ytb.account.wallet.service.sq.external.user.impl.UserInfoServiceImpl;
import java.util.ArrayList;
import java.util.List;

public class SysAccountTradeServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public PageUtils select(AccountTrade data, Paging paging) {


        AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();
        AccountTradeExample example = new AccountTradeExample();
        AccountTradeExample.Criteria criteria = example.createCriteria();

        if (data.getTradeId() != null) {
            criteria.andTradeIdEqualTo(data.getTradeId());
        }

        if (data.getTermId() != null) {
            criteria.andTermIdEqualTo(data.getTermId());
        }

        if (data.getTradeNo() != null) {
            criteria.andTradeNoEqualTo(data.getTradeNo());
        }
        if (data.getAcId() != null) {
            criteria.andAcIdEqualTo(data.getAcId());
        }

        if (data.getToAcId() != null) {
            criteria.andToAcIdEqualTo(data.getToAcId());
        }

        if (data.getTradeNoPre() != null) {
            criteria.andTradeNoPreEqualTo(data.getTradeNoPre());
        }

        if (data.getTradeType() != null) {
            criteria.andTradeTypeEqualTo(data.getTradeType());
        }

        if (data.getStatus() != null) {
            criteria.andStatusEqualTo(data.getStatus());
        }

        if (data.getCalFlag() != null) {
            criteria.andCalFlagEqualTo(data.getCalFlag());
        }
        if (paging != null) {

            if (
                    paging.getToOrder().equals("desc") || paging.getToOrder().equals("asc")) {
                example.setOrderByClause(paging.getToOrder() + "  " + paging.getOrderBy() + " ");
            }

        }

        List<AccountTrade> list = accountTradeService.selectByExample(example);

        PageUtils pageUtil = new PageUtils();
        if (paging != null) {
            pageUtil = new PageUtils(list, list.size(), paging.getPageSize(), paging.getCurrPage());
            Integer firstIndex = (paging.getCurrPage() - 1) * paging.getPageSize();
            //到第几条数据结束
            Integer lastIndex = paging.getPageSize();

            example.setOffset(firstIndex);
            example.setLimit(lastIndex);

            list = accountTradeService.selectByExample(example);
        }

        pageUtil.setList(list);

        return pageUtil;
       /* msgBody = "{\"list\":" + JSONArray.toJSONString(accountTrades) + "}";


        return handler.buildMsg(retcode, retmsg, msgBody);*/
    }

    public PageUtils selectAndProject(AccountTrade data, Paging paging) {


        AccountTradeServiceImpl accountTradeService = new AccountTradeServiceImpl();
        AccountTradeExample example = new AccountTradeExample();
        AccountTradeExample.Criteria criteria = example.createCriteria();

        if (data.getTradeId() != null) {
            criteria.andTradeIdEqualTo(data.getTradeId());
        }

        if (data.getTermId() != null) {
            criteria.andTermIdEqualTo(data.getTermId());
        }

        if (data.getTradeNo() != null) {
            criteria.andTradeNoEqualTo(data.getTradeNo());
        }
        if (data.getAcId() != null) {
            criteria.andAcIdEqualTo(data.getAcId());
        }

        if (data.getToAcId() != null) {
            criteria.andToAcIdEqualTo(data.getToAcId());
        }

        if (data.getTradeNoPre() != null) {
            criteria.andTradeNoPreEqualTo(data.getTradeNoPre());
        }

        if (data.getTradeType() != null) {
            criteria.andTradeTypeEqualTo(data.getTradeType());
        }

        if (data.getStatus() != null) {
            criteria.andStatusEqualTo(data.getStatus());
        }

        if (data.getCalFlag() != null) {
            criteria.andCalFlagEqualTo(data.getCalFlag());
        }
        if (paging != null) {

            if (
                    paging.getToOrder().equals("desc") || paging.getToOrder().equals("asc")) {
                example.setOrderByClause(paging.getToOrder() + "  " + paging.getOrderBy() + " ");
            }

        }

        List<AccountTrade> accountTrades = accountTradeService.selectByExample(example);

        PageUtils pageUtil = new PageUtils();
        if (paging != null) {
            pageUtil = new PageUtils(accountTrades, accountTrades.size(), paging.getPageSize(), paging.getCurrPage());
            Integer firstIndex = (paging.getCurrPage() - 1) * paging.getPageSize();
            //到第几条数据结束
            Integer lastIndex = paging.getPageSize();

            example.setOffset(firstIndex);
            example.setLimit(lastIndex);

            accountTrades = accountTradeService.selectByExample(example);
        }

        ProjectTradeService projectTradeService = new ProjectTradeServiceImpl();
        ProjectService projectService = new ProjectServiceImpl();

        List<AccountTradeProject> accountTradeProjects = new ArrayList<>();

        for (int i = 0; i < accountTrades.size(); i++) {
            AccountTradeProjectPojo tradeProjectPojo= new AccountTradeProjectPojo();
           // tradeProjectPojo.setAccountTrade(accountTrades.get(i));

            Project project = getProject(projectService, accountTrades.get(i).getProjectId());
            tradeProjectPojo.setProject(project);
            ProjectTrade projectTrade = getProjectTrade(projectTradeService, accountTrades.get(i).getProjectId(), accountTrades.get(i).getTradeId());

            tradeProjectPojo.setProjectTrade(projectTrade);

            //accountTradeProjects.add(tradeProjectPojo);

            if (accountTrades.get(i).getToAcId() != null && accountTrades.get(i).getToAcId() > 0) {
                UserInfoByTrade pb = getuserInfoByInnerId(accountTrades.get(i).getToAcId());
                tradeProjectPojo.setUserInfoByTradePB(pb);
            }
            if (accountTrades.get(i).getAcId() != null && accountTrades.get(i).getAcId() > 0) {
                UserInfoByTrade pa = getuserInfoByInnerId(accountTrades.get(i).getAcId());
                tradeProjectPojo.setUserInfoByTradePA(pa);
            }


        }

        pageUtil.setList(accountTradeProjects);

        return pageUtil;

    }


    public Project getProject(ProjectService projectService, Integer a) {

        Project asd = projectService.selectByPrimaryKey(a);

        return asd;
    }


    public UserInfoByTrade getuserInfoByInnerId(Integer innerId) {
        AccountUserInner userInner = getInner(innerId);
        if (userInner == null) {
            //   throw new YtbError("获取inner信息失败");
            return null;
        }

        UserInfoByTrade infoByTrade = getUserInfo(userInner.getUserId(), userInner.getCompanyId());
        if (infoByTrade == null) {
            return null;
            // throw new YtbError("获取inner信息失败");
        }
        return infoByTrade;
    }


    public AccountUserInner getInner(Integer innerId) {
        AccountUserInnerService accountUserInnerService = new AccountUserInnerServiceImpl();
        return accountUserInnerService.selectByPrimaryKey(innerId);

    }


    public UserInfoByTrade getUserInfo(Integer userId, Integer commId) {
        UserInfoByTrade userInfoByTrade = new UserInfoByTrade();
        if (userId > 0 & commId == 0) {
            UserInfoService userInfoService = new UserInfoServiceImpl();
            UserInfo info = userInfoService.selectByPrimaryKey(userId);
            userInfoByTrade.setId(info.getUserId());
            userInfoByTrade.setUserName(info.getRealName());
        } else if (userId > 0) {
            CompanyInfoService companyInfoService = new CompanyInfoServiceImpl();
            CompanyInfo info = companyInfoService.selectByPrimaryKey(commId);
            userInfoByTrade.setId(info.getCompanyId());
            userInfoByTrade.setUserName(info.getCompanyName());
        }


        return userInfoByTrade;
    }


    public ProjectTrade getProjectTrade(ProjectTradeService projectService, Integer projectId, Integer tradeId) {
        ProjectTradeExample projectTradeExample = new ProjectTradeExample();
        ProjectTradeExample.Criteria criteria = projectTradeExample.createCriteria();
        criteria.andProjectIdEqualTo(projectId);
        criteria.andTradeIdEqualTo(tradeId);

        List<ProjectTrade> asd = projectService.selectByExample(projectTradeExample);

        if (asd.size() > 0) {
            return asd.get(0);
        } else {
            return null;
        }

    }


}
