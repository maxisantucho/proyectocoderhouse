package com.proyectocoderhouse.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectocoderhouse.modelos.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{

}
