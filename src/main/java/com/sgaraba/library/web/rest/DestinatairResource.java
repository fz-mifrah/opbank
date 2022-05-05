package com.sgaraba.library.web.rest;

import com.sgaraba.library.domain.Destinatair;
import com.sgaraba.library.repository.DestinatairRepository;
import com.sgaraba.library.service.DestinatairService;
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
 * REST controller for managing {@link com.sgaraba.library.domain.Destinatair}.
 */
@RestController
@RequestMapping("/api")
public class DestinatairResource {

    private final Logger log = LoggerFactory.getLogger(DestinatairResource.class);

    private static final String ENTITY_NAME = "destinatair";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DestinatairService destinatairService;

    private final DestinatairRepository destinatairRepository;

    public DestinatairResource(DestinatairService destinatairService, DestinatairRepository destinatairRepository) {
        this.destinatairService = destinatairService;
        this.destinatairRepository = destinatairRepository;
    }

    /**
     * {@code POST  /destinatairs} : Create a new destinatair.
     *
     * @param destinatair the destinatair to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new destinatair, or with status {@code 400 (Bad Request)} if the destinatair has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/destinatairs")
    public ResponseEntity<Destinatair> createDestinatair(@Valid @RequestBody Destinatair destinatair) throws URISyntaxException {
        log.debug("REST request to save Destinatair : {}", destinatair);
        if (destinatair.getId() != null) {
            throw new BadRequestAlertException("A new destinatair cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Destinatair result = destinatairService.save(destinatair);
        return ResponseEntity
            .created(new URI("/api/destinatairs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /destinatairs/:id} : Updates an existing destinatair.
     *
     * @param id the id of the destinatair to save.
     * @param destinatair the destinatair to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated destinatair,
     * or with status {@code 400 (Bad Request)} if the destinatair is not valid,
     * or with status {@code 500 (Internal Server Error)} if the destinatair couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/destinatairs/{id}")
    public ResponseEntity<Destinatair> updateDestinatair(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Destinatair destinatair
    ) throws URISyntaxException {
        log.debug("REST request to update Destinatair : {}, {}", id, destinatair);
        if (destinatair.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, destinatair.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!destinatairRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Destinatair result = destinatairService.update(destinatair);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, destinatair.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /destinatairs/:id} : Partial updates given fields of an existing destinatair, field will ignore if it is null
     *
     * @param id the id of the destinatair to save.
     * @param destinatair the destinatair to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated destinatair,
     * or with status {@code 400 (Bad Request)} if the destinatair is not valid,
     * or with status {@code 404 (Not Found)} if the destinatair is not found,
     * or with status {@code 500 (Internal Server Error)} if the destinatair couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/destinatairs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Destinatair> partialUpdateDestinatair(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Destinatair destinatair
    ) throws URISyntaxException {
        log.debug("REST request to partial update Destinatair partially : {}, {}", id, destinatair);
        if (destinatair.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, destinatair.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!destinatairRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Destinatair> result = destinatairService.partialUpdate(destinatair);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, destinatair.getId().toString())
        );
    }

    /**
     * {@code GET  /destinatairs} : get all the destinatairs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of destinatairs in body.
     */
    @GetMapping("/destinatairs")
    public List<Destinatair> getAllDestinatairs() {
        log.debug("REST request to get all Destinatairs");
        return destinatairService.findAll();
    }

    /**
     * {@code GET  /destinatairs/:id} : get the "id" destinatair.
     *
     * @param id the id of the destinatair to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the destinatair, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/destinatairs/{id}")
    public ResponseEntity<Destinatair> getDestinatair(@PathVariable Long id) {
        log.debug("REST request to get Destinatair : {}", id);
        Optional<Destinatair> destinatair = destinatairService.findOne(id);
        return ResponseUtil.wrapOrNotFound(destinatair);
    }

    /**
     * {@code DELETE  /destinatairs/:id} : delete the "id" destinatair.
     *
     * @param id the id of the destinatair to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/destinatairs/{id}")
    public ResponseEntity<Void> deleteDestinatair(@PathVariable Long id) {
        log.debug("REST request to delete Destinatair : {}", id);
        destinatairService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
