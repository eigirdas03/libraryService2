package libraryService.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import libraryService.models.BookLibraryMapper;
import libraryService.models.BookLibraryMapperId;

@Repository
public interface BookLibraryMapperRepository extends JpaRepository<BookLibraryMapper, BookLibraryMapperId>
{
	List<BookLibraryMapper> findByBook(long book);
	List<BookLibraryMapper> findByLibrary(long library);
	
	@Transactional
	long deleteByBook(long book);
	
	@Transactional
	long deleteByLibrary(long library);
}
