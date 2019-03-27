package com.travnich.account.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class Document extends BaseEntity{

    private String name;

    public Document() {
    }

    public Document(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
