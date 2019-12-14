package ytb.account.wallet.service.service.calculation.serviceFee.impl;

import ytb.manager.charges.model.ServiceFee;
import ytb.manager.charges.model.ServiceFeeExample;
import ytb.manager.charges.service.impl.ServiceFeeServiceImpl;

import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.ServiceFeeConst;
import ytb.account.wallet.service.service.calculation.serviceFee.ServiceBalanceService;

import ytb.account.wallet.tool.CurrencyUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/2/26
 */
public class ServiceBalanceServiceImpl implements ServiceBalanceService {

    /**
     * 服务费计算方法
     */
    @Override
    public BigDecimal serviceBalance(BigDecimal balance, Short tradeSubtype) {
        try {
            BigDecimal serviceBalance = null;

            ServiceFeeServiceImpl serviceFeeService = new ServiceFeeServiceImpl();
            ServiceFeeExample serviceFeeExample = new ServiceFeeExample();
            ServiceFeeExample.Criteria criteria = serviceFeeExample.createCriteria();

            if (tradeSubtype == AccountConst.TRADE_SUBTYPE_BANK) {
                String projectType = String.valueOf(ServiceFeeConst.project_type_bank);
                criteria.andProjectTypeEqualTo(projectType);

            } else if (tradeSubtype == AccountConst.TRADE_SUBTYPE_WE_CHAT) {
                String projectType = String.valueOf(ServiceFeeConst.project_type_alipay);
                criteria.andProjectTypeEqualTo(projectType);

            } else if (tradeSubtype == AccountConst.TRADE_SUBTYPE_ALIPAY) {
                String projectType = String.valueOf(ServiceFeeConst.project_type_wechat);
                criteria.andProjectTypeEqualTo(projectType);

            } else {
                serviceBalance = null;
            }

            List<ServiceFee> serviceFees = serviceFeeService.selectByExample(serviceFeeExample);
            if (serviceFees.size() > 0) {

                for (int i = 0; i < serviceFees.size(); i++) {

                    serviceFees.get(i).getLowerLimit();
////取出税率判断
                    if (
                            balance.compareTo(BigDecimal.valueOf(serviceFees.get(i).getLowerLimit())) == 1
                                    ||
                                    balance.compareTo(BigDecimal.valueOf(serviceFees.get(i).getLowerLimit())) == 0
                                            &
                                            balance.compareTo(BigDecimal.valueOf(serviceFees.get(i).getUpperLimit())) == -1
                                            &
                                            balance.compareTo(BigDecimal.valueOf(serviceFees.get(i).getUpperLimit())) == 1

                    ) {
                        serviceBalance = CurrencyUtils.multiply(balance, serviceFees.get(i).getFeeRate());
                        //  serviceBalance = (balance.multiply(serviceFees.get(i).getFeeRate()));
                    } else {
                        serviceBalance = BigDecimal.ZERO;
                    }

                }
            } else {
                serviceBalance = BigDecimal.ZERO;
            }
            return serviceBalance;
        } catch (Exception e) {
            throw e;
        }
    }
}
