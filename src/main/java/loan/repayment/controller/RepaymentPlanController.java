package loan.repayment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import loan.repayment.DataTransferObjects.LoanApplicationDTO;


@RestController
public class RepaymentPlanController {
	
	@PostMapping(value = "/generate-plan", consumes = "application/json", produces = "application/json")
	public String generateRepaymentPlan(@RequestBody LoanApplicationDTO loanApplicationDTO) {
		
		return "OK";
	}
}
