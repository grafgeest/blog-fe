package xyz.grafgeest.blog.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "post")
public class Post extends GenericEntity {

    @NotNull
    @Size(min = 3)
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Size(min = 3)
    @Column(name = "content", nullable = false)
    private String content;

    public Post() {
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
