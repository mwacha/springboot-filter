package tk.mwacha.http.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.*;

@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class RequestLoggingFilter implements Filter {

    private static final List<String> HEADERS_TO_SKIP = Arrays.asList("authorization", "token", "security", "oauth", "auth");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        CachedRequestHttpServletRequest cachedRequestHttpServletRequest =
                new CachedRequestHttpServletRequest((HttpServletRequest) servletRequest);

        log.info("URL: {} | Requesr: {} | HTTP Method: {} | Headers: {} | QueryStringParams: {}} | RequestBody: {}}",
                cachedRequestHttpServletRequest.getRequestURL(), cachedRequestHttpServletRequest.getRemoteAddr(),
                cachedRequestHttpServletRequest.getMethod(), getRequestHeaders(cachedRequestHttpServletRequest),
                cachedRequestHttpServletRequest.getQueryString(), getBody(cachedRequestHttpServletRequest));

        chain.doFilter(cachedRequestHttpServletRequest, servletResponse);
    }

    private Map<String, String> getRequestHeaders(HttpServletRequest request) {
        Map<String, String> headersMap = new HashMap<>();
        Enumeration<String> headerEnumeration = request.getHeaderNames();
        while (headerEnumeration.hasMoreElements()) {
            String header = headerEnumeration.nextElement();

            if (HEADERS_TO_SKIP.stream().noneMatch(h -> h.toLowerCase().contains(header.toLowerCase())
                    || header.toLowerCase().contains(h.toLowerCase()))) {
                headersMap.put(header, request.getHeader(header));
            }
        }
        return headersMap;
    }

    private String getBody(CachedRequestHttpServletRequest request) throws IOException {
        StringBuilder body = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();

        while ((line = reader.readLine()) != null) {
            body.append(line);
        }

        return body.toString();
    }

    private static class CachedRequestHttpServletRequest extends HttpServletRequestWrapper {

        private byte[] cachedBody;

        public CachedRequestHttpServletRequest(HttpServletRequest request) throws IOException {
            super(request);
            this.cachedBody = StreamUtils.copyToByteArray(request.getInputStream());
        }

        @Override
        public ServletInputStream getInputStream() {
            return new RequestServletInputStream(this.cachedBody);
        }

        @Override
        public BufferedReader getReader() {
            return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(this.cachedBody)));
        }
    }

}