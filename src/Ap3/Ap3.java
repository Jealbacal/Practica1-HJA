package Ap3;

import Ap2.Ap2;
import Player.Partida;
import Player.Ranking;
import Player.Carta;
import Player.Mano;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Ap3 extends Ap2 {

	public String in;
	public String out;

	public Ap3(String in, String out) {
		super(in,out);

	}

	@Override
	public void buclePrincipal(){
		try {
			BufferedReader bff = new BufferedReader(new FileReader(new File(this.in)));
			String act;
			while ((act = bff.readLine()) != null) {
				Partida p = leerPartida(act);
				int number_of_c_cards = 5;
				ArrayList<Mano> maxAbs = new ArrayList<Mano>();
				HashMap<Mano,Integer> asignacion= new HashMap<Mano,Integer>();
				for(int i = 0; i < p.getnJug(); ++i) {
					//Ponemos las cartas comunes y las del jugador para hacer las combinaciones
					ArrayList<Carta> resultList =
							(ArrayList<Carta>) Stream.concat(p.getMesa().stream(), p.getJugadores().get(i).getCartas().stream()).collect(Collectors.toList());
					ArrayList<Mano> combs = new ArrayList<Mano>();
					ArrayList<Carta> manoAct = new ArrayList<Carta> //Inicializo a valores sin importancia
							(Arrays.asList(p.getMesa().get(0), p.getMesa().get(0), p.getMesa().get(0), p.getMesa().get(0), p.getMesa().get(0)));
					combinaciones(resultList, manoAct, 0, resultList.size() - 1, 0, combs);
					Mano max = evaluaCombinaciones(combs,number_of_c_cards);
					asignacion.put(max,i+1);
					maxAbs.add(max);
				}
				ordenaPartida(maxAbs);
				//imprimir maxAbs
				imprimir(asignacion,maxAbs,act);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public Partida leerPartida(String act) {
		int nJg = (int) (act.charAt(0)-'0');
		ArrayList<Carta> mesa = new ArrayList<Carta>();
		ArrayList<Mano> jugadores = new ArrayList<Mano>();
		Mano aux;
		Carta auxC;
		//carta c = new carta(act.substring(i,i+1), act.substring(i+1,i+2));
		//4;J1AhAc;J2JsJh;J37c8c;J44sKc;5dKs6cTh9h
		//0   4      11     18     25   30                
		for (int i = 4; i < (4 + (nJg * 7)); i += 7) {
			aux = new Mano();
			aux.addCarta(new Carta(act.substring(i, i + 1), act.substring(i + 1, i + 2)));
			aux.addCarta(new Carta(act.substring(i + 2, i + 3), act.substring(i + 3, i + 4)));
			jugadores.add(aux);
		}

		for (int i = ((7 * nJg) + 2); i < ((7 * nJg) + 12); i += 2) {
			auxC = new Carta(act.substring(i, i + 1), act.substring(i + 1, i + 2));
			mesa.add(auxC);
		}

		Partida p = new Partida(mesa, jugadores, nJg);
		return p;
	}

	//Ordeno con el metodo de burbuja, tendriamos que utilizar otro un poco mas eficiente
 	public void ordenaPartida(ArrayList<Mano> combs){
 		Mano aux;
 	    for(int i = 0;i < combs.size()-1;i++){
 	        for(int j = 0;j < combs.size()-i-1;j++){

 	            if(combs.get(j+1).getBesthand().biggerThan(combs.get(j).getBesthand())){    
 	                aux = combs.get(j+1);
 	               combs.set(j+1,combs.get(j));
 	               combs.set(j,aux);
 	            }

 	            else if(combs.get(j+1).getBesthand()==combs.get(j).getBesthand()){

					aux=combs.get(j).compareMano(combs.get(j+1));

					if(aux==combs.get(j+1)){

						combs.set(j+1,combs.get(j));
						combs.set(j,aux);
					}
				}

 	        }
 	    }
	}



	public void  imprimir(HashMap<Mano,Integer> asignacion, ArrayList<Mano> maxAbs, String act){


		try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.out,true))) {

			writer.write(act);
			writer.append("\n");
			for(int i=0; i < maxAbs.size();i++){

				if (maxAbs.get(i).getBesthand() == Ranking.PAIR) {
					writer.write("J"+asignacion.get(maxAbs.get(i))+": "+maxAbs.get(i).toString() + " (Pair of "+serialize(maxAbs.get(i).getCartasG().get(0).toString())+")");
					writer.append("\n");
				}

				else if (maxAbs.get(i).getBesthand() == Ranking.TWOPAIR) {
					writer.write("J"+asignacion.get(maxAbs.get(i))+": "+maxAbs.get(i).toString() + " (Two Pair)");
					writer.append("\n");
				}

				else if (maxAbs.get(i).getBesthand() == Ranking.THREEOFAKIND) {
					writer.write("J"+asignacion.get(maxAbs.get(i))+": "+maxAbs.get(i).toString() + " (Three of a kind of "+serialize(maxAbs.get(i).getCartasG().get(0).toString())+")");
					writer.append("\n");
				}

				else if (maxAbs.get(i).getBesthand() == Ranking.STRAIGHT) {
					writer.write("J"+asignacion.get(maxAbs.get(i))+": "+maxAbs.get(i).toString() + " (Straight)");
					writer.append("\n");
				}

				else if (maxAbs.get(i).getBesthand() == Ranking.FLUSH) {
					writer.write("J"+asignacion.get(maxAbs.get(i))+": "+maxAbs.get(i).toString() + " (Flush)");
					writer.append("\n");
				}

				else if (maxAbs.get(i).getBesthand() == Ranking.FULLHOUSE) {
					writer.write("J"+asignacion.get(maxAbs.get(i))+": "+maxAbs.get(i).toString() + " (Full House)");
					writer.append("\n");
				}

				else if (maxAbs.get(i).getBesthand() == Ranking.FOUROFAKIND) {
					writer.write("J"+asignacion.get(maxAbs.get(i))+": "+maxAbs.get(i).toString() + "(Four of a kind of "+serialize(maxAbs.get(i).getCartasG().get(0).toString())+")");
					writer.append("\n");
				}

				else if (maxAbs.get(i).getBesthand() == Ranking.STRAIGHTFLUSH) {
					writer.write("J"+asignacion.get(maxAbs.get(i))+": "+maxAbs.get(i).toString() + " (Straight Flush)");
					writer.append("\n");
				}

				else {
					writer.write("J"+asignacion.get(maxAbs.get(i))+": "+maxAbs.get(i).toString() + " (High Card "+ maxAbs.get(i).getCartas().get(4)+ ")");
					writer.append("\n");

				}


			}
			writer.append("\n");
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}



}