package Ap3;

import Player.Partida;
import Player.carta;
import Player.mano;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ap3 {

	public String in;
	public String out;

	public ap3(String in, String out) {
		this.in = in;
		this.out = out;

		try {
			BufferedReader bff = new BufferedReader(new FileReader(new File(this.in)));
			String act;
			while ((act = bff.readLine()) != null) { //Bucle principal
				//
				//	ToDo  //	leer la mano y evaluar
				//
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Partida leerMano(String act) {
		int nJg = (int) act.charAt(0);
		ArrayList<carta> mesa = new ArrayList<carta>();
		ArrayList<mano> jugadores = new ArrayList<mano>();
		mano aux;
		carta auxC;
		//carta c = new carta(act.substring(i,i+1), act.substring(i+1,i+2));
		//4;J1AhAc;J2JsJh;J37c8c;J44sKc;5dKs6cTh9h
		//0   4      11     18     25   30                
		for (int i = 4; i < (4 + (nJg * 7)); i += 7) {
			aux = new mano();
			aux.addCarta(new carta(act.substring(i, i + 1), act.substring(i + 1, i + 2)));
			aux.addCarta(new carta(act.substring(i + 2, i + 3), act.substring(i + 3, i + 4)));
			jugadores.add(aux);
		}

		for (int i = ((7 * nJg) + 2); i < ((7 * nJg) + 12); i += 2) {
			auxC = new carta(act.substring(i, i + 1), act.substring(i + 1, i + 2));
			mesa.add(auxC);
		}

		Partida p = new Partida(mesa, jugadores);
		return p;
	}

	public void evalua() {
	}

}