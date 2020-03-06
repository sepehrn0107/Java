package encapsulation;

import java.util.ArrayList;

public class Stack {

	private ArrayList<String> stackArray;

	public Stack() {
		this.stackArray = new ArrayList<String>();
	}

	public void addItem(String item) {
		stackArray.add(0, item);
	}

	public String removeItem(int index) {
		if (!(index + 1 > stackArray.size())) {

			String item = stackArray.get(index);
			if (item != null) {
				stackArray.remove(index);
				return item;
			}
		}
		return null;
	}

	public String getElement(int index) {
		if (!(index > stackArray.size()) && index >= 0) {

				String item = stackArray.get(index);
				if (item != null) {
					return item;
				}
		}
		return null;
	}

	public int getSize() {
		return stackArray.size();
	}

	public void printStack() {
		for (String s : stackArray) {
			System.out.println(s + " ");
		}
	}
}