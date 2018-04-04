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
public class CapabilitiesController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public CapabilitiesController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    
    /**
     * 
     * @return
     */
    @Security.Authenticated(SecuredController.class)
    public Result list(){

        List<Capabilities> page = Capabilities.find.all();
        return ok(list_cap.render(page));
    }

    /**
     * 
     * @return
     */

    public Result create(){
        Form<Capabilities> capForm = formFactory.form(Capabilities.class);
        return ok(createForm_cap.render(capForm));
    }

    /**
     * 
     * @return
     */
  
    public Result save(){
        Form<Capabilities> capForm = formFactory.form(Capabilities.class).bindFromRequest();
        if (capForm.hasErrors()){
            return badRequest(createForm_cap.render(capForm));
        } else {
            
            Capabilities c = capForm.get();
            
            c.save();

            return redirect(routes.CapabilitiesController.list());
        }
    }

    /**
     * 
     * @param id
     * @return
     */
 
    public Result edit(Long id){
        Capabilities c = Capabilities.find.byId(id);
        if (c == null){
            return notFound("404 NotFound");
        }
        Form<Capabilities> capForm = formFactory.form(Capabilities.class).fill(c);

        return ok(editForm_cap.render(id, capForm));
    }

    /**
     * 
     * @param id
     * @return
     */
  
    public Result update(Long id){
        Form<Capabilities> capForm = formFactory.form(Capabilities.class).bindFromRequest();
        if (capForm.hasErrors()){
            return badRequest(editForm_cap.render(id, capForm));
        } else {
            Capabilities c1 = Capabilities.find.byId(id);
            if(c1 == null) return notFound();
            Capabilities c2 = capForm.get();
            c2.id = id;
            c2.update();
            return redirect(routes.CapabilitiesController.list());
        }
    }

    /**
     * 
     * @param id
     * @return
     */
 
    public Result delete(Long id){
        Capabilities c = Capabilities.find.byId(id);
        if (c == null){
            return notFound();
        }

        c.delete();
        return redirect(routes.CapabilitiesController.list());
    }
}
