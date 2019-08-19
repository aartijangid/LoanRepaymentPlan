package loan.repayment.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import loan.repayment.DataObjects.LoanApplicationDO;
import loan.repayment.DataObjects.RepaymentPlanDO;
import loan.repayment.calculate.AnnuityCalculator;
import loan.repayment.calculate.InterestCalculator;
import loan.repayment.calculate.OutstandingPrincipalCalculator;
import loan.repayment.calculate.PrincipalCalculator;

public class RepaymentPlan {

	Double loanAmount; 
	Double nominalRate;
	int duration;
	LocalDateTime startDate;

	public RepaymentPlan(LoanApplicationDO loanApplicationDO) {
		this.loanAmount = loanApplicationDO.getLoanAmount();
		this.nominalRate = loanApplicationDO.getNominalRate();
		this.duration = loanApplicationDO.getDuration();
		this.startDate = loanApplicationDO.getStartDate();
	}

	DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
	DecimalFormat df = new DecimalFormat("#.##", symbols);

	public List<RepaymentPlanDO> generate(){
		List<RepaymentPlanDO> repaymentPlanDOList = new ArrayList<RepaymentPlanDO>();
		Double initialOutstandingPrincipal = loanAmount;

		for(int month = 0; month < duration; month++) {

			RepaymentPlanDO monthlyRepaymentPlanDO = new RepaymentPlanDO();
			monthlyRepaymentPlanDO = generatePlanForAMonth(initialOutstandingPrincipal, month);

			Double remainingOutstandingPrincipal = Double.parseDouble(monthlyRepaymentPlanDO.getRemainingOutstandingPrincipal());
			initialOutstandingPrincipal = remainingOutstandingPrincipal;

			repaymentPlanDOList.add(monthlyRepaymentPlanDO);
		}

		return repaymentPlanDOList;
	}

	private RepaymentPlanDO generatePlanForAMonth(Double currentOutstandingPrincipal, int month) {

		RepaymentPlanDO monthlyRepaymentPlanDO = new RepaymentPlanDO();

		// Month date
		monthlyRepaymentPlanDO.setDate(startDate.plusMonths(month));

		// Interest
		Double currentInterestAmount = InterestCalculator.run(nominalRate, currentOutstandingPrincipal);
		monthlyRepaymentPlanDO.setInterest(df.format(currentInterestAmount));

		// Annuity or BorrowerPaymentAmount
		Double currentAnnuity = AnnuityCalculator.run(loanAmount, nominalRate, duration);
		monthlyRepaymentPlanDO.setBorrowerPaymentAmount(df.format(currentAnnuity));

		// Principal
		Double currentPrincipal = PrincipalCalculator.run(currentInterestAmount, currentAnnuity);
		monthlyRepaymentPlanDO.setPrincipal(df.format(currentPrincipal));

		// Initial Outstanding Principal
		monthlyRepaymentPlanDO.setInitialOutstandingPrincipal(df.format(currentOutstandingPrincipal));

		// Remaininng Outstanding Principal
		Double remainingOutstandingPrincipal = OutstandingPrincipalCalculator.run(currentOutstandingPrincipal, currentPrincipal);
		monthlyRepaymentPlanDO.setRemainingOutstandingPrincipal(df.format(remainingOutstandingPrincipal));

		return monthlyRepaymentPlanDO;
	}

}
