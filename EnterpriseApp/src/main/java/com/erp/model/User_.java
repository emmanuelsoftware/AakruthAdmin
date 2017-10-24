package com.erp.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Character> sta;
	public static volatile SingularAttribute<User, String> usrnme;
	public static volatile SingularAttribute<User, String> phnnbr;
	public static volatile SingularAttribute<User, String> pswd;
	public static volatile SetAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, Integer> usrId;
	public static volatile SingularAttribute<User, Date> enddte;
	public static volatile SingularAttribute<User, Integer> audUsrId;
	public static volatile SingularAttribute<User, Branch> branch;
	public static volatile SingularAttribute<User, String> adr;
	public static volatile SingularAttribute<User, Date> strdte;
	public static volatile SetAttribute<User, PurchaseBill> purchaseBills;
	public static volatile SetAttribute<User, SaleBill> saleBills;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SetAttribute<User, Task> tasks;

}

