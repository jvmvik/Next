package com.arm.nimbus.sccm.service;

import java.util.Date;

/**
 * Basic example of Service build with CDI.
 * A tutorial is available here :
 * @link http://www.byteslounge.com/tutorials/configuring-cdi-with-tomcat-example
 *
 * This service return the current date.
 *
 * @author vicben01
 */
public class SimpleService implements Service
{
  @Override
  public Date currentDate()
  {
    Date d = new Date();
    d.setTime(d.getTime() + 3600);
    return d;
  }
}
