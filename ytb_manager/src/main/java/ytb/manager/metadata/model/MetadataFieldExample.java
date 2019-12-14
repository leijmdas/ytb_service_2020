package ytb.manager.metadata.model;

import java.util.ArrayList;
import java.util.List;

public class MetadataFieldExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public MetadataFieldExample() {
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

        public Criteria andFieldIdIsNull() {
            addCriterion("field_id is null");
            return (Criteria) this;
        }

        public Criteria andFieldIdIsNotNull() {
            addCriterion("field_id is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIdEqualTo(Integer value) {
            addCriterion("field_id =", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdNotEqualTo(Integer value) {
            addCriterion("field_id <>", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdGreaterThan(Integer value) {
            addCriterion("field_id >", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("field_id >=", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdLessThan(Integer value) {
            addCriterion("field_id <", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdLessThanOrEqualTo(Integer value) {
            addCriterion("field_id <=", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdIn(List<Integer> values) {
            addCriterion("field_id in", values, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdNotIn(List<Integer> values) {
            addCriterion("field_id not in", values, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdBetween(Integer value1, Integer value2) {
            addCriterion("field_id between", value1, value2, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdNotBetween(Integer value1, Integer value2) {
            addCriterion("field_id not between", value1, value2, "fieldId");
            return (Criteria) this;
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

        public Criteria andFieldNameIsNull() {
            addCriterion("field_name is null");
            return (Criteria) this;
        }

        public Criteria andFieldNameIsNotNull() {
            addCriterion("field_name is not null");
            return (Criteria) this;
        }

        public Criteria andFieldNameEqualTo(String value) {
            addCriterion("field_name =", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotEqualTo(String value) {
            addCriterion("field_name <>", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameGreaterThan(String value) {
            addCriterion("field_name >", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameGreaterThanOrEqualTo(String value) {
            addCriterion("field_name >=", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLessThan(String value) {
            addCriterion("field_name <", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLessThanOrEqualTo(String value) {
            addCriterion("field_name <=", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLike(String value) {
            addCriterion("field_name like", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotLike(String value) {
            addCriterion("field_name not like", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameIn(List<String> values) {
            addCriterion("field_name in", values, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotIn(List<String> values) {
            addCriterion("field_name not in", values, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameBetween(String value1, String value2) {
            addCriterion("field_name between", value1, value2, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotBetween(String value1, String value2) {
            addCriterion("field_name not between", value1, value2, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldMemoIsNull() {
            addCriterion("field_memo is null");
            return (Criteria) this;
        }

        public Criteria andFieldMemoIsNotNull() {
            addCriterion("field_memo is not null");
            return (Criteria) this;
        }

        public Criteria andFieldMemoEqualTo(String value) {
            addCriterion("field_memo =", value, "fieldMemo");
            return (Criteria) this;
        }

        public Criteria andFieldMemoNotEqualTo(String value) {
            addCriterion("field_memo <>", value, "fieldMemo");
            return (Criteria) this;
        }

        public Criteria andFieldMemoGreaterThan(String value) {
            addCriterion("field_memo >", value, "fieldMemo");
            return (Criteria) this;
        }

        public Criteria andFieldMemoGreaterThanOrEqualTo(String value) {
            addCriterion("field_memo >=", value, "fieldMemo");
            return (Criteria) this;
        }

        public Criteria andFieldMemoLessThan(String value) {
            addCriterion("field_memo <", value, "fieldMemo");
            return (Criteria) this;
        }

        public Criteria andFieldMemoLessThanOrEqualTo(String value) {
            addCriterion("field_memo <=", value, "fieldMemo");
            return (Criteria) this;
        }

        public Criteria andFieldMemoLike(String value) {
            addCriterion("field_memo like", value, "fieldMemo");
            return (Criteria) this;
        }

        public Criteria andFieldMemoNotLike(String value) {
            addCriterion("field_memo not like", value, "fieldMemo");
            return (Criteria) this;
        }

        public Criteria andFieldMemoIn(List<String> values) {
            addCriterion("field_memo in", values, "fieldMemo");
            return (Criteria) this;
        }

        public Criteria andFieldMemoNotIn(List<String> values) {
            addCriterion("field_memo not in", values, "fieldMemo");
            return (Criteria) this;
        }

        public Criteria andFieldMemoBetween(String value1, String value2) {
            addCriterion("field_memo between", value1, value2, "fieldMemo");
            return (Criteria) this;
        }

        public Criteria andFieldMemoNotBetween(String value1, String value2) {
            addCriterion("field_memo not between", value1, value2, "fieldMemo");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIsNull() {
            addCriterion("field_type is null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIsNotNull() {
            addCriterion("field_type is not null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeEqualTo(String value) {
            addCriterion("field_type =", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotEqualTo(String value) {
            addCriterion("field_type <>", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThan(String value) {
            addCriterion("field_type >", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThanOrEqualTo(String value) {
            addCriterion("field_type >=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThan(String value) {
            addCriterion("field_type <", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThanOrEqualTo(String value) {
            addCriterion("field_type <=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLike(String value) {
            addCriterion("field_type like", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotLike(String value) {
            addCriterion("field_type not like", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIn(List<String> values) {
            addCriterion("field_type in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotIn(List<String> values) {
            addCriterion("field_type not in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeBetween(String value1, String value2) {
            addCriterion("field_type between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotBetween(String value1, String value2) {
            addCriterion("field_type not between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldIsmustIsNull() {
            addCriterion("field_ismust is null");
            return (Criteria) this;
        }

        public Criteria andFieldIsmustIsNotNull() {
            addCriterion("field_ismust is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIsmustEqualTo(Boolean value) {
            addCriterion("field_ismust =", value, "fieldIsmust");
            return (Criteria) this;
        }

        public Criteria andFieldIsmustNotEqualTo(Boolean value) {
            addCriterion("field_ismust <>", value, "fieldIsmust");
            return (Criteria) this;
        }

        public Criteria andFieldIsmustGreaterThan(Boolean value) {
            addCriterion("field_ismust >", value, "fieldIsmust");
            return (Criteria) this;
        }

        public Criteria andFieldIsmustGreaterThanOrEqualTo(Boolean value) {
            addCriterion("field_ismust >=", value, "fieldIsmust");
            return (Criteria) this;
        }

        public Criteria andFieldIsmustLessThan(Boolean value) {
            addCriterion("field_ismust <", value, "fieldIsmust");
            return (Criteria) this;
        }

        public Criteria andFieldIsmustLessThanOrEqualTo(Boolean value) {
            addCriterion("field_ismust <=", value, "fieldIsmust");
            return (Criteria) this;
        }

        public Criteria andFieldIsmustIn(List<Boolean> values) {
            addCriterion("field_ismust in", values, "fieldIsmust");
            return (Criteria) this;
        }

        public Criteria andFieldIsmustNotIn(List<Boolean> values) {
            addCriterion("field_ismust not in", values, "fieldIsmust");
            return (Criteria) this;
        }

        public Criteria andFieldIsmustBetween(Boolean value1, Boolean value2) {
            addCriterion("field_ismust between", value1, value2, "fieldIsmust");
            return (Criteria) this;
        }

        public Criteria andFieldIsmustNotBetween(Boolean value1, Boolean value2) {
            addCriterion("field_ismust not between", value1, value2, "fieldIsmust");
            return (Criteria) this;
        }

        public Criteria andFieldSizeIsNull() {
            addCriterion("field_size is null");
            return (Criteria) this;
        }

        public Criteria andFieldSizeIsNotNull() {
            addCriterion("field_size is not null");
            return (Criteria) this;
        }

        public Criteria andFieldSizeEqualTo(Integer value) {
            addCriterion("field_size =", value, "fieldSize");
            return (Criteria) this;
        }

        public Criteria andFieldSizeNotEqualTo(Integer value) {
            addCriterion("field_size <>", value, "fieldSize");
            return (Criteria) this;
        }

        public Criteria andFieldSizeGreaterThan(Integer value) {
            addCriterion("field_size >", value, "fieldSize");
            return (Criteria) this;
        }

        public Criteria andFieldSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("field_size >=", value, "fieldSize");
            return (Criteria) this;
        }

        public Criteria andFieldSizeLessThan(Integer value) {
            addCriterion("field_size <", value, "fieldSize");
            return (Criteria) this;
        }

        public Criteria andFieldSizeLessThanOrEqualTo(Integer value) {
            addCriterion("field_size <=", value, "fieldSize");
            return (Criteria) this;
        }

        public Criteria andFieldSizeIn(List<Integer> values) {
            addCriterion("field_size in", values, "fieldSize");
            return (Criteria) this;
        }

        public Criteria andFieldSizeNotIn(List<Integer> values) {
            addCriterion("field_size not in", values, "fieldSize");
            return (Criteria) this;
        }

        public Criteria andFieldSizeBetween(Integer value1, Integer value2) {
            addCriterion("field_size between", value1, value2, "fieldSize");
            return (Criteria) this;
        }

        public Criteria andFieldSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("field_size not between", value1, value2, "fieldSize");
            return (Criteria) this;
        }

        public Criteria andFieldVisibleIsNull() {
            addCriterion("field_visible is null");
            return (Criteria) this;
        }

        public Criteria andFieldVisibleIsNotNull() {
            addCriterion("field_visible is not null");
            return (Criteria) this;
        }

        public Criteria andFieldVisibleEqualTo(Boolean value) {
            addCriterion("field_visible =", value, "fieldVisible");
            return (Criteria) this;
        }

        public Criteria andFieldVisibleNotEqualTo(Boolean value) {
            addCriterion("field_visible <>", value, "fieldVisible");
            return (Criteria) this;
        }

        public Criteria andFieldVisibleGreaterThan(Boolean value) {
            addCriterion("field_visible >", value, "fieldVisible");
            return (Criteria) this;
        }

        public Criteria andFieldVisibleGreaterThanOrEqualTo(Boolean value) {
            addCriterion("field_visible >=", value, "fieldVisible");
            return (Criteria) this;
        }

        public Criteria andFieldVisibleLessThan(Boolean value) {
            addCriterion("field_visible <", value, "fieldVisible");
            return (Criteria) this;
        }

        public Criteria andFieldVisibleLessThanOrEqualTo(Boolean value) {
            addCriterion("field_visible <=", value, "fieldVisible");
            return (Criteria) this;
        }

        public Criteria andFieldVisibleIn(List<Boolean> values) {
            addCriterion("field_visible in", values, "fieldVisible");
            return (Criteria) this;
        }

        public Criteria andFieldVisibleNotIn(List<Boolean> values) {
            addCriterion("field_visible not in", values, "fieldVisible");
            return (Criteria) this;
        }

        public Criteria andFieldVisibleBetween(Boolean value1, Boolean value2) {
            addCriterion("field_visible between", value1, value2, "fieldVisible");
            return (Criteria) this;
        }

        public Criteria andFieldVisibleNotBetween(Boolean value1, Boolean value2) {
            addCriterion("field_visible not between", value1, value2, "fieldVisible");
            return (Criteria) this;
        }

        public Criteria andFieldDisplaysizeIsNull() {
            addCriterion("field_displaysize is null");
            return (Criteria) this;
        }

        public Criteria andFieldDisplaysizeIsNotNull() {
            addCriterion("field_displaysize is not null");
            return (Criteria) this;
        }

        public Criteria andFieldDisplaysizeEqualTo(Integer value) {
            addCriterion("field_displaysize =", value, "fieldDisplaysize");
            return (Criteria) this;
        }

        public Criteria andFieldDisplaysizeNotEqualTo(Integer value) {
            addCriterion("field_displaysize <>", value, "fieldDisplaysize");
            return (Criteria) this;
        }

        public Criteria andFieldDisplaysizeGreaterThan(Integer value) {
            addCriterion("field_displaysize >", value, "fieldDisplaysize");
            return (Criteria) this;
        }

        public Criteria andFieldDisplaysizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("field_displaysize >=", value, "fieldDisplaysize");
            return (Criteria) this;
        }

        public Criteria andFieldDisplaysizeLessThan(Integer value) {
            addCriterion("field_displaysize <", value, "fieldDisplaysize");
            return (Criteria) this;
        }

        public Criteria andFieldDisplaysizeLessThanOrEqualTo(Integer value) {
            addCriterion("field_displaysize <=", value, "fieldDisplaysize");
            return (Criteria) this;
        }

        public Criteria andFieldDisplaysizeIn(List<Integer> values) {
            addCriterion("field_displaysize in", values, "fieldDisplaysize");
            return (Criteria) this;
        }

        public Criteria andFieldDisplaysizeNotIn(List<Integer> values) {
            addCriterion("field_displaysize not in", values, "fieldDisplaysize");
            return (Criteria) this;
        }

        public Criteria andFieldDisplaysizeBetween(Integer value1, Integer value2) {
            addCriterion("field_displaysize between", value1, value2, "fieldDisplaysize");
            return (Criteria) this;
        }

        public Criteria andFieldDisplaysizeNotBetween(Integer value1, Integer value2) {
            addCriterion("field_displaysize not between", value1, value2, "fieldDisplaysize");
            return (Criteria) this;
        }

        public Criteria andFieldReadonlyIsNull() {
            addCriterion("field_readonly is null");
            return (Criteria) this;
        }

        public Criteria andFieldReadonlyIsNotNull() {
            addCriterion("field_readonly is not null");
            return (Criteria) this;
        }

        public Criteria andFieldReadonlyEqualTo(Boolean value) {
            addCriterion("field_readonly =", value, "fieldReadonly");
            return (Criteria) this;
        }

        public Criteria andFieldReadonlyNotEqualTo(Boolean value) {
            addCriterion("field_readonly <>", value, "fieldReadonly");
            return (Criteria) this;
        }

        public Criteria andFieldReadonlyGreaterThan(Boolean value) {
            addCriterion("field_readonly >", value, "fieldReadonly");
            return (Criteria) this;
        }

        public Criteria andFieldReadonlyGreaterThanOrEqualTo(Boolean value) {
            addCriterion("field_readonly >=", value, "fieldReadonly");
            return (Criteria) this;
        }

        public Criteria andFieldReadonlyLessThan(Boolean value) {
            addCriterion("field_readonly <", value, "fieldReadonly");
            return (Criteria) this;
        }

        public Criteria andFieldReadonlyLessThanOrEqualTo(Boolean value) {
            addCriterion("field_readonly <=", value, "fieldReadonly");
            return (Criteria) this;
        }

        public Criteria andFieldReadonlyIn(List<Boolean> values) {
            addCriterion("field_readonly in", values, "fieldReadonly");
            return (Criteria) this;
        }

        public Criteria andFieldReadonlyNotIn(List<Boolean> values) {
            addCriterion("field_readonly not in", values, "fieldReadonly");
            return (Criteria) this;
        }

        public Criteria andFieldReadonlyBetween(Boolean value1, Boolean value2) {
            addCriterion("field_readonly between", value1, value2, "fieldReadonly");
            return (Criteria) this;
        }

        public Criteria andFieldReadonlyNotBetween(Boolean value1, Boolean value2) {
            addCriterion("field_readonly not between", value1, value2, "fieldReadonly");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultIsNull() {
            addCriterion("field_default is null");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultIsNotNull() {
            addCriterion("field_default is not null");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultEqualTo(String value) {
            addCriterion("field_default =", value, "fieldDefault");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultNotEqualTo(String value) {
            addCriterion("field_default <>", value, "fieldDefault");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultGreaterThan(String value) {
            addCriterion("field_default >", value, "fieldDefault");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultGreaterThanOrEqualTo(String value) {
            addCriterion("field_default >=", value, "fieldDefault");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultLessThan(String value) {
            addCriterion("field_default <", value, "fieldDefault");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultLessThanOrEqualTo(String value) {
            addCriterion("field_default <=", value, "fieldDefault");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultLike(String value) {
            addCriterion("field_default like", value, "fieldDefault");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultNotLike(String value) {
            addCriterion("field_default not like", value, "fieldDefault");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultIn(List<String> values) {
            addCriterion("field_default in", values, "fieldDefault");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultNotIn(List<String> values) {
            addCriterion("field_default not in", values, "fieldDefault");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultBetween(String value1, String value2) {
            addCriterion("field_default between", value1, value2, "fieldDefault");
            return (Criteria) this;
        }

        public Criteria andFieldDefaultNotBetween(String value1, String value2) {
            addCriterion("field_default not between", value1, value2, "fieldDefault");
            return (Criteria) this;
        }

        public Criteria andFieldOrderIsNull() {
            addCriterion("field_order is null");
            return (Criteria) this;
        }

        public Criteria andFieldOrderIsNotNull() {
            addCriterion("field_order is not null");
            return (Criteria) this;
        }

        public Criteria andFieldOrderEqualTo(Integer value) {
            addCriterion("field_order =", value, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderNotEqualTo(Integer value) {
            addCriterion("field_order <>", value, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderGreaterThan(Integer value) {
            addCriterion("field_order >", value, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("field_order >=", value, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderLessThan(Integer value) {
            addCriterion("field_order <", value, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderLessThanOrEqualTo(Integer value) {
            addCriterion("field_order <=", value, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderIn(List<Integer> values) {
            addCriterion("field_order in", values, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderNotIn(List<Integer> values) {
            addCriterion("field_order not in", values, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderBetween(Integer value1, Integer value2) {
            addCriterion("field_order between", value1, value2, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("field_order not between", value1, value2, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldSrcIsNull() {
            addCriterion("field_src is null");
            return (Criteria) this;
        }

        public Criteria andFieldSrcIsNotNull() {
            addCriterion("field_src is not null");
            return (Criteria) this;
        }

        public Criteria andFieldSrcEqualTo(Integer value) {
            addCriterion("field_src =", value, "fieldSrc");
            return (Criteria) this;
        }

        public Criteria andFieldSrcNotEqualTo(Integer value) {
            addCriterion("field_src <>", value, "fieldSrc");
            return (Criteria) this;
        }

        public Criteria andFieldSrcGreaterThan(Integer value) {
            addCriterion("field_src >", value, "fieldSrc");
            return (Criteria) this;
        }

        public Criteria andFieldSrcGreaterThanOrEqualTo(Integer value) {
            addCriterion("field_src >=", value, "fieldSrc");
            return (Criteria) this;
        }

        public Criteria andFieldSrcLessThan(Integer value) {
            addCriterion("field_src <", value, "fieldSrc");
            return (Criteria) this;
        }

        public Criteria andFieldSrcLessThanOrEqualTo(Integer value) {
            addCriterion("field_src <=", value, "fieldSrc");
            return (Criteria) this;
        }

        public Criteria andFieldSrcIn(List<Integer> values) {
            addCriterion("field_src in", values, "fieldSrc");
            return (Criteria) this;
        }

        public Criteria andFieldSrcNotIn(List<Integer> values) {
            addCriterion("field_src not in", values, "fieldSrc");
            return (Criteria) this;
        }

        public Criteria andFieldSrcBetween(Integer value1, Integer value2) {
            addCriterion("field_src between", value1, value2, "fieldSrc");
            return (Criteria) this;
        }

        public Criteria andFieldSrcNotBetween(Integer value1, Integer value2) {
            addCriterion("field_src not between", value1, value2, "fieldSrc");
            return (Criteria) this;
        }

        public Criteria andRefPoolIsNull() {
            addCriterion("ref_pool is null");
            return (Criteria) this;
        }

        public Criteria andRefPoolIsNotNull() {
            addCriterion("ref_pool is not null");
            return (Criteria) this;
        }

        public Criteria andRefPoolEqualTo(String value) {
            addCriterion("ref_pool =", value, "refPool");
            return (Criteria) this;
        }

        public Criteria andRefPoolNotEqualTo(String value) {
            addCriterion("ref_pool <>", value, "refPool");
            return (Criteria) this;
        }

        public Criteria andRefPoolGreaterThan(String value) {
            addCriterion("ref_pool >", value, "refPool");
            return (Criteria) this;
        }

        public Criteria andRefPoolGreaterThanOrEqualTo(String value) {
            addCriterion("ref_pool >=", value, "refPool");
            return (Criteria) this;
        }

        public Criteria andRefPoolLessThan(String value) {
            addCriterion("ref_pool <", value, "refPool");
            return (Criteria) this;
        }

        public Criteria andRefPoolLessThanOrEqualTo(String value) {
            addCriterion("ref_pool <=", value, "refPool");
            return (Criteria) this;
        }

        public Criteria andRefPoolLike(String value) {
            addCriterion("ref_pool like", value, "refPool");
            return (Criteria) this;
        }

        public Criteria andRefPoolNotLike(String value) {
            addCriterion("ref_pool not like", value, "refPool");
            return (Criteria) this;
        }

        public Criteria andRefPoolIn(List<String> values) {
            addCriterion("ref_pool in", values, "refPool");
            return (Criteria) this;
        }

        public Criteria andRefPoolNotIn(List<String> values) {
            addCriterion("ref_pool not in", values, "refPool");
            return (Criteria) this;
        }

        public Criteria andRefPoolBetween(String value1, String value2) {
            addCriterion("ref_pool between", value1, value2, "refPool");
            return (Criteria) this;
        }

        public Criteria andRefPoolNotBetween(String value1, String value2) {
            addCriterion("ref_pool not between", value1, value2, "refPool");
            return (Criteria) this;
        }

        public Criteria andRefTableIsNull() {
            addCriterion("ref_table is null");
            return (Criteria) this;
        }

        public Criteria andRefTableIsNotNull() {
            addCriterion("ref_table is not null");
            return (Criteria) this;
        }

        public Criteria andRefTableEqualTo(String value) {
            addCriterion("ref_table =", value, "refTable");
            return (Criteria) this;
        }

        public Criteria andRefTableNotEqualTo(String value) {
            addCriterion("ref_table <>", value, "refTable");
            return (Criteria) this;
        }

        public Criteria andRefTableGreaterThan(String value) {
            addCriterion("ref_table >", value, "refTable");
            return (Criteria) this;
        }

        public Criteria andRefTableGreaterThanOrEqualTo(String value) {
            addCriterion("ref_table >=", value, "refTable");
            return (Criteria) this;
        }

        public Criteria andRefTableLessThan(String value) {
            addCriterion("ref_table <", value, "refTable");
            return (Criteria) this;
        }

        public Criteria andRefTableLessThanOrEqualTo(String value) {
            addCriterion("ref_table <=", value, "refTable");
            return (Criteria) this;
        }

        public Criteria andRefTableIn(List<String> values) {
            addCriterion("ref_table in", values, "refTable");
            return (Criteria) this;
        }

        public Criteria andRefTableNotIn(List<String> values) {
            addCriterion("ref_table not in", values, "refTable");
            return (Criteria) this;
        }

        public Criteria andRefTableBetween(String value1, String value2) {
            addCriterion("ref_table between", value1, value2, "refTable");
            return (Criteria) this;
        }

        public Criteria andRefTableNotBetween(String value1, String value2) {
            addCriterion("ref_table not between", value1, value2, "refTable");
            return (Criteria) this;
        }

        public Criteria andRefFieldIsNull() {
            addCriterion("ref_field is null");
            return (Criteria) this;
        }

        public Criteria andRefFieldIsNotNull() {
            addCriterion("ref_field is not null");
            return (Criteria) this;
        }

        public Criteria andRefFieldEqualTo(String value) {
            addCriterion("ref_field =", value, "refField");
            return (Criteria) this;
        }

        public Criteria andRefFieldNotEqualTo(String value) {
            addCriterion("ref_field <>", value, "refField");
            return (Criteria) this;
        }

        public Criteria andRefFieldGreaterThan(String value) {
            addCriterion("ref_field >", value, "refField");
            return (Criteria) this;
        }

        public Criteria andRefFieldGreaterThanOrEqualTo(String value) {
            addCriterion("ref_field >=", value, "refField");
            return (Criteria) this;
        }

        public Criteria andRefFieldLessThan(String value) {
            addCriterion("ref_field <", value, "refField");
            return (Criteria) this;
        }

        public Criteria andRefFieldLessThanOrEqualTo(String value) {
            addCriterion("ref_field <=", value, "refField");
            return (Criteria) this;
        }

        public Criteria andRefFieldIn(List<String> values) {
            addCriterion("ref_field in", values, "refField");
            return (Criteria) this;
        }

        public Criteria andRefFieldNotIn(List<String> values) {
            addCriterion("ref_field not in", values, "refField");
            return (Criteria) this;
        }

        public Criteria andRefFieldBetween(String value1, String value2) {
            addCriterion("ref_field between", value1, value2, "refField");
            return (Criteria) this;
        }

        public Criteria andRefFieldNotBetween(String value1, String value2) {
            addCriterion("ref_field not between", value1, value2, "refField");
            return (Criteria) this;
        }

        public Criteria andRefDisplayidIsNull() {
            addCriterion("ref_displayID is null");
            return (Criteria) this;
        }

        public Criteria andRefDisplayidIsNotNull() {
            addCriterion("ref_displayID is not null");
            return (Criteria) this;
        }

        public Criteria andRefDisplayidEqualTo(String value) {
            addCriterion("ref_displayID =", value, "refDisplayid");
            return (Criteria) this;
        }

        public Criteria andRefDisplayidNotEqualTo(String value) {
            addCriterion("ref_displayID <>", value, "refDisplayid");
            return (Criteria) this;
        }

        public Criteria andRefDisplayidGreaterThan(String value) {
            addCriterion("ref_displayID >", value, "refDisplayid");
            return (Criteria) this;
        }

        public Criteria andRefDisplayidGreaterThanOrEqualTo(String value) {
            addCriterion("ref_displayID >=", value, "refDisplayid");
            return (Criteria) this;
        }

        public Criteria andRefDisplayidLessThan(String value) {
            addCriterion("ref_displayID <", value, "refDisplayid");
            return (Criteria) this;
        }

        public Criteria andRefDisplayidLessThanOrEqualTo(String value) {
            addCriterion("ref_displayID <=", value, "refDisplayid");
            return (Criteria) this;
        }

        public Criteria andRefDisplayidIn(List<String> values) {
            addCriterion("ref_displayID in", values, "refDisplayid");
            return (Criteria) this;
        }

        public Criteria andRefDisplayidNotIn(List<String> values) {
            addCriterion("ref_displayID not in", values, "refDisplayid");
            return (Criteria) this;
        }

        public Criteria andRefDisplayidBetween(String value1, String value2) {
            addCriterion("ref_displayID between", value1, value2, "refDisplayid");
            return (Criteria) this;
        }

        public Criteria andRefDisplayidNotBetween(String value1, String value2) {
            addCriterion("ref_displayID not between", value1, value2, "refDisplayid");
            return (Criteria) this;
        }

        public Criteria andRefFilterIsNull() {
            addCriterion("ref_filter is null");
            return (Criteria) this;
        }

        public Criteria andRefFilterIsNotNull() {
            addCriterion("ref_filter is not null");
            return (Criteria) this;
        }

        public Criteria andRefFilterEqualTo(Byte value) {
            addCriterion("ref_filter =", value, "refFilter");
            return (Criteria) this;
        }

        public Criteria andRefFilterNotEqualTo(Byte value) {
            addCriterion("ref_filter <>", value, "refFilter");
            return (Criteria) this;
        }

        public Criteria andRefFilterGreaterThan(Byte value) {
            addCriterion("ref_filter >", value, "refFilter");
            return (Criteria) this;
        }

        public Criteria andRefFilterGreaterThanOrEqualTo(Byte value) {
            addCriterion("ref_filter >=", value, "refFilter");
            return (Criteria) this;
        }

        public Criteria andRefFilterLessThan(Byte value) {
            addCriterion("ref_filter <", value, "refFilter");
            return (Criteria) this;
        }

        public Criteria andRefFilterLessThanOrEqualTo(Byte value) {
            addCriterion("ref_filter <=", value, "refFilter");
            return (Criteria) this;
        }

        public Criteria andRefFilterIn(List<Byte> values) {
            addCriterion("ref_filter in", values, "refFilter");
            return (Criteria) this;
        }

        public Criteria andRefFilterNotIn(List<Byte> values) {
            addCriterion("ref_filter not in", values, "refFilter");
            return (Criteria) this;
        }

        public Criteria andRefFilterBetween(Byte value1, Byte value2) {
            addCriterion("ref_filter between", value1, value2, "refFilter");
            return (Criteria) this;
        }

        public Criteria andRefFilterNotBetween(Byte value1, Byte value2) {
            addCriterion("ref_filter not between", value1, value2, "refFilter");
            return (Criteria) this;
        }

        public Criteria andFieldPkIsNull() {
            addCriterion("field_pk is null");
            return (Criteria) this;
        }

        public Criteria andFieldPkIsNotNull() {
            addCriterion("field_pk is not null");
            return (Criteria) this;
        }

        public Criteria andFieldPkEqualTo(Boolean value) {
            addCriterion("field_pk =", value, "fieldPk");
            return (Criteria) this;
        }

        public Criteria andFieldPkNotEqualTo(Boolean value) {
            addCriterion("field_pk <>", value, "fieldPk");
            return (Criteria) this;
        }

        public Criteria andFieldPkGreaterThan(Boolean value) {
            addCriterion("field_pk >", value, "fieldPk");
            return (Criteria) this;
        }

        public Criteria andFieldPkGreaterThanOrEqualTo(Boolean value) {
            addCriterion("field_pk >=", value, "fieldPk");
            return (Criteria) this;
        }

        public Criteria andFieldPkLessThan(Boolean value) {
            addCriterion("field_pk <", value, "fieldPk");
            return (Criteria) this;
        }

        public Criteria andFieldPkLessThanOrEqualTo(Boolean value) {
            addCriterion("field_pk <=", value, "fieldPk");
            return (Criteria) this;
        }

        public Criteria andFieldPkIn(List<Boolean> values) {
            addCriterion("field_pk in", values, "fieldPk");
            return (Criteria) this;
        }

        public Criteria andFieldPkNotIn(List<Boolean> values) {
            addCriterion("field_pk not in", values, "fieldPk");
            return (Criteria) this;
        }

        public Criteria andFieldPkBetween(Boolean value1, Boolean value2) {
            addCriterion("field_pk between", value1, value2, "fieldPk");
            return (Criteria) this;
        }

        public Criteria andFieldPkNotBetween(Boolean value1, Boolean value2) {
            addCriterion("field_pk not between", value1, value2, "fieldPk");
            return (Criteria) this;
        }

        public Criteria andFieldAutoIsNull() {
            addCriterion("field_auto is null");
            return (Criteria) this;
        }

        public Criteria andFieldAutoIsNotNull() {
            addCriterion("field_auto is not null");
            return (Criteria) this;
        }

        public Criteria andFieldAutoEqualTo(Boolean value) {
            addCriterion("field_auto =", value, "fieldAuto");
            return (Criteria) this;
        }

        public Criteria andFieldAutoNotEqualTo(Boolean value) {
            addCriterion("field_auto <>", value, "fieldAuto");
            return (Criteria) this;
        }

        public Criteria andFieldAutoGreaterThan(Boolean value) {
            addCriterion("field_auto >", value, "fieldAuto");
            return (Criteria) this;
        }

        public Criteria andFieldAutoGreaterThanOrEqualTo(Boolean value) {
            addCriterion("field_auto >=", value, "fieldAuto");
            return (Criteria) this;
        }

        public Criteria andFieldAutoLessThan(Boolean value) {
            addCriterion("field_auto <", value, "fieldAuto");
            return (Criteria) this;
        }

        public Criteria andFieldAutoLessThanOrEqualTo(Boolean value) {
            addCriterion("field_auto <=", value, "fieldAuto");
            return (Criteria) this;
        }

        public Criteria andFieldAutoIn(List<Boolean> values) {
            addCriterion("field_auto in", values, "fieldAuto");
            return (Criteria) this;
        }

        public Criteria andFieldAutoNotIn(List<Boolean> values) {
            addCriterion("field_auto not in", values, "fieldAuto");
            return (Criteria) this;
        }

        public Criteria andFieldAutoBetween(Boolean value1, Boolean value2) {
            addCriterion("field_auto between", value1, value2, "fieldAuto");
            return (Criteria) this;
        }

        public Criteria andFieldAutoNotBetween(Boolean value1, Boolean value2) {
            addCriterion("field_auto not between", value1, value2, "fieldAuto");
            return (Criteria) this;
        }

        public Criteria andFieldIsnullIsNull() {
            addCriterion("field_isNull is null");
            return (Criteria) this;
        }

        public Criteria andFieldIsnullIsNotNull() {
            addCriterion("field_isNull is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIsnullEqualTo(Boolean value) {
            addCriterion("field_isNull =", value, "fieldIsnull");
            return (Criteria) this;
        }

        public Criteria andFieldIsnullNotEqualTo(Boolean value) {
            addCriterion("field_isNull <>", value, "fieldIsnull");
            return (Criteria) this;
        }

        public Criteria andFieldIsnullGreaterThan(Boolean value) {
            addCriterion("field_isNull >", value, "fieldIsnull");
            return (Criteria) this;
        }

        public Criteria andFieldIsnullGreaterThanOrEqualTo(Boolean value) {
            addCriterion("field_isNull >=", value, "fieldIsnull");
            return (Criteria) this;
        }

        public Criteria andFieldIsnullLessThan(Boolean value) {
            addCriterion("field_isNull <", value, "fieldIsnull");
            return (Criteria) this;
        }

        public Criteria andFieldIsnullLessThanOrEqualTo(Boolean value) {
            addCriterion("field_isNull <=", value, "fieldIsnull");
            return (Criteria) this;
        }

        public Criteria andFieldIsnullIn(List<Boolean> values) {
            addCriterion("field_isNull in", values, "fieldIsnull");
            return (Criteria) this;
        }

        public Criteria andFieldIsnullNotIn(List<Boolean> values) {
            addCriterion("field_isNull not in", values, "fieldIsnull");
            return (Criteria) this;
        }

        public Criteria andFieldIsnullBetween(Boolean value1, Boolean value2) {
            addCriterion("field_isNull between", value1, value2, "fieldIsnull");
            return (Criteria) this;
        }

        public Criteria andFieldIsnullNotBetween(Boolean value1, Boolean value2) {
            addCriterion("field_isNull not between", value1, value2, "fieldIsnull");
            return (Criteria) this;
        }

        public Criteria andFieldIscalIsNull() {
            addCriterion("field_iscal is null");
            return (Criteria) this;
        }

        public Criteria andFieldIscalIsNotNull() {
            addCriterion("field_iscal is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIscalEqualTo(Boolean value) {
            addCriterion("field_iscal =", value, "fieldIscal");
            return (Criteria) this;
        }

        public Criteria andFieldIscalNotEqualTo(Boolean value) {
            addCriterion("field_iscal <>", value, "fieldIscal");
            return (Criteria) this;
        }

        public Criteria andFieldIscalGreaterThan(Boolean value) {
            addCriterion("field_iscal >", value, "fieldIscal");
            return (Criteria) this;
        }

        public Criteria andFieldIscalGreaterThanOrEqualTo(Boolean value) {
            addCriterion("field_iscal >=", value, "fieldIscal");
            return (Criteria) this;
        }

        public Criteria andFieldIscalLessThan(Boolean value) {
            addCriterion("field_iscal <", value, "fieldIscal");
            return (Criteria) this;
        }

        public Criteria andFieldIscalLessThanOrEqualTo(Boolean value) {
            addCriterion("field_iscal <=", value, "fieldIscal");
            return (Criteria) this;
        }

        public Criteria andFieldIscalIn(List<Boolean> values) {
            addCriterion("field_iscal in", values, "fieldIscal");
            return (Criteria) this;
        }

        public Criteria andFieldIscalNotIn(List<Boolean> values) {
            addCriterion("field_iscal not in", values, "fieldIscal");
            return (Criteria) this;
        }

        public Criteria andFieldIscalBetween(Boolean value1, Boolean value2) {
            addCriterion("field_iscal between", value1, value2, "fieldIscal");
            return (Criteria) this;
        }

        public Criteria andFieldIscalNotBetween(Boolean value1, Boolean value2) {
            addCriterion("field_iscal not between", value1, value2, "fieldIscal");
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