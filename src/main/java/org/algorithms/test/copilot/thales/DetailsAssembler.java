package org.algorithms.test.copilot.thales;

import java.util.ArrayList;
import java.util.List;

public class DetailsAssembler {

    public static List<DetailsDTO> assemble(List<DetailsEntity> entities) {
        List<DetailsDTO> result = new ArrayList<>();

        for (int i = 0; i < entities.size(); i++) {
            DetailsEntity e = entities.get(i);
            String d = e.getDetails();

            if (d.startsWith("CHK-")) {
                int count = Integer.parseInt(d.substring(4));
                StringBuilder sb = new StringBuilder();

                for (int j = 1; j <= count; j++) {
                    sb.append(entities.get(i + j).getDetails());
                }

                result.add(new DetailsDTO(e.getId(), sb.toString()));
                i += count;
            } else {
                result.add(new DetailsDTO(e.getId(), d));
            }
        }

        return result;
    }
}