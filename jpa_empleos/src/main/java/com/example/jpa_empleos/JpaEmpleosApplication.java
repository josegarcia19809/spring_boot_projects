package com.example.jpa_empleos;

import com.example.jpa_empleos.models.*;
import com.example.jpa_empleos.repository.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.*;

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

        buscarUsuario();
    }

    /**
     * Método para buscar un usuario y sus métodos asociados
     */
    public void buscarUsuario() {
        Optional<Usuario> usuarioOptional = usuarioRepo.findById(1);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Perfiles asignados");
            for (Perfil perfil : usuario.getPerfiles()) {
                System.out.println(perfil.getPerfil());
            }
        }
    }

    /**
     * Crear usuario con 2 perfiles ADMINISTRADOR=2, USUARIO=3
     */
    private void crearUsuarioConPerfiles() {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("José García");
        nuevoUsuario.setEmail("jose@gmail.com");
        nuevoUsuario.setUsername("joseg"); // 👈 nombre de usuario
        nuevoUsuario.setPassword("12345");
        nuevoUsuario.setEstatus(1); // activo por defecto
        nuevoUsuario.setFechaRegistro(LocalDate.now()); // 👈 fecha actual

        // Supongamos que ya tienes dos perfiles obtenidos del repositorio
        Perfil perfil1 = new Perfil();
        perfil1.setId(2);

        Perfil perfil2 = new Perfil();
        perfil2.setId(3);

        // Crear el conjunto de perfiles y asignarlo
        Set<Perfil> perfilesUsuario = new HashSet<>();
        perfilesUsuario.add(perfil1);
        perfilesUsuario.add(perfil2);
        nuevoUsuario.setPerfiles(perfilesUsuario);

        usuarioRepo.save(nuevoUsuario);
    }

    /**
     * Método para crear los perfiles
     */
    private void crearPefiles() {
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

    private void guardarVacante() {
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
