//package tk.mwacha.http.filters;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@Component
//@Order(2)
//@Slf4j
//public class TransactionFilter implements Filter {
//
//    @Override
//    public void init(final FilterConfig filterConfig) throws ServletException {
//        log.info("Initializing filter :{}", this);
//    }
//
//    @Override
//    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        log.info("Starting Transaction for req :{}", req.getRequestURI());
//
//        chain.doFilter(request, response);
//
//        log.info("Committing Transaction for req :{}", req.getRequestURI());
//    }
//
//    @Override
//    public void destroy() {
//        log.warn("Destructing filter :{}", this);
//    }
//}
