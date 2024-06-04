package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Habitacion {
    private String tipo;
    private int capacidad;
    private double precio;
    private String comodidades;
    private boolean disponible;
    private List<Reserva> reservas;

    public Habitacion(String tipo, int capacidad, double precio, String comodidades) {
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precio = precio;
        this.comodidades = comodidades;
        this.disponible = true;
        this.reservas = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getComodidades() {
        return comodidades;
    }

    public void setComodidades(String comodidades) {
        this.comodidades = comodidades;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }	

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
        boolean stillReserved = false;
        for (Reserva r : reservas) {
            if (r.getFechaEntrada().before(reserva.getFechaSalida()) && r.getFechaSalida().after(reserva.getFechaEntrada())) {
                stillReserved = true;
                break;
            }
        }
        if (!stillReserved) {
            setDisponible(true);
        }
    }
    
    @Override
    public String toString() {
        return tipo + " - Capacidad: " + capacidad + " - Precio: " + precio;
    }
}