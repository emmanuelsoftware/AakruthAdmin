package com.aakruth.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PurTbl.class)
public abstract class PurTbl_ {

	public static volatile SingularAttribute<PurTbl, Character> sta;
	public static volatile SingularAttribute<PurTbl, Integer> cnt;
	public static volatile SingularAttribute<PurTbl, BigDecimal> vat;
	public static volatile SingularAttribute<PurTbl, Integer> purId;
	public static volatile SingularAttribute<PurTbl, UsrTbl> usrTbl;
	public static volatile SingularAttribute<PurTbl, Integer> invoice;
	public static volatile SingularAttribute<PurTbl, Date> purdte;
	public static volatile SingularAttribute<PurTbl, Date> enddte;
	public static volatile SingularAttribute<PurTbl, PrdTbl> prdTbl;

}

