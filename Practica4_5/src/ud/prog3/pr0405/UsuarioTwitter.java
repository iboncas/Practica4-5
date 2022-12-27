package ud.prog3.pr0405;

import java.util.ArrayList;

public class UsuarioTwitter implements Comparable<UsuarioTwitter>{
	
	private String id;
	private String screenName;
	private ArrayList<String> tags;
	private String avatar;
	private long followersCount;
	private long friendsCount;
	private String lang;
	private long lastSeen;
	private String tweetId;
	private ArrayList<String> friends;
	private long amigosSistema;
	
	public UsuarioTwitter(String id, String screenName, ArrayList<String> tags, String avatar, long followersCount, long friendsCount, String lang, long lastSeen, String tweetId, ArrayList<String> friends) {
		super();
		this.id = id;
		this.screenName = screenName;
		this.tags = tags;
		this.avatar = avatar;
		this.followersCount = followersCount;
		this.friendsCount = friendsCount;
		this.lang = lang;
		this.lastSeen = lastSeen;
		this.tweetId = tweetId;
		this.friends = friends;
	}

	public String getId() {
		return id;
	}
	public String getScreenName() {
		return screenName;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public String getAvatar() {
		return avatar;
	}
	public long getFollowersCount() {
		return followersCount;
	}
	public long getFriendsCount() {
		return friendsCount;
	}
	public String getLang() {
		return lang;
	}
	public long getLastSeen() {
		return lastSeen;
	}
	public String getTweetId() {
		return tweetId;
	}
	public ArrayList<String> getFriends() {
		return friends;
	}
	public long getAmigosSistema() {
		return amigosSistema;
	}

	
	public void setId(String id) {
		this.id = id;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public void setFollowersCount(long followersCount) {
		this.followersCount = followersCount;
	}
	public void setFriendsCount(long friendsCount) {
		this.friendsCount = friendsCount;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public void setLastSeen(long lastSeen) {
		this.lastSeen = lastSeen;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	public void setFriends(ArrayList<String> friends) {
		this.friends = friends;
	}
	public void setAmigosSistema(long amigosSistema) {
		this.amigosSistema = amigosSistema;
	}

	
	@Override
	public String toString() {
		return "UsuarioTwitter [id=" + id + ", screenName=" + screenName + ", tags=" + tags + ", avatar=" + avatar
				+ ", followersCount=" + followersCount + ", friensCount=" + friendsCount + ", lang=" + lang
				+ ", lastSeen=" + lastSeen + ", tweetId=" + tweetId + ", friends=" + friends + "]";
	}

	@Override
	public int compareTo(UsuarioTwitter o) {
		if(this.amigosSistema < o.amigosSistema) {
			return 1;
		}else if(this.amigosSistema > o.amigosSistema) {
			return -1;
		} else {
			if(this.screenName.compareTo(o.getScreenName()) > 0) {
				return 1;
			}else {
				return -1;
			}
		}
	}
}
