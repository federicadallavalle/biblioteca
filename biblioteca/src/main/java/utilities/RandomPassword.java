package utilities;

public class RandomPassword { 
		  
	    // genera una password random di lunghezza n 
	    public static String getPassword(int n) 
	    { 
	  
	        // caratteri che vengono usati nello StringBuilder
	        String caratteriUsati = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789"+"abcdefghijklmnopqrstuvxyz"+",.-_?^!$%&"; 
	  
	        // creo StringBuilder che prende in entrata la lunghezza n della password da generare 
	        StringBuilder sb = new StringBuilder(n); 
	        
	        // itero per creare la variabile sb
	        for (int i = 0; i < n; i++) { 
	  
	            // genero un numero random che varia da zero alla lunghezza della variabile caratteriUsati
	        	// e lo assegno a index
	            int index = (int)(caratteriUsati.length() * Math.random()); 
	  
	            // aggiungo i caratteri uno a uno nello StringBuilder sb
	            // prendendo il carattere in corrispondenza del numero index generato prima
	            sb.append(caratteriUsati.charAt(index)); 
	        } 
	        
	        return sb.toString(); 
	    } 

	} 
