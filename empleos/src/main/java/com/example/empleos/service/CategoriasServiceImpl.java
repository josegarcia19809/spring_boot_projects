package com.example.empleos.service;

import com.example.empleos.models.Categoria;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

@Service
public class CategoriasServiceImpl implements ICategoriasService {

    private List<Categoria> categorias= null;

    public CategoriasServiceImpl() {
        categorias = new LinkedList<>();
        Categoria ventas = new Categoria();
        ventas.setId(1);
        ventas.setNombre("Ventas");
        ventas.setDescripcion("Área encargada de la comercialización de productos");

        Categoria contabilidad = new Categoria();
        contabilidad.setId(2);
        contabilidad.setNombre("Contabilidad");
        contabilidad.setDescripcion("Gestión de finanzas y registros contables");

        Categoria transporte = new Categoria();
        transporte.setId(3);
        transporte.setNombre("Transporte");
        transporte.setDescripcion("Movilización y logística de mercancías");

        Categoria informatica = new Categoria();
        informatica.setId(4);
        informatica.setNombre("Informática");
        informatica.setDescripcion("Soporte y desarrollo de sistemas");

        Categoria construccion = new Categoria();
        construccion.setId(5);
        construccion.setNombre("Construcción");
        construccion.setDescripcion("Obras y proyectos de infraestructura");

        categorias.add(ventas);
        categorias.add(contabilidad);
        categorias.add(transporte);
        categorias.add(informatica);
        categorias.add(construccion);
    }

    @Override
    public void guardar(Categoria categoria) {
        categorias.add(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return categorias;
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        for (Categoria categoria : categorias) {
            if (categoria.getId().equals(idCategoria)) {
                return categoria;
            }
        }
        return null;
    }
}
