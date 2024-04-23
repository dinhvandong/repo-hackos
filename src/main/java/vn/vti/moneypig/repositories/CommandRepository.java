package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.Command;

public interface CommandRepository extends MongoRepository<Command, Long>
{


}
