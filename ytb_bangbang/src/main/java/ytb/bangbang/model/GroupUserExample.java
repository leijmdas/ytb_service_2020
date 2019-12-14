package ytb.bangbang.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public GroupUserExample() {
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

        public Criteria andGroupUserIdIsNull() {
            addCriterion("group_user_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupUserIdIsNotNull() {
            addCriterion("group_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupUserIdEqualTo(Integer value) {
            addCriterion("group_user_id =", value, "groupUserId");
            return (Criteria) this;
        }

        public Criteria andGroupUserIdNotEqualTo(Integer value) {
            addCriterion("group_user_id <>", value, "groupUserId");
            return (Criteria) this;
        }

        public Criteria andGroupUserIdGreaterThan(Integer value) {
            addCriterion("group_user_id >", value, "groupUserId");
            return (Criteria) this;
        }

        public Criteria andGroupUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_user_id >=", value, "groupUserId");
            return (Criteria) this;
        }

        public Criteria andGroupUserIdLessThan(Integer value) {
            addCriterion("group_user_id <", value, "groupUserId");
            return (Criteria) this;
        }

        public Criteria andGroupUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_user_id <=", value, "groupUserId");
            return (Criteria) this;
        }

        public Criteria andGroupUserIdIn(List<Integer> values) {
            addCriterion("group_user_id in", values, "groupUserId");
            return (Criteria) this;
        }

        public Criteria andGroupUserIdNotIn(List<Integer> values) {
            addCriterion("group_user_id not in", values, "groupUserId");
            return (Criteria) this;
        }

        public Criteria andGroupUserIdBetween(Integer value1, Integer value2) {
            addCriterion("group_user_id between", value1, value2, "groupUserId");
            return (Criteria) this;
        }

        public Criteria andGroupUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_user_id not between", value1, value2, "groupUserId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_ID is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("group_ID =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("group_ID <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("group_ID >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_ID >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("group_ID <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_ID <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("group_ID in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("group_ID not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("group_ID between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_ID not between", value1, value2, "groupId");
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

        public Criteria andGroupUserTypeIsNull() {
            addCriterion("group_User_Type is null");
            return (Criteria) this;
        }

        public Criteria andGroupUserTypeIsNotNull() {
            addCriterion("group_User_Type is not null");
            return (Criteria) this;
        }

        public Criteria andGroupUserTypeEqualTo(Integer value) {
            addCriterion("group_User_Type =", value, "groupUserType");
            return (Criteria) this;
        }

        public Criteria andGroupUserTypeNotEqualTo(Integer value) {
            addCriterion("group_User_Type <>", value, "groupUserType");
            return (Criteria) this;
        }

        public Criteria andGroupUserTypeGreaterThan(Integer value) {
            addCriterion("group_User_Type >", value, "groupUserType");
            return (Criteria) this;
        }

        public Criteria andGroupUserTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_User_Type >=", value, "groupUserType");
            return (Criteria) this;
        }

        public Criteria andGroupUserTypeLessThan(Integer value) {
            addCriterion("group_User_Type <", value, "groupUserType");
            return (Criteria) this;
        }

        public Criteria andGroupUserTypeLessThanOrEqualTo(Integer value) {
            addCriterion("group_User_Type <=", value, "groupUserType");
            return (Criteria) this;
        }

        public Criteria andGroupUserTypeIn(List<Integer> values) {
            addCriterion("group_User_Type in", values, "groupUserType");
            return (Criteria) this;
        }

        public Criteria andGroupUserTypeNotIn(List<Integer> values) {
            addCriterion("group_User_Type not in", values, "groupUserType");
            return (Criteria) this;
        }

        public Criteria andGroupUserTypeBetween(Integer value1, Integer value2) {
            addCriterion("group_User_Type between", value1, value2, "groupUserType");
            return (Criteria) this;
        }

        public Criteria andGroupUserTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("group_User_Type not between", value1, value2, "groupUserType");
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