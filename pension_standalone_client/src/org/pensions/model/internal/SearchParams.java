package org.pensions.model.internal;

/**
 * Created by Dinesh Liyanage on 5/24/2016.
 */

/**
 * provides search params for pensioner search
 */
public class SearchParams {

    private String refNumber;
    private String wopNumber;
    private String nic;
    private String pensionNumber;
    private String name;

    public SearchParams() {
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getWopNumber() {
        return wopNumber;
    }

    public void setWopNumber(String wopNumber) {
        this.wopNumber = wopNumber;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPensionNumber() {
        return pensionNumber;
    }

    public void setPensionNumber(String pensionNumber) {
        this.pensionNumber = pensionNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
