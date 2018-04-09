package com.github.cvazer.tryout.playgendary.pure;

import com.github.cvazer.tryout.playgendary.pure.configurations.MainConfiguration;
import com.github.cvazer.tryout.playgendary.pure.configurations.PersistenceConfiguration;
import com.github.cvazer.tryout.playgendary.pure.configurations.RestSecurityConfiguration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MainInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class)
                .addMappingForUrlPatterns(null, false, "/*");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                MainConfiguration.class,
                RestSecurityConfiguration.class,
                PersistenceConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
