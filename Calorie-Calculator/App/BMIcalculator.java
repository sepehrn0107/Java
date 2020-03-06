package App;

import java.text.DecimalFormat;

public class BMIcalculator {
	private double weight , height, age, calculator ,lifeExpectancy;
	public double result1;
	public double bmi;
	

	
	
	
	public double calculate(double cm,double kg) {
		this.height=cm;
		this.weight=kg;
		
		result1 = (double) (kg/(Math.pow(cm, 2)));
		
		
		return result1;
	}
	
	public void setHeight(double cm) {
		this.height=cm;
	}
	
	public void setWeight(double kg) {
		this.weight=kg;
	}
	public void setBMI(double bmi) {
		this.bmi=bmi;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public double getBMI() {
		return this.bmi;
	}
	
	

	
	
	


}
