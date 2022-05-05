package com.sgaraba.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Recharge.
 */
@Entity
@Table(name = "recharge")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Recharge implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "num_tel", nullable = false)
    private Long numTel;

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

    public Recharge id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumTel() {
        return this.numTel;
    }

    public Recharge numTel(Long numTel) {
        this.setNumTel(numTel);
        return this;
    }

    public void setNumTel(Long numTel) {
        this.numTel = numTel;
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

    public Recharge operation(Operation operation) {
        this.setOperation(operation);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Recharge)) {
            return false;
        }
        return id != null && id.equals(((Recharge) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Recharge{" +
            "id=" + getId() +
            ", numTel=" + getNumTel() +
            "}";
    }
}
