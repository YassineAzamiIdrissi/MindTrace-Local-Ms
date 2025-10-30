package mindTrace.local.ServicesImpl;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Constants.ExceptionsMessages;
import mindTrace.local.Dtos.ProjectReqDTO;
import mindTrace.local.Dtos.ProjectRespDTO;
import mindTrace.local.Entities.Admin;
import mindTrace.local.Entities.Project;
import mindTrace.local.GenMappers.Mapper;
import mindTrace.local.Repositories.AdminRepository;
import mindTrace.local.Repositories.ProjectRepository;
import mindTrace.local.Services.ProjectService;
import org.springframework.stereotype.Service;

import static mindTrace.local.Constants.ExceptionsMessages.*;
import static mindTrace.local.GenMappers.Mapper.fromEntityProjectRespDTO;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final AdminRepository adminRepository;

    private void validateProjectData(ProjectReqDTO project) throws RuntimeException {
        if(project.getName() == null || project.getName().isEmpty()) {
            throw new RuntimeException(PROJECT_NAME_REQUIRED);
        }
        if(project.getDescription() == null || project.getDescription().isEmpty()) {
            throw new RuntimeException(PROJECT_DESCRIPTION_REQUIRED);
        }
    }
    @Override
    public ProjectRespDTO saveProject(ProjectReqDTO project) {
        validateProjectData(project);
        Admin admin = adminRepository.findByEmail(project.getAdminEmail()).
                orElseThrow(() -> new
                        RuntimeException(ADMIN_NOT_FOUND));
        Project entity = Mapper.fromDtoToProjectEntity(project);
        entity.setCreator(admin);
        Project saved = projectRepository.save(entity);
        return fromEntityProjectRespDTO(saved);
    }
}
