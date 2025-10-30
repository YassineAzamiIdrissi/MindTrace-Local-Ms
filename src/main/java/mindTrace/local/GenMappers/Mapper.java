package mindTrace.local.GenMappers;

import mindTrace.local.Dtos.*;
import mindTrace.local.Entities.*;

public class Mapper {
    public static User fromDtoToUserEntity(UserRegistrationDTO req) {
        return User.builder().
                firstname(req.getFirstname()).
                lastname(req.getLastname()).
                email(req.getEmail()).
                build();
    }
    public static Admin fromDtoToAdminEntity(UserRegistrationDTO req) {
        return Admin.builder().
                firstname(req.getFirstname()).
                lastname(req.getLastname()).
                email(req.getEmail()).
                build();
    }
    public static Project fromDtoToProjectEntity(ProjectReqDTO req) {
        return Project.builder().
                title(req.getName()).
                description(req.getDescription()).
                build();
    }
    public static ProjectRespDTO fromEntityProjectRespDTO(Project ent) {
        return ProjectRespDTO.builder().
                id(ent.getId()).
                name(ent.getTitle()).
                description(ent.getDescription()).
                creator(ent.getCreator().getFullName()).
                build();
    }
    public static FileResponseDTO fromEntityToFileResponseDTO(File ent) {
        return FileResponseDTO.
                builder().
                name(ent.getName()).
                projectName(ent.getProject().getTitle()).
                size(ent.getSize()).
                extension(ent.getExtension()).
                build();
    }
    public static TicketResponseDTO fromEntityToTicketResponseDTO(Ticket ticket) {
        return TicketResponseDTO.builder().
                name(ticket.getTitle()).
                project(ticket.getProject().getTitle()).
                creator(ticket.getUser().getFullName()).
                description(ticket.getDescription()).
                build();
    }
}
