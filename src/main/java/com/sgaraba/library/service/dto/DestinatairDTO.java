package com.sgaraba.library.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sgaraba.library.domain.Destinatair} entity.
 */
public class DestinatairDTO implements Serializable {

    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    @NotNull
    private Long rib;

    private VirementDTO rib;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Long getRib() {
        return rib;
    }

    public void setRib(Long rib) {
        this.rib = rib;
    }

    public VirementDTO getRib() {
        return rib;
    }

    public void setRib(VirementDTO rib) {
        this.rib = rib;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DestinatairDTO)) {
            return false;
        }

        DestinatairDTO destinatairDTO = (DestinatairDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, destinatairDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DestinatairDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", rib=" + getRib() +
            ", rib=" + getRib() +
            "}";
    }
}
