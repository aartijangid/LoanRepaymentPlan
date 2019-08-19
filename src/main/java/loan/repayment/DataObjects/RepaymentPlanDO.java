package loan.repayment.DataObjects;

import java.time.LocalDateTime;

public class RepaymentPlanDO {
	private String borrowerPaymentAmount;
	private LocalDateTime date;
	private String initialOutstandingPrincipal;
	private String interest;
	private String principal;
	private String remainingOutstandingPrincipal;

	public String getBorrowerPaymentAmount() {
		return borrowerPaymentAmount;
	}
	public void setBorrowerPaymentAmount(String borrowerPaymentAmount) {
		this.borrowerPaymentAmount = borrowerPaymentAmount;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getInitialOutstandingPrincipal() {
		return initialOutstandingPrincipal;
	}
	public void setInitialOutstandingPrincipal(String initialOutstandingPrincipal) {
		this.initialOutstandingPrincipal = initialOutstandingPrincipal;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getRemainingOutstandingPrincipal() {
		return remainingOutstandingPrincipal;
	}
	public void setRemainingOutstandingPrincipal(String remainingOutstandingPrincipal) {
		this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\r\n");
		builder.append("[borrowerPaymentAmount=");
		builder.append(borrowerPaymentAmount);
		builder.append(", date=");
		builder.append(date);
		builder.append(", initialOutstandingPrincipal=");
		builder.append(initialOutstandingPrincipal);
		builder.append(", interest=");
		builder.append(interest);
		builder.append(", principal=");
		builder.append(principal);
		builder.append(", remainingOutstandingPrincipal=");
		builder.append(remainingOutstandingPrincipal);
		builder.append("]");
		return builder.toString();
	}

}
