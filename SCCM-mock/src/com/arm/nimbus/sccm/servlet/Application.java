package com.arm.nimbus.sccm.servlet;

import com.arm.nimbus.sccm.config.BootStrap;
import com.arm.nimbus.sccm.model.Product;
import com.arm.nimbus.sccm.service.Service;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Application main http servlet
 *
 * @author vicben01
 */
public class Application extends JsonHttpServlet
{

  @Inject
  Service service;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    if (req.getRequestURI().endsWith("list"))
    {
      Session session1 = sessionFactory.openSession();
      session1.beginTransaction();

      resp.setContentType(TEXT_PLAIN);
      resp.setStatus(HttpServletResponse.SC_OK);

      List<Product> results = session1.createCriteria(Product.class).setMaxResults(10).list();
      for (Product product : results)
        render(resp, product);

      session1.getTransaction().commit();
      session1.close();
    }
    else if (req.getRequestURI().endsWith("date"))
    {
      resp.setContentType(TEXT_PLAIN);
      resp.setStatus(HttpServletResponse.SC_OK);
      render(resp, "Current date is : " + service.currentDate());

    }
    else
    {
      resp.setContentType(TEXT_PLAIN);
      resp.setStatus(HttpServletResponse.SC_OK);
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
    super.init();
    // BootStrap data in db
    BootStrap.start(sessionFactory);

  }
}
