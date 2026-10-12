package com.acxiom.crm.audit;
import org.springframework.stereotype.Service; import org.springframework.security.core.context.SecurityContextHolder;
@Service public class AuditService { private final AuditRepository repo; public AuditService(AuditRepository r){repo=r;} public void log(String module,String action,Long id,String details){AuditLog a=new AuditLog();var auth=SecurityContextHolder.getContext().getAuthentication();a.setUsername(auth==null?"system":auth.getName());a.setModule(module);a.setAction(action);a.setRecordId(id);a.setDetails(details);repo.save(a);} }
