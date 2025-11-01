package mindTrace.local.Repositories;

import mindTrace.local.Entities.Modification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModificationRepository extends JpaRepository<Modification, Integer> {
}
