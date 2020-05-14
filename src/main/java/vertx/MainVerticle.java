package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> promise) throws Exception {
    Router router = Router.router(vertx);
    router.route("/login").handler(StaticHandler.create("client"));

    router.route("/login").handler(routingContext -> {
      HttpServerResponse response = routingContext.response();
      response.sendFile("argon-dashboard-master/examples/login.html");
    });

    vertx.createHttpServer().requestHandler(router).listen(8888, http -> {
      if(http.succeeded()) {
        promise.complete();
        System.out.println("Server started on port 8888");
      } else {
        promise.fail(http.cause());
      }
    });


    /*vertx.createHttpServer().requestHandler(req -> {
      req.response().putHeader("content-type", "text/plain").end("Hello");
    }).listen(8888, http -> {
      if(http.succeeded()){
        promise.complete();
        System.out.println("Server started on port 8888");
      } else {
        promise.fail(http.cause());
      }
    });*/

  }
}