package com.fileshare.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.s3.model.S3Object;
import com.fileshare.s3.S3Delete;
import com.fileshare.s3.S3Download;
import com.fileshare.s3.S3Upload;

/**
 * Servlet implementation class ShareServlet
 */
@WebServlet("/ShareServlet")
public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShareServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] sharedStr = request.getParameterValues("share");
		String[] unsharedStr = request.getParameterValues("unshare");
		String user = (String) request.getSession().getAttribute("user");
		S3Download s3Dowload = new S3Download();
		S3Upload s3Share = new S3Upload();
		S3Object s3Obj;
		if (sharedStr != null) {
			for (String filename : sharedStr) {
				s3Obj = s3Dowload.DownloadFromS3(filename, user);
				s3Share.uploadToS3(user, s3Obj);
			}
		}

		S3Delete s3delete = new S3Delete();
		if (unsharedStr != null) {
			for (String us : unsharedStr) {
				s3delete.deleteSharedObject(us);
			}
		}
		response.sendRedirect(getServletContext().getContextPath()
				+ "/successShare.jsp");
	}

}
