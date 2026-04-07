package org.algorithms.test.copilot.thales;

import java.util.ArrayList;
import java.util.List;

public class DetailsChunkMapper {

    private static final int MAX_LEN = 10;

    public static List<DetailsEntity> toEntities(DetailsDTO dto) {
        String text = dto.fullDetails();

        if (text.length() <= MAX_LEN) {
            return List.of(new DetailsEntity(text));
        }

        List<String> chunks = splitIntoChunks(text);
        List<DetailsEntity> entities = new ArrayList<>();

        // Header: CHK-N
        entities.add(new DetailsEntity(STR."CHK-\{chunks.size()}"));

        // Add chunks
        for (String chunk : chunks) {
            entities.add(new DetailsEntity(chunk));
        }

        return entities;
    }

    private static List<String> splitIntoChunks(String s) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i += DetailsChunkMapper.MAX_LEN) {
            list.add(s.substring(i, Math.min(s.length(), i + DetailsChunkMapper.MAX_LEN)));
        }
        return list;
    }
}