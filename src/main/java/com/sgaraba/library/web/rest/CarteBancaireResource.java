package com.sgaraba.library.web.rest;

import com.sgaraba.library.domain.CarteBancaire;
import com.sgaraba.library.repository.CarteBancaireRepository;
import com.sgaraba.library.service.CarteBancaireService;
import com.sgaraba.library.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sgaraba.library.domain.CarteBancaire}.
 */
@RestController
@RequestMapping("/api")
public class CarteBancaireResource {

    private final Logger log = LoggerFactory.getLogger(CarteBancaireResource.class);

    private static final String ENTITY_NAME = "carteBancaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarteBancaireService carteBancaireService;

    private final CarteBancaireRepository carteBancaireRepository;

    public CarteBancaireResource(CarteBancaireService carteBancaireService, CarteBancaireRepository carteBancaireRepository) {
        this.carteBancaireService = carteBancaireService;
        this.carteBancaireRepository = carteBancaireRepository;
    }

    /**
     * {@code POST  /carte-bancaires} : Create a new carteBancaire.
     *
     * @param carteBancaire the carteBancaire to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new carteBancaire, or with status {@code 400 (Bad Request)} if the carteBancaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/carte-bancaires")
    public ResponseEntity<CarteBancaire> createCarteBancaire(@Valid @RequestBody CarteBancaire carteBancaire) throws URISyntaxException {
        log.debug("REST request to save CarteBancaire : {}", carteBancaire);
        if (carteBancaire.getId() != null) {
            throw new BadRequestAlertException("A new carteBancaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CarteBancaire result = carteBancaireService.save(carteBancaire);
        return ResponseEntity
            .created(new URI("/api/carte-bancaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /carte-bancaires/:id} : Updates an existing carteBancaire.
     *
     * @param id the id of the carteBancaire to save.
     * @param carteBancaire the carteBancaire to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carteBancaire,
     * or with status {@code 400 (Bad Request)} if the carteBancaire is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carteBancaire couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/carte-bancaires/{id}")
    public ResponseEntity<CarteBancaire> updateCarteBancaire(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CarteBancaire carteBancaire
    ) throws URISyntaxException {
        log.debug("REST request to update CarteBancaire : {}, {}", id, carteBancaire);
        if (carteBancaire.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carteBancaire.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carteBancaireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CarteBancaire result = carteBancaireService.update(carteBancaire);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carteBancaire.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /carte-bancaires/:id} : Partial updates given fields of an existing carteBancaire, field will ignore if it is null
     *
     * @param id the id of the carteBancaire to save.
     * @param carteBancaire the carteBancaire to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carteBancaire,
     * or with status {@code 400 (Bad Request)} if the carteBancaire is not valid,
     * or with status {@code 404 (Not Found)} if the carteBancaire is not found,
     * or with status {@code 500 (Internal Server Error)} if the carteBancaire couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/carte-bancaires/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CarteBancaire> partialUpdateCarteBancaire(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CarteBancaire carteBancaire
    ) throws URISyntaxException {
        log.debug("REST request to partial update CarteBancaire partially : {}, {}", id, carteBancaire);
        if (carteBancaire.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carteBancaire.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carteBancaireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CarteBancaire> result = carteBancaireService.partialUpdate(carteBancaire);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carteBancaire.getId().toString())
        );
    }

    /**
     * {@code GET  /carte-bancaires} : get all the carteBancaires.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carteBancaires in body.
     */
    @GetMapping("/carte-bancaires")
    public ResponseEntity<List<CarteBancaire>> getAllCarteBancaires(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false) String filter
    ) {
        if ("compte-is-null".equals(filter)) {
            log.debug("REST request to get all CarteBancaires where compte is null");
            return new ResponseEntity<>(carteBancaireService.findAllWhereCompteIsNull(), HttpStatus.OK);
        }
        log.debug("REST request to get a page of CarteBancaires");
        Page<CarteBancaire> page = carteBancaireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /carte-bancaires/:id} : get the "id" carteBancaire.
     *
     * @param id the id of the carteBancaire to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carteBancaire, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/carte-bancaires/{id}")
    public ResponseEntity<CarteBancaire> getCarteBancaire(@PathVariable Long id) {
        log.debug("REST request to get CarteBancaire : {}", id);
        Optional<CarteBancaire> carteBancaire = carteBancaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carteBancaire);
    }

    /**
     * {@code DELETE  /carte-bancaires/:id} : delete the "id" carteBancaire.
     *
     * @param id the id of the carteBancaire to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/carte-bancaires/{id}")
    public ResponseEntity<Void> deleteCarteBancaire(@PathVariable Long id) {
        log.debug("REST request to delete CarteBancaire : {}", id);
        carteBancaireService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
