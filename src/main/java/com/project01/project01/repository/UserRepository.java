package com.project01.project01.repository;

import com.project01.project01.entity.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument,String> {
    UserDocument findByusername(String username);
}
