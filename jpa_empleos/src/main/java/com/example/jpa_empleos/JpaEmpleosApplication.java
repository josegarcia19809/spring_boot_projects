package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.models.EstatusVacante;
import com.example.jpa_empleos.models.Perfil;
import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

    private final CategoriasRepository categoriasRepo;
    private final CategoriasJPARepository categoriasJPARepo;
    private final VacantesRepository vacantesRepo;
    private final PerfilesRepository perfilesRepo;
    private final UsuarioRepository usuarioRepo;


    public JpaEmpleosApplication(CategoriasRepository categoriasRepo, CategoriasJPARepository categoriasJPARepo, VacantesRepository vacantesRepo, PerfilesRepository perfilesRepo, UsuarioRepository usuarioRepo) {
        this.categoriasRepo = categoriasRepo;
        this.categoriasJPARepo = categoriasJPARepo;
        this.vacantesRepo = vacantesRepo;
        this.perfilesRepo = perfilesRepo;
        this.usuarioRepo = usuarioRepo;
    }



    public static void main(String[] args) {
        SpringApplication.run(JpaEmpleosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        crearPerfiles();
    }

    /**
     * Método para crear los perfiles
     */
    private void crearPerfiles() {
        perfilesRepo.saveAll(obtenerPerfiles());
    }

    /**
     * Método que regresa una lista de Perfiles que se tienen en la aplicación de empleos
     */
    private List<Perfil> obtenerPerfiles() {
        List<Perfil> perfiles = new LinkedList<>();
        Perfil perfil1 = new Perfil();
        perfil1.setPerfil("SUPERVISOR");

        Perfil perfil2 = new Perfil();
        perfil2.setPerfil("ADMINISTRADOR");

        Perfil perfil3 = new Perfil();
        perfil3.setPerfil("USUARIO");

        perfiles.add(perfil1);
        perfiles.add(perfil2);
        perfiles.add(perfil3);
        return perfiles;
    }



    /**
     * Guardar una vacante
     */

    private void guardarVacante(){
        Vacante vacante = new Vacante();
        vacante.setNombre("Desarrollador Java PRO");
        vacante.setDescripcion("Se busca desarrollador con experiencia en Spring Boot y JPA.");
        vacante.setFecha(new Date());
        vacante.setSalario(25000.0);
        vacante.setEstatus(EstatusVacante.Creada); // Enum, asegúrate que exista en tu proyecto
        vacante.setDestacado(1);
        vacante.setImagen("logo_empresa.png");
        vacante.setDetalles("Trabajo remoto con horario flexible. Beneficios y capacitación incluidos.");

        // Crear una categoría asociada
        Categoria categoria = new Categoria();
        categoria.setId(1); // Si ya existe en la BD, solo asignas el id
        // o puedes crear una nueva:
        // categoria.setNombre("Tecnología");
        // categoria.setDescripcion("Empleos relacionados con desarrollo y TI.");

        vacante.setCategoria(categoria);
        vacantesRepo.save(vacante);
    }


    /**
     * Método findAll - Interfaz JPARepository
     */
    public void buscarVacantes() {
        List<Vacante> vacantes = vacantesRepo.findAll();
        for (Vacante vacante : vacantes) {
            System.out.println(vacante.getId() + ". " + vacante.getNombre() +
                    " -> " + vacante.getCategoria().getNombre());
        }
    }


    /**
     * Método findAll - Interfaz JPARepository
     */
//    private void buscarVacantes() {
//        List<Vacante> vacantes = vacantesRepo.findAll();
//        for (Vacante vacante : vacantes) {
//            System.out.println(vacante.getId() + ". " + vacante.getNombre());
//        }
//    }


    /**
     * Metodo findAll [Con paginacion y Ordenados] - Interfaz PagingAndSortingRepository
     */
    private void buscarTodosPaginacionOrdenados() {
        Page<Categoria> page = categoriasJPARepo.findAll(PageRequest.of(0, 5,
                Sort.by("nombre").descending()));

        System.out.println("Total Registros: " + page.getTotalElements());
        System.out.println("Total Paginas: " + page.getTotalPages());
        for (Categoria c : page.getContent()) {
            System.out.println(c.getId() + " " + c.getNombre());
        }
    }


    /**
     * Metodo findAll [Con Paginación] - Interfaz PagingAndSortingRepository
     */
    private void buscarTodosPaginacion() {
        Page<Categoria> page = categoriasJPARepo.findAll(PageRequest.of(3, 5));
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
            System.out.println(categoria.getId()+" "+categoria.getNombre());
        }
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



