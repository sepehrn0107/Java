package app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalcController {
	
	@FXML
	TextField input;
	
	@FXML
	Button button0;
	
	@FXML
	Button button1;
	
	@FXML
	Button button2;
	
	@FXML
	Button button3;
	
	@FXML
	Button button4;
	
	@FXML
	Button button5;
	
	@FXML
	Button button6;
	
	@FXML
	Button button7;
	
	@FXML
	Button button8;
	
	@FXML
	Button button9;
	
	@FXML
	Button buttonPlus;
	
	@FXML
	Button buttonMinus;
	
	@FXML
	Button buttonTimes;
	
	@FXML
	Button buttonDevide;
	
	@FXML
	Button buttonEquals;
	
	@FXML
	Button buttonDot;
	
	Input i = new Input();
	
	public void onClick0() {
		input.setText(input.getText() + "0");
	}
	
	public void onClick1() {
		input.setText(input.getText() + "1");		
	}
	
	public void onClick2() {
		input.setText(input.getText() + "2");
	}
	
	public void onClick3() {
		input.setText(input.getText() + "3");
	}
	
	public void onClick4() {
		input.setText(input.getText() + "4");
	}
	
	public void onClick5() {
		input.setText(input.getText() + "5");
	}
	
	public void onClick6() {
		input.setText(input.getText() + "6");
	}
	
	public void onClick7() {
		input.setText(input.getText() + "7");
	}
	
	public void onClick8() {
		input.setText(input.getText() + "8");
	}
	
	public void onClick9() {
		input.setText(input.getText() + "9");
	}
	
	public void onClickPlus() {
		input.setText(input.getText() + "+");
	}
	
	public void onClickMinus() {
		input.setText(input.getText() + "-");
	}
	
	public void onClickTimes() {
		input.setText(input.getText() + "*");
	}
	
	public void onClickDevide() {
		input.setText(input.getText() + "/");
	}
	
	public void onClickDot() {
		input.setText(input.getText() + ".");
	}
	
	public void onClickEquals() {
		input.setText(i.getAnswer(input.getText()));
	}

}
