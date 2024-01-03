package org.acme

import io.quarkus.security.Authenticated
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.{GET, Path, Produces}

@Path("/api/admin")
@Authenticated
class AdminResource {

  @GET
  @Produces(Array[String](MediaType.TEXT_PLAIN))
  def admin = "granted"

}
