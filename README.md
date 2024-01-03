# quarkus-reproducer-37979

The application has been created as follows:
```shell script
mvn io.quarkus.platform:quarkus-maven-plugin:3.6.4:create \
    -DprojectGroupId=org.acme \
    -DprojectArtifactId=quarkus-reproducer-37979 \
    -Dextensions='scala,oidc,keycloak-authorization,resteasy-reactive-jackson' \
    -DnoCode -DbuildTool=gradle
```

Then a dependency to 'com.fasterxml.jackson.module:jackson-module-scala_2.13:2.16.0' has been added to the build.gradle file
so jackson can json serialize scala collections in rest endpoints and the guide at https://quarkus.io/guides/security-keycloak-authorization
has been followed. I started keycloak in docker (and not as a quarkus dev service).

When I run the application using 
```shell script
./gradlew quarkusDev
```

I see the following exception:
```
2024-01-03 15:10:38,877 WARN  [io.qua.oid.com.run.OidcCommonUtils] (vert.x-eventloop-thread-1) OIDC Server is not available:: io.vertx.core.json.DecodeException: Invalid Json Object decoded as scala.collection.immutable.HashMap
at io.vertx.ext.web.codec.impl.BodyCodecImpl.lambda$static$1(BodyCodecImpl.java:48)
at io.vertx.ext.web.client.HttpResponse.bodyAsJsonObject(HttpResponse.java:151)
at io.vertx.mutiny.ext.web.client.HttpResponse.bodyAsJsonObject(HttpResponse.java:244)
at io.quarkus.oidc.common.runtime.OidcCommonUtils.lambda$discoverMetadata$2(OidcCommonUtils.java:439)
at io.smallrye.context.impl.wrappers.SlowContextualFunction.apply(SlowContextualFunction.java:21)
at io.smallrye.mutiny.operators.uni.UniOnItemTransform$UniOnItemTransformProcessor.onItem(UniOnItemTransform.java:36)
at io.smallrye.mutiny.vertx.AsyncResultUni.lambda$subscribe$1(AsyncResultUni.java:35)
at io.smallrye.mutiny.vertx.DelegatingHandler.handle(DelegatingHandler.java:25)
at io.vertx.ext.web.client.impl.HttpContext.handleDispatchResponse(HttpContext.java:397)
at io.vertx.ext.web.client.impl.HttpContext.execute(HttpContext.java:384)
at io.vertx.ext.web.client.impl.HttpContext.next(HttpContext.java:362)
at io.vertx.ext.web.client.impl.HttpContext.fire(HttpContext.java:329)
at io.vertx.ext.web.client.impl.HttpContext.dispatchResponse(HttpContext.java:291)
at io.vertx.ext.web.client.impl.HttpContext.lambda$null$7(HttpContext.java:507)
at io.vertx.core.impl.ContextInternal.dispatch(ContextInternal.java:277)
at io.vertx.core.impl.ContextInternal.dispatch(ContextInternal.java:259)
at io.vertx.core.impl.EventLoopContext.lambda$runOnContext$0(EventLoopContext.java:43)
at io.netty.util.concurrent.AbstractEventExecutor.runTask(AbstractEventExecutor.java:173)
at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:166)
at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:470)
at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:566)
at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:997)
at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
at java.base/java.lang.Thread.run(Thread.java:842)
```

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/quarkus-reproducer-37979-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Related Guides

- OpenID Connect ([guide](https://quarkus.io/guides/security-openid-connect)): Verify Bearer access tokens and authenticate users with Authorization Code Flow
- Keycloak Authorization ([guide](https://quarkus.io/guides/security-keycloak-authorization)): Policy enforcer using Keycloak-managed permissions to control access to protected resources
