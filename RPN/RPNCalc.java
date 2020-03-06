package encapsulation;

public class RPNCalc {

	private Stack stack;

	public RPNCalc() {
		stack = new Stack();
	}

	public void push(double item) {
		String itemString = Double.toString(item);
		stack.addItem(itemString);
	}

	public double pop() {
		if (stack.getSize() == 0) {
			return Double.NaN;
		}
		return Double.parseDouble(stack.removeItem(0));
	}

	public double peek(int index) {
		String item = stack.getElement(index);
		if (item != null) {
			return Double.parseDouble(item);
		}
		return Double.NaN;
	}

	public int getSize() {
		return stack.getSize();
	}

	public void performOperation(char operation) {
		double x = pop();
		double y = pop();
		double result;

		if (operation == '+') {
			result = x + y;
			push(result);
		} else if (operation == '-') {
			result = y - x;
			push(result);
		} else if (operation == '*') {
			result = x * y;
			push(result);
		} else if (operation == '/') {
			result = y / x;
			push(result);
		} else if (operation == '~') {
			push(x);
			push(y);
		} else {
			push(y);
			push(x);
		}
	}

	public void print() {
		stack.printStack();
	}

}