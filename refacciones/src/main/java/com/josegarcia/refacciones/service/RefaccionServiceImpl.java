package com.josegarcia.refacciones.service;

import com.josegarcia.refacciones.models.Refaccion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RefaccionServiceImpl implements IRefaccionService {
    List<Refaccion> listaRefacciones = null;

    public RefaccionServiceImpl() {
        listaRefacciones = new ArrayList<>();
        listaRefacciones.add(new Refaccion(1, "Filtro de aceite", "Motor", 2023, 120.50f));
        listaRefacciones.add(new Refaccion(2, "Bujía iridium", "Encendido", 2022, 95.00f));
        listaRefacciones.add(new Refaccion(3, "Pastillas de freno", "Frenos", 2024, 310.99f));
        listaRefacciones.add(new Refaccion(4, "Amortiguador trasero", "Suspensión", 2021,
                850.00f));
    }

    @Override
    public List<Refaccion> buscarTodas() {
        return listaRefacciones;
    }

    @Override
    public Refaccion buscarPorId(int idRefaccion) {
        for (Refaccion refaccion : listaRefacciones) {
            if (refaccion.getId() == idRefaccion) {
                return refaccion;
            }
        }
        return null;
    }
}
