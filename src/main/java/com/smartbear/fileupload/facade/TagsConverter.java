package com.smartbear.fileupload.facade;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class TagsConverter {
    public String convertToString(List<String> tags) {
        return tags.isEmpty() ? "" : String.join(" ", tags);
    }

    public List<String> convertToList(String tags) {
        return tags.isEmpty() ? Collections.emptyList() : Arrays.asList(tags.split(" "));
    }
}
