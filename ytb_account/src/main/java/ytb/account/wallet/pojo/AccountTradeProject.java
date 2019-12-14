package ytb.account.wallet.pojo;

import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.Project;
import ytb.account.wallet.model.ProjectTrade;

/**
 * Copyright@ vipcchua.github.io
 * Author:Cchua
 * Date:2019/3/22
 */
public class AccountTradeProject {

    private AccountTrade accountTrade;

    private Project project;

    private ProjectTrade projectTrade;

    private UserInfoByTrade userInfoByTradePA;

    private UserInfoByTrade userInfoByTradePB;

    public UserInfoByTrade getUserInfoByTradePA() {
        return userInfoByTradePA;
    }

    public void setUserInfoByTradePA(UserInfoByTrade userInfoByTradePA) {
        this.userInfoByTradePA = userInfoByTradePA;
    }

    public UserInfoByTrade getUserInfoByTradePB() {
        return userInfoByTradePB;
    }

    public void setUserInfoByTradePB(UserInfoByTrade userInfoByTradePB) {
        this.userInfoByTradePB = userInfoByTradePB;
    }

    public AccountTrade getAccountTrade() {
        return accountTrade;
    }

    public void setAccountTrade(AccountTrade accountTrade) {
        this.accountTrade = accountTrade;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectTrade getProjectTrade() {
        return projectTrade;
    }

    public void setProjectTrade(ProjectTrade projectTrade) {
        this.projectTrade = projectTrade;
    }
}
