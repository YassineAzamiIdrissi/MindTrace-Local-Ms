package mindTrace.local.Repositories;

import mindTrace.local.Entities.Project;
import mindTrace.local.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    List<Project> findAllByStatus(Status status);
}
