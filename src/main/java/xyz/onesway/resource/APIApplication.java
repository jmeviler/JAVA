package xyz.onesway.resource;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

public class APIApplication extends ResourceConfig{
    public APIApplication() {
        //加载Resource
        register(HomeResource.class);

        //注册数据转换器
        register(JacksonJsonProvider.class);

        // Logging.
        register(LoggingFilter.class);
    }
}
