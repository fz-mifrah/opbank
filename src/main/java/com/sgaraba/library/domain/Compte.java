package com.sgaraba.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Compte.
 */
@Entity
@Table(name = "compte")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Compte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "rib", nullable = false)
    private Long rib;

    @Column(name = "date_ouvertur")
    private Instant dateOuvertur;

    @NotNull
    @Column(name = "code", nullable = false)
    private Integer code;

    @OneToOne
    @JoinColumn(unique = true)
    private CarteBancaire nom;

    @JsonIgnoreProperties(value = { "nom" }, allowSetters = true)
    @OneToOne(mappedBy = "nom")
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties(value = { "countries" }, allowSetters = true)
    private Banque banque;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Compte id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRib() {
        return this.rib;
    }

    public Compte rib(Long rib) {
        this.setRib(rib);
        return this;
    }

    public void setRib(Long rib) {
        this.rib = rib;
    }

    public Instant getDateOuvertur() {
        return this.dateOuvertur;
    }

    public Compte dateOuvertur(Instant dateOuvertur) {
        this.setDateOuvertur(dateOuvertur);
        return this;
    }

    public void setDateOuvertur(Instant dateOuvertur) {
        this.dateOuvertur = dateOuvertur;
    }

    public Integer getCode() {
        return this.code;
    }

    public Compte code(Integer code) {
        this.setCode(code);
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CarteBancaire getNom() {
        return this.nom;
    }

    public void setNom(CarteBancaire carteBancaire) {
        this.nom = carteBancaire;
    }

    public Compte nom(CarteBancaire carteBancaire) {
        this.setNom(carteBancaire);
        return this;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        if (this.client != null) {
            this.client.setNom(null);
        }
        if (client != null) {
            client.setNom(this);
        }
        this.client = client;
    }

    public Compte client(Client client) {
        this.setClient(client);
        return this;
    }

    public Banque getBanque() {
        return this.banque;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    public Compte banque(Banque banque) {
        this.setBanque(banque);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Compte)) {
            return false;
        }
        return id != null && id.equals(((Compte) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Compte{" +
            "id=" + getId() +
            ", rib=" + getRib() +
            ", dateOuvertur='" + getDateOuvertur() + "'" +
            ", code=" + getCode() +
            "}";
    }
}
