package tokenRing;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TokenServer {

	public static void main(String agrs[]) throws Exception {
		//creacion de nuestro server con su respectivo puerto
		System.out.println("Server iniciando...");
		while (true) {
			Server sr = new Server();
			sr.recPort(8000);
			sr.recData();
		}
	}
}

// server
class Server {
	boolean hasToken = false;
	boolean sendData = false;
	int recport;

	//puerto
	void recPort(int recport) {
		this.recport = recport;
	}

	void recData() throws Exception {
		byte buff[] = new byte[256];
		//conector para recibir y mostrar la data
		DatagramSocket ds;
		// datragrama del paquete que se envia por el puerto establecido
		DatagramPacket dp;
		//mensaje
		String str;
		ds = new DatagramSocket(recport);
		dp = new DatagramPacket(buff, buff.length);
		ds.receive(dp);
		ds.close();
		str = new String(dp.getData(), 0, dp.getLength());
		System.out.println("El mensaje de " + str);
	}

}
