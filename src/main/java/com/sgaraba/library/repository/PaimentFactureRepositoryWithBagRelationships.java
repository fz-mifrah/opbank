package com.sgaraba.library.repository;

import com.sgaraba.library.domain.PaimentFacture;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface PaimentFactureRepositoryWithBagRelationships {
    Optional<PaimentFacture> fetchBagRelationships(Optional<PaimentFacture> paimentFacture);

    List<PaimentFacture> fetchBagRelationships(List<PaimentFacture> paimentFactures);

    Page<PaimentFacture> fetchBagRelationships(Page<PaimentFacture> paimentFactures);
}
