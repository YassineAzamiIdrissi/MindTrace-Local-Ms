package mindTrace.local.ServicesImpl;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Constants.ExceptionsMessages;
import mindTrace.local.Dtos.TicketReqDTO;
import mindTrace.local.Dtos.TicketResponseDTO;
import mindTrace.local.Entities.Project;
import mindTrace.local.Entities.Ticket;
import mindTrace.local.Entities.User;
import mindTrace.local.GenMapper.Mapper;
import mindTrace.local.Repositories.ProjectRepository;
import mindTrace.local.Repositories.TicketRepository;
import mindTrace.local.Repositories.UserRepository;
import mindTrace.local.Services.TicketService;
import org.springframework.stereotype.Service;

import static mindTrace.local.Constants.ExceptionsMessages.TICKET_NOT_FOUND;
import static mindTrace.local.Constants.ExceptionsMessages.USER_NOT_FOUND;
import static mindTrace.local.Enums.Status.STARTING;
import static mindTrace.local.GenMapper.Mapper.fromEntityToTicketResponseDTO;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public TicketResponseDTO saveTicket
            (TicketReqDTO req, Integer userId, Integer projectId) {
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
    public TicketResponseDTO attachTicketToParent(Integer subTicket, Integer parent) {
        Ticket parentTicket = ticketRepository.findById(parent).
                orElseThrow(() -> new RuntimeException(TICKET_NOT_FOUND));
        Ticket childTicket =  ticketRepository.findById(subTicket).
                orElseThrow(()-> new RuntimeException(TICKET_NOT_FOUND));
        childTicket.setTicket(parentTicket);
        return fromEntityToTicketResponseDTO
                (ticketRepository.save(childTicket));
    }
}
