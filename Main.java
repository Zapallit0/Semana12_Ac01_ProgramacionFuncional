import java.util.*;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static List<Desarrollador> employeeList = new ArrayList<>();
    public static void main(String[] args) {
        IniciativaFactory employeeFactory = new IniciativaFactory();
        employeeList = employeeFactory.getAllDesarrolladores();
//TODO
//1. Enumere todas las iniciativas distintas de manera descente.
//2. Imprima el nombre completo de cualquier desarrollador cuyo nombre comience con "A".
//3. Liste todos los desarrolladores que se unieron en el año 2023 (el año se extraerá del código del desarrollador, es decir, los primeros 4 caracteres)
//4. Ordene los desarrolladores según el nombre; y luego ordene por salario.
//5. Imprima los nombres de todos los desarrolladores con el tercer salario más alto. (Generalícelo para el enésimo salario más alto).
//6. Imprimir salario mínimo.
//7. Imprima la lista de todos los desarrolladores con salario mínimo.

//8. Liste a todas las personas que trabajan en más de 2 proyectos.

//9. Conteo del total de laptops asignadas a los desarrolladores.

//10. Recuento de todas las iniciativas con Luis Carrillo Lopez.
        long contadorIniciativasLuisCarrilloLopez = employeeList.stream()
                .flatMap(desarrollador -> desarrollador.getIniciativas().stream())
                .filter(iniciativa -> iniciativa.getNombreProjectManager().equals("Luis Carrillo Lopez"))
                .count();
        System.out.println("Número de iniciativas con Luis Carrillo Lopez: " + contadorIniciativasLuisCarrilloLopez);

//11. Lista de todas las personas que trabajan con Luis Carrillo Lopez.
        String nombreLuisito = "Luis Carrillo Lopez";
        List<Desarrollador> personasQueTrabajanConLuisCarrillo= employeeList.stream()
                .filter(desarrollador -> desarrollador.getIniciativas().stream()
                        .anyMatch(iniciativa -> iniciativa.getNombreProjectManager().equals(nombreLuisito)))
                .toList();
        System.out.println("Personas que trabajan con Luis Carrillo Lopez:");
        personasQueTrabajanConLuisCarrillo.forEach(desarrollador -> System.out.println(desarrollador.getNombres()));

//12. Cree un mapa basado en estos datos, el key debe ser el año de incorporación y el valor debe ser la lista de todos los desarrolladores que se incorporaron en ese año en particular.

        List<Desarrollador> desarrolladores = new IniciativaFactory().getAllDesarrolladores();
        Map<Integer, List<Desarrollador>> desarrolladoresPorAño = desarrolladores.stream()
                .collect(Collectors.groupingBy(d -> Integer.parseInt(d.getCodigo().substring(0, 4))));

        desarrolladoresPorAño.forEach((año, listaDesarrolladores) -> {
            System.out.println("Año: " + año);
            listaDesarrolladores.forEach(desarrollador -> System.out.println("  - " + desarrollador.getNombres() + " " + desarrollador.getApellidos()));
        });

//14. Cree un mapa basado en estos datos, el key debe ser el año deincorporación y el valor debe ser el recuento de personas que se unieron en ese añoen particular.
        Map<Integer, Long> conteoPorAño = desarrolladores.stream()
                .collect(Collectors.groupingBy(d -> Integer.parseInt(d.getCodigo().substring(0, 4)), Collectors.counting()));

        conteoPorAño.forEach((anio, cantidad) -> {
            System.out.println("Año: " + anio + ", Cantidad: " + cantidad);
        });
    }
}