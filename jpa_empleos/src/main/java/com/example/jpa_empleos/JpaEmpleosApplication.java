package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final CategoriasRepository categoriasRepo;

    public JpaEmpleosApplication(CategoriasRepository categoriasRepo) {
        this.categoriasRepo = categoriasRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        existeId();
    }

    /**
     * Método existsById - Interfaz CrudRepository
     */
    private void existeId() {
        int id = 150;
        boolean existe = categoriasRepo.existsById(id);
        System.out.println("Existe la categoría " + id + ": " + existe);
    }

    /**
     * Método findAll - Interfaz CrudRepository
     */
    private void encontrarTodos() {
        Iterable<Categoria> categorias = categoriasRepo.findAll();
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

    /**
     * Método findAllById - Interfaz CrudRepository
     */
    private void encontrarVariasPorId() {
        List<Integer> listaCategorias = new LinkedList<>();
        listaCategorias.add(14);
        listaCategorias.add(16);
        listaCategorias.add(18);

        Iterable<Categoria> categorias = categoriasRepo.findAllById(listaCategorias);
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

    /**
     * Método deleteAll - Interfaz CrudRepository
     */
    private void borrarTodos() {
        categoriasRepo.deleteAll();
        System.out.println("Todos los registros se han borrado...");
    }

    /**
     * Método count - Interfaz CrudRepository
     */
    private void conteo() {
        Long numRegistros = categoriasRepo.count();
        System.out.println("Total de categorías: " + numRegistros);
    }


    /**
     * Método deleteById(borrar) - Interfaz CrudRepository
     */
    private void eliminar() {
        int idCategoria = 1;
        categoriasRepo.deleteById(idCategoria);
        System.out.println("Registro eliminado...");
    }

    /**
     * Método save(actualizar) - Interfaz CrudRepository
     */
    private void modificar() {
        Optional<Categoria> categoriaBuscada = categoriasRepo.findById(1);
        if (categoriaBuscada.isPresent()) {
            Categoria categoriaTmp = categoriaBuscada.get();
            categoriaTmp.setNombre("Ingeniería de Software");
            categoriaTmp.setDescripcion("Desarrollo de sistemas");

            categoriasRepo.save(categoriaTmp);

            System.out.println(categoriaBuscada);
            System.out.println("Categoría actualizada...");
        } else {
            System.out.println("Categoría no encontrada");
        }
    }

    /**
     * Método findById - Interfaz CrudRepository
     */
    private void buscarPorId() {
        Optional<Categoria> categoriaBuscada = categoriasRepo.findById(5);
        if (categoriaBuscada.isPresent()) {
            System.out.println(categoriaBuscada.get());
        } else {
            System.out.println("Categoría no encontrada");
        }
    }

    /**
     * Método save - Interfaz CrudRepository
     */
    private void guardar() {
        System.out.println("Guardando...");

        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre("Finanzas");
        nuevaCategoria.setDescripcion("Trabajos relacionados con finanzas y " +
                "contabilidad");

        categoriasRepo.save(nuevaCategoria);
        System.out.println(nuevaCategoria);
    }
}
