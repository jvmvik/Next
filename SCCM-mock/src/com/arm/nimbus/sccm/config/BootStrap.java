package com.arm.nimbus.sccm.config;

import com.arm.nimbus.sccm.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

/**
 * @author vicben01
 */
public class BootStrap
{
  public static void start(SessionFactory sessionFactory)
  {
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    Product p = new Product();
    p.setName("product1");
    p.setDate(new Date());
    session.save(p);

    session.getTransaction().commit();
    session.close();

  }
}
