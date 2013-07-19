package com.arm.nimbus.sccm.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author vicben01
 */
@Entity
public class Product
{
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy = "increment")
  private Long id;

  private String name;

  private Date date;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "PRODUCT_DATE")
  public Date getDate()
  {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
