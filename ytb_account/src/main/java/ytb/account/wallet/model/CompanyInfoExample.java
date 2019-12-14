package ytb.account.wallet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompanyInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public CompanyInfoExample() {
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

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyUserIdIsNull() {
            addCriterion("company_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyUserIdIsNotNull() {
            addCriterion("company_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyUserIdEqualTo(Integer value) {
            addCriterion("company_user_id =", value, "companyUserId");
            return (Criteria) this;
        }

        public Criteria andCompanyUserIdNotEqualTo(Integer value) {
            addCriterion("company_user_id <>", value, "companyUserId");
            return (Criteria) this;
        }

        public Criteria andCompanyUserIdGreaterThan(Integer value) {
            addCriterion("company_user_id >", value, "companyUserId");
            return (Criteria) this;
        }

        public Criteria andCompanyUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_user_id >=", value, "companyUserId");
            return (Criteria) this;
        }

        public Criteria andCompanyUserIdLessThan(Integer value) {
            addCriterion("company_user_id <", value, "companyUserId");
            return (Criteria) this;
        }

        public Criteria andCompanyUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("company_user_id <=", value, "companyUserId");
            return (Criteria) this;
        }

        public Criteria andCompanyUserIdIn(List<Integer> values) {
            addCriterion("company_user_id in", values, "companyUserId");
            return (Criteria) this;
        }

        public Criteria andCompanyUserIdNotIn(List<Integer> values) {
            addCriterion("company_user_id not in", values, "companyUserId");
            return (Criteria) this;
        }

        public Criteria andCompanyUserIdBetween(Integer value1, Integer value2) {
            addCriterion("company_user_id between", value1, value2, "companyUserId");
            return (Criteria) this;
        }

        public Criteria andCompanyUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("company_user_id not between", value1, value2, "companyUserId");
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

        public Criteria andCompanyFlagIsNull() {
            addCriterion("company_flag is null");
            return (Criteria) this;
        }

        public Criteria andCompanyFlagIsNotNull() {
            addCriterion("company_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyFlagEqualTo(Byte value) {
            addCriterion("company_flag =", value, "companyFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyFlagNotEqualTo(Byte value) {
            addCriterion("company_flag <>", value, "companyFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyFlagGreaterThan(Byte value) {
            addCriterion("company_flag >", value, "companyFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("company_flag >=", value, "companyFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyFlagLessThan(Byte value) {
            addCriterion("company_flag <", value, "companyFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyFlagLessThanOrEqualTo(Byte value) {
            addCriterion("company_flag <=", value, "companyFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyFlagIn(List<Byte> values) {
            addCriterion("company_flag in", values, "companyFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyFlagNotIn(List<Byte> values) {
            addCriterion("company_flag not in", values, "companyFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyFlagBetween(Byte value1, Byte value2) {
            addCriterion("company_flag between", value1, value2, "companyFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("company_flag not between", value1, value2, "companyFlag");
            return (Criteria) this;
        }

        public Criteria andContactsIsNull() {
            addCriterion("contacts is null");
            return (Criteria) this;
        }

        public Criteria andContactsIsNotNull() {
            addCriterion("contacts is not null");
            return (Criteria) this;
        }

        public Criteria andContactsEqualTo(String value) {
            addCriterion("contacts =", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotEqualTo(String value) {
            addCriterion("contacts <>", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsGreaterThan(String value) {
            addCriterion("contacts >", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsGreaterThanOrEqualTo(String value) {
            addCriterion("contacts >=", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLessThan(String value) {
            addCriterion("contacts <", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLessThanOrEqualTo(String value) {
            addCriterion("contacts <=", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLike(String value) {
            addCriterion("contacts like", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotLike(String value) {
            addCriterion("contacts not like", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsIn(List<String> values) {
            addCriterion("contacts in", values, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotIn(List<String> values) {
            addCriterion("contacts not in", values, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsBetween(String value1, String value2) {
            addCriterion("contacts between", value1, value2, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotBetween(String value1, String value2) {
            addCriterion("contacts not between", value1, value2, "contacts");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNull() {
            addCriterion("phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberEqualTo(String value) {
            addCriterion("phone_number =", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotEqualTo(String value) {
            addCriterion("phone_number <>", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThan(String value) {
            addCriterion("phone_number >", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("phone_number >=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThan(String value) {
            addCriterion("phone_number <", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("phone_number <=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLike(String value) {
            addCriterion("phone_number like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotLike(String value) {
            addCriterion("phone_number not like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIn(List<String> values) {
            addCriterion("phone_number in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotIn(List<String> values) {
            addCriterion("phone_number not in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberBetween(String value1, String value2) {
            addCriterion("phone_number between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("phone_number not between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeIsNull() {
            addCriterion("license_code is null");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeIsNotNull() {
            addCriterion("license_code is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeEqualTo(String value) {
            addCriterion("license_code =", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeNotEqualTo(String value) {
            addCriterion("license_code <>", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeGreaterThan(String value) {
            addCriterion("license_code >", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("license_code >=", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeLessThan(String value) {
            addCriterion("license_code <", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeLessThanOrEqualTo(String value) {
            addCriterion("license_code <=", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeLike(String value) {
            addCriterion("license_code like", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeNotLike(String value) {
            addCriterion("license_code not like", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeIn(List<String> values) {
            addCriterion("license_code in", values, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeNotIn(List<String> values) {
            addCriterion("license_code not in", values, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeBetween(String value1, String value2) {
            addCriterion("license_code between", value1, value2, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeNotBetween(String value1, String value2) {
            addCriterion("license_code not between", value1, value2, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlIsNull() {
            addCriterion("license_url is null");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlIsNotNull() {
            addCriterion("license_url is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlEqualTo(String value) {
            addCriterion("license_url =", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlNotEqualTo(String value) {
            addCriterion("license_url <>", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlGreaterThan(String value) {
            addCriterion("license_url >", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlGreaterThanOrEqualTo(String value) {
            addCriterion("license_url >=", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlLessThan(String value) {
            addCriterion("license_url <", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlLessThanOrEqualTo(String value) {
            addCriterion("license_url <=", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlLike(String value) {
            addCriterion("license_url like", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlNotLike(String value) {
            addCriterion("license_url not like", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlIn(List<String> values) {
            addCriterion("license_url in", values, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlNotIn(List<String> values) {
            addCriterion("license_url not in", values, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlBetween(String value1, String value2) {
            addCriterion("license_url between", value1, value2, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlNotBetween(String value1, String value2) {
            addCriterion("license_url not between", value1, value2, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andIdeaIsNull() {
            addCriterion("idea is null");
            return (Criteria) this;
        }

        public Criteria andIdeaIsNotNull() {
            addCriterion("idea is not null");
            return (Criteria) this;
        }

        public Criteria andIdeaEqualTo(String value) {
            addCriterion("idea =", value, "idea");
            return (Criteria) this;
        }

        public Criteria andIdeaNotEqualTo(String value) {
            addCriterion("idea <>", value, "idea");
            return (Criteria) this;
        }

        public Criteria andIdeaGreaterThan(String value) {
            addCriterion("idea >", value, "idea");
            return (Criteria) this;
        }

        public Criteria andIdeaGreaterThanOrEqualTo(String value) {
            addCriterion("idea >=", value, "idea");
            return (Criteria) this;
        }

        public Criteria andIdeaLessThan(String value) {
            addCriterion("idea <", value, "idea");
            return (Criteria) this;
        }

        public Criteria andIdeaLessThanOrEqualTo(String value) {
            addCriterion("idea <=", value, "idea");
            return (Criteria) this;
        }

        public Criteria andIdeaLike(String value) {
            addCriterion("idea like", value, "idea");
            return (Criteria) this;
        }

        public Criteria andIdeaNotLike(String value) {
            addCriterion("idea not like", value, "idea");
            return (Criteria) this;
        }

        public Criteria andIdeaIn(List<String> values) {
            addCriterion("idea in", values, "idea");
            return (Criteria) this;
        }

        public Criteria andIdeaNotIn(List<String> values) {
            addCriterion("idea not in", values, "idea");
            return (Criteria) this;
        }

        public Criteria andIdeaBetween(String value1, String value2) {
            addCriterion("idea between", value1, value2, "idea");
            return (Criteria) this;
        }

        public Criteria andIdeaNotBetween(String value1, String value2) {
            addCriterion("idea not between", value1, value2, "idea");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andCollegeNumberIsNull() {
            addCriterion("college_number is null");
            return (Criteria) this;
        }

        public Criteria andCollegeNumberIsNotNull() {
            addCriterion("college_number is not null");
            return (Criteria) this;
        }

        public Criteria andCollegeNumberEqualTo(Integer value) {
            addCriterion("college_number =", value, "collegeNumber");
            return (Criteria) this;
        }

        public Criteria andCollegeNumberNotEqualTo(Integer value) {
            addCriterion("college_number <>", value, "collegeNumber");
            return (Criteria) this;
        }

        public Criteria andCollegeNumberGreaterThan(Integer value) {
            addCriterion("college_number >", value, "collegeNumber");
            return (Criteria) this;
        }

        public Criteria andCollegeNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("college_number >=", value, "collegeNumber");
            return (Criteria) this;
        }

        public Criteria andCollegeNumberLessThan(Integer value) {
            addCriterion("college_number <", value, "collegeNumber");
            return (Criteria) this;
        }

        public Criteria andCollegeNumberLessThanOrEqualTo(Integer value) {
            addCriterion("college_number <=", value, "collegeNumber");
            return (Criteria) this;
        }

        public Criteria andCollegeNumberIn(List<Integer> values) {
            addCriterion("college_number in", values, "collegeNumber");
            return (Criteria) this;
        }

        public Criteria andCollegeNumberNotIn(List<Integer> values) {
            addCriterion("college_number not in", values, "collegeNumber");
            return (Criteria) this;
        }

        public Criteria andCollegeNumberBetween(Integer value1, Integer value2) {
            addCriterion("college_number between", value1, value2, "collegeNumber");
            return (Criteria) this;
        }

        public Criteria andCollegeNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("college_number not between", value1, value2, "collegeNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberIsNull() {
            addCriterion("graduate_number is null");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberIsNotNull() {
            addCriterion("graduate_number is not null");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberEqualTo(Integer value) {
            addCriterion("graduate_number =", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberNotEqualTo(Integer value) {
            addCriterion("graduate_number <>", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberGreaterThan(Integer value) {
            addCriterion("graduate_number >", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("graduate_number >=", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberLessThan(Integer value) {
            addCriterion("graduate_number <", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberLessThanOrEqualTo(Integer value) {
            addCriterion("graduate_number <=", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberIn(List<Integer> values) {
            addCriterion("graduate_number in", values, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberNotIn(List<Integer> values) {
            addCriterion("graduate_number not in", values, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberBetween(Integer value1, Integer value2) {
            addCriterion("graduate_number between", value1, value2, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("graduate_number not between", value1, value2, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andOtherNumberIsNull() {
            addCriterion("other_number is null");
            return (Criteria) this;
        }

        public Criteria andOtherNumberIsNotNull() {
            addCriterion("other_number is not null");
            return (Criteria) this;
        }

        public Criteria andOtherNumberEqualTo(Integer value) {
            addCriterion("other_number =", value, "otherNumber");
            return (Criteria) this;
        }

        public Criteria andOtherNumberNotEqualTo(Integer value) {
            addCriterion("other_number <>", value, "otherNumber");
            return (Criteria) this;
        }

        public Criteria andOtherNumberGreaterThan(Integer value) {
            addCriterion("other_number >", value, "otherNumber");
            return (Criteria) this;
        }

        public Criteria andOtherNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("other_number >=", value, "otherNumber");
            return (Criteria) this;
        }

        public Criteria andOtherNumberLessThan(Integer value) {
            addCriterion("other_number <", value, "otherNumber");
            return (Criteria) this;
        }

        public Criteria andOtherNumberLessThanOrEqualTo(Integer value) {
            addCriterion("other_number <=", value, "otherNumber");
            return (Criteria) this;
        }

        public Criteria andOtherNumberIn(List<Integer> values) {
            addCriterion("other_number in", values, "otherNumber");
            return (Criteria) this;
        }

        public Criteria andOtherNumberNotIn(List<Integer> values) {
            addCriterion("other_number not in", values, "otherNumber");
            return (Criteria) this;
        }

        public Criteria andOtherNumberBetween(Integer value1, Integer value2) {
            addCriterion("other_number between", value1, value2, "otherNumber");
            return (Criteria) this;
        }

        public Criteria andOtherNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("other_number not between", value1, value2, "otherNumber");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeIsNull() {
            addCriterion("company_grade is null");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeIsNotNull() {
            addCriterion("company_grade is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeEqualTo(String value) {
            addCriterion("company_grade =", value, "companyGrade");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeNotEqualTo(String value) {
            addCriterion("company_grade <>", value, "companyGrade");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeGreaterThan(String value) {
            addCriterion("company_grade >", value, "companyGrade");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeGreaterThanOrEqualTo(String value) {
            addCriterion("company_grade >=", value, "companyGrade");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeLessThan(String value) {
            addCriterion("company_grade <", value, "companyGrade");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeLessThanOrEqualTo(String value) {
            addCriterion("company_grade <=", value, "companyGrade");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeLike(String value) {
            addCriterion("company_grade like", value, "companyGrade");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeNotLike(String value) {
            addCriterion("company_grade not like", value, "companyGrade");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeIn(List<String> values) {
            addCriterion("company_grade in", values, "companyGrade");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeNotIn(List<String> values) {
            addCriterion("company_grade not in", values, "companyGrade");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeBetween(String value1, String value2) {
            addCriterion("company_grade between", value1, value2, "companyGrade");
            return (Criteria) this;
        }

        public Criteria andCompanyGradeNotBetween(String value1, String value2) {
            addCriterion("company_grade not between", value1, value2, "companyGrade");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIsNull() {
            addCriterion("company_type is null");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIsNotNull() {
            addCriterion("company_type is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeEqualTo(Integer value) {
            addCriterion("company_type =", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotEqualTo(Integer value) {
            addCriterion("company_type <>", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeGreaterThan(Integer value) {
            addCriterion("company_type >", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_type >=", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLessThan(Integer value) {
            addCriterion("company_type <", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLessThanOrEqualTo(Integer value) {
            addCriterion("company_type <=", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIn(List<Integer> values) {
            addCriterion("company_type in", values, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotIn(List<Integer> values) {
            addCriterion("company_type not in", values, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeBetween(Integer value1, Integer value2) {
            addCriterion("company_type between", value1, value2, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("company_type not between", value1, value2, "companyType");
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