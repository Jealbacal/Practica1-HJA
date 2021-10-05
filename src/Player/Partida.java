package Player;

import java.util.ArrayList;

public class Partida {

    private ArrayList<Carta> mesa;
    private ArrayList<Mano> jugadores;
    private int nJug;

    public Partida () {
        jugadores = new ArrayList<Mano>();
    }

    public Partida (ArrayList<Carta> mesa, ArrayList<Mano> jugadores, int nJug) {
        this.jugadores = jugadores;
        this.mesa = mesa;
        this.nJug = nJug;
    }

    public ArrayList<Carta> getMesa() {
        return mesa;
    }

    public void setMesa(ArrayList<Carta> mesa) {
        this.mesa = mesa;
    }

    public ArrayList<Mano> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Mano> jugadores) {
        this.jugadores = jugadores;
    }

    public void addManoJugador(Mano mano) {
        jugadores.add(mano);
    }

	public int getnJug() {
		return nJug;
	}

	public void setnJug(int nJug) {
		this.nJug = nJug;
	}

    

}
