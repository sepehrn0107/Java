package objectstructures;

import java.util.ArrayList;

public class TwitterAccount {
	private String username; //brukernavnet til twitterbrukeren
	private ArrayList <TwitterAccount> following; //brukeren følger
	private ArrayList <TwitterAccount> followedBy; //brukeren blir fulgt av
	private ArrayList <Tweet> tweets; //tweetsene til brukeren
	private int retweetCounter = 0; //antall retweets av brukeren
	
	
	public TwitterAccount(String username) {
		this.username = username;
		this.following = new ArrayList<>(); //følger ingen 
		this.followedBy = new ArrayList<>(); //har ingen følgere
		this.tweets = new ArrayList<>(); //tweetsene til twitterbrukeren
	}
	
	public String getUserName() {
		return this.username;
	}
		
	public void follow(TwitterAccount account) {
		if (account != this && !following.contains(account)){
			this.following.add(account);
			account.followedBy.add(this);
		}
	}
		
	public void unfollow(TwitterAccount account) {
		this.following.remove(account);
		account.followedBy.remove(this);
	}
	
	public boolean isFollowing(TwitterAccount account) {
		if (this.following.contains(account)) {
			return true;
		}
		return false;
	}
	
	public boolean isFollowedBy(TwitterAccount account) {
		if (account.isFollowing(this)) {
			return true;
		}
		return false;
	}

	public void tweet(String text) {
		Tweet newTweet = new Tweet(this, text);
		this.tweets.add(0, newTweet);
	}
		
	public void retweet(Tweet tweet) {
		tweets.add(0, new Tweet(this, tweet));
		tweet.retweet();
		
		if (tweet.getOriginalTweet() != null) {
			tweet.getOriginalTweet().getOwner().increaseRetweetCounter();
		} else {
			tweet.getOwner().increaseRetweetCounter();
		}
	}
	
	public Tweet getTweet(int i) {
		int index = i-1;
		if (index < 0 || index >= this.tweets.size()) {
			throw new IllegalArgumentException("Invalid index");
			
		}
		return this.tweets.get(index);	
	}
	
	public int getTweetCount() {
		return this.tweets.size();
	}
	
	public int getRetweetCount() {
		return this.retweetCounter;
	}
	
	public void increaseRetweetCounter() {
		this.retweetCounter++;
	}
	
	public String toString() {
		return this.username;
	}
	
}