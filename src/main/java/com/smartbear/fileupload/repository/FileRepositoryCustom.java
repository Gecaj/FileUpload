package com.smartbear.fileupload.repository;

import com.smartbear.fileupload.model.File;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FileRepositoryCustom {

    List<File> findFilesWithAndWithoutTags(String tagsQuery, Pageable pageable);

    Long countFilesWithAndWithoutTags(String tagsQuery);
}
