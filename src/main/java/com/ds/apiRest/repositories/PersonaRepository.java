package com.ds.apiRest.repositories;

import com.ds.apiRest.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends BaseRepository<Persona,Long>{


    //Consultas Query
    List<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido);


    //Con paginacion
    Page<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido, Pageable pageable);


    //buscar si existe por dni
    boolean existsByDni(int dni);

    //Anotacion JPQL parametros indexados
    @Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE '%?1%' OR p.apellido LIKE '%?1%'") //like: 1 por la cant de parametros
    List<Persona> search(String filtro);

    //CON PAGINACION
    @Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE '%?1%' OR p.apellido LIKE '%?1%'") //like: 1 por la cant de parametros
    Page<Persona> search3(String filtro, Pageable pageable);

    //Anotacion JPQL parametros nombrado
    @Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE '%:filtro%' OR p.apellido LIKE '%filtro%'") //like: 1 por la cant de parametros
    List<Persona> search2(@Param("filtro") String filtro);

    //CON PAGINACION
    @Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE '%:filtro%' OR p.apellido LIKE '%filtro%'") //like: 1 por la cant de parametros
    Page<Persona> search2(@Param("filtro") String filtro, Pageable pageable);

    //Query nativo
    @Query(value = "SELECT * FROM persona WHERE persona.nombre LIKE '%?1%' OR persona.apellido LIKE '%?1%'",
            nativeQuery = true)
    List<Persona> searchNativo(@Param("filtro") String filtro);

    //CON PAGINACION
    @Query(value = "SELECT * FROM persona WHERE persona.nombre LIKE '%?1%' OR persona.apellido LIKE '%?1%'",
            countQuery = "SELECT count(*) FROM persona", //se crea porque en las consultas nativas no es auto la paginacion
            nativeQuery = true)
    Page<Persona> searchNativo(@Param("filtro") String filtro, Pageable pageable);
}
