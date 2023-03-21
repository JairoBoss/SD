package ricart;

import java.util.Date;

public class Proceso {
    String nombre;
    Integer num;
    Date hora;

    public Proceso() {
    }

    public Proceso(String nombre, Integer num, Date hora) {
        this.nombre = nombre;
        this.num = num;
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
    
    
    
}
