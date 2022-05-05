package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.Destinatair;
import com.sgaraba.library.repository.DestinatairRepository;
import com.sgaraba.library.service.DestinatairService;
import com.sgaraba.library.service.dto.DestinatairDTO;
import com.sgaraba.library.service.mapper.DestinatairMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    private final DestinatairMapper destinatairMapper;

    public DestinatairServiceImpl(DestinatairRepository destinatairRepository, DestinatairMapper destinatairMapper) {
        this.destinatairRepository = destinatairRepository;
        this.destinatairMapper = destinatairMapper;
    }

    @Override
    public DestinatairDTO save(DestinatairDTO destinatairDTO) {
        log.debug("Request to save Destinatair : {}", destinatairDTO);
        Destinatair destinatair = destinatairMapper.toEntity(destinatairDTO);
        destinatair = destinatairRepository.save(destinatair);
        return destinatairMapper.toDto(destinatair);
    }

    @Override
    public DestinatairDTO update(DestinatairDTO destinatairDTO) {
        log.debug("Request to save Destinatair : {}", destinatairDTO);
        Destinatair destinatair = destinatairMapper.toEntity(destinatairDTO);
        destinatair = destinatairRepository.save(destinatair);
        return destinatairMapper.toDto(destinatair);
    }

    @Override
    public Optional<DestinatairDTO> partialUpdate(DestinatairDTO destinatairDTO) {
        log.debug("Request to partially update Destinatair : {}", destinatairDTO);

        return destinatairRepository
            .findById(destinatairDTO.getId())
            .map(existingDestinatair -> {
                destinatairMapper.partialUpdate(existingDestinatair, destinatairDTO);

                return existingDestinatair;
            })
            .map(destinatairRepository::save)
            .map(destinatairMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DestinatairDTO> findAll() {
        log.debug("Request to get all Destinatairs");
        return destinatairRepository.findAll().stream().map(destinatairMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DestinatairDTO> findOne(Long id) {
        log.debug("Request to get Destinatair : {}", id);
        return destinatairRepository.findById(id).map(destinatairMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Destinatair : {}", id);
        destinatairRepository.deleteById(id);
    }
}
