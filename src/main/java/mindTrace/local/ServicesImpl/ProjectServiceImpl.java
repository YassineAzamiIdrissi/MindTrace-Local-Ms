package mindTrace.local.ServicesImpl;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Dtos.FileResponseDTO;
import mindTrace.local.Dtos.ProjectReqDTO;
import mindTrace.local.Dtos.ProjectRespDTO;
import mindTrace.local.Dtos.TicketResponseDTO;
import mindTrace.local.Entities.Admin;
import mindTrace.local.Entities.File;
import mindTrace.local.Entities.Project;
import mindTrace.local.GenMapper.Mapper;
import mindTrace.local.Repositories.AdminRepository;
import mindTrace.local.Repositories.ProjectRepository;
import mindTrace.local.Services.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

import static mindTrace.local.Constants.ExceptionsMessages.*;
import static mindTrace.local.GenMapper.Mapper.fromEntityProjectRespDTO;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final AdminRepository adminRepository;
    // private final FileRepository fileRepository;
    // private final TicketRepository ticketRepository;

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

    @Override
    public List<ProjectRespDTO> listAllProjects() {
        return projectRepository.findAll().
                stream().
                map(Mapper::fromEntityProjectRespDTO).
                toList();
    }

    @Override
    public List<FileResponseDTO> listAllFilesInProject(Integer projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new RuntimeException(PROJECT_NOT_FOUND));
        List<File> innerFiles =
                project.getAssociatedFiles();
        return innerFiles.
                stream().
                map(Mapper::fromEntityToFileResponseDTO).
                toList();
    }

    @Override
    public List<TicketResponseDTO> listAllTicketsInProject
            (Integer projectId) {
        Project concernedProject =
                projectRepository.findById(projectId).
                        orElseThrow(()-> new RuntimeException(PROJECT_NOT_FOUND));
        return concernedProject.getTickets().stream().
                map(Mapper::fromEntityToTicketResponseDTO).
                toList();
    }

    @Override
    public ProjectRespDTO getProject(Integer projectId) {
        return projectRepository.findById(projectId).
                map(Mapper::fromEntityProjectRespDTO).
                orElseThrow(()-> new RuntimeException(PROJECT_NOT_FOUND));
    }
}
