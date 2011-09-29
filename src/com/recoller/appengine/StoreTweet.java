package com.recoller.appengine;

import com.google.appengine.api.datastore.Entity;
/*import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import java.util.List;
*/

/**
 * Kelas ini menghandle persist data tweet. 
 * fungsi storeTweet() menghandle parameter yang diberikan
 * lalu dilempar ke persistEntity() milik kelas Util
 * @author frendhisaidodanaro
 *
 */

public class StoreTweet {
	/**
	 * oneTweet() dipanggil berulang2 di fetchTweet
	 * @param
	 */
	public static void storeTweet(String time, String author, String content){
		Entity oneTweet = new Entity ("Tweet", content);
		oneTweet.setProperty("Time", time);
		oneTweet.setProperty("Author", author);
		Util.persistEntity(oneTweet);
	}

}
