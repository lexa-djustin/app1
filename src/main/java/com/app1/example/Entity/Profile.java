package com.app1.example.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Profile")
@Table(name="profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name="name")
    protected String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "profile", targetEntity = Row.class, cascade = {CascadeType.ALL})
    protected List<Row> rows;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
