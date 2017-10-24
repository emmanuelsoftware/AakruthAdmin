package com.aakruth.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BillTbl.class)
public abstract class BillTbl_ {

	public static volatile SingularAttribute<BillTbl, Date> strdte;
	public static volatile SingularAttribute<BillTbl, Character> sta;
	public static volatile SingularAttribute<BillTbl, Integer> billId;
	public static volatile SingularAttribute<BillTbl, Date> entryDte;
	public static volatile SingularAttribute<BillTbl, Dealer> dealer;
	public static volatile SingularAttribute<BillTbl, UsrTbl> usrTbl;
	public static volatile SingularAttribute<BillTbl, String> poNum;
	public static volatile SingularAttribute<BillTbl, BigDecimal> tax;
	public static volatile SingularAttribute<BillTbl, Date> enddte;
	public static volatile SetAttribute<BillTbl, SalTbl> salTbls;

}

