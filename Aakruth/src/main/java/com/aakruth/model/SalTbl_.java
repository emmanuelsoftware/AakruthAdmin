package com.aakruth.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SalTbl.class)
public abstract class SalTbl_ {

	public static volatile SingularAttribute<SalTbl, Character> sta;
	public static volatile SingularAttribute<SalTbl, BillTbl> billTbl;
	public static volatile SingularAttribute<SalTbl, Integer> cnt;
	public static volatile SingularAttribute<SalTbl, BigDecimal> amt;
	public static volatile SingularAttribute<SalTbl, Integer> usrId;
	public static volatile SingularAttribute<SalTbl, Integer> salId;
	public static volatile SingularAttribute<SalTbl, PrdTbl> prdTbl;

}

