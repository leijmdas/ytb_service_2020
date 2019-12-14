package ytb.manager.metadata.model;

import java.util.ArrayList;
import java.util.List;

public class SubsysDictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public SubsysDictExample() {
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

        public Criteria andSubsysIdIsNull() {
            addCriterion("subsys_id is null");
            return (Criteria) this;
        }

        public Criteria andSubsysIdIsNotNull() {
            addCriterion("subsys_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubsysIdEqualTo(Integer value) {
            addCriterion("subsys_id =", value, "subsysId");
            return (Criteria) this;
        }

        public Criteria andSubsysIdNotEqualTo(Integer value) {
            addCriterion("subsys_id <>", value, "subsysId");
            return (Criteria) this;
        }

        public Criteria andSubsysIdGreaterThan(Integer value) {
            addCriterion("subsys_id >", value, "subsysId");
            return (Criteria) this;
        }

        public Criteria andSubsysIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("subsys_id >=", value, "subsysId");
            return (Criteria) this;
        }

        public Criteria andSubsysIdLessThan(Integer value) {
            addCriterion("subsys_id <", value, "subsysId");
            return (Criteria) this;
        }

        public Criteria andSubsysIdLessThanOrEqualTo(Integer value) {
            addCriterion("subsys_id <=", value, "subsysId");
            return (Criteria) this;
        }

        public Criteria andSubsysIdIn(List<Integer> values) {
            addCriterion("subsys_id in", values, "subsysId");
            return (Criteria) this;
        }

        public Criteria andSubsysIdNotIn(List<Integer> values) {
            addCriterion("subsys_id not in", values, "subsysId");
            return (Criteria) this;
        }

        public Criteria andSubsysIdBetween(Integer value1, Integer value2) {
            addCriterion("subsys_id between", value1, value2, "subsysId");
            return (Criteria) this;
        }

        public Criteria andSubsysIdNotBetween(Integer value1, Integer value2) {
            addCriterion("subsys_id not between", value1, value2, "subsysId");
            return (Criteria) this;
        }

        public Criteria andSubsysNoIsNull() {
            addCriterion("subsys_no is null");
            return (Criteria) this;
        }

        public Criteria andSubsysNoIsNotNull() {
            addCriterion("subsys_no is not null");
            return (Criteria) this;
        }

        public Criteria andSubsysNoEqualTo(String value) {
            addCriterion("subsys_no =", value, "subsysNo");
            return (Criteria) this;
        }

        public Criteria andSubsysNoNotEqualTo(String value) {
            addCriterion("subsys_no <>", value, "subsysNo");
            return (Criteria) this;
        }

        public Criteria andSubsysNoGreaterThan(String value) {
            addCriterion("subsys_no >", value, "subsysNo");
            return (Criteria) this;
        }

        public Criteria andSubsysNoGreaterThanOrEqualTo(String value) {
            addCriterion("subsys_no >=", value, "subsysNo");
            return (Criteria) this;
        }

        public Criteria andSubsysNoLessThan(String value) {
            addCriterion("subsys_no <", value, "subsysNo");
            return (Criteria) this;
        }

        public Criteria andSubsysNoLessThanOrEqualTo(String value) {
            addCriterion("subsys_no <=", value, "subsysNo");
            return (Criteria) this;
        }

        public Criteria andSubsysNoLike(String value) {
            addCriterion("subsys_no like", value, "subsysNo");
            return (Criteria) this;
        }

        public Criteria andSubsysNoNotLike(String value) {
            addCriterion("subsys_no not like", value, "subsysNo");
            return (Criteria) this;
        }

        public Criteria andSubsysNoIn(List<String> values) {
            addCriterion("subsys_no in", values, "subsysNo");
            return (Criteria) this;
        }

        public Criteria andSubsysNoNotIn(List<String> values) {
            addCriterion("subsys_no not in", values, "subsysNo");
            return (Criteria) this;
        }

        public Criteria andSubsysNoBetween(String value1, String value2) {
            addCriterion("subsys_no between", value1, value2, "subsysNo");
            return (Criteria) this;
        }

        public Criteria andSubsysNoNotBetween(String value1, String value2) {
            addCriterion("subsys_no not between", value1, value2, "subsysNo");
            return (Criteria) this;
        }

        public Criteria andSubsysNameIsNull() {
            addCriterion("subsys_name is null");
            return (Criteria) this;
        }

        public Criteria andSubsysNameIsNotNull() {
            addCriterion("subsys_name is not null");
            return (Criteria) this;
        }

        public Criteria andSubsysNameEqualTo(String value) {
            addCriterion("subsys_name =", value, "subsysName");
            return (Criteria) this;
        }

        public Criteria andSubsysNameNotEqualTo(String value) {
            addCriterion("subsys_name <>", value, "subsysName");
            return (Criteria) this;
        }

        public Criteria andSubsysNameGreaterThan(String value) {
            addCriterion("subsys_name >", value, "subsysName");
            return (Criteria) this;
        }

        public Criteria andSubsysNameGreaterThanOrEqualTo(String value) {
            addCriterion("subsys_name >=", value, "subsysName");
            return (Criteria) this;
        }

        public Criteria andSubsysNameLessThan(String value) {
            addCriterion("subsys_name <", value, "subsysName");
            return (Criteria) this;
        }

        public Criteria andSubsysNameLessThanOrEqualTo(String value) {
            addCriterion("subsys_name <=", value, "subsysName");
            return (Criteria) this;
        }

        public Criteria andSubsysNameLike(String value) {
            addCriterion("subsys_name like", value, "subsysName");
            return (Criteria) this;
        }

        public Criteria andSubsysNameNotLike(String value) {
            addCriterion("subsys_name not like", value, "subsysName");
            return (Criteria) this;
        }

        public Criteria andSubsysNameIn(List<String> values) {
            addCriterion("subsys_name in", values, "subsysName");
            return (Criteria) this;
        }

        public Criteria andSubsysNameNotIn(List<String> values) {
            addCriterion("subsys_name not in", values, "subsysName");
            return (Criteria) this;
        }

        public Criteria andSubsysNameBetween(String value1, String value2) {
            addCriterion("subsys_name between", value1, value2, "subsysName");
            return (Criteria) this;
        }

        public Criteria andSubsysNameNotBetween(String value1, String value2) {
            addCriterion("subsys_name not between", value1, value2, "subsysName");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(Short value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Short value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Short value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Short value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Short value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Short value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Short> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Short> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Short value1, Short value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Short value1, Short value2) {
            addCriterion("port not between", value1, value2, "port");
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