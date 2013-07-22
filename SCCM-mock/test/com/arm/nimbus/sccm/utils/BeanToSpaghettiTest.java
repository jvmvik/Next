package com.arm.nimbus.sccm.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * @author vicben01
 */
public class BeanToSpaghettiTest {

  BeanToSpaghetti beanToSpaghetti;

  @Before
  public void setUp()
  {
    beanToSpaghetti = new BeanToSpaghetti();
  }

  @Test
  public void createObject()
  {
    String catJsObj = beanToSpaghetti.createObject(new Cat());
    assertNotNull(catJsObj);
    assertEquals("function Cat(json)\n" +
        "{\n" +
        "var that = this;\n" +
        "that._id=json.id;\n" +
        "that.age=json.age;\n" +
        "that.is_alive=json.isAlive;\n" +
        "that.name=json.name;\n" +
        "\n" +
        "}", catJsObj);
  }

  @Test
  public void createAsArray()
  {
    String result = beanToSpaghetti.createAsArray("Twitt");
    assertNotNull(result);
    assertEquals("// Convert JSON Array to JS Array of Twitt\n" +
        "function asTwittArray(json)\n" +
        "   {\n" +
        "   var array = [];\n" +
        "   for (var i = 0; i < json.length; i++)\n" +
        "   {\n" +
        "   array[i] = new Twitt(json[i]);\n" +
        "   }\n" +
        "   return array;\n" +
        "   }", result);
  }

  // JavaBean under test.
  class Cat
  {
    String name;
    int age;
    boolean alive;

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public int getAge()
    {
      return age;
    }

    public void setAge(int age)
    {
      this.age = age;
    }

    public boolean isAlive()
    {
      return alive;
    }

    public void setAlive(boolean alive)
    {
      this.alive = alive;
    }
  }


}
