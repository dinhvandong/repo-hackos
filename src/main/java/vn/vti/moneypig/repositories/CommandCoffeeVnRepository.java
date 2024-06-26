package vn.vti.moneypig.repositories;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import vn.vti.moneypig.models.CommandCoffeeVn;

import java.time.LocalDateTime;
import java.util.List;

public interface CommandCoffeeVnRepository extends MongoRepository<CommandCoffeeVn, Long>
{
//
//    @Query("{$and: [{timeYYYYMMDDHHmm: {$gte: ?0}}, {timeYYYYMMDDHHmm: {$lte: ?1}}]}")
//    List<CommandCoffeeVn> findLastMessagesWithinTimeframe(LocalDateTime startTime, LocalDateTime endTime);
//@Query("{$where: \"Math.abs(new Date().getTime() - new Date(this.time.toString().substring(0, 14)).getTime()) / 1000 < 60\"}")
//List<CommandCoffeeVn> findLastMessagesWithinTimeframe();

    @Aggregation(pipeline = {
            "{$match: {$expr: {$gt: [{$toDate: {$substr: [{$toString: '$time'}, 0, 14]}}, {$subtract: [{$toDate: new Date()}, 60000]}]}}}",
            "{$sort: {time: -1}}",
            "{$group: {_id: '$userId', message: {$first: '$$ROOT'}}}"
    })
    List<CommandCoffeeVn> findLastMessagesWithinTimeframeGroupedByUser();

    List<CommandCoffeeVn> findByTimeUTCGreaterThanEqual(String timestamp);


}
