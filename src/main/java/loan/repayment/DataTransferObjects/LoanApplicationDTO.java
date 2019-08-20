package loan.repayment.DataTransferObjects;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class LoanApplicationDTO {
	@NotNull(message = "Loan amount is required")
	@Min(value = 1)
	private Double loanAmount;

	@NotNull(message = "Interest rate is required")
	@Min(value = 0)
	private Double nominalRate;

	@NotNull(message = "Duration is number of months required")
	@Min(value = 1)
	private Integer duration;

	@NotNull(message = "Start date is required")
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
}
