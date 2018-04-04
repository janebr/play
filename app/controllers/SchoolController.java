package controllers;


import router.Routes;
import models.School;
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
public class SchoolController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public SchoolController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    
    /**
     * @return
     */
    @Security.Authenticated(SecuredController.class)
    public Result list(){

        List<School> page = School.find.all();
        return ok(list_school.render(page));
    }

    /**
     * @return
     */

    public Result create(){
        Form<School> schoolForm = formFactory.form(School.class);
        return ok(createForm_school.render(schoolForm));
    }

    /**
     * @return
     */
  
    public Result save(){
        Form<School> schoolForm = formFactory.form(School.class).bindFromRequest();
        if (schoolForm.hasErrors()){
            return badRequest(createForm_school.render(schoolForm));
        } else {
            School s = schoolForm.get();
            s.save();

            return redirect(routes.SchoolController.list());
        }
    }

    /**
     * @param id
     * @return
     */
 
    public Result edit(Long id){
       School s = School.find.byId(id);
        if (s == null){
            return notFound("404 NotFound");
        }
        Form<School> schoolForm = formFactory.form(School.class).fill(s);

        return ok(editForm_school.render(id, schoolForm));
    }

    /**
     * @param id
     * @return
     */
  
    public Result update(Long id){
        Form<School> schoolForm = formFactory.form(School.class).bindFromRequest();
        if (schoolForm.hasErrors()){
            return badRequest(editForm_school.render(id, schoolForm));
        } else {
            School s1 = School.find.byId(id);
            if(s1 == null) return notFound();
            School s2 = schoolForm.get();
            s2.id = id;
            s2.update();
            return redirect(routes.SchoolController.list());
        }
    }

    /**
     * @param id
     * @return
     */
 
    public Result delete(Long id){
        School s = School.find.byId(id);
        if (s == null){
            return notFound();
        }

        s.delete();
        return redirect(routes.SchoolController.list());
    }
}
