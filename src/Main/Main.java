package Main;

import java.util.*;

import Ap1.ap1;
import Ap2.ap2;
import Ap3.ap3;
import Ap4.ap4;

public class Main {

	
	public static void main ( String [] args) {
		String prc, in, out;
		prc = new String(args[0]);
		in = new String(args[1]);
		out = new String(args[2]);
		
		switch(prc) {
		case "1":
			ap1 a1 = new ap1(in, out);
			break;
		case "2":
			ap2 a2 = new ap2(in, out);
			break;
		case "3":
			ap3 a3 = new ap3(in, out);
			break;

		case "4":
			ap4 a4 = new ap4(in, out);
			break;
		default:
			//error
		}
	}
}
