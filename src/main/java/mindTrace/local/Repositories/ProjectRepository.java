package mindTrace.local.Repositories;

import mindTrace.local.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
