package com.smartbear.fileupload.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchByTagsResponseDto {
    private final Integer total_records;
    private final List<RelatedTagDto> related_tags;
    private final List<FileDto> files;
}
