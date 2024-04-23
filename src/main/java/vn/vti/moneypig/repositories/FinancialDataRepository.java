package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.FinancialData;

public interface FinancialDataRepository extends MongoRepository<FinancialData, Long>
{


}
