package springdiaryproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name="replies")
public class Reply {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }
}
