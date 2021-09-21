package Utils;

public class carta {
	
	public int valor;
	public String palo;
	
	public carta(String valor, String palo) {
		
		switch(valor) {
		case"A":
			this.valor = 1;
			break;
		case"K":
			this.valor = 13;
			break;
		case"Q":
			this.valor = 12;
			break;
		case"J":
			this.valor = 11;
			break;
		case"T":
			this.valor = 10;
			break;
		default:
			this.valor = Integer.parseInt(valor);
		}
		
		this.palo = palo;
	}
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getPalo() {
		return palo;
	}

	public void setPalo(String palo) {
		this.palo = palo;
	}

	@Override
	public String toString() {
		return valor + palo;
	}
}
