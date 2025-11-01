package mindTrace.local.Repositories;

import mindTrace.local.Entities.Modification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModificationRepository extends JpaRepository<Modification, Integer> {
    List<Modification> findAllByOrderByCreatedDateDesc();
}

