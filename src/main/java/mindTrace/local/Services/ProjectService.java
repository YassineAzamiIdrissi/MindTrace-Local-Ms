package mindTrace.local.Services;

import mindTrace.local.Dtos.ProjectReqDTO;
import mindTrace.local.Dtos.ProjectRespDTO;

public interface ProjectService {
    ProjectRespDTO saveProject(ProjectReqDTO project);
}
