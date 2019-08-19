package loan.repayment.calculate;

public class OutstandingPrincipalCalculator {

	public static Double run(Double initialOutstandingPrincipal, Double principal) {
		return (initialOutstandingPrincipal - principal);
	}
}
