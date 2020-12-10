package com.ibar.demo.repositories;

import com.ibar.demo.model.Photo;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface PhotoRepository extends MongoRepository<Photo, ObjectId> {
   Optional<Photo> findByUserId(int id);
}
