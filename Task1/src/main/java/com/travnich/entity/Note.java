package com.travnich.entity;

import com.travnich.constant.GenerateInfo;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;
import java.util.UUID;

@Entity
@Data
public class Note{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Basic(optional = false)
    private LocalDate date;

    @Basic(optional = false)
    private String latinString;

    @Basic(optional = false)
    private String russianString;

    @Basic(optional = false)
    private Integer intNumber;

    @Basic(optional = false)
    private Double doubleNumber;

    private Note(LocalDate date, String latinString, String russianString, Integer intNumber, Double doubleNumber) {
        this.date = date;
        this.latinString = latinString;
        this.russianString = russianString;
        this.intNumber = intNumber;
        this.doubleNumber = doubleNumber;
    }

    public Note() {
    }

    public static Note createNote(String noteLine) {
        StringTokenizer st = new StringTokenizer(noteLine);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GenerateInfo.FORMAT_DATE);    //crutch
        LocalDate date = LocalDate.parse(st.nextToken(GenerateInfo.DELIMETER), formatter);
        String latinString = st.nextToken(GenerateInfo.DELIMETER);
        String russianString = st.nextToken(GenerateInfo.DELIMETER);
        Integer intNumber = Integer.parseInt(st.nextToken(GenerateInfo.DELIMETER));
        String strDouble = st.nextToken(GenerateInfo.DELIMETER);
        strDouble = strDouble.replace(",", ".");    // crutch
        Double doubleNumber = Double.parseDouble(strDouble);

        return new Note(
                date,
                latinString,
                russianString,
                intNumber,
                doubleNumber
        );
    }

}
