package com.smartbear.fileupload.facade;

import com.smartbear.fileupload.dto.RelatedTagDto;
import com.smartbear.fileupload.model.File;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RelatedTagsFinderTest {

    private RelatedTagsFinder relatedTagsFinder = new RelatedTagsFinder(new TagsConverter());

    @Test
    void shouldFindRelatedTagsWithFilesCount() {
        // given
        List<File> filesList = createFiles();
        List<String> tags = Lists.newArrayList("Tag1", "Tag2");
        // when
        List<RelatedTagDto> relatedTags = relatedTagsFinder.findRelatedTags(filesList, tags);
        // then
        assertThat(relatedTags).hasSize(3);
        assertThat(relatedTags).contains(new RelatedTagDto("Tag3", 3));
        assertThat(relatedTags).contains(new RelatedTagDto("Tag5", 2));
        assertThat(relatedTags).contains(new RelatedTagDto("Tag6", 1));
    }

    private List<File> createFiles() {
        List<File> files = new ArrayList<>();
        files.add(new File(UUID.randomUUID(), "File 1", "Tag1 Tag2 Tag3 Tag5"));
        files.add(new File(UUID.randomUUID(), "File 1", "Tag1 Tag2 Tag3 Tag5"));
        files.add(new File(UUID.randomUUID(), "File 1", "Tag1 Tag2 Tag3"));
        files.add(new File(UUID.randomUUID(), "File 1", "Tag1 Tag2 Tag6"));
        files.add(new File(UUID.randomUUID(), "File 1", "Tag1 Tag2"));
        return files;
    }
}
