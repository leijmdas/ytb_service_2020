package ytb.account.wallet.service.service.autoSettlement.impl;

import org.apache.ibatis.session.SqlSession;
import ytb.account.wallet.dao.AccountTradeMapper;
import ytb.account.wallet.model.AccountTrade;
import ytb.account.wallet.model.AccountTradeExample;
import ytb.account.wallet.service.AccountConst.AccountConst;
import ytb.account.wallet.service.AccountConst.TradeConst;
import ytb.account.wallet.tool.MyBatisUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Copyright@ Cchua
 * Author:Cchua
 * Date:2019/3/8
 */
public class TradeAuto {


    public static void Cash() {
        SqlSession sq = MyBatisUtil.getSession(false);

        try {

            AccountTradeMapper tradeMapper = sq.getMapper(AccountTradeMapper.class);
            AccountTradeExample example = new AccountTradeExample();

            AccountTradeExample.Criteria criteria = example.createCriteria();
            criteria.andServiceTypeEqualTo(TradeConst.SERVICE_TYPE_cash);
            criteria.andStatusEqualTo(TradeConst.status_success);

            //    criteria.andTermIdEqualTo(intDate());

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(yesterday);
                criteria.andTermIdEqualTo(intDate(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            List<AccountTrade> data = tradeMapper.selectByExample(example);


            if (data.size() > 0) {

            }


        } finally {
            sq.close();

        }
    }


    public static void Project() {
        SqlSession sq = MyBatisUtil.getSession(false);

        try {

            Integer asd = 1;
            Integer ba = 2;

            AccountTradeMapper tradeMapper = sq.getMapper(AccountTradeMapper.class);

            AccountTradeExample example = new AccountTradeExample();

            AccountTradeExample.Criteria criteria = example.createCriteria();
            criteria.andServiceTypeEqualTo(TradeConst.SERVICE_TYPE_project);
            criteria.andStatusEqualTo(TradeConst.status_success);
            criteria.andTradeSubtypeEqualTo(AccountConst.TRADE_SUBTYPE_BALANCE_2_FROZEN);
            criteria.andTradeTypeEqualTo(TradeConst.TRADE_TYPE_PAYOUT);
            criteria.andTradeNoPreEqualTo("0");

            //    criteria.andTermIdEqualTo(intDate());

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(yesterday);
                criteria.andTermIdEqualTo(intDate(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            List<AccountTrade> data = tradeMapper.selectByExample(example);

            if (data.size() <= 0) {
                sq.close();
            }

            for (int i = 0; i < data.size(); i++) {

                data.get(i).getServiceFee();
                data.get(i).getFee();
                data.get(i).getTax();
                data.get(i).getBalance();

            }


        } finally {
            sq.close();

        }
    }


    private static Integer intDate(Date dt) {

        String s = DateFormat.getDateInstance(DateFormat.DEFAULT).format(dt).replaceAll("-", "");
        return Integer.parseInt(s);
    }


}
