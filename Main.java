import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Doctor> doctores = new ArrayList<>();
    static ArrayList<Paciente> pacientes = new ArrayList<>();
    static ArrayList<Cita> citas = new ArrayList<>();

    // Credenciales del administrador
    static final String USUARIO = "admin";
    static final String PASSWORD = "1234";

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" SISTEMA DE ADMINISTRACION DE CITAS");
        System.out.println("======================================");

        if (iniciarSesion()) {
            mostrarMenu();
        }
    }

    public static boolean iniciarSesion() {

        while (true) {
            System.out.println("\n--- INICIO DE SESION ---");

            System.out.print("Usuario: ");
            String usuario = scanner.nextLine();

            System.out.print("Contrasena: ");
            String password = scanner.nextLine();

            if (usuario.equals(USUARIO) && password.equals(PASSWORD)) {
                System.out.println("\nAcceso correcto.");
                return true;
            }

            System.out.println("\nCredenciales invalidas. Intente nuevamente.");
        }
    }

    public static void mostrarMenu() {

        int opcion = 0;

        while (opcion != 5) {

            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1. Registrar doctor");
            System.out.println("2. Registrar paciente");
            System.out.println("3. Crear cita");
            System.out.println("4. Consultar informacion");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        registrarDoctor();
                        break;

                    case 2:
                        registrarPaciente();
                        break;

                    case 3:
                        crearCita();
                        break;

                    case 4:
                        consultarInformacion();
                        break;

                    case 5:
                        System.out.println("\nSesion finalizada.");
                        System.out.println("Gracias por utilizar el sistema.");
                        break;

                    default:
                        System.out.println("\nOpcion no valida.");
                }

            } catch (Exception e) {
                System.out.println("\nError: ingrese una opcion valida.");
            }
        }
    }

    public static void registrarDoctor() {

        System.out.println("\n--- REGISTRAR DOCTOR ---");

        System.out.print("ID del doctor: ");
        String id = scanner.nextLine();

        if (buscarDoctor(id) != null) {
            System.out.println("Ya existe un doctor con ese ID.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();

        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();

        Doctor doctor = new Doctor(id, nombre, especialidad);
        doctores.add(doctor);

        System.out.println("\nDoctor registrado correctamente.");
    }

    public static void registrarPaciente() {

        System.out.println("\n--- REGISTRAR PACIENTE ---");

        System.out.print("ID del paciente: ");
        String id = scanner.nextLine();

        if (buscarPaciente(id) != null) {
            System.out.println("Ya existe un paciente con ese ID.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();

        Paciente paciente = new Paciente(id, nombre);
        pacientes.add(paciente);

        System.out.println("\nPaciente registrado correctamente.");
    }

    public static void crearCita() {

        System.out.println("\n--- CREAR CITA ---");

        if (doctores.isEmpty() || pacientes.isEmpty()) {
            System.out.println(
                "Debe registrar al menos un doctor y un paciente antes de crear una cita."
            );
            return;
        }

        System.out.print("ID de la cita: ");
        String id = scanner.nextLine();

        System.out.print("Fecha de la cita: ");
        String fecha = scanner.nextLine();

        System.out.print("Hora de la cita: ");
        String hora = scanner.nextLine();

        System.out.print("Motivo de la cita: ");
        String motivo = scanner.nextLine();

        System.out.print("ID del doctor: ");
        String idDoctor = scanner.nextLine();

        Doctor doctor = buscarDoctor(idDoctor);

        if (doctor == null) {
            System.out.println("Doctor no encontrado.");
            return;
        }

        System.out.print("ID del paciente: ");
        String idPaciente = scanner.nextLine();

        Paciente paciente = buscarPaciente(idPaciente);

        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        Cita cita = new Cita(
            id,
            fecha,
            hora,
            motivo,
            doctor,
            paciente
        );

        citas.add(cita);

        System.out.println("\nCita registrada correctamente.");
    }

    public static Doctor buscarDoctor(String id) {

        for (Doctor doctor : doctores) {
            if (doctor.id.equals(id)) {
                return doctor;
            }
        }

        return null;
    }

    public static Paciente buscarPaciente(String id) {

        for (Paciente paciente : pacientes) {
            if (paciente.id.equals(id)) {
                return paciente;
            }
        }

        return null;
    }

    public static void consultarInformacion() {

        System.out.println("\n--- CONSULTAR INFORMACION ---");
        System.out.println("1. Ver doctores");
        System.out.println("2. Ver pacientes");
        System.out.println("3. Ver citas");

        System.out.print("Seleccione una opcion: ");
        String opcion = scanner.nextLine();

        switch (opcion) {

            case "1":

                System.out.println("\n--- DOCTORES ---");

                if (doctores.isEmpty()) {
                    System.out.println("No existen doctores registrados.");
                }

                for (Doctor doctor : doctores) {
                    System.out.println(doctor);
                }

                break;

            case "2":

                System.out.println("\n--- PACIENTES ---");

                if (pacientes.isEmpty()) {
                    System.out.println("No existen pacientes registrados.");
                }

                for (Paciente paciente : pacientes) {
                    System.out.println(paciente);
                }

                break;

            case "3":

                System.out.println("\n--- CITAS ---");

                if (citas.isEmpty()) {
                    System.out.println("No existen citas registradas.");
                }

                for (Cita cita : citas) {
                    System.out.println(cita);
                }

                break;

            default:
                System.out.println("Opcion no valida.");
        }
    }
}


// Clase abstracta Persona
abstract class Persona {

    String id;
    String nombre;

    public Persona(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}


// Clase Doctor
class Doctor extends Persona {

    String especialidad;

    public Doctor(String id, String nombre, String especialidad) {
        super(id, nombre);
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Nombre: " + nombre +
               " | Especialidad: " + especialidad;
    }
}


// Clase Paciente
class Paciente extends Persona {

    public Paciente(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Nombre: " + nombre;
    }
}


// Clase Cita
class Cita {

    String id;
    String fecha;
    String hora;
    String motivo;

    Doctor doctor;
    Paciente paciente;

    public Cita(
        String id,
        String fecha,
        String hora,
        String motivo,
        Doctor doctor,
        Paciente paciente
    ) {

        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    @Override
    public String toString() {

        return "ID Cita: " + id +
               " | Fecha: " + fecha +
               " | Hora: " + hora +
               " | Motivo: " + motivo +
               " | Doctor: " + doctor.nombre +
               " | Paciente: " + paciente.nombre;
    }
}
