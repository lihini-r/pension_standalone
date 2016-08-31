package org.pensions.data.reports;

public class GratuityCategory {

	public static final String STAT_CENTRAL = "Central";
	public static final String STAT_PROVINCIAL = "Provincial";
	public static final String STAT_MILITARY = "Military";
	
	public static final String BOC_NAME = "BOC";
	public static final String BOC_CODE = "7010";
	public static final String PB_NAME = "PEOPLES_BANK";
	public static final String PB_CODE = "7135";
	public static final String NSB_NAME = "NSB";
	public static final String NSB_CODE = "7719";
	
	public static final String STAT_CIVIL = "Civil";
	public static final String STAT_CIVIL_ID = "(Central|Provincial)";
	
	public static final String TYPE_ARMY = "Army";
	public static final String TYPE_NAVY = "Navy";
	public static final String TYPE_AIRFORCE = "Air Force";
	
	private String statutoryBody;
	private String statutoryId;
	private String bankName;
	private String bankCode;
	private String type;
	
	GratuityCategory(String statutoryBody, String bankName, String bankCode, String type){
		this.statutoryBody = statutoryBody;
		this.bankName = bankName;
		this.bankCode = bankCode;
		this.type = type;
	}
	
	/**
	 * @return the statutoryBody
	 */
	public String getStatutoryBody() {
		return statutoryBody;
	}
	/**
	 * @param statutoryBody the statutoryBody to set
	 */
	public void setStatutoryBody(String statutoryBody) {
		this.statutoryBody = statutoryBody;
	}
	/**
	 * @return the statutoryId
	 */
	public String getStatutoryId() {
		String candidateId = this.statutoryBody;
		if(!isMilitary()){
			candidateId = STAT_CIVIL_ID;
		}
		return candidateId;
	}
	/**
	 * @param statutoryId the statutoryId to set
	 */
	public void setStatutoryId(String statutoryId) {
		this.statutoryId = statutoryId;
	}
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}
	/**
	 * @param bankCode the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public String categoryId(){
		String categoryId = this.statutoryBody!=null?this.statutoryBody:"" + "-" + this.type!=null?this.type:"" + this.bankName!=null?this.bankName:"";
		return categoryId.toUpperCase();
	}
	
	public boolean isCentral(){
		return STAT_CENTRAL.equals(this.statutoryBody);
	}
	
	public boolean isMilitary(){
		return STAT_MILITARY.equals(this.statutoryBody);
	}
	
	public String categoryName(){
		String name = this.bankName;
		return name.toUpperCase();
	}
	public String categoryNamePro(){
		String namepro = this.statutoryBody!=null?this.statutoryBody:"" + this.bankName!=null?this.bankName:"";
		return namepro.toUpperCase();
	}
	public String categoryIdNew(){
		StringBuilder categoryId = new StringBuilder();
		if(this.statutoryBody!=null){
			categoryId.append(this.statutoryBody);
		}
		if(this.type!=null && !this.type.isEmpty()){
			categoryId.append("-");
			categoryId.append(this.type);
		}
		if(this.bankName!=null && !this.bankName.isEmpty()){
			categoryId.append("-");
			categoryId.append(this.bankName);
		}
		return categoryId.toString().toUpperCase();
	}
	
}
