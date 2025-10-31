package mindTrace.local.Repositories;

import mindTrace.local.Entities.Ticket;
import mindTrace.local.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByStatus(Status status);
}
