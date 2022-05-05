package com.sgaraba.library.service.impl;

import com.sgaraba.library.domain.Banque;
import com.sgaraba.library.repository.BanqueRepository;
import com.sgaraba.library.service.BanqueService;
import com.sgaraba.library.service.dto.BanqueDTO;
import com.sgaraba.library.service.mapper.BanqueMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    private final BanqueMapper banqueMapper;

    public BanqueServiceImpl(BanqueRepository banqueRepository, BanqueMapper banqueMapper) {
        this.banqueRepository = banqueRepository;
        this.banqueMapper = banqueMapper;
    }

    @Override
    public BanqueDTO save(BanqueDTO banqueDTO) {
        log.debug("Request to save Banque : {}", banqueDTO);
        Banque banque = banqueMapper.toEntity(banqueDTO);
        banque = banqueRepository.save(banque);
        return banqueMapper.toDto(banque);
    }

    @Override
    public BanqueDTO update(BanqueDTO banqueDTO) {
        log.debug("Request to save Banque : {}", banqueDTO);
        Banque banque = banqueMapper.toEntity(banqueDTO);
        banque = banqueRepository.save(banque);
        return banqueMapper.toDto(banque);
    }

    @Override
    public Optional<BanqueDTO> partialUpdate(BanqueDTO banqueDTO) {
        log.debug("Request to partially update Banque : {}", banqueDTO);

        return banqueRepository
            .findById(banqueDTO.getId())
            .map(existingBanque -> {
                banqueMapper.partialUpdate(existingBanque, banqueDTO);

                return existingBanque;
            })
            .map(banqueRepository::save)
            .map(banqueMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BanqueDTO> findAll() {
        log.debug("Request to get all Banques");
        return banqueRepository.findAll().stream().map(banqueMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BanqueDTO> findOne(Long id) {
        log.debug("Request to get Banque : {}", id);
        return banqueRepository.findById(id).map(banqueMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Banque : {}", id);
        banqueRepository.deleteById(id);
    }
}
