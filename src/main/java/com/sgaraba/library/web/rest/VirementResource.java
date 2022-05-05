package com.sgaraba.library.web.rest;

import com.sgaraba.library.domain.Virement;
import com.sgaraba.library.repository.VirementRepository;
import com.sgaraba.library.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sgaraba.library.domain.Virement}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class VirementResource {

    private final Logger log = LoggerFactory.getLogger(VirementResource.class);

    private static final String ENTITY_NAME = "virement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VirementRepository virementRepository;

    public VirementResource(VirementRepository virementRepository) {
        this.virementRepository = virementRepository;
    }

    /**
     * {@code POST  /virements} : Create a new virement.
     *
     * @param virement the virement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new virement, or with status {@code 400 (Bad Request)} if the virement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/virements")
    public ResponseEntity<Virement> createVirement(@RequestBody Virement virement) throws URISyntaxException {
        log.debug("REST request to save Virement : {}", virement);
        if (virement.getId() != null) {
            throw new BadRequestAlertException("A new virement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Virement result = virementRepository.save(virement);
        return ResponseEntity
            .created(new URI("/api/virements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /virements/:id} : Updates an existing virement.
     *
     * @param id the id of the virement to save.
     * @param virement the virement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated virement,
     * or with status {@code 400 (Bad Request)} if the virement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the virement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/virements/{id}")
    public ResponseEntity<Virement> updateVirement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Virement virement
    ) throws URISyntaxException {
        log.debug("REST request to update Virement : {}, {}", id, virement);
        if (virement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, virement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!virementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Virement result = virementRepository.save(virement);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, virement.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /virements/:id} : Partial updates given fields of an existing virement, field will ignore if it is null
     *
     * @param id the id of the virement to save.
     * @param virement the virement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated virement,
     * or with status {@code 400 (Bad Request)} if the virement is not valid,
     * or with status {@code 404 (Not Found)} if the virement is not found,
     * or with status {@code 500 (Internal Server Error)} if the virement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/virements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Virement> partialUpdateVirement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Virement virement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Virement partially : {}, {}", id, virement);
        if (virement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, virement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!virementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Virement> result = virementRepository
            .findById(virement.getId())
            .map(existingVirement -> {
                if (virement.getDescription() != null) {
                    existingVirement.setDescription(virement.getDescription());
                }

                return existingVirement;
            })
            .map(virementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, virement.getId().toString())
        );
    }

    /**
     * {@code GET  /virements} : get all the virements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of virements in body.
     */
    @GetMapping("/virements")
    public List<Virement> getAllVirements() {
        log.debug("REST request to get all Virements");
        return virementRepository.findAll();
    }

    /**
     * {@code GET  /virements/:id} : get the "id" virement.
     *
     * @param id the id of the virement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the virement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/virements/{id}")
    public ResponseEntity<Virement> getVirement(@PathVariable Long id) {
        log.debug("REST request to get Virement : {}", id);
        Optional<Virement> virement = virementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(virement);
    }

    /**
     * {@code DELETE  /virements/:id} : delete the "id" virement.
     *
     * @param id the id of the virement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/virements/{id}")
    public ResponseEntity<Void> deleteVirement(@PathVariable Long id) {
        log.debug("REST request to delete Virement : {}", id);
        virementRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
