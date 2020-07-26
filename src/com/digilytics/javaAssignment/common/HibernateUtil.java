package com.digilytics.javaAssignment.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.digilytics.javaAssignment.pojo.Role;
import com.digilytics.javaAssignment.pojo.UserRole;
import com.digilytics.javaAssignment.pojo.Users;




public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static Configuration configuration;
	private static Session session;
	
	static {
		try {
		if(sessionFactory == null)
		configuration = new Configuration();
		configuration.configure();	
		configuration.addAnnotatedClass(Users.class);
		configuration.addAnnotatedClass(Role.class);
		configuration.addAnnotatedClass(UserRole.class);
		
		
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}catch (Exception e) {
		e.printStackTrace();
		
	}
	}	
	
	public static Session getSession() {		
		session = HibernateUtil.getSessionFactory() == null ? null 
				: HibernateUtil.getSessionFactory().openSession();		
		return session;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Configuration getConfiguration() {
		return configuration;
	}

	
	
}
