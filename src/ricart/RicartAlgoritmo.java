package ricart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RicartAlgoritmo {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<Proceso> lista = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Algoritmo de Ricart y Agrawala");
        int nProcesos = 3;
        {
            for (int i = 0; i < nProcesos; i++) {
                try {
                    Proceso pr = new Proceso("Proceso" + (i + 1), (i + 1), new Date());
                    Thread.sleep(3*1000);
                    lista.add(pr);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RicartAlgoritmo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("\n\n");
            for (int i = 0; i < nProcesos; i++) {
                System.out.println("Proceso " + i);
                metodo();
                System.out.println("\n\n");
            }
        }
    }

    public static void metodo() {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Nombre del proceso " + lista.get(i).getNombre());
            System.out.println("Numero del proceso " + lista.get(i).getNum());
            Date fecha = lista.get(i).getHora();
            String hora = "hh: mm: ss";
            SimpleDateFormat formato = new SimpleDateFormat(hora);
            System.out.println("Hora del proceso " + formato.format(fecha));
            System.out.println("Proceso " + lista.get(i).getNombre() + " OK!");
            System.out.println("\n");
            
        }
    }

}
