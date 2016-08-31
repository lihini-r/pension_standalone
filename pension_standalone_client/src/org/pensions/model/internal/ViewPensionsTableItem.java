package org.pensions.model.internal;

/**
 * Created by Dinesh Liyanage on 5/25/2016.
 */
public class ViewPensionsTableItem {

    private Long pensionNumber;
    private String type;
    private String office;
    private String circular;
    private String state;

    public ViewPensionsTableItem() {
    }

    public String getCircular() {
        return circular;
    }

    public void setCircular(String circular) {
        this.circular = circular;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Long getPensionNumber() {
        return pensionNumber;
    }

    public void setPensionNumber(Long pensionNumber) {
        this.pensionNumber = pensionNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
