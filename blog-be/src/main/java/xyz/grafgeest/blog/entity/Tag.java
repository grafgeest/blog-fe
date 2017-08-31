package xyz.grafgeest.blog.entity;

import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@AttributeOverride(name = "id", column = @Column(name = "tag_id"))
public class Tag extends GenericEntity {

    @Column(name = "name")
    private String name;
}
