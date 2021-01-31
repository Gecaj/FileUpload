package com.smartbear.fileupload.mapper;

import com.smartbear.fileupload.dto.FileDto;
import com.smartbear.fileupload.dto.SaveFileDto;
import com.smartbear.fileupload.facade.TagsConverter;
import com.smartbear.fileupload.model.File;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FileMapper {
    private final TagsConverter tagsConverter;
    public FileDto toFileDto(File entity) {
        return new FileDto(entity.getUuid(), entity.getName());
    }
}
