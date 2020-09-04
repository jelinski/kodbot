package pl.jellysoft.kodbot.config.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.jellysoft.kodbot.controller.ErrorController;
import pl.jellysoft.kodbot.controller.GameController;
import pl.jellysoft.kodbot.controller.HomeController;
import pl.jellysoft.kodbot.controller.PlayController;
import pl.jellysoft.kodbot.controller.VersionController;
import pl.jellysoft.kodbot.service.MapService;
import pl.jellysoft.kodbot.service.ResolverService;

@Configuration
public class ControllersConfiguration {

    @Bean
    HomeController homeController() {
        return new HomeController();
    }

    @Bean
    PlayController playController(MapService mapService) {
        return new PlayController(mapService);
    }

    @Bean
    GameController gameController(MapService mapService, ResolverService resolverService) {
        return new GameController(mapService, resolverService);
    }

    @Bean
    ErrorController errorController(MessageSource messageSource) {
        return new ErrorController(messageSource);
    }

    @Bean
    VersionController versionController() {
        return new VersionController();
    }

}
