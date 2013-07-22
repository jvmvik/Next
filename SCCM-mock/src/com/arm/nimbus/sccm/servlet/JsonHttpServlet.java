package com.arm.nimbus.sccm.servlet;

import com.arm.nimbus.sccm.config.HibernateSessionFactory;
import com.google.gson.Gson;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JsonHttpServlet
 *
 * This servlet add common useful features to HttpServlet.
 *
 * Features:
 *  - Prepare an hibernate sessionFactory
 *  - Provide methods to render object as JSON
 *
 * @author vicben01
 */
public abstract class JsonHttpServlet extends HttpServlet
{
  protected static final String TEXT_PLAIN = "text/plain";
  protected SessionFactory sessionFactory;
  protected Gson gson = new Gson();

  /**
   * Render an object as JSON
   *
   * @param response
   * @param object
   * @throws IOException
   */
  protected void render(HttpServletResponse response, Object object) throws IOException
  {
    response.getOutputStream().println(gson.toJson(object));
  }

  @Override
  public void init() throws ServletException
  {
    log("Servlet initialization");
    sessionFactory = HibernateSessionFactory.start();
    super.init();
  }

}
