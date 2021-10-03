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
import Player.Ranking;
import Player.carta;
import Player.mano;

public class ap4 {
	public String in;
	public String out;

	public ap4(String in, String out) {

		this.in = in;
		this.out = out;
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
				 * hay clase jugador se puede aÃ±adir como atributo*/
				String s_h_cards = act.substring(0, 8);

				// Desde donde empiezan hasta el final del string
				String s_c_Cards = act.substring(11);

				// Por ahora es una varible local, Â¿Habria que hacer algo con ella?
				// Es equivalente a .length() del ArrayList de cartas de
				// la instancia de c_cards de mano.
				int number_of_c_cards = Integer.parseInt(act.substring(9, 10));

				//Metodos que leen las cartas de sus strings derivados de act.
				// Va a hacer falta un merge de manos para hacer las combis.
				// o otra forma de hacerlo xD

				//h_cards = hole_cards = tus cartas
				//c_cards = community_cards = las cartas de la mesa

				// Lo pongo en una unica lista porque las tiene que combinar indistintamente
				ArrayList<carta> h_cards = read_cards(s_h_cards + s_c_Cards);
				ArrayList<carta> c_cards = read_cards(s_c_Cards);


				ArrayList<mano> combs = new ArrayList<mano>();
				ArrayList<carta> manoAct = new ArrayList<carta>
						(Arrays.asList(h_cards.get(0), h_cards.get(0), h_cards.get(0), h_cards.get(0), h_cards.get(0)));
				combinaciones(h_cards, manoAct, 0, h_cards.size() - 1, 0, combs);
				//h_cards.clear();

				ArrayList<mano> combsF = new ArrayList<mano>();
				manoAct = new ArrayList<carta>
						(Arrays.asList(h_cards.get(0), h_cards.get(0), h_cards.get(0), h_cards.get(0), h_cards.get(0)));

				//Maximo absoluto, la mano a imprimir
				mano maxAbs = null;
				for( mano aux : combs) {
					ArrayList<carta> resultList =
							(ArrayList<carta>) Stream.concat(aux.getCartas().stream(), c_cards.stream()).collect(Collectors.toList());
					combinaciones(manoAct, resultList, 0, 2+Integer.parseInt(act.substring(9, 10)), 0, combsF);
					//Aqui se llama a evalua de ap1 y se guarda en un array
					mano max = evaluaCombinaciones(combs,number_of_c_cards);
					if(maxAbs == null)
						maxAbs = max;
					else if (max.getBesthand().biggerThan(maxAbs.getBesthand()))
						maxAbs = max;
				}
				//imprimir maxAbs
				System.out.println("fgh");

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



	public mano evaluaCombinaciones(ArrayList<mano> combs,int number_of_c_cards){
		mano max = null;

		for (mano x : combs){
			evalua(x,number_of_c_cards);
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
		ArrayList<carta> combG=new ArrayList<>();

		while (i < mano.cartas.size() - 1) {
			//miramos segun sea pareja y vamos mirando hacia delante si es trio o poker y lo guardamso en el string para mostrarlo y adelantamos la i
			if (i + 1 < mano.cartas.size() && mano.cartas.get(i).getValor() == mano.cartas.get(i + 1).getValor()) {
				//me guardo si hay pareja y con par1 veo si hay mas de 1 para poner doble pareja

				if (i + 2 < mano.cartas.size() && mano.cartas.get(i).getValor() == mano.cartas.get(i + 2).getValor()) {

					if (i + 3 < mano.cartas.size() && mano.cartas.get(i).getValor() == mano.cartas.get(i + 3).getValor()) {

						comb.add(Ranking.FOUROFAKIND);

						//ganadora = mano.cartas.get(i).toString() + mano.cartas.get(i + 1).toString() + mano.cartas.get(i + 2).toString() + mano.cartas.get(i + 3).toString();

						combG.add(mano.cartas.get(i));
						combG.add(mano.cartas.get(i+1));
						combG.add(mano.cartas.get(i+2));
						combG.add(mano.cartas.get(i+3));


						i = i + 3;

					} else {
						comb.add(Ranking.THREEOFAKIND);

						//ganadora = mano.cartas.get(i).toString() + mano.cartas.get(i + 1).toString() + mano.cartas.get(i + 2).toString();
						combG.add(mano.cartas.get(i));
						combG.add(mano.cartas.get(i+1));
						combG.add(mano.cartas.get(i+2));

						i = i + 2;
					}

				} else {
					par1++;
					comb.add(Ranking.PAIR);
					combG.add(mano.cartas.get(i));
					combG.add(mano.cartas.get(i+1));
					//ganadora = mano.cartas.get(i).toString() + mano.cartas.get(i + 1).toString();

					i++;


				}

				if(i==3){
					mano.setCartasS(mano.cartas.get(i+1));
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

				if(j>=1 && j<=3 && mano.cartas.get(j-1).getPalo().equals(mano.cartas.get(j + 1).getPalo()))
					pColor++;
			}


			//Esta mal
			//Miramos si los valores solo tienen una diferencia de 1 entre si para comprobar si ahy escalera y guradamos el valor de i para saber por donde seria
			//el draw y con pEscalera me aseguro que solo falta una carta para hacer la escalera
			if(mano.cartas.get(j).getValor()!=mano.cartas.get(j+1).getValor()-1 ) {

				straight = false;

				if ((mano.cartas.get(j+1).getValor() - mano.cartas.get(j ).getValor()) == 2) {
					hueco++;
					pEscalera++;
					almacenai=j;

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

		if((hueco==2) &&  pEscalera==2)
			nose=true;

		else if(pEscalera==1)
			nose=true;


		//draw miro que posibilidade tengo
		if(pColor==4 && number_of_c_cards!=5 ){//draw de c
			mano.setDrawF(0);

		}

		if(nose && number_of_c_cards!= 5){
			if(almacenai==0 || almacenai==3) {
				mano.setDrawS(0);
			}

			else {
				mano.setDrawS(1);
			}

		}

		//best mano miro al final que mano es mejor comprobando por orden de mejor a peor
		if (straight && color) {
			mano.setBesthand(Ranking.STRAIGHTFLUSH);

			//mano.setCartasG(mano.cartas);
		}

		else if (comb.contains(Ranking.FOUROFAKIND)){
			mano.setBesthand(Ranking.FOUROFAKIND);
			mano.setCartasG(combG);


		}


		else if (comb.contains(Ranking.THREEOFAKIND) && comb.contains(Ranking.PAIR)) {
			mano.setBesthand(Ranking.FULLHOUSE);
			mano.setCartasG(combG);

		}
		else if (color) {
			mano.setBesthand(Ranking.FLUSH);
			//mano.setCartasG(mano.cartas);

		}
		else if (straight) {
			mano.setBesthand(Ranking.STRAIGHT);

			//mano.setCartasG(mano.cartas);
		}
		else if (comb.contains(Ranking.THREEOFAKIND)) {
			mano.setBesthand(Ranking.THREEOFAKIND);


			mano.setCartasG(combG);
		}


		else if (par1 == 2) {
			mano.setBesthand(Ranking.TWOPAIR);
			mano.setCartasG(combG);
		}

		else if (comb.contains(Ranking.PAIR)) {
			mano.setBesthand(Ranking.PAIR);
			mano.setCartasG(combG);
		}

		else {
			mano.setBesthand(Ranking.HIGHCARD);
			//mano.setCartasG (mano.cartas.get(4));
		}

	}
}
