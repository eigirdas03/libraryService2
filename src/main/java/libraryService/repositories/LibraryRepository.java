package libraryService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import libraryService.models.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long>
{

}
