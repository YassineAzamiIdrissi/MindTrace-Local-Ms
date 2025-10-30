package mindTrace.local.Services;

import mindTrace.local.Dtos.FileResponseDTO;
import mindTrace.local.Dtos.ProjectReqDTO;
import mindTrace.local.Dtos.ProjectRespDTO;

import java.util.List;

public interface ProjectService {
    ProjectRespDTO saveProject(ProjectReqDTO project);
    List<ProjectRespDTO> listAllProjects();
    List<FileResponseDTO> listAllFilesInProject(Integer projectId);
}
