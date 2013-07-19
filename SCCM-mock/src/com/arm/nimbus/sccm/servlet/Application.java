package com.arm.nimbus.sccm.servlet;

import com.arm.nimbus.sccm.config.BootStrap;
import com.arm.nimbus.sccm.config.HibernateSessionFactory;
import com.arm.nimbus.sccm.model.Product;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author vicben01
 */
public class Application extends HttpServlet
{
  SessionFactory sessionFactory;
  Gson gson = new Gson();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    if (req.getRequestURI().endsWith("list"))
    {
      resp.setContentType("text/plain");
      resp.setStatus(200);
      Session session1 = sessionFactory.openSession();
      session1.beginTransaction();

      List<Product> results = session1.createCriteria(Product.class).setMaxResults(10).list();
      for (Product product : results)
      {
        resp.getOutputStream().println("Product: " + gson.toJson(product));
      }
      session1.getTransaction().commit();
      session1.close();
    }
    else
    {
      resp.setContentType("text/plain");
      resp.setStatus(200);
      resp.getOutputStream().println("HEllo world!");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    super.doPost(req, resp);
  }

  @Override
  public void destroy()
  {
    super.destroy();
  }

  @Override
  public void init() throws ServletException
  {
    System.out.println("Servlet initialization");
    sessionFactory = HibernateSessionFactory.start();

    // BootStrap data in db
    BootStrap.start(null);

    super.init();
  }
}
