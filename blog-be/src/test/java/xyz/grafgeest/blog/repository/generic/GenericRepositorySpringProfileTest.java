package xyz.grafgeest.blog.repository.generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.grafgeest.blog.SpringBootRestApplication;
import xyz.grafgeest.blog.entity.GenericEntity;
import xyz.grafgeest.blog.entity.Tag;
import xyz.grafgeest.blog.repository.GenericRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {SpringBootRestApplication.class})
public class GenericRepositorySpringProfileTest {

    @Before
    public void setUp() throws Exception {
        log.info("setUp");
    }

    @Autowired
    private GenericRepository<Tag> genericRepository;

    @Test
    public void givenGenericRepository_whenSaveAndRetrieveEntity_thenOK() {
        Tag genericEntity = genericRepository.save(new Tag());
        Tag foundEntity = genericRepository.findOne(genericEntity.getId());

        assertNotNull(foundEntity);
        assertEquals(genericEntity.getId(), foundEntity.getId());
    }
}