package loan.repayment.calculate;

public class AnnuityCalculator {

	public static Double run(Double loanAmount, Double nominalRate, int duration) {
		// Normalise the rate as a percentage
		nominalRate = nominalRate / 100.0;

		// Divide the nominal rate by the number of months in a year
		double nominalRateByMonth = nominalRate / 12.0;

		// Formulate to calcuate annuity 
		double annuity = (loanAmount.doubleValue() * nominalRateByMonth) /
				(1 - Math.pow(1 + nominalRateByMonth, -duration));

		return annuity;
	}
}
