package com.app1.example.Entity;

import javax.persistence.*;

@Entity
@Table(name = "row")
public class Row {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "type")
    protected int type;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @Column(name = "label")
    protected String label;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return this.profile;
    }
}
