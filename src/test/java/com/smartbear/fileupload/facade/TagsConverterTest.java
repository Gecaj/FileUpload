package com.smartbear.fileupload.facade;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TagsConverterTest {

    private TagsConverter tagsConverter = new TagsConverter();

    @Test
    void shouldConvertEmptyListToEmptyString() {
        // given
        List<String> tags = Collections.emptyList();
        // when
        String string = tagsConverter.convertToString(tags);
        // then
        assertThat(string).isEmpty();
    }

    @Test
    void shouldConvertSingleElementListToSingleString() {
        // given
        List<String> tags = Collections.singletonList("tag1");
        // when
        String string = tagsConverter.convertToString(tags);
        // then
        assertThat(string).isEqualTo("tag1");
    }

    @Test
    void shouldConvertMultiElementListToStringSeparatedWithSpace() {
        // given
        List<String> tags = Lists.newArrayList("tag1", "tag2", "tag3", "tag4", "tag5");
        // when
        String string = tagsConverter.convertToString(tags);
        // then
        assertThat(string).isEqualTo("tag1 tag2 tag3 tag4 tag5");
    }
}
