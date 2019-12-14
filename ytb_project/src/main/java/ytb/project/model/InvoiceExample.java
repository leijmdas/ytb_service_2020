package ytb.project.model;

import ytb.common.context.model.Ytb_ModelSkipNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InvoiceExample  extends Ytb_ModelSkipNull {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public InvoiceExample() {
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

        public Criteria andQrCodeIsNull() {
            addCriterion("qr_code is null");
            return (Criteria) this;
        }

        public Criteria andQrCodeIsNotNull() {
            addCriterion("qr_code is not null");
            return (Criteria) this;
        }

        public Criteria andQrCodeEqualTo(String value) {
            addCriterion("qr_code =", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotEqualTo(String value) {
            addCriterion("qr_code <>", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeGreaterThan(String value) {
            addCriterion("qr_code >", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeGreaterThanOrEqualTo(String value) {
            addCriterion("qr_code >=", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLessThan(String value) {
            addCriterion("qr_code <", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLessThanOrEqualTo(String value) {
            addCriterion("qr_code <=", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLike(String value) {
            addCriterion("qr_code like", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotLike(String value) {
            addCriterion("qr_code not like", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeIn(List<String> values) {
            addCriterion("qr_code in", values, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotIn(List<String> values) {
            addCriterion("qr_code not in", values, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeBetween(String value1, String value2) {
            addCriterion("qr_code between", value1, value2, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotBetween(String value1, String value2) {
            addCriterion("qr_code not between", value1, value2, "qrCode");
            return (Criteria) this;
        }

        public Criteria andMachineNumberIsNull() {
            addCriterion("machine_number is null");
            return (Criteria) this;
        }

        public Criteria andMachineNumberIsNotNull() {
            addCriterion("machine_number is not null");
            return (Criteria) this;
        }

        public Criteria andMachineNumberEqualTo(Integer value) {
            addCriterion("machine_number =", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberNotEqualTo(Integer value) {
            addCriterion("machine_number <>", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberGreaterThan(Integer value) {
            addCriterion("machine_number >", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("machine_number >=", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberLessThan(Integer value) {
            addCriterion("machine_number <", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberLessThanOrEqualTo(Integer value) {
            addCriterion("machine_number <=", value, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberIn(List<Integer> values) {
            addCriterion("machine_number in", values, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberNotIn(List<Integer> values) {
            addCriterion("machine_number not in", values, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberBetween(Integer value1, Integer value2) {
            addCriterion("machine_number between", value1, value2, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andMachineNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("machine_number not between", value1, value2, "machineNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIsNull() {
            addCriterion("invoice_code is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIsNotNull() {
            addCriterion("invoice_code is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeEqualTo(String value) {
            addCriterion("invoice_code =", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotEqualTo(String value) {
            addCriterion("invoice_code <>", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeGreaterThan(String value) {
            addCriterion("invoice_code >", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_code >=", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLessThan(String value) {
            addCriterion("invoice_code <", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLessThanOrEqualTo(String value) {
            addCriterion("invoice_code <=", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLike(String value) {
            addCriterion("invoice_code like", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotLike(String value) {
            addCriterion("invoice_code not like", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIn(List<String> values) {
            addCriterion("invoice_code in", values, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotIn(List<String> values) {
            addCriterion("invoice_code not in", values, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeBetween(String value1, String value2) {
            addCriterion("invoice_code between", value1, value2, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotBetween(String value1, String value2) {
            addCriterion("invoice_code not between", value1, value2, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberIsNull() {
            addCriterion("invoice_number is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberIsNotNull() {
            addCriterion("invoice_number is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberEqualTo(String value) {
            addCriterion("invoice_number =", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberNotEqualTo(String value) {
            addCriterion("invoice_number <>", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberGreaterThan(String value) {
            addCriterion("invoice_number >", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_number >=", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberLessThan(String value) {
            addCriterion("invoice_number <", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberLessThanOrEqualTo(String value) {
            addCriterion("invoice_number <=", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberLike(String value) {
            addCriterion("invoice_number like", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberNotLike(String value) {
            addCriterion("invoice_number not like", value, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberIn(List<String> values) {
            addCriterion("invoice_number in", values, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberNotIn(List<String> values) {
            addCriterion("invoice_number not in", values, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberBetween(String value1, String value2) {
            addCriterion("invoice_number between", value1, value2, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andInvoiceNumberNotBetween(String value1, String value2) {
            addCriterion("invoice_number not between", value1, value2, "invoiceNumber");
            return (Criteria) this;
        }

        public Criteria andDateOfInvoiceIsNull() {
            addCriterion("date_of_invoice is null");
            return (Criteria) this;
        }

        public Criteria andDateOfInvoiceIsNotNull() {
            addCriterion("date_of_invoice is not null");
            return (Criteria) this;
        }

        public Criteria andDateOfInvoiceEqualTo(Date value) {
            addCriterionForJDBCDate("date_of_invoice =", value, "dateOfInvoice");
            return (Criteria) this;
        }

        public Criteria andDateOfInvoiceNotEqualTo(Date value) {
            addCriterionForJDBCDate("date_of_invoice <>", value, "dateOfInvoice");
            return (Criteria) this;
        }

        public Criteria andDateOfInvoiceGreaterThan(Date value) {
            addCriterionForJDBCDate("date_of_invoice >", value, "dateOfInvoice");
            return (Criteria) this;
        }

        public Criteria andDateOfInvoiceGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date_of_invoice >=", value, "dateOfInvoice");
            return (Criteria) this;
        }

        public Criteria andDateOfInvoiceLessThan(Date value) {
            addCriterionForJDBCDate("date_of_invoice <", value, "dateOfInvoice");
            return (Criteria) this;
        }

        public Criteria andDateOfInvoiceLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date_of_invoice <=", value, "dateOfInvoice");
            return (Criteria) this;
        }

        public Criteria andDateOfInvoiceIn(List<Date> values) {
            addCriterionForJDBCDate("date_of_invoice in", values, "dateOfInvoice");
            return (Criteria) this;
        }

        public Criteria andDateOfInvoiceNotIn(List<Date> values) {
            addCriterionForJDBCDate("date_of_invoice not in", values, "dateOfInvoice");
            return (Criteria) this;
        }

        public Criteria andDateOfInvoiceBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date_of_invoice between", value1, value2, "dateOfInvoice");
            return (Criteria) this;
        }

        public Criteria andDateOfInvoiceNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date_of_invoice not between", value1, value2, "dateOfInvoice");
            return (Criteria) this;
        }

        public Criteria andCheckCodeIsNull() {
            addCriterion("check_code is null");
            return (Criteria) this;
        }

        public Criteria andCheckCodeIsNotNull() {
            addCriterion("check_code is not null");
            return (Criteria) this;
        }

        public Criteria andCheckCodeEqualTo(String value) {
            addCriterion("check_code =", value, "checkCode");
            return (Criteria) this;
        }

        public Criteria andCheckCodeNotEqualTo(String value) {
            addCriterion("check_code <>", value, "checkCode");
            return (Criteria) this;
        }

        public Criteria andCheckCodeGreaterThan(String value) {
            addCriterion("check_code >", value, "checkCode");
            return (Criteria) this;
        }

        public Criteria andCheckCodeGreaterThanOrEqualTo(String value) {
            addCriterion("check_code >=", value, "checkCode");
            return (Criteria) this;
        }

        public Criteria andCheckCodeLessThan(String value) {
            addCriterion("check_code <", value, "checkCode");
            return (Criteria) this;
        }

        public Criteria andCheckCodeLessThanOrEqualTo(String value) {
            addCriterion("check_code <=", value, "checkCode");
            return (Criteria) this;
        }

        public Criteria andCheckCodeLike(String value) {
            addCriterion("check_code like", value, "checkCode");
            return (Criteria) this;
        }

        public Criteria andCheckCodeNotLike(String value) {
            addCriterion("check_code not like", value, "checkCode");
            return (Criteria) this;
        }

        public Criteria andCheckCodeIn(List<String> values) {
            addCriterion("check_code in", values, "checkCode");
            return (Criteria) this;
        }

        public Criteria andCheckCodeNotIn(List<String> values) {
            addCriterion("check_code not in", values, "checkCode");
            return (Criteria) this;
        }

        public Criteria andCheckCodeBetween(String value1, String value2) {
            addCriterion("check_code between", value1, value2, "checkCode");
            return (Criteria) this;
        }

        public Criteria andCheckCodeNotBetween(String value1, String value2) {
            addCriterion("check_code not between", value1, value2, "checkCode");
            return (Criteria) this;
        }

        public Criteria andBuyNameIsNull() {
            addCriterion("buy_name is null");
            return (Criteria) this;
        }

        public Criteria andBuyNameIsNotNull() {
            addCriterion("buy_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuyNameEqualTo(String value) {
            addCriterion("buy_name =", value, "buyName");
            return (Criteria) this;
        }

        public Criteria andBuyNameNotEqualTo(String value) {
            addCriterion("buy_name <>", value, "buyName");
            return (Criteria) this;
        }

        public Criteria andBuyNameGreaterThan(String value) {
            addCriterion("buy_name >", value, "buyName");
            return (Criteria) this;
        }

        public Criteria andBuyNameGreaterThanOrEqualTo(String value) {
            addCriterion("buy_name >=", value, "buyName");
            return (Criteria) this;
        }

        public Criteria andBuyNameLessThan(String value) {
            addCriterion("buy_name <", value, "buyName");
            return (Criteria) this;
        }

        public Criteria andBuyNameLessThanOrEqualTo(String value) {
            addCriterion("buy_name <=", value, "buyName");
            return (Criteria) this;
        }

        public Criteria andBuyNameLike(String value) {
            addCriterion("buy_name like", value, "buyName");
            return (Criteria) this;
        }

        public Criteria andBuyNameNotLike(String value) {
            addCriterion("buy_name not like", value, "buyName");
            return (Criteria) this;
        }

        public Criteria andBuyNameIn(List<String> values) {
            addCriterion("buy_name in", values, "buyName");
            return (Criteria) this;
        }

        public Criteria andBuyNameNotIn(List<String> values) {
            addCriterion("buy_name not in", values, "buyName");
            return (Criteria) this;
        }

        public Criteria andBuyNameBetween(String value1, String value2) {
            addCriterion("buy_name between", value1, value2, "buyName");
            return (Criteria) this;
        }

        public Criteria andBuyNameNotBetween(String value1, String value2) {
            addCriterion("buy_name not between", value1, value2, "buyName");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberIsNull() {
            addCriterion("buy_tax_number is null");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberIsNotNull() {
            addCriterion("buy_tax_number is not null");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberEqualTo(String value) {
            addCriterion("buy_tax_number =", value, "buyTaxNumber");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberNotEqualTo(String value) {
            addCriterion("buy_tax_number <>", value, "buyTaxNumber");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberGreaterThan(String value) {
            addCriterion("buy_tax_number >", value, "buyTaxNumber");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberGreaterThanOrEqualTo(String value) {
            addCriterion("buy_tax_number >=", value, "buyTaxNumber");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberLessThan(String value) {
            addCriterion("buy_tax_number <", value, "buyTaxNumber");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberLessThanOrEqualTo(String value) {
            addCriterion("buy_tax_number <=", value, "buyTaxNumber");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberLike(String value) {
            addCriterion("buy_tax_number like", value, "buyTaxNumber");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberNotLike(String value) {
            addCriterion("buy_tax_number not like", value, "buyTaxNumber");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberIn(List<String> values) {
            addCriterion("buy_tax_number in", values, "buyTaxNumber");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberNotIn(List<String> values) {
            addCriterion("buy_tax_number not in", values, "buyTaxNumber");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberBetween(String value1, String value2) {
            addCriterion("buy_tax_number between", value1, value2, "buyTaxNumber");
            return (Criteria) this;
        }

        public Criteria andBuyTaxNumberNotBetween(String value1, String value2) {
            addCriterion("buy_tax_number not between", value1, value2, "buyTaxNumber");
            return (Criteria) this;
        }

        public Criteria andBuyAddressIsNull() {
            addCriterion("buy_address is null");
            return (Criteria) this;
        }

        public Criteria andBuyAddressIsNotNull() {
            addCriterion("buy_address is not null");
            return (Criteria) this;
        }

        public Criteria andBuyAddressEqualTo(String value) {
            addCriterion("buy_address =", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressNotEqualTo(String value) {
            addCriterion("buy_address <>", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressGreaterThan(String value) {
            addCriterion("buy_address >", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("buy_address >=", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressLessThan(String value) {
            addCriterion("buy_address <", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressLessThanOrEqualTo(String value) {
            addCriterion("buy_address <=", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressLike(String value) {
            addCriterion("buy_address like", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressNotLike(String value) {
            addCriterion("buy_address not like", value, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressIn(List<String> values) {
            addCriterion("buy_address in", values, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressNotIn(List<String> values) {
            addCriterion("buy_address not in", values, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressBetween(String value1, String value2) {
            addCriterion("buy_address between", value1, value2, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyAddressNotBetween(String value1, String value2) {
            addCriterion("buy_address not between", value1, value2, "buyAddress");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneIsNull() {
            addCriterion("buy_phone is null");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneIsNotNull() {
            addCriterion("buy_phone is not null");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneEqualTo(String value) {
            addCriterion("buy_phone =", value, "buyPhone");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneNotEqualTo(String value) {
            addCriterion("buy_phone <>", value, "buyPhone");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneGreaterThan(String value) {
            addCriterion("buy_phone >", value, "buyPhone");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("buy_phone >=", value, "buyPhone");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneLessThan(String value) {
            addCriterion("buy_phone <", value, "buyPhone");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneLessThanOrEqualTo(String value) {
            addCriterion("buy_phone <=", value, "buyPhone");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneLike(String value) {
            addCriterion("buy_phone like", value, "buyPhone");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneNotLike(String value) {
            addCriterion("buy_phone not like", value, "buyPhone");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneIn(List<String> values) {
            addCriterion("buy_phone in", values, "buyPhone");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneNotIn(List<String> values) {
            addCriterion("buy_phone not in", values, "buyPhone");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneBetween(String value1, String value2) {
            addCriterion("buy_phone between", value1, value2, "buyPhone");
            return (Criteria) this;
        }

        public Criteria andBuyPhoneNotBetween(String value1, String value2) {
            addCriterion("buy_phone not between", value1, value2, "buyPhone");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankIsNull() {
            addCriterion("buy_opening_bank is null");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankIsNotNull() {
            addCriterion("buy_opening_bank is not null");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankEqualTo(String value) {
            addCriterion("buy_opening_bank =", value, "buyOpeningBank");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankNotEqualTo(String value) {
            addCriterion("buy_opening_bank <>", value, "buyOpeningBank");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankGreaterThan(String value) {
            addCriterion("buy_opening_bank >", value, "buyOpeningBank");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankGreaterThanOrEqualTo(String value) {
            addCriterion("buy_opening_bank >=", value, "buyOpeningBank");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankLessThan(String value) {
            addCriterion("buy_opening_bank <", value, "buyOpeningBank");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankLessThanOrEqualTo(String value) {
            addCriterion("buy_opening_bank <=", value, "buyOpeningBank");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankLike(String value) {
            addCriterion("buy_opening_bank like", value, "buyOpeningBank");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankNotLike(String value) {
            addCriterion("buy_opening_bank not like", value, "buyOpeningBank");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankIn(List<String> values) {
            addCriterion("buy_opening_bank in", values, "buyOpeningBank");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankNotIn(List<String> values) {
            addCriterion("buy_opening_bank not in", values, "buyOpeningBank");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankBetween(String value1, String value2) {
            addCriterion("buy_opening_bank between", value1, value2, "buyOpeningBank");
            return (Criteria) this;
        }

        public Criteria andBuyOpeningBankNotBetween(String value1, String value2) {
            addCriterion("buy_opening_bank not between", value1, value2, "buyOpeningBank");
            return (Criteria) this;
        }

        public Criteria andBuyAccountIsNull() {
            addCriterion("buy_account is null");
            return (Criteria) this;
        }

        public Criteria andBuyAccountIsNotNull() {
            addCriterion("buy_account is not null");
            return (Criteria) this;
        }

        public Criteria andBuyAccountEqualTo(String value) {
            addCriterion("buy_account =", value, "buyAccount");
            return (Criteria) this;
        }

        public Criteria andBuyAccountNotEqualTo(String value) {
            addCriterion("buy_account <>", value, "buyAccount");
            return (Criteria) this;
        }

        public Criteria andBuyAccountGreaterThan(String value) {
            addCriterion("buy_account >", value, "buyAccount");
            return (Criteria) this;
        }

        public Criteria andBuyAccountGreaterThanOrEqualTo(String value) {
            addCriterion("buy_account >=", value, "buyAccount");
            return (Criteria) this;
        }

        public Criteria andBuyAccountLessThan(String value) {
            addCriterion("buy_account <", value, "buyAccount");
            return (Criteria) this;
        }

        public Criteria andBuyAccountLessThanOrEqualTo(String value) {
            addCriterion("buy_account <=", value, "buyAccount");
            return (Criteria) this;
        }

        public Criteria andBuyAccountLike(String value) {
            addCriterion("buy_account like", value, "buyAccount");
            return (Criteria) this;
        }

        public Criteria andBuyAccountNotLike(String value) {
            addCriterion("buy_account not like", value, "buyAccount");
            return (Criteria) this;
        }

        public Criteria andBuyAccountIn(List<String> values) {
            addCriterion("buy_account in", values, "buyAccount");
            return (Criteria) this;
        }

        public Criteria andBuyAccountNotIn(List<String> values) {
            addCriterion("buy_account not in", values, "buyAccount");
            return (Criteria) this;
        }

        public Criteria andBuyAccountBetween(String value1, String value2) {
            addCriterion("buy_account between", value1, value2, "buyAccount");
            return (Criteria) this;
        }

        public Criteria andBuyAccountNotBetween(String value1, String value2) {
            addCriterion("buy_account not between", value1, value2, "buyAccount");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andPayeeIsNull() {
            addCriterion("payee is null");
            return (Criteria) this;
        }

        public Criteria andPayeeIsNotNull() {
            addCriterion("payee is not null");
            return (Criteria) this;
        }

        public Criteria andPayeeEqualTo(String value) {
            addCriterion("payee =", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeNotEqualTo(String value) {
            addCriterion("payee <>", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeGreaterThan(String value) {
            addCriterion("payee >", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeGreaterThanOrEqualTo(String value) {
            addCriterion("payee >=", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeLessThan(String value) {
            addCriterion("payee <", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeLessThanOrEqualTo(String value) {
            addCriterion("payee <=", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeLike(String value) {
            addCriterion("payee like", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeNotLike(String value) {
            addCriterion("payee not like", value, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeIn(List<String> values) {
            addCriterion("payee in", values, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeNotIn(List<String> values) {
            addCriterion("payee not in", values, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeBetween(String value1, String value2) {
            addCriterion("payee between", value1, value2, "payee");
            return (Criteria) this;
        }

        public Criteria andPayeeNotBetween(String value1, String value2) {
            addCriterion("payee not between", value1, value2, "payee");
            return (Criteria) this;
        }

        public Criteria andToReviewIsNull() {
            addCriterion("to_review is null");
            return (Criteria) this;
        }

        public Criteria andToReviewIsNotNull() {
            addCriterion("to_review is not null");
            return (Criteria) this;
        }

        public Criteria andToReviewEqualTo(String value) {
            addCriterion("to_review =", value, "toReview");
            return (Criteria) this;
        }

        public Criteria andToReviewNotEqualTo(String value) {
            addCriterion("to_review <>", value, "toReview");
            return (Criteria) this;
        }

        public Criteria andToReviewGreaterThan(String value) {
            addCriterion("to_review >", value, "toReview");
            return (Criteria) this;
        }

        public Criteria andToReviewGreaterThanOrEqualTo(String value) {
            addCriterion("to_review >=", value, "toReview");
            return (Criteria) this;
        }

        public Criteria andToReviewLessThan(String value) {
            addCriterion("to_review <", value, "toReview");
            return (Criteria) this;
        }

        public Criteria andToReviewLessThanOrEqualTo(String value) {
            addCriterion("to_review <=", value, "toReview");
            return (Criteria) this;
        }

        public Criteria andToReviewLike(String value) {
            addCriterion("to_review like", value, "toReview");
            return (Criteria) this;
        }

        public Criteria andToReviewNotLike(String value) {
            addCriterion("to_review not like", value, "toReview");
            return (Criteria) this;
        }

        public Criteria andToReviewIn(List<String> values) {
            addCriterion("to_review in", values, "toReview");
            return (Criteria) this;
        }

        public Criteria andToReviewNotIn(List<String> values) {
            addCriterion("to_review not in", values, "toReview");
            return (Criteria) this;
        }

        public Criteria andToReviewBetween(String value1, String value2) {
            addCriterion("to_review between", value1, value2, "toReview");
            return (Criteria) this;
        }

        public Criteria andToReviewNotBetween(String value1, String value2) {
            addCriterion("to_review not between", value1, value2, "toReview");
            return (Criteria) this;
        }

        public Criteria andDrawerIsNull() {
            addCriterion("drawer is null");
            return (Criteria) this;
        }

        public Criteria andDrawerIsNotNull() {
            addCriterion("drawer is not null");
            return (Criteria) this;
        }

        public Criteria andDrawerEqualTo(String value) {
            addCriterion("drawer =", value, "drawer");
            return (Criteria) this;
        }

        public Criteria andDrawerNotEqualTo(String value) {
            addCriterion("drawer <>", value, "drawer");
            return (Criteria) this;
        }

        public Criteria andDrawerGreaterThan(String value) {
            addCriterion("drawer >", value, "drawer");
            return (Criteria) this;
        }

        public Criteria andDrawerGreaterThanOrEqualTo(String value) {
            addCriterion("drawer >=", value, "drawer");
            return (Criteria) this;
        }

        public Criteria andDrawerLessThan(String value) {
            addCriterion("drawer <", value, "drawer");
            return (Criteria) this;
        }

        public Criteria andDrawerLessThanOrEqualTo(String value) {
            addCriterion("drawer <=", value, "drawer");
            return (Criteria) this;
        }

        public Criteria andDrawerLike(String value) {
            addCriterion("drawer like", value, "drawer");
            return (Criteria) this;
        }

        public Criteria andDrawerNotLike(String value) {
            addCriterion("drawer not like", value, "drawer");
            return (Criteria) this;
        }

        public Criteria andDrawerIn(List<String> values) {
            addCriterion("drawer in", values, "drawer");
            return (Criteria) this;
        }

        public Criteria andDrawerNotIn(List<String> values) {
            addCriterion("drawer not in", values, "drawer");
            return (Criteria) this;
        }

        public Criteria andDrawerBetween(String value1, String value2) {
            addCriterion("drawer between", value1, value2, "drawer");
            return (Criteria) this;
        }

        public Criteria andDrawerNotBetween(String value1, String value2) {
            addCriterion("drawer not between", value1, value2, "drawer");
            return (Criteria) this;
        }

        public Criteria andSaleNameIsNull() {
            addCriterion("sale_name is null");
            return (Criteria) this;
        }

        public Criteria andSaleNameIsNotNull() {
            addCriterion("sale_name is not null");
            return (Criteria) this;
        }

        public Criteria andSaleNameEqualTo(String value) {
            addCriterion("sale_name =", value, "saleName");
            return (Criteria) this;
        }

        public Criteria andSaleNameNotEqualTo(String value) {
            addCriterion("sale_name <>", value, "saleName");
            return (Criteria) this;
        }

        public Criteria andSaleNameGreaterThan(String value) {
            addCriterion("sale_name >", value, "saleName");
            return (Criteria) this;
        }

        public Criteria andSaleNameGreaterThanOrEqualTo(String value) {
            addCriterion("sale_name >=", value, "saleName");
            return (Criteria) this;
        }

        public Criteria andSaleNameLessThan(String value) {
            addCriterion("sale_name <", value, "saleName");
            return (Criteria) this;
        }

        public Criteria andSaleNameLessThanOrEqualTo(String value) {
            addCriterion("sale_name <=", value, "saleName");
            return (Criteria) this;
        }

        public Criteria andSaleNameLike(String value) {
            addCriterion("sale_name like", value, "saleName");
            return (Criteria) this;
        }

        public Criteria andSaleNameNotLike(String value) {
            addCriterion("sale_name not like", value, "saleName");
            return (Criteria) this;
        }

        public Criteria andSaleNameIn(List<String> values) {
            addCriterion("sale_name in", values, "saleName");
            return (Criteria) this;
        }

        public Criteria andSaleNameNotIn(List<String> values) {
            addCriterion("sale_name not in", values, "saleName");
            return (Criteria) this;
        }

        public Criteria andSaleNameBetween(String value1, String value2) {
            addCriterion("sale_name between", value1, value2, "saleName");
            return (Criteria) this;
        }

        public Criteria andSaleNameNotBetween(String value1, String value2) {
            addCriterion("sale_name not between", value1, value2, "saleName");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberIsNull() {
            addCriterion("sale_tax_number is null");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberIsNotNull() {
            addCriterion("sale_tax_number is not null");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberEqualTo(String value) {
            addCriterion("sale_tax_number =", value, "saleTaxNumber");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberNotEqualTo(String value) {
            addCriterion("sale_tax_number <>", value, "saleTaxNumber");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberGreaterThan(String value) {
            addCriterion("sale_tax_number >", value, "saleTaxNumber");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberGreaterThanOrEqualTo(String value) {
            addCriterion("sale_tax_number >=", value, "saleTaxNumber");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberLessThan(String value) {
            addCriterion("sale_tax_number <", value, "saleTaxNumber");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberLessThanOrEqualTo(String value) {
            addCriterion("sale_tax_number <=", value, "saleTaxNumber");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberLike(String value) {
            addCriterion("sale_tax_number like", value, "saleTaxNumber");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberNotLike(String value) {
            addCriterion("sale_tax_number not like", value, "saleTaxNumber");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberIn(List<String> values) {
            addCriterion("sale_tax_number in", values, "saleTaxNumber");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberNotIn(List<String> values) {
            addCriterion("sale_tax_number not in", values, "saleTaxNumber");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberBetween(String value1, String value2) {
            addCriterion("sale_tax_number between", value1, value2, "saleTaxNumber");
            return (Criteria) this;
        }

        public Criteria andSaleTaxNumberNotBetween(String value1, String value2) {
            addCriterion("sale_tax_number not between", value1, value2, "saleTaxNumber");
            return (Criteria) this;
        }

        public Criteria andSaleAddressIsNull() {
            addCriterion("sale_address is null");
            return (Criteria) this;
        }

        public Criteria andSaleAddressIsNotNull() {
            addCriterion("sale_address is not null");
            return (Criteria) this;
        }

        public Criteria andSaleAddressEqualTo(String value) {
            addCriterion("sale_address =", value, "saleAddress");
            return (Criteria) this;
        }

        public Criteria andSaleAddressNotEqualTo(String value) {
            addCriterion("sale_address <>", value, "saleAddress");
            return (Criteria) this;
        }

        public Criteria andSaleAddressGreaterThan(String value) {
            addCriterion("sale_address >", value, "saleAddress");
            return (Criteria) this;
        }

        public Criteria andSaleAddressGreaterThanOrEqualTo(String value) {
            addCriterion("sale_address >=", value, "saleAddress");
            return (Criteria) this;
        }

        public Criteria andSaleAddressLessThan(String value) {
            addCriterion("sale_address <", value, "saleAddress");
            return (Criteria) this;
        }

        public Criteria andSaleAddressLessThanOrEqualTo(String value) {
            addCriterion("sale_address <=", value, "saleAddress");
            return (Criteria) this;
        }

        public Criteria andSaleAddressLike(String value) {
            addCriterion("sale_address like", value, "saleAddress");
            return (Criteria) this;
        }

        public Criteria andSaleAddressNotLike(String value) {
            addCriterion("sale_address not like", value, "saleAddress");
            return (Criteria) this;
        }

        public Criteria andSaleAddressIn(List<String> values) {
            addCriterion("sale_address in", values, "saleAddress");
            return (Criteria) this;
        }

        public Criteria andSaleAddressNotIn(List<String> values) {
            addCriterion("sale_address not in", values, "saleAddress");
            return (Criteria) this;
        }

        public Criteria andSaleAddressBetween(String value1, String value2) {
            addCriterion("sale_address between", value1, value2, "saleAddress");
            return (Criteria) this;
        }

        public Criteria andSaleAddressNotBetween(String value1, String value2) {
            addCriterion("sale_address not between", value1, value2, "saleAddress");
            return (Criteria) this;
        }

        public Criteria andSalePhoneIsNull() {
            addCriterion("sale_phone is null");
            return (Criteria) this;
        }

        public Criteria andSalePhoneIsNotNull() {
            addCriterion("sale_phone is not null");
            return (Criteria) this;
        }

        public Criteria andSalePhoneEqualTo(String value) {
            addCriterion("sale_phone =", value, "salePhone");
            return (Criteria) this;
        }

        public Criteria andSalePhoneNotEqualTo(String value) {
            addCriterion("sale_phone <>", value, "salePhone");
            return (Criteria) this;
        }

        public Criteria andSalePhoneGreaterThan(String value) {
            addCriterion("sale_phone >", value, "salePhone");
            return (Criteria) this;
        }

        public Criteria andSalePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("sale_phone >=", value, "salePhone");
            return (Criteria) this;
        }

        public Criteria andSalePhoneLessThan(String value) {
            addCriterion("sale_phone <", value, "salePhone");
            return (Criteria) this;
        }

        public Criteria andSalePhoneLessThanOrEqualTo(String value) {
            addCriterion("sale_phone <=", value, "salePhone");
            return (Criteria) this;
        }

        public Criteria andSalePhoneLike(String value) {
            addCriterion("sale_phone like", value, "salePhone");
            return (Criteria) this;
        }

        public Criteria andSalePhoneNotLike(String value) {
            addCriterion("sale_phone not like", value, "salePhone");
            return (Criteria) this;
        }

        public Criteria andSalePhoneIn(List<String> values) {
            addCriterion("sale_phone in", values, "salePhone");
            return (Criteria) this;
        }

        public Criteria andSalePhoneNotIn(List<String> values) {
            addCriterion("sale_phone not in", values, "salePhone");
            return (Criteria) this;
        }

        public Criteria andSalePhoneBetween(String value1, String value2) {
            addCriterion("sale_phone between", value1, value2, "salePhone");
            return (Criteria) this;
        }

        public Criteria andSalePhoneNotBetween(String value1, String value2) {
            addCriterion("sale_phone not between", value1, value2, "salePhone");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankIsNull() {
            addCriterion("sale_opening_bank is null");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankIsNotNull() {
            addCriterion("sale_opening_bank is not null");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankEqualTo(String value) {
            addCriterion("sale_opening_bank =", value, "saleOpeningBank");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankNotEqualTo(String value) {
            addCriterion("sale_opening_bank <>", value, "saleOpeningBank");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankGreaterThan(String value) {
            addCriterion("sale_opening_bank >", value, "saleOpeningBank");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankGreaterThanOrEqualTo(String value) {
            addCriterion("sale_opening_bank >=", value, "saleOpeningBank");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankLessThan(String value) {
            addCriterion("sale_opening_bank <", value, "saleOpeningBank");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankLessThanOrEqualTo(String value) {
            addCriterion("sale_opening_bank <=", value, "saleOpeningBank");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankLike(String value) {
            addCriterion("sale_opening_bank like", value, "saleOpeningBank");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankNotLike(String value) {
            addCriterion("sale_opening_bank not like", value, "saleOpeningBank");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankIn(List<String> values) {
            addCriterion("sale_opening_bank in", values, "saleOpeningBank");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankNotIn(List<String> values) {
            addCriterion("sale_opening_bank not in", values, "saleOpeningBank");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankBetween(String value1, String value2) {
            addCriterion("sale_opening_bank between", value1, value2, "saleOpeningBank");
            return (Criteria) this;
        }

        public Criteria andSaleOpeningBankNotBetween(String value1, String value2) {
            addCriterion("sale_opening_bank not between", value1, value2, "saleOpeningBank");
            return (Criteria) this;
        }

        public Criteria andSaleAccountIsNull() {
            addCriterion("sale_account is null");
            return (Criteria) this;
        }

        public Criteria andSaleAccountIsNotNull() {
            addCriterion("sale_account is not null");
            return (Criteria) this;
        }

        public Criteria andSaleAccountEqualTo(String value) {
            addCriterion("sale_account =", value, "saleAccount");
            return (Criteria) this;
        }

        public Criteria andSaleAccountNotEqualTo(String value) {
            addCriterion("sale_account <>", value, "saleAccount");
            return (Criteria) this;
        }

        public Criteria andSaleAccountGreaterThan(String value) {
            addCriterion("sale_account >", value, "saleAccount");
            return (Criteria) this;
        }

        public Criteria andSaleAccountGreaterThanOrEqualTo(String value) {
            addCriterion("sale_account >=", value, "saleAccount");
            return (Criteria) this;
        }

        public Criteria andSaleAccountLessThan(String value) {
            addCriterion("sale_account <", value, "saleAccount");
            return (Criteria) this;
        }

        public Criteria andSaleAccountLessThanOrEqualTo(String value) {
            addCriterion("sale_account <=", value, "saleAccount");
            return (Criteria) this;
        }

        public Criteria andSaleAccountLike(String value) {
            addCriterion("sale_account like", value, "saleAccount");
            return (Criteria) this;
        }

        public Criteria andSaleAccountNotLike(String value) {
            addCriterion("sale_account not like", value, "saleAccount");
            return (Criteria) this;
        }

        public Criteria andSaleAccountIn(List<String> values) {
            addCriterion("sale_account in", values, "saleAccount");
            return (Criteria) this;
        }

        public Criteria andSaleAccountNotIn(List<String> values) {
            addCriterion("sale_account not in", values, "saleAccount");
            return (Criteria) this;
        }

        public Criteria andSaleAccountBetween(String value1, String value2) {
            addCriterion("sale_account between", value1, value2, "saleAccount");
            return (Criteria) this;
        }

        public Criteria andSaleAccountNotBetween(String value1, String value2) {
            addCriterion("sale_account not between", value1, value2, "saleAccount");
            return (Criteria) this;
        }

        public Criteria andTotalSumIsNull() {
            addCriterion("total_sum is null");
            return (Criteria) this;
        }

        public Criteria andTotalSumIsNotNull() {
            addCriterion("total_sum is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSumEqualTo(String value) {
            addCriterion("total_sum =", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumNotEqualTo(String value) {
            addCriterion("total_sum <>", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumGreaterThan(String value) {
            addCriterion("total_sum >", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumGreaterThanOrEqualTo(String value) {
            addCriterion("total_sum >=", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumLessThan(String value) {
            addCriterion("total_sum <", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumLessThanOrEqualTo(String value) {
            addCriterion("total_sum <=", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumLike(String value) {
            addCriterion("total_sum like", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumNotLike(String value) {
            addCriterion("total_sum not like", value, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumIn(List<String> values) {
            addCriterion("total_sum in", values, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumNotIn(List<String> values) {
            addCriterion("total_sum not in", values, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumBetween(String value1, String value2) {
            addCriterion("total_sum between", value1, value2, "totalSum");
            return (Criteria) this;
        }

        public Criteria andTotalSumNotBetween(String value1, String value2) {
            addCriterion("total_sum not between", value1, value2, "totalSum");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxIsNull() {
            addCriterion("aggregate_tax is null");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxIsNotNull() {
            addCriterion("aggregate_tax is not null");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxEqualTo(String value) {
            addCriterion("aggregate_tax =", value, "aggregateTax");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxNotEqualTo(String value) {
            addCriterion("aggregate_tax <>", value, "aggregateTax");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxGreaterThan(String value) {
            addCriterion("aggregate_tax >", value, "aggregateTax");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxGreaterThanOrEqualTo(String value) {
            addCriterion("aggregate_tax >=", value, "aggregateTax");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxLessThan(String value) {
            addCriterion("aggregate_tax <", value, "aggregateTax");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxLessThanOrEqualTo(String value) {
            addCriterion("aggregate_tax <=", value, "aggregateTax");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxLike(String value) {
            addCriterion("aggregate_tax like", value, "aggregateTax");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxNotLike(String value) {
            addCriterion("aggregate_tax not like", value, "aggregateTax");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxIn(List<String> values) {
            addCriterion("aggregate_tax in", values, "aggregateTax");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxNotIn(List<String> values) {
            addCriterion("aggregate_tax not in", values, "aggregateTax");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxBetween(String value1, String value2) {
            addCriterion("aggregate_tax between", value1, value2, "aggregateTax");
            return (Criteria) this;
        }

        public Criteria andAggregateTaxNotBetween(String value1, String value2) {
            addCriterion("aggregate_tax not between", value1, value2, "aggregateTax");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxIsNull() {
            addCriterion("total_price_and_tax is null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxIsNotNull() {
            addCriterion("total_price_and_tax is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxEqualTo(String value) {
            addCriterion("total_price_and_tax =", value, "totalPriceAndTax");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxNotEqualTo(String value) {
            addCriterion("total_price_and_tax <>", value, "totalPriceAndTax");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxGreaterThan(String value) {
            addCriterion("total_price_and_tax >", value, "totalPriceAndTax");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxGreaterThanOrEqualTo(String value) {
            addCriterion("total_price_and_tax >=", value, "totalPriceAndTax");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxLessThan(String value) {
            addCriterion("total_price_and_tax <", value, "totalPriceAndTax");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxLessThanOrEqualTo(String value) {
            addCriterion("total_price_and_tax <=", value, "totalPriceAndTax");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxLike(String value) {
            addCriterion("total_price_and_tax like", value, "totalPriceAndTax");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxNotLike(String value) {
            addCriterion("total_price_and_tax not like", value, "totalPriceAndTax");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxIn(List<String> values) {
            addCriterion("total_price_and_tax in", values, "totalPriceAndTax");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxNotIn(List<String> values) {
            addCriterion("total_price_and_tax not in", values, "totalPriceAndTax");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxBetween(String value1, String value2) {
            addCriterion("total_price_and_tax between", value1, value2, "totalPriceAndTax");
            return (Criteria) this;
        }

        public Criteria andTotalPriceAndTaxNotBetween(String value1, String value2) {
            addCriterion("total_price_and_tax not between", value1, value2, "totalPriceAndTax");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateIsNull() {
            addCriterion("invoice_state is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateIsNotNull() {
            addCriterion("invoice_state is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateEqualTo(String value) {
            addCriterion("invoice_state =", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateNotEqualTo(String value) {
            addCriterion("invoice_state <>", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateGreaterThan(String value) {
            addCriterion("invoice_state >", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_state >=", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateLessThan(String value) {
            addCriterion("invoice_state <", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateLessThanOrEqualTo(String value) {
            addCriterion("invoice_state <=", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateLike(String value) {
            addCriterion("invoice_state like", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateNotLike(String value) {
            addCriterion("invoice_state not like", value, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateIn(List<String> values) {
            addCriterion("invoice_state in", values, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateNotIn(List<String> values) {
            addCriterion("invoice_state not in", values, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateBetween(String value1, String value2) {
            addCriterion("invoice_state between", value1, value2, "invoiceState");
            return (Criteria) this;
        }

        public Criteria andInvoiceStateNotBetween(String value1, String value2) {
            addCriterion("invoice_state not between", value1, value2, "invoiceState");
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