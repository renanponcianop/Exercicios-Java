package com.exercicos.ex5.repositories;

import com.exercicos.ex5.entities.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>, VeiculoCustomRepository {}
