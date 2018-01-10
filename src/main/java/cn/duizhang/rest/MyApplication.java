package cn.duizhang.rest;

/**
 * Created by hanlinli on 2018/1/10.
 */

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MyApplication extends Application<MyConfiguration> {
    public static void main(String[] args) throws Exception {
        new MyApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<MyConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(MyConfiguration configuration,
                    Environment environment) {
            final MyResource resource = new MyResource(
                    configuration.getTemplate(),
                    configuration.getDefaultName()
            );
            environment.jersey().register(resource);
        }

}
