package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.FinancialData;
import vn.vti.moneypig.models.OilWorldData;

import java.util.List;
import java.util.Optional;

public interface OilWorldDataRepository extends MongoRepository<OilWorldData, Long> {
    public Optional<OilWorldData> findByUtcTime(String utcTime);

    OilWorldData findFirstByOrderByIdDesc();


    List<OilWorldData> findFirst20ByOrderByIdDesc();
}
