package loan.repayment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RepaymentPlanController {
	
	@RequestMapping(value = "/generate-plan", method = RequestMethod.GET)
	public String generateRepaymentPlan() {
		
		return "OK";
	}
}
