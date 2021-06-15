package com.steammer.domain;

import com.steammer.domain.tags.Tags;
import com.steammer.domain.tags.TagsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagRepositoryTest {

    @Autowired
    TagsRepository tagsRepository;

    @Test
    public void create() {
        Tags tag = Tags.builder()
                .tagId(Long.valueOf(00000))
                .tagName("testTag")
                .build();

        tagsRepository.save(tag);

        Optional<Tags> tagList = tagsRepository.findById(Long.valueOf(00000));

        Tags tags = tagList.orElse(null);

        assert tags != null;
        assertThat(tags.getTagId()).isEqualTo(Long.valueOf(00000));
        assertThat(tags.getTagName()).isEqualTo("testTag");
    }
}
