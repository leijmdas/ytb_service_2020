package ytb.manager.metadata.model;

import java.util.ArrayList;
import java.util.List;

public class MetadataDictExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public MetadataDictExample() {
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

        public Criteria andMetadataIdIsNull() {
            addCriterion("metadata_id is null");
            return (Criteria) this;
        }

        public Criteria andMetadataIdIsNotNull() {
            addCriterion("metadata_id is not null");
            return (Criteria) this;
        }

        public Criteria andMetadataIdEqualTo(Integer value) {
            addCriterion("metadata_id =", value, "metadataId");
            return (Criteria) this;
        }

        public Criteria andMetadataIdNotEqualTo(Integer value) {
            addCriterion("metadata_id <>", value, "metadataId");
            return (Criteria) this;
        }

        public Criteria andMetadataIdGreaterThan(Integer value) {
            addCriterion("metadata_id >", value, "metadataId");
            return (Criteria) this;
        }

        public Criteria andMetadataIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("metadata_id >=", value, "metadataId");
            return (Criteria) this;
        }

        public Criteria andMetadataIdLessThan(Integer value) {
            addCriterion("metadata_id <", value, "metadataId");
            return (Criteria) this;
        }

        public Criteria andMetadataIdLessThanOrEqualTo(Integer value) {
            addCriterion("metadata_id <=", value, "metadataId");
            return (Criteria) this;
        }

        public Criteria andMetadataIdIn(List<Integer> values) {
            addCriterion("metadata_id in", values, "metadataId");
            return (Criteria) this;
        }

        public Criteria andMetadataIdNotIn(List<Integer> values) {
            addCriterion("metadata_id not in", values, "metadataId");
            return (Criteria) this;
        }

        public Criteria andMetadataIdBetween(Integer value1, Integer value2) {
            addCriterion("metadata_id between", value1, value2, "metadataId");
            return (Criteria) this;
        }

        public Criteria andMetadataIdNotBetween(Integer value1, Integer value2) {
            addCriterion("metadata_id not between", value1, value2, "metadataId");
            return (Criteria) this;
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

        public Criteria andMetadataNameIsNull() {
            addCriterion("metadata_name is null");
            return (Criteria) this;
        }

        public Criteria andMetadataNameIsNotNull() {
            addCriterion("metadata_name is not null");
            return (Criteria) this;
        }

        public Criteria andMetadataNameEqualTo(String value) {
            addCriterion("metadata_name =", value, "metadataName");
            return (Criteria) this;
        }

        public Criteria andMetadataNameNotEqualTo(String value) {
            addCriterion("metadata_name <>", value, "metadataName");
            return (Criteria) this;
        }

        public Criteria andMetadataNameGreaterThan(String value) {
            addCriterion("metadata_name >", value, "metadataName");
            return (Criteria) this;
        }

        public Criteria andMetadataNameGreaterThanOrEqualTo(String value) {
            addCriterion("metadata_name >=", value, "metadataName");
            return (Criteria) this;
        }

        public Criteria andMetadataNameLessThan(String value) {
            addCriterion("metadata_name <", value, "metadataName");
            return (Criteria) this;
        }

        public Criteria andMetadataNameLessThanOrEqualTo(String value) {
            addCriterion("metadata_name <=", value, "metadataName");
            return (Criteria) this;
        }

        public Criteria andMetadataNameLike(String value) {
            addCriterion("metadata_name like", value, "metadataName");
            return (Criteria) this;
        }

        public Criteria andMetadataNameNotLike(String value) {
            addCriterion("metadata_name not like", value, "metadataName");
            return (Criteria) this;
        }

        public Criteria andMetadataNameIn(List<String> values) {
            addCriterion("metadata_name in", values, "metadataName");
            return (Criteria) this;
        }

        public Criteria andMetadataNameNotIn(List<String> values) {
            addCriterion("metadata_name not in", values, "metadataName");
            return (Criteria) this;
        }

        public Criteria andMetadataNameBetween(String value1, String value2) {
            addCriterion("metadata_name between", value1, value2, "metadataName");
            return (Criteria) this;
        }

        public Criteria andMetadataNameNotBetween(String value1, String value2) {
            addCriterion("metadata_name not between", value1, value2, "metadataName");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasIsNull() {
            addCriterion("metadata_alias is null");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasIsNotNull() {
            addCriterion("metadata_alias is not null");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasEqualTo(String value) {
            addCriterion("metadata_alias =", value, "metadataAlias");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasNotEqualTo(String value) {
            addCriterion("metadata_alias <>", value, "metadataAlias");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasGreaterThan(String value) {
            addCriterion("metadata_alias >", value, "metadataAlias");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasGreaterThanOrEqualTo(String value) {
            addCriterion("metadata_alias >=", value, "metadataAlias");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasLessThan(String value) {
            addCriterion("metadata_alias <", value, "metadataAlias");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasLessThanOrEqualTo(String value) {
            addCriterion("metadata_alias <=", value, "metadataAlias");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasLike(String value) {
            addCriterion("metadata_alias like", value, "metadataAlias");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasNotLike(String value) {
            addCriterion("metadata_alias not like", value, "metadataAlias");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasIn(List<String> values) {
            addCriterion("metadata_alias in", values, "metadataAlias");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasNotIn(List<String> values) {
            addCriterion("metadata_alias not in", values, "metadataAlias");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasBetween(String value1, String value2) {
            addCriterion("metadata_alias between", value1, value2, "metadataAlias");
            return (Criteria) this;
        }

        public Criteria andMetadataAliasNotBetween(String value1, String value2) {
            addCriterion("metadata_alias not between", value1, value2, "metadataAlias");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoIsNull() {
            addCriterion("metadata_memo is null");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoIsNotNull() {
            addCriterion("metadata_memo is not null");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoEqualTo(String value) {
            addCriterion("metadata_memo =", value, "metadataMemo");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoNotEqualTo(String value) {
            addCriterion("metadata_memo <>", value, "metadataMemo");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoGreaterThan(String value) {
            addCriterion("metadata_memo >", value, "metadataMemo");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoGreaterThanOrEqualTo(String value) {
            addCriterion("metadata_memo >=", value, "metadataMemo");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoLessThan(String value) {
            addCriterion("metadata_memo <", value, "metadataMemo");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoLessThanOrEqualTo(String value) {
            addCriterion("metadata_memo <=", value, "metadataMemo");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoLike(String value) {
            addCriterion("metadata_memo like", value, "metadataMemo");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoNotLike(String value) {
            addCriterion("metadata_memo not like", value, "metadataMemo");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoIn(List<String> values) {
            addCriterion("metadata_memo in", values, "metadataMemo");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoNotIn(List<String> values) {
            addCriterion("metadata_memo not in", values, "metadataMemo");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoBetween(String value1, String value2) {
            addCriterion("metadata_memo between", value1, value2, "metadataMemo");
            return (Criteria) this;
        }

        public Criteria andMetadataMemoNotBetween(String value1, String value2) {
            addCriterion("metadata_memo not between", value1, value2, "metadataMemo");
            return (Criteria) this;
        }

        public Criteria andMetadataParentidIsNull() {
            addCriterion("metadata_parentid is null");
            return (Criteria) this;
        }

        public Criteria andMetadataParentidIsNotNull() {
            addCriterion("metadata_parentid is not null");
            return (Criteria) this;
        }

        public Criteria andMetadataParentidEqualTo(Integer value) {
            addCriterion("metadata_parentid =", value, "metadataParentid");
            return (Criteria) this;
        }

        public Criteria andMetadataParentidNotEqualTo(Integer value) {
            addCriterion("metadata_parentid <>", value, "metadataParentid");
            return (Criteria) this;
        }

        public Criteria andMetadataParentidGreaterThan(Integer value) {
            addCriterion("metadata_parentid >", value, "metadataParentid");
            return (Criteria) this;
        }

        public Criteria andMetadataParentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("metadata_parentid >=", value, "metadataParentid");
            return (Criteria) this;
        }

        public Criteria andMetadataParentidLessThan(Integer value) {
            addCriterion("metadata_parentid <", value, "metadataParentid");
            return (Criteria) this;
        }

        public Criteria andMetadataParentidLessThanOrEqualTo(Integer value) {
            addCriterion("metadata_parentid <=", value, "metadataParentid");
            return (Criteria) this;
        }

        public Criteria andMetadataParentidIn(List<Integer> values) {
            addCriterion("metadata_parentid in", values, "metadataParentid");
            return (Criteria) this;
        }

        public Criteria andMetadataParentidNotIn(List<Integer> values) {
            addCriterion("metadata_parentid not in", values, "metadataParentid");
            return (Criteria) this;
        }

        public Criteria andMetadataParentidBetween(Integer value1, Integer value2) {
            addCriterion("metadata_parentid between", value1, value2, "metadataParentid");
            return (Criteria) this;
        }

        public Criteria andMetadataParentidNotBetween(Integer value1, Integer value2) {
            addCriterion("metadata_parentid not between", value1, value2, "metadataParentid");
            return (Criteria) this;
        }

        public Criteria andMetadataTypeIsNull() {
            addCriterion("metadata_type is null");
            return (Criteria) this;
        }

        public Criteria andMetadataTypeIsNotNull() {
            addCriterion("metadata_type is not null");
            return (Criteria) this;
        }

        public Criteria andMetadataTypeEqualTo(Integer value) {
            addCriterion("metadata_type =", value, "metadataType");
            return (Criteria) this;
        }

        public Criteria andMetadataTypeNotEqualTo(Integer value) {
            addCriterion("metadata_type <>", value, "metadataType");
            return (Criteria) this;
        }

        public Criteria andMetadataTypeGreaterThan(Integer value) {
            addCriterion("metadata_type >", value, "metadataType");
            return (Criteria) this;
        }

        public Criteria andMetadataTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("metadata_type >=", value, "metadataType");
            return (Criteria) this;
        }

        public Criteria andMetadataTypeLessThan(Integer value) {
            addCriterion("metadata_type <", value, "metadataType");
            return (Criteria) this;
        }

        public Criteria andMetadataTypeLessThanOrEqualTo(Integer value) {
            addCriterion("metadata_type <=", value, "metadataType");
            return (Criteria) this;
        }

        public Criteria andMetadataTypeIn(List<Integer> values) {
            addCriterion("metadata_type in", values, "metadataType");
            return (Criteria) this;
        }

        public Criteria andMetadataTypeNotIn(List<Integer> values) {
            addCriterion("metadata_type not in", values, "metadataType");
            return (Criteria) this;
        }

        public Criteria andMetadataTypeBetween(Integer value1, Integer value2) {
            addCriterion("metadata_type between", value1, value2, "metadataType");
            return (Criteria) this;
        }

        public Criteria andMetadataTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("metadata_type not between", value1, value2, "metadataType");
            return (Criteria) this;
        }

        public Criteria andIsCachedIsNull() {
            addCriterion("is_cached is null");
            return (Criteria) this;
        }

        public Criteria andIsCachedIsNotNull() {
            addCriterion("is_cached is not null");
            return (Criteria) this;
        }

        public Criteria andIsCachedEqualTo(Boolean value) {
            addCriterion("is_cached =", value, "isCached");
            return (Criteria) this;
        }

        public Criteria andIsCachedNotEqualTo(Boolean value) {
            addCriterion("is_cached <>", value, "isCached");
            return (Criteria) this;
        }

        public Criteria andIsCachedGreaterThan(Boolean value) {
            addCriterion("is_cached >", value, "isCached");
            return (Criteria) this;
        }

        public Criteria andIsCachedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_cached >=", value, "isCached");
            return (Criteria) this;
        }

        public Criteria andIsCachedLessThan(Boolean value) {
            addCriterion("is_cached <", value, "isCached");
            return (Criteria) this;
        }

        public Criteria andIsCachedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_cached <=", value, "isCached");
            return (Criteria) this;
        }

        public Criteria andIsCachedIn(List<Boolean> values) {
            addCriterion("is_cached in", values, "isCached");
            return (Criteria) this;
        }

        public Criteria andIsCachedNotIn(List<Boolean> values) {
            addCriterion("is_cached not in", values, "isCached");
            return (Criteria) this;
        }

        public Criteria andIsCachedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_cached between", value1, value2, "isCached");
            return (Criteria) this;
        }

        public Criteria andIsCachedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_cached not between", value1, value2, "isCached");
            return (Criteria) this;
        }

        public Criteria andMetadataDbIsNull() {
            addCriterion("metadata_db is null");
            return (Criteria) this;
        }

        public Criteria andMetadataDbIsNotNull() {
            addCriterion("metadata_db is not null");
            return (Criteria) this;
        }

        public Criteria andMetadataDbEqualTo(String value) {
            addCriterion("metadata_db =", value, "metadataDb");
            return (Criteria) this;
        }

        public Criteria andMetadataDbNotEqualTo(String value) {
            addCriterion("metadata_db <>", value, "metadataDb");
            return (Criteria) this;
        }

        public Criteria andMetadataDbGreaterThan(String value) {
            addCriterion("metadata_db >", value, "metadataDb");
            return (Criteria) this;
        }

        public Criteria andMetadataDbGreaterThanOrEqualTo(String value) {
            addCriterion("metadata_db >=", value, "metadataDb");
            return (Criteria) this;
        }

        public Criteria andMetadataDbLessThan(String value) {
            addCriterion("metadata_db <", value, "metadataDb");
            return (Criteria) this;
        }

        public Criteria andMetadataDbLessThanOrEqualTo(String value) {
            addCriterion("metadata_db <=", value, "metadataDb");
            return (Criteria) this;
        }

        public Criteria andMetadataDbLike(String value) {
            addCriterion("metadata_db like", value, "metadataDb");
            return (Criteria) this;
        }

        public Criteria andMetadataDbNotLike(String value) {
            addCriterion("metadata_db not like", value, "metadataDb");
            return (Criteria) this;
        }

        public Criteria andMetadataDbIn(List<String> values) {
            addCriterion("metadata_db in", values, "metadataDb");
            return (Criteria) this;
        }

        public Criteria andMetadataDbNotIn(List<String> values) {
            addCriterion("metadata_db not in", values, "metadataDb");
            return (Criteria) this;
        }

        public Criteria andMetadataDbBetween(String value1, String value2) {
            addCriterion("metadata_db between", value1, value2, "metadataDb");
            return (Criteria) this;
        }

        public Criteria andMetadataDbNotBetween(String value1, String value2) {
            addCriterion("metadata_db not between", value1, value2, "metadataDb");
            return (Criteria) this;
        }

        public Criteria andMetadataAutocreateIsNull() {
            addCriterion("metadata_autocreate is null");
            return (Criteria) this;
        }

        public Criteria andMetadataAutocreateIsNotNull() {
            addCriterion("metadata_autocreate is not null");
            return (Criteria) this;
        }

        public Criteria andMetadataAutocreateEqualTo(Boolean value) {
            addCriterion("metadata_autocreate =", value, "metadataAutocreate");
            return (Criteria) this;
        }

        public Criteria andMetadataAutocreateNotEqualTo(Boolean value) {
            addCriterion("metadata_autocreate <>", value, "metadataAutocreate");
            return (Criteria) this;
        }

        public Criteria andMetadataAutocreateGreaterThan(Boolean value) {
            addCriterion("metadata_autocreate >", value, "metadataAutocreate");
            return (Criteria) this;
        }

        public Criteria andMetadataAutocreateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("metadata_autocreate >=", value, "metadataAutocreate");
            return (Criteria) this;
        }

        public Criteria andMetadataAutocreateLessThan(Boolean value) {
            addCriterion("metadata_autocreate <", value, "metadataAutocreate");
            return (Criteria) this;
        }

        public Criteria andMetadataAutocreateLessThanOrEqualTo(Boolean value) {
            addCriterion("metadata_autocreate <=", value, "metadataAutocreate");
            return (Criteria) this;
        }

        public Criteria andMetadataAutocreateIn(List<Boolean> values) {
            addCriterion("metadata_autocreate in", values, "metadataAutocreate");
            return (Criteria) this;
        }

        public Criteria andMetadataAutocreateNotIn(List<Boolean> values) {
            addCriterion("metadata_autocreate not in", values, "metadataAutocreate");
            return (Criteria) this;
        }

        public Criteria andMetadataAutocreateBetween(Boolean value1, Boolean value2) {
            addCriterion("metadata_autocreate between", value1, value2, "metadataAutocreate");
            return (Criteria) this;
        }

        public Criteria andMetadataAutocreateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("metadata_autocreate not between", value1, value2, "metadataAutocreate");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtIsNull() {
            addCriterion("metadata_stmt is null");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtIsNotNull() {
            addCriterion("metadata_stmt is not null");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtEqualTo(String value) {
            addCriterion("metadata_stmt =", value, "metadataStmt");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtNotEqualTo(String value) {
            addCriterion("metadata_stmt <>", value, "metadataStmt");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtGreaterThan(String value) {
            addCriterion("metadata_stmt >", value, "metadataStmt");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtGreaterThanOrEqualTo(String value) {
            addCriterion("metadata_stmt >=", value, "metadataStmt");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtLessThan(String value) {
            addCriterion("metadata_stmt <", value, "metadataStmt");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtLessThanOrEqualTo(String value) {
            addCriterion("metadata_stmt <=", value, "metadataStmt");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtLike(String value) {
            addCriterion("metadata_stmt like", value, "metadataStmt");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtNotLike(String value) {
            addCriterion("metadata_stmt not like", value, "metadataStmt");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtIn(List<String> values) {
            addCriterion("metadata_stmt in", values, "metadataStmt");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtNotIn(List<String> values) {
            addCriterion("metadata_stmt not in", values, "metadataStmt");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtBetween(String value1, String value2) {
            addCriterion("metadata_stmt between", value1, value2, "metadataStmt");
            return (Criteria) this;
        }

        public Criteria andMetadataStmtNotBetween(String value1, String value2) {
            addCriterion("metadata_stmt not between", value1, value2, "metadataStmt");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexIsNull() {
            addCriterion("metadata_index is null");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexIsNotNull() {
            addCriterion("metadata_index is not null");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexEqualTo(String value) {
            addCriterion("metadata_index =", value, "metadataIndex");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexNotEqualTo(String value) {
            addCriterion("metadata_index <>", value, "metadataIndex");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexGreaterThan(String value) {
            addCriterion("metadata_index >", value, "metadataIndex");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexGreaterThanOrEqualTo(String value) {
            addCriterion("metadata_index >=", value, "metadataIndex");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexLessThan(String value) {
            addCriterion("metadata_index <", value, "metadataIndex");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexLessThanOrEqualTo(String value) {
            addCriterion("metadata_index <=", value, "metadataIndex");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexLike(String value) {
            addCriterion("metadata_index like", value, "metadataIndex");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexNotLike(String value) {
            addCriterion("metadata_index not like", value, "metadataIndex");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexIn(List<String> values) {
            addCriterion("metadata_index in", values, "metadataIndex");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexNotIn(List<String> values) {
            addCriterion("metadata_index not in", values, "metadataIndex");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexBetween(String value1, String value2) {
            addCriterion("metadata_index between", value1, value2, "metadataIndex");
            return (Criteria) this;
        }

        public Criteria andMetadataIndexNotBetween(String value1, String value2) {
            addCriterion("metadata_index not between", value1, value2, "metadataIndex");
            return (Criteria) this;
        }

        public Criteria andMetadataOrderIsNull() {
            addCriterion("metadata_order is null");
            return (Criteria) this;
        }

        public Criteria andMetadataOrderIsNotNull() {
            addCriterion("metadata_order is not null");
            return (Criteria) this;
        }

        public Criteria andMetadataOrderEqualTo(Integer value) {
            addCriterion("metadata_order =", value, "metadataOrder");
            return (Criteria) this;
        }

        public Criteria andMetadataOrderNotEqualTo(Integer value) {
            addCriterion("metadata_order <>", value, "metadataOrder");
            return (Criteria) this;
        }

        public Criteria andMetadataOrderGreaterThan(Integer value) {
            addCriterion("metadata_order >", value, "metadataOrder");
            return (Criteria) this;
        }

        public Criteria andMetadataOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("metadata_order >=", value, "metadataOrder");
            return (Criteria) this;
        }

        public Criteria andMetadataOrderLessThan(Integer value) {
            addCriterion("metadata_order <", value, "metadataOrder");
            return (Criteria) this;
        }

        public Criteria andMetadataOrderLessThanOrEqualTo(Integer value) {
            addCriterion("metadata_order <=", value, "metadataOrder");
            return (Criteria) this;
        }

        public Criteria andMetadataOrderIn(List<Integer> values) {
            addCriterion("metadata_order in", values, "metadataOrder");
            return (Criteria) this;
        }

        public Criteria andMetadataOrderNotIn(List<Integer> values) {
            addCriterion("metadata_order not in", values, "metadataOrder");
            return (Criteria) this;
        }

        public Criteria andMetadataOrderBetween(Integer value1, Integer value2) {
            addCriterion("metadata_order between", value1, value2, "metadataOrder");
            return (Criteria) this;
        }

        public Criteria andMetadataOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("metadata_order not between", value1, value2, "metadataOrder");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkIsNull() {
            addCriterion("metadata_remark is null");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkIsNotNull() {
            addCriterion("metadata_remark is not null");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkEqualTo(String value) {
            addCriterion("metadata_remark =", value, "metadataRemark");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkNotEqualTo(String value) {
            addCriterion("metadata_remark <>", value, "metadataRemark");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkGreaterThan(String value) {
            addCriterion("metadata_remark >", value, "metadataRemark");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("metadata_remark >=", value, "metadataRemark");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkLessThan(String value) {
            addCriterion("metadata_remark <", value, "metadataRemark");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkLessThanOrEqualTo(String value) {
            addCriterion("metadata_remark <=", value, "metadataRemark");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkLike(String value) {
            addCriterion("metadata_remark like", value, "metadataRemark");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkNotLike(String value) {
            addCriterion("metadata_remark not like", value, "metadataRemark");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkIn(List<String> values) {
            addCriterion("metadata_remark in", values, "metadataRemark");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkNotIn(List<String> values) {
            addCriterion("metadata_remark not in", values, "metadataRemark");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkBetween(String value1, String value2) {
            addCriterion("metadata_remark between", value1, value2, "metadataRemark");
            return (Criteria) this;
        }

        public Criteria andMetadataRemarkNotBetween(String value1, String value2) {
            addCriterion("metadata_remark not between", value1, value2, "metadataRemark");
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