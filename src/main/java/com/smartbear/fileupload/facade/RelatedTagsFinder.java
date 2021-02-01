package com.smartbear.fileupload.facade;

import com.smartbear.fileupload.dto.RelatedTagDto;
import com.smartbear.fileupload.model.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RelatedTagsFinder {

    private final TagsConverter tagsConverter;

    public List<RelatedTagDto> findRelatedTags(List<File> files, List<String> queryTags) {
        Map<String, Integer> tagsCount = new HashMap<>();
        List<String> relatedTags = files.stream()
                .flatMap(f -> tagsConverter.convertToList(f.getTags()).stream())
                .filter(t -> !queryTags.contains(t)).collect(Collectors.toList());
        relatedTags.forEach(t -> tagsCount.merge(t, 1, Integer::sum));
        return tagsCount.entrySet().stream()
                .map(e -> new RelatedTagDto(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
