package com.erp.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Purchase.class)
public abstract class Purchase_ {

	public static volatile SingularAttribute<Purchase, BigDecimal> sgst;
	public static volatile SingularAttribute<Purchase, Character> sta;
	public static volatile SingularAttribute<Purchase, Product> product;
	public static volatile SingularAttribute<Purchase, PurchaseBill> purchaseBill;
	public static volatile SingularAttribute<Purchase, Integer> cnt;
	public static volatile SingularAttribute<Purchase, Integer> purId;
	public static volatile SingularAttribute<Purchase, Integer> orderNum;
	public static volatile SingularAttribute<Purchase, BigDecimal> otherTax;
	public static volatile SingularAttribute<Purchase, BigDecimal> cgst;
	public static volatile SingularAttribute<Purchase, Date> enddte;
	public static volatile SingularAttribute<Purchase, Integer> audUsrId;
	public static volatile SingularAttribute<Purchase, BigDecimal> igst;
	public static volatile SingularAttribute<Purchase, Date> strdte;
	public static volatile SingularAttribute<Purchase, BigDecimal> purAmt;

}

