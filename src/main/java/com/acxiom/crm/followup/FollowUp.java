package com.acxiom.crm.followup;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import java.time.LocalDate; import java.time.LocalDateTime;
@Entity @Table(name="follow_ups") public class FollowUp {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Size(max=150) private String subject;
 @NotNull private LocalDate followUpDate;
 @NotBlank private String relatedType="Customer";
 @NotNull private Long relatedId;
 @NotBlank private String type="Call";
 @NotBlank private String status="Planned";
 private String assignedUser="sales";
 @Column(length=2000) private String notes;
 private LocalDateTime createdAt=LocalDateTime.now();
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getSubject(){return subject;} public void setSubject(String v){subject=v;} public LocalDate getFollowUpDate(){return followUpDate;} public void setFollowUpDate(LocalDate v){followUpDate=v;} public String getRelatedType(){return relatedType;} public void setRelatedType(String v){relatedType=v;} public Long getRelatedId(){return relatedId;} public void setRelatedId(Long v){relatedId=v;} public String getType(){return type;} public void setType(String v){type=v;} public String getStatus(){return status;} public void setStatus(String v){status=v;} public String getAssignedUser(){return assignedUser;} public void setAssignedUser(String v){assignedUser=v;} public String getNotes(){return notes;} public void setNotes(String v){notes=v;} public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
 public boolean isOverdue(){return "Planned".equals(status)&&followUpDate!=null&&followUpDate.isBefore(LocalDate.now());}
}
