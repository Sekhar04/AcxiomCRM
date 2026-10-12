package com.acxiom.crm.audit;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="audit_logs") public class AuditLog {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private String username; private String module; private String action; private Long recordId; private LocalDateTime timestamp=LocalDateTime.now(); private String result="SUCCESS"; @Column(length=2000) private String details;
 public Long getId(){return id;} public String getUsername(){return username;} public String getModule(){return module;} public String getAction(){return action;} public Long getRecordId(){return recordId;} public LocalDateTime getTimestamp(){return timestamp;} public String getResult(){return result;} public String getDetails(){return details;} public void setUsername(String v){username=v;} public void setModule(String v){module=v;} public void setAction(String v){action=v;} public void setRecordId(Long v){recordId=v;} public void setTimestamp(LocalDateTime v){timestamp=v;} public void setResult(String v){result=v;} public void setDetails(String v){details=v;}
}
