package App;

import java.text.DecimalFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CalculatorController {
	
	


    @FXML
    private TextField weight;

    @FXML
    private TextField height;

    @FXML
    private TextField age;

    @FXML
    private TextField result;
    
    @FXML
    private TextField calResult;

    @FXML
    private Button lifeExpectancy;
    
    @FXML
    private ToggleGroup ToggleGroup1;
    
    @FXML
    private RadioButton maleCheck;
    
    @FXML
    private RadioButton femaleCheck;
    
    @FXML
    private Button calculateCalButton;
    
    @FXML private Button saveBTN, loadBTN;
    
    private BMIcalculator calculator;
    private CalorieCalculator calCalculator;
    private BMIIOinterface io;
    public double pubBMI;
    public double weightKG;
     


    
    

	@FXML
	public void initialize() {
    	calculator = new BMIcalculator();
    	calCalculator = new CalorieCalculator();
    	io = new BMICalculatorIO();
		
	}
	
	
	
	
    public void calculateBMI() {
		try {
			
			double cm = (Double.parseDouble(height.getText()))/100;
			double kg = Double.parseDouble(weight.getText());
			calculator.setHeight(cm);
			calculator.setWeight(kg);
			pubBMI=calculator.calculate(cm,kg);
			System.out.println(pubBMI);
			calculator.setBMI(pubBMI);
			DecimalFormat df = new DecimalFormat("#.00");
			
			
			
			
			String stringResult = "Din BMI er " + df.format(pubBMI) ; 
			
			result.setText(stringResult);
			
		
		}
		catch(Exception e) {
			result.setText("Du fucka opp bmi");
		}
			
    }
    
    
    
    public void showCals() {
    	try {
    		double cm1 = (Double.parseDouble(height.getText()))/100;
			double kg1 = Double.parseDouble(weight.getText());
			
			RadioButton chk = (RadioButton)ToggleGroup1.getSelectedToggle();
			String btnId= chk.getId();
			
			int CalResult1=(int) Math.round(calCalculator.showCals(kg1,cm1,btnId));
    		String stringCalResult = "Anbefalt kaloriinntak " + CalResult1; 
    		calResult.setText(stringCalResult);
    		
    		
    	}
    	catch(Exception e) {
			calResult.setText("Du fucka med kaloriene");
    	
    }
    }
    
    
    	
    public void save() {
    	try {
    	io.save("BMItekstfil.txt", calculator, calCalculator);
 
    	
    } catch (Exception e) {
    	e.printStackTrace();
    	calResult.setText("fuckups med skriving til fil");
    }
    	}
    	
    
    
    public void load() {
    	try {
    		BmiObjectLoader loader = io.load("BMItekstfil.txt");
    		calculator=loader.calculator;
    		calCalculator=loader.calCalculator;
    		
    		height.setText(Double.toString(calculator.getHeight()*100));
    		weight.setText(Double.toString(calculator.getWeight()));
    		DecimalFormat df = new DecimalFormat("#.00");
    		result.setText("Din BMI er " + df.format(calculator.getBMI()));
    		
    		calResult.setText("Anbefalt kaloriinntak " + Math.round(calCalculator.getCals()));
    		 
    		if(calCalculator.getCheck().equals("maleCheck")) {
    			 maleCheck.setSelected(true);
  
    		 } else {
    			 femaleCheck.setSelected(true);
    		 }
    		 
    		 
    	} catch (Exception e ) {
    		calResult.setText("Du fucka med loadinga");
    	}
    }
    	
    	public static void main(String[] args) {
    		
    	}
    

		

}

