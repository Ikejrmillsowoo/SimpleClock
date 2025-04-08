//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TimeZone;


public class SimpleClock extends JFrame {

    Calendar calendar;
    SimpleDateFormat timeFormat;
    SimpleDateFormat timeFormat24;
    SimpleDateFormat timeFormatGMT;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;

    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;
    JButton militaryTimeButton;
    JButton localGMTButton;
    String time;
    // String time24;
    String day;
    String date;


    SimpleClock() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Digital Clock");
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        this.setSize(420, 300);
        this.setResizable(false);


        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        timeFormat24 = new SimpleDateFormat("HH:mm:ss"); // 24-hour format
        timeFormatGMT = new SimpleDateFormat("hh:mm:ss a"); // 24-hour format
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 65));
        timeLabel.setBackground(Color.CYAN);
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setOpaque(true);
        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Ink Free", Font.BOLD, 40));
        dayLabel.setLayout(new FlowLayout(FlowLayout.CENTER));

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Free", Font.BOLD, 36));
        dateLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));

        militaryTimeButton = new JButton("24 hour");
        militaryTimeButton.addActionListener(setMilitaryTimeButton());
        add(militaryTimeButton);
        militaryTimeButton.setLayout(new FlowLayout(FlowLayout.TRAILING));


        localGMTButton = new JButton("Local/ GMT");
        localGMTButton.addActionListener(setGMTTimeButton());
        localGMTButton.setLayout(new FlowLayout(FlowLayout.TRAILING));

        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);

        JPanel btnPanel = new JPanel();
        btnPanel.add(militaryTimeButton);
        btnPanel.add(localGMTButton);

        this.add(btnPanel);
        this.setVisible(true);

        setTimer();
    }

    public void setTimer() {
        Runnable task = () -> {
            while (true) {
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);

                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);

                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        System.out.println(thread.getPriority());
    }

    public ActionListener setMilitaryTimeButton() {
        return e -> {
            time = timeFormat24.format(GregorianCalendar.getInstance().getTime());
            timeLabel.setText(time);

//            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); // 24-hour format
//            time = timeFormat24.format(GregorianCalendar.getInstance().getTime());
//            timeLabel.setText(time);
        };
    }

    public ActionListener setGMTTimeButton() {
        return e -> {
            ZoneId id = ZoneId.of("GMT");
            timeFormatGMT.setTimeZone(TimeZone.getTimeZone(id));
            time = timeFormatGMT.format(new GregorianCalendar().getTime());
            timeLabel.setText(time);
        };
    }


    public static void main(String[] args) {
        new SimpleClock();
    }
}
