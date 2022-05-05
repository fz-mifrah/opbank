package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.Banque;
import com.sgaraba.library.repository.BanqueRepository;
import com.sgaraba.library.service.BanqueService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Banque}.
 */
@Service
@Transactional
public class BanqueServiceImpl implements BanqueService {

    private final Logger log = LoggerFactory.getLogger(BanqueServiceImpl.class);

    private final BanqueRepository banqueRepository;

    public BanqueServiceImpl(BanqueRepository banqueRepository) {
        this.banqueRepository = banqueRepository;
    }

    @Override
    public Banque save(Banque banque) {
        log.debug("Request to save Banque : {}", banque);
        return banqueRepository.save(banque);
    }

    @Override
    public Banque update(Banque banque) {
        log.debug("Request to save Banque : {}", banque);
        return banqueRepository.save(banque);
    }

    @Override
    public Optional<Banque> partialUpdate(Banque banque) {
        log.debug("Request to partially update Banque : {}", banque);

        return banqueRepository
            .findById(banque.getId())
            .map(existingBanque -> {
                if (banque.getNom() != null) {
                    existingBanque.setNom(banque.getNom());
                }
                if (banque.getAdresse() != null) {
                    existingBanque.setAdresse(banque.getAdresse());
                }
                if (banque.getEmail() != null) {
                    existingBanque.setEmail(banque.getEmail());
                }

                return existingBanque;
            })
            .map(banqueRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Banque> findAll() {
        log.debug("Request to get all Banques");
        return banqueRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Banque> findOne(Long id) {
        log.debug("Request to get Banque : {}", id);
        return banqueRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Banque : {}", id);
        banqueRepository.deleteById(id);
    }
}
