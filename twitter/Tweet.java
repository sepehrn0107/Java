package objectstructures;

public class Tweet {
	
	private TwitterAccount owner; //eier av tweeten
	private String text; //teksten til en tweet
	private Tweet originalTweet; //original-tweeten
	private int retweetCount = 0; //antall ganger retweetet
	
	public Tweet(TwitterAccount acc, String text) {
		this.owner = acc;
		this.text = text;	
		this.originalTweet = null;
		 
	}
	
	public Tweet(TwitterAccount acc, Tweet originalTweet) {
		if (acc.equals(originalTweet.getOwner())){ 
			throw new IllegalArgumentException("You can't retweet your own tweet!");
		}
		this.owner = acc;
		this.text = originalTweet.getText();
		this.originalTweet = originalTweet;
		if (originalTweet.getOriginalTweet() == null) {
			this.originalTweet = originalTweet;
		}
		else {
			this.originalTweet = originalTweet.getOriginalTweet();
		}
		originalTweet.retweet(); 
	}
	
	
	public String getText() {
		return this.text;
	}
	
	
	public TwitterAccount getOwner() {
		return this.owner;
	}
	
	
	public Tweet getOriginalTweet() {
		return this.originalTweet;
	}
	
	
	public int getRetweetCount() {
		return this.retweetCount;
	}
	
	
	public void retweet() {
		this.retweetCount++;
	}
	

	public String toString() {
		return this.getOwner().getUserName() + ": " + this.getText();
	}
	
	
}