package org.pensions.enumarations;

public enum DialogType {

	SERVICE_PERIOD_CALCULATER("/org/pensions/view/pd3/ServicePeriodDialog.fxml"),
	STICKEY_NOTE("/org/pensions/view/StickeyNote.fxml"),
	VIEW_PENSIONS("/org/pensions/view/pd3/ViewPensions.fxml"),
	APLICATON_CONFIRM("/org/pensions/view/pd3/ApprovePensionView.fxml"),
	APLICATION_PRINT("/org/pensions/printModule/Template.fxml"),
	VIEW_GRATUITIES("/org/pensions/view/pd3/ViewGratuities.fxml"),
	RECEIVE_PENSION_APPLICATION("/org/pensions/view/pd3/ReceivedView.fxml"),
	VIEW_HISTORY("/org/pensions/view/pd3/HistoryView.fxml"),
	GENERATE_REPORT("/org/pensions/view/pd3/PaymentReportView.fxml"),
	ACCEPT_GRATUITIES("/org/pensions/view/pd3/AcceptGratuityView.fxml"),
	GENERATE_GRATUITY_REPORT("/org/pensions/view/pd3/GratuityReportViewController.fxml"),
	GENERATE_DEDUCTION_REPORT("/org/pensions/view/pd3/DeductionReportView.fxml"),
	PD3_SELECTION("/org/pensions/view/PD3Selection.fxml"),
	REPORT_SELECTION("/org/pensions/view/ReportSelection.fxml"),
	CONTACT_DETAILS("/org/pensions/view/pd3/ContactDetailsView.fxml"),
	SERVICE_DETAILS("/org/pensions/view/pd3/ServiceDetailsView.fxml");
	
	
	private String fxmlFileUrl;
	
	private DialogType(String value) {
		this.fxmlFileUrl = value;
	}
	
	public String getFxmlUrl() {
		return this.fxmlFileUrl;
	}
	
}
