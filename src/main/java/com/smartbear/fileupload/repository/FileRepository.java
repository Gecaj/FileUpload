package com.smartbear.fileupload.repository;

import com.smartbear.fileupload.model.File;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FileRepository extends MongoRepository<File, UUID>, FileRepositoryCustom {
}
