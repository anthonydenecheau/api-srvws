package com.scc.licence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.scc.licence.model.Holder;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {LicenceRepository.class})
@DataJpaTest
@EntityScan(basePackageClasses = {Holder.class})
public class LicenceRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LicenceRepository licenceRepository;

    @Test
    public void whenFindById_thenReturnHolder() {

        // given
        Holder holder = createHolder(); 
        entityManager.persist(holder);
        entityManager.flush();
     
        // when
        Optional<Holder> found = licenceRepository.findById(holder.getId());
     
        // then
        assertThat(found.get().getLastname())
          .isEqualTo(holder.getLastname());
    	
    }

    private Holder createHolder() {
    	Holder holder = new Holder()
			.withId(1)
			.withFirstname("CHARLIE")
			.withLastname("BROWN")
		;

        return holder;
    }
	
}
