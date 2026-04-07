package org.algorithms.test.copilot.thales;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DetailsChunkMapperTest {

    @Test
    void testChunking20Chars() {
        DetailsDTO dto = new DetailsDTO(null, "ABCDEFGHIJKLMNOPQRST");
        List<DetailsEntity> entities = DetailsChunkMapper.toEntities(dto);

        assertEquals(3, entities.size());
        assertEquals("CHK-2", entities.get(0).getDetails());
        assertEquals("ABCDEFGHIJ", entities.get(1).getDetails());
        assertEquals("KLMNOPQRST", entities.get(2).getDetails());
    }
}