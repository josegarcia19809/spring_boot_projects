package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasJPARepository;
import com.example.jpa_empleos.repository.CategoriasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final CategoriasRepository categoriasRepo;
    private final CategoriasJPARepository categoriasJPARepo;

    public JpaEmpleosApplication(CategoriasRepository categoriasRepo, CategoriasJPARepository categoriasJPARepo) {
        this.categoriasRepo = categoriasRepo;
        this.categoriasJPARepo = categoriasJPARepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        buscarTodosPaginacion();
    }

    /**
     * Metodo findAll [Con Paginación] - Interfaz PagingAndSortingRepository
     */
    private void buscarTodosPaginacion() {
        Page<Categoria> page = categoriasJPARepo.findAll(PageRequest.of(0, 5));
        System.out.println("Total Registros: " + page.getTotalElements());
        System.out.println("Total Paginas: " + page.getTotalPages());
        for (Categoria c : page.getContent()) {
            System.out.println(c.getId() + " " + c.getNombre());
        }
    }


    /**
     * Metodo findAll [Ordenados por un campo] - Interfaz PagingAndSortingRepository
     */
    private void buscarTodosOrdenados() {
        List<Categoria> categorias = categoriasJPARepo.findAll(Sort.by("nombre").descending());
        for (Categoria categoria : categorias) {
            System.out.println(categoria.getId() + " " + categoria.getNombre());
        }
    }

    /**
     * Método deleteAllInBatch [Usar con precaución] - Interfaz JPARepository
     */
    private void borrarTodasEnBloque() {
        categoriasJPARepo.deleteAllInBatch();
    }

    /**
     * Método findAll - Interfaz JPARepository
     */
    private void buscarTodasJPA() {
        List<Categoria> categorias = categoriasJPARepo.findAll();
        for (Categoria categoria : categorias) {
            System.out.println(categoria.getId() + " " + categoria.getNombre());
        }
    }

    /**
     * Método saveAll - Interfaz CrudRepository
     */
    private void guardarTodas() {
        List<Categoria> nuevasCategorias = obtenerCategorias();
        categoriasRepo.saveAll(nuevasCategorias);
        System.out.println("Categorías guardadas...");
    }

    // Método que devuelve una lista de categorías
    public List<Categoria> obtenerCategorias() {
        List<Categoria> categorias = new LinkedList<>();

        Categoria c1 = new Categoria();
        c1.setNombre("Tecnología");
        c1.setDescripcion("Empleos relacionados con software, hardware y redes.");
        categorias.add(c1);

        Categoria c2 = new Categoria();
        c2.setNombre("Educación");
        c2.setDescripcion("Puestos en docencia, investigación y capacitación.");
        categorias.add(c2);

        Categoria c3 = new Categoria();
        c3.setNombre("Salud");
        c3.setDescripcion("Trabajos en hospitales, clínicas y laboratorios.");
        categorias.add(c3);

        Categoria c4 = new Categoria();
        c4.setNombre("Construcción");
        c4.setDescripcion("Empleos en obras civiles, arquitectura e ingeniería.");
        categorias.add(c4);

        Categoria c5 = new Categoria();
        c5.setNombre("Finanzas");
        c5.setDescripcion("Puestos en contabilidad, banca y administración financiera.");
        categorias.add(c5);

        return categorias;
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
