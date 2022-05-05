package com.sgaraba.library.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.sgaraba.library.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DestinatairTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Destinatair.class);
        Destinatair destinatair1 = new Destinatair();
        destinatair1.setId(1L);
        Destinatair destinatair2 = new Destinatair();
        destinatair2.setId(destinatair1.getId());
        assertThat(destinatair1).isEqualTo(destinatair2);
        destinatair2.setId(2L);
        assertThat(destinatair1).isNotEqualTo(destinatair2);
        destinatair1.setId(null);
        assertThat(destinatair1).isNotEqualTo(destinatair2);
    }
}
