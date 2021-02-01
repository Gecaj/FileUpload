package com.smartbear.fileupload.dto;

import lombok.Data;

@Data
public class RelatedTagDto {
    private final String tag;
    private final Integer file_count;
}
