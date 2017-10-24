package com.erp.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Dealer.class)
public abstract class Dealer_ {

	public static volatile SingularAttribute<Dealer, Character> sta;
	public static volatile SingularAttribute<Dealer, String> phnnbr;
	public static volatile SingularAttribute<Dealer, String> poc;
	public static volatile SetAttribute<Dealer, Debit> debits;
	public static volatile SingularAttribute<Dealer, Integer> dealerId;
	public static volatile SingularAttribute<Dealer, Date> enddte;
	public static volatile SingularAttribute<Dealer, Character> type;
	public static volatile SingularAttribute<Dealer, Integer> audUsrId;
	public static volatile SingularAttribute<Dealer, String> gstin;
	public static volatile SingularAttribute<Dealer, String> adr;
	public static volatile SetAttribute<Dealer, Product> products;
	public static volatile SingularAttribute<Dealer, Date> strdte;
	public static volatile SetAttribute<Dealer, PurchaseBill> purchaseBills;
	public static volatile SetAttribute<Dealer, Credit> credits;
	public static volatile SingularAttribute<Dealer, String> name;
	public static volatile SetAttribute<Dealer, SaleBill> saleBills;
	public static volatile SingularAttribute<Dealer, String> email;

}

