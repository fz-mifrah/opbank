package com.sgaraba.library.repository;

import com.sgaraba.library.domain.PaimentFacture;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class PaimentFactureRepositoryWithBagRelationshipsImpl implements PaimentFactureRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<PaimentFacture> fetchBagRelationships(Optional<PaimentFacture> paimentFacture) {
        return paimentFacture.map(this::fetchFactures);
    }

    @Override
    public Page<PaimentFacture> fetchBagRelationships(Page<PaimentFacture> paimentFactures) {
        return new PageImpl<>(
            fetchBagRelationships(paimentFactures.getContent()),
            paimentFactures.getPageable(),
            paimentFactures.getTotalElements()
        );
    }

    @Override
    public List<PaimentFacture> fetchBagRelationships(List<PaimentFacture> paimentFactures) {
        return Optional.of(paimentFactures).map(this::fetchFactures).orElse(Collections.emptyList());
    }

    PaimentFacture fetchFactures(PaimentFacture result) {
        return entityManager
            .createQuery(
                "select paimentFacture from PaimentFacture paimentFacture left join fetch paimentFacture.factures where paimentFacture is :paimentFacture",
                PaimentFacture.class
            )
            .setParameter("paimentFacture", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<PaimentFacture> fetchFactures(List<PaimentFacture> paimentFactures) {
        return entityManager
            .createQuery(
                "select distinct paimentFacture from PaimentFacture paimentFacture left join fetch paimentFacture.factures where paimentFacture in :paimentFactures",
                PaimentFacture.class
            )
            .setParameter("paimentFactures", paimentFactures)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
