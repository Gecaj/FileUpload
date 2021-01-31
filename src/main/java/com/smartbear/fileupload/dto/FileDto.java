package com.smartbear.fileupload.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FileDto {
    private final UUID uuid;
    private final String name;
}
