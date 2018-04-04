package controllers;


import router.Routes;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;


import play.twirl.api.Html;
import router.Routes;
import views.html.*;



import javax.inject.Inject;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        List<Capabilities> all = Capabilities.find.all();
        List<Gallery> all2 = Gallery.find.all();
        List <School> all3 = School.find.all();
        List <Team> all4 = Team.find.all();
        return ok(index.render(all, all2, all3, all4));
    }

    @Security.Authenticated(SecuredController.class)
    public Result message(){
        return ok(sendMessage.render());
    }

    @Security.Authenticated(SecuredController.class)
    public Result more(){
        User user = User.find.byId(session("email"));
        return ok(registrUser.render(user));
    }
      
   
}
