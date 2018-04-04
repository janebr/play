package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Gallery extends Model {

    @Id
    public Long id;

    @Constraints.Required(message = "Обязательное поле")
    public String description;


    public String imageUrl;
    public static final Finder<Long, Gallery> find = new Finder<>(Gallery.class);
}
