package com.exercicos.ex5.repositories;

import com.exercicos.ex5.entities.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface VeiculoCustomRepository {

    public List<Veiculo> findByFilters(Boolean vendido, Integer decada, String fabricante, Boolean recentes);
}
