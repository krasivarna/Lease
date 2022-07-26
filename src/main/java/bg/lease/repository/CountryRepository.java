package bg.lease.repository;

import bg.lease.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity,String> {
    @Query(value="Select * from country v where v.Name like %:keyword%  or v.no like %:keyword%",nativeQuery = true)
    List<CountryEntity> findByKeyword (@Param("keyword") String keyword);

    Optional<CountryEntity> findByNo(String no);
    void deleteByNo(String no);
}
