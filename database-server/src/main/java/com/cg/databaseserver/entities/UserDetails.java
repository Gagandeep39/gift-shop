package com.cg.databaseserver.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDetails {

  @Id
  private Long userDetailsId;

  @Column(length = 40)
  private String firstName;
  @Column(length = 40)
  private String lastName;

  private String emailId;
  @Column(length = 10)
  private String phoneNo;
  
  @Column(length = 80)
  private String securityQuestion;
  @Column(length = 10)
  private String securityAnswer;
  
  @OneToOne
  @JoinColumn(name = "addressId", referencedColumnName = "addressId", foreignKey = @ForeignKey(name = "FK_ADDR_ID"))
  private Address address;

  @OneToOne
  @JoinColumn(name = "userDetailsId", referencedColumnName = "userId", foreignKey = @ForeignKey(name = "FK_USER_ID"))
  @MapsId
  private User user;

}