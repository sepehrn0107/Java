package stateandbehavior;

public class Location {
	int x;
	int y;
	
	void up() {
		this.y -= 1;
	}
	void down() {
		this.y += 1;
	}
	void left() {
		this.x -= 1;
	}
	void right() {
		this.x += 1;
	}
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	public static void main(String[] args) {
		Location location = new Location();
		location.up();
		location.up();
		location.up();
		location.up();
		location.up();
		location.left();
		System.out.println(location.getX());
		System.out.println(location.getY());
		
	}
}