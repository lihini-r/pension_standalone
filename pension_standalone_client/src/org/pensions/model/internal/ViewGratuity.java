package org.pensions.model.internal;

import org.pensions.model.GratuityDTO;
import org.pensions.model.PensionerDTO;

public class ViewGratuity {
	private PensionerDTO pensioner;
    private GratuityDTO gto;
    
    public ViewGratuity(GratuityDTO gto, PensionerDTO pensioner) {
        this.pensioner = pensioner;
        this.gto = gto;
    }
    
    public PensionerDTO getPensioner() {
        return pensioner;
    }

    public void setPensioner(PensionerDTO pensioner) {
        this.pensioner = pensioner;
    }
    
    public GratuityDTO getGratuity() {
        return gto;
    }

    public void setGratuity(GratuityDTO gto) {
        this.gto = gto;
    }

}
