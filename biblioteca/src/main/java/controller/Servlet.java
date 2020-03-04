package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Prestito;
import model.Utente;
import service.ScadenzaService;
import serviceimpl.LoginServiceImpl;
import serviceimpl.PrestitoServiceImpl;
import serviceimpl.ScadenzaServiceImpl;
import serviceimpl.UtenteServiceImpl;
import utilities.Eccezione;

@WebServlet("*.do")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Utente utente;

	public Servlet() {
		super();
	}

	String pagina = "";

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher rd;
		ServletContext sc;
		pagina = "";
		String path = request.getServletPath();
		String comando = path.substring(1, path.lastIndexOf(".do"));
		switch (comando) {

		case "create-prestito":
			Prestito p = new Prestito();
			p.setDataInizio(LocalDate.parse(request.getParameter("dataInizio")));
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
		    p.setDataInizio(LocalDate.parse(request.getParameter("dataInizio")));
		    p.setDataConsegna(LocalDate.parse(request.getParameter("dataConsegna")));
			p.setDataUltimoSollecito(LocalDate.parse(request.getParameter("dataUltimoSollecito")));
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
			String msg = "";
			String user = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println(password);
			if (user.isBlank() || password.isBlank()) {
				msg = "Impossibile avere campi vuoti";
				request.setAttribute(msg, msg);
				pagina = "login";
				break;
			}
			// crea un utente con campi vuoti e imposta solo il nome utente
			Utente utente = Utente.getEmptyUtente();
			utente.setUsername(user);
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

		case "registrazione":
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			 email = request.getParameter("email");
			String via = request.getParameter("via");
			String civico = request.getParameter("civico");
			String citta = request.getParameter("citta");
			String provincia = request.getParameter("provincia");
			String cap = request.getParameter("cap");
			String telefono = request.getParameter("telefono");
			String ruolo = "iscritto";
			
			
			System.out.println("testoooooooooooooooooooooooooooooooooooooooooooooooooo");
			Utente u = new Utente(nome,cognome,email,via,civico,citta,provincia,cap,telefono,ruolo,"aaa","bbb");
			try {
				UtenteServiceImpl us = UtenteServiceImpl.getIstance();
				us.createUtente(u);
			} catch (Eccezione e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;

		case "gestione-scadenze":
			List<Utente> listaUtenti = null;
			try {
				ScadenzaService scadenzaService = ScadenzaServiceImpl.getInstance();
				listaUtenti = scadenzaService.listaUtentiScadenze();
			} catch (Eccezione e) {
				e.printStackTrace();
			}
			pagina = "gestione-scadenze";
			break;
		}
		response.sendRedirect(request.getContextPath() + "/" + pagina + ".jsp");
//		sc = getServletContext();
//		rd = sc.getRequestDispatcher("/" + pagina + ".jsp");
//		rd.forward(request, response);
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
