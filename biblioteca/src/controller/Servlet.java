package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Servlet() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher rd;
		ServletContext sc;
		String pagina = "";
		String path = request.getServletPath();
		String comando = path.substring(1, path.lastIndexOf(".do"));
		switch (comando) {
		case "create-prestito":
			break;
		case "update-prestito":
			break;
		case "search-prestito":
			break;
		case "delete-prestito":
			break;
		case "login":
			pagina = "login";
			break;
		}
		sc = getServletContext();
		rd = sc.getRequestDispatcher("/" + pagina + ".jsp");
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
