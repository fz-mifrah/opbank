package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.Operateur;
import com.sgaraba.library.repository.OperateurRepository;
import com.sgaraba.library.service.OperateurService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Operateur}.
 */
@Service
@Transactional
public class OperateurServiceImpl implements OperateurService {

    private final Logger log = LoggerFactory.getLogger(OperateurServiceImpl.class);

    private final OperateurRepository operateurRepository;

    public OperateurServiceImpl(OperateurRepository operateurRepository) {
        this.operateurRepository = operateurRepository;
    }

    @Override
    public Operateur save(Operateur operateur) {
        log.debug("Request to save Operateur : {}", operateur);
        return operateurRepository.save(operateur);
    }

    @Override
    public Operateur update(Operateur operateur) {
        log.debug("Request to save Operateur : {}", operateur);
        return operateurRepository.save(operateur);
    }

    @Override
    public Optional<Operateur> partialUpdate(Operateur operateur) {
        log.debug("Request to partially update Operateur : {}", operateur);

        return operateurRepository
            .findById(operateur.getId())
            .map(existingOperateur -> {
                if (operateur.getNom() != null) {
                    existingOperateur.setNom(operateur.getNom());
                }

                return existingOperateur;
            })
            .map(operateurRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Operateur> findAll() {
        log.debug("Request to get all Operateurs");
        return operateurRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Operateur> findOne(Long id) {
        log.debug("Request to get Operateur : {}", id);
        return operateurRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Operateur : {}", id);
        operateurRepository.deleteById(id);
    }
}
