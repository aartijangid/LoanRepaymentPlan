package loan.repayment.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import loan.repayment.DataObjects.RepaymentPlanDO;
import loan.repayment.DataTransferObjects.LoanApplicationDTO;
import loan.repayment.DataTransferObjects.RepaymentPlanDTO;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationServiceTest {

	private LoanApplicationService loanApplicationService;

	private LoanApplicationDTO loanApplicationDTO = new LoanApplicationDTO();
	
	private String LOAN_AMOUNT = "5000.00";
	private String NOMINAL_INTEREST_RATE = "5.0";
	private String DURATION_IN_MONTHS = "2";
	private String START_DATE = "2018-01-01T00:00:01";

	@Before
	public void setUp() {
		loanApplicationService = new LoanApplicationService();
		loanApplicationDTO.setLoanAmount(LOAN_AMOUNT);
		loanApplicationDTO.setNominalRate(NOMINAL_INTEREST_RATE);
		loanApplicationDTO.setDuration(DURATION_IN_MONTHS);
		loanApplicationDTO.setStartDate(START_DATE);
	}

	@Test
	public void givenLoanApplicationDTOShouldReturnListOfRepaymentDTOs() {
		// when
		List<RepaymentPlanDTO> repaymentPlanDTOList = loanApplicationService.serve(loanApplicationDTO);

		// then
		Assert.assertEquals(Integer.parseInt(DURATION_IN_MONTHS), repaymentPlanDTOList.size());
	}
}
