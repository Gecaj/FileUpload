package com.smartbear.fileupload.dto;

import lombok.Data;

import java.util.List;

@Data
public class FileDto {
    private final String name;
    private final List<String> tags;
}
