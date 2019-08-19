package loan.repayment.calculate;

public class InterestCalculator {

	public static Double run(Double nominalRate, Double initialOutstandingPrincipal) {

		return ((nominalRate * 30 * initialOutstandingPrincipal) / 360)/100;
	}
}
