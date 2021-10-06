package Main;

import Ap1.Ap1;
import Ap2.Ap2;
import Ap3.Ap3;
import Ap4.Ap4;

public class Main {

	
	public static void main ( String [] args) {
		String prc, in, out;
		prc = new String(args[0]);
		in = new String(args[1]);
		out = new String(args[2]);
		if(args.length > 0)
		switch(prc) {
		case "1":
			Ap1 a1 = new Ap1(in, out);
			a1.buclePrincipal();
			break;
		case "2":
			Ap2 a2 = new Ap2(in, out);
			a2.buclePrincipal();
			break;
		case "3":
			Ap3 a3 = new Ap3(in, out);
			a3.buclePrincipal();
			break;

		case "4":
			Ap4 a4 = new Ap4(in, out);
			a4.buclePrincipal();
			break;
		default:
			//error
		}
		else {
			Window_Main GUI = new Window_Main();
			
		}
	}
}
