package com.fileshare.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.s3.model.ObjectListing;
import com.fileshare.s3.S3ListObjects;

/**
 * Servlet implementation class ListFiles
 */
@WebServlet("/ListFilesServlet")
public class ListFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListFilesServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	doPost(request, response);
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		S3ListObjects s3ObjList = new S3ListObjects();
		String sessionUser = (String) request.getSession().getAttribute("user");
		ObjectListing userObjListing = s3ObjList.listFromS3(sessionUser);
		ObjectListing sharedObjListing = s3ObjList.listFromS3("shared");
		request.getSession().setAttribute("userObjListing", userObjListing);
		request.getSession().setAttribute("sharedObjListing", sharedObjListing);
		RequestDispatcher rd = request.getRequestDispatcher("explorer.jsp");
		rd.forward(request, response);
	}

}
