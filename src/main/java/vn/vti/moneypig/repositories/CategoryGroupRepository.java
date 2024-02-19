package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.vti.moneypig.models.CategoryGroup;

import java.util.List;

public interface CategoryGroupRepository extends MongoRepository<CategoryGroup, Long> {

   // List<CategoryGroup> findAllByUserID(Long userID);

}
