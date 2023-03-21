package tokenRing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TokenClient12 {
	InetAddress lclhost;
	int sendport, recport;
	boolean hasToken = true;
	boolean setSendData = false;
	TokenClient12 tkcl, tkser;

	TokenClient12(InetAddress lclhost) {
		this.lclhost = lclhost;
	}

	void setSendPort(int sendport) {
		this.sendport = sendport;
	}

	void setRecPort(int recport) {
		this.recport = recport;
	}

	void sendData() throws Exception {
		BufferedReader br;
		String str = "Token";
		DatagramSocket ds;
		DatagramPacket dp;

		if (setSendData == true) {
			System.out.println("enviando ");
			System.out.println("Ingrese el mensaje");
			br = new BufferedReader(new InputStreamReader(System.in));
			str = "ClienteUno....." + br.readLine();
			System.out.println("enviado ahora");
		}
		ds = new DatagramSocket(sendport);
		dp = new DatagramPacket(str.getBytes(), str.length(), lclhost, sendport - 1000);
		ds.send(dp);
		ds.close();
		setSendData = false;
		hasToken = false;
	}

	void recData() throws Exception {
		String msgstr;
		byte buffer[] = new byte[256];
		DatagramSocket ds;
		DatagramPacket dp;
		ds = new DatagramSocket(recport);
		dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		ds.close();
		msgstr = new String(dp.getData(), 0, dp.getLength());
		System.out.println("2 " + msgstr);

		if (msgstr.equals("Token")) {
			hasToken = true;
		}
	}

}
