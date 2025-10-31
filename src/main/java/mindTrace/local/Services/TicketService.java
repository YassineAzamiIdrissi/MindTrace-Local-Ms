package mindTrace.local.Services;

import mindTrace.local.Dtos.TicketReqDTO;
import mindTrace.local.Dtos.TicketResponseDTO;


public interface TicketService {
    TicketResponseDTO saveTicket
            (TicketReqDTO req,Integer userId, Integer projectId);
}
