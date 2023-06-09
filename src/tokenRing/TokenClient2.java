package tokenRing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TokenClient2 {

	static boolean setSendData;
	static boolean hasToken;

	public static void main(String arg[]) throws Exception {

		InetAddress lclhost;
		BufferedReader br;
		String str1;
		TokenClient21 tkcl;
		TokenClient21 ser;

		while (true) {
			lclhost = InetAddress.getLocalHost();
			tkcl = new TokenClient21(lclhost);
			tkcl.setRecPort(8004);
			tkcl.setSendPort(9002);
			lclhost = InetAddress.getLocalHost();
			ser = new TokenClient21(lclhost);
			ser.setSendPort(9000);
			System.out.println("entrando al if");

			if (hasToken == true) {
				System.out.println("�Desea ingresar los datos? -> YES / NO");
				br = new BufferedReader(new InputStreamReader(System.in));
				str1 = br.readLine();
				if (str1.equalsIgnoreCase("yes")) {
					System.out.println("ignorecase");
					ser.setSendData = true;
					ser.sendData();
				} else if (str1.equalsIgnoreCase("no")) {
					tkcl.sendData();
					hasToken = false;
				}
			} else {
				System.out.println("MODO RECEPTOR...");
				tkcl.recData();
				hasToken = true;
			}
		}
	}
}

class TokenClient21 {
	InetAddress lclhost;
	int sendport, recport;
	boolean setSendData = false;
	boolean hasToken = false;
	TokenClient21 tkcl;
	TokenClient21 ser;

	TokenClient21(InetAddress lclhost) {
		this.lclhost = lclhost;
	}

	void setSendPort(int sendport) {
		this.sendport = sendport;
	}

	void setRecPort(int recport) {
		this.recport = recport;
	}

	void sendData() throws Exception {
		System.out.println("case");
		BufferedReader br;
		String str = "Token";
		DatagramSocket ds;
		DatagramPacket dp;

		if (setSendData == true) {
			System.out.println("Escriba el mensaje");
			br = new BufferedReader(new InputStreamReader(System.in));
			str = "Cliente2....." + br.readLine();
		}
		ds = new DatagramSocket(sendport);
		dp = new DatagramPacket(str.getBytes(), str.length(), lclhost, sendport - 1000);
		ds.send(dp);
		ds.close();
		System.out.println("Data enviada");
		setSendData = false;
		hasToken = false;
	}

	void recData() throws Exception {
		String msgstr;
		byte buffer[] = new byte[256];
		DatagramSocket ds;
		DatagramPacket dp;
		ds = new DatagramSocket(recport);
		// ds = new DatagramSocket(4000);
		dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);

		ds.close();
		msgstr = new String(dp.getData(), 0, dp.getLength());
		System.out.println("1 " + msgstr);

		if (msgstr.equals("Token")) {
			hasToken = true;
		}
	}

}
