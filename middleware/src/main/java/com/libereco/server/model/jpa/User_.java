/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.model.jpa;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.libereco.server.model.User;

/**
 * Meta-model class for JPA2 prototype.
 * 
 * @author Aleksandar
 *
 */
@StaticMetamodel(User.class)
public class User_ {

    public static volatile SingularAttribute<User, Integer> id;

    public static volatile SingularAttribute<User, String> userName;
}
