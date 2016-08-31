package org.pensions.pensionstatemanager;

import org.pensions.data.dao.PensionsDAO;
import org.pensions.model.Hetoes;
import org.pensions.model.PensionDTO;
import org.pensions.model.PensionerDTO;
import org.pensions.view.util.NotificationManager;

public class UpdateStatus {
	
	public void updatePensionState(PensionerDTO dto, boolean verify){
		Hetoes activePension = dto.getPensions().stream()
				.filter(pension -> pension.getRel().equals("ACTIVE_PENSION"))
				.findFirst().orElse(null);
		PensionDTO pension = new PensionsDAO(dto.getId()).getPension(Long.valueOf(activePension.getUrl()));
		PensionsDAO dao = new PensionsDAO(dto.getId());
		dao.updatePensionStatus(verify, Long.valueOf(activePension.getUrl()));
		NotificationManager.info("Successfully Updated", "Successfully Updated");
	}
	
	

}
