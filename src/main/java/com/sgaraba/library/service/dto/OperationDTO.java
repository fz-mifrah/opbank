package com.sgaraba.library.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sgaraba.library.domain.Operation} entity.
 */
public class OperationDTO implements Serializable {

    private Long id;

    @NotNull
    private String numOperation;

    private LocalDate date;

    private String typeOperatin;

    private String etatOperation;

    @NotNull
    private Double montant;

    private VirementDTO typeOperation;

    private TransferDTO typeOperation;

    private RechargeDTO typeOperation;

    private PaimentFactureDTO typeOperation;

    private CompteDTO typeOperation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumOperation() {
        return numOperation;
    }

    public void setNumOperation(String numOperation) {
        this.numOperation = numOperation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTypeOperatin() {
        return typeOperatin;
    }

    public void setTypeOperatin(String typeOperatin) {
        this.typeOperatin = typeOperatin;
    }

    public String getEtatOperation() {
        return etatOperation;
    }

    public void setEtatOperation(String etatOperation) {
        this.etatOperation = etatOperation;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public VirementDTO getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(VirementDTO typeOperation) {
        this.typeOperation = typeOperation;
    }

    public TransferDTO getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TransferDTO typeOperation) {
        this.typeOperation = typeOperation;
    }

    public RechargeDTO getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(RechargeDTO typeOperation) {
        this.typeOperation = typeOperation;
    }

    public PaimentFactureDTO getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(PaimentFactureDTO typeOperation) {
        this.typeOperation = typeOperation;
    }

    public CompteDTO getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(CompteDTO typeOperation) {
        this.typeOperation = typeOperation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperationDTO)) {
            return false;
        }

        OperationDTO operationDTO = (OperationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, operationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperationDTO{" +
            "id=" + getId() +
            ", numOperation='" + getNumOperation() + "'" +
            ", date='" + getDate() + "'" +
            ", typeOperatin='" + getTypeOperatin() + "'" +
            ", etatOperation='" + getEtatOperation() + "'" +
            ", montant=" + getMontant() +
            ", typeOperation=" + getTypeOperation() +
            ", typeOperation=" + getTypeOperation() +
            ", typeOperation=" + getTypeOperation() +
            ", typeOperation=" + getTypeOperation() +
            ", typeOperation=" + getTypeOperation() +
            "}";
    }
}
