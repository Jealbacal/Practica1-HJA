package Ap2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import Ap1.Ap1;
import Player.*;


public class Ap2 extends Ap1 {

	public String in;
	public String out;

	public Ap2(String in, String out) {
		super(in,out);

	}

	@Override
	public void buclePrincipal(){

		try {
			// Mantenemos leer la linea entera y procesarla entera.
			BufferedReader bff = new BufferedReader(new FileReader(new File(this.in)));
			String act="";
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
				int number_of_c_cards = Integer.parseInt(act.substring(5, 6));

				//Metodos que leen las cartas de sus strings derivados de act.
				// Va a hacer falta un merge de manos para hacer las combis.
				// o otra forma de hacerlo xD

				//h_cards = hole_cards = tus cartas
				//c_cards = community_cards = las cartas de la mesa

				// Lo pongo en una unica lista porque las tiene que combinar indistintamente
				ArrayList<Carta> h_cards = read_cards(s_h_cards + s_c_Cards);
				//ArrayList<carta> c_cards = read_cards(s_c_Cards);


				ArrayList<Mano> combs = new ArrayList<Mano>();
				ArrayList<Carta> manoAct = new ArrayList<Carta>
						(Arrays.asList(h_cards.get(0), h_cards.get(1), h_cards.get(2), h_cards.get(3), h_cards.get(4)));
				combinaciones(h_cards, manoAct, 0, h_cards.size() - 1, 0, combs);
				Mano max = evaluaCombinaciones(combs,number_of_c_cards);

				//Imprimir max
				imprimir(max,act,number_of_c_cards);


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

	public void combinaciones(ArrayList<Carta> cartas, ArrayList<Carta> manoAct,
							  int ini, int fin, int i, ArrayList<Mano> combs) {

		if (i == 5) {
			Mano aux= new Mano();

			for(int k=0;k<manoAct.size();k++){
				 aux.addCarta(manoAct.get(k));
			}

			combs.add(aux);
		}
		else
			for (int j = ini; j <= fin && fin - j + 1 >= 5 - i; j++) {
				manoAct.set(i, cartas.get(j));
				combinaciones(cartas, manoAct, j + 1, fin, i + 1, combs);
			}
	}

 	public Mano evaluaCombinaciones(ArrayList<Mano> combs, int number_of_c_cards){
		Mano max = null;
			
		for (Mano x : combs){
			evalua(x);
			if(max == null)
				max= x;

			else if (x.getBesthand().biggerThan(max.getBesthand()))
				max = x;
			else if(x.getBesthand()==max.getBesthand()){

				max=max.compareMano(x);

			}
		}
		return max;
	}
 	

	public void  imprimir(Mano max, String act, int number_of_c_cards ){


		try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.out,true))) {

			writer.write(act);
			writer.append("\n");
			if (max.getBesthand() == Ranking.PAIR) {
				writer.write("-Best hand: Pair of " + serialize(max.getCartasG().get(0).toString()) + " with " + max.toString());
				writer.append("\n");
			} else if (max.getBesthand() == Ranking.TWOPAIR) {
				writer.write("-Best hand: Two-Pair  " + " with " + max.toString());
				writer.append("\n");
			} else if (max.getBesthand() == Ranking.THREEOFAKIND) {
				writer.write("-Best hand: Three of a kind of " + serialize(max.getCartasG().get(0).toString()) + " with " + max.toString());
				writer.append("\n");
			} else if (max.getBesthand() == Ranking.STRAIGHT) {
				writer.write("-Best hand: Straight " + " with " + max.toString());
				writer.append("\n");
			} else if (max.getBesthand() == Ranking.FLUSH) {
				writer.write("-Best hand: Flush " + " with " + max.toString());
				writer.append("\n");
			} else if (max.getBesthand() == Ranking.FULLHOUSE) {
				writer.write("-Best hand: Full house " + " with " + max.toString());
				writer.append("\n");
			} else if (max.getBesthand() == Ranking.FOUROFAKIND) {
				writer.write("-Best hand: Four of a kind of " + serialize(max.getCartasG().get(0).toString()) + " with " + max.toString());
				writer.append("\n");
			} else if (max.getBesthand() == Ranking.STRAIGHTFLUSH) {
				writer.write("-Best hand: Straight Flush " + " with " + max.toString());
				writer.append("\n");
			} else {
				writer.write("-Best hand: High card " + max.getCartas().get(4).toString() + " with " + max.toString());
				writer.append("\n");

			}

			if (number_of_c_cards != 5) {

				if (max.getDrawF() == 0) {
					writer.write("-Draw: Flush");
					writer.append("\n");
				}

				if (max.getDrawS() == 0) {
					writer.write("-Draw: Straight Open-Ended");
					writer.append("\n");
				} else if (max.getDrawS() == 1) {
					writer.write("-Draw: Straight Gutshot");
					writer.append("\n");
				}
				writer.append("\n");
			}
		}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}


}