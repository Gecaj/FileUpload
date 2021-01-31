package com.smartbear.fileupload.dto;

import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
public class FileDto {
    @NonNull
    private final String name;
    @NonNull
    private final List<String> tags;
}
