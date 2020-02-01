package com.abeldevelop.architecture.service.labelsanderrors.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ErrorMessageEntity.class)
public class ErrorMessageEntity_ {

	public static volatile SingularAttribute<ErrorMessageEntity, String> id;
	public static volatile SingularAttribute<ErrorMessageEntity, String> serviceName;
	public static volatile SingularAttribute<ErrorMessageEntity, String> languageCode;
	public static volatile SingularAttribute<ErrorMessageEntity, String> code;
	public static volatile SingularAttribute<ErrorMessageEntity, String> message;
	public static volatile SingularAttribute<ErrorMessageEntity, Integer> version;
	
}
