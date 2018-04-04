package controllers;


import router.Routes;
import models.Team;
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
public class TeamController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public TeamController(FormFactory formFactory) {
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

        List<Team> page = Team.find.all();
        return ok(list_team.render(page));
    }

    /**
     * 
     * @return
     */

    public Result create(){
        Form<Team> teamForm = formFactory.form(Team.class);
        return ok(createForm_team.render(teamForm));
    }

    /**
     * 
     * @return
     */
  
    public Result save(){
        Form<Team> teamForm = formFactory.form(Team.class).bindFromRequest();
        if (teamForm.hasErrors()){
            return badRequest(createForm_team.render(teamForm));
        } else {
            
           Team t = teamForm.get();
            
            t.save();

            return redirect(routes.TeamController.list());
        }
    }

    /**
     * 
     * @param id
     * @return
     */
 
    public Result edit(Long id){
       Team t = Team.find.byId(id);
        if (t == null){
            return notFound("404 NotFound");
        }
        Form<Team> teamForm = formFactory.form(Team.class).fill(t);

        return ok(editForm_team.render(id, teamForm));
    }

    /**
     * 
     * @param id
     * @return
     */
  
    public Result update(Long id){
        Form<Team> teamForm = formFactory.form(Team.class).bindFromRequest();
        if (teamForm.hasErrors()){
            return badRequest(editForm_team.render(id, teamForm));
        } else {
            Team t1 = Team.find.byId(id);
            if(t1 == null) return notFound();
            Team t2 = teamForm.get();
            t2.id = id;
            t2.update();
            return redirect(routes.TeamController.list());
        }
    }

    /**
     * 
     * @param id
     * @return
     */
 
    public Result delete(Long id){
        Team t = Team.find.byId(id);
        if (t == null){
            return notFound();
        }

        t.delete();
        return redirect(routes.TeamController.list());
    }
}
