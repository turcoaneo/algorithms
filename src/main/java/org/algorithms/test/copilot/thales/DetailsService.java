package org.algorithms.test.copilot.thales;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class DetailsService {

    private final DetailsRepository repository;

    public DetailsService(DetailsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveDTO(DetailsDTO dto) {
        List<DetailsEntity> entities = DetailsChunkMapper.toEntities(dto);
        repository.saveAll(entities);
        System.out.println(dto.fullDetails());
        entities.forEach(e -> System.out.println(STR."\{e.getDetails()} - \{e.getId()}"));
    }

    public List<DetailsDTO> loadAllAsDTOs() {
        List<DetailsEntity> all = repository.findAll()
                .stream()
//                .sorted((a, b) -> Long.compare(a.getId(), b.getId()))
                .sorted(Comparator.comparingLong(DetailsEntity::getId))
                .toList();

        return DetailsAssembler.assemble(all);
    }
}