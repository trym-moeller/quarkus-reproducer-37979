package org.acme

import io.quarkus.security.identity.SecurityIdentity
import jakarta.inject.Inject
import jakarta.ws.rs.{GET, Path}
import org.jboss.resteasy.reactive.NoCache

@Path("/api/users")
class UserResource @Inject()(val identity: SecurityIdentity) {

  class User(val userName: String) {
    def getUserName: String = userName
  }

  @GET
  @Path("/me")
  @NoCache
  def me = new User(identity.getPrincipal.getName)
}
