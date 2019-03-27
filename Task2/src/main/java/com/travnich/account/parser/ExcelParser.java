package com.travnich.account.parser;

import com.travnich.account.entity.Account;
import com.travnich.account.entity.ClassAccount;
import com.travnich.account.entity.Document;
import com.travnich.account.entity.GroupAccount;
import com.travnich.account.list.MyArrayList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelParser {

    private static final Pattern PATTERN_GROUP = Pattern.compile("\\d{2}");
    private static final Pattern PATTERN_ACCOUNT = Pattern.compile("\\d{4}");
    private static final Pattern PATTERN_CLASS = Pattern.compile("\\s*КЛАСС\\s+\\d+\\s*");
    private static final Pattern PATTERN_SUMMARY_CLASS = Pattern.compile("ПО(\\s+)КЛАССУ");
    private static final String STOP_WORD = "б/сч";

    private MyArrayList<Account> accounts = new MyArrayList<>();
    private MyArrayList<GroupAccount> groupAccounts = new MyArrayList<>();
    private MyArrayList<ClassAccount> classAccounts = new MyArrayList<>();
    private Document document;
    private MultipartFile file;

    public ExcelParser(Document document, MultipartFile file) {
        this.document = document;
        this.file = file;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<GroupAccount> getGroupAccounts() {
        return groupAccounts;
    }

    public List<ClassAccount> getClassAccounts() {
        return classAccounts;
    }

    public List<Account> parse() {

        InputStream in = null;
        HSSFWorkbook wb = null;
        try {
            in = file.getInputStream();

            wb = new HSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();

        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            Cell firstCell = cells.next();
            int firstCellType = firstCell.getCellType();
            if (firstCellType == Cell.CELL_TYPE_STRING) {
                String text = firstCell.getStringCellValue().toLowerCase();
                if (text.equals(STOP_WORD)) break;
            }
        }
        it.next();
        Matcher matcher;
        ClassAccount lastClassAccount = null;
        int startGroup = 0, startAccount = 0;
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell firstCell = cells.next();
                int firstCellType = firstCell.getCellType();
                switch (firstCellType) {
                    case Cell.CELL_TYPE_STRING:
                        String cellText = firstCell.getStringCellValue();
                        matcher = PATTERN_ACCOUNT.matcher(cellText);
                        if (matcher.find()) {
                            addAccount(accounts, cells, cellText);
                        }
                        else {
                            matcher = PATTERN_GROUP.matcher(cellText);
                            if (matcher.find()) {
                                addGroupAccount(groupAccounts, accounts.subListWithRef(startAccount, accounts.size()), cells, cellText);
                                startAccount = accounts.size();
                            }
                            else {
                                matcher = PATTERN_CLASS.matcher(cellText);
                                if (matcher.find()) {
                                    String className = matcher.replaceFirst("");
                                    lastClassAccount = new ClassAccount(className, document);
                                }
                                else {
                                    matcher = PATTERN_SUMMARY_CLASS.matcher(cellText);
                                    if (matcher.find()) {
                                        addBankInfoInClassAccount(lastClassAccount, groupAccounts.subListWithRef(startGroup, groupAccounts.size()), cells);
                                        startGroup = groupAccounts.size();
                                    }
                                }
                            }
                        }
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        Double number = firstCell.getNumericCellValue();
                        cellText = ((Integer) number.intValue()).toString();
                        matcher = PATTERN_ACCOUNT.matcher(cellText);
                        if (matcher.find()) {
                            addAccount(accounts, cells, cellText);
                        }
                        else {
                            matcher = PATTERN_GROUP.matcher(cellText);
                            if (matcher.find()) {
                                addGroupAccount(groupAccounts, accounts.subListWithRef(startAccount, accounts.size()), cells, cellText);
                                startAccount = accounts.size();
                            }
                        }
                        break;

                }
                break;
            }
        }

        return accounts;
    }

    private void addBankInfoInClassAccount(
            ClassAccount classAccount,
            List<GroupAccount> groupAccounts,
            Iterator<Cell> cells
    ) {

        Double activeIn, activeOut, passiveIn, passiveOut,debit, credit;
        Cell cell;

        cell = cells.next();
        activeIn = cell.getNumericCellValue();
        cell = cells.next();
        passiveIn = cell.getNumericCellValue();
        cell = cells.next();
        debit = cell.getNumericCellValue();
        cell = cells.next();
        credit = cell.getNumericCellValue();
        cell = cells.next();
        activeOut = cell.getNumericCellValue();
        cell = cells.next();
        passiveOut = cell.getNumericCellValue();

        classAccount.setActiveIn(activeIn);
        classAccount.setActiveOut(activeOut);
        classAccount.setCredit(credit);
        classAccount.setDebit(debit);
        classAccount.setPassiveIn(passiveIn);
        classAccount.setPassiveOut(passiveOut);

//        for (GroupAccount g: groupAccounts) {
//            g.setClassAccount(classAccount);
//        }
        classAccount.setGroupAccounts(groupAccounts);
        classAccounts.add(classAccount);
    }

    private void addGroupAccount(List<GroupAccount> groupAccounts,
                                         List<Account> accounts,
                                         Iterator<Cell> cells,
                                         String firstCellText
    ) {
        Integer bankAccountId;
        Double activeIn, activeOut, passiveIn, passiveOut,debit, credit;

        bankAccountId = Integer.parseInt(firstCellText);
        Cell cell;

        cell = cells.next();
        activeIn = cell.getNumericCellValue();
        cell = cells.next();
        passiveIn = cell.getNumericCellValue();
        cell = cells.next();
        debit = cell.getNumericCellValue();
        cell = cells.next();
        credit = cell.getNumericCellValue();
        cell = cells.next();
        activeOut = cell.getNumericCellValue();
        cell = cells.next();
        passiveOut = cell.getNumericCellValue();

        GroupAccount groupAccount = new GroupAccount(bankAccountId,
                activeIn,
                passiveIn,
                debit,
                credit,
                activeOut,
                passiveOut,
                document);
//        for (Account a: accounts) {
//            a.setGroupAccount(groupAccount);
//        }
        groupAccount.setAccounts(accounts);
        groupAccounts.add(groupAccount);
    }

    private void addAccount(List<Account> accountList,
                            Iterator<Cell> cells,
                            String firstCellText
    ) {
        Integer bankAccountId;
        Double activeIn, activeOut, passiveIn, passiveOut,debit, credit;

        bankAccountId = Integer.parseInt(firstCellText);
        Cell cell;

        cell = cells.next();
        activeIn = cell.getNumericCellValue();
        cell = cells.next();
        passiveIn = cell.getNumericCellValue();
        cell = cells.next();
        debit = cell.getNumericCellValue();
        cell = cells.next();
        credit = cell.getNumericCellValue();
        cell = cells.next();
        activeOut = cell.getNumericCellValue();
        cell = cells.next();
        passiveOut = cell.getNumericCellValue();

        Account account = new Account(bankAccountId,
                activeIn,
                passiveIn,
                debit,
                credit,
                activeOut,
                passiveOut,
                document);
        accountList.add(account);
    }
}
