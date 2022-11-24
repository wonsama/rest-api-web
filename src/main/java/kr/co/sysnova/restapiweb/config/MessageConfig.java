package kr.co.sysnova.restapiweb.config;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import dev.akkinoc.util.YamlResourceBundle;

// @see more : https://github.com/akkinoc/yaml-resource-bundle

@Configuration
public class MessageConfig implements WebMvcConfigurer {
    @Bean
    public LocaleResolver localeResolver() {

        // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/i18n/package-summary.html
        // SessionLcaleResolver외 다른 방식도 확인할수 있다

        // LocaleResolver는 다양한 방식의 Resolver를 제공하는데 그중에서 SessionLocaleResolver를 사용한다.
        // LocaleChangeInterceptor로 "lang"값을 통해
        // Locale 정보가 변경되면 SessionLocaleResolver에 의해서 해당 세션의 Locale 정보가 변경되는 것이다.
        // 그러면 해당 세션이 만료될때까지 변경된 Locale정보가 유지될것이다.

        SessionLocaleResolver slr = new SessionLocaleResolver();

        // 세션에 지역설정을 한다. 위 설정을 통해 "lang"쿼리의 값에 매칭되는 yml파일이 없다면 기본으로 한국어를 제공하게 된다.
        slr.setDefaultLocale(Locale.KOREAN);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {

        // LocaleChangeInterceptor는 spring에서 공식적으로 국제화 처리를 위해 제공하는 인터셉터이다.
        // 이 인터셉터를 이용해서 "lang"이름을 갖는 쿼리파라미터의 값을 바탕으로 언어정보를 변경한다.
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 지역설정을 변경하는 인터셉터를 만든다.
        // 요청시 쿼리 파라미터로 lang정보를 넣으면 변경된다 (lang="en")
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 이 인터셉터를 시스템 레지스트리에 추가해준다.
        // 이 추가를 위해서 WebMvcConfigurer를 상속받는 것이다.
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public MessageSource messageSource(
            // yml파일을 참조하는 MessageSource를 생성했다.
            // basename과 encoding값을 application.yml에서 받아오는 애노테이션이 달려있다.
            @Value("${spring.messages.basename}") String basename,
            @Value("${spring.messages.encoding}") String encoding) {
        YamlMessageSource ms = new YamlMessageSource();
        ms.setBasename(basename);
        ms.setDefaultEncoding(encoding);
        // MessasgeFormat을 전체 메시지에 적용할 것인지 여부 (T/F)
        ms.setAlwaysUseMessageFormat(true);
        // 메시지를 찾지 못했을 때, 예외 처리 대신 메시지 코드를 그대로 반환할지 말지 (T/F)
        ms.setUseCodeAsDefaultMessage(true);
        // 감지된 Locale파일이 없을 때, (T) -> system locale값 사용, (F) -> messages.properties값 사용
        ms.setFallbackToSystemLocale(true);
        return ms;
    }

    private static class YamlMessageSource extends ResourceBundleMessageSource {

        // locale정보에 따라 맞는 yml파일을 basename과 locale 조합으로 찾아서 읽는다.
        @Override
        protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
            return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
        }
    }

}
