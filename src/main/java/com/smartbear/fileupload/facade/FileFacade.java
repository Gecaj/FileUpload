package com.smartbear.fileupload.facade;

import com.smartbear.fileupload.dto.RelatedTagDto;
import com.smartbear.fileupload.dto.SaveFileDto;
import com.smartbear.fileupload.dto.SearchByTagsResponseDto;
import com.smartbear.fileupload.mapper.FileMapper;
import com.smartbear.fileupload.model.File;
import com.smartbear.fileupload.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FileFacade {

    private static final int PAGE_SIZE = 10;

    private final FileMapper fileMapper;
    private final TagsConverter tagsConverter;
    private final FileRepository fileRepository;
    private final RelatedTagsFinder relatedTagsFinder;

    public UUID addFile(SaveFileDto saveFileDto) {
        validateTags(saveFileDto.getTags());
        File file = fileRepository.save(File.builder()
                .uuid(UUID.randomUUID())
                .name(saveFileDto.getName())
                .tags(tagsConverter.convertToString(saveFileDto.getTags()))
                .build());
        return file.getUuid();
    }

    public SearchByTagsResponseDto findFilesByTags(String tagsQuery, int page) {
        validateQuery(tagsQuery);
        List<String> includeTags = extractIncludeTags(tagsQuery);
        String includeQueryString = prepareIncludeQueryString(includeTags);
        String excludeQueryString = prepareExcludeQueryString(tagsQuery);
        String queryString = String.format("%s %s", includeQueryString, excludeQueryString);
        List<File> allFiles = fileRepository.findFilesWithAndWithoutTags(queryString);
        List<File> filesFromPage = fileRepository.findFilesWithAndWithoutTags(queryString, PageRequest.of(page, PAGE_SIZE));
        List<RelatedTagDto> related_tags = relatedTagsFinder.findRelatedTags(allFiles, includeTags);
        return new SearchByTagsResponseDto(allFiles.size(), related_tags, filesFromPage.stream()
                .map(fileMapper::toFileDto)
                .collect(Collectors.toList()));
    }

    private List<String> extractIncludeTags(String tagsQuery) {
        return Arrays.stream(tagsQuery.split(" "))
                .filter(s -> s.startsWith("+"))
                .map(s -> s.replace("+", ""))
                .collect(Collectors.toList());
    }

    private String prepareExcludeQueryString(String tagsQuery) {
        return Arrays.stream(tagsQuery.split(" ")).filter(s -> s.startsWith("-")).collect(Collectors.joining(" "));
    }

    private String prepareIncludeQueryString(List<String> includeTags) {
        return includeTags.stream().map(s -> "\"" + s + "\"").collect(Collectors.joining(" "));
    }

    private void validateTags(List<String> tags) {
        boolean containsInvalidChars = tags.stream().anyMatch(s -> s.contains("+") || s.contains("-") || s.contains(" "));
        if (containsInvalidChars) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tags cannot contain '+', '-' and whitespace characters.");
        }
    }

    private void validateQuery(String tagsQuery) {
        boolean validQueryElements = Arrays.stream(tagsQuery.split(" ")).allMatch(s -> s.startsWith("+") || s.startsWith("-"));
        if (!validQueryElements) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tags query should contain tags starting with either '+' or '-', separated by whitespace.");
        }
    }
}
