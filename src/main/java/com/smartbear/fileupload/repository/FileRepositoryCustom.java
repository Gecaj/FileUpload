package com.smartbear.fileupload.repository;

import com.smartbear.fileupload.model.File;

import java.util.List;

public interface FileRepositoryCustom {

    List<File> findFilesWithAndWithoutTags(String tagsQuery);
}
