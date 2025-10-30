package mindTrace.local.Repositories;

import mindTrace.local.Entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer>
{

}
