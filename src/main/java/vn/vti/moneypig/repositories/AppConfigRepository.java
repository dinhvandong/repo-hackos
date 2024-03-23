package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.AppConfig;

public interface AppConfigRepository extends MongoRepository<AppConfig, Long> {
}
