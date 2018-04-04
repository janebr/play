package controllers;

import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import views.html.*;

import javax.inject.Inject;


public class AuthController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public AuthController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

   
    public Result auth() {
        Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors())
            
            return badRequest(loginPole.render(loginForm));
        else {
     
            session("email", loginForm.get().email);
           
            flash("success","welcome!");
            
            return redirect(routes.HomeController.more());
        }
    }

  
    public Result login() {
        if (session("email") != null) return redirect(routes.HomeController.index());
        else return ok(loginPole.render(formFactory.form(Login.class)));
    }



  
    public Result logout() {
        session().clear();
        return redirect(routes.HomeController.index());
    }


    
    public Result signup() {
        if (session("email") != null) return redirect(routes.HomeController.index());
        else return ok(registrPole.render(formFactory.form(Register.class)));
    }

  
    public Result register() {
        Form<Register> regForm = formFactory.form(Register.class).bindFromRequest();
        if (regForm.hasErrors()) return badRequest(registrPole.render(regForm));
        else {
            Register reg = regForm.get();
            User user = new User(reg.email, reg.password, reg.name, reg.phone);
            user.save();
            session("email", reg.email);
            return redirect(routes.HomeController.more());
        }
    }




   

}