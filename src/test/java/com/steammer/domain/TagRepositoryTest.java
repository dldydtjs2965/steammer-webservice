package com.steammer.domain;

import com.steammer.domain.tags.Tag;
import com.steammer.domain.tags.TagRepository;
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
    TagRepository tagRepository;

    @Test
    public void create() {
        //given
        Tag tag = Tag.builder()
                .tagId(Long.valueOf(0))
                .tagName("testTag")
                .build();
        //when
        tagRepository.save(tag);

        //then
        Optional<Tag> tagList = tagRepository.findById(Long.valueOf(00000));

        Tag tagsTest = tagList.orElse(null);

        assert tagsTest != null;
        assertThat(tagsTest.getTagId()).isEqualTo(Long.valueOf(00000));
        assertThat(tagsTest.getTagName()).isEqualTo("testTag");
    }
}
