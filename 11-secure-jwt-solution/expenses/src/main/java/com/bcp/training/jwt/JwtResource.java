package com.bcp.training.jwt;



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import static com.bcp.training.jwt.JwtGenerator.generateJwtForAdmin;
import static com.bcp.training.jwt.JwtGenerator.generateJwtForRegularUser;

@Path( "/jwt" )
@ApplicationScoped
public class JwtResource {

    @GET
    @Path( "/{username}" )
    public String getJwt( @PathParam( "username" ) String username ) {

        if ( username.equalsIgnoreCase( "admin" ) ) {
            return generateJwtForAdmin( username );
        }

        return generateJwtForRegularUser( username );
    }

}