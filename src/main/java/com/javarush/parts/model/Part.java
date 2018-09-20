package com.javarush.parts.model;

import javax.persistence.*;


/**
 * @author dubetskyi_ov on 13.09.2018
 */

@Entity
public class Part  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String title;
    private Boolean must;
    private Integer amount;
    public Part() {
    }

    public Part(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getMust() {
        return must;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMust(Boolean must) {
        this.must = must;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", Title='" + title + '\'' +
                ", must='" + must + '\'' +
                '}';
    }
}
