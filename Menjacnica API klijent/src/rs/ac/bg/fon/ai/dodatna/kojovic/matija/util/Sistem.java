package rs.ac.bg.fon.ai.dodatna.kojovic.matija.util;

import java.util.LinkedList;

import rs.ac.bg.fon.ai.dodatna.kojovic.matija.communication.Communication;
import rs.ac.bg.fon.ai.dodatna.kojovic.matija.domain.Zemlja;
import rs.ac.bg.fon.ai.dodatna.kojovic.matija.gui.MenjacnicaGUI;

public class Sistem {

	private static LinkedList<Zemlja> lst = new LinkedList<Zemlja>();
	
	public static String[] imenaZemalja(){
		String[] imena = new String[lst.size()];
		
		for (int i = 0; i < imena.length; i++) {
			imena[i] = lst.get(i).getName();
		}
		return imena;
	}
	
	public static void ucitajULst(){
		lst = Communication.zemlje();
	}
	
	public static double kurs(String iz, String u){
		String idIz = "";
		String idU = "";
		
		for (Zemlja zemlja : lst) {
			if(zemlja.getName().equals(iz)){
				idIz = zemlja.getId();
			}
			if(zemlja.getName().equals(u)){
				idU = zemlja.getId();
			}
		}
		return Communication.odnosValuta(idIz, idU);
	}
	
}