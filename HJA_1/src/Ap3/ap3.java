package Ap3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ap3 {

	public String in;
	public String out;
	
	public ap3(String in, String out) {
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
