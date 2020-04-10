package company.vanolincikus;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class CreditFrame extends JFrame implements ActionListener {

    ArrayList<Credit> credits = new ArrayList<>();
    public Credit credit;
    public JTextField creditNameTextField;
    public JTextField amountDateTextField;
    public JTextField finalMonthTextField;
    public JTextField monthAmountTextField;
    public JButton OkButton;
    public JTable creditTable;
    public DefaultTableModel tableModel;
    public JLabel creditNameLabel;
    public JLabel amountDateLabel;
    public JLabel finalMonthLabel;
    public JLabel monthAmountLabel;
    public SimpleDateFormat closeCredit;
    public JButton showNearestButton;
    public JTextField nearestCreditField;
    public long diff;
    public long MinDiff = Long.MAX_VALUE;
    public Date currentDate = new Date();
    public Credit nearestCredit;


    public CreditFrame() {

        setLayout(new FlowLayout());
        setSize(1000, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        creditNameLabel = new JLabel("Credit name :");
        add(creditNameLabel);

        creditNameTextField = new JTextField("                  ");
        add(creditNameTextField);

        amountDateLabel = new JLabel("Amount before : ");
        add(amountDateLabel);

        amountDateTextField = new JTextField("              ");
        add(amountDateTextField);


        finalMonthLabel = new JLabel("Last amount on :");
        add(finalMonthLabel);

        finalMonthTextField = new JTextField("                ");
        add(finalMonthTextField);

        monthAmountLabel = new JLabel("Amount");
        add(monthAmountLabel);

        monthAmountTextField = new JTextField("        ");
        add(monthAmountTextField);

        OkButton = new JButton("ADD");
        add(OkButton);
        OkButton.addActionListener(this);

        creditTable = new JTable();
        JScrollPane creditPane = new JScrollPane();
        String collumns[] = {"Credit org", "Amount before", "End of credit", "Month amount"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(collumns);
        creditTable.setModel(tableModel);
        creditPane.setViewportView(creditTable);
        add(creditPane);

        showNearestButton = new JButton("Print Nearest");
        add(showNearestButton);
        showNearestButton.addActionListener(this);

        nearestCreditField = new JTextField("                                                     ");
        add(nearestCreditField);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OkButton) {
            credit = new Credit();
            credit.setCreditName(creditNameTextField.getText());
            credit.setAmountDate( amountDateTextField.getText());
            try {//създаваме шаблон по който ще форматираме датата
                closeCredit = new SimpleDateFormat("dd/MM/yyyy");
                //парсваме от стринга въведен в текстовото поле според шаблона със ключовата
                //дума parse и присвояваме значението към credit.finalMonth
                credit.setFinalMonth(closeCredit.parse(finalMonthTextField.getText()));
            } catch (ParseException event) {
                JOptionPane.showMessageDialog(null, "Spell the end date in days/month/year", "Error", JOptionPane.ERROR_MESSAGE);
            }
            credit.setMonthAmount( Double.parseDouble(monthAmountTextField.getText()));
            //добавяма обект credit към ArrayList credits
            credits.add(credit);
            //зареждаме стойностите в таблицата
            Object[] row = new Object[4];
            row[0] = credit.getCreditName();
            row[1] = "before " + credit.getAmountDate();
            row[2] = closeCredit.format(credit.getFinalMonth());
            row[3] = credit.getMonthAmount();
            tableModel.addRow(row);

            creditNameTextField.setText ("                           ");
            amountDateTextField.setText ("             ");
            finalMonthTextField.setText ("                           ");
            monthAmountTextField.setText("                            ");


        } else if (e.getSource() == showNearestButton) {
            for (int i = 0; i < credits.size(); i++) {
                diff = credits.get(i).getFinalMonth().getTime() - currentDate.getTime();
                if (diff < MinDiff) {
                    MinDiff = diff;
                    //присвояваме към nearestCredit кредита с най-скоро изтичащата дата
                    nearestCredit = credits.get(i);
                }
            }
            String message = nearestCredit.getCreditName() + nearestCredit.getAmountDate();
            nearestCreditField.setText(message);
        }
    }
}
