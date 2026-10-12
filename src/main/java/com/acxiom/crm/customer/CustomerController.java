package com.acxiom.crm.customer;
import jakarta.validation.Valid;
import com.acxiom.crm.audit.AuditService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Controller
public class CustomerController {
  private final CustomerRepository repo;
  private final AuditService audit;
  public CustomerController(CustomerRepository repo, AuditService audit){this.repo=repo; this.audit=audit;}

  @GetMapping("/") public String dashboard(Model model) {
    model.addAttribute("customerCount", repo.count());
    model.addAttribute("customers", repo.findAll());
    model.addAttribute("leadCount", 0);
    model.addAttribute("opportunityCount", 0);
    model.addAttribute("followupCount", 0);
    return "dashboard";
  }
  @GetMapping("/customers") public String list(@RequestParam(required=false) String q, Model model) {
    List<Customer> all = repo.findAll();
    if(q != null && !q.isBlank()) all = all.stream().filter(c ->
      (c.getName()!=null && c.getName().toLowerCase().contains(q.toLowerCase())) ||
      (c.getEmail()!=null && c.getEmail().toLowerCase().contains(q.toLowerCase())) ||
      (c.getCompany()!=null && c.getCompany().toLowerCase().contains(q.toLowerCase()))).toList();
    model.addAttribute("customers", all); model.addAttribute("q", q == null ? "" : q);
    return "customers";
  }
  @GetMapping("/customers/new") public String createForm(Model model) {
    model.addAttribute("customer", new Customer()); return "customer-form";
  }
  @PostMapping("/customers") public String create(@Valid @ModelAttribute Customer customer, BindingResult result) {
    if(repo.existsByEmailIgnoreCase(customer.getEmail())) result.rejectValue("email","duplicate","Email already exists");
    if(repo.existsByPhone(customer.getPhone())) result.rejectValue("phone","duplicate","Phone already exists");
    if(result.hasErrors()) return "customer-form";
    repo.save(customer); audit.log("CUSTOMER","CREATE",customer.getId(),"Created customer: "+customer.getName()); return "redirect:/customers";
  }
  @GetMapping("/customers/{id}/edit") public String editForm(@PathVariable Long id, Model model) {
    model.addAttribute("customer", repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    return "customer-form";
  }
  @PostMapping("/customers/{id}") public String update(@PathVariable Long id, @Valid @ModelAttribute Customer form, BindingResult result) {
    Customer existing = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    if(repo.existsByEmailIgnoreCase(form.getEmail()) && !existing.getEmail().equalsIgnoreCase(form.getEmail())) result.rejectValue("email","duplicate","Email already exists");
    if(repo.existsByPhone(form.getPhone()) && !existing.getPhone().equals(form.getPhone())) result.rejectValue("phone","duplicate","Phone already exists");
    if(result.hasErrors()) return "customer-form";
    existing.setName(form.getName()); existing.setEmail(form.getEmail()); existing.setPhone(form.getPhone());
    existing.setCompany(form.getCompany()); existing.setStatus(form.getStatus()); repo.save(existing); audit.log("CUSTOMER","UPDATE",existing.getId(),"Updated customer: "+existing.getName());
    return "redirect:/customers";
  }
  @PostMapping("/customers/{id}/delete") public String delete(@PathVariable Long id) {
    repo.deleteById(id); audit.log("CUSTOMER","DELETE",id,"Deleted customer"); return "redirect:/customers";
  }
}
