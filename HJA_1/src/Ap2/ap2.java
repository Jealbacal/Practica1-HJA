package Ap2;

import java.io.*;

import Utils.*;

public class ap2 {

	public String in;
	public String out;
	
	public ap2(String in, String out) {
		this.in = in;
		this.out = out;
		
		try {
			BufferedReader bff = new BufferedReader(new FileReader(new File(this.in)));
			String act;
			while ((act = bff.readLine()) != null) { //Bucle principal
			//
			//	ToDo  //	leer la mano y evaluar
			//
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void leerMano() {
		
		//
		//	ToDo
		//
		
	}
	
	public void evalua() {
		
		//
		//	ToDo
		//
		
	}
}
