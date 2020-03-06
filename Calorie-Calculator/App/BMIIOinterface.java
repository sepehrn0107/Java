package App;
import java.io.IOException;



public interface BMIIOinterface {
	void save(String filename, BMIcalculator calculator, CalorieCalculator calCalculator) throws IOException;
	BmiObjectLoader load(String filename) throws IOException;

}
