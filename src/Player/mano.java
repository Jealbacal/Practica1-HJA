package Player;

import java.util.ArrayList;


public class mano  {

    public ArrayList<carta> cartas;
    public Ranking besthand;
    public String cartasG;
    public carta cartasS;
    public int draws;


    public Ranking getBesthand() {
        return besthand;
    }

    public String getCartasG() {
        return cartasG;
    }

    public void setBesthand(Ranking besthand) {
        this.besthand = besthand;
    }

    public void setCartasG(String cartasG) {
        this.cartasG = cartasG;
    }

    public void setCartasS(carta cartasS) {
        this.cartasS = cartasS;
    }

    public carta getCartasS() {
        return cartasS;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }


    public int getDraws() {
        return draws;
    }

    public mano (ArrayList<carta> cartas) {
        this.cartas = cartas;
        this.besthand=Ranking.HIGHCARD;
        this.cartasG="";
        this.cartasS=null;
        this.draws=-1;


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
        for(int i = 0; i < cartas.size() ;++i)
            aux += cartas.get(i).toString();
        return aux;
    }


}