package app;

import java.util.ArrayList;

public class Calculator {
	
	ArrayList<String> outputQueue = new ArrayList<String>();
	
	public void pushIndex(String s, int i) {
		if (isNumber(s) || isOperator(s)) {
			outputQueue.add(i, s);			
		} else {
			throw new IllegalArgumentException("Input er ikke tall eller gyldig operator");
		}
	}
	
	public void pushTop(String s) {
		if (isNumber(s) || isOperator(s)) {
			outputQueue.add(s);			
		} else {
			throw new IllegalArgumentException("Input er ikke tall eller gyldig operator");
		}
	}
	
	public boolean isNumber(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public boolean isOperator(String s) {
		char[] c = s.toCharArray();
		if (c.length != 1 || (c[0] != '+' && c[0] != '-' && c[0] != '*' && c[0] != '/')) {
			return false;
		}
		return true;
	}
	
	public void performOperation(int index) {
		char o = pop(index).toCharArray()[0];
		if (o != '+' && o != '-' && o != '*' && o != '/') {
			throw new IllegalArgumentException("Kjenner ikke operator");
		}
		double v1 = Double.parseDouble(pop(index - 1));
		double v2 = Double.parseDouble(pop(index - 2));
		if (o == '+') {
			pushIndex("" + (v2 + v1), index - 2);
		} else if (o == '-') {
			pushIndex("" + (v2 - v1), index - 2);
		} else if (o == '*') {
			pushIndex("" + (v2 * v1), index - 2);
		} else if (o == '/') {
			pushIndex("" + (v2 / v1), index - 2);
		}		
	}
	
	public String pop(int index) {
		String tmp = outputQueue.get(index);
		outputQueue.remove(index);
		return tmp;
	}
	
	public void calculate() {
		int k = outputQueue.size();
		int l = 0;
		while (outputQueue.size() > 1) {
			l++;
			if (l > k) {
				break;
			}
			int j = outputQueue.size();
			for (int i = 0; i < j; i++) {
				if (isOperator(outputQueue.get(i))) {
					performOperation(i);
					break;
				}
			}
		}
	}
	
	public void clearQueue() {
		outputQueue.clear();
	}
	
	public String peak() {
		String tmp = "";
		if (outputQueue != null && !outputQueue.isEmpty()) {
			  tmp = outputQueue.get(outputQueue.size()-1);
		}
		return tmp;
	}

	public static void main(String[] args) {
		
	}

}
