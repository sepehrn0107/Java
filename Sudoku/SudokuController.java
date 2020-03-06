package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class SudokuController {
	
	 
	//Sudoku game = new Sudoku();
	@FXML
	Sudoku game = new Sudoku(".....2..38.273.45....6..87.9.8..5367..6...1..4513..9.8.84..3....79.512.62..8.....");
	
	@FXML
	TextField s00, s01, s02, s03, s04, s05, s06, s07, s08, s09, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19, s20, s21, s22, s23, s24, s25, s26, s27, s28, s29, s30, s31, s32, s33, s34, s35, s36, s37, s38, s39, s40, s41, s42, s43, s44, s45, s46, s47, s48, s49, s50, s51, s52, s53, s54, s55, s56, s57, s58, s59, s60, s61, s62, s63, s64, s65, s66, s67, s68, s69, s70, s71, s72, s73, s74, s75, s76, s77, s78, s79, s80;
	@FXML
	TextField file;
	
	@FXML
	Button undo, redo, load, save;
	
	
	
	@FXML
	ArrayList<ArrayList<TextField>> board;

	
	@FXML
	public void initialize() {
		board = new ArrayList<ArrayList<TextField>>();
		int counter = 0;
		List<TextField> tf = Arrays.asList(s00, s01, s02, s03, s04, s05, s06, s07, s08, s09, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19, s20, s21, s22, s23, s24, s25, s26, s27, s28, s29, s30, s31, s32, s33, s34, s35, s36, s37, s38, s39, s40, s41, s42, s43, s44, s45, s46, s47, s48, s49, s50, s51, s52, s53, s54, s55, s56, s57, s58, s59, s60, s61, s62, s63, s64, s65, s66, s67, s68, s69, s70, s71, s72, s73, s74, s75, s76, s77, s78, s79, s80);
		for (int i = 0; i < 9; i++) {
			board.add(new ArrayList<TextField>());
			for (int j = 0; j < 9; j++) {
				TextField field = tf.get(counter);
				board.get(i).add(field);
				counter++;
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				TextField CurrentText = board.get(i).get(j);
				char value = game.getCell(i, j).getValue();
				if (value != '.') {
					CurrentText.setText(Character.toString(value));
					CurrentText.setMouseTransparent(true);
				}
			}
		}	
	}
	
	//handleDigit kontrollerer en onAction av alle
	@FXML
	public void handleDigit() {
		/*for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String digit = board.get(i).get(j).getText();
				if (digit.length() != 0) {
					if (game.getCell(i, j).isChangeable()) {
						game.setCell(i, j, digit.charAt(0));
					}
				}
			}
		}
		update();*/
	}
	
	@FXML
	public void keyPressed() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String digit = board.get(i).get(j).getText();
				if (digit.length() != 0) {
					if (game.getCell(i, j).isChangeable()) {
						game.setCell(i, j, digit.charAt(0));
					}
				}else {
					game.setCell(i, j, '.');
				}
			}
		}
		update();
	}
	
	@FXML 
	void update() {
		game.resetConflict();
		game.checkCellConflict();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String digit = board.get(i).get(j).getText();
				char value = game.getCell(i, j).getValue();
				if (game.getCell(i, j).isUncertain() && digit != ""){
					board.get(i).get(j).setText(Character.toString(value) + "*");
				}

			}
		}
	}
	
	//Må lage en funksjon som gjør feltet tomt om man trykker på det
	
	
	
	
	@FXML 
	public void undo(){
		game.undo();
		update();
		System.out.println(game.toString());
	}
	
	@FXML
	public void redo() {
		game.redo();
		update();
	}
	
	@FXML 
	public void load(){
		
	}
	
	@FXML
	public void save() {
		
	}
	
	
	
}
