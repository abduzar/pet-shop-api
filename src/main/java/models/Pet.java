package models;

import com.devskiller.jfairy.Fairy;
import enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Pet {
    private static final Logger logger = LoggerFactory.getLogger(Pet.class);
    private static Fairy jfairy = Fairy.create();
    private int id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<Tag> tags;
    private Status status;

    public static Pet generatePetWithID(int id) {
        Tag tag = Tag.generateTagWithID(jfairy.baseProducer().randomInt(99));
        Category category = Category.generateTagWithID(jfairy.baseProducer().randomInt(99));
        Pet generated = new Pet();
        generated.setId(id);
        generated.setCategory(category);
        generated.setName(jfairy.person().getUsername());
        generated.setPhotoUrls(new ArrayList<>());
        generated.setTags(new ArrayList<Tag>() {{
            add(tag);
        }});
        generated.setStatus(Status.AVAILABLE.name());
        logger.info("Pet with id=" + generated.getId() + " is generated");
        return generated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(ArrayList<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status.name().toLowerCase();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }
}
