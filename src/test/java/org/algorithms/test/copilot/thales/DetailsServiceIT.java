package org.algorithms.test.copilot.thales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DetailsServiceIT {

    @Autowired
    private DetailsService service;

    @Test
    void testSaveAndLoad() {
        DetailsDTO dto = new DetailsDTO(null, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
        service.saveDTO(dto);

        dto = new DetailsDTO(null, "Simple");
        service.saveDTO(dto);

        dto = new DetailsDTO(null, "More complicated");
        service.saveDTO(dto);

        List<DetailsDTO> result = service.loadAllAsDTOs();

        assertEquals(3, result.size());
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", result.getFirst().fullDetails());
        assertEquals("Simple", result.get(1).fullDetails());
        assertEquals("More complicated", result.getLast().fullDetails());
    }
}