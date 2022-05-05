package com.sgaraba.library.web.rest;

import com.sgaraba.library.domain.Operateur;
import com.sgaraba.library.repository.OperateurRepository;
import com.sgaraba.library.service.OperateurService;
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
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sgaraba.library.domain.Operateur}.
 */
@RestController
@RequestMapping("/api")
public class OperateurResource {

    private final Logger log = LoggerFactory.getLogger(OperateurResource.class);

    private static final String ENTITY_NAME = "operateur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperateurService operateurService;

    private final OperateurRepository operateurRepository;

    public OperateurResource(OperateurService operateurService, OperateurRepository operateurRepository) {
        this.operateurService = operateurService;
        this.operateurRepository = operateurRepository;
    }

    /**
     * {@code POST  /operateurs} : Create a new operateur.
     *
     * @param operateur the operateur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operateur, or with status {@code 400 (Bad Request)} if the operateur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/operateurs")
    public ResponseEntity<Operateur> createOperateur(@Valid @RequestBody Operateur operateur) throws URISyntaxException {
        log.debug("REST request to save Operateur : {}", operateur);
        if (operateur.getId() != null) {
            throw new BadRequestAlertException("A new operateur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Operateur result = operateurService.save(operateur);
        return ResponseEntity
            .created(new URI("/api/operateurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /operateurs/:id} : Updates an existing operateur.
     *
     * @param id the id of the operateur to save.
     * @param operateur the operateur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operateur,
     * or with status {@code 400 (Bad Request)} if the operateur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operateur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/operateurs/{id}")
    public ResponseEntity<Operateur> updateOperateur(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Operateur operateur
    ) throws URISyntaxException {
        log.debug("REST request to update Operateur : {}, {}", id, operateur);
        if (operateur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operateur.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operateurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Operateur result = operateurService.update(operateur);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operateur.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /operateurs/:id} : Partial updates given fields of an existing operateur, field will ignore if it is null
     *
     * @param id the id of the operateur to save.
     * @param operateur the operateur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operateur,
     * or with status {@code 400 (Bad Request)} if the operateur is not valid,
     * or with status {@code 404 (Not Found)} if the operateur is not found,
     * or with status {@code 500 (Internal Server Error)} if the operateur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/operateurs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Operateur> partialUpdateOperateur(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Operateur operateur
    ) throws URISyntaxException {
        log.debug("REST request to partial update Operateur partially : {}, {}", id, operateur);
        if (operateur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operateur.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operateurRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Operateur> result = operateurService.partialUpdate(operateur);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operateur.getId().toString())
        );
    }

    /**
     * {@code GET  /operateurs} : get all the operateurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operateurs in body.
     */
    @GetMapping("/operateurs")
    public List<Operateur> getAllOperateurs() {
        log.debug("REST request to get all Operateurs");
        return operateurService.findAll();
    }

    /**
     * {@code GET  /operateurs/:id} : get the "id" operateur.
     *
     * @param id the id of the operateur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operateur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/operateurs/{id}")
    public ResponseEntity<Operateur> getOperateur(@PathVariable Long id) {
        log.debug("REST request to get Operateur : {}", id);
        Optional<Operateur> operateur = operateurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(operateur);
    }

    /**
     * {@code DELETE  /operateurs/:id} : delete the "id" operateur.
     *
     * @param id the id of the operateur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/operateurs/{id}")
    public ResponseEntity<Void> deleteOperateur(@PathVariable Long id) {
        log.debug("REST request to delete Operateur : {}", id);
        operateurService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
