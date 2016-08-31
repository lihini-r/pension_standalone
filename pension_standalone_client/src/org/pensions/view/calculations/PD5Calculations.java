/**
 * 
 */
package org.pensions.view.calculations;

import java.text.DecimalFormat;
import java.time.Period;

import org.pensions.data.dao.PercentageDAO;
import org.pensions.model.Percentage;

import javafx.scene.text.Text;

/**
 * @author Kani
 *
 */
public class PD5Calculations {
	
	DecimalFormat df = new DecimalFormat("0.00");
	
	public Percentage getReducedUnreducedPercentages(Text serviceYears, Text serviceMonths, Text grossSalary) {
		PercentageDAO dao = new PercentageDAO();
		Percentage percentage = dao.getPercentage(Integer.valueOf(serviceYears.getText()), Integer.parseInt(serviceMonths.getText()), Double.valueOf(grossSalary.getText()));
		return percentage;
	}
	
	/*
	 *@Method for calculation no pay leaves
	 */
	public int[] setNoPayLeaves(Text noPayYears, Text noPayMonths, Text noPayDays, Text setOffYears, Text setOffMonths, Text setOffDays) {
		Period noPay = Period.of(Integer.parseInt(noPayYears.getText()),Integer.parseInt(noPayMonths.getText()),Integer.parseInt(noPayDays.getText()));
		Period setOff = Period.of(Integer.parseInt(setOffYears.getText()),Integer.parseInt(setOffMonths.getText()),Integer.parseInt(setOffDays.getText()));
		Period netNoPay = noPay.minus(setOff);
		
		return new int[] {netNoPay.getYears(), netNoPay.getMonths(), netNoPay.getDays()};
	}

	/*
	 * @Calculation for deductable percentage for each 6 months 1%
	 * @return type double
	 */
	public double calculateDeductablePercentage(org.pensions.model.Period p) {
		double deductablePercentage = 0.0;
		
		if(p.getMonths() >= 0 && p.getMonths() < 6) {
			deductablePercentage = (25-p.getYears())*2;
		} else if(p.getMonths() >= 6) {
			deductablePercentage = (25-(p.getYears()+0.5))*2;
		} else {
			deductablePercentage = 0.0;
		}

		return deductablePercentage;
	}

	/*
	 * @Calculation for deductable percentage foe each no pay months 0.2%
	 * @return type double
	 */
	public double calculateDeductablePercentage_NoPayMonths(int netNopayYears, int netNopayMonths, int netNopayDays) {
		double deductablePercentage = 0.0;

		if(netNopayDays > 0) {
			netNopayDays = 1;
		}
		
		deductablePercentage = (netNopayYears*12 + netNopayMonths + netNopayDays)*0.2;
		return Double.parseDouble(df.format(deductablePercentage));
	}

	/*
	 * @Calculate for net percentage
	 * @return type double
	 */
	public double[] calculateNetPercentage(Text serviceYears, Text serviceMonths, Text serviceDays, Double assignReducedPercentage, Double assignUnReducedPercentage, Double calculateDeductablePercentage_NoPayMonths) {
		
		org.pensions.model.Period servicePeriod = new org.pensions.model.Period();
		servicePeriod.setYears(Integer.parseInt(serviceYears.getText()));
		servicePeriod.setMonths(Integer.parseInt(serviceMonths.getText()));
		servicePeriod.setDays(Integer.parseInt(serviceDays.getText()));
		
		double netPercentageReduced = 0.0;
		double netPercentageUnreduced = 0.0;
		
		if(Integer.parseInt(serviceYears.getText()) >= 25) {
			netPercentageReduced = assignReducedPercentage - calculateDeductablePercentage_NoPayMonths;
			netPercentageUnreduced = assignUnReducedPercentage - calculateDeductablePercentage_NoPayMonths;
		} else {
			netPercentageReduced = assignReducedPercentage - calculateDeductablePercentage_NoPayMonths - calculateDeductablePercentage(servicePeriod);
			netPercentageUnreduced = assignUnReducedPercentage - calculateDeductablePercentage_NoPayMonths - calculateDeductablePercentage(servicePeriod);
		}
		
		return new double[] {netPercentageReduced,netPercentageUnreduced};
	}

	/*
	 * @Calculation for net gratuity amount
	 * @return type double
	 */
	public double calculateNetGratuity(Text govDeductions, Double calculateGrossAnnualSalary, Double calculateGrossGratuityAmount) {
		double netGratuity = 0.0;

		netGratuity = (calculateGrossAnnualSalary > calculateGrossGratuityAmount) ? calculateGrossAnnualSalary : calculateGrossGratuityAmount - Double.parseDouble(govDeductions.getText());

		return netGratuity;
	}

	/*
	 * @Calculation for gross annual salary
	 * @return type double
	 */
	public double calculateGrossAnnualSalary(Text annualSalary, Text allowances) {
		double grossAnnualSalary = 0.0;
		
		if(annualSalary.getText().isEmpty() == false && allowances.getText().isEmpty() == false){
			grossAnnualSalary = Double.parseDouble(annualSalary.getText()) + Double.parseDouble(allowances.getText());
		} else {
			grossAnnualSalary = 0.00;
		}

		return grossAnnualSalary;
	}

	/*
	 * @Calculationh for gross gratuity amount
	 * @return type double
	 */
	public double calculateGrossGratuityAmount(double[] calculateNetPercentage, double calculateGrossAnnualSalary) {
		
		double grossGratuity = 0.0;
		double[] percentages = calculateNetPercentage;
		grossGratuity = calculateGrossAnnualSalary * (percentages[1])/100*2;
		return grossGratuity;
		
	}


}