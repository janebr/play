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
public class GalleryController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public GalleryController(FormFactory formFactory) {
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

        List<Gallery> page = Gallery.find.all();
        return ok(list_gal.render(page));
    }

    /**
     * 
     * @return
     */

    public Result create(){
        Form<Gallery> galForm = formFactory.form(Gallery.class);
        return ok(createForm_gal.render(galForm));
    }

    /**
     * 
     * @return
     */
  
    public Result save(){
        Form<Gallery> galForm = formFactory.form(Gallery.class).bindFromRequest();
        if (galForm.hasErrors()){
            return badRequest(createForm_gal.render(galForm));
        } else {
           
            Gallery g = galForm.get();
           
            g.save();

            return redirect(routes.GalleryController.list());
        }
    }

    /**
     * 
     * @param id
     * @return
     */
 
    public Result edit(Long id){
        Gallery g = Gallery.find.byId(id);
        if (g == null){
            return notFound("404 NotFound");
        }
        Form<Gallery> galForm = formFactory.form(Gallery.class).fill(g);

        return ok(editForm_gal.render(id, galForm));
    }

    /**
     * 
     * @param id
     * @return
     */
  
    public Result update(Long id){
        Form<Gallery> galForm = formFactory.form(Gallery.class).bindFromRequest();
        if (galForm.hasErrors()){
            return badRequest(editForm_gal.render(id, galForm));
        } else {
            Gallery g1 = Gallery.find.byId(id);
            if(g1 == null) return notFound();
            Gallery g2 = galForm.get();
            g2.id = id;
            g2.update();
            return redirect(routes.GalleryController.list());
        }
    }

    /**
     * 
     * @param id
     * @return
     */
 
    public Result delete(Long id){
        Gallery g = Gallery.find.byId(id);
        if (g == null){
            return notFound();
        }

        g.delete();
        return redirect(routes.GalleryController.list());
    }
}
