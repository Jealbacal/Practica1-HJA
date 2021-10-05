package Ap1;

import Player.Ranking;
import Player.Carta;
import Player.Mano;

import java.io.*;
import java.util.ArrayList;


public class Ap1 {

	public String in;
	public String out;
	
	public Ap1(String in, String out) {
		this.in = in;
		this.out = out;

	}

	public void buclePrincipal(){

		try {
			BufferedReader bff = new BufferedReader(new FileReader(new File(this.in)));
			String act;
			while ((act = bff.readLine()) != null) { //Bucle principal
				Mano mano = leerMano(act);
				evalua(mano);
				imprimir(mano);
			}


		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public Mano leerMano(String act) {
		Mano mano = new Mano();
		for (int i = 0; i < act.length(); i +=2) {
			Carta c = new Carta(act.substring(i,i+1), act.substring(i+1,i+2));
			mano.addCarta(c);
		}
		return mano;
	}
	
	public void evalua(Mano mano) {

		mano.getCartas().sort(Carta::compareTo);
		int i=0;
		int j=0;
		int hueco=0;
		int par1=1;
		int huecardo=0;
		int pEscalera=0;
		int almacenai=0;
		int trebol=0;
		int corazones=0;
		int diamantes=0;
		int picas=0;
		boolean straight=true,color=true,proyectoE=false;
		ArrayList<Ranking> comb = new ArrayList<>();
		ArrayList<Carta> combG=new ArrayList<>();

		while(i<mano.getCartas().size()-1){
        //miramos segun sea pareja y vamos mirando hacia delante si es trio o poker y lo guardamso en el string para mostrarlo y adelantamos la i
			if(i+1<mano.getCartas().size() &&mano.getCartas().get(i).getValor()==mano.getCartas().get(i+1).getValor()  ) {
				//me guardo si hay pareja y con par1 veo si hay mas de 1 para poner doble pareja

				if(i+2<mano.getCartas().size() && mano.getCartas().get(i).getValor()==mano.getCartas().get(i+2).getValor()  ){

					if(i+3<mano.getCartas().size() && mano.getCartas().get(i).getValor()==mano.getCartas().get(i+3).getValor()  ){

						comb.add(Ranking.FOUROFAKIND);


						combG.add(mano.getCartas().get(i));
						combG.add(mano.getCartas().get(i+1));
						combG.add(mano.getCartas().get(i+2));
						combG.add(mano.getCartas().get(i+3));

						i=i+3;

					}
					else {
						comb.add(Ranking.THREEOFAKIND);

						combG.add(mano.getCartas().get(i));
						combG.add(mano.getCartas().get(i+1));
						combG.add(mano.getCartas().get(i+2));
						mano.setTrio(mano.getCartas().get(i).getValor());

						i=i+2;
					}

				}
				else{
					par1++;
					comb.add(Ranking.PAIR);
					combG.add(mano.getCartas().get(i));
					combG.add(mano.getCartas().get(i+1));
					mano.setPareja(mano.getCartas().get(i).getValor());

					i++;

				}


			}

			i++;
		}



		while(j<mano.getCartas().size()-1) {
			//miramos si el tienen el mismo color o no
			//
			if(mano.getCartas().get(j).getPalo()=="d"){
				diamantes++;
			}
			else if(mano.getCartas().get(j).getPalo()=="c"){
				trebol++;
			}
			else if(mano.getCartas().get(j).getPalo()=="s"){
				picas++;
			}
			else if(mano.getCartas().get(j).getPalo()=="h"){
				corazones++;
			}



			//Miramos si los valores solo tienen una diferencia de 1 entre si para comprobar si ahy escalera y guradamos el valor de i para saber por donde seria
			//el draw y con pEscalera me aseguro que solo falta una carta para hacer la escalera
//			if(mano.getCartas().get(j).getValor()!=mano.getCartas().get(j+1).getValor()-1 ) {
//
//				straight = false;
//				//miramos si hay un hueco tipo 4h6c donde podria ir una entre medias
//				if ((mano.getCartas().get(j+1).getValor() - mano.getCartas().get(j ).getValor()) == 2) {
//					hueco++;
//					pEscalera++;
//					almacenai=j;
//
//				}
//				//si hay un hueco y estamos en el final esa carta podria susutituirse por una que entrara en el hueco
//				else if(hueco==1 && j==3  ){
//					hueco++;
//					//pEscalera=1;
//					pEscalera++;
//					almacenai=j-1;
//
//				}//caso de que solo haya uno diferente por ejemplo
//				else{
//
//					pEscalera++;
//					almacenai=j;
//				}
//			}
			//////////////////////////////////////
			if (mano.getCartas().get(j).getValor() != mano.getCartas().get(j + 1).getValor() - 1) {

				straight = false;
				//miramos si hay un hueco tipo 4h6c donde podria ir una entre medias

				if(hueco==1 && j==3  ){


					//pEscalera=1;
					pEscalera++;
					almacenai=j-1;

				}
				else if ((mano.getCartas().get(j + 1).getValor() - mano.getCartas().get(j).getValor()) == 2) {
					hueco++;
					pEscalera++;
					almacenai = j;


				}

				else if ((mano.getCartas().get(j + 1).getValor() - mano.getCartas().get(j).getValor()) > 2) {
					huecardo++;
					pEscalera++;
					almacenai = j;

					if(j==0|| j==3)
						huecardo--;


				}

				else {

					pEscalera++;
					almacenai = j;

				}

				//caso especial del 'A' con la escalera '2,3,4,5' //
				if (mano.getCartas().get(j).getValor() == 5 && mano.getCartas().get(j + 1).getValor() == 14 && pEscalera == 1 && j == 3) {

					straight = true;
					pEscalera = 0;
					almacenai = 0;
				}


			}
			j++;
		}

//		if((hueco==2) &&  pEscalera==2)
//			proyectoE=true;
//
//		else if(pEscalera==1)
//			proyectoE=true;
		//////////////////////
		//fallo algo entre medias
		//23789 23456 23336
		if(pEscalera==1 && mano.getCartas().get(4).getValor()-mano.getCartas().get(0).getValor()==4)
			proyectoE=true;
			//falla primero
			//26789
		else if(pEscalera==1 && almacenai==0)
			proyectoE=true;
			//falla ultimo 45679
		else if(pEscalera==1 && almacenai==3)
			proyectoE=true;
			//2 fallos entre medias per hueco
			//23356 567JK 23568
		else if(hueco==1 && pEscalera==2 && huecardo==0)
			proyectoE=true;

			//234KA 2456A
		else if(mano.getCartas().get(4).getValor()==14  && pEscalera==1){
			proyectoE=true;
			almacenai=0;
		}
		//2335A   2334A 2333A
		else if((mano.getCartas().get(4).getValor()==14 && mano.getCartas().get(0).getValor()==2 && pEscalera==2 && hueco==1)
				|| (mano.getCartas().get(4).getValor()==14 && mano.getCartas().get(0).getValor()==2 && pEscalera==2 && mano.getCartas().get(1).getValor() <= 5 &&
				mano.getCartas().get(2).getValor()<=5 && mano.getCartas().get(3).getValor()<=5)){

			proyectoE=true;
			almacenai=2;
		}


		if( picas==4 || corazones==4 || diamantes==4 || trebol==4 ){//draw de c
			mano.setDrawF(0);

		}

		if(proyectoE){
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


	public void imprimir (Mano mano){

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.out,true))){

			writer.write(mano.toString() );
			writer.append("\n");
			if(mano.getBesthand()==Ranking.PAIR){
				writer.write("-Best hand: Pair of "+ serialize(mano.getCartasG().get(0).toString()) +"("+mano.toStringCartasG()+")");
				writer.append("\n");
			}
			else if(mano.getBesthand()==Ranking.TWOPAIR){
				writer.write("-Best hand: Two-Pair  " + "("+mano.toStringCartasG()+")");
				writer.append("\n");
			}
			else if(mano.getBesthand()==Ranking.THREEOFAKIND){
				writer.write("-Best hand: Three of a kind of "+ serialize(mano.getCartasG().get(0).toString()) +"("+mano.toStringCartasG()+")");
				writer.append("\n");
			}
			else if(mano.getBesthand()==Ranking.STRAIGHT){
				writer.write("-Best hand: Straight "+ mano.toString());
				writer.append("\n");
			}
			else if(mano.getBesthand()==Ranking.FLUSH){
				writer.write("-Best hand: Flush "+  mano.toString());
				writer.append("\n");
			}
			else if(mano.getBesthand()==Ranking.FULLHOUSE){
				writer.write("-Best hand: Full house "+  mano.toString());
				writer.append("\n");
			}
			else if(mano.getBesthand()==Ranking.FOUROFAKIND){
				writer.write("-Best hand: Four of a kind of "+ serialize(mano.getCartasG().get(0).toString()) +"("+mano.toStringCartasG()+")");
				writer.append("\n");
			}
			else if(mano.getBesthand()==Ranking.STRAIGHTFLUSH){
				writer.write("-Best hand: Straight Flush "+ mano.toString());
				writer.append("\n");
			}
			else {
				writer.write("-Best hand: High card " + mano.getCartas().get(4).toString());
				writer.append("\n");

			}
			//draw miro que posibilidade tengo
			if(mano.getDrawF()==0) {
				writer.write("-Draw: Flush");
				writer.append("\n");
			}

			if(mano.getDrawS()==0){
				writer.write("-Draw: Straight Open-Ended");
				writer.append("\n");
			}

			else if (mano.getDrawS()==1) {
				writer.write("-Draw: Straight Gutshot");
				writer.append("\n");
			}

			writer.append("\n");


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//para escribir bien al sacar la mejor mano
	public String serialize(String ganadora) {

		switch (ganadora.charAt(0)){

			case 'A' :
				return "Aces";

			case 'K':
				return  "K's";

			case 'Q':
				return  "Q's";

			case 'J':
				return  "J's";

			case 'T':
				return  "T's";

			default:
				return  ganadora.charAt(0)+"'s";

		}
	}



}


