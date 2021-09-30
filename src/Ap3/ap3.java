package Ap3;

import Player.Partida;
import Player.carta;
import Player.mano;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Ap2.ap2;

public class ap3 extends ap2{

	public String in;
	public String out;

	public ap3(String in, String out) {
		super(in, out);
		this.in = in;
		this.out = out;

		try {
			BufferedReader bff = new BufferedReader(new FileReader(new File(this.in)));
			String act;
			while ((act = bff.readLine()) != null) { 
				Partida p = leerMano(act);
				int number_of_c_cards = 5;
				ArrayList<mano> maxAbs = new ArrayList<mano>();
				for(int i = 0; i < p.getnJug(); ++i) {
					//Ponemos las cartas comunes y las del jugador para hacer las combinaciones
					ArrayList<carta> resultList = 
			        		(ArrayList<carta>) Stream.concat(p.getMesa().stream(), p.getJugadores().get(i).getCartas().stream()).collect(Collectors.toList());
					ArrayList<mano> combs = new ArrayList<mano>();
					ArrayList<carta> manoAct = new ArrayList<carta> //Inicializo a valores sin importancia
						(Arrays.asList(p.mesa.get(0), p.mesa.get(0), p.mesa.get(0), p.mesa.get(0), p.mesa.get(0)));
					combinaciones(resultList, manoAct, 0, resultList.size() - 1, 0, combs);
					mano max = evaluaCombinaciones(combs,number_of_c_cards);
					maxAbs.add(max);
				}
				ordenaPartida(maxAbs);
				//imprimir maxAbs
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

		Partida p = new Partida(mesa, jugadores, nJg);
		return p;
	}

	//Ordeno con el metodo de burbuja, tendriamos que utilizar otro un poco mas eficiente
 	public void ordenaPartida(ArrayList<mano> combs){
 		mano aux;
 	    for(int i = 0;i < combs.size()-1;i++){
 	        for(int j = 0;j < combs.size()-i-1;j++){
 	            if(combs.get(j+1).getBesthand().biggerThan(combs.get(j).getBesthand())){    
 	                aux = combs.get(j+1);
 	               combs.set(j+1,combs.get(j));
 	               combs.set(j,aux);
 	            }
 	        }
 	    }
	}

}