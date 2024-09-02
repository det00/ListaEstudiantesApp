package com.estudiantes.presentacion;

import com.estudiantes.datos.EstudianteDAO;
import com.estudiantes.dominio.Estudiante;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        EstudianteDAO edao = new EstudianteDAO();
        while (!salir) {
            try {
                mostrarMenu();
                salir = ejecutarAcciones(consola, edao);
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println("""
                ****MENU LISTA DE ESTUDIANTES****
                1. AGREGAR
                2. LISTAR
                3. BUSCAR
                4. MODIFICAR
                5. ELIMINAR
                6. SALIR
                """);
    }

    public static boolean ejecutarAcciones(Scanner consola, EstudianteDAO edao) {
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion) { //Agregar estudiante
            case 1 -> {
                System.out.println("Agregar estudiante:");
                System.out.print("Nombre: ");
                String nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = edao.agregarEstudiante(estudiante);
                if (agregado) {
                    System.out.println("Se ha agregado correctamente el estudiante " + estudiante);
                } else System.out.println("No se ha podido agregar al estudiante " + estudiante);
            }
            case 2 -> {
                List<Estudiante> estudiantes = new ArrayList<>();
                estudiantes = edao.listar();
                if (estudiantes != null) {
                    System.out.println("LISTA DE ESTUDIANTES");
                    estudiantes.forEach(System.out::println);
                } else System.out.println("La lista esta vacia");
            }
            case 3 -> {
                System.out.println("BUSCAR ESTUDIANTE POR ID");
                System.out.print("Introduce el ID: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = edao.findById(estudiante);
                if (encontrado){
                    System.out.println("Se ha encontrado el estudiante: " + estudiante);
                } else System.out.println("No se ha encontrado el estudiante: " + estudiante);
            }

            case 4 -> {
                System.out.println("ACTUALIZAR ESTUDIANTE");
                System.out.print("ID de estudiante a modificar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();
                var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = edao.modificarEstudiante(estudiante);
                if (modificado){
                    System.out.println("Se ha modificado correctamente el estudiante: " + estudiante);
                } else System.out.println("No se ha podido modificar es estudiante: " + estudiante);
            }
            case 5 -> {
                System.out.println("ELIMINAR ESTUDIANTE");
                System.out.print("ID de estudiante a eliminar: ");
                var id = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(id);
                var eliminado = edao.deleteById(estudiante);
                if (eliminado) {
                    System.out.println("Se ha eliminado correctamente el usuario: " + estudiante);
                } else System.out.println("No se ha podido eliminar el estudiante: " + estudiante);
            }
            case 6 -> {
                System.out.println("SALIR DE LA APLICACION");
                salir = true;
            }
            default -> System.out.println("Opcion incorrecta: " + opcion);
        }
        return salir;
    }
}