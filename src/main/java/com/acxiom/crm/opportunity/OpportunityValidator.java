package com.acxiom.crm.opportunity;
import org.springframework.stereotype.Component; import java.time.LocalDate; import java.util.List;
@Component public class OpportunityValidator {
 private static final List<String> STAGES=List.of("Qualification","Proposal","Negotiation","Won","Lost");
 public String validate(Opportunity o){if(o.getAmount()==null||o.getAmount()<=0)return "Amount must be greater than zero.";if(o.getProbability()==null||o.getProbability()<0||o.getProbability()>100)return "Probability must be between 0 and 100.";if(o.getStage()==null||!STAGES.contains(o.getStage()))return "Select a valid stage.";if(o.getExpectedCloseDate()==null)return "Expected close date is required.";if(!List.of("Won","Lost").contains(o.getStage())&&o.getExpectedCloseDate().isBefore(LocalDate.now()))return "Expected close date cannot be in the past for an active opportunity.";return null;}
}
