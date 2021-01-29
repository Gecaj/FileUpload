package com.smartbear.fileupload.repository;

import com.smartbear.fileupload.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, String> {
}
