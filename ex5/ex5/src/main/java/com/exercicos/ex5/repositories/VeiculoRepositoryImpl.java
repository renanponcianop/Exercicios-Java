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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
class VeiculoRepositoryImpl implements VeiculoCustomRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<Veiculo> findByFilters(Boolean vendido, Integer decada, String fabricante, Boolean recentes) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Veiculo> cq = cb.createQuery(Veiculo.class);

        Root<Veiculo> root = cq.from(Veiculo.class);
        List<Predicate> predicates = new ArrayList<>();
        if (vendido != null){
            predicates.add(cb.equal(root.get("vendido"), vendido));
        }
        if (decada != null) {
            predicates.add(cb.between(root.get("ano"),decada,decada+9));
        }
        if (fabricante != null) {
            predicates.add(cb.equal(root.get("marca"),fabricante));
        }
        if (recentes != null) {
            Calendar c= Calendar.getInstance();
            Date end = c.getTime();
            c.add(Calendar.DATE, -14);
            Date begin = c.getTime();
            predicates.add(cb.between(root.get("created"),begin,end));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }
}
