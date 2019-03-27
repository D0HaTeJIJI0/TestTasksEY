package com.travnich.account.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class ClassAccount extends BaseAccountEntity{

    private String name;

    @OneToMany
    private List<GroupAccount> groupAccounts;

    public ClassAccount() {
    }

    public ClassAccount(String name, Document document) {
        super(document);
        this.name = name;
    }

    public ClassAccount(String name,
                        List<GroupAccount> groupAccounts,
                        Double activeIn,
                        Double passiveIn,
                        Double debit,
                        Double credit,
                        Double activeOut,
                        Double passiveOut,
                        Document document) {

        super(activeIn, passiveIn, debit, credit, activeOut, passiveOut, document);
        this.name = name;
        this.groupAccounts = groupAccounts;
    }

    @Override
    public String toString() {
        return "ClassAccount{" +
                "name='" + name + '\'' +
                ", groupAccounts=" + groupAccounts +
                ", activeIn=" + activeIn +
                ", passiveIn=" + passiveIn +
                ", debit=" + debit +
                ", credit=" + credit +
                ", activeOut=" + activeOut +
                ", passiveOut=" + passiveOut +
                ", id=" + id +
                '}';
    }
}
