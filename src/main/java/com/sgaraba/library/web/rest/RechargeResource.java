package com.sgaraba.library.web.rest;

import com.sgaraba.library.domain.Recharge;
import com.sgaraba.library.repository.RechargeRepository;
import com.sgaraba.library.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sgaraba.library.domain.Recharge}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RechargeResource {

    private final Logger log = LoggerFactory.getLogger(RechargeResource.class);

    private static final String ENTITY_NAME = "recharge";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RechargeRepository rechargeRepository;

    public RechargeResource(RechargeRepository rechargeRepository) {
        this.rechargeRepository = rechargeRepository;
    }

    /**
     * {@code POST  /recharges} : Create a new recharge.
     *
     * @param recharge the recharge to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recharge, or with status {@code 400 (Bad Request)} if the recharge has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/recharges")
    public ResponseEntity<Recharge> createRecharge(@Valid @RequestBody Recharge recharge) throws URISyntaxException {
        log.debug("REST request to save Recharge : {}", recharge);
        if (recharge.getId() != null) {
            throw new BadRequestAlertException("A new recharge cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Recharge result = rechargeRepository.save(recharge);
        return ResponseEntity
            .created(new URI("/api/recharges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /recharges/:id} : Updates an existing recharge.
     *
     * @param id the id of the recharge to save.
     * @param recharge the recharge to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recharge,
     * or with status {@code 400 (Bad Request)} if the recharge is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recharge couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/recharges/{id}")
    public ResponseEntity<Recharge> updateRecharge(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Recharge recharge
    ) throws URISyntaxException {
        log.debug("REST request to update Recharge : {}, {}", id, recharge);
        if (recharge.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, recharge.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rechargeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Recharge result = rechargeRepository.save(recharge);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recharge.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /recharges/:id} : Partial updates given fields of an existing recharge, field will ignore if it is null
     *
     * @param id the id of the recharge to save.
     * @param recharge the recharge to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recharge,
     * or with status {@code 400 (Bad Request)} if the recharge is not valid,
     * or with status {@code 404 (Not Found)} if the recharge is not found,
     * or with status {@code 500 (Internal Server Error)} if the recharge couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/recharges/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Recharge> partialUpdateRecharge(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Recharge recharge
    ) throws URISyntaxException {
        log.debug("REST request to partial update Recharge partially : {}, {}", id, recharge);
        if (recharge.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, recharge.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rechargeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Recharge> result = rechargeRepository
            .findById(recharge.getId())
            .map(existingRecharge -> {
                if (recharge.getNumTel() != null) {
                    existingRecharge.setNumTel(recharge.getNumTel());
                }

                return existingRecharge;
            })
            .map(rechargeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recharge.getId().toString())
        );
    }

    /**
     * {@code GET  /recharges} : get all the recharges.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recharges in body.
     */
    @GetMapping("/recharges")
    public List<Recharge> getAllRecharges() {
        log.debug("REST request to get all Recharges");
        return rechargeRepository.findAll();
    }

    /**
     * {@code GET  /recharges/:id} : get the "id" recharge.
     *
     * @param id the id of the recharge to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recharge, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/recharges/{id}")
    public ResponseEntity<Recharge> getRecharge(@PathVariable Long id) {
        log.debug("REST request to get Recharge : {}", id);
        Optional<Recharge> recharge = rechargeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(recharge);
    }

    /**
     * {@code DELETE  /recharges/:id} : delete the "id" recharge.
     *
     * @param id the id of the recharge to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/recharges/{id}")
    public ResponseEntity<Void> deleteRecharge(@PathVariable Long id) {
        log.debug("REST request to delete Recharge : {}", id);
        rechargeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
