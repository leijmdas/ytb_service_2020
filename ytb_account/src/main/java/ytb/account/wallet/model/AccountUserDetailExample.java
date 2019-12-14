package ytb.account.wallet.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountUserDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public AccountUserDetailExample() {
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

        public Criteria andDetailIdIsNull() {
            addCriterion("detail_id is null");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNotNull() {
            addCriterion("detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andDetailIdEqualTo(Integer value) {
            addCriterion("detail_id =", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotEqualTo(Integer value) {
            addCriterion("detail_id <>", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThan(Integer value) {
            addCriterion("detail_id >", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("detail_id >=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThan(Integer value) {
            addCriterion("detail_id <", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThanOrEqualTo(Integer value) {
            addCriterion("detail_id <=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdIn(List<Integer> values) {
            addCriterion("detail_id in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotIn(List<Integer> values) {
            addCriterion("detail_id not in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdBetween(Integer value1, Integer value2) {
            addCriterion("detail_id between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotBetween(Integer value1, Integer value2) {
            addCriterion("detail_id not between", value1, value2, "detailId");
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

        public Criteria andTradeItemIsNull() {
            addCriterion("trade_item is null");
            return (Criteria) this;
        }

        public Criteria andTradeItemIsNotNull() {
            addCriterion("trade_item is not null");
            return (Criteria) this;
        }

        public Criteria andTradeItemEqualTo(String value) {
            addCriterion("trade_item =", value, "tradeItem");
            return (Criteria) this;
        }

        public Criteria andTradeItemNotEqualTo(String value) {
            addCriterion("trade_item <>", value, "tradeItem");
            return (Criteria) this;
        }

        public Criteria andTradeItemGreaterThan(String value) {
            addCriterion("trade_item >", value, "tradeItem");
            return (Criteria) this;
        }

        public Criteria andTradeItemGreaterThanOrEqualTo(String value) {
            addCriterion("trade_item >=", value, "tradeItem");
            return (Criteria) this;
        }

        public Criteria andTradeItemLessThan(String value) {
            addCriterion("trade_item <", value, "tradeItem");
            return (Criteria) this;
        }

        public Criteria andTradeItemLessThanOrEqualTo(String value) {
            addCriterion("trade_item <=", value, "tradeItem");
            return (Criteria) this;
        }

        public Criteria andTradeItemLike(String value) {
            addCriterion("trade_item like", value, "tradeItem");
            return (Criteria) this;
        }

        public Criteria andTradeItemNotLike(String value) {
            addCriterion("trade_item not like", value, "tradeItem");
            return (Criteria) this;
        }

        public Criteria andTradeItemIn(List<String> values) {
            addCriterion("trade_item in", values, "tradeItem");
            return (Criteria) this;
        }

        public Criteria andTradeItemNotIn(List<String> values) {
            addCriterion("trade_item not in", values, "tradeItem");
            return (Criteria) this;
        }

        public Criteria andTradeItemBetween(String value1, String value2) {
            addCriterion("trade_item between", value1, value2, "tradeItem");
            return (Criteria) this;
        }

        public Criteria andTradeItemNotBetween(String value1, String value2) {
            addCriterion("trade_item not between", value1, value2, "tradeItem");
            return (Criteria) this;
        }

        public Criteria andOriginalBalanceIsNull() {
            addCriterion("original_balance is null");
            return (Criteria) this;
        }

        public Criteria andOriginalBalanceIsNotNull() {
            addCriterion("original_balance is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalBalanceEqualTo(BigDecimal value) {
            addCriterion("original_balance =", value, "originalBalance");
            return (Criteria) this;
        }

        public Criteria andOriginalBalanceNotEqualTo(BigDecimal value) {
            addCriterion("original_balance <>", value, "originalBalance");
            return (Criteria) this;
        }

        public Criteria andOriginalBalanceGreaterThan(BigDecimal value) {
            addCriterion("original_balance >", value, "originalBalance");
            return (Criteria) this;
        }

        public Criteria andOriginalBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("original_balance >=", value, "originalBalance");
            return (Criteria) this;
        }

        public Criteria andOriginalBalanceLessThan(BigDecimal value) {
            addCriterion("original_balance <", value, "originalBalance");
            return (Criteria) this;
        }

        public Criteria andOriginalBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("original_balance <=", value, "originalBalance");
            return (Criteria) this;
        }

        public Criteria andOriginalBalanceIn(List<BigDecimal> values) {
            addCriterion("original_balance in", values, "originalBalance");
            return (Criteria) this;
        }

        public Criteria andOriginalBalanceNotIn(List<BigDecimal> values) {
            addCriterion("original_balance not in", values, "originalBalance");
            return (Criteria) this;
        }

        public Criteria andOriginalBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_balance between", value1, value2, "originalBalance");
            return (Criteria) this;
        }

        public Criteria andOriginalBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_balance not between", value1, value2, "originalBalance");
            return (Criteria) this;
        }

        public Criteria andTradeBalanceIsNull() {
            addCriterion("trade_balance is null");
            return (Criteria) this;
        }

        public Criteria andTradeBalanceIsNotNull() {
            addCriterion("trade_balance is not null");
            return (Criteria) this;
        }

        public Criteria andTradeBalanceEqualTo(BigDecimal value) {
            addCriterion("trade_balance =", value, "tradeBalance");
            return (Criteria) this;
        }

        public Criteria andTradeBalanceNotEqualTo(BigDecimal value) {
            addCriterion("trade_balance <>", value, "tradeBalance");
            return (Criteria) this;
        }

        public Criteria andTradeBalanceGreaterThan(BigDecimal value) {
            addCriterion("trade_balance >", value, "tradeBalance");
            return (Criteria) this;
        }

        public Criteria andTradeBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_balance >=", value, "tradeBalance");
            return (Criteria) this;
        }

        public Criteria andTradeBalanceLessThan(BigDecimal value) {
            addCriterion("trade_balance <", value, "tradeBalance");
            return (Criteria) this;
        }

        public Criteria andTradeBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("trade_balance <=", value, "tradeBalance");
            return (Criteria) this;
        }

        public Criteria andTradeBalanceIn(List<BigDecimal> values) {
            addCriterion("trade_balance in", values, "tradeBalance");
            return (Criteria) this;
        }

        public Criteria andTradeBalanceNotIn(List<BigDecimal> values) {
            addCriterion("trade_balance not in", values, "tradeBalance");
            return (Criteria) this;
        }

        public Criteria andTradeBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_balance between", value1, value2, "tradeBalance");
            return (Criteria) this;
        }

        public Criteria andTradeBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("trade_balance not between", value1, value2, "tradeBalance");
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

        public Criteria andInTimeIsNull() {
            addCriterion("in_time is null");
            return (Criteria) this;
        }

        public Criteria andInTimeIsNotNull() {
            addCriterion("in_time is not null");
            return (Criteria) this;
        }

        public Criteria andInTimeEqualTo(Date value) {
            addCriterion("in_time =", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeNotEqualTo(Date value) {
            addCriterion("in_time <>", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeGreaterThan(Date value) {
            addCriterion("in_time >", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("in_time >=", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeLessThan(Date value) {
            addCriterion("in_time <", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeLessThanOrEqualTo(Date value) {
            addCriterion("in_time <=", value, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeIn(List<Date> values) {
            addCriterion("in_time in", values, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeNotIn(List<Date> values) {
            addCriterion("in_time not in", values, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeBetween(Date value1, Date value2) {
            addCriterion("in_time between", value1, value2, "inTime");
            return (Criteria) this;
        }

        public Criteria andInTimeNotBetween(Date value1, Date value2) {
            addCriterion("in_time not between", value1, value2, "inTime");
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

        public Criteria andBalanceTypeIsNull() {
            addCriterion("balance_type is null");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIsNotNull() {
            addCriterion("balance_type is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeEqualTo(Integer value) {
            addCriterion("balance_type =", value, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNotEqualTo(Integer value) {
            addCriterion("balance_type <>", value, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeGreaterThan(Integer value) {
            addCriterion("balance_type >", value, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("balance_type >=", value, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeLessThan(Integer value) {
            addCriterion("balance_type <", value, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("balance_type <=", value, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIn(List<Integer> values) {
            addCriterion("balance_type in", values, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNotIn(List<Integer> values) {
            addCriterion("balance_type not in", values, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeBetween(Integer value1, Integer value2) {
            addCriterion("balance_type between", value1, value2, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("balance_type not between", value1, value2, "balanceType");
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