import ricartSockets.Proceso;
import ricartSockets.Solicitud;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            // Conectar al servidor
//            Socket socket = new Socket("175.1.32.156", 12345);
            Socket socket = new Socket("175.1.61.235", 12345);
            System.out.println("Conectado al servidor");

            // Enviar un objeto de la clase Proceso al servidor
            Proceso proceso = new Proceso("Esteban", 1, System.currentTimeMillis());
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            salida.writeObject(proceso);
            Thread.sleep(7000);
            // Solicitar acceso a sección crítica
            salida.writeObject(new Solicitud());


            // Leer la respuesta del servidor
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            String respuesta = (String) entrada.readObject();
            System.out.println("Respuesta del servidor: " + respuesta);

            // Cerrar la conexión
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}