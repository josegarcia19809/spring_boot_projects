package com.example.empleos.service;

import com.example.empleos.models.EstatusVacante;
import com.example.empleos.models.Vacante;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class VacanteServiceImpl implements  IVacanteService {
    private List<Vacante> listaVacantes = null;

    public VacanteServiceImpl() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        listaVacantes = new LinkedList<>();

        try {
            Vacante vacante1 = new Vacante();
            vacante1.setId(1);
            vacante1.setNombre("Ingeniero Civil");
            vacante1.setDescripcion("Se solicita ingeniero para diseñar puente peatonal");
            vacante1.setFecha(sdf.parse("08-02-2019"));
            vacante1.setSalario(8500.00);
            vacante1.setDestacado(1);
            vacante1.setDetalles("Crea edificios");
            vacante1.setEstatus(EstatusVacante.Aprobada);

            vacante1.setImagen("empresa1.png");

            Vacante vacante2 = new Vacante();
            vacante2.setId(2);
            vacante2.setNombre("Contador público");
            vacante2.setDescripcion("Empresa importante solicita el contador con cinco años de experiencia titulado");
            vacante2.setFecha(sdf.parse("09-02-2019"));
            vacante2.setSalario(12000.00);
            vacante2.setDestacado(0);
            vacante2.setDetalles("Cuenta");
            vacante2.setEstatus(EstatusVacante.Aprobada);
            vacante2.setImagen("empresa2.png");

            Vacante vacante3 = new Vacante();
            vacante3.setId(3);
            vacante3.setNombre("Ingeniero Eléctrico");
            vacante3.setDescripcion("Se solicita ingeniero para mantenimiento de la Instalación eléctrica");
            vacante3.setFecha(sdf.parse("10-02-2019"));
            vacante3.setSalario(10500.00);
            vacante3.setDestacado(0);
            vacante3.setDetalles("Crea conexiones");
            vacante3.setEstatus(EstatusVacante.Creada);
            // No tiene imagen

            Vacante vacante4 = new Vacante();
            vacante4.setId(4);
            vacante4.setNombre("Diseñador gráfico");
            vacante4.setDescripcion("Se solicita diseñador para diseñar Publicidad de la empresa");
            vacante4.setFecha(sdf.parse("11-02-2019"));
            vacante4.setSalario(7500.00);
            vacante4.setDestacado(1);
            vacante4.setDetalles("Crea diseños");
            vacante4.setEstatus(EstatusVacante.Creada);
            vacante4.setImagen("empresa3.png");

            listaVacantes.add(vacante1);
            listaVacantes.add(vacante2);
            listaVacantes.add(vacante3);
            listaVacantes.add(vacante4);

        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Vacante> buscarTodas() {
        return listaVacantes;
    }

    @Override
    public Vacante buscarPorId(Integer idVacante) {
        for (Vacante v : listaVacantes) {
            if (v.getId() == idVacante) {
                return v;
            }
        }
        return null;
    }

    @Override
    public void guardar(Vacante vacante) {
        listaVacantes.add(vacante);
    }

}
