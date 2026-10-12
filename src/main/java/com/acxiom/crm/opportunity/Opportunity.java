package com.acxiom.crm.opportunity;
import com.acxiom.crm.customer.Customer;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import java.time.LocalDate;
@Entity @Table(name="opportunities")
public class Opportunity {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Size(max=120) private String name;
 @ManyToOne(optional=false) @JoinColumn(name="customer_id",nullable=false) private Customer customer;
 @NotNull @DecimalMin("0.01") private Double amount;
 @NotNull @Min(0) @Max(100) private Integer probability;
 @NotNull private LocalDate expectedCloseDate;
 @NotBlank private String stage="Qualification";
 private String status="OPEN"; private String owner="sales"; private String source; @Column(length=2000) private String notes;
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getName(){return name;} public void setName(String v){name=v;} public Customer getCustomer(){return customer;} public void setCustomer(Customer v){customer=v;} public Double getAmount(){return amount;} public void setAmount(Double v){amount=v;} public Integer getProbability(){return probability;} public void setProbability(Integer v){probability=v;} public LocalDate getExpectedCloseDate(){return expectedCloseDate;} public void setExpectedCloseDate(LocalDate v){expectedCloseDate=v;} public String getStage(){return stage;} public void setStage(String v){stage=v;} public String getStatus(){return status;} public void setStatus(String v){status=v;} public String getOwner(){return owner;} public void setOwner(String v){owner=v;} public String getSource(){return source;} public void setSource(String v){source=v;} public String getNotes(){return notes;} public void setNotes(String v){notes=v;} public Double getWeightedValue(){return amount==null||probability==null?0.0:amount*probability/100.0;}
}
