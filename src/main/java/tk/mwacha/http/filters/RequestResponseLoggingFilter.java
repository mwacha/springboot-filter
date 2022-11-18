package tk.mwacha.http.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

@Component
@Order(1)
@Slf4j
public class RequestResponseLoggingFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        log.info("Initializing filter :{}", this);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        log.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());

        String bodyInStringFormat = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        log.info("Request body: {}", bodyInStringFormat);

        log.info("Logging Response :{}", res.getWriter());


    }

    @Override
    public void destroy() {
        log.warn("Destructing filter :{}", this);
    }
}
