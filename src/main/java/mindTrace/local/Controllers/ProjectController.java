package mindTrace.local.Controllers;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Dtos.*;
import mindTrace.local.Enums.Status;
import mindTrace.local.Services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ProjectRespDTO saveProject(@RequestBody ProjectReqDTO project) {
        return projectService.saveProject(project);
    }

    @GetMapping
    public List<ProjectRespDTO> listAllProjects() {
        return projectService.listAllProjects();
    }

    @GetMapping("/{projectId}/files")
    public List<FileResponseDTO> listAllFilesInProject(@PathVariable Integer projectId) {
        return projectService.listAllFilesInProject(projectId);
    }

    @GetMapping("/{projectId}/tickets")
    public List<TicketResponseDTO> listAllTicketsInProject(@PathVariable Integer projectId) {
        return projectService.listAllTicketsInProject(projectId);
    }

    @GetMapping("/{projectId}")
    public ProjectRespDTO getProject(@PathVariable Integer projectId) {
        return projectService.getProject(projectId);
    }

    @GetMapping("/status/{status}")
    public List<ProjectRespDTO> listProjectsByStatus(@PathVariable Status status) {
        return projectService.listProjectsByStatus(status);
    }

}
