package stateandbehavior;

public class Digit {
	
	String bokst="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	int tallsystem;
	int siffer=0;
	
	
	boolean increment() {
		if (siffer==tallsystem-1) {
			this.siffer=0;
			return true;
		}
		else {
			this.siffer += 1;
			return false;
		}
		
	}
	
	int getValue() {
		return siffer;
			
	}
	int setBase(int number) {
		tallsystem=number;
		return tallsystem;
	}
	
	int getBase() {
		return tallsystem;
	}
	
	public String toString() {
		return "Tallet er " + bokst.charAt(siffer);
	}
	
	public static void main(String[] args) {
		Digit teller = new Digit();
		teller.setBase(13);
		System.out.println(teller.toString());

		teller.increment();
		teller.increment();
		System.out.println(teller.toString());
		teller.increment();
		System.out.println(teller.getValue());
		System.out.println(teller.toString());
		teller.increment();
	}
	
		
}