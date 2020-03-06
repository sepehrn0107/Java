package App;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class CalorieCalculator {
	double numCal, calResult;
	private RadioButton maleCheck;
	private RadioButton femaleCheck;
	public double BMIresult;
	public double dailyCal;
	private String chk;
	private String check;

	

	
	
public double showCals(double kg,double cm, String chk) {
	BMIresult = (float) (kg/(Math.pow(cm, 2)));
	if(chk.equals("maleCheck")){
		this.check="maleCheck";
		if (BMIresult<18.5) {
			calResult= kg*24*1.1;
			
			
			
		} else if ((BMIresult>=18.5) && (BMIresult<27)) {
			calResult=kg*24;
			
		} else if (BMIresult>=27) {
			calResult=kg*24*0.90;
		} 
		
		}else {
			this.check="femaleCheck";
			
		if(BMIresult<17.8) {
			calResult=kg*24*1.05*0.9;
			
		} else if((BMIresult>17.8) && (BMIresult<26.5)) {
			calResult=kg*24*0.9;
		} else if(BMIresult>26.5) {
			calResult=kg*24*0.90*0.9;
		}
		
		
	}
	this.dailyCal=calResult;
	return calResult;

	
	}

public void setCals(double cal) {
	this.dailyCal=cal;
}

public void setToggleGroup(String tglGrp) {
	if (tglGrp.equals("maleCheck")){
		this.check="maleCheck";
	} else {
		this.check="femaleCheck";
	}
}

public String getToggleGroup() {
	return this.check;
}

public String getCheck() {
	return this.check;
}

public double getCals() {
	return this.dailyCal;
}


}
