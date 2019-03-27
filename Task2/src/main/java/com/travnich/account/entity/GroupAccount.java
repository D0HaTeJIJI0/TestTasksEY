package com.travnich.account.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class GroupAccount extends BaseAccountEntity{

    private Integer groupAccountId;

    @OneToMany
    private List<Account> accounts;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private ClassAccount classAccount;

    public GroupAccount() {
    }

    public GroupAccount(Integer groupAccountId,
                        Double activeIn,
                        Double passiveIn,
                        Double debit,
                        Double credit,
                        Double activeOut,
                        Double passiveOut,
                        Document document) {

        super(activeIn, passiveIn, debit, credit, activeOut, passiveOut, document);
        this.groupAccountId = groupAccountId;
    }

    @Override
    public String toString() {
        return "GroupAccount{" +
                "groupAccountId=" + groupAccountId +
                ", accounts=" + accounts +
//                ", classAccount=" + classAccount +
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
