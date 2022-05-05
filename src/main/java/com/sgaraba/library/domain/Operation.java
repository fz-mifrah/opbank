package com.sgaraba.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Operation.
 */
@Entity
@Table(name = "operation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "num_operation", nullable = false)
    private String numOperation;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "type_operatin")
    private String typeOperatin;

    @Column(name = "etat_operation")
    private String etatOperation;

    @NotNull
    @Column(name = "montant", nullable = false)
    private Double montant;

    @JsonIgnoreProperties(value = { "operation" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    @JsonIgnoreProperties(value = { "operation" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    @ManyToOne
    private Virement typeOperation;

    @JsonIgnoreProperties(value = { "operation" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    @JsonIgnoreProperties(value = { "operation" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    @ManyToOne
    private Transfer typeOperation;

    @JsonIgnoreProperties(value = { "operation" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    @JsonIgnoreProperties(value = { "operation" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    @ManyToOne
    @JsonIgnoreProperties(value = { "rib", "ribs", "client", "dateOuverture" }, allowSetters = true)
    private Compte typeOperation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Operation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumOperation() {
        return this.numOperation;
    }

    public Operation numOperation(String numOperation) {
        this.setNumOperation(numOperation);
        return this;
    }

    public void setNumOperation(String numOperation) {
        this.numOperation = numOperation;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Operation date(LocalDate date) {
        this.setDate(date);
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTypeOperatin() {
        return this.typeOperatin;
    }

    public Operation typeOperatin(String typeOperatin) {
        this.setTypeOperatin(typeOperatin);
        return this;
    }

    public void setTypeOperatin(String typeOperatin) {
        this.typeOperatin = typeOperatin;
    }

    public String getEtatOperation() {
        return this.etatOperation;
    }

    public Operation etatOperation(String etatOperation) {
        this.setEtatOperation(etatOperation);
        return this;
    }

    public void setEtatOperation(String etatOperation) {
        this.etatOperation = etatOperation;
    }

    public Double getMontant() {
        return this.montant;
    }

    public Operation montant(Double montant) {
        this.setMontant(montant);
        return this;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Virement getTypeOperation() {
        return this.typeOperation;
    }

    public void setTypeOperation(Virement virement) {
        this.typeOperation = virement;
    }

    public Operation typeOperation(Virement virement) {
        this.setTypeOperation(virement);
        return this;
    }

    public Transfer getTypeOperation() {
        return this.typeOperation;
    }

    public void setTypeOperation(Transfer transfer) {
        this.typeOperation = transfer;
    }

    public Operation typeOperation(Transfer transfer) {
        this.setTypeOperation(transfer);
        return this;
    }

    public Compte getTypeOperation() {
        return this.typeOperation;
    }

    public void setTypeOperation(Compte compte) {
        this.typeOperation = compte;
    }

    public Operation typeOperation(Compte compte) {
        this.setTypeOperation(compte);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Operation)) {
            return false;
        }
        return id != null && id.equals(((Operation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Operation{" +
            "id=" + getId() +
            ", numOperation='" + getNumOperation() + "'" +
            ", date='" + getDate() + "'" +
            ", typeOperatin='" + getTypeOperatin() + "'" +
            ", etatOperation='" + getEtatOperation() + "'" +
            ", montant=" + getMontant() +
            "}";
    }
}
