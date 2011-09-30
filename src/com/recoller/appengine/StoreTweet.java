package com.recoller.appengine;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Entity;
/*import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import java.util.List;
*/

/**
 * Kelas ini menghandle persist data tweet. 
 * fungsi storeTweet() menghandle parameter yang dipakai di RecollerServlet
 * lalu dilempar ke persistEntity() milik kelas Util
 * @author frendhisaidodanaro
 *
 */

public class StoreTweet {
	/**
	 * storeTweet() dipanggil berulang2 di fetchTweet
	 * tweetCounter di increment disini 
	 * untuk menghitung berapa tweet yang sudah diproses untuk dipersist
	 * jadi tweetCounter tidak selalu merepresentasikan 
	 * jumlah tweet yang sudah berhasil dipersist
	 * @param
	 */

	private static final Logger logger = Logger.getLogger(StoreTweet.class.getCanonicalName());
	
	
	static void storeTweet(String author, String time, String content){
		try{
		Entity oneTweet = new Entity ("tweet");
		oneTweet.setProperty("Author", author);
		oneTweet.setProperty("Time", time);
		oneTweet.setProperty("Content", content);
		Util.persistEntity(oneTweet);
		InfoServlet.tweetCounter++;
		} catch (Exception e) {
			e.printStackTrace();
		    logger.log(Level.INFO, "Gagal persist di StoreTweet");
		}
	}

}
