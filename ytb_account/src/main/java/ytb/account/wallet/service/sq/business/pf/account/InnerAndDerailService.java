package ytb.account.wallet.service.sq.business.pf.account;

import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.model.*;
import ytb.account.wallet.model.project.TradeInfo;
import ytb.account.wallet.model.project.TransferInfo;
import ytb.account.wallet.pojo.ProjectBalanceAgent;
import ytb.account.wallet.pojo.ProjectBalanceOutAc;
import ytb.account.wallet.pojo.ProjectBalanceToAc;
import ytb.account.wallet.service.sq.business.user.pojo.projectRefunds;

import java.math.BigDecimal;
import java.util.List;

/**
 * 内部资金之间钱
 * 项目流程方法
 * Copyright@ CchuaprojectByPAFirstPay
 * Author:Cchua
 * Date:2019/2/19
 */
public interface InnerAndDerailService {

    /**
     * 自动加减流水
     * balance 要扣的钱
     * accountPfInner 原始信息
     * acType  AcType 账户类型
     * return 原始流水accountPfDetail
     */
    AccountPfDetail pfDetailAutoCalculation(BigDecimal balance, AccountPfInner accountPfInner, AccountPfDetail accountPfDetail, Short acType, String sta);


    Boolean pfDetailAutoCalculation(AccountPfTrade accountTrade, SqlSession sq);
}
