package Ap4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;
import Ap1.ap1;
import Ap2.ap2;
import Player.carta;
import Player.mano;

public class ap4 extends ap2{
	public String in;
	public String out;

	public ap4(String in, String out) {
		super(in, out);

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
				ArrayList<carta> h_cards = read_cards(s_h_cards);
				ArrayList<carta> c_cards = read_cards(s_c_Cards);


				ArrayList<mano> combs = new ArrayList<mano>();
				ArrayList<carta> manoAct = new ArrayList<carta>
				(Arrays.asList(h_cards.get(0), h_cards.get(1), h_cards.get(2), h_cards.get(3), h_cards.get(4)));
				combinaciones(h_cards, manoAct, 0, h_cards.size() - 1, 0, combs);
				//h_cards.clear();
				
				ArrayList<mano> combsF = new ArrayList<mano>();
				manoAct = new ArrayList<carta>
				(Arrays.asList(h_cards.get(0), h_cards.get(1), h_cards.get(2), h_cards.get(3), h_cards.get(4)));
				
				//Maximo absoluto, la mano a imprimir
				mano maxAbs = null;
				for( mano aux : combs) {
			        ArrayList<carta> resultList = 
			        		(ArrayList<carta>) Stream.concat(aux.getCartas().stream(), c_cards.stream()).collect(Collectors.toList());
					combinaciones(resultList, manoAct, 0, 2+Integer.parseInt(act.substring(9, 10)), 0, combsF);
					//Aqui se llama a evalua de ap1 y se guarda en un array
					mano max = evaluaCombinaciones(combs,number_of_c_cards);
					if(maxAbs == null)
						maxAbs = max;
					else if (max.getBesthand().biggerThan(maxAbs.getBesthand()))
						maxAbs = max;
				}
				//imprimir maxAbs
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	//Lee cartas y las mete en una mano. 
	// Copiado del ap1 de Pablo que me ha gustado como lo ha hecho.
	public ArrayList<carta> read_cards(String s_cards) {
		ArrayList<carta> hand = new ArrayList<carta>();
		for (int i = 0; i < s_cards.length(); i += 2) {
			carta c = new carta(s_cards.substring(i, i + 1),
					s_cards.substring(i + 1, i + 2));
			hand.add(c);
		}

		return hand;
	}


	public void combinaciones(ArrayList<carta> cartas, ArrayList<carta> manoAct,
			int ini, int fin, int i, ArrayList<mano> combs) {

		if (i == 5) {
			mano aux= new mano();

			for(int k=0;k<manoAct.size();k++){
				aux.addCarta(manoAct.get(k));
			}

			combs.add(aux);
		} else
			for (int j = ini; j <= fin && fin - j + 1 >= 5 - i; j++) {
				manoAct.set(i, cartas.get(j));
				combinaciones(cartas, manoAct, j + 1, fin, i + 1, combs);
			}
	}
}
