package Ap1;

import Player.Ranking;
import Player.carta;
import Player.mano;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class ap1 {

	public String in;
	public String out;
	
	public ap1(String in, String out) {
		this.in = in;
		this.out = out;
		
		try {
			BufferedReader bff = new BufferedReader(new FileReader(new File(this.in)));
			String act;
			while ((act = bff.readLine()) != null) { //Bucle principal
				mano mano = leerMano(act);
				evalua(mano);
			}
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public mano leerMano(String act) {
		mano mano = new mano();
		for (int i = 0; i < act.length(); i +=2) {
			carta c = new carta(act.substring(i,i+1), act.substring(i+1,i+2));
			mano.addCarta(c);
		}
		return mano;
	}
	
	public void evalua(mano mano) {

		mano.cartas.sort(carta::compareTo);
		int i=0;
		int j=0;
		int hueco=0;
		int par1=0;
		int pColor=5;
		int pEscalera=0;
		int almacenai=0;
		String ganadora="";
		boolean straight=true,color=true;
		Ranking best;
		ArrayList<Ranking> comb = new ArrayList<>();

		while(i<mano.cartas.size()-1){
        //miramos segun sea pareja y vamos mirando hacia delante si es trio o poker y lo guardamso en el string para mostrarlo y adelantamos la i
			if(i+1<mano.cartas.size() &&mano.cartas.get(i).getValor()==mano.cartas.get(i+1).getValor()  ) {
				//me guardo si hay pareja y con par1 veo si hay mas de 1 para poner doble pareja

				if(i+2<mano.cartas.size() && mano.cartas.get(i).getValor()==mano.cartas.get(i+2).getValor()  ){

					if(i+3<mano.cartas.size() && mano.cartas.get(i).getValor()==mano.cartas.get(i+3).getValor()  ){

						comb.add(Ranking.FOUROFAKIND);

						ganadora=mano.cartas.get(i).toString()+mano.cartas.get(i+1).toString()+mano.cartas.get(i+2).toString()+mano.cartas.get(i+3).toString();

						i=i+3;

					}
					else {
						comb.add(Ranking.THREEOFAKIND);

						ganadora=mano.cartas.get(i).toString()+mano.cartas.get(i+1).toString()+mano.cartas.get(i+2).toString();
						i=i+2;
					}

				}
				else{
					par1++;
					comb.add(Ranking.PAIR);
					ganadora=mano.cartas.get(i).toString()+mano.cartas.get(i+1);

					i++;

				}


			}

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

				}
				else if(hueco==1 && i==4  ){
					hueco++;
					pEscalera=1;

				}
				else{

					pEscalera++;
					almacenai=j;
				}
			}

			//caso especial del 'A' con la escalera '2,3,4,5' //
			if(mano.cartas.get(j).getValor()==5 && mano.cartas.get(j+1).getValor()==14 && straight){

				straight=true;
				pEscalera=0;
				almacenai=0;
			}

			j++;
		}

		//best mano miro al final que mano es mejor comprobando por orden de mejor a peor
		if(straight && color)
			best=Ranking.STRAIGHTFLUSH;

		else if (comb.contains(Ranking.FOUROFAKIND))
			best=Ranking.FOUROFAKIND;

		else if(comb.contains(Ranking.THREEOFAKIND) && comb.contains(Ranking.PAIR) )
			best=Ranking.FULLHOUSE;

		else if(color)
			best=Ranking.FLUSH;

		else if (straight)
			best=Ranking.STRAIGHT;

		else if (comb.contains(Ranking.THREEOFAKIND))
			best=Ranking.THREEOFAKIND;

		else if (par1==2){
			best=Ranking.TWOPAIR;
		}
		else if(comb.contains(Ranking.PAIR))
			best=Ranking.PAIR;

		else
			best=Ranking.HIGHCARD;




		try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.out,true))){

			writer.write(mano.toString() );
			writer.append("\n");
			if(best==Ranking.PAIR){
				writer.write("-Best hand: Pair of "+ serialize(ganadora) +"("+ganadora+")");
				writer.append("\n");
			}
			else if(best==Ranking.TWOPAIR){
				writer.write("-Best hand: Two-Pair  " + "("+ganadora+")"+ writer.append("\n\n"));
				writer.append("\n");
			}
			else if(best==Ranking.THREEOFAKIND){
				writer.write("-Best hand: Three of a kind of "+ serialize(ganadora) +"("+ganadora+")");
				writer.append("\n");
			}
			else if(best==Ranking.STRAIGHT){
				writer.write("-Best hand: Straight "+ mano.toString());
				writer.append("\n");
			}
			else if(best==Ranking.FLUSH){
				writer.write("-Best hand: Flush "+  mano.toString());
				writer.append("\n");
			}
			else if(best==Ranking.FULLHOUSE){
				writer.write("-Best hand: Full house "+  mano.toString());
				writer.append("\n");
			}
			else if(best==Ranking.FOUROFAKIND){
				writer.write("-Best hand: Four of a kind of "+ serialize(ganadora) +"("+ganadora+")");
				writer.append("\n");
			}
			else if(best==Ranking.STRAIGHTFLUSH){
				writer.write("-Best hand: Straight Flush "+ mano.toString());
				writer.append("\n");
			}
			else {
				writer.write("-Best hand: High card " + mano.cartas.get(4).toString());
				writer.append("\n");

			}
			//draw miro que posibilidade tengo
			if(pColor==4){//draw de c
				writer.write("-Draw: Flush");
				writer.append("\n");
			}

			if(pEscalera==1){
				if(almacenai==0) {
					writer.write("-Draw: Straight Open");
					writer.append("\n");
				}

				else if(almacenai==3) {
					writer.write("-Draw: Straight Ended" );
					writer.append("\n");
				}
				else {
					writer.write("-Draw: Straight Gutshot");
					writer.append("\n");
				}

			}

			writer.append("\n");


		} catch (IOException e) {
			e.printStackTrace();
		}

	}


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


