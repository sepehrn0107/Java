package game;

public class Cell {
	
	private char value;
	private boolean changeable;
	private boolean uncertain;
	
	
	public Cell() {
		this.value = '.';
		this.changeable = true;
	}
	
	//Konstruktør for celle med gyldig verdi
	public Cell(char value) {
		if (!(isValidCell(value))) {
			throw new IllegalArgumentException("Må være gyldig verdi");
		}
		this.value = value;
		this.changeable = true;
	}
	
	// Sjekker om cellen inneholder gyldige tall
	public boolean isValidCell(char value) {
		String validValues = ".123456789";
		if (validValues.indexOf(value) == -1) {
			return false;
		}
		return true;
	}
	
	
	// Sjekker om cellen kan endres
	public boolean isChangeable() {
		return this.changeable;
	}
	
	public void setUnchangeable() {
		this.changeable = false;
	}
	
	public char getValue() {
		return this.value;
	}
	
	//Setter verdi hvis den er changeable og gyldig
	public void setValue(char value) {
		if (!isChangeable() || !isValidCell(value)) throw new IllegalArgumentException("Går ikke");
		this.value = value;
	}
	
	public void setUncertain() {
		this.uncertain = true;
	}
	public void setCertain() {
		this.uncertain = false;
	}
	
	public boolean isUncertain() {
		return this.uncertain;
	}
	
	
	
	public String toString() {
		String out = "";
		out += this.value;
		return out;
	}
	
	
}
