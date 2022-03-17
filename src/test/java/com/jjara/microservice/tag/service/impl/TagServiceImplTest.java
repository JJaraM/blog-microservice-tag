package com.jjara.microservice.tag.service.impl;

import com.jjara.microservice.tag.domain.Sequence;
import com.jjara.microservice.tag.domain.Tag;
import com.jjara.microservice.tag.repository.SequenceRepository;
import com.jjara.microservice.tag.repository.TagRepository;
import com.jjara.microservice.tag.service.TagService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import javax.annotation.Resource;
import java.util.Arrays;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    TagServiceImpl.class,
})
public class TagServiceImplTest {

    @MockBean private TagRepository tagRepository;
    @MockBean private SequenceRepository sequenceRepository;
    @Resource private TagService tagService;

    @Test public void Given_aNewInstanceOfTag_When_create_Then_verifyCreation() {
        var id = 1L;
        var tag = new Tag();
        var sequence = new Sequence();
        sequence.setSeq(id);

        var sequenceMono = Mono.just(sequence);
        Mockito.when(sequenceRepository.getNextSequenceId(Mockito.eq("tag"))).thenReturn(sequenceMono);
        Mockito.doAnswer(invocation -> {
            Tag modifiedTag = invocation.getArgument(0);
            var mono = Mono.just(modifiedTag);
            Assertions.assertEquals(id, modifiedTag.getId());
            return mono;
        }).when(tagRepository).save(Mockito.any());

        StepVerifier.create(tagService.create(tag)).expectNextMatches(t -> t.getId() == id).verifyComplete();
        Mockito.verify(tagRepository, Mockito.atMostOnce()).save(tag);
    }

    @Test public void Given_aListOfIds_When_findAllById_Then_returnAFluxTag() {
        var tag1 = new Tag();
        var tag2 = new Tag();

        var ids = Arrays.asList(tag1.getId(), tag2.getId());
        var tags = Flux.just(tag1, tag2);

        Mockito.when(tagRepository.findAllById(ids)).thenReturn(tags);
        StepVerifier.create(tagService.findAllById(ids)).expectNext(tag1, tag2).verifyComplete();
    }
}
