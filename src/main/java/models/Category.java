package models;

import com.devskiller.jfairy.Fairy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Category {
    private static final Logger logger = LoggerFactory.getLogger(Category.class);
    private static Fairy jfairy = Fairy.create();
    private int id;
    private String name;

    public static Category generateTagWithID(int id) {
        Category generated = new Category();
        generated.setId(id);
        generated.setName(jfairy.person().getUsername());
        logger.info("Category with id=" + generated.getId() + " is generated");
        return generated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
