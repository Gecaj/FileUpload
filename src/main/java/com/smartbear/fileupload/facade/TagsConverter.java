package com.smartbear.fileupload.facade;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagsConverter {
    public String convertToString(List<String> tags) {
        return tags.isEmpty() ? "" : String.join(" ", tags);
    }
}
