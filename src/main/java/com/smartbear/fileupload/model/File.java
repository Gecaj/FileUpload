package com.smartbear.fileupload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@Document
public class File {
    @Id
    private UUID uuid;
    private String name;
    private String tags;
}
