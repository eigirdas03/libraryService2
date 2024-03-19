package libraryService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import libraryService.models.BookLibraryMapper;
import libraryService.models.BookLibraryMapperId;

@Repository
public interface BookLibraryMapperRepository extends JpaRepository<BookLibraryMapper, BookLibraryMapperId>
{

}
