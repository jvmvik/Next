package com.arm.nimbus.sccm.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Generate a knockout.js object model from a Bean.
 *
 * @author vicben01
 */
public class BeanToSpaghetti
{
  /**
   * Create a JS Object from a JSON Object
   */
  public String createObject(Object bean)
  {
    String s = "function " + bean.getClass().getSimpleName() + "(json)\n";
    s += "{\n" +
        "var that = this;\n" +
        "that._id=json.id;\n";
    //TODO iterate on instances
    for (String paramName : getParamNames(bean))
    {
      s += "that." + jsonName(paramName) + "=json." + gsonName(paramName) + ";\n";
    }
    return s + "\n}";
  }

  private List<String> getParamNames(Object bean)
  {
    List<String> list = new ArrayList<>();
    try
    {
      BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());

      PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();

      // copy properties into hashtable
      for (PropertyDescriptor property : properties)
      {
        //Class paramClass = property.getPropertyType();
        Method getter = property.getReadMethod();

        // Converts getBookTitle() to book_title
        String paramName = getter.getName();
        if (!"getClass".equals(paramName))
        {
          list.add(paramName);
        }
      }
    }
    catch (IntrospectionException e)
    {
      e.printStackTrace();
    }
    return list;
  }

  private String jsonName(String paramName)
  {
    return splitCamelCase(paramName).replace("get_", "");
  }

  private String gsonName(String paramName)
  {
    return toFirstCharLowerCase(paramName.replace("get", ""));
  }

  private String toFirstCharLowerCase(String s)
  {
    String first = s.substring(0,1);
    return first.toLowerCase() + s.substring(1);
  }

  private String splitCamelCase(String s)
  {
    return s.replaceAll(
        String.format("%s|%s|%s",
            "(?<=[A-Z])(?=[A-Z][a-z])",
            "(?<=[^A-Z])(?=[A-Z])",
            "(?<=[A-Za-z])(?=[^A-Za-z])"
        ),
        "_"
    ).toLowerCase();
  }

  /**
   * Convert object to Array
   */
  public String createAsArray(String className)
  {
    return "// Convert JSON Array to JS Array of " + className + "\n" +
        "function as" + className + "Array(json)\n" +
        "   {\n" +
        "   var array = [];\n" +
        "   for (var i = 0; i < json.length; i++)\n" +
        "   {\n" +
        "   array[i] = new " + className + "(json[i]);\n" +
        "   }\n" +
        "   return array;\n" +
        "   }";
  }

}
