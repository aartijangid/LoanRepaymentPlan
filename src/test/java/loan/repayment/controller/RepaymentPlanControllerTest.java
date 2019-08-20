package loan.repayment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import loan.repayment.DataTransferObjects.LoanApplicationDTO;
import loan.repayment.DataTransferObjects.RepaymentPlanDTO;
import loan.repayment.generate.LoanRepaymentApplication;
import loan.repayment.service.LoanApplicationService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = LoanRepaymentApplication.class)
@WebMvcTest
@AutoConfigureMockMvc
public class RepaymentPlanControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private LoanApplicationService loanApplicationService;

	@Test
	public void when_LoanApplication_then_ShouldReturnOK() throws Exception {
		// Given
		String generatePlanURL = "/generate-plan";
		JSONObject loanApplicationJSONObject = new JSONObject();

		loanApplicationJSONObject.put("loanAmount", "5000.00");
		loanApplicationJSONObject.put("nominalRate", "5.0");
		loanApplicationJSONObject.put("duration", 24);
		loanApplicationJSONObject.put("startDate", "2018-01-01T00:00:01Z");

		LoanApplicationDTO loanApplicationDTO = new LoanApplicationDTO();
		loanApplicationDTO.setLoanAmount(5000.00);
		loanApplicationDTO.setNominalRate(5.0);
		loanApplicationDTO.setDuration(24);
		loanApplicationDTO.setStartDate(LocalDateTime.now());

		when(loanApplicationService.serve(loanApplicationDTO)).thenReturn(new ArrayList<RepaymentPlanDTO>());

		// When
		ResultActions result = mockMVC.perform(
				post(generatePlanURL)
				.content(loanApplicationJSONObject.toString())
				.contentType(MediaType.APPLICATION_JSON_UTF8));

		// Then
		result.andExpect(status().isOk());
	}

	@Test
	public void when_loanAmount_Missing_then_ShouldReturnError() throws Exception {
		// Given
		String generatePlanURL = "/generate-plan";
		JSONObject loanApplicationJSONObject = new JSONObject();

		loanApplicationJSONObject.put("nominalRate", "5.0");
		loanApplicationJSONObject.put("duration", 24);
		loanApplicationJSONObject.put("startDate", "2018-01-01T00:00:01Z");

		// When
		ResultActions result = mockMVC.perform(
				post(generatePlanURL)
				.content(loanApplicationJSONObject.toString())
				.contentType(MediaType.APPLICATION_JSON_UTF8));

		// Then
		result
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.loanAmount", is("Loan amount is required")));
	}

	@Test
	public void when_loanAmount_is_zero_then_ShouldReturnError() throws Exception {
		// Given
		String generatePlanURL = "/generate-plan";
		JSONObject loanApplicationJSONObject = new JSONObject();

		loanApplicationJSONObject.put("loanAmount", "0");
		loanApplicationJSONObject.put("nominalRate", "5.0");
		loanApplicationJSONObject.put("duration", 2);
		loanApplicationJSONObject.put("startDate", "2018-01-01T00:00:01Z");

		// When
		ResultActions result = mockMVC.perform(
				post(generatePlanURL)
				.content(loanApplicationJSONObject.toString())
				.contentType(MediaType.APPLICATION_JSON_UTF8));

		// Then
		result
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.loanAmount", is("must be greater than or equal to 1")));
	}

	@Test
	public void when_nominalRate_Missing_then_ShouldReturnError() throws Exception {
		// Given
		String generatePlanURL = "/generate-plan";
		JSONObject loanApplicationJSONObject = new JSONObject();

		loanApplicationJSONObject.put("loanAmount", "5000.00");
		loanApplicationJSONObject.put("duration", 24);
		loanApplicationJSONObject.put("startDate", "2018-01-01T00:00:01Z");

		// When
		ResultActions result = mockMVC.perform(
				post(generatePlanURL)
				.content(loanApplicationJSONObject.toString())
				.contentType(MediaType.APPLICATION_JSON_UTF8));

		// Then
		result
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.nominalRate", is("Interest rate is required")));
	}

	@Test
	public void when_nominalRate_is_negative_then_ShouldReturnError() throws Exception {
		// Given
		String generatePlanURL = "/generate-plan";
		JSONObject loanApplicationJSONObject = new JSONObject();

		loanApplicationJSONObject.put("loanAmount", "100");
		loanApplicationJSONObject.put("nominalRate", "-0.5");
		loanApplicationJSONObject.put("duration", 2);
		loanApplicationJSONObject.put("startDate", "2018-01-01T00:00:01Z");

		// When
		ResultActions result = mockMVC.perform(
				post(generatePlanURL)
				.content(loanApplicationJSONObject.toString())
				.contentType(MediaType.APPLICATION_JSON_UTF8));

		// Then
		result
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.nominalRate", is("must be greater than or equal to 0")));
	}

	@Test
	public void when_duration_Missing_then_ShouldReturnError() throws Exception {
		// Given
		String generatePlanURL = "/generate-plan";
		JSONObject loanApplicationJSONObject = new JSONObject();

		loanApplicationJSONObject.put("loanAmount", "5000.00");
		loanApplicationJSONObject.put("nominalRate", "5.0");
		loanApplicationJSONObject.put("startDate", "2018-01-01T00:00:01Z");

		// When
		ResultActions result = mockMVC.perform(
				post(generatePlanURL)
				.content(loanApplicationJSONObject.toString())
				.contentType(MediaType.APPLICATION_JSON_UTF8));

		// Then
		result
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.duration", is("Duration is number of months required")));
	}

	@Test
	public void when_duration_is_negative_then_ShouldReturnError() throws Exception {
		// Given
		String generatePlanURL = "/generate-plan";
		JSONObject loanApplicationJSONObject = new JSONObject();

		loanApplicationJSONObject.put("loanAmount", "100");
		loanApplicationJSONObject.put("nominalRate", "1.5");
		loanApplicationJSONObject.put("duration", 0);
		loanApplicationJSONObject.put("startDate", "2018-01-01T00:00:01Z");

		// When
		ResultActions result = mockMVC.perform(
				post(generatePlanURL)
				.content(loanApplicationJSONObject.toString())
				.contentType(MediaType.APPLICATION_JSON_UTF8));

		// Then
		result
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.duration", is("must be greater than or equal to 1")));
	}

	@Test
	public void when_startDate_Missing_then_ShouldReturnError() throws Exception {
		// Given
		String generatePlanURL = "/generate-plan";
		JSONObject loanApplicationJSONObject = new JSONObject();

		loanApplicationJSONObject.put("loanAmount", "5000.00");
		loanApplicationJSONObject.put("nominalRate", "5.0");
		loanApplicationJSONObject.put("duration", 24);

		// When
		ResultActions result = mockMVC.perform(
				post(generatePlanURL)
				.content(loanApplicationJSONObject.toString())
				.contentType(MediaType.APPLICATION_JSON_UTF8));

		// Then
		result
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.startDate", is("Start date is required")));
	}
}
