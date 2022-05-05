package com.sgaraba.library.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.sgaraba.library.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DestinatairDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DestinatairDTO.class);
        DestinatairDTO destinatairDTO1 = new DestinatairDTO();
        destinatairDTO1.setId(1L);
        DestinatairDTO destinatairDTO2 = new DestinatairDTO();
        assertThat(destinatairDTO1).isNotEqualTo(destinatairDTO2);
        destinatairDTO2.setId(destinatairDTO1.getId());
        assertThat(destinatairDTO1).isEqualTo(destinatairDTO2);
        destinatairDTO2.setId(2L);
        assertThat(destinatairDTO1).isNotEqualTo(destinatairDTO2);
        destinatairDTO1.setId(null);
        assertThat(destinatairDTO1).isNotEqualTo(destinatairDTO2);
    }
}
