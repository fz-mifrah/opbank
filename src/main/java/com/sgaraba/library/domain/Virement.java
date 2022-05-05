package com.sgaraba.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Virement.
 */
@Entity
@Table(name = "virement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Virement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "rib")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "rib" }, allowSetters = true)
    private Set<Destinatair> destinataires = new HashSet<>();

    @OneToMany(mappedBy = "numCompte")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "numCompte" }, allowSetters = true)
    private Set<Beneficiaire> beneficiaires = new HashSet<>();

    @JsonIgnoreProperties(
        value = { "typeOperation", "typeOperation", "typeOperation", "typeOperation", "typeOperation" },
        allowSetters = true
    )
    @OneToOne(mappedBy = "typeOperation")
    private Operation operation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Virement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public Virement description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Destinatair> getDestinataires() {
        return this.destinataires;
    }

    public void setDestinataires(Set<Destinatair> destinatairs) {
        if (this.destinataires != null) {
            this.destinataires.forEach(i -> i.setRib(null));
        }
        if (destinatairs != null) {
            destinatairs.forEach(i -> i.setRib(this));
        }
        this.destinataires = destinatairs;
    }

    public Virement destinataires(Set<Destinatair> destinatairs) {
        this.setDestinataires(destinatairs);
        return this;
    }

    public Virement addDestinataire(Destinatair destinatair) {
        this.destinataires.add(destinatair);
        destinatair.setRib(this);
        return this;
    }

    public Virement removeDestinataire(Destinatair destinatair) {
        this.destinataires.remove(destinatair);
        destinatair.setRib(null);
        return this;
    }

    public Set<Beneficiaire> getBeneficiaires() {
        return this.beneficiaires;
    }

    public void setBeneficiaires(Set<Beneficiaire> beneficiaires) {
        if (this.beneficiaires != null) {
            this.beneficiaires.forEach(i -> i.setNumCompte(null));
        }
        if (beneficiaires != null) {
            beneficiaires.forEach(i -> i.setNumCompte(this));
        }
        this.beneficiaires = beneficiaires;
    }

    public Virement beneficiaires(Set<Beneficiaire> beneficiaires) {
        this.setBeneficiaires(beneficiaires);
        return this;
    }

    public Virement addBeneficiaire(Beneficiaire beneficiaire) {
        this.beneficiaires.add(beneficiaire);
        beneficiaire.setNumCompte(this);
        return this;
    }

    public Virement removeBeneficiaire(Beneficiaire beneficiaire) {
        this.beneficiaires.remove(beneficiaire);
        beneficiaire.setNumCompte(null);
        return this;
    }

    public Operation getOperation() {
        return this.operation;
    }

    public void setOperation(Operation operation) {
        if (this.operation != null) {
            this.operation.setTypeOperation(null);
        }
        if (operation != null) {
            operation.setTypeOperation(this);
        }
        this.operation = operation;
    }

    public Virement operation(Operation operation) {
        this.setOperation(operation);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Virement)) {
            return false;
        }
        return id != null && id.equals(((Virement) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Virement{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
