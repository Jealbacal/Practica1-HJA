package Ap2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import Ap1.ap1;
import Player.*;


public class ap2  extends ap1 {

	public String in;
	public String out;

	public ap2(String in, String out) {
		super(in, out);

		try {
			// Mantenemos leer la linea entera y procesarla entera.
			BufferedReader bff = new BufferedReader(new FileReader(new File(this.in)));
			String act;
			while ((act = bff.readLine()) != null) { //Bucle principal

				/*----PARSE--------------------------------------------------------
				 * SIN TESTEAR TODAVIA*/

				/*Como las entradas no varian siempre ocupan
				 *  las mismas posiciones en el string, asi que podemos usar
				 *  .substring() con indices indiscriminadamente*/

				/* Este string recoge las cartas del jugador, mas adelante si
				 * hay clase jugador se puede añadir como atributo*/
				String s_h_cards = act.substring(0, 4);

				// Desde donde empiezan hasta el final del string
				String s_c_Cards = act.substring(7);

				// Por ahora es una varible local, ¿Habria que hacer algo con ella?
				// Es equivalente a .length() del ArrayList de cartas de
				// la instancia de c_cards de mano.
				// ---> ERROR  int number_of_c_cards = Integer.parseInt(act.substring(5, 6));

				//Metodos que leen las cartas de sus strings derivados de act.
				// Va a hacer falta un merge de manos para hacer las combis.
				// o otra forma de hacerlo xD

				//h_cards = hole_cards = tus cartas
				//c_cards = community_cards = las cartas de la mesa

				// Lo pongo en una unica lista porque las tiene que combinar indistintamente
				ArrayList<carta> h_cards = read_cards(s_h_cards + s_c_Cards);
				//ArrayList<carta> c_cards = read_cards(s_c_Cards);


				ArrayList<mano> combs = new ArrayList<mano>();
				ArrayList<carta> manoAct = new ArrayList<carta>
						(Arrays.asList(h_cards.get(0), h_cards.get(1), h_cards.get(2), h_cards.get(3), h_cards.get(4)));
				combinaciones(h_cards, manoAct, 0, h_cards.size() - 1, 0, combs);
				h_cards.clear();

				//Aqui se llama a evalua de ap1 y se pone la mejor de todas
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
	
/*
 *void combination(int arr[], int data[], int start, int end, int index, int r) 
{ 
  if(index == r) 
  { 
    for (int j = 0; j < r; j++) 
      cout << data[j] << " "; 
    cout << endl; 
    return; 
  } 
  for(int i = start; i <= end && end - i + 1 >= r - index; i++) 
  { 
    data[index] = arr[i]; 
    combination(arr, data, i+1, end, index+1, r); 
  } 
}  
 */

	public void combinaciones(ArrayList<carta> cartas, ArrayList<carta> manoAct,
							  int ini, int fin, int i, ArrayList<mano> combs) {

		if (i == 5) {
			mano aux = new mano(manoAct);
			combs.add(aux);
		} else
			for (int j = ini; j <= fin && fin - j + 1 >= 5 - i; j++) {
				manoAct.set(i, cartas.get(j));
				combinaciones(cartas, manoAct, j + 1, fin, i + 1, combs);
			}
	}

	@Override
	public void evalua(mano mano) {

		mano.cartas.sort(carta::compareTo);
		int i = 0;
		int j = 0;
		int hueco = 0;
		int par1 = 0;
		int pColor = 5;
		int pEscalera = 0;
		int almacenai = 0;
		boolean river;
		String ganadora = "",ganar;
		boolean straight = true, color = true;
		Ranking best;
		ArrayList<Ranking> comb = new ArrayList<>();

		while (i < mano.cartas.size() - 1) {
			//miramos segun sea pareja y vamos mirando hacia delante si es trio o poker y lo guardamso en el string para mostrarlo y adelantamos la i
			if (i + 1 < mano.cartas.size() && mano.cartas.get(i).getValor() == mano.cartas.get(i + 1).getValor()) {
				//me guardo si hay pareja y con par1 veo si hay mas de 1 para poner doble pareja

				if (i + 2 < mano.cartas.size() && mano.cartas.get(i).getValor() == mano.cartas.get(i + 2).getValor()) {

					if (i + 3 < mano.cartas.size() && mano.cartas.get(i).getValor() == mano.cartas.get(i + 3).getValor()) {

						comb.add(Ranking.FOUROFAKIND);

						ganadora = mano.cartas.get(i).toString() + mano.cartas.get(i + 1).toString() + mano.cartas.get(i + 2).toString() + mano.cartas.get(i + 3).toString();

						i = i + 3;

					} else {
						comb.add(Ranking.THREEOFAKIND);

						ganadora = mano.cartas.get(i).toString() + mano.cartas.get(i + 1).toString() + mano.cartas.get(i + 2).toString();
						i = i + 2;
					}

				} else {
					par1++;
					comb.add(Ranking.PAIR);
					ganadora = mano.cartas.get(i).toString() + mano.cartas.get(i + 1);

					i++;

				}


			}

			i++;
		}


		while (j < mano.cartas.size() - 1) {
			//miramos si el tienen el mismo color o no
			if (!mano.cartas.get(j).getPalo().equals(mano.cartas.get(j + 1).getPalo())) {
				color = false;
				pColor--;
			}


			//Esta mal
			//Miramos si los valores solo tienen una diferencia de 1 entre si para comprobar si ahy escalera y guradamos el valor de i para saber por donde seria
			//el draw y con pEscalera me aseguro que solo falta una carta para hacer la escalera
			if (mano.cartas.get(j).getValor() != mano.cartas.get(j + 1).getValor() - 1) {

				straight = false;

				if ((mano.cartas.get(j + 1).getValor() - mano.cartas.get(j).getValor()) == 2) {
					hueco++;

				} else if (hueco == 1 && i == 4) {
					hueco++;
					pEscalera = 1;

				} else {

					pEscalera++;
					almacenai = j;
				}
			}

			//caso especial del 'A' con la escalera '2,3,4,5' //
			if (mano.cartas.get(j).getValor() == 5 && mano.cartas.get(j + 1).getValor() == 14 && straight) {

				straight = true;
				pEscalera = 0;
				almacenai = 0;
			}

			j++;
		}

		//best mano miro al final que mano es mejor comprobando por orden de mejor a peor
		if (straight && color) {
			best = Ranking.STRAIGHTFLUSH;
			ganar = best.toString() + " " + mano.toString();
		}

		else if (comb.contains(Ranking.FOUROFAKIND)){
			best = Ranking.FOUROFAKIND;
			ganar = best.toString() + " " + ganadora;
		}


		else if (comb.contains(Ranking.THREEOFAKIND) && comb.contains(Ranking.PAIR)) {
			best = Ranking.FULLHOUSE;
			ganar = best.toString() + " " + ganadora;
		}
		else if (color) {
			best = Ranking.FLUSH;
			ganar = best.toString() + " " + mano.toString();
		}
		else if (straight) {
			best = Ranking.STRAIGHT;
			ganar = best.toString() + " " + mano.toString();
		}
		else if (comb.contains(Ranking.THREEOFAKIND)) {
			best = Ranking.THREEOFAKIND;
			ganar = best.toString() + " " + ganadora;
		}


		else if (par1 == 2) {
			best = Ranking.TWOPAIR;
			ganar = best.toString() + " " + ganadora;
		}

		else if (comb.contains(Ranking.PAIR)) {
			best = Ranking.PAIR;
			ganar = best.toString() + " " + ganadora;
		}

		else {
			best = Ranking.HIGHCARD;
			ganar = best.toString() + " " + mano.cartas.get(4).toString();
		}
	}

}