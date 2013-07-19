package com.arm.nimbus.sccm.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Session Factory
 *
 *  - Ensure that the configuration is called once.
 *
 * @author vicben01
 */
public class HibernateSessionFactory
{
  static SessionFactory sessionFactory;

  public static SessionFactory start()
  {
    if (sessionFactory == null)
    {
      sessionFactory = new Configuration()
          .configure()
          .buildSessionFactory();
    }
    return sessionFactory;
  }

  public static void stop()
  {
    if(sessionFactory != null)
      sessionFactory.close();
  }
}
