package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.Operation;
import com.sgaraba.library.repository.OperationRepository;
import com.sgaraba.library.service.OperationService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Operation}.
 */
@Service
@Transactional
public class OperationServiceImpl implements OperationService {

    private final Logger log = LoggerFactory.getLogger(OperationServiceImpl.class);

    private final OperationRepository operationRepository;

    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public Operation save(Operation operation) {
        log.debug("Request to save Operation : {}", operation);
        return operationRepository.save(operation);
    }

    @Override
    public Operation update(Operation operation) {
        log.debug("Request to save Operation : {}", operation);
        return operationRepository.save(operation);
    }

    @Override
    public Optional<Operation> partialUpdate(Operation operation) {
        log.debug("Request to partially update Operation : {}", operation);

        return operationRepository
            .findById(operation.getId())
            .map(existingOperation -> {
                if (operation.getNumOperation() != null) {
                    existingOperation.setNumOperation(operation.getNumOperation());
                }
                if (operation.getDate() != null) {
                    existingOperation.setDate(operation.getDate());
                }
                if (operation.getTypeOperatin() != null) {
                    existingOperation.setTypeOperatin(operation.getTypeOperatin());
                }
                if (operation.getEtatOperation() != null) {
                    existingOperation.setEtatOperation(operation.getEtatOperation());
                }
                if (operation.getMontant() != null) {
                    existingOperation.setMontant(operation.getMontant());
                }

                return existingOperation;
            })
            .map(operationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Operation> findAll() {
        log.debug("Request to get all Operations");
        return operationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Operation> findOne(Long id) {
        log.debug("Request to get Operation : {}", id);
        return operationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Operation : {}", id);
        operationRepository.deleteById(id);
    }
}
