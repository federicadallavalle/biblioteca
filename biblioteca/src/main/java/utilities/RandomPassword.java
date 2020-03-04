package utilities;
//manuel
public class RandomPassword { 
		  
	    /**
	     * Questo metodo genera una password randomica di lunghezza n
	     * composta da caratteri in UpperCase e LowerCase e caratteri speciali
	     */
	
	    public static String getPassword(int n)
	    { 
	  
	        // Caratteri che uso nello StringBuilder
	        String caratteriUsati = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789"+"abcdefghijklmnopqrstuvxyz"+"-_?!$%&";
	  
	        // Creo StringBuilder che prende in entrata la lunghezza n della password da generare 
	        StringBuilder sb = new StringBuilder(n); 
	        
	        // Itero per creare sb
	        for (int i = 0; i < n; i++) { 
	  
	            // Genero un numero random che varia da zero alla lunghezza della variabile caratteriUsati
	        	// e lo assegno a index
	            int index = (int)(caratteriUsati.length() * Math.random()); 
	  
	            // Aggiungo i caratteri uno a uno nello StringBuilder sb
	            // prendendo il carattere in corrispondenza del numero index generato prima
	            sb.append(caratteriUsati.charAt(index)); 
	        }
	        
	        return sb.toString(); 
	    } 

	} 
