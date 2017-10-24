package com.aakruth.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transaction.class)
public abstract class Transaction_ {

	public static volatile SingularAttribute<Transaction, Integer> tranId;
	public static volatile SetAttribute<Transaction, Credit> credits;
	public static volatile SetAttribute<Transaction, Debit> debits;
	public static volatile SingularAttribute<Transaction, String> detail;
	public static volatile SingularAttribute<Transaction, Character> type;

}

