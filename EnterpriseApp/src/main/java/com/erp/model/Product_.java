package com.erp.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, BigDecimal> sgst;
	public static volatile SingularAttribute<Product, Character> sta;
	public static volatile SingularAttribute<Product, Integer> prdId;
	public static volatile SingularAttribute<Product, String> hsnCode;
	public static volatile SetAttribute<Product, Purchase> purchases;
	public static volatile SingularAttribute<Product, BigDecimal> discount;
	public static volatile SingularAttribute<Product, BigDecimal> otherTax;
	public static volatile SingularAttribute<Product, BigDecimal> cgst;
	public static volatile SingularAttribute<Product, Date> enddte;
	public static volatile SingularAttribute<Product, Integer> audUsrId;
	public static volatile SingularAttribute<Product, BigDecimal> igst;
	public static volatile SetAttribute<Product, Sale> sales;
	public static volatile SingularAttribute<Product, String> prdnme;
	public static volatile SingularAttribute<Product, Date> strdte;
	public static volatile SingularAttribute<Product, BigDecimal> saleAmt;
	public static volatile SingularAttribute<Product, Dealer> dealer;
	public static volatile SingularAttribute<Product, Short> warranty;
	public static volatile SingularAttribute<Product, BigDecimal> purAmt;

}

