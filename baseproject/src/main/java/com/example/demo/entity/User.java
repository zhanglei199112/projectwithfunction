package com.example.demo.entity;

import com.example.demo.handlers.AddressTypeHandler;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "tb_user_")
@Getter
@Setter
public class User implements Serializable {
  @Id
  @Column(name = "id_")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name_")
  private String name;

  @Column(name = "age_")
  private Integer age;

  @Column(name="address_")
//  @ColumnType(typeHandler = AddressTypeHandler.class)
  private Address address;

  @Column(name="sex_")
  private SexEnum sex;

  private static final long serialVersionUID = 1L;

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", address=" + address +
        ", sex=" + sex +
        '}';
  }
}
