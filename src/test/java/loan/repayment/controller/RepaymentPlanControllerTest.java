package loan.repayment.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import loan.repayment.generate.LoanRepaymentApplication;

@RunWith(SpringRunner.class)
@WebMvcTest(RepaymentPlanController.class)
@ContextConfiguration(classes = LoanRepaymentApplication.class)
public class RepaymentPlanControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@Test
	public void when_LoanApplication_then_ShouldReturnOK() throws Exception {
		// Given 
		String generatePlanURL = "/generate-plan";

		// When
		ResultActions result = mockMVC.perform(get(generatePlanURL));

		// Then
		result.andExpect(status().isOk());

	}
}
