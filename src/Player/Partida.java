package Player;

import java.util.ArrayList;

public class Partida {

    public ArrayList<carta> mesa;
    public ArrayList<mano> jugadores;
    public int nJug;

    public Partida () {
        jugadores = new ArrayList<mano>();
    }

    public Partida (ArrayList<carta> mesa, ArrayList<mano> jugadores, int nJug) {
        this.jugadores = jugadores;
        this.mesa = mesa;
        this.nJug = nJug;
    }

    public ArrayList<carta> getMesa() {
        return mesa;
    }

    public void setMesa(ArrayList<carta> mesa) {
        this.mesa = mesa;
    }

    public ArrayList<mano> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<mano> jugadores) {
        this.jugadores = jugadores;
    }

    public void addManoJugador(mano mano) {
        jugadores.add(mano);
    }

	public int getnJug() {
		return nJug;
	}

	public void setnJug(int nJug) {
		this.nJug = nJug;
	}

    

}
