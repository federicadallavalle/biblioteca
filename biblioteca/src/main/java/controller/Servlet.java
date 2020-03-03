package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Prestito;
import model.Utente;
import serviceimpl.LoginServiceImpl;
import serviceimpl.PrestitoServiceImpl;
import utilities.Eccezione;

@WebServlet("*.do")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Utente utente;

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
			Prestito p = new Prestito();
			p.setDataInizio(LocalDate.now());
			p.getUtente().setId(Long.valueOf(request.getParameter("idUtente")));
			p.getLibro().setId(Long.valueOf(request.getParameter("idLibro")));
			try {
				PrestitoServiceImpl.getInstance().createPrestito(p);
			} catch (Eccezione e1) {
				System.out.println(e1.getMessage());
			}
			break;
		case "update-prestito":
			p = new Prestito();
			p.setId(Long.parseLong(request.getParameter("idPrestito")));
			p.setDataInizio(LocalDate.now());
			p.setDataConsegna(LocalDate.now());
			p.setDataUltimoSollecito(LocalDate.now());
			p.getUtente().setId(Long.valueOf(request.getParameter("idUtente")));
			p.getLibro().setId(Long.valueOf(request.getParameter("idLibro")));
			try {
				PrestitoServiceImpl.getInstance().updatePrestito(p);
			} catch (Eccezione e1) {
				System.out.println(e1.getMessage());
			}
			break;

		case "search-prestito":
			break;

		case "delete-prestito":
			Long idPrestito = Long.parseLong(request.getParameter("idPrestito"));
			try {
				PrestitoServiceImpl.getInstance().deletePrestito(idPrestito);
			} catch (Eccezione e1) {
				System.out.println(e1.getMessage());
			}
			break;

		case "login":
			String user = request.getParameter("username");
			String password = request.getParameter("password");
			utente = new Utente("", "", "", "", user);
			try {
				pagina = LoginServiceImpl.getIstance().login(request, utente, password);
			} catch (Eccezione e) {
				System.out.println("Utente non trovato: " + e.getMessage());
				pagina = "login";
			}
			break;

		case "password-dimenticata":
			String email = request.getParameter("email");
			utente = new Utente("", "", email, "", "");
			try {
				pagina = LoginServiceImpl.getIstance().login(request, utente, email);
			} catch (Eccezione e) {
				System.out.println("Indirizzo Email non valido: " + e.getMessage());
				pagina = "password-dimenticata";
			}
			sc = getServletContext();
			rd = sc.getRequestDispatcher("/" + pagina + ".jsp");
			rd.forward(request, response);
			break;

		}
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
