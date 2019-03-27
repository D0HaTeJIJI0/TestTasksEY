package com.travnich.account.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Account extends BaseAccountEntity {

    private Integer bankAccountId;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private GroupAccount groupAccount;

    public Account() {
        super();
    }

    public Account(Integer bankAccountId,
                   Double activeIn,
                   Double passiveIn,
                   Double debit,
                   Double credit,
                   Double activeOut,
                   Double passiveOut,
                   Document document) {

        super(activeIn, passiveIn, debit, credit, activeOut, passiveOut, document);
        this.bankAccountId = bankAccountId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "bankAccountId=" + bankAccountId +
//                ", groupAccount=" + groupAccount +
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
