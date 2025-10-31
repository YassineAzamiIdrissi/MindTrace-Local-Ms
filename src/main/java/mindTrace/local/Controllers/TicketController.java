package mindTrace.local.Controllers;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Dtos.TicketReqDTO;
import mindTrace.local.Dtos.TicketResponseDTO;
import mindTrace.local.Enums.Status;
import mindTrace.local.Services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody TicketReqDTO req) {
        return ResponseEntity.ok(ticketService.saveTicket(req));
    }

    @PutMapping("/update")
    public ResponseEntity<TicketResponseDTO> updateTicket(
            @RequestBody TicketReqDTO req) {
        return ResponseEntity.ok(ticketService.updateTicket(req));
    }

    @PutMapping("/{subTicket}/attach/{parent}")
    public ResponseEntity<TicketResponseDTO> attachTicketToParent(
            @PathVariable Integer subTicket,
            @PathVariable Integer parent) {
        return ResponseEntity.ok(ticketService.attachTicketToParent(subTicket, parent));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable Integer ticketId) {
        return ResponseEntity.ok(ticketService.getTicketById(ticketId));
    }

    @GetMapping("/{ticketId}/with-prev")
    public ResponseEntity<List<TicketResponseDTO>> getTicketAndPrevVersion(@PathVariable Integer ticketId) {
        return ResponseEntity.ok(ticketService.getTicketAndPrevVersion(ticketId));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getTotalTickets() {
        return ResponseEntity.ok(ticketService.getTotalTickets());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TicketResponseDTO>> getTicketsByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(ticketService.getTicketsByStatus(status));
    }

    @GetMapping("/{ticketId}/versions")
    public ResponseEntity<List<TicketResponseDTO>> getTicketVersions(@PathVariable Integer ticketId) {
        return ResponseEntity.ok(ticketService.getTicketVersion(ticketId));
    }
}
