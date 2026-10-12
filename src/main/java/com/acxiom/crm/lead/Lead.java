package com.acxiom.crm.lead;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity @Table(name="leads")
public class Lead {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Size(max=100) private String name;
 @Email private String email;
 private String phone, company;
 @NotBlank private String source;
 @NotBlank private String status="New";
 @NotNull @DecimalMin("0.0") @DecimalMax("1000000000.0") private Double expectedValue=0.0;
 private String assignedTo;
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getName(){return name;} public void setName(String v){name=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
 public String getCompany(){return company;} public void setCompany(String v){company=v;}
 public String getSource(){return source;} public void setSource(String v){source=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
 public Double getExpectedValue(){return expectedValue;} public void setExpectedValue(Double v){expectedValue=v;}
 public String getAssignedTo(){return assignedTo;} public void setAssignedTo(String v){assignedTo=v;}
}
