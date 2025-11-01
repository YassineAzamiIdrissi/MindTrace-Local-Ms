package mindTrace.local.ServicesImpl;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Dtos.ModificationRespDTO;
import mindTrace.local.GenMapper.Mapper;
import mindTrace.local.Repositories.ModificationRepository;
import mindTrace.local.Services.ModificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModificationServiceImpls implements ModificationService {
    private final ModificationRepository modificationRepository;

    @Override
    public List<ModificationRespDTO> getLastModifications() {
        return modificationRepository.findAllByOrderByCreatedDateDesc().
                stream().
                map(Mapper::fromEntityToModificationDTO).
                toList();
    }
}
