package com.erp.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sale.class)
public abstract class Sale_ {

	public static volatile SingularAttribute<Sale, BigDecimal> sgst;
	public static volatile SingularAttribute<Sale, Character> sta;
	public static volatile SingularAttribute<Sale, Product> product;
	public static volatile SingularAttribute<Sale, BigDecimal> saleAmt;
	public static volatile SingularAttribute<Sale, SaleBill> saleBill;
	public static volatile SingularAttribute<Sale, Integer> cnt;
	public static volatile SingularAttribute<Sale, BigDecimal> discount;
	public static volatile SingularAttribute<Sale, BigDecimal> otherTax;
	public static volatile SingularAttribute<Sale, Integer> salId;
	public static volatile SingularAttribute<Sale, BigDecimal> cgst;
	public static volatile SingularAttribute<Sale, Integer> audUsrId;
	public static volatile SingularAttribute<Sale, BigDecimal> igst;

}

