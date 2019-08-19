package loan.repayment.DataObjects;

import java.time.LocalDateTime;

public class LoanApplicationDO {
	private Double loanAmount;
	private Double nominalRate;
	private int duration;
	private LocalDateTime startDate;

	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Double getNominalRate() {
		return nominalRate;
	}
	public void setNominalRate(Double nominalRate) {
		this.nominalRate = nominalRate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime localDateTime) {
		this.startDate = localDateTime;
	}

}
