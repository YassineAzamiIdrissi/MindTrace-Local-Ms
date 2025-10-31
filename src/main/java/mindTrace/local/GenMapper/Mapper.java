package mindTrace.local.GenMapper;

import mindTrace.local.Dtos.*;
import mindTrace.local.Entities.*;
import mindTrace.local.Enums.Status;

import static mindTrace.local.Enums.Status.STARTING;

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
                status(STARTING).
                description(req.getDescription()).
                build();
    }
    public static ProjectRespDTO fromEntityProjectRespDTO(Project ent) {
        return ProjectRespDTO.builder().
                id(ent.getId()).
                innerTickets(ent.getTickets() != null ? ent.getTickets().size() : 0).
                innerFiles(ent.getAssociatedFiles() != null ? ent.getAssociatedFiles().size() : 0).
                status(ent.getStatus()).
                name(ent.getTitle()).
                createdAt(ent.getCreatedDate()).
                description(ent.getDescription()).
                creator(ent.getCreator().getFullName()).
                build();
    }
    public static FileResponseDTO fromEntityToFileResponseDTO(File ent) {
        return FileResponseDTO.
                builder().
                name(ent.getName()).
                size(ent.getSize()).
                createdAt(ent.getCreatedDate()).
                projectName(ent.getProject().getTitle()).
                size(ent.getSize()).
                url(ent.getUrl()).
                extension(ent.getExtension()).
                build();
    }
    public static TicketResponseDTO fromEntityToTicketResponseDTO(Ticket ticket) {
        return TicketResponseDTO.builder().
                id(ticket.getId()).
                status(ticket.getStatus()).
                name(ticket.getTitle()).
                project(ticket.getProject().getTitle()).
                creator(ticket.getUser().getFullName()).
                description(ticket.getDescription()).
                build();
    }
    public static TicketResponseDTO fromVersionToTicketResponseDTO(TicketVersion version) {
        return TicketResponseDTO.builder().
                id(version.getTicket().getId()).
                name(version.getTitle()).
                description(version.getDescription()).
                status(version.getStatus()).
                creator(version.getTicket().getUser().getFullName()).
                project(version.getTicket().getProject().getTitle()).
                build();
    }
    public static MessageDTO fromEntityToMessageDTO(Message ent) {
        return MessageDTO.builder().
                userId(ent.getUser() == null ? 0 : ent.getUser().getId()).
                projectId(ent.getProject().getId()).
                content(ent.getContent()).
                sentAt(ent.getCreatedDate()).
                build();
    }
}
