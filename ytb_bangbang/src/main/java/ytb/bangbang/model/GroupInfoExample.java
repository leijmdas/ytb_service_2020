package ytb.bangbang.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public GroupInfoExample() {
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

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("group_Name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_Name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_Name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_Name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_Name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_Name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_Name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_Name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_Name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_Name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_Name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_Name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_Name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_Name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_Time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_Time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_Time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_Time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_Time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_Time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_Time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_Time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_Time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_Time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_Time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_Time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andGroupIconIsNull() {
            addCriterion("group_Icon is null");
            return (Criteria) this;
        }

        public Criteria andGroupIconIsNotNull() {
            addCriterion("group_Icon is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIconEqualTo(String value) {
            addCriterion("group_Icon =", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconNotEqualTo(String value) {
            addCriterion("group_Icon <>", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconGreaterThan(String value) {
            addCriterion("group_Icon >", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconGreaterThanOrEqualTo(String value) {
            addCriterion("group_Icon >=", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconLessThan(String value) {
            addCriterion("group_Icon <", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconLessThanOrEqualTo(String value) {
            addCriterion("group_Icon <=", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconLike(String value) {
            addCriterion("group_Icon like", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconNotLike(String value) {
            addCriterion("group_Icon not like", value, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconIn(List<String> values) {
            addCriterion("group_Icon in", values, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconNotIn(List<String> values) {
            addCriterion("group_Icon not in", values, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconBetween(String value1, String value2) {
            addCriterion("group_Icon between", value1, value2, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupIconNotBetween(String value1, String value2) {
            addCriterion("group_Icon not between", value1, value2, "groupIcon");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNull() {
            addCriterion("group_Type is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNotNull() {
            addCriterion("group_Type is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeEqualTo(Integer value) {
            addCriterion("group_Type =", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotEqualTo(Integer value) {
            addCriterion("group_Type <>", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThan(Integer value) {
            addCriterion("group_Type >", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_Type >=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThan(Integer value) {
            addCriterion("group_Type <", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThanOrEqualTo(Integer value) {
            addCriterion("group_Type <=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIn(List<Integer> values) {
            addCriterion("group_Type in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotIn(List<Integer> values) {
            addCriterion("group_Type not in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeBetween(Integer value1, Integer value2) {
            addCriterion("group_Type between", value1, value2, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("group_Type not between", value1, value2, "groupType");
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