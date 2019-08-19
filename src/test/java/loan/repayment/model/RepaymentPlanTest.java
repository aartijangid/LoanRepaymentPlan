package loan.repayment.model;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import loan.repayment.DataObjects.LoanApplicationDO;
import loan.repayment.DataObjects.RepaymentPlanDO;
import loan.repayment.model.RepaymentPlan;

public class RepaymentPlanTest {
	@Test
	public void givenLoanApplicationSouldReturnRepaymentPlan() {

		Double loanAmount = 5000.00;
		Double nominalRate = 5.0;
		int duration = 24;
		LocalDateTime startDate = LocalDateTime.parse("2018-01-01T00:00:01");

		LoanApplicationDO loanApplicationDO = new LoanApplicationDO();
		loanApplicationDO.setLoanAmount(loanAmount);
		loanApplicationDO.setNominalRate(nominalRate);
		loanApplicationDO.setDuration(duration);
		loanApplicationDO.setStartDate(startDate);

		RepaymentPlan repaymentPlan = new RepaymentPlan(loanApplicationDO);

		List<RepaymentPlanDO> repaymentPlanDOList = repaymentPlan.generate();

		repaymentPlanDOList.forEach(repayment -> System.out.println(repayment));

		//Assert.assertNotNull(repaymentPlanDOList);
		Assert.assertEquals(duration, repaymentPlanDOList.size());
	}
}
