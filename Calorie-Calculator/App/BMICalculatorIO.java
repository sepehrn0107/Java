package App;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.util.Scanner;

import javafx.scene.control.TextField;



public class BMICalculatorIO implements BMIIOinterface {
	
	
	public void save(String filename, BMIcalculator calculator, CalorieCalculator calCalculator) throws IOException{
			DecimalFormat df = new DecimalFormat("#.00");
			PrintWriter pw = new PrintWriter(filename);
			String s =  Double.toString(calculator.getHeight()) +"," + Double.toString(calculator.getWeight())+"," +Double.toString(calculator.getBMI())+","
			+ Double.toString(calCalculator.getCals()) +"," + calCalculator.getCheck();
			System.out.println(calculator.getHeight());
			pw.print(s);
			pw.flush();
			pw.close();
			
			
		
	}
	
	 public BmiObjectLoader load(String filename) throws IOException {
		
		Scanner scanner = new Scanner(new File(filename));
		
		String[] calc = scanner.nextLine().split(",");
		
		scanner.close();
		
		BMIcalculator c1= new BMIcalculator();
		DecimalFormat df = new DecimalFormat("#.00");
		c1.setHeight(Double.parseDouble(calc[0]));
		c1.setWeight(Double.parseDouble(calc[1]));
		c1.setBMI(Double.parseDouble(calc[2]));
		
		CalorieCalculator c2 = new CalorieCalculator();
		c2.setCals(Double.parseDouble(calc[3]));
		System.out.println(calc[4]);
		c2.setToggleGroup(calc[4]);
		
		BmiObjectLoader loader = new BmiObjectLoader();
		loader.calculator=c1;
		loader.calCalculator=c2;
		
		
		
		return loader;
		
		}


			
}

			
		
	


