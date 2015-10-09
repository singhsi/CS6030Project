package com.fileshare.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fileshare.s3.S3Upload;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		S3Upload s3u = new S3Upload();
		PrintWriter out = response.getWriter();
		String userF = (String) request.getSession().getAttribute("user");
		int count = 0;
		if(!ServletFileUpload.isMultipartContent(request)){
			 out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Servlet upload</title>");  
	         out.println("</head>");
	         out.println("<body>");
	         out.println("<p>No file uploaded</p>"); 
	         out.println("</body>");
	         out.println("</html>");
	         return;
		}
		FileItemFactory itemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(itemFactory);
		RequestDispatcher rd;
		try {
			List<FileItem> multiFI = upload.parseRequest(request);
			for (FileItem item : multiFI) {
				//String fileName = item.getName();
				//InputStream itemIS = item.getInputStream();
				//File f = new File(item.getName());
				s3u.uploadToS3(userF,item);
				count++;
			}
			
			if(count > 0){
				rd = request.getRequestDispatcher("successfullUpload.jsp");
				rd.forward(request, response);
			}
			else{
				rd = request.getRequestDispatcher("unsuccessfullUpload.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			rd = request.getRequestDispatcher("unsuccessfullUpload.jsp");
			rd.forward(request, response);
		}

	}
}
