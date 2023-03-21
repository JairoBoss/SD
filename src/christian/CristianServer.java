package christian;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class CristianServer {

    private static final int PORT = 2020;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            while (true) {
                System.out.println("Servidor iniciado en puerto " + PORT + "\n");
                Socket client = server.accept();
                System.out.println("Cliente conectado... sincronizar relojes");

                //recibir el tiempo de reloj del cliente
                DataInputStream input = new DataInputStream(client.getInputStream());
                Long TCliente = input.readLong();
                double TClienteSeconds = TCliente / 1_000_000_000.0;

                System.out.println("Tiempo recibido del cliente en segundos [" + TClienteSeconds + "]");


                //enviar tiempo del servidor
                OutputStream output = client.getOutputStream();
                DataOutputStream writer = new DataOutputStream(output);
                //simulación de retardo por razones de conexión y tiempo de respuesta
                //por parte del servidor partiendo del supuesto que está en una
                //dirección ip distinta en la red
                Thread.sleep(3000);
                long TServer = System.nanoTime();
                double TServerSeconds = TServer / 1_000_000_000.0;
                writer.writeLong(TServer);
                System.out.println("Tiempo enviado al cliente en segundos [" + TServerSeconds + "]");

                System.out.println("\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
