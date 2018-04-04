package controllers;

import controllers.routes;
import play.mvc.*;
import play.mvc.Http.Context;

public class SecuredController extends Security.Authenticator {
    /**
     * 
     *
     * @param ctx 
     * @return 
     */
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

    /**
     * 
     *
     * @param ctx 
     * @return 
     */

	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect(routes.AuthController.login());
	}
}