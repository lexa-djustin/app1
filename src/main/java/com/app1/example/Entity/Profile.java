package com.app1.example.Entity;

import java.util.ArrayList;
import java.util.List;

public class Profile {

    protected List<Row> rows;

    protected String name;

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
