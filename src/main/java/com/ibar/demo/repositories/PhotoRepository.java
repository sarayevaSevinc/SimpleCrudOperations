package com.ibar.demo.repositories;

import com.ibar.demo.model.Photo;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PhotoRepository extends MongoRepository<Photo, ObjectId> {
    Optional<Photo> findByUserId(long id);
}
