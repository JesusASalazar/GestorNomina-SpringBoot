package Laboral;

import Laboral.model.entities.Empleado;
import Laboral.model.entities.Nomina;
import Laboral.model.entities.SueldoBase;
import Laboral.service.EmpleadoService;
import Laboral.service.NominaService;
import Laboral.service.SueldoBaseService;
import Laboral.Util.FileHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LaboralApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboralApplication.class, args);
    }

    // Clase interna para ejecutar el menú de consola
    public static class MenuRunner implements CommandLineRunner {

        private final EmpleadoService empleadoService;
        private final NominaService nominaService;
        private final SueldoBaseService sueldoBaseService;

        public MenuRunner(EmpleadoService empleadoService, NominaService nominaService, SueldoBaseService sueldoBaseService) {
            this.empleadoService = empleadoService;
            this.nominaService = nominaService;
            this.sueldoBaseService = sueldoBaseService;
        }

        @Override
        public void run(String... args) {
            Scanner sc = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n--- MENU DE USUARIO ---");
                System.out.println("1- Mostrar todos los empleados");
                System.out.println("2- Mostrar salario de un empleado por DNI");
                System.out.println("3- Modificar empleados");
                System.out.println("4- Recalcular sueldo de un empleado");
                System.out.println("5- Recalcular sueldo de todos los empleados");
                System.out.println("6- Copia de seguridad de la base de datos");
                System.out.println("0- Salir");
                System.out.print("Selecciona una opción: ");

                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1 -> mostrarTodosEmpleados();
                    case 2 -> mostrarSalarioEmpleado(sc);
                    case 3 -> modificarEmpleados(sc);
                    case 4 -> recalcularSueldoEmpleado(sc);
                    case 5 -> recalcularSueldoTodos();
                    case 6 -> copiaSeguridad();
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida.");
                }

            } while (opcion != 0);

            sc.close();
        }

        private void mostrarTodosEmpleados() {
            List<Empleado> empleados = empleadoService.getAll();
            StringBuilder sb = new StringBuilder();
            for (Empleado e : empleados) {
                sb.append(e.toString()).append("\n");
            }
            System.out.println(sb);
        }

        private void mostrarSalarioEmpleado(Scanner sc) {
            System.out.print("Introduce DNI del empleado: ");
            String dni = sc.nextLine();
            try {
                Empleado empleado = empleadoService.findById(dni);
                Nomina nomina = nominaService.findByEmpleado(empleado);
                System.out.println("Salario de " + empleado.getDni() + ": " + nomina.getSueldo());
            } catch (Exception e) {
                System.out.println("Empleado no encontrado.");
            }
        }

        private void modificarEmpleados(Scanner sc) {
            System.out.print("Introduce DNI del empleado a modificar: ");
            String dni = sc.nextLine();
            try {
                Empleado empleado = empleadoService.findById(dni);
                System.out.print("Introduce nueva categoría: ");
                int categoria = sc.nextInt();
                System.out.print("Introduce nuevos años: ");
                int anyos = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                empleado.setCategoria(categoria);
                empleado.setAnyos(anyos);
                empleadoService.save(empleado);

                System.out.println("Empleado actualizado correctamente.");
            } catch (Exception e) {
                System.out.println("Error modificando empleado.");
            }
        }

        private void recalcularSueldoEmpleado(Scanner sc) {
            System.out.print("Introduce DNI del empleado: ");
            String dni = sc.nextLine();
            try {
                Empleado empleado = empleadoService.findById(dni);
                Nomina nomina = nominaService.findByEmpleado(empleado); // recarga la nómina
                System.out.println("Sueldo recalculado: " + nomina.getSueldo());
            } catch (Exception e) {
                System.out.println("Empleado no encontrado.");
            }
        }

        private void recalcularSueldoTodos() {
            List<Empleado> empleados = empleadoService.getAll();
            for (Empleado e : empleados) {
                Nomina nomina = nominaService.findByEmpleado(e);
                System.out.println("Sueldo de " + e.getNombre() + " recalculado: " + nomina.getSueldo());
            }
        }

        private void copiaSeguridad() {
            String ruta = "src/main/resources/sueldos.dat";
            try (FileHandler fh = new FileHandler("src/main/resources/dummy_read.txt", ruta)) {
                List<SueldoBase> sueldos = sueldoBaseService.getAll();
                for (SueldoBase sb : sueldos) {
                    fh.writeInt(sb.getCategoria());
                    fh.writeInt(sb.getBaseSalarial());
                }
                System.out.println("Copia de seguridad realizada en " + ruta);
            } catch (IOException e) {
                System.out.println("Error creando copia de seguridad.");
            }
        }
    }
}
