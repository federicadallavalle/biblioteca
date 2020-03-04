package controller;

import java.io.IOException;
import java.time.LocalDate;

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
import utilities.RandomPassword;

@WebServlet("*.do")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Utente utente;
	Prestito p;

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
			createPrestito(request);
			break;

		case "update-prestito":
			updatePrestito(request);
			break;

		case "search-prestito":
			break;

		case "delete-prestito":
			deletePrestito(request);
			break;

		case "login":
			pagina = login(request);
			break;

		case "password-dimenticata":
			pagina = passwordDimenticata(request);
			break;

		case "registrazione":
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String email = request.getParameter("email");
			String via = request.getParameter("via");
			String civico = request.getParameter("civico");
			String citta = request.getParameter("citta");
			String provincia = request.getParameter("provincia");
			String cap = request.getParameter("cap");
			String telefono = request.getParameter("telefono");
			String ruolo = "iscritto";
			String username=nome+RandomPassword.getPassword(3);
			password= RandomPassword.getPassword(7);
			
			System.out.println("testoooooooooooooooooooooooooooooooooooooooooooooooooo");
			Utente u = new Utente(nome,cognome,email,via,civico,citta,provincia,cap,telefono,ruolo,username,password);
			try {
				UtenteServiceImpl us = UtenteServiceImpl.getIstance();
				us.createUtente(u);
				pagina="registrazione-utente";
			
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
			request.setAttribute("listaUtenti", listaUtenti);
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

	private void createPrestito(HttpServletRequest request) {
		p = new Prestito();
		p.setDataInizio(LocalDate.now());
		p.getUtente().setId(Long.valueOf(request.getParameter("idUtente")));
		p.getLibro().setId(Long.valueOf(request.getParameter("idLibro")));
		try {
			PrestitoServiceImpl.getInstance().createPrestito(p);
		} catch (Eccezione e1) {
			System.out.println(e1.getMessage());
		}
	}

	private void updatePrestito(HttpServletRequest request) {
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
	}

	private void deletePrestito(HttpServletRequest request) {
		Long idPrestito = Long.parseLong(request.getParameter("idPrestito"));
		try {
			PrestitoServiceImpl.getInstance().deletePrestito(idPrestito);
		} catch (Eccezione e1) {
			System.out.println(e1.getMessage());
		}
	}

	private String login(HttpServletRequest request) {
		String msg = "";
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(password);
		if (user.isBlank() || password.isBlank()) {
			msg = "Impossibile avere campi vuoti";
			pagina = "login";
		} else {
			// crea un utente con campi vuoti e imposta solo il nome utente
			Utente utente = Utente.getEmptyUtente();
			utente.setUsername(user);
			try {
				pagina = LoginServiceImpl.getIstance().login(request, utente, password);
			} catch (Eccezione e) {
				System.out.println("Utente non trovato: " + e.getMessage());
				pagina = "login";
			}
		}
		return pagina;
	}

	private String passwordDimenticata(HttpServletRequest request) {
		String email = request.getParameter("email");
		utente = Utente.getEmptyUtente();
		utente.setEmail(email);
		try {
			pagina = LoginServiceImpl.getIstance().passwordDimenticata(request, utente, email);
		} catch (Eccezione e) {
			System.out.println("Indirizzo Email non valido: " + e.getMessage());
			pagina = "password-dimenticata";
		}
		return pagina;
	}
}
