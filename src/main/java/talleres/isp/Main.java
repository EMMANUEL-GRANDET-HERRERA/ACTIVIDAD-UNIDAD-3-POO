package talleres.isp;

interface Cocinero {
    void cocinar();
}

interface Camarero {
    void atenderMesas();
}

interface PersonalLimpieza {
    void limpiar();
}

interface EnvioEmail {
    void enviarEmail(String destinatario, String mensaje);
}

interface EnvioSMS {
    void enviarSMS(String numero, String mensaje);
}

interface EmpleadoGeneral {
    void cocinar();
    void limpiar();
    void atenderMesas();
}

interface NotificacionGeneral {
    void enviarEmail(String destinatario, String mensaje);
    void enviarSMS(String numero, String mensaje);
}

interface Reparacion {
    void reparar();
}

interface Limpieza {
    void limpiarArea();
}

interface Transferencia {
    void transferir(double monto);
}

interface Retiro {
    void retirar(double monto);
}

interface PagoFactura {
    void pagarFactura(double monto);
}

interface Conducible {
    void conducir();
}

interface TransporteMercancia {
    void cargarMercancia(String mercancia);
}

public class Main {
    static class CocineroEmpleado implements Cocinero {
        public void cocinar() {
            System.out.println("El cocinero está preparando la comida.");
        }
    }

    static class CamareroEmpleado implements Camarero {
        public void atenderMesas() {
            System.out.println("El camarero está atendiendo las mesas.");
        }
    }

    static class ServicioEmail implements EnvioEmail {
        public void enviarEmail(String destinatario, String mensaje) {
            System.out.println("Email para " + destinatario + ": " + mensaje);
        }
    }

    static class ServicioSMS implements EnvioSMS {
        public void enviarSMS(String numero, String mensaje) {
            System.out.println("SMS para " + numero + ": " + mensaje);
        }
    }

    static class CocineroForzado implements EmpleadoGeneral {
        public void cocinar() {
            System.out.println("El cocinero está cocinando.");
        }

        public void limpiar() {
            System.out.println("No realiza tareas de limpieza.");
        }

        public void atenderMesas() {
            System.out.println("No atiende mesas.");
        }
    }

    static class ServicioEmailForzado implements NotificacionGeneral {
        public void enviarEmail(String destinatario, String mensaje) {
            System.out.println("Email para " + destinatario + ": " + mensaje);
        }

        public void enviarSMS(String numero, String mensaje) {
            System.out.println("Este servicio no envía SMS.");
        }
    }

    static class Tecnico implements Reparacion {
        public void reparar() {
            System.out.println("El técnico realizó una reparación.");
        }
    }

    static class AuxiliarLimpieza implements Limpieza {
        public void limpiarArea() {
            System.out.println("El auxiliar limpió el área.");
        }
    }

    static class CuentaBancaria implements Transferencia, Retiro, PagoFactura {
        public void transferir(double monto) {
            System.out.println("Transferencia realizada: $" + monto);
        }

        public void retirar(double monto) {
            System.out.println("Retiro realizado: $" + monto);
        }

        public void pagarFactura(double monto) {
            System.out.println("Factura pagada: $" + monto);
        }
    }

    static class Automovil implements Conducible {
        public void conducir() {
            System.out.println("El automóvil está en movimiento.");
        }
    }

    static class Camion implements Conducible, TransporteMercancia {
        public void conducir() {
            System.out.println("El camión está en movimiento.");
        }

        public void cargarMercancia(String mercancia) {
            System.out.println("Mercancía transportada: " + mercancia);
        }
    }

    public static void main(String[] args) {
        System.out.println("EJEMPLOS DEL ISP");
        new CocineroEmpleado().cocinar();
        new CamareroEmpleado().atenderMesas();
        new ServicioEmail().enviarEmail("cliente@correo.com", "Su pedido está listo.");
        new ServicioSMS().enviarSMS("3001234567", "Su pedido está listo.");

        System.out.println("\nVIOLACIONES DEL ISP");
        new CocineroForzado().limpiar();
        new ServicioEmailForzado().enviarSMS("3001234567", "Mensaje");

        System.out.println("\nEJERCICIO 1: MANTENIMIENTO");
        new Tecnico().reparar();
        new AuxiliarLimpieza().limpiarArea();

        System.out.println("\nEJERCICIO 2: OPERACIONES BANCARIAS");
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.transferir(150000);
        cuenta.retirar(50000);
        cuenta.pagarFactura(80000);

        System.out.println("\nEJERCICIO 3: VEHÍCULOS");
        new Automovil().conducir();
        Camion camion = new Camion();
        camion.conducir();
        camion.cargarMercancia("Cajas");
    }
}
