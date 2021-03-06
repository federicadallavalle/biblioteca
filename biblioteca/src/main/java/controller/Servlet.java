package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListaUtente;
import model.Libro;
import model.ListaLibri;
import model.ListaUtente;
import model.Prestito;
import model.Utente;
import service.ScadenzaService;
import serviceimpl.LibroServiceImpl;
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
//		RequestDispatcher rd;
//		ServletContext sc;
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
			pagina = registrazione(request);
			break;
		case "cerca-utenti":
			pagina = cercaUtente(request);
			break;

		case "gestione-scadenze":
			List<Utente> listaUtenti = null;
			try {
				// TODO: cancella stampe di prova
				ScadenzaService scadenzaService = ScadenzaServiceImpl.getInstance();
				listaUtenti = scadenzaService.listaUtentiScadenze();
				System.out.println("------------lista utenti---------------");
				for (Utente u1 : listaUtenti) {
					System.out.println(u1.getNome());
				}
			} catch (Eccezione e) {
				System.out.println("------------fallito---------------");
				e.printStackTrace();
			}
			request.setAttribute("listaUtenti", listaUtenti);
			pagina = "gestione-scadenze";
			break;
		case "lista-libri-gestore":
			String key = request.getParameter("cerca");
			ListaLibri lista = listaLibriGestore(request, key);
			request.getSession().setAttribute("listaLibri", lista);
			pagina = "lista-libri-gestore";
			break;
		case "lista-libri":
			pagina = listaLibri(request);
			break;
		case "modifica-libro":
			System.out.println(request.getParameter("id"));
		default:
			pagina = "index";
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
			try {
				pagina = LoginServiceImpl.getIstance().login(request, user, password);
			} catch (Eccezione e) {
				System.out.println("Utente non trovato: " + e.getMessage());
				pagina = "login";
			}
		}

		return pagina;
	}

	private String cercaUtente(HttpServletRequest request) {
		List<Utente> listaUtenti = new ArrayList<Utente>();
		ListaUtente lista = (ListaUtente) request.getSession().getAttribute("listaUtenti");

		String nome = request.getParameter("nome");
		System.out.println(nome);
		String cognome = request.getParameter("cognome");
		System.out.println(cognome);
		String email = request.getParameter("email");
		System.out.println(email);
		String username = request.getParameter("username");
		System.out.println(username);
		String ruolo = request.getParameter("ruolo");
		System.out.println(ruolo);

		Utente u = Utente.getEmptyUtente();
		u.setNome(nome);
		u.setNome(cognome);
		u.setNome(email);
		u.setUsername(username);
		try {
			listaUtenti = UtenteServiceImpl.getIstance().searchUtente(u);
			lista.setLista(listaUtenti);
			request.getSession().setAttribute("listaUtenti", lista);
			pagina = "gestione-utente";
			return pagina;
		} catch (Eccezione e) {
			System.out.println(e.getMessage());
		}

		pagina = "gestione-utente";
		return pagina;
	}

	private String registrazione(HttpServletRequest request) {
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
		String username = nome + RandomPassword.getPassword(3);
		String password = RandomPassword.getPassword(7);

		Utente u = new Utente(nome, cognome, email, via, civico, citta, provincia, cap, telefono, ruolo, username,
				password);
		try {
			UtenteServiceImpl us = UtenteServiceImpl.getIstance();
			us.createUtente(u);
			pagina = "registrazione-utente";
			return pagina;
		} catch (Eccezione e) {

			return pagina;
		}
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

	private ListaLibri listaLibriGestore(HttpServletRequest request, String key) {
		ListaLibri listaLibri = new ListaLibri();
		try {
			listaLibri = LibroServiceImpl.getIstance().getList(key);
		} catch (Eccezione e) {
			System.out.println(e.getMessage());
			pagina = "lista-libri-gestore";
		}
		return listaLibri;
	}

	private String listaLibri(HttpServletRequest request) {
		System.out.println("Entrati nel metodo listaLibri.");
		ListaLibri lista = new ListaLibri();
		// TODO : stub
//		lista.setLista(LibroServiceImpl.getInstance().searchLibro(""));
		lista.getLista().add(new Libro(null, "test1", null, null, 12, null, 0, null, null));
		lista.getLista().add(new Libro(null, "test2", null, null, 16, null, 0, null, null));
		lista.getLista().add(new Libro(null, "test3", null, null, 18, null, 0, null, null));
		// TODO : fine stub
		request.getSession().setAttribute("libri", lista);
		request.setAttribute("libri", lista);
		return "home-pubblica";
	}

	private String gestioneScadenze(HttpServletRequest request) {
		ListaUtente listaUtenti = new ListaUtente();
		listaUtenti.setLista(null);
		try {
			// TODO: cancella stampe di prova
			ScadenzaService scadenzaService = ScadenzaServiceImpl.getInstance();
			listaUtenti.setLista(scadenzaService.listaUtentiScadenze());
		} catch (Eccezione e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("listaUtenti", listaUtenti);
		return "gestione-scadenze";
	}
}
