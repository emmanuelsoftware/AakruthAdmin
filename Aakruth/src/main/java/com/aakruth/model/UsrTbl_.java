package com.aakruth.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UsrTbl.class)
public abstract class UsrTbl_ {

	public static volatile SingularAttribute<UsrTbl, Character> sta;
	public static volatile SingularAttribute<UsrTbl, String> usrnme;
	public static volatile SingularAttribute<UsrTbl, String> phnnbr;
	public static volatile SingularAttribute<UsrTbl, String> pswd;
	public static volatile SingularAttribute<UsrTbl, Integer> usrId;
	public static volatile SingularAttribute<UsrTbl, Date> enddte;
	public static volatile SetAttribute<UsrTbl, PurTbl> purTbls;
	public static volatile SingularAttribute<UsrTbl, String> adr;
	public static volatile SingularAttribute<UsrTbl, Date> strdte;
	public static volatile SetAttribute<UsrTbl, RoleTbl> roleTbls;
	public static volatile SetAttribute<UsrTbl, BillTbl> billTbls;
	public static volatile SingularAttribute<UsrTbl, String> email;
	public static volatile SetAttribute<UsrTbl, Notification> notifications;

}

