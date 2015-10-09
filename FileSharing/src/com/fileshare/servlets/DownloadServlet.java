package com.fileshare.servlets;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.fileshare.s3.S3Download;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String) request.getSession().getAttribute("user");
		//String filename = "sample3";
		String filename = request.getParameter("filename");
		S3Download s3Download = new S3Download();
		S3Object s3Object = s3Download.DownloadFromS3(filename, user);
		S3ObjectInputStream objInputStream = s3Object.getObjectContent();
		IOUtils.copy(objInputStream, new FileOutputStream("/home/simranjit/Desktop/"+filename));
//		request.setAttribute("s3Object", s3Object);
//		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//		rd.forward(request, response);
		response.sendRedirect("login.jsp");
	}

}
