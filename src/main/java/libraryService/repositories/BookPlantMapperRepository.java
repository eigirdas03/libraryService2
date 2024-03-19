package libraryService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import libraryService.models.BookPlantMapper;
import libraryService.models.BookPlantMapperId;

@Repository
public interface BookPlantMapperRepository extends JpaRepository<BookPlantMapper, BookPlantMapperId>
{

}
