package Modelo;

import java.util.Date;

public class Reserva {
    private Date fechaEntrada;
    private Date fechaSalida;
    private String nombre;
    private int numHuespedes;

    public Reserva(Date fechaEntrada, Date fechaSalida, String nombre, int numHuespedes) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.nombre = nombre;
        this.numHuespedes = numHuespedes;
    }

    public boolean intersectaCon(Date otraFechaEntrada, Date otraFechaSalida) {
        return (otraFechaEntrada.before(fechaSalida) && otraFechaSalida.after(fechaEntrada));
    }

    // Getters and Setters for the new fields
    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumHuespedes() {
        return numHuespedes;
    }

    public void setNumHuespedes(int numHuespedes) {
        this.numHuespedes = numHuespedes;
    }
    @Override
    public String toString() {
        return "Reserva para " + nombre + " del " + fechaEntrada + " al " + fechaSalida + " para " + numHuespedes + " huéspedes";
    }
}