package mindTrace.local.Services;

import mindTrace.local.Entities.TicketVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketVersionRepository extends JpaRepository<TicketVersion, Integer> {
    List<TicketVersion> findByTicketIdOrderByCreatedDateDesc(int ticketId);
}
