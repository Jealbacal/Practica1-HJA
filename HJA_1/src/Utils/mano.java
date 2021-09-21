package Utils;

import java.util.*;

public class mano {

	public ArrayList<carta> cartas;
	
	public mano (ArrayList<carta> cartas) {
		this.cartas = cartas;
	}
	
	public mano () {
		this.cartas = new ArrayList<>();
	}
	
	public ArrayList<carta> getCartas() {
		return cartas;
	}

	public void setCartas(ArrayList<carta> cartas) {
		this.cartas = cartas;
	}

	public void addCarta(carta carta) {
		cartas.add(carta);
	}
	
	@Override
	public String toString() {
		String aux = new String();
		for(int i = 0; i < cartas.size()-1 ;++i)
			aux += cartas.get(i).toString();
		return aux;
	}
	
}
