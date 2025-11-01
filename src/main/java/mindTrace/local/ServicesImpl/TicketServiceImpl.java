package mindTrace.local.ServicesImpl;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Dtos.TicketReqDTO;
import mindTrace.local.Dtos.TicketResponseDTO;
import mindTrace.local.Entities.*;
import mindTrace.local.Enums.Status;
import mindTrace.local.GenMapper.Mapper;
import mindTrace.local.Repositories.ModificationRepository;
import mindTrace.local.Repositories.ProjectRepository;
import mindTrace.local.Repositories.TicketRepository;
import mindTrace.local.Repositories.UserRepository;
import mindTrace.local.Services.TicketService;
import mindTrace.local.Services.TicketVersionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static mindTrace.local.Constants.ExceptionsMessages.*;
import static mindTrace.local.Enums.Status.STARTING;
import static mindTrace.local.GenMapper.Mapper.fromEntityToTicketResponseDTO;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TicketVersionRepository ticketVersionRepository;
    private final ModificationRepository modifRepository;
    private void validateTicketData(TicketReqDTO dto) {
        if(dto.getDescription() == null || dto.getDescription().isEmpty()) {
            throw new RuntimeException(TICKET_DESCRIPTION_REQUIRED);
        }
        if(dto.getTitle() == null || dto.getTitle().isEmpty()) {
            throw new RuntimeException(TICKET_TITLE_REQUIRED);
        }
        if(dto.getProjectId() == null || dto.getProjectId() == 0) {
               throw new RuntimeException(TICKET_PROJECT_REQUIRED);
        }

        if(dto.getUserId() == null || dto.getUserId() == 0) {
            throw new RuntimeException(TICKET_CREATOR_REQUIRED);
        }
        if(dto.getStatus() == null) {
            throw new RuntimeException(TICKET_STATUS_REQUIRED);
        }
    }

    @Override
    public TicketResponseDTO saveTicket
            (TicketReqDTO req) {
        validateTicketData(req);
        Integer projectId = req.getProjectId();
        Integer userId = req.getUserId();
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
        Project project = projectRepository.findById(projectId).
                orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
        Ticket ticket = Ticket.builder().
                title(req.getTitle()).
                project(project).
                user(user).
                description(req.getDescription()).
                status(STARTING).
                build();
        return fromEntityToTicketResponseDTO(ticketRepository.save(ticket));
    }

    @Override
    public TicketResponseDTO updateTicket(TicketReqDTO req) {
        validateTicketData(req);
        String modifsDesc = "The ";
        boolean absoluteChange = false;
        Integer ticketId = req.getId();
        Ticket concernedTicket = ticketRepository.findById(ticketId).
                orElseThrow(()-> new RuntimeException(TICKET_NOT_FOUND));
        TicketVersion currentVersion = TicketVersion.
                builder().
                ticket(concernedTicket).
                title(concernedTicket.getTitle()).
                description(concernedTicket.getDescription()).
                status(concernedTicket.getStatus()).
                build();
        if(!currentVersion.getDescription().equals(req.getDescription())) {
            absoluteChange= true;
            modifsDesc+="Description ";
        }
        if(!currentVersion.getTitle().equals(req.getTitle())) {
            absoluteChange= true;
            modifsDesc+="Title ";
        }
        if(!currentVersion.getStatus().equals(req.getStatus())) {
            absoluteChange= true;
            modifsDesc+="Status ";
        }
        if(absoluteChange) {
            modifsDesc+="Of the ticket N°"+concernedTicket.getId()+" Has changed";
            Modification modif = Modification.builder().
                    description(modifsDesc).
                    ticket(concernedTicket).
                    build();
            modifRepository.save(modif);
        }

        ticketVersionRepository.save(currentVersion);
        concernedTicket.setTitle(req.getTitle());
        concernedTicket.setDescription(req.getDescription());
        concernedTicket.setStatus(req.getStatus());
        return Mapper.fromEntityToTicketResponseDTO(ticketRepository.save(concernedTicket));
    }

    @Override
    public TicketResponseDTO attachTicketToParent(Integer subTicket, Integer parent) {
        Ticket parentTicket = ticketRepository.findById(parent).
                orElseThrow(() -> new RuntimeException(TICKET_NOT_FOUND));
        Ticket childTicket =  ticketRepository.findById(subTicket).
                orElseThrow(()-> new RuntimeException(TICKET_NOT_FOUND));
        childTicket.setTicket(parentTicket);
        return fromEntityToTicketResponseDTO
                (ticketRepository.save(childTicket));
    }

    @Override
    public TicketResponseDTO getTicketById(Integer ticketId) {
        return ticketRepository.findById(ticketId).
                map(Mapper::fromEntityToTicketResponseDTO).
                orElseThrow(()-> new RuntimeException(TICKET_NOT_FOUND));
    }

    @Override
    public List<TicketResponseDTO> getTicketAndPrevVersion(Integer ticketId) {
        Ticket concernedTicket =
                ticketRepository.findById(ticketId).
                        orElseThrow(()-> new
                                RuntimeException(TICKET_NOT_FOUND));
        List<TicketVersion> versions =
                ticketVersionRepository.findByTicketIdOrderByCreatedDateDesc(ticketId);
        TicketVersion lastVersion = versions.get(0);
        List<TicketResponseDTO> ret = new ArrayList<>();
        ret.add(Mapper.fromVersionToTicketResponseDTO(lastVersion));
        ret.add(Mapper.fromEntityToTicketResponseDTO(concernedTicket));
        return ret;
    }

    @Override
    public Integer getTotalTickets() {
        return ticketRepository.findAll().size();
    }

    @Override
    public List<TicketResponseDTO> getTicketsByStatus(Status status) {
        return ticketRepository.findAllByStatus(status).
                stream().map(Mapper::fromEntityToTicketResponseDTO).
                toList();
    }

    @Override
    public List<TicketResponseDTO> getTicketVersion(Integer ticketId) {
        List<TicketVersion> versions =
                ticketVersionRepository.findByTicketIdOrderByCreatedDateDesc(
                        ticketId
                );
        return versions.stream().map(
                Mapper::fromVersionToTicketResponseDTO
        ).toList();
    }

}
