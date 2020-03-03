package serviceimpl;

import javax.servlet.http.HttpServletRequest;

import service.LoginService;
import utilities.Eccezione;

public class LoginServiceImpl implements LoginService{
	
	  private static LoginServiceImpl istance=null; 
	  
	  private LoginServiceImpl() {
	  }

	  public static LoginServiceImpl getIstance() {
	    if(istance==null)
	      istance = new LoginServiceImpl();
	    return istance;
	  }
	    
	@Override
	public void login(HttpServletRequest request, String username, String password) throws Eccezione {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passwordDimenticata(HttpServletRequest request, String email) throws Eccezione {
		
		
	}

	@Override
	public void nuovaPassword(HttpServletRequest request, String email, String password) {
		// TODO Auto-generated method stub
		
	}

		
	}

