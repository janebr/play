package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Capabilities extends Model {

    @Id
    public Long id;

    @Constraints.Required(message = "Обязательное поле")
    public String title;

    @Constraints.Required(message = "Обязательное поле")
    public String title2;

    public String classli;

    @Constraints.Required(message = "Обязательное поле")
    public String description;


    public String imageUrl;
    public static final Finder<Long, Capabilities> find = new Finder<>(Capabilities.class);
}