package com.aakruth.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PrdTbl.class)
public abstract class PrdTbl_ {

	public static volatile SingularAttribute<PrdTbl, String> prdnme;
	public static volatile SingularAttribute<PrdTbl, Date> strdte;
	public static volatile SingularAttribute<PrdTbl, Character> sta;
	public static volatile SingularAttribute<PrdTbl, Integer> prdId;
	public static volatile SingularAttribute<PrdTbl, Short> amc;
	public static volatile SingularAttribute<PrdTbl, Dealer> dealer;
	public static volatile SingularAttribute<PrdTbl, BigDecimal> amt;
	public static volatile SingularAttribute<PrdTbl, Integer> usrId;
	public static volatile SingularAttribute<PrdTbl, String> typ;
	public static volatile SingularAttribute<PrdTbl, Date> enddte;
	public static volatile SetAttribute<PrdTbl, PurTbl> purTbls;
	public static volatile SetAttribute<PrdTbl, SalTbl> salTbls;

}

