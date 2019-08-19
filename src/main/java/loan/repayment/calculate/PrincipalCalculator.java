package loan.repayment.calculate;

public class PrincipalCalculator {

	public static Double run(Double interest, Double annuity) {
		return (annuity - interest);
	}
}
