package com.smartbear.fileupload.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Data
public class File {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String tags;
}
