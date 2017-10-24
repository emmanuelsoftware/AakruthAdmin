package com.aakruth.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Debit.class)
public abstract class Debit_ {

	public static volatile SingularAttribute<Debit, Character> sta;
	public static volatile SingularAttribute<Debit, BigDecimal> amount;
	public static volatile SingularAttribute<Debit, BigDecimal> vat;
	public static volatile SingularAttribute<Debit, Integer> usrId;
	public static volatile SingularAttribute<Debit, String> particular;
	public static volatile SingularAttribute<Debit, Integer> debId;
	public static volatile SingularAttribute<Debit, Date> enddte;
	public static volatile SingularAttribute<Debit, String> voucherNo;
	public static volatile SingularAttribute<Debit, Date> strdte;
	public static volatile SingularAttribute<Debit, Integer> billId;
	public static volatile SingularAttribute<Debit, Dealer> dealer;
	public static volatile SingularAttribute<Debit, Integer> typeOfPayment;
	public static volatile SingularAttribute<Debit, Transaction> transaction;
	public static volatile SingularAttribute<Debit, String> remarks;

}

