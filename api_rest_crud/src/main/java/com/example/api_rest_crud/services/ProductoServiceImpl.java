package com.example.api_rest_crud.services;

import com.example.api_rest_crud.entities.Producto;
import com.example.api_rest_crud.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoServiceI {

    @Autowired
    private ProductoRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Producto> buscarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Producto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Producto guardar(Producto nuevoProducto) {
        return repository.save(nuevoProducto);
    }

    @Transactional
    @Override
    public Optional<Producto> actualizar(Long id, Producto producto) {

        Optional<Producto> productoOptional = repository.findById(id);

        if (productoOptional.isPresent()) {
            Producto productoGuardado = productoOptional.orElseThrow();

            productoGuardado.setNombre(producto.getNombre());
            productoGuardado.setDescripcion(producto.getDescripcion());
            productoGuardado.setPrecio(producto.getPrecio());
            productoGuardado.setImagen(producto.getImagen());

            return Optional.of(repository.save(productoGuardado));
        }

        return productoOptional;
    }


    @Transactional
    @Override
    public Optional<Producto> borrar(Long id) {
        Optional<Producto> optionalProduct = repository.findById(id);
        optionalProduct.ifPresent(repository::delete);
        return optionalProduct;
    }
}
