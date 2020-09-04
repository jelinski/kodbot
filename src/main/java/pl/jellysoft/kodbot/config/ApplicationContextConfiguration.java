package pl.jellysoft.kodbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.jellysoft.kodbot.repository.MapProvider;
import pl.jellysoft.kodbot.service.MapService;
import pl.jellysoft.kodbot.service.MapServiceImpl;
import pl.jellysoft.kodbot.service.ResolverService;

@Configuration
public class ApplicationContextConfiguration {

    @Bean
    MapProvider mapProvider() {
        return new MapProvider();
    }

    @Bean
    MapService mapService(MapProvider mapProvider) {
        return new MapServiceImpl(mapProvider);
    }

    @Bean
    ResolverService resolverService(MapService mapService) {
        return new ResolverService(mapService);
    }

}
