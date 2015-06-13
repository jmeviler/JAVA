package xyz.onesway.resource;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import xyz.onesway.sever.TestResource;

public class APIApplication extends ResourceConfig{
    public APIApplication() {
        //加载Resource
        register(HomeResource.class);
        register(UserResource.class);
        register(DeviceResource.class);
        register(TaskResource.class);
        register(ActuatorResource.class);
        register(SensorResource.class);
        register(SpacebrewResource.class);
        register(TemResource.class);
        register(TestResource.class);
        //注册数据转换器
        register(JacksonJsonProvider.class);

        // Logging.
        register(LoggingFilter.class);
    }
}
