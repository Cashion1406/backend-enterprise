package com.enterprise.backend.model;


import com.enterprise.backend.DTO.Idea.IdeasPerCate;
import com.enterprise.backend.DTO.Idea.IdeasPerDepartment;
import com.enterprise.backend.DTO.Topic.IdeaAnalytics;
import com.enterprise.backend.DTO.Topic.TopicWithMostFollowers;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@NamedNativeQuery(name = "Idea.top7ideas",

        query = "select c.id as cate_id, c.cate_name as cate_name, count(*) as numOfIdeas from idea_cate_tbl i inner join cate_tbl c on c.id =i.cate_id group by c.id order by count(i.idea_id) desc limit 7",
        resultSetMapping = "Mapping.MostIdeaPerCate"
)


@SqlResultSetMapping(name = "Mapping.MostIdeaPerCate",classes = @ConstructorResult(targetClass = IdeasPerCate.class, columns = {

        @ColumnResult(name = "cate_id"),
        @ColumnResult(name = "cate_name"),
        @ColumnResult(name = "numOfIdeas")
}))

@NamedNativeQuery(name = "Idea.ideasPerDepartment",

        query = "select d.id as department_id,d.depart_name as department_name,d.department_info as department_info,count(*) as numberOfIdeas from idea_tbl i inner join client_tbl c on c.id = i.client_id inner join department_tbl d on d.id = c.department_id group by d.id order by count(i.id) desc limit 7",
        resultSetMapping = "Mapping.countIdeasPerDepartment"
)



@SqlResultSetMapping(name = "Mapping.countIdeasPerDepartment",classes = @ConstructorResult(targetClass = IdeasPerDepartment.class, columns = {

        @ColumnResult(name = "department_id"),
        @ColumnResult(name = "department_name"),
        @ColumnResult(name = "department_info"),
        @ColumnResult(name = "numberOfIdeas")
}))

@NamedNativeQuery(name = "Idea.ideasAnalytics",
        query = "SELECT t.topic_name as topic_name , c.cate_name as cate_name, i.idea_title as idea_title, d.depart_name as department_name FROM cate_tbl c INNER JOIN idea_cate_tbl ci ON c.id = ci.cate_id INNER JOIN idea_tbl i ON ci.idea_id = i.id INNER JOIN topic_tbl t ON i.topic_id =  t.id INNER JOIN client_tbl cli ON i.client_id = cli.id INNER JOIN department_tbl d ON cli.department_id = d.id",
        resultSetMapping = "Mapping.getIdeasAnalytics"
)

@SqlResultSetMapping(name = "Mapping.getIdeasAnalytics", classes = @ConstructorResult(targetClass = IdeaAnalytics.class, columns = {
        @ColumnResult(name = "topic_name"),
        @ColumnResult(name = "cate_name"),
        @ColumnResult(name = "idea_title"),
        @ColumnResult(name = "department_name")
}))


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Table(name = "idea_tbl")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "idea_title")
    private String name;

    @Column(name = "idea_body")
    private String body;

    @Column(name = "date")
    private String date;

    @Column(name = "modify_date")
    private String modify_date;

    @Column(name = "attached_path")
    private String attached_path;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @JsonIncludeProperties({"name", "id"})
    private Topic topic;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIncludeProperties({"firstname", "lastname", "id"})
    private Client client;


    @OneToMany(mappedBy = "idea", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference(value = "idea_reaction")
    private Set<Reaction> reactions = new HashSet<>();


    @OneToMany(mappedBy = "idea", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference(value = "idea_comment")
    private Set<Comment> comments = new HashSet<>();


    @OneToMany(mappedBy = "idea_id",orphanRemoval = true ,cascade = CascadeType.PERSIST)
    private Set<Idea_cate> idea_cate = new HashSet<>();

    @Column(name = "isAnonymous")
    private Boolean isAnonymous;

    @Column(name = "isDeleted")
    private Boolean isDeleted;
}
