package com.recoller.appengine;

/*
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map; */
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

/*
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
*/

/**
*Ini adalah kelas Utility, Tugas utamanya melakukan persist data ke data store
*Digunakan oleh kelas lain sebagai fungsi persist data melalui persistEntity()
*Fungsi logger nya belum dipakai. karna belum tau dimana store file log nya. 
*/
public class Util {
	
	private static final Logger logger = Logger.getLogger(Util.class.getCanonicalName());
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	
/**
 * fungsi persistEntity step akhir di program setelah selanjutnya diurus sama API datastore
 * @param entity : object entity yang akan di persist
 */
static void persistEntity(Entity entity) {
	logger.log(Level.INFO, "Saving entity");
	try{
	datastore.put(entity);
	}catch (Exception e){
		e.printStackTrace();
		logger.log(Level.INFO,"Gagal di persist di Datastore");
	}
}


/**
 * get DatastoreService instance
 * @return DatastoreService instance
 */
static DatastoreService getDatastoreServiceInstance(){
	  return datastore;
}
 
	
}

