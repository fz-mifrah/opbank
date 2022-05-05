package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.Compte;
import com.sgaraba.library.repository.CompteRepository;
import com.sgaraba.library.service.CompteService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Compte}.
 */
@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private final Logger log = LoggerFactory.getLogger(CompteServiceImpl.class);

    private final CompteRepository compteRepository;

    public CompteServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public Compte save(Compte compte) {
        log.debug("Request to save Compte : {}", compte);
        return compteRepository.save(compte);
    }

    @Override
    public Compte update(Compte compte) {
        log.debug("Request to save Compte : {}", compte);
        return compteRepository.save(compte);
    }

    @Override
    public Optional<Compte> partialUpdate(Compte compte) {
        log.debug("Request to partially update Compte : {}", compte);

        return compteRepository
            .findById(compte.getId())
            .map(existingCompte -> {
                if (compte.getRib() != null) {
                    existingCompte.setRib(compte.getRib());
                }
                if (compte.getDateOuverture() != null) {
                    existingCompte.setDateOuverture(compte.getDateOuverture());
                }
                if (compte.getCode() != null) {
                    existingCompte.setCode(compte.getCode());
                }

                return existingCompte;
            })
            .map(compteRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Compte> findAll(Pageable pageable) {
        log.debug("Request to get all Comptes");
        return compteRepository.findAll(pageable);
    }

    /**
     *  Get all the comptes where Client is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Compte> findAllWhereClientIsNull() {
        log.debug("Request to get all comptes where Client is null");
        return StreamSupport
            .stream(compteRepository.findAll().spliterator(), false)
            .filter(compte -> compte.getClient() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Compte> findOne(Long id) {
        log.debug("Request to get Compte : {}", id);
        return compteRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Compte : {}", id);
        compteRepository.deleteById(id);
    }
}
