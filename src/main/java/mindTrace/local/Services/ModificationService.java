package mindTrace.local.Services;

import mindTrace.local.Dtos.ModificationRespDTO;

import java.util.List;

public interface ModificationService {
    List<ModificationRespDTO> getLastModifications();
}
