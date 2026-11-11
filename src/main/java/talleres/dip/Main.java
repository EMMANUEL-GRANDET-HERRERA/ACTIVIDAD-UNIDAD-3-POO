package talleres.dip;

import java.util.HashMap;
import java.util.Map;

interface MetodoPago {
    void procesarPago(double monto);
}

interface ServicioNotificacion {
    void enviarMensaje(String destinatario, String mensaje);
}

interface ServicioAutenticacion {
    boolean autenticar(String usuario, String clave);
}

interface Almacenamiento {
    void guardar(String nombre, String contenido);
    String recuperar(String nombre);
}

interface GeneradorReporte {
    void generar(String contenido);
}

public class Main {
    static class TarjetaCredito implements MetodoPago {
        public void procesarPago(double monto) {
            System.out.println("Pago con tarjeta de crédito: $" + monto);
        }
    }

    static class PayPal implements MetodoPago {
        public void procesarPago(double monto) {
            System.out.println("Pago con PayPal: $" + monto);
        }
    }

    static class ProcesadorPago {
        private final MetodoPago metodoPago;

        ProcesadorPago(MetodoPago metodoPago) {
            this.metodoPago = metodoPago;
        }

        void realizarPago(double monto) {
            metodoPago.procesarPago(monto);
        }
    }

    static class EmailService implements ServicioNotificacion {
        public void enviarMensaje(String destinatario, String mensaje) {
            System.out.println("Email para " + destinatario + ": " + mensaje);
        }
    }

    static class SMSService implements ServicioNotificacion {
        public void enviarMensaje(String destinatario, String mensaje) {
            System.out.println("SMS para " + destinatario + ": " + mensaje);
        }
    }

    static class Notificador {
        private final ServicioNotificacion servicio;

        Notificador(ServicioNotificacion servicio) {
            this.servicio = servicio;
        }

        void notificar(String destinatario, String mensaje) {
            servicio.enviarMensaje(destinatario, mensaje);
        }
    }

    static class ProcesadorPagoIncorrecto {
        private final TarjetaCredito tarjeta;

        ProcesadorPagoIncorrecto(TarjetaCredito tarjeta) {
            this.tarjeta = tarjeta;
        }

        void realizarPago(double monto) {
            tarjeta.procesarPago(monto);
        }
    }

    static class NotificadorIncorrecto {
        private final EmailService email;

        NotificadorIncorrecto(EmailService email) {
            this.email = email;
        }

        void notificar(String destinatario, String mensaje) {
            email.enviarMensaje(destinatario, mensaje);
        }
    }

    static class AutenticacionLocal implements ServicioAutenticacion {
        public boolean autenticar(String usuario, String clave) {
            return usuario.equals("emmanuel") && clave.equals("1234");
        }
    }

    static class AutenticacionOAuth implements ServicioAutenticacion {
        public boolean autenticar(String usuario, String clave) {
            return !usuario.isBlank() && !clave.isBlank();
        }
    }

    static class GestorAutenticacion {
        private final ServicioAutenticacion servicio;

        GestorAutenticacion(ServicioAutenticacion servicio) {
            this.servicio = servicio;
        }

        void iniciarSesion(String usuario, String clave) {
            System.out.println(servicio.autenticar(usuario, clave)
                    ? "Autenticación correcta."
                    : "Datos incorrectos.");
        }
    }

    static class AlmacenamientoLocal implements Almacenamiento {
        private final Map<String, String> archivos = new HashMap<>();

        public void guardar(String nombre, String contenido) {
            archivos.put(nombre, contenido);
            System.out.println("Archivo guardado localmente: " + nombre);
        }

        public String recuperar(String nombre) {
            return archivos.get(nombre);
        }
    }

    static class AlmacenamientoNube implements Almacenamiento {
        private final Map<String, String> archivos = new HashMap<>();

        public void guardar(String nombre, String contenido) {
            archivos.put(nombre, contenido);
            System.out.println("Archivo guardado en la nube: " + nombre);
        }

        public String recuperar(String nombre) {
            return archivos.get(nombre);
        }
    }

    static class GestorArchivos {
        private final Almacenamiento almacenamiento;

        GestorArchivos(Almacenamiento almacenamiento) {
            this.almacenamiento = almacenamiento;
        }

        void guardarArchivo(String nombre, String contenido) {
            almacenamiento.guardar(nombre, contenido);
        }

        void mostrarArchivo(String nombre) {
            System.out.println("Contenido: " + almacenamiento.recuperar(nombre));
        }
    }

    static class ReportePDF implements GeneradorReporte {
        public void generar(String contenido) {
            System.out.println("Reporte PDF: " + contenido);
        }
    }

    static class ReporteExcel implements GeneradorReporte {
        public void generar(String contenido) {
            System.out.println("Reporte Excel: " + contenido);
        }
    }

    static class GestorReportes {
        private final GeneradorReporte generador;

        GestorReportes(GeneradorReporte generador) {
            this.generador = generador;
        }

        void crearReporte(String contenido) {
            generador.generar(contenido);
        }
    }

    public static void main(String[] args) {
        System.out.println("EJEMPLO 1: MÉTODOS DE PAGO");
        new ProcesadorPago(new TarjetaCredito()).realizarPago(100000);
        new ProcesadorPago(new PayPal()).realizarPago(200000);

        System.out.println("\nEJEMPLO 2: NOTIFICACIONES");
        new Notificador(new EmailService()).notificar("cliente@correo.com", "Hola.");
        new Notificador(new SMSService()).notificar("3001234567", "Hola.");

        System.out.println("\nVIOLACIONES DEL DIP");
        new ProcesadorPagoIncorrecto(new TarjetaCredito()).realizarPago(50000);
        new NotificadorIncorrecto(new EmailService()).notificar("cliente@correo.com", "Mensaje.");

        System.out.println("\nEJERCICIO 1: AUTENTICACIÓN");
        new GestorAutenticacion(new AutenticacionLocal()).iniciarSesion("emmanuel", "1234");
        new GestorAutenticacion(new AutenticacionOAuth()).iniciarSesion("usuario", "clave");

        System.out.println("\nEJERCICIO 2: ALMACENAMIENTO");
        GestorArchivos gestorLocal = new GestorArchivos(new AlmacenamientoLocal());
        gestorLocal.guardarArchivo("trabajo.txt", "Actividad de POO");
        gestorLocal.mostrarArchivo("trabajo.txt");
        GestorArchivos gestorNube = new GestorArchivos(new AlmacenamientoNube());
        gestorNube.guardarArchivo("trabajo.txt", "Actividad de POO");
        gestorNube.mostrarArchivo("trabajo.txt");

        System.out.println("\nEJERCICIO 3: REPORTES");
        new GestorReportes(new ReportePDF()).crearReporte("Resultados del sistema");
        new GestorReportes(new ReporteExcel()).crearReporte("Resultados del sistema");
    }
}
