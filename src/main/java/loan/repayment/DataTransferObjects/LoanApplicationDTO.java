package loan.repayment.DataTransferObjects;

public class LoanApplicationDTO {
	private String loanAmount = "";
	private String nominalRate = "";
	private String duration = "";
	private String startDate = "";
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getNominalRate() {
		return nominalRate;
	}
	public void setNominalRate(String nominalRate) {
		this.nominalRate = nominalRate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}
