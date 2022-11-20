package tk.mwacha.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import tk.mwacha.http.filters.RequestLoggingFilter;

@Configuration
public class FilterConfig {

    public FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestLoggingFilter());

        registrationBean.addUrlPatterns("/users/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
