package com.sgaraba.library.repository;

import com.sgaraba.library.domain.Destinatair;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Destinatair entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DestinatairRepository extends JpaRepository<Destinatair, Long> {}
