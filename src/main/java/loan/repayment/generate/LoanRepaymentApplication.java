package loan.repayment.generate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "loan.repayment")
public class LoanRepaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanRepaymentApplication.class, args);
	}

}
