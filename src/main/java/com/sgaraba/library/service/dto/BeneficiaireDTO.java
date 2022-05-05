package com.sgaraba.library.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sgaraba.library.domain.Beneficiaire} entity.
 */
public class BeneficiaireDTO implements Serializable {

    private Long id;

    @NotNull
    private String nomPrenom;

    @NotNull
    private Long numCompte;

    private VirementDTO numCompte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public Long getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(Long numCompte) {
        this.numCompte = numCompte;
    }

    public VirementDTO getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(VirementDTO numCompte) {
        this.numCompte = numCompte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BeneficiaireDTO)) {
            return false;
        }

        BeneficiaireDTO beneficiaireDTO = (BeneficiaireDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, beneficiaireDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BeneficiaireDTO{" +
            "id=" + getId() +
            ", nomPrenom='" + getNomPrenom() + "'" +
            ", numCompte=" + getNumCompte() +
            ", numCompte=" + getNumCompte() +
            "}";
    }
}
