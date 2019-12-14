package ytb.account.wallet.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountPfInnerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public AccountPfInnerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPfInnerIdIsNull() {
            addCriterion("pf_inner_id is null");
            return (Criteria) this;
        }

        public Criteria andPfInnerIdIsNotNull() {
            addCriterion("pf_inner_id is not null");
            return (Criteria) this;
        }

        public Criteria andPfInnerIdEqualTo(Integer value) {
            addCriterion("pf_inner_id =", value, "pfInnerId");
            return (Criteria) this;
        }

        public Criteria andPfInnerIdNotEqualTo(Integer value) {
            addCriterion("pf_inner_id <>", value, "pfInnerId");
            return (Criteria) this;
        }

        public Criteria andPfInnerIdGreaterThan(Integer value) {
            addCriterion("pf_inner_id >", value, "pfInnerId");
            return (Criteria) this;
        }

        public Criteria andPfInnerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pf_inner_id >=", value, "pfInnerId");
            return (Criteria) this;
        }

        public Criteria andPfInnerIdLessThan(Integer value) {
            addCriterion("pf_inner_id <", value, "pfInnerId");
            return (Criteria) this;
        }

        public Criteria andPfInnerIdLessThanOrEqualTo(Integer value) {
            addCriterion("pf_inner_id <=", value, "pfInnerId");
            return (Criteria) this;
        }

        public Criteria andPfInnerIdIn(List<Integer> values) {
            addCriterion("pf_inner_id in", values, "pfInnerId");
            return (Criteria) this;
        }

        public Criteria andPfInnerIdNotIn(List<Integer> values) {
            addCriterion("pf_inner_id not in", values, "pfInnerId");
            return (Criteria) this;
        }

        public Criteria andPfInnerIdBetween(Integer value1, Integer value2) {
            addCriterion("pf_inner_id between", value1, value2, "pfInnerId");
            return (Criteria) this;
        }

        public Criteria andPfInnerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pf_inner_id not between", value1, value2, "pfInnerId");
            return (Criteria) this;
        }

        public Criteria andTermIdIsNull() {
            addCriterion("term_id is null");
            return (Criteria) this;
        }

        public Criteria andTermIdIsNotNull() {
            addCriterion("term_id is not null");
            return (Criteria) this;
        }

        public Criteria andTermIdEqualTo(Short value) {
            addCriterion("term_id =", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdNotEqualTo(Short value) {
            addCriterion("term_id <>", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdGreaterThan(Short value) {
            addCriterion("term_id >", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdGreaterThanOrEqualTo(Short value) {
            addCriterion("term_id >=", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdLessThan(Short value) {
            addCriterion("term_id <", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdLessThanOrEqualTo(Short value) {
            addCriterion("term_id <=", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdIn(List<Short> values) {
            addCriterion("term_id in", values, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdNotIn(List<Short> values) {
            addCriterion("term_id not in", values, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdBetween(Short value1, Short value2) {
            addCriterion("term_id between", value1, value2, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdNotBetween(Short value1, Short value2) {
            addCriterion("term_id not between", value1, value2, "termId");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIsNull() {
            addCriterion("pay_password is null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIsNotNull() {
            addCriterion("pay_password is not null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordEqualTo(String value) {
            addCriterion("pay_password =", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotEqualTo(String value) {
            addCriterion("pay_password <>", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordGreaterThan(String value) {
            addCriterion("pay_password >", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("pay_password >=", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLessThan(String value) {
            addCriterion("pay_password <", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLessThanOrEqualTo(String value) {
            addCriterion("pay_password <=", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLike(String value) {
            addCriterion("pay_password like", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotLike(String value) {
            addCriterion("pay_password not like", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIn(List<String> values) {
            addCriterion("pay_password in", values, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotIn(List<String> values) {
            addCriterion("pay_password not in", values, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordBetween(String value1, String value2) {
            addCriterion("pay_password between", value1, value2, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotBetween(String value1, String value2) {
            addCriterion("pay_password not between", value1, value2, "payPassword");
            return (Criteria) this;
        }

        public Criteria andAcTypeIsNull() {
            addCriterion("ac_type is null");
            return (Criteria) this;
        }

        public Criteria andAcTypeIsNotNull() {
            addCriterion("ac_type is not null");
            return (Criteria) this;
        }

        public Criteria andAcTypeEqualTo(Short value) {
            addCriterion("ac_type =", value, "acType");
            return (Criteria) this;
        }

        public Criteria andAcTypeNotEqualTo(Short value) {
            addCriterion("ac_type <>", value, "acType");
            return (Criteria) this;
        }

        public Criteria andAcTypeGreaterThan(Short value) {
            addCriterion("ac_type >", value, "acType");
            return (Criteria) this;
        }

        public Criteria andAcTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("ac_type >=", value, "acType");
            return (Criteria) this;
        }

        public Criteria andAcTypeLessThan(Short value) {
            addCriterion("ac_type <", value, "acType");
            return (Criteria) this;
        }

        public Criteria andAcTypeLessThanOrEqualTo(Short value) {
            addCriterion("ac_type <=", value, "acType");
            return (Criteria) this;
        }

        public Criteria andAcTypeIn(List<Short> values) {
            addCriterion("ac_type in", values, "acType");
            return (Criteria) this;
        }

        public Criteria andAcTypeNotIn(List<Short> values) {
            addCriterion("ac_type not in", values, "acType");
            return (Criteria) this;
        }

        public Criteria andAcTypeBetween(Short value1, Short value2) {
            addCriterion("ac_type between", value1, value2, "acType");
            return (Criteria) this;
        }

        public Criteria andAcTypeNotBetween(Short value1, Short value2) {
            addCriterion("ac_type not between", value1, value2, "acType");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceInIsNull() {
            addCriterion("balance_in is null");
            return (Criteria) this;
        }

        public Criteria andBalanceInIsNotNull() {
            addCriterion("balance_in is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceInEqualTo(BigDecimal value) {
            addCriterion("balance_in =", value, "balanceIn");
            return (Criteria) this;
        }

        public Criteria andBalanceInNotEqualTo(BigDecimal value) {
            addCriterion("balance_in <>", value, "balanceIn");
            return (Criteria) this;
        }

        public Criteria andBalanceInGreaterThan(BigDecimal value) {
            addCriterion("balance_in >", value, "balanceIn");
            return (Criteria) this;
        }

        public Criteria andBalanceInGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_in >=", value, "balanceIn");
            return (Criteria) this;
        }

        public Criteria andBalanceInLessThan(BigDecimal value) {
            addCriterion("balance_in <", value, "balanceIn");
            return (Criteria) this;
        }

        public Criteria andBalanceInLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_in <=", value, "balanceIn");
            return (Criteria) this;
        }

        public Criteria andBalanceInIn(List<BigDecimal> values) {
            addCriterion("balance_in in", values, "balanceIn");
            return (Criteria) this;
        }

        public Criteria andBalanceInNotIn(List<BigDecimal> values) {
            addCriterion("balance_in not in", values, "balanceIn");
            return (Criteria) this;
        }

        public Criteria andBalanceInBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_in between", value1, value2, "balanceIn");
            return (Criteria) this;
        }

        public Criteria andBalanceInNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_in not between", value1, value2, "balanceIn");
            return (Criteria) this;
        }

        public Criteria andBalanceOutIsNull() {
            addCriterion("balance_out is null");
            return (Criteria) this;
        }

        public Criteria andBalanceOutIsNotNull() {
            addCriterion("balance_out is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceOutEqualTo(BigDecimal value) {
            addCriterion("balance_out =", value, "balanceOut");
            return (Criteria) this;
        }

        public Criteria andBalanceOutNotEqualTo(BigDecimal value) {
            addCriterion("balance_out <>", value, "balanceOut");
            return (Criteria) this;
        }

        public Criteria andBalanceOutGreaterThan(BigDecimal value) {
            addCriterion("balance_out >", value, "balanceOut");
            return (Criteria) this;
        }

        public Criteria andBalanceOutGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_out >=", value, "balanceOut");
            return (Criteria) this;
        }

        public Criteria andBalanceOutLessThan(BigDecimal value) {
            addCriterion("balance_out <", value, "balanceOut");
            return (Criteria) this;
        }

        public Criteria andBalanceOutLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_out <=", value, "balanceOut");
            return (Criteria) this;
        }

        public Criteria andBalanceOutIn(List<BigDecimal> values) {
            addCriterion("balance_out in", values, "balanceOut");
            return (Criteria) this;
        }

        public Criteria andBalanceOutNotIn(List<BigDecimal> values) {
            addCriterion("balance_out not in", values, "balanceOut");
            return (Criteria) this;
        }

        public Criteria andBalanceOutBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_out between", value1, value2, "balanceOut");
            return (Criteria) this;
        }

        public Criteria andBalanceOutNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_out not between", value1, value2, "balanceOut");
            return (Criteria) this;
        }

        public Criteria andBalanceAgentIsNull() {
            addCriterion("balance_agent is null");
            return (Criteria) this;
        }

        public Criteria andBalanceAgentIsNotNull() {
            addCriterion("balance_agent is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceAgentEqualTo(BigDecimal value) {
            addCriterion("balance_agent =", value, "balanceAgent");
            return (Criteria) this;
        }

        public Criteria andBalanceAgentNotEqualTo(BigDecimal value) {
            addCriterion("balance_agent <>", value, "balanceAgent");
            return (Criteria) this;
        }

        public Criteria andBalanceAgentGreaterThan(BigDecimal value) {
            addCriterion("balance_agent >", value, "balanceAgent");
            return (Criteria) this;
        }

        public Criteria andBalanceAgentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_agent >=", value, "balanceAgent");
            return (Criteria) this;
        }

        public Criteria andBalanceAgentLessThan(BigDecimal value) {
            addCriterion("balance_agent <", value, "balanceAgent");
            return (Criteria) this;
        }

        public Criteria andBalanceAgentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_agent <=", value, "balanceAgent");
            return (Criteria) this;
        }

        public Criteria andBalanceAgentIn(List<BigDecimal> values) {
            addCriterion("balance_agent in", values, "balanceAgent");
            return (Criteria) this;
        }

        public Criteria andBalanceAgentNotIn(List<BigDecimal> values) {
            addCriterion("balance_agent not in", values, "balanceAgent");
            return (Criteria) this;
        }

        public Criteria andBalanceAgentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_agent between", value1, value2, "balanceAgent");
            return (Criteria) this;
        }

        public Criteria andBalanceAgentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_agent not between", value1, value2, "balanceAgent");
            return (Criteria) this;
        }

        public Criteria andTakeoutMoneyIsNull() {
            addCriterion("takeout_money is null");
            return (Criteria) this;
        }

        public Criteria andTakeoutMoneyIsNotNull() {
            addCriterion("takeout_money is not null");
            return (Criteria) this;
        }

        public Criteria andTakeoutMoneyEqualTo(BigDecimal value) {
            addCriterion("takeout_money =", value, "takeoutMoney");
            return (Criteria) this;
        }

        public Criteria andTakeoutMoneyNotEqualTo(BigDecimal value) {
            addCriterion("takeout_money <>", value, "takeoutMoney");
            return (Criteria) this;
        }

        public Criteria andTakeoutMoneyGreaterThan(BigDecimal value) {
            addCriterion("takeout_money >", value, "takeoutMoney");
            return (Criteria) this;
        }

        public Criteria andTakeoutMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("takeout_money >=", value, "takeoutMoney");
            return (Criteria) this;
        }

        public Criteria andTakeoutMoneyLessThan(BigDecimal value) {
            addCriterion("takeout_money <", value, "takeoutMoney");
            return (Criteria) this;
        }

        public Criteria andTakeoutMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("takeout_money <=", value, "takeoutMoney");
            return (Criteria) this;
        }

        public Criteria andTakeoutMoneyIn(List<BigDecimal> values) {
            addCriterion("takeout_money in", values, "takeoutMoney");
            return (Criteria) this;
        }

        public Criteria andTakeoutMoneyNotIn(List<BigDecimal> values) {
            addCriterion("takeout_money not in", values, "takeoutMoney");
            return (Criteria) this;
        }

        public Criteria andTakeoutMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("takeout_money between", value1, value2, "takeoutMoney");
            return (Criteria) this;
        }

        public Criteria andTakeoutMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("takeout_money not between", value1, value2, "takeoutMoney");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNull() {
            addCriterion("open_time is null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNotNull() {
            addCriterion("open_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeEqualTo(Date value) {
            addCriterion("open_time =", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotEqualTo(Date value) {
            addCriterion("open_time <>", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThan(Date value) {
            addCriterion("open_time >", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("open_time >=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThan(Date value) {
            addCriterion("open_time <", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThanOrEqualTo(Date value) {
            addCriterion("open_time <=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIn(List<Date> values) {
            addCriterion("open_time in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotIn(List<Date> values) {
            addCriterion("open_time not in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeBetween(Date value1, Date value2) {
            addCriterion("open_time between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotBetween(Date value1, Date value2) {
            addCriterion("open_time not between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andCalTimeIsNull() {
            addCriterion("cal_time is null");
            return (Criteria) this;
        }

        public Criteria andCalTimeIsNotNull() {
            addCriterion("cal_time is not null");
            return (Criteria) this;
        }

        public Criteria andCalTimeEqualTo(Date value) {
            addCriterion("cal_time =", value, "calTime");
            return (Criteria) this;
        }

        public Criteria andCalTimeNotEqualTo(Date value) {
            addCriterion("cal_time <>", value, "calTime");
            return (Criteria) this;
        }

        public Criteria andCalTimeGreaterThan(Date value) {
            addCriterion("cal_time >", value, "calTime");
            return (Criteria) this;
        }

        public Criteria andCalTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cal_time >=", value, "calTime");
            return (Criteria) this;
        }

        public Criteria andCalTimeLessThan(Date value) {
            addCriterion("cal_time <", value, "calTime");
            return (Criteria) this;
        }

        public Criteria andCalTimeLessThanOrEqualTo(Date value) {
            addCriterion("cal_time <=", value, "calTime");
            return (Criteria) this;
        }

        public Criteria andCalTimeIn(List<Date> values) {
            addCriterion("cal_time in", values, "calTime");
            return (Criteria) this;
        }

        public Criteria andCalTimeNotIn(List<Date> values) {
            addCriterion("cal_time not in", values, "calTime");
            return (Criteria) this;
        }

        public Criteria andCalTimeBetween(Date value1, Date value2) {
            addCriterion("cal_time between", value1, value2, "calTime");
            return (Criteria) this;
        }

        public Criteria andCalTimeNotBetween(Date value1, Date value2) {
            addCriterion("cal_time not between", value1, value2, "calTime");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}