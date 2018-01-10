package cn.duizhang.rest;

/**
 * Created by hanlinli on 2018/1/10.
 */

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class MyResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public MyResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public MyResponse sayHello(@QueryParam("name") String value) {
//        final String value = String.format(template, name);
        return new MyResponse(counter.incrementAndGet(), value);
    }
}
