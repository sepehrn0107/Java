package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Stack;


public class Sudoku implements SudokuFileHandler{
	
	Scanner in = new Scanner(System.in);
	
	private int nextRow;
	private int nextCol;
	
	private char nextValue;
	
	Stack<Sudoku> undo = new Stack<Sudoku>();
	Stack<Sudoku> redo = new Stack<Sudoku>();
	
	Stack<Cell> actions = new Stack<Cell>();
	Stack<Cell> regrets = new Stack<Cell>();
	
	Stack<String> lastValue = new Stack<String>();
	
	private ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();
	
	// Konstruktør med tomt brett
	public Sudoku() {
		for (int i = 0; i < 9; i++) {
			board.add(new ArrayList<Cell>());
			for (int j = 0; j < 9; j++) {
				board.get(i).add(new Cell());
			}
		}
	}
	
	//Konstruktør med ferdiglagd brett
	public Sudoku(String brett) {
		int counter = 0;
		for (int i = 0; i < 9; i++) {
			board.add(new ArrayList<Cell>());
			for (int j = 0; j < 9; j++) {
				char currentChar = brett.charAt(counter);
				Cell cell = new Cell(currentChar);
				if (currentChar != '.') {
					cell.setUnchangeable();
					board.get(i).add(cell);
				} else board.get(i).add(cell);
				counter++;
			}
		}
	}
	
	public void setBoard(String loadBrett) {
		ArrayList<ArrayList<Cell>> board = new ArrayList<ArrayList<Cell>>();

		int counter = 0;
		for (int i = 0; i < 9; i++) {
			board.add(new ArrayList<Cell>());
			for (int j = 0; j < 9; j++) {
				char currentChar = loadBrett.charAt(counter);
				Cell cell = new Cell(currentChar);
				if (currentChar != '.') {
					cell.setUnchangeable();
					board.get(i).add(cell);
				} else board.get(i).add(cell);
				counter++;
			}
		}
	}
	
	public String currentBoard() {
		String text = "";
		
		for (int i = 0; i < 9; i++) {
			for( int j = 0; j < 9; j++){
				char c = getCell(i,j).getValue();
				text += Character.toString(c);
			}
		}
		return text;
	}
	
	//Henter ut celle i rad r (fra 0) og kolonne c
	public Cell getCell(int r, int c) {
		return board.get(r).get(c);
	}
	
	
	// Setter verdi på cellen hvis den er changeable og valid
	public void setCell(int r, int c, char value) {
		if (getCell(r, c).isChangeable()) {
			getCell(r,c).setValue(value);
			if(actions.empty()) {
				actions.push(getCell(r,c));
				lastValue.push(Character.toString(value));
			}
			if (!getCell(r,c).equals(actions.peek())) {
				actions.push(getCell(r,c));
				lastValue.push(Character.toString(value));
			}
		} else {
			return;
		}
		this.checkCellConflict();
	}
	
	//Metode for å fjerne et element
	public void removeCell(int r, int c) {
		if (getCell(r,c).isChangeable()) {
			getCell(r,c).setCertain();
			setCell(r,c, '.'); 
		}
	}
	
	//Sjekker om verdien som settes i cellen er i konflikt. Returner true om den er det.
	public boolean checkCell(int r, int c, char value) {
		if (getCell(r,c).isChangeable()) getCell(r,c).setValue(value);
		if (conflict()) {
			removeCell(r, c);
			return true;
		}
		else {
			removeCell(r, c);
			return false;
		}
	}
	
	//Bygger metode for at sudokuspillet skal fungere. Om det er konflikt skal setUncertain bli true
	//Sjekker rader
	public boolean checkRows() {
		ArrayList<Character> chars = new ArrayList<Character>();
		for (int i = 0; i < 9; i++) {
			chars.clear();
			for (int j = 0; j < 9; j++) {
				if(getCell(i,j).getValue() != '.') {
					if (chars.contains(getCell(i,j).getValue())) {
						return false;
					}
					chars.add(getCell(i,j).getValue());
				}
			}
		}
		return true;
	}
	
	
	//Sjekker kolonner
	public boolean checkColons() {
		ArrayList<Character> chars = new ArrayList<Character>();
		for (int j = 0; j < 9; j++) {
			chars.clear();
			for (int i = 0; i < 9; i++) {
				if(getCell(i,j).getValue() != '.') {
					if (chars.contains(getCell(i,j).getValue())) {
						return false;
					}
					chars.add(getCell(i,j).getValue());
				}
			}
		}
		return true;
	}

	//Sjekker punkter
	public boolean checkFull() {
		for (int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if (getCell(i,j).getValue() == '.') return false;		
			}
		}
		return true;
	}
	
	//Sjekker 3x3 bokser
	public boolean checkBoxes() {
		ArrayList<Character> chars = new ArrayList<Character>();
		//Boks på 1,1
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if (getCell(i,j).getValue() != '.') {
					if(chars.contains(getCell(i,j).getValue())){
						return false;
					}
					chars.add(getCell(i,j).getValue());
				}
			}
		}
		chars.clear();
		//boks på 1,2
		for(int i = 0; i < 3; i++) {
			for(int j = 3; j < 6; j++) {
				if (getCell(i,j).getValue() != '.') {
					if(chars.contains(getCell(i,j).getValue())){
						return false;
					}
					chars.add(getCell(i,j).getValue());
				}
			}
		}
		chars.clear();
		//boks på 1,3
		for(int i = 0; i < 3; i++) {
			for(int j = 6; j < 9; j++) {
				if (getCell(i,j).getValue() != '.') {
					if(chars.contains(getCell(i,j).getValue())){
						return false;
					}
					chars.add(getCell(i,j).getValue());
				}
			}
		}
		chars.clear();
		//boks på 2,1
		for(int i = 3; i < 6; i++) {
			for(int j = 0; j < 3; j++) {
				if (getCell(i,j).getValue() != '.') {
					if(chars.contains(getCell(i,j).getValue())){
						return false;
					}
					chars.add(getCell(i,j).getValue());
				}
			}
		}
		chars.clear();
		//boks på 2,2
		for(int i = 3; i < 6; i++) {
			for(int j = 3; j < 6; j++) {
				if (getCell(i,j).getValue() != '.') {
					if(chars.contains(getCell(i,j).getValue())){
						return false;
					}
					chars.add(getCell(i,j).getValue());
				}
			}
		}
		chars.clear();
		//boks på 2,3
		for(int i = 3; i < 6; i++) {
			for(int j = 6; j < 9; j++) {
				if (getCell(i,j).getValue() != '.') {
					if(chars.contains(getCell(i,j).getValue())){
						return false;
					}
					chars.add(getCell(i,j).getValue());
				}
			}
		}
		chars.clear();
		//boks på 3,1
		for(int i = 6; i < 9; i++) {
			for(int j = 0; j < 3; j++) {
				if (getCell(i,j).getValue() != '.') {
					if(chars.contains(getCell(i,j).getValue())){
						return false;
					}
					chars.add(getCell(i,j).getValue());
				}
			}
		}
		chars.clear();
		//boks på 3,2
		for(int i = 6; i < 9; i++) {
			for(int j = 3; j < 6; j++) {
				if (getCell(i,j).getValue() != '.') {
					if(chars.contains(getCell(i,j).getValue())){
						return false;
					}
					chars.add(getCell(i,j).getValue());
				}
			}
		}
		chars.clear();
		//boks på 3,3
		for(int i = 6; i < 9; i++) {
			for(int j = 6; j < 9; j++) {
				if (getCell(i,j).getValue() != '.') {
					if(chars.contains(getCell(i,j).getValue())){
						return false;
					}
					chars.add(getCell(i,j).getValue());
				}
			}
		}
		return true;
	}
	
	//Sjekker om verdien som puttes inn er i konflikt
	public boolean conflict() {
		if (!checkRows() || !checkColons() || !checkBoxes()) return true;
		return false;
	}
	
	
	//Sjekker om cellen er i konlikt
	public boolean cellConflict(int r, int c) {
		ArrayList<Character> chars = new ArrayList<Character>();
		for (int j = 0; j < 9; j++) {
			chars.clear();
			if(getCell(r,j).getValue() != '.') {
				if (chars.contains(getCell(r,j).getValue())) {
					return true;
				}
				chars.add(getCell(r,j).getValue());
			}
		}
		chars.clear();
		for (int j = 0; j < 9; j++) {
			chars.clear();
			if(getCell(j,c).getValue() != '.') {
				if (chars.contains(getCell(j,c).getValue())) {
					return true;
				}
				chars.add(getCell(j,c).getValue());
			}
		}
		chars.clear();
		//Boks på 1,1
		if (r < 3 && c < 3) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if (getCell(i,j).getValue() != '.') {
						if(chars.contains(getCell(i,j).getValue())){
							if(getCell(i,j).getValue() == getCell(r,c).getValue()) {
								return true;
							}
						}
						chars.add(getCell(i,j).getValue());
					}
				}
			}
		}
		chars.clear();
		//Boks på 2,1
		if (r > 2 && r < 6 && c < 3) {
			for(int i = 3; i < 6; i++) {
				for(int j = 0; j < 3; j++) {
					if (getCell(i,j).getValue() != '.') {
						if(chars.contains(getCell(i,j).getValue())){
							if(getCell(i,j).getValue() == getCell(r,c).getValue()) {
								return true;
							}
						}
						chars.add(getCell(i,j).getValue());
					}
				}
			}
		}
		chars.clear();
		//Boks på 3,1
		if (r > 5 && c < 3) {
			for(int i = 6; i < 9; i++) {
				for(int j = 0; j < 3; j++) {
					if (getCell(i,j).getValue() != '.') {
						if(chars.contains(getCell(i,j).getValue())){
							if(getCell(i,j).getValue() == getCell(r,c).getValue()) {
								return true;
							}
						}
						chars.add(getCell(i,j).getValue());
					}
				}
			}
		}
		chars.clear();
		//boks på 1,2
		if (r < 3 && c > 2 && c < 6) {
			for(int i = 0; i < 3; i++) {
				for(int j = 3; j < 6; j++) {
					if (getCell(i,j).getValue() != '.') {
						if(chars.contains(getCell(i,j).getValue())){
							if(getCell(i,j).getValue() == getCell(r,c).getValue()) {
								return true;
							}
						}
						chars.add(getCell(i,j).getValue());
					}
				}
			}
		}
		chars.clear();
		//boks på 2,2
		if (r < 6 && r > 2 && c > 2 && c < 6) {
			for(int i = 3; i < 6; i++) {
				for(int j = 3; j < 6; j++) {
					if (getCell(i,j).getValue() != '.') {
						if(chars.contains(getCell(i,j).getValue())){
							if(getCell(i,j).getValue() == getCell(r,c).getValue()) {
								return true;
							}
						}
						chars.add(getCell(i,j).getValue());
					}
				}
			}
		}
		chars.clear();
		//boks på 2,3
		if (r > 2 && r < 6 && c > 5) {
			for(int i = 3; i < 6; i++) {
				for(int j = 6; j < 9; j++) {
					if (getCell(i,j).getValue() != '.') {
						if(chars.contains(getCell(i,j).getValue())){
							if(getCell(i,j).getValue() == getCell(r,c).getValue()) {
								return true;
							}
						}
						chars.add(getCell(i,j).getValue());
					}
				}
			}
		}
		chars.clear();
		//boks på 3,1
		if (r > 5 && c < 3) {
			for(int i = 6; i < 9; i++) {
				for(int j = 0; j < 3; j++) {
					if (getCell(i,j).getValue() != '.') {
						if(chars.contains(getCell(i,j).getValue())){
							if(getCell(i,j).getValue() == getCell(r,c).getValue()) {
								return true;
							}
						}
						chars.add(getCell(i,j).getValue());
					}
				}
			}
		}
		chars.clear();
		//boks på 3,2
		if (r > 5 && c < 6 && c > 2) {
			for(int i = 6; i < 9; i++) {
				for(int j = 3; j < 6; j++) {
					if (getCell(i,j).getValue() != '.') {
						if(chars.contains(getCell(i,j).getValue())){
							if(getCell(i,j).getValue() == getCell(r,c).getValue()) {
								return true;
							}
						}
						chars.add(getCell(i,j).getValue());
					}
				}
			}
		}
		chars.clear();
		//boks på 3,3
		if (r > 5 && c > 5) {
			for(int i = 6; i < 9; i++) {
				for(int j = 6; j < 9; j++) {
					if (getCell(i,j).getValue() != '.') {
						if(chars.contains(getCell(i,j).getValue())){
							if(getCell(i,j).getValue() == getCell(r,c).getValue()) {
								return true;
							}
						}
						chars.add(getCell(i,j).getValue());
					}
				}
			}
		}
		return false;
	}
	
	//Metode for å sjekke alle cellene om de er i konflikt. Vil sette en celle uncertain om den er i konflikt.
	public void checkCellConflict() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				Cell cell = getCell(i,j);
				if (cellConflict(i,j)) {
					cell.setUncertain();
				}
			}
		}
	}
	
	//Resetter alle konflikter på brettet
	public void resetConflict() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				Cell cell = getCell(i,j);
				cell.setCertain();
			}
		}
	}
	
	//Implementerer metode for undo
	public void undo() {
		if (actions.empty()) {
			throw new IllegalArgumentException("Kan ikke angre uten at det er en handling");
		}
		
		regrets.push(actions.peek());
		
		lastValue.push(Character.toString(actions.peek().getValue()));
		
		Cell lastAction = actions.pop();
		lastAction.setValue('.');
		this.resetConflict();
		this.checkCellConflict();
		
	}
	
	//Implementerer metode for redo
	public void redo() {
		//Trenger at actions stacken legger til i regret stacken etter undo
		if (regrets.empty()) {
			throw new IllegalArgumentException("Kan ikke gjenta om ingenting er angret");
		}
		Cell lastRegret = regrets.peek();
		actions.push(lastRegret);
		lastRegret.setValue(lastValue.pop().charAt(0));
		
		this.resetConflict();
		this.checkCellConflict();

		regrets.pop();
	}
	
	
	//Sjekker om spiller har vunnet
	public boolean hasWon() {
		if (checkRows() && checkColons() && checkFull()) return true;
		return false;
	}
	
	//Metode for stringmodifier
	public String stringModifier(int r, int c) {
		String out = "";
		Cell cell = getCell(r,c);
		if (!cell.isChangeable()) {
			out += "(" + cell + ")";
			return out;
		}
		if (cell.isUncertain()) {
			out += " " + cell + "*";
			return out;
		}
		out += " " + cell + " ";
		return out;
	}
	
	//Metode for å printe ut toString
	public String toString() {
		String out = "";
		out += "+--------------+--------------+--------------+\n";
		for (int r = 0; r < 9; r++) {
			if (r == 3 || r == 6) {
				out += "+--------------+--------------+--------------+\n";
				out += "|  ";
			}
			else {
				out += "|  ";
			}
			for (int c = 0; c < 9; c++) {
				if (c == 3 || c == 6) {
					out += "|  ";
				}
				out += stringModifier(r,c) + " ";
			}
			out += "|\n";
		}
		out += "+--------------+--------------+--------------+\n";
		return out;
	}
	
	//Tar input fra bruker
	public void getInput(String in) {
		if (in.length() == 2) {
			this.nextRow = Character.getNumericValue(in.charAt(0));
			this.nextCol = Character.getNumericValue(in.charAt(1));
			removeCell(nextRow, nextCol);
		}
		if (in.length() == 3) {
			this.nextRow = Character.getNumericValue(in.charAt(0));
			this.nextCol = Character.getNumericValue(in.charAt(1));
			this.nextValue = in.charAt(2);
			setCell(this.nextRow, this.nextCol, this.nextValue);
		}
	}
	
	//Metode for å spille
	public void play() {
		System.out.println();
		
		while (!hasWon()) {
			String input = in.nextLine();
			getInput(input);
			
		}
	}
	
	public void save(String fileName) {
		String text = "";
		
		for (int i = 0; i < 9; i++) {
			for( int j = 0; j < 9; j++){
				char c = getCell(i,j).getValue();
				text += Character.toString(c);
			}
		}
		
		try (PrintWriter out = new PrintWriter(fileName)){
			out.println(text);
			out.close();
		}
		catch (FileNotFoundException e)
        {
            System.err.println("Error: file "+ fileName + " could not be opened for writing.");
            System.exit(1);
        }
	}
	
	public void load(String fileName) {
		BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(fileName));
            String board = in.readLine();
            this.setBoard(board);
            in.close();
        }
        catch (IOException e)
        {
            e.getStackTrace();
        }
        
	}
	
	
	public static void main(String[] args) throws FileNotFoundException{
		Sudoku board = new Sudoku(".....2..38.273.45....6..87.9.8..5367..6...1..4513..9.8.84..3....79.512.62..8.....");
		System.out.println(board.toString());
		
	}
	
}
