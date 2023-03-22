package ricartSockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class RicartAgrawalaServer {
    private static final int PUERTO = 12345;
    private static ArrayList<Proceso> lista = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            int numProcesos = 10;
            for (int i = 0; i < numProcesos; i++) {
                Socket socket = servidor.accept();
                new Thread(new ProcesoHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ProcesoHandler implements Runnable {
        private Socket socket;

        public ProcesoHandler(Socket socket) {
            this.socket = socket;
        }


        @Override
        public void run() {
            try (ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream())) {

                Proceso proceso = (Proceso) entrada.readObject();
                lista.add(proceso);
                System.out.println("Proceso " + proceso.getNombre() + " conectado.");

                while (true) {
                    // Esperar solicitud de acceso a sección crítica
                    entrada.readObject();
                    System.out.println("Proceso " + proceso.getNombre() + " solicitando acceso a la sección crítica.");

                    // Aplicar algoritmo de Ricart y Agrawala
                    boolean tieneAcceso = true;
                    for (Proceso otroProceso : lista) {
                        if (otroProceso.getNum() != proceso.getNum()) {
                            salida.writeObject(otroProceso);
                            Proceso respuesta = (Proceso) entrada.readObject();
                            if (respuesta.getHora().compareTo(proceso.getHora()) > 0
                                    || (respuesta.getHora().equals(proceso.getHora())
                                    && respuesta.getNum() > proceso.getNum())) {
                                tieneAcceso = false;
                                break;
                            }
                        }
                    }

                    // Responder con "OK" o "WAIT"
                    if (tieneAcceso) {
                        System.out.println("Proceso " + proceso.getNombre() + " tiene acceso a la sección crítica.");
                        salida.writeObject("OK");
                    } else {
                        System.out.println("Proceso " + proceso.getNombre() + " debe esperar para acceder a la sección crítica.");
                        salida.writeObject("WAIT");
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
            }
        }
    }
}