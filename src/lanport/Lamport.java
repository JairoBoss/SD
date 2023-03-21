package lanport;

import java.util.ArrayList;

public class Lamport {
    
    public static void main(String[] args) {
        Lamport la = new Lamport();
        la.ejecutarAlgoritmo();
    }

    public void ejecutarAlgoritmo() {
        
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        int numeroDeClientes = 6;
        int intervaloSleep = 2;
        for (int i = 0; i < numeroDeClientes; i++) {
            Cliente c = new Cliente(listaClientes, i, "cliente " + (i+1), (i + 1), 1);
            listaClientes.add(c);
            intervaloSleep--;
        }

        for (Cliente client : listaClientes) {
            client.start();
        }
    }
}
