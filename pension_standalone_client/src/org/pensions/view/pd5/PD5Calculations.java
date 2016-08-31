package org.pensions.view.pd5;

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
	
	public Percentage getReducedUnreducedPercentages(String[] inputFields) {
		PercentageDAO dao = new PercentageDAO();
		Percentage percentage = dao.getPercentage(Integer.valueOf(inputFields[0]), Integer.parseInt(inputFields[1]), Double.valueOf(inputFields[9]));
		return percentage;
	}
	
	/*
	 *@Method for calculation no pay leaves
	 */
	public int[] setNoPayLeaves(String[] inputFields) {
		Period noPay = Period.of(Integer.parseInt(inputFields[3]),Integer.parseInt(inputFields[4]),Integer.parseInt(inputFields[5]));
		Period setOff = Period.of(Integer.parseInt(inputFields[6]),Integer.parseInt(inputFields[7]),Integer.parseInt(inputFields[8]));
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
	public double[] calculateNetPercentage(String[] inputFields, Double assignReducedPercentage, Double assignUnReducedPercentage, Double calculateDeductablePercentage_NoPayMonths) {
		
		org.pensions.model.Period servicePeriod = new org.pensions.model.Period();
		servicePeriod.setYears(Integer.parseInt(inputFields[0]));
		servicePeriod.setMonths(Integer.parseInt(inputFields[1]));
		servicePeriod.setDays(Integer.parseInt(inputFields[2]));
		
		double netPercentageReduced = 0.0;
		double netPercentageUnreduced = 0.0;
		
		if(Integer.parseInt(inputFields[0]) >= 25) {
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
	public double calculateNetGratuity(String[] inputFields, Double calculateGrossAnnualSalary, Double calculateGrossGratuityAmount) {
		double netGratuity = 0.0;

		netGratuity = (calculateGrossAnnualSalary > calculateGrossGratuityAmount) ? calculateGrossAnnualSalary : calculateGrossGratuityAmount - Double.parseDouble(inputFields[10]);

		return netGratuity;
	}

	/*
	 * @Calculation for gross annual salary
	 * @return type double
	 */
	public double calculateGrossAnnualSalary(String[] inputFields) {
		double grossAnnualSalary = 0.0;
		
		if(inputFields[11].isEmpty() == false && inputFields[12].isEmpty() == false){
			grossAnnualSalary = Double.parseDouble(inputFields[11]) + Double.parseDouble(inputFields[12]);
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