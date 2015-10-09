package com.fileshare.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fileshare.beans.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    List<User> users;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addUsers();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Boolean authorized = false;
		for(User user : users){
			if((user.getUsername().equalsIgnoreCase(username))
					&& user.getPassword().equals(password)){
				authorized = true;
			}
		}
		RequestDispatcher rd;
		if(authorized){
			HttpSession session = request.getSession();
			session.setAttribute("user", username);
			//response.sendRedirect(getServletContext().getContextPath()+"/ListFilesServlet");
			rd = request.getRequestDispatcher("/ListFilesServlet");
			rd.forward(request, response);
		} else{
			rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}
	
	private void addUsers(){
		users = new ArrayList<User>();
		users.add(new User("user1", "pass1"));
		users.add(new User("user2", "pass2"));
		users.add(new User("user3", "pass3"));
		users.add(new User("user4", "pass4"));
		users.add(new User("user5", "pass5"));
		users.add(new User("user6", "pass6"));
		users.add(new User("user7", "pass7"));
	}

}
