package com.michael.timezones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class CustomRepositoryRestMvcConfiguration extends RepositoryRestMvcConfiguration {
    @Bean
    @Primary
    public RepositoryRestConfiguration repositoryRestConfiguration(PersistentEntities persistentEntities,
                                                                   RepositoryRestConfiguration repositoryRestConfiguration) {
        for (PersistentEntity<?, ?> persistentEntity : persistentEntities) {
            repositoryRestConfiguration.exposeIdsFor(persistentEntity.getType());
        }
        return repositoryRestConfiguration;
    }
}
