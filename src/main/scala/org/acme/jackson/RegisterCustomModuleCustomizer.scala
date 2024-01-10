package org.acme.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import io.quarkus.jackson.ObjectMapperCustomizer
import io.quarkus.logging.Log
import io.quarkus.runtime.Startup
import io.vertx.core.json.Json
import jakarta.enterprise.context.ApplicationScoped


@Startup
@ApplicationScoped
class RegisterCustomModuleCustomizer extends ObjectMapperCustomizer {
  @Override
  def customize(objectMapper: ObjectMapper): Unit = {
    // Lets instantiate the json codec/objectmapper before adding the scala decoder...
//    Json.decodeValue("{}") // TODO Add to get to work
//    objectMapper.registerModule(new DefaultScalaModule())
    objectMapper.registerModule(new QuarkusScalaModule())
    Log.warn("Have added the QuarkusScalaModule to the jackson objectMapper")
//    DatabindCodec.mapper.registerModule(new DefaultScalaModule)
//    Log.info("Have added the scala json (de)serialization to the vertx object mapper")
  }
}
