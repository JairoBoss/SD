package lanport;

import java.util.ArrayList;

public class Cliente extends Thread {

    private ArrayList<Cliente> clientes;
    private ArrayList<Mensaje> mensajesRecibidos;
    private int rangoSleep = 0;
    private int rangoReloj = 0;
    private int time = 0;
    private int id;
    private String nombre;
    private int[] sendTimes = {3};
    private int idMensajeRecibido = 0;

    public Cliente(ArrayList<Cliente> clients, int id, String name, int clockRange, int sleepRange) {
        this.rangoSleep = sleepRange;
        this.rangoReloj = clockRange;
        this.clientes = clients;
        this.nombre = name;
        this.id = id;
        this.mensajesRecibidos = new ArrayList<Mensaje>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                sleep(this.rangoSleep * 1000);
                this.time += rangoReloj;
//                System.out.println("Hilo " + this.nombre + ", tiempo: " + this.time);

                if (this.isCalendarizado(this.time)) {
                    this.enviarMensaje(this.id + 1, "Hola ");
                }

                for (Mensaje mensaje : mensajesRecibidos) {
                    if (!mensaje.isRecibido()) {
                        this.procesarMensaje(mensaje);
                    }
                    if (!mensaje.isRespondido() && autorizarEnvio()) {
                        this.enviarMensaje(mensaje.getIdEmisor(), "Response");
                        mensaje.setRespondido(true);
                    }
                }

            }
        } catch (Exception e) {
        }
    }

    private boolean autorizarEnvio() {
        int recibido = 0;
        int respondido = 0;

        for (Mensaje mensaje : this.mensajesRecibidos) {
            if (mensaje.isRespondido()) {
                respondido++;
            }
            if (mensaje.isRecibido()) {
                recibido++;
            }
        }

        int diff = Math.abs(recibido - respondido);

        return (diff == 1) ? true : false;
    }

    private boolean isCalendarizado(int tiempo) {
        for (int number : sendTimes) {
            if (tiempo == number) {
                return true;
            }
        }
        return false;
    }

    public void enviarMensaje(int idReceptor, String mensaje) {
        System.out.println("*****");
        System.out.println("Función enviar, datos: ");
        System.out.println("Remitente: " + this.nombre);
        System.out.println("Destinatario: " + this.clientes.get(idReceptor).getClientName());
        this.clientes.get(idReceptor).mensajeRecibidoDesde(this.id, this.nombre, mensaje, this.time);
        System.out.println("*****\n");
    }

    public void mensajeRecibidoDesde(int idEmisor, String nombreEmisor, String mensaje, int tiempoEmisor) {

        if (tiempoEmisor < this.time) {
            System.out.println("----> " + this.nombre);
            System.out.println("Mensaje de " + nombreEmisor + " PROCESADO. Mensaje recibido: " + mensaje);
            System.out.println("Tiempo de envío del mensaje: " + tiempoEmisor);
            System.out.println("Tiempo de esta unidad: " + this.time);
            System.out.println("----");
            this.mensajesRecibidos.add(new Mensaje(this.idMensajeRecibido, idEmisor,
                    tiempoEmisor, nombreEmisor, this.id, mensaje, false, true));
        } else {
            this.mensajesRecibidos.add(new Mensaje(this.idMensajeRecibido, idEmisor,
                    tiempoEmisor, nombreEmisor, this.id, mensaje, false, false));
        }
        this.idMensajeRecibido++;
    }

    public void procesarMensaje(Mensaje mensaje) {
        if (mensaje.getTiempoEmisor() < this.time) {
            System.out.println("--- " + this.nombre);
            System.out.println("Mensaje de " + mensaje.getNombreEmisor()
                    + " PROCESADO. Mensaje recibido: " + mensaje.getMensaje());
            System.out.println("Tiempo de envío del mensaje: " + mensaje.getTiempoEmisor());
            System.out.println("Tiempo de esta unidad: " + this.time);
            System.out.println("---");
            this.mensajesRecibidos.get(mensaje.getId()).setRecibido(true);
        }
    }

    public String getClientName() {
        return this.nombre;
    }
}
