package com.example.empleos.service.db;

import com.example.empleos.models.EstatusVacante;
import com.example.empleos.models.Vacante;
import com.example.empleos.repository.VacantesRepository;
import com.example.empleos.service.IVacanteService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class VacantesServiceJPA implements IVacanteService {

    private VacantesRepository vacantesRepository;

    public VacantesServiceJPA(VacantesRepository vacantesRepository) {
        this.vacantesRepository = vacantesRepository;
    }

    @Override
    public List<Vacante> buscarTodas() {
        return vacantesRepository.findAll();
    }

    @Override
    public Vacante buscarPorId(Integer idVacante) {
        Optional<Vacante> vacante = vacantesRepository.findById(idVacante);
        if (vacante.isPresent()) {
            return vacante.get();
        }
        return null;
    }

    @Override
    public void guardar(Vacante vacante) {
        vacantesRepository.save(vacante);
    }

    @Override
    public List<Vacante> buscarDestacadas() {
        return vacantesRepository.findByDestacadoAndEstatusOrderByIdDesc(1,
                EstatusVacante.Aprobada);
    }
}
