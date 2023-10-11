package com.ds.apiRest.services;

import com.ds.apiRest.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonaService extends BaseService<Persona,Long>{

    //Metodo Query consulta
    List<Persona> search(String filtro) throws Exception;

    //Con paginacion
    Page<Persona> search(String filtro, Pageable pageable) throws Exception;
}
