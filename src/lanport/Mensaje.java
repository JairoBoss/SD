package lanport;

public class Mensaje {

    private int id;
    private int idEmisor;
    private int tiempoEmisor;
    private String nombreEmisor;
    private int idReceptor;
    private String mensaje;
    private boolean respondido = false;
    private boolean recibido = false;

    public Mensaje(int id, int senderId, int senderTime, String senderName, 
            int receiverId, String message, boolean answered, boolean received) {
        this.id = id;
        this.idEmisor = senderId;
        this.tiempoEmisor = senderTime;
        this.nombreEmisor = senderName;
        this.idReceptor = receiverId;
        this.mensaje = message;
        this.respondido = answered;
        this.recibido = received;
    }

    public int getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(int idEmisor) {
        this.idEmisor = idEmisor;
    }

    public int getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(int idReceptor) {
        this.idReceptor = idReceptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isRespondido() {
        return respondido;
    }

    public void setRespondido(boolean respondido) {
        this.respondido = respondido;
    }

    public boolean isRecibido() {
        return recibido;
    }

    public void setRecibido(boolean recibido) {
        this.recibido = recibido;
    }

    public int getTiempoEmisor() {
        return tiempoEmisor;
    }

    public void setTiempoEmisor(int tiempoEmisor) {
        this.tiempoEmisor = tiempoEmisor;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
