package com.dolphin.edium.authen.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("authenService")
public class AuthenService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessage() {

        return "{My message}";
    }
}
