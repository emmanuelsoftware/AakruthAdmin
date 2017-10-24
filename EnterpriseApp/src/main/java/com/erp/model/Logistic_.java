package com.erp.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Logistic.class)
public abstract class Logistic_ {

	public static volatile SingularAttribute<Logistic, Character> sta;
	public static volatile SingularAttribute<Logistic, Date> endDate;
	public static volatile SingularAttribute<Logistic, String> contactPerson;
	public static volatile SingularAttribute<Logistic, Integer> usrId;
	public static volatile SingularAttribute<Logistic, String> transmitFrom;
	public static volatile SetAttribute<Logistic, PurchaseBill> purchaseBills;
	public static volatile SingularAttribute<Logistic, String> logisticName;
	public static volatile SingularAttribute<Logistic, String> transmitTwo;
	public static volatile SingularAttribute<Logistic, String> contactNumber;
	public static volatile SetAttribute<Logistic, SaleBill> saleBills;
	public static volatile SingularAttribute<Logistic, Integer> logId;
	public static volatile SingularAttribute<Logistic, Integer> refId;
	public static volatile SingularAttribute<Logistic, String> category;
	public static volatile SingularAttribute<Logistic, String> trackingNumber;
	public static volatile SingularAttribute<Logistic, Date> startDate;

}

