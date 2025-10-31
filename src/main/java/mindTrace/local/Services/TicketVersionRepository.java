package mindTrace.local.Services;

import mindTrace.local.Entities.TicketVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketVersionRepository extends JpaRepository<TicketVersion, Integer> {
}
