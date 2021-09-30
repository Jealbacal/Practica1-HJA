package Ap2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import Ap1.ap1;
import Player.*;


public class ap2   {

	public String in;
	public String out;

	public ap2(String in, String out) {

		this.in = in;
		this.out = out;

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
				ArrayList<carta> h_cards = read_cards(s_h_cards + s_c_Cards);
				//ArrayList<carta> c_cards = read_cards(s_c_Cards);


				ArrayList<mano> combs = new ArrayList<mano>();
				ArrayList<carta> manoAct = new ArrayList<carta>
						(Arrays.asList(h_cards.get(0), h_cards.get(1), h_cards.get(2), h_cards.get(3), h_cards.get(4)));
				combinaciones(h_cards, manoAct, 0, h_cards.size() - 1, 0, combs);
				evaluaCombinaciones(combs,number_of_c_cards);
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

 	public void evaluaCombinaciones(ArrayList<mano> combs,int number_of_c_cards){
		mano max = null;
		for (mano x : combs){
			evalua(x,number_of_c_cards);


		}

	}


	public void evalua(mano mano,int number_of_c_cards) {

		mano.cartas.sort(carta::compareTo);
		int i = 0;
		int j = 0;
		int hueco = 0;
		int par1 = 0;
		int pColor = 5;
		int pEscalera = 0;
		int almacenai = 0;
		boolean nose=false;
		String ganadora = "",ganar="";
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
					ganadora = mano.cartas.get(i).toString() + mano.cartas.get(i + 1).toString();

					i++;

				}


			}
			else
				mano.setCartasS(mano.cartas.get(i));

			i++;
		}


		while(j<mano.cartas.size()-1){
			//miramos si el tienen el mismo color o no
			if(!mano.cartas.get(j).getPalo().equals(mano.cartas.get(j + 1).getPalo())) {
				color = false;
				pColor--;
			}


			//Esta mal
			//Miramos si los valores solo tienen una diferencia de 1 entre si para comprobar si ahy escalera y guradamos el valor de i para saber por donde seria
			//el draw y con pEscalera me aseguro que solo falta una carta para hacer la escalera
			if(mano.cartas.get(j).getValor()!=mano.cartas.get(j+1).getValor()-1 ) {

				straight = false;

				if ((mano.cartas.get(j+1).getValor() - mano.cartas.get(j ).getValor()) == 2) {
					hueco++;
					pEscalera++;
					almacenai=j-1;

				}
				else if(hueco==1 && j==3  ){
					hueco++;
					//pEscalera=1;
					pEscalera++;
					almacenai=j-1;

				}
				else{

					pEscalera++;
					almacenai=j;
				}
			}

			//caso especial del 'A' con la escalera '2,3,4,5' //
			if(mano.cartas.get(j).getValor()==5 && mano.cartas.get(j+1).getValor()==14 && pEscalera==1 && j==3){

				straight=true;
				pEscalera=0;
				almacenai=0;
			}

			j++;
		}


		if((hueco==2)|| pEscalera==1)
			nose=true;


		//draw miro que posibilidade tengo
		if(pColor==4 && number_of_c_cards!=5 ){//draw de c
			mano.setDraws(0);

		}

		if(nose && number_of_c_cards!= 5){
			if(almacenai==0 || almacenai==3) {
				mano.setDraws(1);

			}

			else {
				mano.setDraws(2);
			}

		}

		//best mano miro al final que mano es mejor comprobando por orden de mejor a peor
		if (straight && color) {
			mano.setBesthand(Ranking.STRAIGHTFLUSH);

			mano.setCartasG(mano.toString());
		}

		else if (comb.contains(Ranking.FOUROFAKIND)){
			mano.setBesthand(Ranking.FOUROFAKIND);
			mano.setCartasG(ganadora);


		}


		else if (comb.contains(Ranking.THREEOFAKIND) && comb.contains(Ranking.PAIR)) {
			mano.setBesthand(Ranking.FULLHOUSE);
			mano.setCartasG(ganadora);

		}
		else if (color) {
			mano.setBesthand(Ranking.FLUSH);
			mano.setCartasG(mano.toString());

		}
		else if (straight) {
			mano.setBesthand(Ranking.STRAIGHT);

			mano.setCartasG(mano.toString());
		}
		else if (comb.contains(Ranking.THREEOFAKIND)) {
			mano.setBesthand(Ranking.THREEOFAKIND);


			mano.setCartasG(ganadora);
		}


		else if (par1 == 2) {
			mano.setBesthand(Ranking.TWOPAIR);
			mano.setCartasG(ganadora);
		}

		else if (comb.contains(Ranking.PAIR)) {
			mano.setBesthand(Ranking.PAIR);
			mano.setCartasG(ganadora);
		}

		else {
			mano.setBesthand(Ranking.HIGHCARD);
			mano.setCartasG (mano.cartas.get(4).toString());
		}


	}


}