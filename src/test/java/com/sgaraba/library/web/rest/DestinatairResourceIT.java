package com.sgaraba.library.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sgaraba.library.IntegrationTest;
import com.sgaraba.library.domain.Destinatair;
import com.sgaraba.library.repository.DestinatairRepository;
import com.sgaraba.library.service.dto.DestinatairDTO;
import com.sgaraba.library.service.mapper.DestinatairMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DestinatairResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DestinatairResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final Long DEFAULT_RIB = 1L;
    private static final Long UPDATED_RIB = 2L;

    private static final String ENTITY_API_URL = "/api/destinatairs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DestinatairRepository destinatairRepository;

    @Autowired
    private DestinatairMapper destinatairMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDestinatairMockMvc;

    private Destinatair destinatair;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Destinatair createEntity(EntityManager em) {
        Destinatair destinatair = new Destinatair().nom(DEFAULT_NOM).prenom(DEFAULT_PRENOM).rib(DEFAULT_RIB);
        return destinatair;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Destinatair createUpdatedEntity(EntityManager em) {
        Destinatair destinatair = new Destinatair().nom(UPDATED_NOM).prenom(UPDATED_PRENOM).rib(UPDATED_RIB);
        return destinatair;
    }

    @BeforeEach
    public void initTest() {
        destinatair = createEntity(em);
    }

    @Test
    @Transactional
    void createDestinatair() throws Exception {
        int databaseSizeBeforeCreate = destinatairRepository.findAll().size();
        // Create the Destinatair
        DestinatairDTO destinatairDTO = destinatairMapper.toDto(destinatair);
        restDestinatairMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(destinatairDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Destinatair in the database
        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeCreate + 1);
        Destinatair testDestinatair = destinatairList.get(destinatairList.size() - 1);
        assertThat(testDestinatair.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testDestinatair.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testDestinatair.getRib()).isEqualTo(DEFAULT_RIB);
    }

    @Test
    @Transactional
    void createDestinatairWithExistingId() throws Exception {
        // Create the Destinatair with an existing ID
        destinatair.setId(1L);
        DestinatairDTO destinatairDTO = destinatairMapper.toDto(destinatair);

        int databaseSizeBeforeCreate = destinatairRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDestinatairMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(destinatairDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Destinatair in the database
        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = destinatairRepository.findAll().size();
        // set the field null
        destinatair.setNom(null);

        // Create the Destinatair, which fails.
        DestinatairDTO destinatairDTO = destinatairMapper.toDto(destinatair);

        restDestinatairMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(destinatairDTO))
            )
            .andExpect(status().isBadRequest());

        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = destinatairRepository.findAll().size();
        // set the field null
        destinatair.setPrenom(null);

        // Create the Destinatair, which fails.
        DestinatairDTO destinatairDTO = destinatairMapper.toDto(destinatair);

        restDestinatairMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(destinatairDTO))
            )
            .andExpect(status().isBadRequest());

        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRibIsRequired() throws Exception {
        int databaseSizeBeforeTest = destinatairRepository.findAll().size();
        // set the field null
        destinatair.setRib(null);

        // Create the Destinatair, which fails.
        DestinatairDTO destinatairDTO = destinatairMapper.toDto(destinatair);

        restDestinatairMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(destinatairDTO))
            )
            .andExpect(status().isBadRequest());

        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDestinatairs() throws Exception {
        // Initialize the database
        destinatairRepository.saveAndFlush(destinatair);

        // Get all the destinatairList
        restDestinatairMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(destinatair.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].rib").value(hasItem(DEFAULT_RIB.intValue())));
    }

    @Test
    @Transactional
    void getDestinatair() throws Exception {
        // Initialize the database
        destinatairRepository.saveAndFlush(destinatair);

        // Get the destinatair
        restDestinatairMockMvc
            .perform(get(ENTITY_API_URL_ID, destinatair.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(destinatair.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.rib").value(DEFAULT_RIB.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDestinatair() throws Exception {
        // Get the destinatair
        restDestinatairMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDestinatair() throws Exception {
        // Initialize the database
        destinatairRepository.saveAndFlush(destinatair);

        int databaseSizeBeforeUpdate = destinatairRepository.findAll().size();

        // Update the destinatair
        Destinatair updatedDestinatair = destinatairRepository.findById(destinatair.getId()).get();
        // Disconnect from session so that the updates on updatedDestinatair are not directly saved in db
        em.detach(updatedDestinatair);
        updatedDestinatair.nom(UPDATED_NOM).prenom(UPDATED_PRENOM).rib(UPDATED_RIB);
        DestinatairDTO destinatairDTO = destinatairMapper.toDto(updatedDestinatair);

        restDestinatairMockMvc
            .perform(
                put(ENTITY_API_URL_ID, destinatairDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(destinatairDTO))
            )
            .andExpect(status().isOk());

        // Validate the Destinatair in the database
        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeUpdate);
        Destinatair testDestinatair = destinatairList.get(destinatairList.size() - 1);
        assertThat(testDestinatair.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testDestinatair.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testDestinatair.getRib()).isEqualTo(UPDATED_RIB);
    }

    @Test
    @Transactional
    void putNonExistingDestinatair() throws Exception {
        int databaseSizeBeforeUpdate = destinatairRepository.findAll().size();
        destinatair.setId(count.incrementAndGet());

        // Create the Destinatair
        DestinatairDTO destinatairDTO = destinatairMapper.toDto(destinatair);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDestinatairMockMvc
            .perform(
                put(ENTITY_API_URL_ID, destinatairDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(destinatairDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Destinatair in the database
        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDestinatair() throws Exception {
        int databaseSizeBeforeUpdate = destinatairRepository.findAll().size();
        destinatair.setId(count.incrementAndGet());

        // Create the Destinatair
        DestinatairDTO destinatairDTO = destinatairMapper.toDto(destinatair);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDestinatairMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(destinatairDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Destinatair in the database
        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDestinatair() throws Exception {
        int databaseSizeBeforeUpdate = destinatairRepository.findAll().size();
        destinatair.setId(count.incrementAndGet());

        // Create the Destinatair
        DestinatairDTO destinatairDTO = destinatairMapper.toDto(destinatair);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDestinatairMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(destinatairDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Destinatair in the database
        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDestinatairWithPatch() throws Exception {
        // Initialize the database
        destinatairRepository.saveAndFlush(destinatair);

        int databaseSizeBeforeUpdate = destinatairRepository.findAll().size();

        // Update the destinatair using partial update
        Destinatair partialUpdatedDestinatair = new Destinatair();
        partialUpdatedDestinatair.setId(destinatair.getId());

        partialUpdatedDestinatair.nom(UPDATED_NOM).rib(UPDATED_RIB);

        restDestinatairMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDestinatair.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDestinatair))
            )
            .andExpect(status().isOk());

        // Validate the Destinatair in the database
        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeUpdate);
        Destinatair testDestinatair = destinatairList.get(destinatairList.size() - 1);
        assertThat(testDestinatair.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testDestinatair.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testDestinatair.getRib()).isEqualTo(UPDATED_RIB);
    }

    @Test
    @Transactional
    void fullUpdateDestinatairWithPatch() throws Exception {
        // Initialize the database
        destinatairRepository.saveAndFlush(destinatair);

        int databaseSizeBeforeUpdate = destinatairRepository.findAll().size();

        // Update the destinatair using partial update
        Destinatair partialUpdatedDestinatair = new Destinatair();
        partialUpdatedDestinatair.setId(destinatair.getId());

        partialUpdatedDestinatair.nom(UPDATED_NOM).prenom(UPDATED_PRENOM).rib(UPDATED_RIB);

        restDestinatairMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDestinatair.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDestinatair))
            )
            .andExpect(status().isOk());

        // Validate the Destinatair in the database
        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeUpdate);
        Destinatair testDestinatair = destinatairList.get(destinatairList.size() - 1);
        assertThat(testDestinatair.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testDestinatair.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testDestinatair.getRib()).isEqualTo(UPDATED_RIB);
    }

    @Test
    @Transactional
    void patchNonExistingDestinatair() throws Exception {
        int databaseSizeBeforeUpdate = destinatairRepository.findAll().size();
        destinatair.setId(count.incrementAndGet());

        // Create the Destinatair
        DestinatairDTO destinatairDTO = destinatairMapper.toDto(destinatair);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDestinatairMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, destinatairDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(destinatairDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Destinatair in the database
        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDestinatair() throws Exception {
        int databaseSizeBeforeUpdate = destinatairRepository.findAll().size();
        destinatair.setId(count.incrementAndGet());

        // Create the Destinatair
        DestinatairDTO destinatairDTO = destinatairMapper.toDto(destinatair);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDestinatairMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(destinatairDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Destinatair in the database
        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDestinatair() throws Exception {
        int databaseSizeBeforeUpdate = destinatairRepository.findAll().size();
        destinatair.setId(count.incrementAndGet());

        // Create the Destinatair
        DestinatairDTO destinatairDTO = destinatairMapper.toDto(destinatair);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDestinatairMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(destinatairDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Destinatair in the database
        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDestinatair() throws Exception {
        // Initialize the database
        destinatairRepository.saveAndFlush(destinatair);

        int databaseSizeBeforeDelete = destinatairRepository.findAll().size();

        // Delete the destinatair
        restDestinatairMockMvc
            .perform(delete(ENTITY_API_URL_ID, destinatair.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Destinatair> destinatairList = destinatairRepository.findAll();
        assertThat(destinatairList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
