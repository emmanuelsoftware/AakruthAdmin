package com.erp.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SaleBill.class)
public abstract class SaleBill_ {

	public static volatile SingularAttribute<SaleBill, Date> strdte;
	public static volatile SingularAttribute<SaleBill, Character> sta;
	public static volatile SingularAttribute<SaleBill, Logistic> logistic;
	public static volatile SingularAttribute<SaleBill, Integer> billId;
	public static volatile SingularAttribute<SaleBill, Date> entryDte;
	public static volatile SingularAttribute<SaleBill, Dealer> dealer;
	public static volatile SingularAttribute<SaleBill, String> poNum;
	public static volatile SingularAttribute<SaleBill, Date> enddte;
	public static volatile SingularAttribute<SaleBill, User> user;
	public static volatile SingularAttribute<SaleBill, Character> taxType;
	public static volatile SetAttribute<SaleBill, Sale> sales;

}

