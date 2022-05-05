package com.sgaraba.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
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

    @Column(name = "date_ouverture")
    private LocalDate dateOuverture;

    @NotNull
    @Column(name = "code", nullable = false)
    private Integer code;

    @JsonIgnoreProperties(value = { "compte" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    @OneToMany(mappedBy = "typeOperation")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private CarteBancaire rib;

    @JsonIgnoreProperties(value = { "compte" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    @OneToMany(mappedBy = "typeOperation")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "typeOperation", "typeOperation", "typeOperation", "typeOperation", "typeOperation" },
        allowSetters = true
    )
    private Set<Operation> ribs = new HashSet<>();

    @JsonIgnoreProperties(value = { "nom" }, allowSetters = true)
    @OneToOne(mappedBy = "nom")
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties(value = { "noms" }, allowSetters = true)
    private Banque dateOuverture;

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

    public LocalDate getDateOuverture() {
        return this.dateOuverture;
    }

    public Compte dateOuverture(LocalDate dateOuverture) {
        this.setDateOuverture(dateOuverture);
        return this;
    }

    public void setDateOuverture(LocalDate dateOuverture) {
        this.dateOuverture = dateOuverture;
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

    public CarteBancaire getRib() {
        return this.rib;
    }

    public void setRib(CarteBancaire carteBancaire) {
        this.rib = carteBancaire;
    }

    public Compte rib(CarteBancaire carteBancaire) {
        this.setRib(carteBancaire);
        return this;
    }

    public Set<Operation> getRibs() {
        return this.ribs;
    }

    public void setRibs(Set<Operation> operations) {
        if (this.ribs != null) {
            this.ribs.forEach(i -> i.setTypeOperation(null));
        }
        if (operations != null) {
            operations.forEach(i -> i.setTypeOperation(this));
        }
        this.ribs = operations;
    }

    public Compte ribs(Set<Operation> operations) {
        this.setRibs(operations);
        return this;
    }

    public Compte addRib(Operation operation) {
        this.ribs.add(operation);
        operation.setTypeOperation(this);
        return this;
    }

    public Compte removeRib(Operation operation) {
        this.ribs.remove(operation);
        operation.setTypeOperation(null);
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

    public Banque getDateOuverture() {
        return this.dateOuverture;
    }

    public void setDateOuverture(Banque banque) {
        this.dateOuverture = banque;
    }

    public Compte dateOuverture(Banque banque) {
        this.setDateOuverture(banque);
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
            ", dateOuverture='" + getDateOuverture() + "'" +
            ", code=" + getCode() +
            "}";
    }
}
