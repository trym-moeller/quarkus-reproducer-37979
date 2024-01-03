package org.acme.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import io.quarkus.jackson.ObjectMapperCustomizer
import io.quarkus.logging.Log
import io.quarkus.runtime.Startup
import io.vertx.core.json.jackson.DatabindCodec
import jakarta.enterprise.context.ApplicationScoped


@Startup
@ApplicationScoped
class RegisterCustomModuleCustomizer extends ObjectMapperCustomizer {
  @Override
  def customize(objectMapper: ObjectMapper): Unit = {
    objectMapper.registerModule(new DefaultScalaModule())
    Log.warn("Have added the DefaultScalaModule to the jackson objectMapper")
//    DatabindCodec.mapper.registerModule(new DefaultScalaModule)
//    Log.info("Have added the scala json (de)serialization to the vertx object mapper")
  }
}
