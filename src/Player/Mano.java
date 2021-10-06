package Player;

import java.util.ArrayList;


public class Mano {

    private ArrayList<Carta> cartas;
    private Ranking besthand;
    private ArrayList<Carta>cartasG;
    private int drawF;
    private int drawS;
    private int pareja;
    private int trio;


    public Ranking getBesthand() {
        return besthand;
    }

    public ArrayList<Carta>getCartasG() {
        return cartasG;
    }



    public void setBesthand(Ranking besthand) {
        this.besthand = besthand;
    }

    public void setCartasG(ArrayList<Carta>cartasG) {
        this.cartasG = cartasG;
    }


    public void setDrawF(int drawF) {
        this.drawF = drawF;
    }

    public int getDrawF() {
        return drawF;
    }

    public void setDrawS(int draws) {
        this.drawS = draws;
    }

    public int getDrawS() {
        return drawS;
    }

    public int getPareja(){return pareja;}

    public void setPareja(int par){this.pareja=par;}

    public int getTrio(){return trio;}

    public void setTrio(int trio){this.trio=trio;}


    public Mano(ArrayList<Carta> cartas) {
        this.cartas = cartas;
        this.besthand=Ranking.HIGHCARD;
        this.cartasG=null;
        this.drawS=-1;
        this.drawF=-1;



    }

    public Mano() {
        this.cartas = new ArrayList<>();
        this.besthand=Ranking.HIGHCARD;
        this.cartasG=null;
        this.drawS=-1;
        this.drawF=-1;
    }


    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public void addCarta(Carta carta) {
        cartas.add(carta);
    }


    @Override
    public String toString() {
        String aux = new String();
        for(int i = 0; i < cartas.size() ;++i)
            aux += cartas.get(i).toString();
        return aux;
    }

    public String toStringCartasG() {
        String aux = new String();
        for(int i = 0; i < cartasG.size() ;++i)
            aux += cartasG.get(i).toString();
        return aux;
    }

    public Mano compareMano(Mano x){
    //
        if(x.getBesthand()==Ranking.HIGHCARD){

          return  this.compareOnebyOne(x);

        }

        else if(x.getBesthand()==Ranking.PAIR){
            if(this.getPareja()>x.getPareja()){
                return this;
            }
            else if (this.getPareja()==x.getPareja()){

                return  this.compareOnebyOne(x);

            }
            else
                return x;
        }

        else if(x.getBesthand()==Ranking.TWOPAIR){
            if(this.cartasG.get(3).getValor()>x.getCartasG().get(3).getValor())
                return this;

            else if (this.cartasG.get(3).getValor()==x.getCartasG().get(3).getValor()){

                if(this.cartasG.get(1).getValor()>x.getCartasG().get(1).getValor())
                    return this;

                else if(this.cartasG.get(1).getValor()==x.getCartasG().get(1).getValor()){

                    return  compareOnebyOne(x);
                }

                else{
                    return x;
                }

            }
            else
                return x;
        }

        else if(x.getBesthand()==Ranking.THREEOFAKIND) {
            if(this.getTrio()>x.getTrio()){
                return this;
            }
            else if (this.getTrio()==x.getTrio()){

                return  this.compareOnebyOne(x);


            }
            else return x;
        }

        else if(x.getBesthand()==Ranking.STRAIGHT){

            if(this.cartas.get(4).getValor()>x.getCartas().get(4).getValor())
                return this;

            else
                return x;
        }

        else if(x.getBesthand()==Ranking.FLUSH){

            return  this.compareOnebyOne(x);
        }


        else if(x.getBesthand()==Ranking.FULLHOUSE){
            //miro trio
            if(this.getTrio()>x.getTrio()){
                return this;
            }
            else{
                // si trios iguales miro parejas
                if(this.getTrio()==x.getTrio()){
                    if(this.getPareja()>x.getPareja()){
                        return this;
                    }
                    else{
                        return x;
                    }
                }
                else{
                    return x;
                }
            }


        }

        else if(x.getBesthand()==Ranking.FOUROFAKIND){
            if(this.cartasG.get(3).getValor()>x.getCartasG().get(3).getValor()){
                return this;
            }
            else if (this.cartasG.get(3).getValor()==x.getCartasG().get(3).getValor()){

                return compareOnebyOne(x);

            }
            else return x;
        }

        else if(x.getBesthand()==Ranking.STRAIGHTFLUSH){

            if(this.cartas.get(4).getValor()>x.getCartas().get(4).getValor()){
                return this;
            }
            else{
                return x;
            }
        }


        return x;
    }

    //asquerosidad si se encuentra una mejor manera pls
    private Mano compareOnebyOne(Mano x){
        int i= x.getCartas().size()-1;
        boolean manoT=false,manoX=false;
        while(i >= 0 && !manoT && !manoX){

            if(this.getCartas().get(i).getValor() > x.getCartas().get(i).getValor())
                manoT=true;

            else if (this.getCartas().get(i).getValor() < x.getCartas().get(i).getValor())
                manoX=true;

            else
                 i--;
        }

        if(manoT)
            return this;

        else if(manoX)
            return x;

        else
            return  this;

    }



}