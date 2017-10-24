package com.erp.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Branch.class)
public abstract class Branch_ {

	public static volatile SingularAttribute<Branch, Integer> branchId;
	public static volatile SingularAttribute<Branch, String> addrress;
	public static volatile SingularAttribute<Branch, Integer> contactNumber;
	public static volatile SingularAttribute<Branch, String> branchName;
	public static volatile SingularAttribute<Branch, String> gstin;
	public static volatile SingularAttribute<Branch, String> pan;
	public static volatile SingularAttribute<Branch, String> email;
	public static volatile SetAttribute<Branch, User> users;

}

