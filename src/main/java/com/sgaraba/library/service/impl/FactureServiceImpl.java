package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.Facture;
import com.sgaraba.library.repository.FactureRepository;
import com.sgaraba.library.service.FactureService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Facture}.
 */
@Service
@Transactional
public class FactureServiceImpl implements FactureService {

    private final Logger log = LoggerFactory.getLogger(FactureServiceImpl.class);

    private final FactureRepository factureRepository;

    public FactureServiceImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Override
    public Facture save(Facture facture) {
        log.debug("Request to save Facture : {}", facture);
        return factureRepository.save(facture);
    }

    @Override
    public Facture update(Facture facture) {
        log.debug("Request to save Facture : {}", facture);
        return factureRepository.save(facture);
    }

    @Override
    public Optional<Facture> partialUpdate(Facture facture) {
        log.debug("Request to partially update Facture : {}", facture);

        return factureRepository
            .findById(facture.getId())
            .map(existingFacture -> {
                if (facture.getNom() != null) {
                    existingFacture.setNom(facture.getNom());
                }

                return existingFacture;
            })
            .map(factureRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Facture> findAll() {
        log.debug("Request to get all Factures");
        return factureRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Facture> findOne(Long id) {
        log.debug("Request to get Facture : {}", id);
        return factureRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Facture : {}", id);
        factureRepository.deleteById(id);
    }
}
