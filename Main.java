import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.*;


class Main {
    static List<Students>studentsList;
    public static void main(String[] args) throws IOException {
        // Paso 1: Leer el archivo CSV usando el metodo cargarArchivo y cargar los datos en una lista de objetos Students
        cargarArchivo();
        // Paso 2: Aspirantes por carrera (mostrar la lista y el total)
        mostrarStudentsPorCarrera();
        // Paso 3: Total de mujeres por carrera
        totalMujeresPorCarrera();
        // Paso 4: Total de hombres por carrera
        totalHombresPorCarrera();
        // Paso 5: Estudiante con el puntaje más alto (math_score) por carrera
        topStudentPorCarrera();
        // Paso 6: Estudiante con el puntaje más alto de todos
        topStudentTodos();
        // Paso 7: Puntaje Promedio (math_score) por carrera
        puntajePromedioPorCarrera();
        

    }
    static void cargarArchivo() throws IOException{ //Exception hace referencia a excepciones relacionadas con operaciones de entrada y salida.
        Pattern pattern =Pattern.compile(","); // El patrón es una coma (','), es decir el archivo CSV utiliza comas para separar las columnas.
        String filename= "student-scores.csv"; // Variable con el nombre del archivo csv

        try(Stream<String> lines = Files.lines(Path.of(filename))){ //Se crea un flujo (Stream) de líneas de texto a partir del archivo CSV
            studentsList=lines.skip(1).map(line->{  //el skip salta de linea a la siguiente (la primera no es util)   //map es el metodo y que pasa por todos los elementos del stream(flujo)
                String[]data=pattern.split(line);
                return new Students(
                        Integer.parseInt(data[0].trim()),      //posicion que necesitamos (en el excel)
                        data[1].trim(),
                        data[2].trim(),
                        data[4].trim(),
                        data[9].trim(),
                        Integer.parseInt(data[10].trim())
                    );
            }).collect(Collectors.toList());         //collect guarda info
            //studentsList.forEach(System.out::println);
        }
    }
    
    // Paso 2: Aspirantes por carrera (mostrar la lista y el total)
    static void mostrarStudentsPorCarrera(){
     
        System.out.printf("Aspirantes por carrera:\n");
        Map<String, List<Students>> studentsByCareer =
                studentsList.stream()         //es una secuencia de elementos que se procesan de manera funcional----permiten realizar operaciones en colecciones de datos
                        .collect(Collectors.groupingBy(Students::getCareer_aspiration));
        studentsByCareer.forEach(
                (career, studentList) ->
                {
                    System.out.println("Carrera: " + career);
                    studentList.forEach(student -> System.out.println("  Id: " + student.getId() + ", " + student.getFirst_Name() + " " + student.getLast_Name()));
                    System.out.println("Total: " + studentList.size()+"\n");
                }
        );
    }
    // Paso 3: Total de mujeres por carrera
    static void totalMujeresPorCarrera(){
        System.out.println("Mujeres por carrera:\n");
        Map<String, Long> womenByCareer = studentsList.stream()   //tipo de estructura de dato que corresponde a la lista de parejas
                .filter(student -> student.getGender().equals("female"))
                .collect(Collectors.groupingBy(Students::getCareer_aspiration, Collectors.counting()));
        //Map: Produce un flujo en el que cada elemento del flujo original está asociado a un nuevo valor
        //Filter: Produce un flujo que contiene sólo los elementos que satisfacen la condicion
        //collect: Crea una nueva colección de elementos que contienen los resultados de las operaciones anteriores del flujo
        womenByCareer.forEach(
                (career, total) ->
                {
                    System.out.print("Carrera: " + career);
                    System.out.print(",  Total: " + total+"\n");
                }
            );//forEach: Realiza un procesamiento sobre cada elemento en un flujo
        System.out.println();
        
    }
    // Paso 4: Total de hombres por carrera
    static void totalHombresPorCarrera(){
        System.out.println("Hombres por carrera:\n");
        Map<String, Long> menByCareer = studentsList.stream()
            .filter(student -> student.getGender().equals("male"))
            .collect(Collectors.groupingBy(Students::getCareer_aspiration, Collectors.counting()));


        menByCareer.forEach(
                (career, total) ->
                {
                    System.out.print("Carrera: " + career);
                    System.out.print(",  Total: " + total+"\n");
                }
        );
        System.out.println();
        
    }

    // Paso 5: Estudiante con el puntaje más alto (math_score) por carrera
    static void topStudentPorCarrera(){
        System.out.println("Estudiante con el puntaje más alto (math_score) por carrera:\n");
    
        Map<String, Optional<Students>> topStudentByCareer = studentsList.stream()
            .collect(Collectors.groupingBy(Students::getCareer_aspiration,
                Collectors.maxBy((s1, s2) -> Integer.compare(s1.getMath_score(), s2.getMath_score()))));

        topStudentByCareer.forEach(
                (career, students) ->
                {
                    System.out.print("Carrera: " + career);
                    System.out.print(",  Estudiante: \n" + students.orElse(null)+"\n");
                }
        );
        System.out.println();
        }

    // Paso 6: Estudiante con el puntaje más alto de todos
    static void topStudentTodos(){
        System.out.println("Estudiante con el puntaje (math_score) más alto de todos: \n");
        Optional<Students> topStudentOverall = studentsList.stream()          //optional tiene en cuenta una comparacion entre los datos
        .max((s1, s2) -> Integer.compare(s1.getMath_score(), s2.getMath_score()));
        System.out.println(topStudentOverall.orElse(null));

        System.out.println();
        //siempre va a ser el estudiante 21. Es el que tiene el numero de id mas pequeño y el puntaje 100 (el primero que encuentra con puntaje 100)

   }

   // Paso 7: Puntaje Promedio (math_score) por carrera
   static void puntajePromedioPorCarrera(){
        System.out.println("Puntaje Promedio (math_score) por carrera: \n");
        Map<String, Double> averageMathScoreByCareer = studentsList.stream()
        .collect(Collectors.groupingBy(Students::getCareer_aspiration,
                Collectors.averagingDouble(Students::getMath_score)));

        averageMathScoreByCareer.forEach(
        (career, promedio) ->
        {
                System.out.print("Carrera: " + career);
                System.out.print(",  Promedio: " + promedio+"\n");
        }
        );
        System.out.println();

   }
}
