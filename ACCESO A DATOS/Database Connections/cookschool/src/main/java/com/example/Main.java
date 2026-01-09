package com.example;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final ChefCRUD chefCRUD = new ChefCRUD();
    private static final CourseCRUD courseCRUD = new CourseCRUD();
    private static final RecipeCRUD recipeCRUD = new RecipeCRUD();
    private static final StudentCRUD studentCRUD = new StudentCRUD();

    public static void main(String[] args) {

        int option;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Gestionar Chefs");
            System.out.println("2. Gestionar Cursos");
            System.out.println("3. Gestionar Recetas");
            System.out.println("4. Gestionar Estudiantes");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> menuChefs();
                case 2 -> menuCursos();
                case 3 -> menuRecetas();
                case 4 -> menuEstudiantes();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }

        } while (option != 0);
    }

    // ============================
    //         MENU CHEFS
    // ============================
    private static void menuChefs() {
        int op;
        do {
            System.out.println("\n--- Gestión de Chefs ---");
            System.out.println("1. Crear Chef");
            System.out.println("2. Leer Chef");
            System.out.println("3. Actualizar Chef");
            System.out.println("4. Eliminar Chef");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> crearChef();
                case 2 -> leerChef();
                case 3 -> actualizarChef();
                case 4 -> eliminarChef();
                case 0 -> {}
                default -> System.out.println("Opción inválida");
            }

        } while (op != 0);
    }

    private static void crearChef() {
        System.out.print("Nombre del chef: ");
        String name = sc.nextLine();
        System.out.print("Especialidad: ");
        String specialty = sc.nextLine();

        Chef chef = new Chef(name, specialty);
        chefCRUD.create(chef);

        System.out.println("Chef creado correctamente.");
    }

    private static void leerChef() {
        System.out.print("ID del chef: ");
        int id = sc.nextInt();
        sc.nextLine();

        Chef chef = chefCRUD.read(id);
        System.out.println(chef != null ? chef : "No encontrado.");
    }

    private static void actualizarChef() {
        System.out.print("ID del chef a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Chef chef = chefCRUD.read(id);
        if (chef == null) {
            System.out.println("Chef no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        chef.setName(sc.nextLine());
        System.out.print("Nueva especialidad: ");
        chef.setSpecialty(sc.nextLine());

        chefCRUD.update(chef);
        System.out.println("Chef actualizado.");
    }

    private static void eliminarChef() {
        System.out.print("ID del chef a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        chefCRUD.delete(id);
        System.out.println("Chef eliminado.");
    }

    // ============================
    //         MENU CURSOS
    // ============================
    private static void menuCursos() {
        int op;
        do {
            System.out.println("\n--- Gestión de Cursos ---");
            System.out.println("1. Crear Curso");
            System.out.println("2. Leer Curso");
            System.out.println("3. Actualizar Curso");
            System.out.println("4. Eliminar Curso");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> crearCurso();
                case 2 -> leerCurso();
                case 3 -> actualizarCurso();
                case 4 -> eliminarCurso();
                case 0 -> {}
                default -> System.out.println("Opción inválida");
            }

        } while (op != 0);
    }

    private static void crearCurso() {
        System.out.print("Nombre del curso: ");
        String name = sc.nextLine();
        System.out.print("Duración (horas): ");
        int duration = sc.nextInt();
        sc.nextLine();

        System.out.print("ID del chef responsable: ");
        int chefId = sc.nextInt();
        sc.nextLine();

        Chef chef = chefCRUD.read(chefId);
        if (chef == null) {
            System.out.println("Chef no encontrado.");
            return;
        }

        Course course = new Course(name, duration, chef);
        courseCRUD.create(course);

        System.out.println("Curso creado.");
    }

    private static void leerCurso() {
        System.out.print("ID del curso: ");
        int id = sc.nextInt();
        sc.nextLine();

        Course course = courseCRUD.read(id);
        System.out.println(course != null ? course : "No encontrado.");
    }

    private static void actualizarCurso() {
        System.out.print("ID del curso a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Course course = courseCRUD.read(id);
        if (course == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        course.setName(sc.nextLine());
        System.out.print("Nueva duración: ");
        course.setDuration(sc.nextInt());
        sc.nextLine();

        courseCRUD.update(course);
        System.out.println("Curso actualizado.");
    }

    private static void eliminarCurso() {
        System.out.print("ID del curso a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        courseCRUD.delete(id);
        System.out.println("Curso eliminado.");
    }

    // ============================
    //         MENU RECETAS
    // ============================
    private static void menuRecetas() {
        int op;
        do {
            System.out.println("\n--- Gestión de Recetas ---");
            System.out.println("1. Crear Receta");
            System.out.println("2. Leer Receta");
            System.out.println("3. Actualizar Receta");
            System.out.println("4. Eliminar Receta");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> crearReceta();
                case 2 -> leerReceta();
                case 3 -> actualizarReceta();
                case 4 -> eliminarReceta();
                case 0 -> {}
                default -> System.out.println("Opción inválida");
            }

        } while (op != 0);
    }

    private static void crearReceta() {
        System.out.print("Nombre de la receta: ");
        String name = sc.nextLine();
        System.out.print("Dificultad: ");
        String difficulty = sc.nextLine();

        System.out.print("ID del curso: ");
        int courseId = sc.nextInt();
        sc.nextLine();

        Course course = courseCRUD.read(courseId);
        if (course == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        Recipe recipe = new Recipe(name, difficulty, course);
        recipeCRUD.create(recipe);

        System.out.println("Receta creada.");
    }

    private static void leerReceta() {
        System.out.print("ID de la receta: ");
        int id = sc.nextInt();
        sc.nextLine();

        Recipe recipe = recipeCRUD.read(id);
        System.out.println(recipe != null ? recipe : "No encontrada.");
    }

    private static void actualizarReceta() {
        System.out.print("ID de la receta a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Recipe recipe = recipeCRUD.read(id);
        if (recipe == null) {
            System.out.println("Receta no encontrada.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        recipe.setName(sc.nextLine());
        System.out.print("Nueva dificultad: ");
        recipe.setDifficulty(sc.nextLine());

        recipeCRUD.update(recipe);
        System.out.println("Receta actualizada.");
    }

    private static void eliminarReceta() {
        System.out.print("ID de la receta a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        recipeCRUD.delete(id);
        System.out.println("Receta eliminada.");
    }

    // ============================
    //       MENU ESTUDIANTES
    // ============================
    private static void menuEstudiantes() {
        int op;
        do {
            System.out.println("\n--- Gestión de Estudiantes ---");
            System.out.println("1. Crear Estudiante");
            System.out.println("2. Leer Estudiante");
            System.out.println("3. Actualizar Estudiante");
            System.out.println("4. Eliminar Estudiante");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> crearEstudiante();
                case 2 -> leerEstudiante();
                case 3 -> actualizarEstudiante();
                case 4 -> eliminarEstudiante();
                case 0 -> {}
                default -> System.out.println("Opción inválida");
            }

        } while (op != 0);
    }

    private static void crearEstudiante() {
        System.out.print("Nombre del estudiante: ");
        String name = sc.nextLine();

        System.out.print("ID del curso: ");
        int courseId = sc.nextInt();
        sc.nextLine();

        Course course = courseCRUD.read(courseId);
        if (course == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        Student student = new Student(name, course);
        studentCRUD.create(student);

        System.out.println("Estudiante creado.");
    }

    private static void leerEstudiante() {
        System.out.print("ID del estudiante: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student student = studentCRUD.read(id);
        System.out.println(student != null ? student : "No encontrado.");
    }

    private static void actualizarEstudiante() {
        System.out.print("ID del estudiante a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student student = studentCRUD.read(id);
        if (student == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        student.setName(sc.nextLine());

        studentCRUD.update(student);
        System.out.println("Estudiante actualizado.");
    }

    private static void eliminarEstudiante() {
        System.out.print("ID del estudiante a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        studentCRUD.delete(id);
        System.out.println("Estudiante eliminado.");
    }
}