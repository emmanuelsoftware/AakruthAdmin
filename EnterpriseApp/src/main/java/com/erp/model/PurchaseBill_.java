package com.erp.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PurchaseBill.class)
public abstract class PurchaseBill_ {

	public static volatile SingularAttribute<PurchaseBill, Date> strdte;
	public static volatile SingularAttribute<PurchaseBill, Character> sta;
	public static volatile SingularAttribute<PurchaseBill, Logistic> logistic;
	public static volatile SetAttribute<PurchaseBill, Purchase> purchases;
	public static volatile SingularAttribute<PurchaseBill, Integer> billId;
	public static volatile SingularAttribute<PurchaseBill, Date> entryDte;
	public static volatile SingularAttribute<PurchaseBill, Dealer> dealer;
	public static volatile SingularAttribute<PurchaseBill, String> poNum;
	public static volatile SingularAttribute<PurchaseBill, Date> enddte;
	public static volatile SingularAttribute<PurchaseBill, User> user;
	public static volatile SingularAttribute<PurchaseBill, Character> taxType;

}

