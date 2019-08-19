package loan.repayment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import loan.repayment.DataTransferObjects.LoanApplicationDTO;
import loan.repayment.DataTransferObjects.RepaymentPlanDTO;
import loan.repayment.service.LoanApplicationService;


@RestController
public class RepaymentPlanController {
	
	@PostMapping(value = "/generate-plan", consumes = "application/json", produces = "application/json")
	public List<RepaymentPlanDTO> generateRepaymentPlan(@RequestBody LoanApplicationDTO loanApplicationDTO) {
		LoanApplicationService loanApplicationService = new LoanApplicationService();
		
		return loanApplicationService.serve(loanApplicationDTO);
	}
}
