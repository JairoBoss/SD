package ricartSockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Instant;

public class Proceso implements Serializable {
    private String nombre;
    private int num;
    private Instant hora; // agregar campo de tipo Instant

    public Proceso(String nombre, int num, long l) {
        this.nombre = nombre;
        this.num = num;
        this.hora = Instant.now(); // inicializar campo con la hora actual
    }

    public String getNombre() {
        return nombre;
    }

    public int getNum() {
        return num;
    }

    public Instant getHora() { // actualizar método para devolver Instant
        return hora;
    }

    public void setHora(Instant hora) { // actualizar método para recibir Instant
        this.hora = hora;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeLong(hora.toEpochMilli());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.hora = Instant.ofEpochMilli(in.readLong());
    }
}