package mindTrace.local.Services;

import mindTrace.local.Dtos.TicketReqDTO;
import mindTrace.local.Dtos.TicketResponseDTO;

import java.util.List;


public interface TicketService {
    TicketResponseDTO saveTicket
            (TicketReqDTO req);

    TicketResponseDTO updateTicket(TicketReqDTO req, String email);

    TicketResponseDTO attachTicketToParent(Integer subTicket,Integer parent);
    TicketResponseDTO getTicketById(Integer ticketId);
    List<TicketResponseDTO> getTicketAndPrevVersion(Integer ticketId);
    Integer getTotalTickets();
}
