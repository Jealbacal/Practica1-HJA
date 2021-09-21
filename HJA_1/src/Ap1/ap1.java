package Ap1;

import java.io.*;

import Utils.*;

public class ap1 {

	public String in;
	public String out;
	
	public ap1(String in, String out) {
		this.in = in;
		this.out = out;
		
		try {
			BufferedReader bff = new BufferedReader(new FileReader(new File(this.in)));
			String act;
			while ((act = bff.readLine()) != null) { //Bucle principal
				mano mano = leerMano(act);
				evalua(mano);
			}
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public mano leerMano(String act) {
		mano mano = new mano();
		for (int i = 0; i < act.length(); i +=2) {
			carta c = new carta(act.substring(i,i+1), act.substring(i+1,i+2));
			mano.addCarta(c);
		}
		return mano;
	}
	
	public void evalua(mano mano) {
		
		//
		//	ToDo
		//
		
	}
	
	
}
