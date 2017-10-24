package com.erp.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Credit.class)
public abstract class Credit_ {

	public static volatile SingularAttribute<Credit, BigDecimal> sgst;
	public static volatile SingularAttribute<Credit, Character> sta;
	public static volatile SingularAttribute<Credit, Integer> purId;
	public static volatile SingularAttribute<Credit, BigDecimal> otherTax;
	public static volatile SingularAttribute<Credit, BigDecimal> cgst;
	public static volatile SingularAttribute<Credit, Integer> credId;
	public static volatile SingularAttribute<Credit, Date> enddte;
	public static volatile SingularAttribute<Credit, Integer> audUsrId;
	public static volatile SingularAttribute<Credit, BigDecimal> igst;
	public static volatile SingularAttribute<Credit, String> voucherNo;
	public static volatile SingularAttribute<Credit, Date> strdte;
	public static volatile SingularAttribute<Credit, Dealer> dealer;
	public static volatile SingularAttribute<Credit, String> particulars;
	public static volatile SingularAttribute<Credit, Integer> typeOfPayment;
	public static volatile SingularAttribute<Credit, Transaction> transaction;
	public static volatile SingularAttribute<Credit, String> remarks;

}

