package Ap3;

import Player.Partida;
import Player.Ranking;
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

public class ap3 {

	public String in;
	public String out;

	public ap3(String in, String out) {

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
				System.out.println("nose");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Partida leerMano(String act) {
		int nJg = (int) (act.charAt(0)-'0');
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


	public void combinaciones(ArrayList<carta> cartas, ArrayList<carta> manoAct,
							  int ini, int fin, int i, ArrayList<mano> combs) {

		if (i == 5) {
			mano aux= new mano();

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