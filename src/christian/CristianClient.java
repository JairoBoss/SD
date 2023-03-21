package christian;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CristianClient {

    private static final int PORT = 2020;

    public static void main(String[] args) {
        try {
//            Socket socket = new Socket("localhost", PORT);

            Socket socket = new Socket("172.16.30.87", PORT);

            // Enviar tiempo al servidor
            OutputStream output = socket.getOutputStream();
            DataOutputStream writer = new DataOutputStream(output);
            long TClient = System.nanoTime();
            writer.writeLong(TClient);

            // Comenzar a cronometrar el tiempo de respuesta
            long TRequest = System.nanoTime();

            // Recibir tiempo del servidor
            InputStream input = socket.getInputStream();
            DataInputStream clockServer = new DataInputStream(input);
            long TServidor = clockServer.readLong();

            // Calcular tiempo de respuesta en segundos
            double tiempoRespuesta = (System.nanoTime() - TRequest) / 1_000_000_000.0;

            // Convertir a minutos
            double tiempoRespuestaMin = tiempoRespuesta / 60.0;

            // Convertir a horas
            double tiempoRespuestaHoras = tiempoRespuesta / 3600.0;

            // Imprimir tiempos en segundos
            System.out.println("Tiempo de reloj recibido del servidor en segundos: " + (TServidor / 1_000_000_000.0));
            System.out.println("Tiempo de respuesta calculado en segundos: " + tiempoRespuesta);
            System.out.println("Tiempo de respuesta calculado en minutos: " + tiempoRespuestaMin);
            System.out.println("Tiempo de respuesta calculado en horas: " + tiempoRespuestaHoras);

            // Calcular el nuevo tiempo para el cliente en segundos
            long TnewClient = TServidor + (long) (tiempoRespuesta * 500_000_000.0);
            double TnewClientSeconds = TnewClient / 1_000_000_000.0;
            System.out.println("Nuevo tiempo de reloj calculado para el cliente en segundos: " + TnewClientSeconds);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}