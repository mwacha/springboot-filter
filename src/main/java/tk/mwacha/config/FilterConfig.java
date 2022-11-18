package tk.mwacha.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import tk.mwacha.http.filters.RequestResponseLoggingFilter;

@Configuration
public class FilterConfig {

    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());

        registrationBean.addUrlPatterns("/users/*");
        registrationBean.setOrder(2);

        return registrationBean;
    }
}
