package models;

import com.devskiller.jfairy.Fairy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tag {
    private static final Logger logger = LoggerFactory.getLogger(Tag.class);
    private static Fairy jfairy = Fairy.create();
    private int id;
    private String name;

    public static Tag generateTagWithID(int id) {
        Tag generated = new Tag();
        generated.setId(id);
        generated.setName(jfairy.person().getUsername());
        logger.info("Tag with id=" + generated.getId() + " is generated");
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