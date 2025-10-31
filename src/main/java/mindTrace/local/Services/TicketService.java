package mindTrace.local.Services;

import mindTrace.local.Dtos.TicketReqDTO;
import mindTrace.local.Dtos.TicketResponseDTO;
import mindTrace.local.Enums.Status;

import java.util.List;


public interface TicketService {
    TicketResponseDTO saveTicket
            (TicketReqDTO req);

    TicketResponseDTO updateTicket(TicketReqDTO req);

    TicketResponseDTO attachTicketToParent(Integer subTicket,Integer parent);
    TicketResponseDTO getTicketById(Integer ticketId);
    List<TicketResponseDTO> getTicketAndPrevVersion(Integer ticketId);
    Integer getTotalTickets();
    List<TicketResponseDTO> getTicketsByStatus(Status status);
    List<TicketResponseDTO> getTicketVersion(Integer ticketId);
}
