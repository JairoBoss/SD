package berkeley.monitor;

import berkeley.cliente.Client;
import berkeley.server.Server;

public class Simulation
{
    public static void main(String args[]){
        SimulatorMonitor sm = new SimulatorMonitor();
        // Crear un hilo servidor e iniciarlo //
        Server srv = new Server(sm);
        srv.start();
        Client clv[] = new Client[3];
        // Crear hilos clientes e iniciarlos //
        for(int i=0;i<3;i++){
            clv[i] = new Client(i,sm);
            clv[i].start();
        }
            
    }
}
