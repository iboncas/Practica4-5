package ud.prog3.pr0405;

import java.io.*;
import java.util.*;

public class GestionTwitter {

	// PASO 4
	protected static HashMap<String, UsuarioTwitter> mapaUsuariosPorId = new HashMap<String, UsuarioTwitter>();
	// PASO 5
	protected static TreeMap<String, UsuarioTwitter> mapaUsuariosPorNick = new TreeMap<String, UsuarioTwitter>(); // Treemap para que esté ordenado por el nick
	// PASO 7
	protected static TreeSet<UsuarioTwitter> usuariosConAmigos = new TreeSet<UsuarioTwitter>();
	
	public static void main(String[] args) {
		try {
			String fileName = "C:\\Users\\icasl\\Desktop"; //PONER EL PATH QUE SE DESEE
			CSV.processCSV( new File( fileName ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		//PASO 6
		// System.out.println(mapaUsuariosPorNick.keySet().size()); 	Resultado: 40000; es decir, no tenemos ningun nick repetido respecto al csv inicial
		// PASO 7
		int count = 0;	// Cuenta de las personas que tienen amigos dentro del sistema
		usuariosConAmigos = new TreeSet<UsuarioTwitter>();
		for(String nick:mapaUsuariosPorNick.keySet()) {
			UsuarioTwitter u = mapaUsuariosPorNick.get(nick);
			int amigosSist = 0;
			for(String id:u.getFriends()) { // Recorre los amigos del usuario y si nuestro set de id lo tiene, se incrementa el numero de amigos de ese usuario
				if(mapaUsuariosPorId.keySet().contains(id)) {
					amigosSist++;
					}
				}
			if(amigosSist != 0) { // Si el usuario tiene algún amigo, se imprimen los amigos fuera del sistema, es decir, el total menos lo calculado, y los amigos dentro
				count++;
				System.out.println("Usuario " + nick + " tiene " + (u.getFriendsCount() - amigosSist) + " fuera de nuestro sistema y "+ u.getFriendsCount() + " dentro.");
				u.setAmigosSistema(amigosSist);
				usuariosConAmigos.add(u);
				}
			}
			System.out.println("Hay " + count + " c"
					+ "on algunos amigos dentro de nuestro sistema"); // Resultado: 32308 -> Lo esperado
				
			for(UsuarioTwitter us:usuariosConAmigos) {
				System.out.println(us.getScreenName() + " - " + us.getAmigosSistema() + " amigos.");
			}
				
			amigosPorUsuarioPorPantalla("TouchOfMyHand");
			
			System.out.println();
				
			amigosPorUsuarioPorNivel("zulfimohdali",3);
				
			System.out.println();
				
			System.out.println(seisGrados("eventsbyelaine", "BillyMacLeod", 0));
				
	}
	
		
	// PASO 8
	public static void amigosPorUsuarioPorPantalla(String nick) {
		boolean esta = false;
		for(UsuarioTwitter us:usuariosConAmigos) {
			if(us.getScreenName().equals(nick)) {
				esta = true;
				break;
			}
		}
		if(!esta) {
			System.out.println("Error: el usuario no está contemplado / no tiene amigos en el sistema");
		} else {
			System.out.println("El usuario " + nick + " tiene " + mapaUsuariosPorNick.get(nick).getAmigosSistema() + " amigos en nuestro sistema que son:");
			ArrayList<String> amigosSist = new ArrayList<String>();
			for(String s:mapaUsuariosPorNick.get(nick).getFriends()) {
				if(mapaUsuariosPorId.keySet().contains(s)) {
					amigosSist.add(mapaUsuariosPorId.get(s).getScreenName());
				}
			}
			amigosRec(amigosSist,0);
			
		}
	}
	
	public static void amigosRec(ArrayList<String> ids, int indx) {
		if(indx >= ids.size()) return;
		System.out.print(ids.get(indx) + " ");
		amigosRec(ids,indx+1);
	}
	
	// PASOS 9 y 10
	
	public static void amigosPorUsuarioPorNivel(String nick, int nivel) {
		boolean esta = false;
		for(UsuarioTwitter us:usuariosConAmigos) {
			if(us.getScreenName().equals(nick)) {
				esta = true;
				break;
			}
		}
		if(!esta) {
			System.out.println("Error: el usuario no está contemplado / no tiene amigos en el sistema");
		} else {
			System.out.println("El usuario " + nick + " tiene los siguientes amigos de nivel " + nivel + ":");
			ArrayList<String> amigosSist = new ArrayList<String>();
			for(String s:mapaUsuariosPorNick.get(nick).getFriends()) {
				if(mapaUsuariosPorId.keySet().contains(s)) {
					amigosSist.add(mapaUsuariosPorId.get(s).getScreenName());
				}
			}
			amigosRecPorNivel(amigosSist,nivel);
			
		}
	}
	public static void amigosRecPorNivel(ArrayList<String> ids, int nivel) {
		if(nivel == 1) {
			/* CÓDIGO PARA ORDENAR POR NICK
			TreeSet<String> amigosOrden = new TreeSet<String>();
			for(String s1:ids) {
				amigosOrden.add(s1);
			}
			ids = new ArrayList<String>();
			for(String s2:amigosOrden) {
				ids.add(s2);
			}*/
			amigosRec(ids,0);
		} else {
			ArrayList<String> temp = new ArrayList<String>();
			for(String st1:ids) {
				ArrayList<String> amigos = mapaUsuariosPorNick.get(st1).getFriends();
				for(String st2: amigos) {
					if(mapaUsuariosPorId.keySet().contains(st2) && !temp.contains(st2)) {
						temp.add(mapaUsuariosPorId.get(st2).getScreenName());
					}
				}
			}
			amigosRecPorNivel(temp,nivel-1);
		}
	}
	
	// PASO 11
	public static boolean seisGrados(String nick1, String nick2, int grados) {
		if(grados > 6) {
			return false;
		} else if(nick1.equals(nick2)){
			return true;
		} else if(!mapaUsuariosPorNick.containsKey(nick1) || !mapaUsuariosPorNick.containsKey(nick2)) {
			return false;
		} else {
			for(String u:mapaUsuariosPorNick.get(nick1).getFriends()) {
				if(mapaUsuariosPorId.keySet().contains(u)) {
					String us = mapaUsuariosPorId.get(u).getScreenName();
					if(seisGrados(us,nick2, grados + 1)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}

