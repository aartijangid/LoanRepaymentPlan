package loan.repayment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import loan.repayment.DataTransferObjects.LoanApplicationDTO;
import loan.repayment.DataTransferObjects.RepaymentPlanDTO;
import loan.repayment.service.LoanApplicationService;


@RestController
@Validated
public class RepaymentPlanController {

	@Autowired
	private LoanApplicationService loanApplicationService;

	@PostMapping(value = "/generate-plan", consumes = "application/json", produces = "application/json")
	public List<RepaymentPlanDTO> generateRepaymentPlan(@Valid @RequestBody LoanApplicationDTO loanApplicationDTO) {
		return loanApplicationService.serve(loanApplicationDTO);
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
