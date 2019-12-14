package ytb.account.wallet.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountUserInnerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public AccountUserInnerExample() {
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

        public Criteria andInnerIdIsNull() {
            addCriterion("inner_id is null");
            return (Criteria) this;
        }

        public Criteria andInnerIdIsNotNull() {
            addCriterion("inner_id is not null");
            return (Criteria) this;
        }

        public Criteria andInnerIdEqualTo(Integer value) {
            addCriterion("inner_id =", value, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdNotEqualTo(Integer value) {
            addCriterion("inner_id <>", value, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdGreaterThan(Integer value) {
            addCriterion("inner_id >", value, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("inner_id >=", value, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdLessThan(Integer value) {
            addCriterion("inner_id <", value, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdLessThanOrEqualTo(Integer value) {
            addCriterion("inner_id <=", value, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdIn(List<Integer> values) {
            addCriterion("inner_id in", values, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdNotIn(List<Integer> values) {
            addCriterion("inner_id not in", values, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdBetween(Integer value1, Integer value2) {
            addCriterion("inner_id between", value1, value2, "innerId");
            return (Criteria) this;
        }

        public Criteria andInnerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("inner_id not between", value1, value2, "innerId");
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

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(Byte value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(Byte value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(Byte value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(Byte value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(Byte value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<Byte> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<Byte> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(Byte value1, Byte value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
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

        public Criteria andPayoutAgentIsNull() {
            addCriterion("payout_agent is null");
            return (Criteria) this;
        }

        public Criteria andPayoutAgentIsNotNull() {
            addCriterion("payout_agent is not null");
            return (Criteria) this;
        }

        public Criteria andPayoutAgentEqualTo(BigDecimal value) {
            addCriterion("payout_agent =", value, "payoutAgent");
            return (Criteria) this;
        }

        public Criteria andPayoutAgentNotEqualTo(BigDecimal value) {
            addCriterion("payout_agent <>", value, "payoutAgent");
            return (Criteria) this;
        }

        public Criteria andPayoutAgentGreaterThan(BigDecimal value) {
            addCriterion("payout_agent >", value, "payoutAgent");
            return (Criteria) this;
        }

        public Criteria andPayoutAgentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("payout_agent >=", value, "payoutAgent");
            return (Criteria) this;
        }

        public Criteria andPayoutAgentLessThan(BigDecimal value) {
            addCriterion("payout_agent <", value, "payoutAgent");
            return (Criteria) this;
        }

        public Criteria andPayoutAgentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("payout_agent <=", value, "payoutAgent");
            return (Criteria) this;
        }

        public Criteria andPayoutAgentIn(List<BigDecimal> values) {
            addCriterion("payout_agent in", values, "payoutAgent");
            return (Criteria) this;
        }

        public Criteria andPayoutAgentNotIn(List<BigDecimal> values) {
            addCriterion("payout_agent not in", values, "payoutAgent");
            return (Criteria) this;
        }

        public Criteria andPayoutAgentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payout_agent between", value1, value2, "payoutAgent");
            return (Criteria) this;
        }

        public Criteria andPayoutAgentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payout_agent not between", value1, value2, "payoutAgent");
            return (Criteria) this;
        }

        public Criteria andIncomeAgentIsNull() {
            addCriterion("income_agent is null");
            return (Criteria) this;
        }

        public Criteria andIncomeAgentIsNotNull() {
            addCriterion("income_agent is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeAgentEqualTo(BigDecimal value) {
            addCriterion("income_agent =", value, "incomeAgent");
            return (Criteria) this;
        }

        public Criteria andIncomeAgentNotEqualTo(BigDecimal value) {
            addCriterion("income_agent <>", value, "incomeAgent");
            return (Criteria) this;
        }

        public Criteria andIncomeAgentGreaterThan(BigDecimal value) {
            addCriterion("income_agent >", value, "incomeAgent");
            return (Criteria) this;
        }

        public Criteria andIncomeAgentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("income_agent >=", value, "incomeAgent");
            return (Criteria) this;
        }

        public Criteria andIncomeAgentLessThan(BigDecimal value) {
            addCriterion("income_agent <", value, "incomeAgent");
            return (Criteria) this;
        }

        public Criteria andIncomeAgentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("income_agent <=", value, "incomeAgent");
            return (Criteria) this;
        }

        public Criteria andIncomeAgentIn(List<BigDecimal> values) {
            addCriterion("income_agent in", values, "incomeAgent");
            return (Criteria) this;
        }

        public Criteria andIncomeAgentNotIn(List<BigDecimal> values) {
            addCriterion("income_agent not in", values, "incomeAgent");
            return (Criteria) this;
        }

        public Criteria andIncomeAgentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("income_agent between", value1, value2, "incomeAgent");
            return (Criteria) this;
        }

        public Criteria andIncomeAgentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("income_agent not between", value1, value2, "incomeAgent");
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