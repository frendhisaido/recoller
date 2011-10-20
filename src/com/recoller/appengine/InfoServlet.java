package com.recoller.appengine;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet untuk melihat informasi berapa tweet yang sudah di store
 * berapa proses fetch url yang gagal
 * berapa proses persist yang gagal
 * berapa kali servlet recoller dipanggil oleh cron job
 * @author frendhisaidodanaro
 *
 */

@SuppressWarnings("serial")
public class InfoServlet extends HttpServlet {
    //static int tweetCounter;
	//static int callCount;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		//resp.getWriter().println("Tweet stored : " + tweetCounter);
		//resp.getWriter().println("Recoller Called: " + callCount);
	}

	

}
