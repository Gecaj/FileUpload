package com.smartbear.fileupload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class File {
    @Id
    private UUID id;
    private String name;
    private String tags;
}
