package Ap2;

import java.io.*;

import Utils.*;

public class ap2 {
	
	/*Si el flujo principal de cada apartado va a ser en estas
	 * clases podemos poner como atributos las manos generadas
	 * 
	 *  o
	 *  
	 *  Una clase Controller que lleve todo y se lance desde main
	 *  y lleve una clase como atributo que lleve la logica de evaluacion y eso*/

	public String in;
	public String out;
	
	
	public ap2(String in, String out) {
		this.in = in;
		this.out = out;
		
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
				String s_h_cards = act.substring(0, 3);
				
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
				mano h_cards = read_cards(s_h_cards);
				mano c_cards = read_cards(s_c_Cards);
				
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//Lee cartas y las mete en una mano. 
	// Copiado del ap1 de Pablo que me ha gustado como lo ha hecho.
	public mano read_cards(String s_cards) {
		mano hand = new mano();
		for(int i = 0; i < s_cards.length(); i += 2) {
			carta c = new carta(s_cards.substring(i, i+1),
					s_cards.substring(i+1, i+2));
			hand.addCarta(c);
		}
		
		return hand;
	}
	
	
	
	public void evalua() {
		
		//
		//	ToDo
		//
		
	}
}
