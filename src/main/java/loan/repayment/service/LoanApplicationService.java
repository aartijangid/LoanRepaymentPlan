package loan.repayment.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import loan.repayment.DataObjects.LoanApplicationDO;
import loan.repayment.DataObjects.RepaymentPlanDO;
import loan.repayment.DataTransferObjects.LoanApplicationDTO;
import loan.repayment.DataTransferObjects.RepaymentPlanDTO;
import loan.repayment.model.RepaymentPlan;

@Service
public class LoanApplicationService {
	
	LoanApplicationDO loanApplicationDO;
	List<RepaymentPlanDO> repaymentPlanDOList;
	
	public List<RepaymentPlanDTO> serve(LoanApplicationDTO loanApplicationDTO){
		
		loanApplicationDO = converLoanApplicationDTOtoDO(loanApplicationDTO);
		
		repaymentPlanDOList = new ArrayList<RepaymentPlanDO>();
		RepaymentPlan repaymentPlan = new RepaymentPlan(loanApplicationDO);
		
		repaymentPlanDOList = repaymentPlan.generate();
		
		return convertRepaymentPlanDOtoDTO(repaymentPlanDOList);
	}
	
	
	private List<RepaymentPlanDTO> convertRepaymentPlanDOtoDTO(List<RepaymentPlanDO> repaymentPlanDOList2) {
		List<RepaymentPlanDTO> repaymentPlanDTOList = new ArrayList<RepaymentPlanDTO>();
		RepaymentPlanDTO repaymentPlanDTO;
		for(RepaymentPlanDO repaymentPlan : repaymentPlanDOList) {
			repaymentPlanDTO = new RepaymentPlanDTO();
			repaymentPlanDTO.setBorrowerPaymentAmount(repaymentPlan.getBorrowerPaymentAmount());
			repaymentPlanDTO.setDate(repaymentPlan.getDate());
			repaymentPlanDTO.setInitialOutstandingPrincipal(repaymentPlan.getInitialOutstandingPrincipal());
			repaymentPlanDTO.setInterest(repaymentPlan.getInterest());
			repaymentPlanDTO.setPrincipal(repaymentPlan.getPrincipal());
			repaymentPlanDTO.setRemainingOutstandingPrincipal(repaymentPlan.getRemainingOutstandingPrincipal());
			
			repaymentPlanDTOList.add(repaymentPlanDTO);
		}
		
		return repaymentPlanDTOList;
	}


	private LoanApplicationDO converLoanApplicationDTOtoDO(LoanApplicationDTO loanApplicationDTO) {
		LoanApplicationDO loanApplicationDO = new LoanApplicationDO();
		loanApplicationDO.setLoanAmount(Double.parseDouble(loanApplicationDTO.getLoanAmount()));
		loanApplicationDO.setNominalRate(Double.parseDouble(loanApplicationDTO.getNominalRate()));
		loanApplicationDO.setDuration(Integer.parseInt(loanApplicationDTO.getDuration()));
		loanApplicationDO.setStartDate(LocalDateTime.parse(loanApplicationDTO.getStartDate()));
		return loanApplicationDO;
	}
}
