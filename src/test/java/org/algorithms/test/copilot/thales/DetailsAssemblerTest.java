package org.algorithms.test.copilot.thales;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DetailsAssemblerTest {

    @Test
    void testAssembly() {
        List<DetailsEntity> entities = List.of(
                new DetailsEntity("CHK-2"),
                new DetailsEntity("ABCDEFGHIJ"),
                new DetailsEntity("KLMNOPQRST")
        );

        List<DetailsDTO> dtoList = DetailsAssembler.assemble(entities);

        assertEquals(1, dtoList.size());
        assertEquals("ABCDEFGHIJKLMNOPQRST", dtoList.getFirst().fullDetails());
    }
}