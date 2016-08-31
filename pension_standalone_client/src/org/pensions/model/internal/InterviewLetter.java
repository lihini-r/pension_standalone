package org.pensions.model.internal;

import org.pensions.model.PensionerDTO;

/**
 * Created by Dinesh Liyanage on 7/10/2016.
 */
public class InterviewLetter {
    private PensionerDTO pensioner;
    private String interviewDate;

    public InterviewLetter(String interviewDate, PensionerDTO pensioner) {
        this.interviewDate = interviewDate;
        this.pensioner = pensioner;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public PensionerDTO getPensioner() {
        return pensioner;
    }

    public void setPensioner(PensionerDTO pensioner) {
        this.pensioner = pensioner;
    }
}
