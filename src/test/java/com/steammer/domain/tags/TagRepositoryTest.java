package com.steammer.domain.tags;

import com.steammer.domain.tags.Tag;
import com.steammer.domain.tags.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TagRepositoryTest {

    @Autowired
    TagRepository tagRepository;

    @Test
    public void create() {
        //given
        Long id = Long.valueOf(1);
        String tagName = "testTag";

        Tag tag = Tag.builder()
                .tagId(id)
                .tagName(tagName)
                .build();
        //when
        tagRepository.save(tag);

        //then
        Optional<Tag> tagList = tagRepository.findById(id);

        Tag tagsTest = tagList.orElse(null);

        assert tagsTest != null;
        assertThat(tagsTest.getTagId()).isEqualTo(id);
        assertThat(tagsTest.getTagName()).isEqualTo(tagName);
    }
}
