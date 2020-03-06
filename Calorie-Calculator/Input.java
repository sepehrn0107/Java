package app;

import java.util.ArrayList;

public class Input {
	
	Calculator calc = new Calculator();
	
	char[] operator1 = {'+', '-'};
	char[] operator2 = {'*', '/'};
	
	ArrayList<String> infix = new ArrayList<String>();
	ArrayList<String> stack = new ArrayList<String>();
	
	public void parseString(String s) {
		s.replace(" ", "");
		char c[] = s.toCharArray();
		String tmp = "";
		for (char ch : c) {
			if (Character.isDigit(ch) || ch == '.') {
				tmp += ch;
			} else {
				this.infix.add(tmp);
				tmp = "";
				this.infix.add("" + ch);
			}
		}
		if (tmp != "") {
			this.infix.add(tmp);
		}
	}
	
	public void toPostfix() {
		calc.clearQueue();
		for (int i = 0; i < infix.size(); i++) {
			if (calc.isNumber(infix.get(i))) {
				calc.pushTop(infix.get(i));
			} else if (calc.isOperator(infix.get(i))) {
				while (checkPrecedence(peek()) >= checkPrecedence(infix.get(i))) {
					popStack();
				}
				stack.add(infix.get(i));
			}
		}
		int j = stack.size();
		for (int i = 0; i < j; i++) {
			popStack();
		}
		infix.clear();
	}
	
	public void popStack() {
		calc.pushTop(peek());
		stack.remove(stack.size() - 1);
	}
	
	public String peek() {
		String tmp = "";
		if (stack != null && !stack.isEmpty()) {
			  tmp = stack.get(stack.size()-1);
		}
		return tmp;
	}
	
	public int checkPrecedence(String s) {
		if (calc.isOperator(s)) {
			char c = s.toCharArray()[0];
			for (int i = 0; i < operator1.length; i++) {
				if (c == operator1[i]) {
					return 1;
				}
			}
			for (int i = 0; i < operator2.length; i++) {
				if (c == operator2[i]) {
					return 2;
				}
			}
		}
		return -1;
	}
	
	public String getAnswer(String s) {
		parseString(s);
		toPostfix();
		calc.calculate();
		String tmp = calc.peak();
		calc.clearQueue();
		return tmp;
	}

	public static void main(String[] args) {
	}

}
