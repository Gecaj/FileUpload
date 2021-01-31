package com.smartbear.fileupload.facade;

import com.smartbear.fileupload.dto.FileDto;
import com.smartbear.fileupload.model.File;
import com.smartbear.fileupload.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileFacade {

    private final TagsConverter tagsConverter;
    private final FileRepository fileRepository;

    public UUID addFile(FileDto fileDto) {
        File file = fileRepository.save(File.builder()
                .id(UUID.randomUUID())
                .name(fileDto.getName())
                .tags(tagsConverter.convertToString(fileDto.getTags()))
                .build());
        return file.getId();
    }
}
