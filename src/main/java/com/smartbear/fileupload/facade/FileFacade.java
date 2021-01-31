package com.smartbear.fileupload.facade;

import com.smartbear.fileupload.dto.FileDto;
import com.smartbear.fileupload.model.File;
import com.smartbear.fileupload.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileFacade {

    private final TagsConverter tagsConverter;
    private final FileRepository fileRepository;

    public UUID addFile(FileDto fileDto) {
        validateTags(fileDto.getTags());
        File file = fileRepository.save(File.builder()
                .id(UUID.randomUUID())
                .name(fileDto.getName())
                .tags(tagsConverter.convertToString(fileDto.getTags()))
                .build());
        return file.getId();
    }

    private void validateTags(List<String> tags) {
        boolean containsInvalidChars = tags.stream().anyMatch(s -> s.contains("+") || s.contains("-") || s.contains(" "));
        if (containsInvalidChars) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tags cannot contain '+', '-' and whitespace characters.");
        }
    }
}
