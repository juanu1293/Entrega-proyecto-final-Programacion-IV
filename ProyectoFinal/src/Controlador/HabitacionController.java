package Controlador;

import Modelo.Habitacion;
import Modelo.Reserva;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HabitacionController {
    private static List<Habitacion> habitaciones = new ArrayList<>();

    public static void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    public static List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public static void actualizarHabitacion(Habitacion habitacion) {
        
    }

    public static void eliminarHabitacion(Habitacion habitacion) {
        habitaciones.remove(habitacion);
    }

    public static List<Habitacion> verificarDisponibilidad(Date fechaInicio, Date fechaFin) {
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.isDisponible()) {
                boolean disponible = true;
                for (Reserva reserva : habitacion.getReservas()) {
                    if (reserva.intersectaCon(fechaInicio, fechaFin)) {
                        disponible = false;
                        break;
                    }
                }
                if (disponible) {
                    habitacionesDisponibles.add(habitacion);
                }
            }
        }
        return habitacionesDisponibles;
    }
    
    public static List<Habitacion> buscarHabitacionesDisponibles(Date fechaEntrada, Date fechaSalida, int numHuespedes, String tipoHabitacion) {
        List<Habitacion> disponibles = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo().equalsIgnoreCase(tipoHabitacion) && habitacion.getCapacidad() >= numHuespedes && habitacion.esDisponible(fechaEntrada, fechaSalida)) {
                disponibles.add(habitacion);
            }
        }
        return disponibles;
    }
    
    public static boolean reservarHabitacion(Habitacion habitacion, Reserva reserva) {
        if (habitacion.isDisponible()) {
            habitacion.agregarReserva(reserva);
            habitacion.setDisponible(false);
            return true;
        }
        return false;
    }
    public static List<Reserva> getReservas() {
        List<Reserva> todasLasReservas = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            todasLasReservas.addAll(habitacion.getReservas());
        }
        return todasLasReservas;
    }
}