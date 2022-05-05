package com.sgaraba.library.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperateurMapperTest {

    private OperateurMapper operateurMapper;

    @BeforeEach
    public void setUp() {
        operateurMapper = new OperateurMapperImpl();
    }
}
