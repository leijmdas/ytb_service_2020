package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InvoiceInfoProjectExample extends Ytb_ModelSkipNull {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public InvoiceInfoProjectExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdIsNull() {
            addCriterion("invoice_id is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdIsNotNull() {
            addCriterion("invoice_id is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdEqualTo(Integer value) {
            addCriterion("invoice_id =", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdNotEqualTo(Integer value) {
            addCriterion("invoice_id <>", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdGreaterThan(Integer value) {
            addCriterion("invoice_id >", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_id >=", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdLessThan(Integer value) {
            addCriterion("invoice_id <", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_id <=", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdIn(List<Integer> values) {
            addCriterion("invoice_id in", values, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdNotIn(List<Integer> values) {
            addCriterion("invoice_id not in", values, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdBetween(Integer value1, Integer value2) {
            addCriterion("invoice_id between", value1, value2, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_id not between", value1, value2, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andPostalAddressIsNull() {
            addCriterion("postal_address is null");
            return (Criteria) this;
        }

        public Criteria andPostalAddressIsNotNull() {
            addCriterion("postal_address is not null");
            return (Criteria) this;
        }

        public Criteria andPostalAddressEqualTo(String value) {
            addCriterion("postal_address =", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressNotEqualTo(String value) {
            addCriterion("postal_address <>", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressGreaterThan(String value) {
            addCriterion("postal_address >", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressGreaterThanOrEqualTo(String value) {
            addCriterion("postal_address >=", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressLessThan(String value) {
            addCriterion("postal_address <", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressLessThanOrEqualTo(String value) {
            addCriterion("postal_address <=", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressLike(String value) {
            addCriterion("postal_address like", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressNotLike(String value) {
            addCriterion("postal_address not like", value, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressIn(List<String> values) {
            addCriterion("postal_address in", values, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressNotIn(List<String> values) {
            addCriterion("postal_address not in", values, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressBetween(String value1, String value2) {
            addCriterion("postal_address between", value1, value2, "postalAddress");
            return (Criteria) this;
        }

        public Criteria andPostalAddressNotBetween(String value1, String value2) {
            addCriterion("postal_address not between", value1, value2, "postalAddress");
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

        public Criteria andBillingStatusIsNull() {
            addCriterion("billing_status is null");
            return (Criteria) this;
        }

        public Criteria andBillingStatusIsNotNull() {
            addCriterion("billing_status is not null");
            return (Criteria) this;
        }

        public Criteria andBillingStatusEqualTo(Integer value) {
            addCriterion("billing_status =", value, "billingStatus");
            return (Criteria) this;
        }

        public Criteria andBillingStatusNotEqualTo(Integer value) {
            addCriterion("billing_status <>", value, "billingStatus");
            return (Criteria) this;
        }

        public Criteria andBillingStatusGreaterThan(Integer value) {
            addCriterion("billing_status >", value, "billingStatus");
            return (Criteria) this;
        }

        public Criteria andBillingStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("billing_status >=", value, "billingStatus");
            return (Criteria) this;
        }

        public Criteria andBillingStatusLessThan(Integer value) {
            addCriterion("billing_status <", value, "billingStatus");
            return (Criteria) this;
        }

        public Criteria andBillingStatusLessThanOrEqualTo(Integer value) {
            addCriterion("billing_status <=", value, "billingStatus");
            return (Criteria) this;
        }

        public Criteria andBillingStatusIn(List<Integer> values) {
            addCriterion("billing_status in", values, "billingStatus");
            return (Criteria) this;
        }

        public Criteria andBillingStatusNotIn(List<Integer> values) {
            addCriterion("billing_status not in", values, "billingStatus");
            return (Criteria) this;
        }

        public Criteria andBillingStatusBetween(Integer value1, Integer value2) {
            addCriterion("billing_status between", value1, value2, "billingStatus");
            return (Criteria) this;
        }

        public Criteria andBillingStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("billing_status not between", value1, value2, "billingStatus");
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
            addCriterionForJDBCDate("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterionForJDBCDate("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterionForJDBCDate("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNull() {
            addCriterion("invoice_type is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNotNull() {
            addCriterion("invoice_type is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeEqualTo(Integer value) {
            addCriterion("invoice_type =", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotEqualTo(Integer value) {
            addCriterion("invoice_type <>", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThan(Integer value) {
            addCriterion("invoice_type >", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_type >=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThan(Integer value) {
            addCriterion("invoice_type <", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_type <=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIn(List<Integer> values) {
            addCriterion("invoice_type in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotIn(List<Integer> values) {
            addCriterion("invoice_type not in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeBetween(Integer value1, Integer value2) {
            addCriterion("invoice_type between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_type not between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIsNull() {
            addCriterion("express_company is null");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIsNotNull() {
            addCriterion("express_company is not null");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyEqualTo(String value) {
            addCriterion("express_company =", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotEqualTo(String value) {
            addCriterion("express_company <>", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyGreaterThan(String value) {
            addCriterion("express_company >", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("express_company >=", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyLessThan(String value) {
            addCriterion("express_company <", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyLessThanOrEqualTo(String value) {
            addCriterion("express_company <=", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyLike(String value) {
            addCriterion("express_company like", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotLike(String value) {
            addCriterion("express_company not like", value, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIn(List<String> values) {
            addCriterion("express_company in", values, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotIn(List<String> values) {
            addCriterion("express_company not in", values, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyBetween(String value1, String value2) {
            addCriterion("express_company between", value1, value2, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyNotBetween(String value1, String value2) {
            addCriterion("express_company not between", value1, value2, "expressCompany");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdIsNull() {
            addCriterion("express_company_id is null");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdIsNotNull() {
            addCriterion("express_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdEqualTo(String value) {
            addCriterion("express_company_id =", value, "expressCompanyId");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdNotEqualTo(String value) {
            addCriterion("express_company_id <>", value, "expressCompanyId");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdGreaterThan(String value) {
            addCriterion("express_company_id >", value, "expressCompanyId");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("express_company_id >=", value, "expressCompanyId");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdLessThan(String value) {
            addCriterion("express_company_id <", value, "expressCompanyId");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("express_company_id <=", value, "expressCompanyId");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdLike(String value) {
            addCriterion("express_company_id like", value, "expressCompanyId");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdNotLike(String value) {
            addCriterion("express_company_id not like", value, "expressCompanyId");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdIn(List<String> values) {
            addCriterion("express_company_id in", values, "expressCompanyId");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdNotIn(List<String> values) {
            addCriterion("express_company_id not in", values, "expressCompanyId");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdBetween(String value1, String value2) {
            addCriterion("express_company_id between", value1, value2, "expressCompanyId");
            return (Criteria) this;
        }

        public Criteria andExpressCompanyIdNotBetween(String value1, String value2) {
            addCriterion("express_company_id not between", value1, value2, "expressCompanyId");
            return (Criteria) this;
        }

        public Criteria andAddresseeIsNull() {
            addCriterion("addressee is null");
            return (Criteria) this;
        }

        public Criteria andAddresseeIsNotNull() {
            addCriterion("addressee is not null");
            return (Criteria) this;
        }

        public Criteria andAddresseeEqualTo(String value) {
            addCriterion("addressee =", value, "addressee");
            return (Criteria) this;
        }

        public Criteria andAddresseeNotEqualTo(String value) {
            addCriterion("addressee <>", value, "addressee");
            return (Criteria) this;
        }

        public Criteria andAddresseeGreaterThan(String value) {
            addCriterion("addressee >", value, "addressee");
            return (Criteria) this;
        }

        public Criteria andAddresseeGreaterThanOrEqualTo(String value) {
            addCriterion("addressee >=", value, "addressee");
            return (Criteria) this;
        }

        public Criteria andAddresseeLessThan(String value) {
            addCriterion("addressee <", value, "addressee");
            return (Criteria) this;
        }

        public Criteria andAddresseeLessThanOrEqualTo(String value) {
            addCriterion("addressee <=", value, "addressee");
            return (Criteria) this;
        }

        public Criteria andAddresseeLike(String value) {
            addCriterion("addressee like", value, "addressee");
            return (Criteria) this;
        }

        public Criteria andAddresseeNotLike(String value) {
            addCriterion("addressee not like", value, "addressee");
            return (Criteria) this;
        }

        public Criteria andAddresseeIn(List<String> values) {
            addCriterion("addressee in", values, "addressee");
            return (Criteria) this;
        }

        public Criteria andAddresseeNotIn(List<String> values) {
            addCriterion("addressee not in", values, "addressee");
            return (Criteria) this;
        }

        public Criteria andAddresseeBetween(String value1, String value2) {
            addCriterion("addressee between", value1, value2, "addressee");
            return (Criteria) this;
        }

        public Criteria andAddresseeNotBetween(String value1, String value2) {
            addCriterion("addressee not between", value1, value2, "addressee");
            return (Criteria) this;
        }

        public Criteria andContactNumberIsNull() {
            addCriterion("contact_number is null");
            return (Criteria) this;
        }

        public Criteria andContactNumberIsNotNull() {
            addCriterion("contact_number is not null");
            return (Criteria) this;
        }

        public Criteria andContactNumberEqualTo(String value) {
            addCriterion("contact_number =", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotEqualTo(String value) {
            addCriterion("contact_number <>", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberGreaterThan(String value) {
            addCriterion("contact_number >", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberGreaterThanOrEqualTo(String value) {
            addCriterion("contact_number >=", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberLessThan(String value) {
            addCriterion("contact_number <", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberLessThanOrEqualTo(String value) {
            addCriterion("contact_number <=", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberLike(String value) {
            addCriterion("contact_number like", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotLike(String value) {
            addCriterion("contact_number not like", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberIn(List<String> values) {
            addCriterion("contact_number in", values, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotIn(List<String> values) {
            addCriterion("contact_number not in", values, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberBetween(String value1, String value2) {
            addCriterion("contact_number between", value1, value2, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotBetween(String value1, String value2) {
            addCriterion("contact_number not between", value1, value2, "contactNumber");
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