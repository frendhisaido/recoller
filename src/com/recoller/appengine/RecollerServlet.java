package com.recoller.appengine;

import java.io.IOException;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.InputSource;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
//import java.io.File;

/**
 * Servlet Recoller, URL fetch berjalan disini, 
 * Ini yang dipanggil oleh cronJob
 * 
 * @author frendhisaidodanaro
 * 
 */

@SuppressWarnings("serial")
public class RecollerServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(RecollerServlet.class.getCanonicalName());
	
	
	/**
	 * doGet() otomatis dipanggil saat RecollerServlet direquest,
	 * Jadi dipake buat ngeinvoke fetchTweet 
	 * variabel callCount : counter berapa kali servlet ini dipanggil
	 * variabel failedFetch : counter berapa kali proses fetch gagal
	 * Agak ragu untuk fetchCounter apakah harus dari objek int atau ambil count dari datastore;
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		fetchTweet();
		InfoServlet.callCount++;
	}
	
	private static void fetchTweet(){
		/**
		 * Jelas butuh errorHandler dan Logger disini
		 * query search twitternya memfilter hanya dari lokasi radius 200 mil dari bandung
		 * dan mengambil tweet terkini dan terpopuler
		 * dengan 100 tweet yang ditampilkan.
		 */
		try{
			//URL sourceURL = new URL("http://api.twitter.com/1/lists/statuses.xml?slug=barito&owner_screen_name=robotodon");
			//URL sourceURL = new URL("http://search.twitter.com/search.atom?&geocode=-6.945512,107.597351,150mi");	
			URL sourceURL = new URL("http://search.twitter.com/search.atom?&geocode=-6.945512,107.597351,150mi&result_type=mixed&rpp=100");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(sourceURL.openStream()));
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("entry");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				
				Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						
						Element eElement = (Element) nNode;
						/*System.out.println("No."+temp+"--------------");
						System.out.println("Published: " + getTagValue("published", eElement));
						System.out.println("Konten: " + getTagValue("title", eElement));
						System.out.println("location: "+ getTagValue("location",eElement));
						System.out.println("User: " + getTagValue("name",eElement));
						System.out.println("");*/
						//assign ke objek string dulu baru ditaro di parameter apa langsung aja??
						StoreTweet.storeTweet(getTagValue("name", eElement), getTagValue("published",eElement), getTagValue("title", eElement));
						
					}		
			}
			
			

		} catch (Exception e){
			e.printStackTrace();
		    logger.log(Level.INFO, "Gagal Fetch dari RecollerServlet");
		}
	}
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		return nValue.getNodeValue();
	}
	
}
