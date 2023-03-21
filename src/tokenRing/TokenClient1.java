package tokenRing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class TokenClient1 {
	public static void main(String arg[]) throws Exception {
		InetAddress lclhost;
		BufferedReader br;
		String str = "";
		TokenClient12 tkcl, tkser;
		// boolean hasToken;
		// boolean setSendData;

		while (true) {
			lclhost = InetAddress.getByName("172.16.30.84");
			tkcl = new TokenClient12(lclhost);
			tkser = new TokenClient12(lclhost);
			// tkcl.setSendPort(9001);
			tkcl.setSendPort(9004);
			tkcl.setRecPort(8002);
			lclhost = InetAddress.getLocalHost();
			tkser.setSendPort(9000);
			//verificacion para saber si se tiene acceso al token o lo estan utilizando
			if (tkcl.hasToken == true) {
				System.out.println("¿Desea ingresar los datos? -> YES / NO");
				br = new BufferedReader(new InputStreamReader(System.in));
				str = br.readLine();
				if (str.equalsIgnoreCase("yes")) {
					System.out.println("listo para enviar");
					tkser.setSendData = true;
					tkser.sendData();
					tkser.setSendData = false;
				} else if (str.equalsIgnoreCase("no")) {
					System.out.println("estoy en el else");
					// tkcl.hasToken=false;
					tkcl.sendData();
					tkcl.recData();
					System.out.println("saliendo del else");
				}
			} else {
				System.out.println("MODO RECEPTOR...");
				tkcl.recData();
			}
		}
	}

}
