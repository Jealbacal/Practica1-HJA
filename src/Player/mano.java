package Player;

import java.util.ArrayList;


public class mano  {

    public ArrayList<carta> cartas;
    public Ranking besthand;
    public ArrayList<carta>cartasG;
    public carta cartasS;
    public int drawF;
    public int drawS;


    public Ranking getBesthand() {
        return besthand;
    }

    public ArrayList<carta>getCartasG() {
        return cartasG;
    }



    public void setBesthand(Ranking besthand) {
        this.besthand = besthand;
    }

    public void setCartasG(ArrayList<carta>cartasG) {
        this.cartasG = cartasG;
    }


    public void setCartasS(carta cartasS) {
        this.cartasS = cartasS;
    }

    public carta getCartasS() {
        return cartasS;
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

    public mano (ArrayList<carta> cartas) {
        this.cartas = cartas;
        this.besthand=Ranking.HIGHCARD;
        this.cartasG=null;
        this.cartasS=null;
        this.drawS=-1;
        this.drawF=-1;



    }

    public mano () {
        this.cartas = new ArrayList<>();
        this.besthand=Ranking.HIGHCARD;
        this.cartasG=null;
        this.cartasS=null;
        this.drawS=-1;
        this.drawF=-1;
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

    public mano compareMano(mano x){
    //
        if(x.getBesthand()==Ranking.HIGHCARD){

          return  this.compareOnebyOne(x);

        }

        else if(x.getBesthand()==Ranking.PAIR){
            if(this.cartasG.get(1).getValor()>x.getCartasG().get(1).getValor()){
                return this;
            }
            else if (this.cartasG.get(1).getValor()==x.getCartasG().get(1).getValor()){
                if(this.cartasS.getValor()>x.getCartasS().getValor())
                    return this;

                else
                    return x;

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

                    if(this.cartasS.getValor()>x.getCartasS().getValor())
                        return this;

                    else
                        return x;
                }

                else{
                    return x;
                }

            }
            else
                return x;
        }

        else if(x.getBesthand()==Ranking.THREEOFAKIND) {
            if(this.cartasG.get(2).getValor()>x.getCartasG().get(2).getValor()){
                return this;
            }
            else if (this.cartasG.get(2).getValor()==x.getCartasG().get(2).getValor()){

                if(this.cartasS.getValor()>x.getCartasS().getValor())
                    return this;

                else return x;

            }
            else return x;
        }

        else if(x.getBesthand()==Ranking.STRAIGHT){

            if(this.cartasG.get(4).getValor()>x.getCartasG().get(4).getValor())
                return this;

            else
                return x;
        }

        else if(x.getBesthand()==Ranking.FLUSH){

            return  this.compareOnebyOne(x);
        }

        //asquerosidad si se consigue una mejor manera pls
        else if(x.getBesthand()==Ranking.FULLHOUSE){

            //trio primero
            if(this.cartasG.get(0).getValor()==this.cartasG.get(2).getValor()){
                //trio 2 primero
                if(x.cartasG.get(0).getValor() == x.cartasG.get(2).getValor()){
                    //comparo ambos trios
                    if(this.cartasG.get(0).getValor()>x.cartasG.get(0).getValor()){
                        return this;
                    }
                    else{
                        //si trios iguales
                        if(this.cartasG.get(0).getValor()==x.cartasG.get(0).getValor()){
                            //pareja mayor nuestra
                            if(this.cartasG.get(3).getValor()>x.cartasG.get(3).getValor()){
                                return this;
                            }
                            else{
                                //parejas iguales
                                if(this.cartasG.get(3).getValor()==x.cartasG.get(3).getValor()){
                                    return this;
                                }
                                else{
                                    return x;
                                }

                            }
                        }
                        else{
                            return x;
                        }


                    }
                }

            }//pareja primero
            else{
                //pareja 2 primero
                //if(x.cartasG.get(0).getValor() != x.cartasG.get(2).getValor()){

                //}

            }
        }

        else if(x.getBesthand()==Ranking.FOUROFAKIND){
            if(this.cartasG.get(3).getValor()>x.getCartasG().get(3).getValor()){
                return this;
            }
            else if (this.cartasG.get(3).getValor()==x.getCartasG().get(3).getValor()){

                if(this.cartasS.getValor()>x.getCartasS().getValor())
                    return this;

                else return x;

            }
            else return x;
        }

        else if(x.getBesthand()==Ranking.STRAIGHTFLUSH){

            if(this.cartasG.get(4).getValor()>x.getCartasG().get(4).getValor()){
                return this;
            }
            else{
                return x;
            }
        }


        return x;
    }

    //asquerosidad si se encuentra una mejor manera pls
    private mano compareOnebyOne(mano x){
        if(this.cartasS.getValor()>x.getCartasS().getValor()){
            return this;
        }
        else if(this.cartasS.getValor()==x.getCartasS().getValor()){

            if(this.cartas.get(3).getValor()>x.cartas.get(3).getValor()){
                return this;
            }
            else if (this.cartas.get(3).getValor()==x.cartas.get(3).getValor()){

                if(this.cartas.get(2).getValor()>x.cartas.get(2).getValor()){
                    return this;
                }

                else if(this.cartas.get(2).getValor()==x.cartas.get(2).getValor()){

                    if(this.cartas.get(1).getValor()>x.cartas.get(1).getValor()){
                        return this;
                    }

                    else if(this.cartas.get(1).getValor()==x.cartas.get(1).getValor()){

                        if(this.cartas.get(0).getValor()>x.cartas.get(0).getValor())
                            return this;

                        else
                            return x;
                    }

                    else
                        return x;


                }

                else
                    return x;

            }
            else
                return x;

        }
        else
            return x;
    }

}