package Player;

import java.util.ArrayList;

public class Partida {

    public ArrayList<carta> mesa;
    public ArrayList<mano> jugadores;

    public Partida () {
        jugadores = new ArrayList<mano>();
    }

    public Partida (ArrayList<carta> mesa, ArrayList<mano> jugadores) {
        this.jugadores = jugadores;
        this.mesa = mesa;
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



}
