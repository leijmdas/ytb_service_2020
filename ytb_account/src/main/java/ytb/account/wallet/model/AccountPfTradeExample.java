package ytb.account.wallet.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountPfTradeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public AccountPfTradeExample() {
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

        public Criteria andToPfInnerIdIsNull() {
            addCriterion("to_pf_inner_id is null");
            return (Criteria) this;
        }

        public Criteria andToPfInnerIdIsNotNull() {
            addCriterion("to_pf_inner_id is not null");
            return (Criteria) this;
        }

        public Criteria andToPfInnerIdEqualTo(Integer value) {
            addCriterion("to_pf_inner_id =", value, "toPfInnerId");
            return (Criteria) this;
        }

        public Criteria andToPfInnerIdNotEqualTo(Integer value) {
            addCriterion("to_pf_inner_id <>", value, "toPfInnerId");
            return (Criteria) this;
        }

        public Criteria andToPfInnerIdGreaterThan(Integer value) {
            addCriterion("to_pf_inner_id >", value, "toPfInnerId");
            return (Criteria) this;
        }

        public Criteria andToPfInnerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("to_pf_inner_id >=", value, "toPfInnerId");
            return (Criteria) this;
        }

        public Criteria andToPfInnerIdLessThan(Integer value) {
            addCriterion("to_pf_inner_id <", value, "toPfInnerId");
            return (Criteria) this;
        }

        public Criteria andToPfInnerIdLessThanOrEqualTo(Integer value) {
            addCriterion("to_pf_inner_id <=", value, "toPfInnerId");
            return (Criteria) this;
        }

        public Criteria andToPfInnerIdIn(List<Integer> values) {
            addCriterion("to_pf_inner_id in", values, "toPfInnerId");
            return (Criteria) this;
        }

        public Criteria andToPfInnerIdNotIn(List<Integer> values) {
            addCriterion("to_pf_inner_id not in", values, "toPfInnerId");
            return (Criteria) this;
        }

        public Criteria andToPfInnerIdBetween(Integer value1, Integer value2) {
            addCriterion("to_pf_inner_id between", value1, value2, "toPfInnerId");
            return (Criteria) this;
        }

        public Criteria andToPfInnerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("to_pf_inner_id not between", value1, value2, "toPfInnerId");
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

        public Criteria andTradeTypeIsNull() {
            addCriterion("trade_type is null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNotNull() {
            addCriterion("trade_type is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeEqualTo(Integer value) {
            addCriterion("trade_type =", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotEqualTo(Integer value) {
            addCriterion("trade_type <>", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThan(Integer value) {
            addCriterion("trade_type >", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade_type >=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThan(Integer value) {
            addCriterion("trade_type <", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("trade_type <=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIn(List<Integer> values) {
            addCriterion("trade_type in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotIn(List<Integer> values) {
            addCriterion("trade_type not in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeBetween(Integer value1, Integer value2) {
            addCriterion("trade_type between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotBetween(Integer value1, Integer value2) {
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andProgressIdIsNull() {
            addCriterion("progress_id is null");
            return (Criteria) this;
        }

        public Criteria andProgressIdIsNotNull() {
            addCriterion("progress_id is not null");
            return (Criteria) this;
        }

        public Criteria andProgressIdEqualTo(Integer value) {
            addCriterion("progress_id =", value, "progressId");
            return (Criteria) this;
        }

        public Criteria andProgressIdNotEqualTo(Integer value) {
            addCriterion("progress_id <>", value, "progressId");
            return (Criteria) this;
        }

        public Criteria andProgressIdGreaterThan(Integer value) {
            addCriterion("progress_id >", value, "progressId");
            return (Criteria) this;
        }

        public Criteria andProgressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("progress_id >=", value, "progressId");
            return (Criteria) this;
        }

        public Criteria andProgressIdLessThan(Integer value) {
            addCriterion("progress_id <", value, "progressId");
            return (Criteria) this;
        }

        public Criteria andProgressIdLessThanOrEqualTo(Integer value) {
            addCriterion("progress_id <=", value, "progressId");
            return (Criteria) this;
        }

        public Criteria andProgressIdIn(List<Integer> values) {
            addCriterion("progress_id in", values, "progressId");
            return (Criteria) this;
        }

        public Criteria andProgressIdNotIn(List<Integer> values) {
            addCriterion("progress_id not in", values, "progressId");
            return (Criteria) this;
        }

        public Criteria andProgressIdBetween(Integer value1, Integer value2) {
            addCriterion("progress_id between", value1, value2, "progressId");
            return (Criteria) this;
        }

        public Criteria andProgressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("progress_id not between", value1, value2, "progressId");
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

        public Criteria andPaymentBalanceIsNull() {
            addCriterion("payment_balance is null");
            return (Criteria) this;
        }

        public Criteria andPaymentBalanceIsNotNull() {
            addCriterion("payment_balance is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentBalanceEqualTo(BigDecimal value) {
            addCriterion("payment_balance =", value, "paymentBalance");
            return (Criteria) this;
        }

        public Criteria andPaymentBalanceNotEqualTo(BigDecimal value) {
            addCriterion("payment_balance <>", value, "paymentBalance");
            return (Criteria) this;
        }

        public Criteria andPaymentBalanceGreaterThan(BigDecimal value) {
            addCriterion("payment_balance >", value, "paymentBalance");
            return (Criteria) this;
        }

        public Criteria andPaymentBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("payment_balance >=", value, "paymentBalance");
            return (Criteria) this;
        }

        public Criteria andPaymentBalanceLessThan(BigDecimal value) {
            addCriterion("payment_balance <", value, "paymentBalance");
            return (Criteria) this;
        }

        public Criteria andPaymentBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("payment_balance <=", value, "paymentBalance");
            return (Criteria) this;
        }

        public Criteria andPaymentBalanceIn(List<BigDecimal> values) {
            addCriterion("payment_balance in", values, "paymentBalance");
            return (Criteria) this;
        }

        public Criteria andPaymentBalanceNotIn(List<BigDecimal> values) {
            addCriterion("payment_balance not in", values, "paymentBalance");
            return (Criteria) this;
        }

        public Criteria andPaymentBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payment_balance between", value1, value2, "paymentBalance");
            return (Criteria) this;
        }

        public Criteria andPaymentBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payment_balance not between", value1, value2, "paymentBalance");
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