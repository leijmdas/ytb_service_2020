package ytb.account.wallet.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountTradeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public AccountTradeExample() {
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

        public Criteria andTradeIdIsNull() {
            addCriterion("trade_id is null");
            return (Criteria) this;
        }

        public Criteria andTradeIdIsNotNull() {
            addCriterion("trade_id is not null");
            return (Criteria) this;
        }

        public Criteria andTradeIdEqualTo(Integer value) {
            addCriterion("trade_id =", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotEqualTo(Integer value) {
            addCriterion("trade_id <>", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThan(Integer value) {
            addCriterion("trade_id >", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade_id >=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThan(Integer value) {
            addCriterion("trade_id <", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThanOrEqualTo(Integer value) {
            addCriterion("trade_id <=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdIn(List<Integer> values) {
            addCriterion("trade_id in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotIn(List<Integer> values) {
            addCriterion("trade_id not in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdBetween(Integer value1, Integer value2) {
            addCriterion("trade_id between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("trade_id not between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeNoIsNull() {
            addCriterion("trade_no is null");
            return (Criteria) this;
        }

        public Criteria andTradeNoIsNotNull() {
            addCriterion("trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andTradeNoEqualTo(String value) {
            addCriterion("trade_no =", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotEqualTo(String value) {
            addCriterion("trade_no <>", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoGreaterThan(String value) {
            addCriterion("trade_no >", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("trade_no >=", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLessThan(String value) {
            addCriterion("trade_no <", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLessThanOrEqualTo(String value) {
            addCriterion("trade_no <=", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLike(String value) {
            addCriterion("trade_no like", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotLike(String value) {
            addCriterion("trade_no not like", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoIn(List<String> values) {
            addCriterion("trade_no in", values, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotIn(List<String> values) {
            addCriterion("trade_no not in", values, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoBetween(String value1, String value2) {
            addCriterion("trade_no between", value1, value2, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotBetween(String value1, String value2) {
            addCriterion("trade_no not between", value1, value2, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreIsNull() {
            addCriterion("trade_no_pre is null");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreIsNotNull() {
            addCriterion("trade_no_pre is not null");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreEqualTo(String value) {
            addCriterion("trade_no_pre =", value, "tradeNoPre");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreNotEqualTo(String value) {
            addCriterion("trade_no_pre <>", value, "tradeNoPre");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreGreaterThan(String value) {
            addCriterion("trade_no_pre >", value, "tradeNoPre");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreGreaterThanOrEqualTo(String value) {
            addCriterion("trade_no_pre >=", value, "tradeNoPre");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreLessThan(String value) {
            addCriterion("trade_no_pre <", value, "tradeNoPre");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreLessThanOrEqualTo(String value) {
            addCriterion("trade_no_pre <=", value, "tradeNoPre");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreLike(String value) {
            addCriterion("trade_no_pre like", value, "tradeNoPre");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreNotLike(String value) {
            addCriterion("trade_no_pre not like", value, "tradeNoPre");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreIn(List<String> values) {
            addCriterion("trade_no_pre in", values, "tradeNoPre");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreNotIn(List<String> values) {
            addCriterion("trade_no_pre not in", values, "tradeNoPre");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreBetween(String value1, String value2) {
            addCriterion("trade_no_pre between", value1, value2, "tradeNoPre");
            return (Criteria) this;
        }

        public Criteria andTradeNoPreNotBetween(String value1, String value2) {
            addCriterion("trade_no_pre not between", value1, value2, "tradeNoPre");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andAcIdIsNull() {
            addCriterion("ac_id is null");
            return (Criteria) this;
        }

        public Criteria andAcIdIsNotNull() {
            addCriterion("ac_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcIdEqualTo(Integer value) {
            addCriterion("ac_id =", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdNotEqualTo(Integer value) {
            addCriterion("ac_id <>", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdGreaterThan(Integer value) {
            addCriterion("ac_id >", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ac_id >=", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdLessThan(Integer value) {
            addCriterion("ac_id <", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdLessThanOrEqualTo(Integer value) {
            addCriterion("ac_id <=", value, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdIn(List<Integer> values) {
            addCriterion("ac_id in", values, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdNotIn(List<Integer> values) {
            addCriterion("ac_id not in", values, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdBetween(Integer value1, Integer value2) {
            addCriterion("ac_id between", value1, value2, "acId");
            return (Criteria) this;
        }

        public Criteria andAcIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ac_id not between", value1, value2, "acId");
            return (Criteria) this;
        }

        public Criteria andToAcIdIsNull() {
            addCriterion("to_ac_id is null");
            return (Criteria) this;
        }

        public Criteria andToAcIdIsNotNull() {
            addCriterion("to_ac_id is not null");
            return (Criteria) this;
        }

        public Criteria andToAcIdEqualTo(Integer value) {
            addCriterion("to_ac_id =", value, "toAcId");
            return (Criteria) this;
        }

        public Criteria andToAcIdNotEqualTo(Integer value) {
            addCriterion("to_ac_id <>", value, "toAcId");
            return (Criteria) this;
        }

        public Criteria andToAcIdGreaterThan(Integer value) {
            addCriterion("to_ac_id >", value, "toAcId");
            return (Criteria) this;
        }

        public Criteria andToAcIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("to_ac_id >=", value, "toAcId");
            return (Criteria) this;
        }

        public Criteria andToAcIdLessThan(Integer value) {
            addCriterion("to_ac_id <", value, "toAcId");
            return (Criteria) this;
        }

        public Criteria andToAcIdLessThanOrEqualTo(Integer value) {
            addCriterion("to_ac_id <=", value, "toAcId");
            return (Criteria) this;
        }

        public Criteria andToAcIdIn(List<Integer> values) {
            addCriterion("to_ac_id in", values, "toAcId");
            return (Criteria) this;
        }

        public Criteria andToAcIdNotIn(List<Integer> values) {
            addCriterion("to_ac_id not in", values, "toAcId");
            return (Criteria) this;
        }

        public Criteria andToAcIdBetween(Integer value1, Integer value2) {
            addCriterion("to_ac_id between", value1, value2, "toAcId");
            return (Criteria) this;
        }

        public Criteria andToAcIdNotBetween(Integer value1, Integer value2) {
            addCriterion("to_ac_id not between", value1, value2, "toAcId");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNull() {
            addCriterion("trade_type is null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNotNull() {
            addCriterion("trade_type is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeEqualTo(Short value) {
            addCriterion("trade_type =", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotEqualTo(Short value) {
            addCriterion("trade_type <>", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThan(Short value) {
            addCriterion("trade_type >", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("trade_type >=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThan(Short value) {
            addCriterion("trade_type <", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThanOrEqualTo(Short value) {
            addCriterion("trade_type <=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIn(List<Short> values) {
            addCriterion("trade_type in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotIn(List<Short> values) {
            addCriterion("trade_type not in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeBetween(Short value1, Short value2) {
            addCriterion("trade_type between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotBetween(Short value1, Short value2) {
            addCriterion("trade_type not between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeSubtypeIsNull() {
            addCriterion("trade_subtype is null");
            return (Criteria) this;
        }

        public Criteria andTradeSubtypeIsNotNull() {
            addCriterion("trade_subtype is not null");
            return (Criteria) this;
        }

        public Criteria andTradeSubtypeEqualTo(Short value) {
            addCriterion("trade_subtype =", value, "tradeSubtype");
            return (Criteria) this;
        }

        public Criteria andTradeSubtypeNotEqualTo(Short value) {
            addCriterion("trade_subtype <>", value, "tradeSubtype");
            return (Criteria) this;
        }

        public Criteria andTradeSubtypeGreaterThan(Short value) {
            addCriterion("trade_subtype >", value, "tradeSubtype");
            return (Criteria) this;
        }

        public Criteria andTradeSubtypeGreaterThanOrEqualTo(Short value) {
            addCriterion("trade_subtype >=", value, "tradeSubtype");
            return (Criteria) this;
        }

        public Criteria andTradeSubtypeLessThan(Short value) {
            addCriterion("trade_subtype <", value, "tradeSubtype");
            return (Criteria) this;
        }

        public Criteria andTradeSubtypeLessThanOrEqualTo(Short value) {
            addCriterion("trade_subtype <=", value, "tradeSubtype");
            return (Criteria) this;
        }

        public Criteria andTradeSubtypeIn(List<Short> values) {
            addCriterion("trade_subtype in", values, "tradeSubtype");
            return (Criteria) this;
        }

        public Criteria andTradeSubtypeNotIn(List<Short> values) {
            addCriterion("trade_subtype not in", values, "tradeSubtype");
            return (Criteria) this;
        }

        public Criteria andTradeSubtypeBetween(Short value1, Short value2) {
            addCriterion("trade_subtype between", value1, value2, "tradeSubtype");
            return (Criteria) this;
        }

        public Criteria andTradeSubtypeNotBetween(Short value1, Short value2) {
            addCriterion("trade_subtype not between", value1, value2, "tradeSubtype");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNull() {
            addCriterion("service_type is null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNotNull() {
            addCriterion("service_type is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeEqualTo(Byte value) {
            addCriterion("service_type =", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotEqualTo(Byte value) {
            addCriterion("service_type <>", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThan(Byte value) {
            addCriterion("service_type >", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("service_type >=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThan(Byte value) {
            addCriterion("service_type <", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThanOrEqualTo(Byte value) {
            addCriterion("service_type <=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIn(List<Byte> values) {
            addCriterion("service_type in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotIn(List<Byte> values) {
            addCriterion("service_type not in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeBetween(Byte value1, Byte value2) {
            addCriterion("service_type between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("service_type not between", value1, value2, "serviceType");
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

        public Criteria andServiceFeeIsNull() {
            addCriterion("service_fee is null");
            return (Criteria) this;
        }

        public Criteria andServiceFeeIsNotNull() {
            addCriterion("service_fee is not null");
            return (Criteria) this;
        }

        public Criteria andServiceFeeEqualTo(BigDecimal value) {
            addCriterion("service_fee =", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeNotEqualTo(BigDecimal value) {
            addCriterion("service_fee <>", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeGreaterThan(BigDecimal value) {
            addCriterion("service_fee >", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("service_fee >=", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeLessThan(BigDecimal value) {
            addCriterion("service_fee <", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("service_fee <=", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeIn(List<BigDecimal> values) {
            addCriterion("service_fee in", values, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeNotIn(List<BigDecimal> values) {
            addCriterion("service_fee not in", values, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("service_fee between", value1, value2, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("service_fee not between", value1, value2, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(BigDecimal value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(BigDecimal value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(BigDecimal value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(BigDecimal value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<BigDecimal> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<BigDecimal> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andTaxIsNull() {
            addCriterion("tax is null");
            return (Criteria) this;
        }

        public Criteria andTaxIsNotNull() {
            addCriterion("tax is not null");
            return (Criteria) this;
        }

        public Criteria andTaxEqualTo(BigDecimal value) {
            addCriterion("tax =", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotEqualTo(BigDecimal value) {
            addCriterion("tax <>", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThan(BigDecimal value) {
            addCriterion("tax >", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tax >=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThan(BigDecimal value) {
            addCriterion("tax <", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tax <=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxIn(List<BigDecimal> values) {
            addCriterion("tax in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotIn(List<BigDecimal> values) {
            addCriterion("tax not in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tax not between", value1, value2, "tax");
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

        public Criteria andIsAIsNull() {
            addCriterion("is_a is null");
            return (Criteria) this;
        }

        public Criteria andIsAIsNotNull() {
            addCriterion("is_a is not null");
            return (Criteria) this;
        }

        public Criteria andIsAEqualTo(Boolean value) {
            addCriterion("is_a =", value, "isA");
            return (Criteria) this;
        }

        public Criteria andIsANotEqualTo(Boolean value) {
            addCriterion("is_a <>", value, "isA");
            return (Criteria) this;
        }

        public Criteria andIsAGreaterThan(Boolean value) {
            addCriterion("is_a >", value, "isA");
            return (Criteria) this;
        }

        public Criteria andIsAGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_a >=", value, "isA");
            return (Criteria) this;
        }

        public Criteria andIsALessThan(Boolean value) {
            addCriterion("is_a <", value, "isA");
            return (Criteria) this;
        }

        public Criteria andIsALessThanOrEqualTo(Boolean value) {
            addCriterion("is_a <=", value, "isA");
            return (Criteria) this;
        }

        public Criteria andIsAIn(List<Boolean> values) {
            addCriterion("is_a in", values, "isA");
            return (Criteria) this;
        }

        public Criteria andIsANotIn(List<Boolean> values) {
            addCriterion("is_a not in", values, "isA");
            return (Criteria) this;
        }

        public Criteria andIsABetween(Boolean value1, Boolean value2) {
            addCriterion("is_a between", value1, value2, "isA");
            return (Criteria) this;
        }

        public Criteria andIsANotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_a not between", value1, value2, "isA");
            return (Criteria) this;
        }

        public Criteria andRetryTimesIsNull() {
            addCriterion("retry_times is null");
            return (Criteria) this;
        }

        public Criteria andRetryTimesIsNotNull() {
            addCriterion("retry_times is not null");
            return (Criteria) this;
        }

        public Criteria andRetryTimesEqualTo(Byte value) {
            addCriterion("retry_times =", value, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesNotEqualTo(Byte value) {
            addCriterion("retry_times <>", value, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesGreaterThan(Byte value) {
            addCriterion("retry_times >", value, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesGreaterThanOrEqualTo(Byte value) {
            addCriterion("retry_times >=", value, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesLessThan(Byte value) {
            addCriterion("retry_times <", value, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesLessThanOrEqualTo(Byte value) {
            addCriterion("retry_times <=", value, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesIn(List<Byte> values) {
            addCriterion("retry_times in", values, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesNotIn(List<Byte> values) {
            addCriterion("retry_times not in", values, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesBetween(Byte value1, Byte value2) {
            addCriterion("retry_times between", value1, value2, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andRetryTimesNotBetween(Byte value1, Byte value2) {
            addCriterion("retry_times not between", value1, value2, "retryTimes");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Integer value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Integer value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Integer value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Integer value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Integer value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Integer> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Integer> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Integer value1, Integer value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Integer value1, Integer value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCalFlagIsNull() {
            addCriterion("cal_flag is null");
            return (Criteria) this;
        }

        public Criteria andCalFlagIsNotNull() {
            addCriterion("cal_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCalFlagEqualTo(Boolean value) {
            addCriterion("cal_flag =", value, "calFlag");
            return (Criteria) this;
        }

        public Criteria andCalFlagNotEqualTo(Boolean value) {
            addCriterion("cal_flag <>", value, "calFlag");
            return (Criteria) this;
        }

        public Criteria andCalFlagGreaterThan(Boolean value) {
            addCriterion("cal_flag >", value, "calFlag");
            return (Criteria) this;
        }

        public Criteria andCalFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("cal_flag >=", value, "calFlag");
            return (Criteria) this;
        }

        public Criteria andCalFlagLessThan(Boolean value) {
            addCriterion("cal_flag <", value, "calFlag");
            return (Criteria) this;
        }

        public Criteria andCalFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("cal_flag <=", value, "calFlag");
            return (Criteria) this;
        }

        public Criteria andCalFlagIn(List<Boolean> values) {
            addCriterion("cal_flag in", values, "calFlag");
            return (Criteria) this;
        }

        public Criteria andCalFlagNotIn(List<Boolean> values) {
            addCriterion("cal_flag not in", values, "calFlag");
            return (Criteria) this;
        }

        public Criteria andCalFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("cal_flag between", value1, value2, "calFlag");
            return (Criteria) this;
        }

        public Criteria andCalFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("cal_flag not between", value1, value2, "calFlag");
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

        public Criteria andOutIdIsNull() {
            addCriterion("out_id is null");
            return (Criteria) this;
        }

        public Criteria andOutIdIsNotNull() {
            addCriterion("out_id is not null");
            return (Criteria) this;
        }

        public Criteria andOutIdEqualTo(Integer value) {
            addCriterion("out_id =", value, "outId");
            return (Criteria) this;
        }

        public Criteria andOutIdNotEqualTo(Integer value) {
            addCriterion("out_id <>", value, "outId");
            return (Criteria) this;
        }

        public Criteria andOutIdGreaterThan(Integer value) {
            addCriterion("out_id >", value, "outId");
            return (Criteria) this;
        }

        public Criteria andOutIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("out_id >=", value, "outId");
            return (Criteria) this;
        }

        public Criteria andOutIdLessThan(Integer value) {
            addCriterion("out_id <", value, "outId");
            return (Criteria) this;
        }

        public Criteria andOutIdLessThanOrEqualTo(Integer value) {
            addCriterion("out_id <=", value, "outId");
            return (Criteria) this;
        }

        public Criteria andOutIdIn(List<Integer> values) {
            addCriterion("out_id in", values, "outId");
            return (Criteria) this;
        }

        public Criteria andOutIdNotIn(List<Integer> values) {
            addCriterion("out_id not in", values, "outId");
            return (Criteria) this;
        }

        public Criteria andOutIdBetween(Integer value1, Integer value2) {
            addCriterion("out_id between", value1, value2, "outId");
            return (Criteria) this;
        }

        public Criteria andOutIdNotBetween(Integer value1, Integer value2) {
            addCriterion("out_id not between", value1, value2, "outId");
            return (Criteria) this;
        }

        public Criteria andTotalBalanceIsNull() {
            addCriterion("total_balance is null");
            return (Criteria) this;
        }

        public Criteria andTotalBalanceIsNotNull() {
            addCriterion("total_balance is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBalanceEqualTo(BigDecimal value) {
            addCriterion("total_balance =", value, "totalBalance");
            return (Criteria) this;
        }

        public Criteria andTotalBalanceNotEqualTo(BigDecimal value) {
            addCriterion("total_balance <>", value, "totalBalance");
            return (Criteria) this;
        }

        public Criteria andTotalBalanceGreaterThan(BigDecimal value) {
            addCriterion("total_balance >", value, "totalBalance");
            return (Criteria) this;
        }

        public Criteria andTotalBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_balance >=", value, "totalBalance");
            return (Criteria) this;
        }

        public Criteria andTotalBalanceLessThan(BigDecimal value) {
            addCriterion("total_balance <", value, "totalBalance");
            return (Criteria) this;
        }

        public Criteria andTotalBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_balance <=", value, "totalBalance");
            return (Criteria) this;
        }

        public Criteria andTotalBalanceIn(List<BigDecimal> values) {
            addCriterion("total_balance in", values, "totalBalance");
            return (Criteria) this;
        }

        public Criteria andTotalBalanceNotIn(List<BigDecimal> values) {
            addCriterion("total_balance not in", values, "totalBalance");
            return (Criteria) this;
        }

        public Criteria andTotalBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_balance between", value1, value2, "totalBalance");
            return (Criteria) this;
        }

        public Criteria andTotalBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_balance not between", value1, value2, "totalBalance");
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

        public Criteria andTermIdEqualTo(Integer value) {
            addCriterion("term_id =", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdNotEqualTo(Integer value) {
            addCriterion("term_id <>", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdGreaterThan(Integer value) {
            addCriterion("term_id >", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("term_id >=", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdLessThan(Integer value) {
            addCriterion("term_id <", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdLessThanOrEqualTo(Integer value) {
            addCriterion("term_id <=", value, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdIn(List<Integer> values) {
            addCriterion("term_id in", values, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdNotIn(List<Integer> values) {
            addCriterion("term_id not in", values, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdBetween(Integer value1, Integer value2) {
            addCriterion("term_id between", value1, value2, "termId");
            return (Criteria) this;
        }

        public Criteria andTermIdNotBetween(Integer value1, Integer value2) {
            addCriterion("term_id not between", value1, value2, "termId");
            return (Criteria) this;
        }

        public Criteria andIpAddressIsNull() {
            addCriterion("ip_address is null");
            return (Criteria) this;
        }

        public Criteria andIpAddressIsNotNull() {
            addCriterion("ip_address is not null");
            return (Criteria) this;
        }

        public Criteria andIpAddressEqualTo(String value) {
            addCriterion("ip_address =", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotEqualTo(String value) {
            addCriterion("ip_address <>", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressGreaterThan(String value) {
            addCriterion("ip_address >", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ip_address >=", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLessThan(String value) {
            addCriterion("ip_address <", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLessThanOrEqualTo(String value) {
            addCriterion("ip_address <=", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLike(String value) {
            addCriterion("ip_address like", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotLike(String value) {
            addCriterion("ip_address not like", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressIn(List<String> values) {
            addCriterion("ip_address in", values, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotIn(List<String> values) {
            addCriterion("ip_address not in", values, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressBetween(String value1, String value2) {
            addCriterion("ip_address between", value1, value2, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotBetween(String value1, String value2) {
            addCriterion("ip_address not between", value1, value2, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andRefundBalanceIsNull() {
            addCriterion("refund_balance is null");
            return (Criteria) this;
        }

        public Criteria andRefundBalanceIsNotNull() {
            addCriterion("refund_balance is not null");
            return (Criteria) this;
        }

        public Criteria andRefundBalanceEqualTo(BigDecimal value) {
            addCriterion("refund_balance =", value, "refundBalance");
            return (Criteria) this;
        }

        public Criteria andRefundBalanceNotEqualTo(BigDecimal value) {
            addCriterion("refund_balance <>", value, "refundBalance");
            return (Criteria) this;
        }

        public Criteria andRefundBalanceGreaterThan(BigDecimal value) {
            addCriterion("refund_balance >", value, "refundBalance");
            return (Criteria) this;
        }

        public Criteria andRefundBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_balance >=", value, "refundBalance");
            return (Criteria) this;
        }

        public Criteria andRefundBalanceLessThan(BigDecimal value) {
            addCriterion("refund_balance <", value, "refundBalance");
            return (Criteria) this;
        }

        public Criteria andRefundBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_balance <=", value, "refundBalance");
            return (Criteria) this;
        }

        public Criteria andRefundBalanceIn(List<BigDecimal> values) {
            addCriterion("refund_balance in", values, "refundBalance");
            return (Criteria) this;
        }

        public Criteria andRefundBalanceNotIn(List<BigDecimal> values) {
            addCriterion("refund_balance not in", values, "refundBalance");
            return (Criteria) this;
        }

        public Criteria andRefundBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_balance between", value1, value2, "refundBalance");
            return (Criteria) this;
        }

        public Criteria andRefundBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_balance not between", value1, value2, "refundBalance");
            return (Criteria) this;
        }

        public Criteria andTalkIdIsNull() {
            addCriterion("talk_id is null");
            return (Criteria) this;
        }

        public Criteria andTalkIdIsNotNull() {
            addCriterion("talk_id is not null");
            return (Criteria) this;
        }

        public Criteria andTalkIdEqualTo(Integer value) {
            addCriterion("talk_id =", value, "talkId");
            return (Criteria) this;
        }

        public Criteria andTalkIdNotEqualTo(Integer value) {
            addCriterion("talk_id <>", value, "talkId");
            return (Criteria) this;
        }

        public Criteria andTalkIdGreaterThan(Integer value) {
            addCriterion("talk_id >", value, "talkId");
            return (Criteria) this;
        }

        public Criteria andTalkIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("talk_id >=", value, "talkId");
            return (Criteria) this;
        }

        public Criteria andTalkIdLessThan(Integer value) {
            addCriterion("talk_id <", value, "talkId");
            return (Criteria) this;
        }

        public Criteria andTalkIdLessThanOrEqualTo(Integer value) {
            addCriterion("talk_id <=", value, "talkId");
            return (Criteria) this;
        }

        public Criteria andTalkIdIn(List<Integer> values) {
            addCriterion("talk_id in", values, "talkId");
            return (Criteria) this;
        }

        public Criteria andTalkIdNotIn(List<Integer> values) {
            addCriterion("talk_id not in", values, "talkId");
            return (Criteria) this;
        }

        public Criteria andTalkIdBetween(Integer value1, Integer value2) {
            addCriterion("talk_id between", value1, value2, "talkId");
            return (Criteria) this;
        }

        public Criteria andTalkIdNotBetween(Integer value1, Integer value2) {
            addCriterion("talk_id not between", value1, value2, "talkId");
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