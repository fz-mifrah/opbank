package com.sgaraba.library.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DestinatairMapperTest {

    private DestinatairMapper destinatairMapper;

    @BeforeEach
    public void setUp() {
        destinatairMapper = new DestinatairMapperImpl();
    }
}
