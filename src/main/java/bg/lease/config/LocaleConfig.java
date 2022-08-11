package bg.lease.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class LocaleConfig {

    @Bean
    public LocaleResolver localeResolver(){
        CookieLocaleResolver clr=new CookieLocaleResolver();
        clr.setCookieName("lang");
        return clr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor lci=new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource rbms=new ResourceBundleMessageSource();
        rbms.setBasename("locale/messages");
        rbms.setDefaultEncoding("UTF-8");
        return rbms;
    }
}
