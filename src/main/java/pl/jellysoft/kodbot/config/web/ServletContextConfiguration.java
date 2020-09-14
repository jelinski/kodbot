package pl.jellysoft.kodbot.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@PropertySource("classpath:application.properties")
@Import({MvcConfiguration.class, ControllersConfiguration.class})
public class ServletContextConfiguration {

    @Bean
    TilesViewResolver tilesViewResolver() {
        return new TilesViewResolver();
    }

    @Bean
    TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tile-defs/definitions.xml");
        return tilesConfigurer;
    }

    @Bean
    SessionLocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("pl"));
        return sessionLocaleResolver;
    }

    @Bean
    ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames(
                "classpath:messages/messages",
                "classpath:messages/panel",
                "classpath:messages/register",
                "classpath:messages/home",
                "classpath:messages/rules",
                "classpath:messages/terms_of_use"
        );
        return messageSource;
    }

}
