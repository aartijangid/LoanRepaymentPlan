package loan.repayment.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
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
		JSONObject loanApplicationJSONObject = new JSONObject();

		loanApplicationJSONObject.put("loanAmount", "5000");
		loanApplicationJSONObject.put("nominalRate", "5.0");
		loanApplicationJSONObject.put("duration", "24");
		loanApplicationJSONObject.put("startDate", "2018-01-01T00:00:01Z");

		// When
		ResultActions result = mockMVC.perform(post(generatePlanURL)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(loanApplicationJSONObject.toString()));

		// Then
		result.andExpect(status().isOk());

	}
}
