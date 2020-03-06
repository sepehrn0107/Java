package stateandbehavior;

public class UpOrDownCounter {
	int start;
	int end;
	public UpOrDownCounter(int start, int end) {
		this.start = start;
		this.end = end;
		if (start == end) {
			throw new IllegalArgumentException("Equal");
			
		}
	}
	
	
	int getCounter() {
		return start;
		
	}
	boolean count() {
		if (start < end) {
			start +=1;
		} else if (start > end) {
			start -= 1;
		}
		return start != end;
	}

	public static void main(String[] args) {
		UpOrDownCounter u1 = new UpOrDownCounter(5,10);
		System.out.println(u1.getCounter());
		u1.count();
		System.out.println(u1.getCounter());
		u1.count();
		System.out.println(u1.getCounter());
		u1.count();
		System.out.println(u1.getCounter());
		u1.count();
		System.out.println(u1.getCounter());
		u1.count();
		System.out.println(u1.getCounter());
		u1.count();
		System.out.println(u1.getCounter());
		System.out.println(u1.count());
		
		// TODO Auto-generated method stub

	}

}
