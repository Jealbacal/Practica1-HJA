package Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import Player.carta;
import Player.mano;

public class InputFileGenerator {
	
	private int n_hands;
	private final static int NLHE_c_h_limit = 5;
	private final int deck = 13;
	private final int suit = 4;
	private final String file_name1 = "entrada_ap1.txt";
	private final String file_name2 = "entrada_ap2.txt";
	private final String file_name3 = "entrada_ap3.txt";
	
	public InputFileGenerator(int n_hands) {
		this.n_hands = n_hands;
	}
	
	//Genera un archivo con el formato ap1
	public void generate_ap1_file() throws IOException {
		
		//Prepara el archivo
		FileWriter fw = new FileWriter(file_name1,false);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
		
		//Genera una mano de 5 cartas
		for(int i = 0; i < n_hands; i++ ) {
			out.println(generateHand(NLHE_c_h_limit));
		}
		
		//Cierra el archivo
		out.close();
			
		}
	
	//Genera un archivo con el formato ap2
	public void generate_ap2_file() throws IOException {
		
		//Prpara el archivo
		FileWriter fw = new FileWriter(file_name2,false);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
		Random random = new Random();
		
		//Genera linea por linea segun el formato especificado
		for(int i = 0; i < n_hands; i++) {
			//Genera las hole cards (2 cartas)
			String line = generateHand(2);
			
			//Separador
			line = line.concat(";");
			
			//Randomiza si estamos en flop, turn, o river
			int n_c_cards = random.ints(3,5+1).findFirst().getAsInt();
			line = line.concat(Integer.toString(n_c_cards));
			
			//Separador
			line = line.concat(";");
			
			//Genera el numero de cartas que indica n_c_cards 
			String c_cards = generateHand(n_c_cards);
			line = line.concat(c_cards);
			
			//Imprime en el archivo
			out.println(line);
		}
		
		//Cierra el archivo
		out.close();
	}
		
	//Genera una mano
	private String generateHand(int n_cards) {
		
		String r_hand = "";
		String r_card = "";
		boolean same_card = false;
		ArrayList<String> aux2comp = new ArrayList<String>();
		
		// Creamos una mano de 5 cartas
		for(int i = 0; i < n_cards; i++) {
			do {
				//Genera la carta
				r_card = generateCard();
				
				//Comprueba que la carta no ha salido ya
				if(!aux2comp.isEmpty()) {
					for(int j = 0; j < aux2comp.size(); j++) {
						if(r_card.equals(aux2comp.get(j))) {
							same_card = true;
						}else {
							same_card = false;
						}
					}
				}
			// Repite si la carta ya ha salido
			}while(same_card);
			
			//Concatena cartas en la mano	
			r_hand = r_hand.concat(r_card); 
			
			//añada al ararylist de la mano para ver si esta repetida
			aux2comp.add(r_card);
			
		}
		
		return r_hand;
	}
	
	//Genera una carta
	private String generateCard() {
		String suit;
		String value;
		Random random = new Random();
		
		//generar numero aleatorio del 1 al 13
		int r_value = random.ints(1,this.deck+1).findFirst().getAsInt();
		//int r_value = (int) ((Math.random() * (13 - 1)) + 1);
		
		//generar numero aleatorio del 0 al 3
		//int r_suit = (int) ((Math.random() * (3 - 0)) + 0);
		int r_suit = random.ints(1,this.suit+1).findFirst().getAsInt();
		
		//traducir numeros a carta
		suit = random2suit(r_suit);
		value = random2value(r_value);
		
		// Son strings asi que concatenamos
		return value.concat(suit);
	}
	
	//Cambia el valor al string de la carta
	private String random2value(int r_valor) {
		String value = null;
		switch (r_valor) {
		case 1:
			value = "A";
			break;
		case 2:
			value = "2";
			break;
		case 3:
			value = "3";
			break;
		case 4:
			value = "4";
			break;
		case 5:
			value = "5";
			break;
		case 6:
			value = "6";
			break;
		case 7:
			value = "7";
			break;
		case 8:
			value = "8";
			break;
		case 9:
			value = "9";
			break;
		case 10:
			value = "T";
			break;
		case 11:
			value = "J";
			break;
		case 12:
			value = "Q";
			break;
		case 13:
			value = "K";
			break;	
		}
		
		return value;
	}

	//Cambia el valor al string del palo
	private String random2suit(int r_suit) {
		String suit = "";
		
		switch (r_suit) {
		case 1:
			suit = "d";
			break;
		case 2:
			suit = "h";
			break;
		case 3:
			suit = "s";
			break;
		case 4:
			suit = "c";
			break;
		}
		
		return suit;
	}
	
	//Generador
	public static void main(String[] args) {
		
		int n_cases = 10;
		
		InputFileGenerator test = new InputFileGenerator(n_cases);
		
		try {
			test.generate_ap1_file();
			test.generate_ap2_file();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
