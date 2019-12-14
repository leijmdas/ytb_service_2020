package ytb.account.wallet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ProjectExample() {
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

        public Criteria andProjectIdChangeIsNull() {
            addCriterion("project_id_change is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdChangeIsNotNull() {
            addCriterion("project_id_change is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdChangeEqualTo(Integer value) {
            addCriterion("project_id_change =", value, "projectIdChange");
            return (Criteria) this;
        }

        public Criteria andProjectIdChangeNotEqualTo(Integer value) {
            addCriterion("project_id_change <>", value, "projectIdChange");
            return (Criteria) this;
        }

        public Criteria andProjectIdChangeGreaterThan(Integer value) {
            addCriterion("project_id_change >", value, "projectIdChange");
            return (Criteria) this;
        }

        public Criteria andProjectIdChangeGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id_change >=", value, "projectIdChange");
            return (Criteria) this;
        }

        public Criteria andProjectIdChangeLessThan(Integer value) {
            addCriterion("project_id_change <", value, "projectIdChange");
            return (Criteria) this;
        }

        public Criteria andProjectIdChangeLessThanOrEqualTo(Integer value) {
            addCriterion("project_id_change <=", value, "projectIdChange");
            return (Criteria) this;
        }

        public Criteria andProjectIdChangeIn(List<Integer> values) {
            addCriterion("project_id_change in", values, "projectIdChange");
            return (Criteria) this;
        }

        public Criteria andProjectIdChangeNotIn(List<Integer> values) {
            addCriterion("project_id_change not in", values, "projectIdChange");
            return (Criteria) this;
        }

        public Criteria andProjectIdChangeBetween(Integer value1, Integer value2) {
            addCriterion("project_id_change between", value1, value2, "projectIdChange");
            return (Criteria) this;
        }

        public Criteria andProjectIdChangeNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id_change not between", value1, value2, "projectIdChange");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andUserId1IsNull() {
            addCriterion("user_id1 is null");
            return (Criteria) this;
        }

        public Criteria andUserId1IsNotNull() {
            addCriterion("user_id1 is not null");
            return (Criteria) this;
        }

        public Criteria andUserId1EqualTo(Integer value) {
            addCriterion("user_id1 =", value, "userId1");
            return (Criteria) this;
        }

        public Criteria andUserId1NotEqualTo(Integer value) {
            addCriterion("user_id1 <>", value, "userId1");
            return (Criteria) this;
        }

        public Criteria andUserId1GreaterThan(Integer value) {
            addCriterion("user_id1 >", value, "userId1");
            return (Criteria) this;
        }

        public Criteria andUserId1GreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id1 >=", value, "userId1");
            return (Criteria) this;
        }

        public Criteria andUserId1LessThan(Integer value) {
            addCriterion("user_id1 <", value, "userId1");
            return (Criteria) this;
        }

        public Criteria andUserId1LessThanOrEqualTo(Integer value) {
            addCriterion("user_id1 <=", value, "userId1");
            return (Criteria) this;
        }

        public Criteria andUserId1In(List<Integer> values) {
            addCriterion("user_id1 in", values, "userId1");
            return (Criteria) this;
        }

        public Criteria andUserId1NotIn(List<Integer> values) {
            addCriterion("user_id1 not in", values, "userId1");
            return (Criteria) this;
        }

        public Criteria andUserId1Between(Integer value1, Integer value2) {
            addCriterion("user_id1 between", value1, value2, "userId1");
            return (Criteria) this;
        }

        public Criteria andUserId1NotBetween(Integer value1, Integer value2) {
            addCriterion("user_id1 not between", value1, value2, "userId1");
            return (Criteria) this;
        }

        public Criteria andCompanyId1IsNull() {
            addCriterion("company_id1 is null");
            return (Criteria) this;
        }

        public Criteria andCompanyId1IsNotNull() {
            addCriterion("company_id1 is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyId1EqualTo(Integer value) {
            addCriterion("company_id1 =", value, "companyId1");
            return (Criteria) this;
        }

        public Criteria andCompanyId1NotEqualTo(Integer value) {
            addCriterion("company_id1 <>", value, "companyId1");
            return (Criteria) this;
        }

        public Criteria andCompanyId1GreaterThan(Integer value) {
            addCriterion("company_id1 >", value, "companyId1");
            return (Criteria) this;
        }

        public Criteria andCompanyId1GreaterThanOrEqualTo(Integer value) {
            addCriterion("company_id1 >=", value, "companyId1");
            return (Criteria) this;
        }

        public Criteria andCompanyId1LessThan(Integer value) {
            addCriterion("company_id1 <", value, "companyId1");
            return (Criteria) this;
        }

        public Criteria andCompanyId1LessThanOrEqualTo(Integer value) {
            addCriterion("company_id1 <=", value, "companyId1");
            return (Criteria) this;
        }

        public Criteria andCompanyId1In(List<Integer> values) {
            addCriterion("company_id1 in", values, "companyId1");
            return (Criteria) this;
        }

        public Criteria andCompanyId1NotIn(List<Integer> values) {
            addCriterion("company_id1 not in", values, "companyId1");
            return (Criteria) this;
        }

        public Criteria andCompanyId1Between(Integer value1, Integer value2) {
            addCriterion("company_id1 between", value1, value2, "companyId1");
            return (Criteria) this;
        }

        public Criteria andCompanyId1NotBetween(Integer value1, Integer value2) {
            addCriterion("company_id1 not between", value1, value2, "companyId1");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdIsNull() {
            addCriterion("project_type_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdIsNotNull() {
            addCriterion("project_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdEqualTo(Integer value) {
            addCriterion("project_type_id =", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdNotEqualTo(Integer value) {
            addCriterion("project_type_id <>", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdGreaterThan(Integer value) {
            addCriterion("project_type_id >", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_type_id >=", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdLessThan(Integer value) {
            addCriterion("project_type_id <", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_type_id <=", value, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdIn(List<Integer> values) {
            addCriterion("project_type_id in", values, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdNotIn(List<Integer> values) {
            addCriterion("project_type_id not in", values, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("project_type_id between", value1, value2, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andProjectTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_type_id not between", value1, value2, "projectTypeId");
            return (Criteria) this;
        }

        public Criteria andFolderIdIsNull() {
            addCriterion("folder_id is null");
            return (Criteria) this;
        }

        public Criteria andFolderIdIsNotNull() {
            addCriterion("folder_id is not null");
            return (Criteria) this;
        }

        public Criteria andFolderIdEqualTo(Integer value) {
            addCriterion("folder_id =", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdNotEqualTo(Integer value) {
            addCriterion("folder_id <>", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdGreaterThan(Integer value) {
            addCriterion("folder_id >", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("folder_id >=", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdLessThan(Integer value) {
            addCriterion("folder_id <", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdLessThanOrEqualTo(Integer value) {
            addCriterion("folder_id <=", value, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdIn(List<Integer> values) {
            addCriterion("folder_id in", values, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdNotIn(List<Integer> values) {
            addCriterion("folder_id not in", values, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdBetween(Integer value1, Integer value2) {
            addCriterion("folder_id between", value1, value2, "folderId");
            return (Criteria) this;
        }

        public Criteria andFolderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("folder_id not between", value1, value2, "folderId");
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

        public Criteria andPhaseNoIsNull() {
            addCriterion("phase_no is null");
            return (Criteria) this;
        }

        public Criteria andPhaseNoIsNotNull() {
            addCriterion("phase_no is not null");
            return (Criteria) this;
        }

        public Criteria andPhaseNoEqualTo(Byte value) {
            addCriterion("phase_no =", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoNotEqualTo(Byte value) {
            addCriterion("phase_no <>", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoGreaterThan(Byte value) {
            addCriterion("phase_no >", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoGreaterThanOrEqualTo(Byte value) {
            addCriterion("phase_no >=", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoLessThan(Byte value) {
            addCriterion("phase_no <", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoLessThanOrEqualTo(Byte value) {
            addCriterion("phase_no <=", value, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoIn(List<Byte> values) {
            addCriterion("phase_no in", values, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoNotIn(List<Byte> values) {
            addCriterion("phase_no not in", values, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoBetween(Byte value1, Byte value2) {
            addCriterion("phase_no between", value1, value2, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseNoNotBetween(Byte value1, Byte value2) {
            addCriterion("phase_no not between", value1, value2, "phaseNo");
            return (Criteria) this;
        }

        public Criteria andPhaseStartIsNull() {
            addCriterion("phase_start is null");
            return (Criteria) this;
        }

        public Criteria andPhaseStartIsNotNull() {
            addCriterion("phase_start is not null");
            return (Criteria) this;
        }

        public Criteria andPhaseStartEqualTo(Short value) {
            addCriterion("phase_start =", value, "phaseStart");
            return (Criteria) this;
        }

        public Criteria andPhaseStartNotEqualTo(Short value) {
            addCriterion("phase_start <>", value, "phaseStart");
            return (Criteria) this;
        }

        public Criteria andPhaseStartGreaterThan(Short value) {
            addCriterion("phase_start >", value, "phaseStart");
            return (Criteria) this;
        }

        public Criteria andPhaseStartGreaterThanOrEqualTo(Short value) {
            addCriterion("phase_start >=", value, "phaseStart");
            return (Criteria) this;
        }

        public Criteria andPhaseStartLessThan(Short value) {
            addCriterion("phase_start <", value, "phaseStart");
            return (Criteria) this;
        }

        public Criteria andPhaseStartLessThanOrEqualTo(Short value) {
            addCriterion("phase_start <=", value, "phaseStart");
            return (Criteria) this;
        }

        public Criteria andPhaseStartIn(List<Short> values) {
            addCriterion("phase_start in", values, "phaseStart");
            return (Criteria) this;
        }

        public Criteria andPhaseStartNotIn(List<Short> values) {
            addCriterion("phase_start not in", values, "phaseStart");
            return (Criteria) this;
        }

        public Criteria andPhaseStartBetween(Short value1, Short value2) {
            addCriterion("phase_start between", value1, value2, "phaseStart");
            return (Criteria) this;
        }

        public Criteria andPhaseStartNotBetween(Short value1, Short value2) {
            addCriterion("phase_start not between", value1, value2, "phaseStart");
            return (Criteria) this;
        }

        public Criteria andViewNumberIsNull() {
            addCriterion("view_number is null");
            return (Criteria) this;
        }

        public Criteria andViewNumberIsNotNull() {
            addCriterion("view_number is not null");
            return (Criteria) this;
        }

        public Criteria andViewNumberEqualTo(Integer value) {
            addCriterion("view_number =", value, "viewNumber");
            return (Criteria) this;
        }

        public Criteria andViewNumberNotEqualTo(Integer value) {
            addCriterion("view_number <>", value, "viewNumber");
            return (Criteria) this;
        }

        public Criteria andViewNumberGreaterThan(Integer value) {
            addCriterion("view_number >", value, "viewNumber");
            return (Criteria) this;
        }

        public Criteria andViewNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("view_number >=", value, "viewNumber");
            return (Criteria) this;
        }

        public Criteria andViewNumberLessThan(Integer value) {
            addCriterion("view_number <", value, "viewNumber");
            return (Criteria) this;
        }

        public Criteria andViewNumberLessThanOrEqualTo(Integer value) {
            addCriterion("view_number <=", value, "viewNumber");
            return (Criteria) this;
        }

        public Criteria andViewNumberIn(List<Integer> values) {
            addCriterion("view_number in", values, "viewNumber");
            return (Criteria) this;
        }

        public Criteria andViewNumberNotIn(List<Integer> values) {
            addCriterion("view_number not in", values, "viewNumber");
            return (Criteria) this;
        }

        public Criteria andViewNumberBetween(Integer value1, Integer value2) {
            addCriterion("view_number between", value1, value2, "viewNumber");
            return (Criteria) this;
        }

        public Criteria andViewNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("view_number not between", value1, value2, "viewNumber");
            return (Criteria) this;
        }

        public Criteria andFavoriteNumberIsNull() {
            addCriterion("favorite_number is null");
            return (Criteria) this;
        }

        public Criteria andFavoriteNumberIsNotNull() {
            addCriterion("favorite_number is not null");
            return (Criteria) this;
        }

        public Criteria andFavoriteNumberEqualTo(Integer value) {
            addCriterion("favorite_number =", value, "favoriteNumber");
            return (Criteria) this;
        }

        public Criteria andFavoriteNumberNotEqualTo(Integer value) {
            addCriterion("favorite_number <>", value, "favoriteNumber");
            return (Criteria) this;
        }

        public Criteria andFavoriteNumberGreaterThan(Integer value) {
            addCriterion("favorite_number >", value, "favoriteNumber");
            return (Criteria) this;
        }

        public Criteria andFavoriteNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("favorite_number >=", value, "favoriteNumber");
            return (Criteria) this;
        }

        public Criteria andFavoriteNumberLessThan(Integer value) {
            addCriterion("favorite_number <", value, "favoriteNumber");
            return (Criteria) this;
        }

        public Criteria andFavoriteNumberLessThanOrEqualTo(Integer value) {
            addCriterion("favorite_number <=", value, "favoriteNumber");
            return (Criteria) this;
        }

        public Criteria andFavoriteNumberIn(List<Integer> values) {
            addCriterion("favorite_number in", values, "favoriteNumber");
            return (Criteria) this;
        }

        public Criteria andFavoriteNumberNotIn(List<Integer> values) {
            addCriterion("favorite_number not in", values, "favoriteNumber");
            return (Criteria) this;
        }

        public Criteria andFavoriteNumberBetween(Integer value1, Integer value2) {
            addCriterion("favorite_number between", value1, value2, "favoriteNumber");
            return (Criteria) this;
        }

        public Criteria andFavoriteNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("favorite_number not between", value1, value2, "favoriteNumber");
            return (Criteria) this;
        }

        public Criteria andIsPublishIsNull() {
            addCriterion("is_publish is null");
            return (Criteria) this;
        }

        public Criteria andIsPublishIsNotNull() {
            addCriterion("is_publish is not null");
            return (Criteria) this;
        }

        public Criteria andIsPublishEqualTo(Boolean value) {
            addCriterion("is_publish =", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotEqualTo(Boolean value) {
            addCriterion("is_publish <>", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishGreaterThan(Boolean value) {
            addCriterion("is_publish >", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_publish >=", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLessThan(Boolean value) {
            addCriterion("is_publish <", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLessThanOrEqualTo(Boolean value) {
            addCriterion("is_publish <=", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishIn(List<Boolean> values) {
            addCriterion("is_publish in", values, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotIn(List<Boolean> values) {
            addCriterion("is_publish not in", values, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishBetween(Boolean value1, Boolean value2) {
            addCriterion("is_publish between", value1, value2, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_publish not between", value1, value2, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsInviteIsNull() {
            addCriterion("is_invite is null");
            return (Criteria) this;
        }

        public Criteria andIsInviteIsNotNull() {
            addCriterion("is_invite is not null");
            return (Criteria) this;
        }

        public Criteria andIsInviteEqualTo(Boolean value) {
            addCriterion("is_invite =", value, "isInvite");
            return (Criteria) this;
        }

        public Criteria andIsInviteNotEqualTo(Boolean value) {
            addCriterion("is_invite <>", value, "isInvite");
            return (Criteria) this;
        }

        public Criteria andIsInviteGreaterThan(Boolean value) {
            addCriterion("is_invite >", value, "isInvite");
            return (Criteria) this;
        }

        public Criteria andIsInviteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_invite >=", value, "isInvite");
            return (Criteria) this;
        }

        public Criteria andIsInviteLessThan(Boolean value) {
            addCriterion("is_invite <", value, "isInvite");
            return (Criteria) this;
        }

        public Criteria andIsInviteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_invite <=", value, "isInvite");
            return (Criteria) this;
        }

        public Criteria andIsInviteIn(List<Boolean> values) {
            addCriterion("is_invite in", values, "isInvite");
            return (Criteria) this;
        }

        public Criteria andIsInviteNotIn(List<Boolean> values) {
            addCriterion("is_invite not in", values, "isInvite");
            return (Criteria) this;
        }

        public Criteria andIsInviteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_invite between", value1, value2, "isInvite");
            return (Criteria) this;
        }

        public Criteria andIsInviteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_invite not between", value1, value2, "isInvite");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIsNull() {
            addCriterion("change_status is null");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIsNotNull() {
            addCriterion("change_status is not null");
            return (Criteria) this;
        }

        public Criteria andChangeStatusEqualTo(Byte value) {
            addCriterion("change_status =", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNotEqualTo(Byte value) {
            addCriterion("change_status <>", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusGreaterThan(Byte value) {
            addCriterion("change_status >", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("change_status >=", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusLessThan(Byte value) {
            addCriterion("change_status <", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusLessThanOrEqualTo(Byte value) {
            addCriterion("change_status <=", value, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIn(List<Byte> values) {
            addCriterion("change_status in", values, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNotIn(List<Byte> values) {
            addCriterion("change_status not in", values, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusBetween(Byte value1, Byte value2) {
            addCriterion("change_status between", value1, value2, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("change_status not between", value1, value2, "changeStatus");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIsNull() {
            addCriterion("change_type is null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIsNotNull() {
            addCriterion("change_type is not null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeEqualTo(Byte value) {
            addCriterion("change_type =", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotEqualTo(Byte value) {
            addCriterion("change_type <>", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeGreaterThan(Byte value) {
            addCriterion("change_type >", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("change_type >=", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLessThan(Byte value) {
            addCriterion("change_type <", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeLessThanOrEqualTo(Byte value) {
            addCriterion("change_type <=", value, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIn(List<Byte> values) {
            addCriterion("change_type in", values, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotIn(List<Byte> values) {
            addCriterion("change_type not in", values, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeBetween(Byte value1, Byte value2) {
            addCriterion("change_type between", value1, value2, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("change_type not between", value1, value2, "changeType");
            return (Criteria) this;
        }

        public Criteria andChangeSubStatusIsNull() {
            addCriterion("change_sub_status is null");
            return (Criteria) this;
        }

        public Criteria andChangeSubStatusIsNotNull() {
            addCriterion("change_sub_status is not null");
            return (Criteria) this;
        }

        public Criteria andChangeSubStatusEqualTo(Short value) {
            addCriterion("change_sub_status =", value, "changeSubStatus");
            return (Criteria) this;
        }

        public Criteria andChangeSubStatusNotEqualTo(Short value) {
            addCriterion("change_sub_status <>", value, "changeSubStatus");
            return (Criteria) this;
        }

        public Criteria andChangeSubStatusGreaterThan(Short value) {
            addCriterion("change_sub_status >", value, "changeSubStatus");
            return (Criteria) this;
        }

        public Criteria andChangeSubStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("change_sub_status >=", value, "changeSubStatus");
            return (Criteria) this;
        }

        public Criteria andChangeSubStatusLessThan(Short value) {
            addCriterion("change_sub_status <", value, "changeSubStatus");
            return (Criteria) this;
        }

        public Criteria andChangeSubStatusLessThanOrEqualTo(Short value) {
            addCriterion("change_sub_status <=", value, "changeSubStatus");
            return (Criteria) this;
        }

        public Criteria andChangeSubStatusIn(List<Short> values) {
            addCriterion("change_sub_status in", values, "changeSubStatus");
            return (Criteria) this;
        }

        public Criteria andChangeSubStatusNotIn(List<Short> values) {
            addCriterion("change_sub_status not in", values, "changeSubStatus");
            return (Criteria) this;
        }

        public Criteria andChangeSubStatusBetween(Short value1, Short value2) {
            addCriterion("change_sub_status between", value1, value2, "changeSubStatus");
            return (Criteria) this;
        }

        public Criteria andChangeSubStatusNotBetween(Short value1, Short value2) {
            addCriterion("change_sub_status not between", value1, value2, "changeSubStatus");
            return (Criteria) this;
        }

        public Criteria andEnterTimeIsNull() {
            addCriterion("enter_time is null");
            return (Criteria) this;
        }

        public Criteria andEnterTimeIsNotNull() {
            addCriterion("enter_time is not null");
            return (Criteria) this;
        }

        public Criteria andEnterTimeEqualTo(Date value) {
            addCriterion("enter_time =", value, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeNotEqualTo(Date value) {
            addCriterion("enter_time <>", value, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeGreaterThan(Date value) {
            addCriterion("enter_time >", value, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("enter_time >=", value, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeLessThan(Date value) {
            addCriterion("enter_time <", value, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeLessThanOrEqualTo(Date value) {
            addCriterion("enter_time <=", value, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeIn(List<Date> values) {
            addCriterion("enter_time in", values, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeNotIn(List<Date> values) {
            addCriterion("enter_time not in", values, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeBetween(Date value1, Date value2) {
            addCriterion("enter_time between", value1, value2, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeNotBetween(Date value1, Date value2) {
            addCriterion("enter_time not between", value1, value2, "enterTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNull() {
            addCriterion("finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("finish_time =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("finish_time <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("finish_time >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_time >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("finish_time <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("finish_time <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("finish_time in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("finish_time not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("finish_time between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("finish_time not between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andPayTimesIsNull() {
            addCriterion("pay_times is null");
            return (Criteria) this;
        }

        public Criteria andPayTimesIsNotNull() {
            addCriterion("pay_times is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimesEqualTo(Byte value) {
            addCriterion("pay_times =", value, "payTimes");
            return (Criteria) this;
        }

        public Criteria andPayTimesNotEqualTo(Byte value) {
            addCriterion("pay_times <>", value, "payTimes");
            return (Criteria) this;
        }

        public Criteria andPayTimesGreaterThan(Byte value) {
            addCriterion("pay_times >", value, "payTimes");
            return (Criteria) this;
        }

        public Criteria andPayTimesGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_times >=", value, "payTimes");
            return (Criteria) this;
        }

        public Criteria andPayTimesLessThan(Byte value) {
            addCriterion("pay_times <", value, "payTimes");
            return (Criteria) this;
        }

        public Criteria andPayTimesLessThanOrEqualTo(Byte value) {
            addCriterion("pay_times <=", value, "payTimes");
            return (Criteria) this;
        }

        public Criteria andPayTimesIn(List<Byte> values) {
            addCriterion("pay_times in", values, "payTimes");
            return (Criteria) this;
        }

        public Criteria andPayTimesNotIn(List<Byte> values) {
            addCriterion("pay_times not in", values, "payTimes");
            return (Criteria) this;
        }

        public Criteria andPayTimesBetween(Byte value1, Byte value2) {
            addCriterion("pay_times between", value1, value2, "payTimes");
            return (Criteria) this;
        }

        public Criteria andPayTimesNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_times not between", value1, value2, "payTimes");
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