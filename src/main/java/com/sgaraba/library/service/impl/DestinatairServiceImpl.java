package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.Destinatair;
import com.sgaraba.library.repository.DestinatairRepository;
import com.sgaraba.library.service.DestinatairService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Destinatair}.
 */
@Service
@Transactional
public class DestinatairServiceImpl implements DestinatairService {

    private final Logger log = LoggerFactory.getLogger(DestinatairServiceImpl.class);

    private final DestinatairRepository destinatairRepository;

    public DestinatairServiceImpl(DestinatairRepository destinatairRepository) {
        this.destinatairRepository = destinatairRepository;
    }

    @Override
    public Destinatair save(Destinatair destinatair) {
        log.debug("Request to save Destinatair : {}", destinatair);
        return destinatairRepository.save(destinatair);
    }

    @Override
    public Destinatair update(Destinatair destinatair) {
        log.debug("Request to save Destinatair : {}", destinatair);
        return destinatairRepository.save(destinatair);
    }

    @Override
    public Optional<Destinatair> partialUpdate(Destinatair destinatair) {
        log.debug("Request to partially update Destinatair : {}", destinatair);

        return destinatairRepository
            .findById(destinatair.getId())
            .map(existingDestinatair -> {
                if (destinatair.getNom() != null) {
                    existingDestinatair.setNom(destinatair.getNom());
                }
                if (destinatair.getPrenom() != null) {
                    existingDestinatair.setPrenom(destinatair.getPrenom());
                }
                if (destinatair.getRib() != null) {
                    existingDestinatair.setRib(destinatair.getRib());
                }

                return existingDestinatair;
            })
            .map(destinatairRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Destinatair> findAll() {
        log.debug("Request to get all Destinatairs");
        return destinatairRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Destinatair> findOne(Long id) {
        log.debug("Request to get Destinatair : {}", id);
        return destinatairRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Destinatair : {}", id);
        destinatairRepository.deleteById(id);
    }
}
