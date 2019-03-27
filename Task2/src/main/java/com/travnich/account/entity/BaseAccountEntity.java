package com.travnich.account.entity;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class BaseAccountEntity extends BaseEntity{

    @Column(precision=10, scale=2)
    protected Double activeIn;

    @Column(precision=10, scale=2)
    protected Double passiveIn;

    @Column(precision=10, scale=2)
    protected Double debit;

    @Column(precision=10, scale=2)
    protected Double credit;

    @Column(precision=10, scale=2)
    protected Double activeOut;

    @Column(precision=10, scale=2)
    protected Double passiveOut;

    @OneToOne
    private Document document;

    public BaseAccountEntity() {
    }

    public BaseAccountEntity(Document document) {
        this.document = document;
    }

    public BaseAccountEntity(Double activeIn, Double passiveIn, Double debit, Double credit, Double activeOut, Double passiveOut, Document document) {
        this.activeIn = activeIn;
        this.passiveIn = passiveIn;
        this.debit = debit;
        this.credit = credit;
        this.activeOut = activeOut;
        this.passiveOut = passiveOut;
        this.document = document;
    }

    @Override
    public String toString() {
        return "BaseAccountEntity{" +
                "activeIn=" + activeIn +
                ", passiveIn=" + passiveIn +
                ", debit=" + debit +
                ", credit=" + credit +
                ", activeOut=" + activeOut +
                ", passiveOut=" + passiveOut +
                ", document=" + document +
                ", id=" + id +
                '}';
    }
}
