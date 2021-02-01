package com.smartbear.fileupload.repository;

import com.smartbear.fileupload.model.File;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextQuery;

import java.util.List;

@RequiredArgsConstructor
public class FileRepositoryCustomImpl implements FileRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<File> findFilesWithAndWithoutTags(String tagsQuery) {
        ensureTextIndex();
        Query query = new TextQuery(tagsQuery);
        return mongoTemplate.find(query, File.class);
    }

    private void ensureTextIndex() {
        TextIndexDefinition indexDefinition = new TextIndexDefinition.TextIndexDefinitionBuilder()
                .onField("tags")
                .build();
        mongoTemplate.indexOps(File.class).ensureIndex(indexDefinition);
    }
}
