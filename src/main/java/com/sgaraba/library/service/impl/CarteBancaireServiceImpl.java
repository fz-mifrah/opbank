package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.CarteBancaire;
import com.sgaraba.library.repository.CarteBancaireRepository;
import com.sgaraba.library.service.CarteBancaireService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CarteBancaire}.
 */
@Service
@Transactional
public class CarteBancaireServiceImpl implements CarteBancaireService {

    private final Logger log = LoggerFactory.getLogger(CarteBancaireServiceImpl.class);

    private final CarteBancaireRepository carteBancaireRepository;

    public CarteBancaireServiceImpl(CarteBancaireRepository carteBancaireRepository) {
        this.carteBancaireRepository = carteBancaireRepository;
    }

    @Override
    public CarteBancaire save(CarteBancaire carteBancaire) {
        log.debug("Request to save CarteBancaire : {}", carteBancaire);
        return carteBancaireRepository.save(carteBancaire);
    }

    @Override
    public CarteBancaire update(CarteBancaire carteBancaire) {
        log.debug("Request to save CarteBancaire : {}", carteBancaire);
        return carteBancaireRepository.save(carteBancaire);
    }

    @Override
    public Optional<CarteBancaire> partialUpdate(CarteBancaire carteBancaire) {
        log.debug("Request to partially update CarteBancaire : {}", carteBancaire);

        return carteBancaireRepository
            .findById(carteBancaire.getId())
            .map(existingCarteBancaire -> {
                if (carteBancaire.getNumCompte() != null) {
                    existingCarteBancaire.setNumCompte(carteBancaire.getNumCompte());
                }

                return existingCarteBancaire;
            })
            .map(carteBancaireRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarteBancaire> findAll() {
        log.debug("Request to get all CarteBancaires");
        return carteBancaireRepository.findAll();
    }

    /**
     *  Get all the carteBancaires where Compte is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CarteBancaire> findAllWhereCompteIsNull() {
        log.debug("Request to get all carteBancaires where Compte is null");
        return StreamSupport
            .stream(carteBancaireRepository.findAll().spliterator(), false)
            .filter(carteBancaire -> carteBancaire.getCompte() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CarteBancaire> findOne(Long id) {
        log.debug("Request to get CarteBancaire : {}", id);
        return carteBancaireRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CarteBancaire : {}", id);
        carteBancaireRepository.deleteById(id);
    }
}
