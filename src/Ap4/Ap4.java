package Ap4;

import Player.Ranking;
import Player.Carta;
import Player.Mano;
import Ap2.Ap2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ap4 extends Ap2 {

	public String in;
	public String out;

	public Ap4(String in, String out) {
		super(in,out);

	}

	@Override
	public void buclePrincipal(){
		try {
			// Mantenemos leer la linea entera y procesarla entera.
			BufferedReader bff = new BufferedReader(new FileReader(new File(this.in)));
			String act = "";
			while ((act = bff.readLine()) != null) { //Bucle principal

				/*----PARSE--------------------------------------------------------
				 * SIN TESTEAR TODAVIA*/

				/*Como las entradas no varian siempre ocupan
				 *  las mismas posiciones en el string, asi que podemos usar
				 *  .substring() con indices indiscriminadamente*/

				/* Este string recoge las cartas del jugador, mas adelante si
				 * hay clase jugador se puede añadir como atributo*/
				//AhAc8s5h;4;2h2d2c2s
				String s_h_cards = act.substring(0, 8);

				// Desde donde empiezan hasta el final del string
				String s_c_Cards = act.substring(11);

				// Por ahora es una varible local, ¿Habria que hacer algo con ella?
				// Es equivalente a .length() del ArrayList de cartas de
				// la instancia de c_cards de mano.
				int number_of_c_cards = Integer.parseInt(act.substring(9, 10));

				//Metodos que leen las cartas de sus strings derivados de act.
				// Va a hacer falta un merge de manos para hacer las combis.
				// o otra forma de hacerlo xD

				//h_cards = hole_cards = tus cartas
				//c_cards = community_cards = las cartas de la mesa

				// Lo pongo en una unica lista porque las tiene que combinar indistintamente
				ArrayList<Carta> h_cards = read_cards(s_h_cards);
				ArrayList<Carta> c_cards = read_cards(s_c_Cards);


				ArrayList<Mano> combs = new ArrayList<Mano>();
				ArrayList<Carta> manoAct = new ArrayList<Carta>
						(Arrays.asList(h_cards.get(0), h_cards.get(0), h_cards.get(0), h_cards.get(0), h_cards.get(0)));
				combinaciones(h_cards, manoAct, 0, h_cards.size() - 1, 0, 2, combs);
				//h_cards.clear();

				ArrayList<Mano> combsF = new ArrayList<Mano>();
				manoAct = new ArrayList<Carta>
						(Arrays.asList(h_cards.get(0), h_cards.get(0), h_cards.get(0), h_cards.get(0), h_cards.get(0)));

				//Maximo absoluto, la mano a imprimir
				Mano maxAbs = null;
				for( Mano aux : combs) {

					combinaciones(c_cards, manoAct, 0, 2+c_cards.size() - 1, 0, 3, combsF);

					for (Mano aux2 : combsF) {
						aux2.setCartas((ArrayList<Carta>) Stream.concat(aux.getCartas().stream(), aux2.getCartas().stream()).collect(Collectors.toList()));

					}
					Mano max = evaluaCombinaciones(combsF,number_of_c_cards);
					if(maxAbs == null)
						maxAbs = max;
					else if (max.getBesthand().biggerThan(maxAbs.getBesthand()))
						maxAbs = max;
				}
				//imprimir maxAbs
				print_out(maxAbs,act);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//Lee cartas y las mete en una mano. 
	// Copiado del ap1 de Pablo que me ha gustado como lo ha hecho.
	public ArrayList<Carta> read_cards(String s_cards) {
		ArrayList<Carta> hand = new ArrayList<Carta>();
		for (int i = 0; i < s_cards.length(); i += 2) {
			Carta c = new Carta(s_cards.substring(i, i + 1),
					s_cards.substring(i + 1, i + 2));
			hand.add(c);
		}

		return hand;
	}
	
	public void combinaciones(ArrayList<Carta> cartas, ArrayList<Carta> manoAct,
			int ini, int fin, int i,int size, ArrayList<Mano> combs) {

		if (i == size) {
			Mano aux= new Mano();

			for(int k=0;k<size;k++){
				aux.addCarta(manoAct.get(k));
			}

			combs.add(aux);
		}
		else
			for (int j = ini; j <= fin && fin - j + 1 >= size - i && j < size; j++) {
				manoAct.set(i, cartas.get(j));
				combinaciones(cartas, manoAct, j + 1, fin, i + 1, size, combs);
			}
	}




	
	private void print_out(Mano maxAbs, String act) throws IOException {
	        
	        FileWriter fw = new FileWriter("out.txt",true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        PrintWriter out = new PrintWriter(bw);
	        
	        out.println(act);
	        
	        if (maxAbs.getBesthand() == Ranking.PAIR) {
	            out.write("-Best hand: Pair of " + serialize(maxAbs.getCartasG().get(0).toString()) + " with " + maxAbs.toString());
	            out.append("\n");
	        } else if (maxAbs.getBesthand() == Ranking.TWOPAIR) {
	            out.write("-Best hand: Two-Pair  " + " with " + maxAbs.toString());
	            out.append("\n");
	        } else if (maxAbs.getBesthand() == Ranking.THREEOFAKIND) {
	            out.write("-Best hand: Three of a kind of " + serialize(maxAbs.getCartasG().get(0).toString()) +  " with " + maxAbs.toString());
	            out.append("\n");
	        } else if (maxAbs.getBesthand() == Ranking.STRAIGHT) {
	            out.write("-Best hand: Straight " + " with " + maxAbs.toString());
	            out.append("\n");
	        } else if (maxAbs.getBesthand() == Ranking.FLUSH) {
	            out.write("-Best hand: Flush " + " with " + maxAbs.toString());
	            out.append("\n");
	        } else if (maxAbs.getBesthand() == Ranking.FULLHOUSE) {
	            out.write("-Best hand: Full house " + " with " + maxAbs.toString());
	            out.append("\n");
	        } else if (maxAbs.getBesthand() == Ranking.FOUROFAKIND) {
	            out.write("-Best hand: Four of a kind of " + serialize(maxAbs.getCartasG().get(0).toString()) + " with " + maxAbs.toString());
	            out.append("\n");
	        } else if (maxAbs.getBesthand() == Ranking.STRAIGHTFLUSH) {
	            out.write("-Best hand: Straight Flush " + " with " + maxAbs.toString());
	            out.append("\n");
	        } else {
	            out.write("-Best hand: High card " + maxAbs.getCartas().get(4).toString() + " with " + maxAbs.toString());
	            out.append("\n");
	
	        }
	
	        if(maxAbs.getDrawF()==0) {
	            out.write("-Draw: Flush");
	            out.append("\n");
	        }
	
	        if(maxAbs.getDrawS()==0){
	            out.write("-Draw: Straight Open-Ended");
	            out.append("\n");
	        }
	
	        else if (maxAbs.getDrawS()==1) {
	            out.write("-Draw: Straight Gutshot");
	            out.append("\n");
	        }
	        out.append("\n");
	        
	        out.close();
	    }
	

	
}
