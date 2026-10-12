package com.acxiom.crm.lead;
import jakarta.validation.Valid;
import com.acxiom.crm.audit.AuditService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class LeadController {
 private final LeadRepository repo; private final AuditService audit;
 private static final List<String> STATUSES=List.of("New","Contacted","Qualified","Unqualified","Converted","Lost");
 public LeadController(LeadRepository repo, AuditService audit){this.repo=repo;this.audit=audit;}
 @GetMapping("/leads") public String list(@RequestParam(required=false) String q,@RequestParam(required=false) String status,Model m){
  var leads=repo.findAll().stream().filter(l->q==null||q.isBlank()||(l.getName()!=null&&l.getName().toLowerCase().contains(q.toLowerCase()))||(l.getCompany()!=null&&l.getCompany().toLowerCase().contains(q.toLowerCase()))).filter(l->status==null||status.isBlank()||l.getStatus().equals(status)).toList();
  m.addAttribute("leads",leads);m.addAttribute("q",q==null?"":q);m.addAttribute("selectedStatus",status==null?"":status);m.addAttribute("statuses",STATUSES);return "leads";
 }
 @GetMapping("/leads/new") public String form(Model m){m.addAttribute("lead",new Lead());m.addAttribute("statuses",STATUSES);return "lead-form";}
 @PostMapping("/leads") public String create(@Valid @ModelAttribute Lead lead,BindingResult r,Model m){if(!STATUSES.contains(lead.getStatus()))r.rejectValue("status","invalid","Invalid status");if(r.hasErrors()){m.addAttribute("statuses",STATUSES);return "lead-form";}repo.save(lead);audit.log("LEAD","CREATE",lead.getId(),"Created lead: "+lead.getName());return "redirect:/leads";}
 @GetMapping("/leads/{id}/edit") public String edit(@PathVariable Long id,Model m){var lead=repo.findById(id).orElseThrow();m.addAttribute("lead",lead);m.addAttribute("statuses",STATUSES);return "lead-form";}
 @PostMapping("/leads/{id}") public String update(@PathVariable Long id,@Valid @ModelAttribute Lead f,BindingResult r,Model m){var old=repo.findById(id).orElseThrow();if(!STATUSES.contains(f.getStatus()))r.rejectValue("status","invalid","Invalid status");if(r.hasErrors()){m.addAttribute("statuses",STATUSES);return "lead-form";}old.setName(f.getName());old.setEmail(f.getEmail());old.setPhone(f.getPhone());old.setCompany(f.getCompany());old.setSource(f.getSource());old.setStatus(f.getStatus());old.setExpectedValue(f.getExpectedValue());old.setAssignedTo(f.getAssignedTo());repo.save(old);audit.log("LEAD","UPDATE",old.getId(),"Updated lead: "+old.getName());return "redirect:/leads";}
 @PostMapping("/leads/{id}/delete") public String delete(@PathVariable Long id){repo.deleteById(id);audit.log("LEAD","DELETE",id,"Deleted lead");return "redirect:/leads";}
}
