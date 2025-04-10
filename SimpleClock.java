//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame {

    Calendar calendar;
    SimpleDateFormat timeFormat;
    private final SimpleDateFormat TIME_FORMAT_24 = new SimpleDateFormat("HH:mm:ss"); // 24-hour format;
    private final SimpleDateFormat TIME_FORMAT_GMT = new SimpleDateFormat("hh:mm:ss a"); // 24-hour format;
    private final SimpleDateFormat TIME_FORMAT_12 = new SimpleDateFormat("hh:mm:ss a"); // 24-hour format;
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
    }

    public ActionListener setMilitaryTimeButton() {
        return e -> {
//            timeFormat = timeFormat.equals(TIME_FORMAT_24)  ? TIME_FORMAT_12 : TIME_FORMAT_24;
            if (timeFormat.equals(TIME_FORMAT_24)) {
                timeFormat = TIME_FORMAT_12;
            } else if (timeFormat.equals(TIME_FORMAT_12)) {
                timeFormat = TIME_FORMAT_24;
            }
        };
    }

    public ActionListener setGMTTimeButton() {
        return e -> {
            ZoneId id = ZoneId.of("GMT");
            TIME_FORMAT_GMT.setTimeZone(TimeZone.getTimeZone(id));
            if (timeFormat.equals(TIME_FORMAT_GMT)) {
                timeFormat = TIME_FORMAT_12;
            } else if (timeFormat.equals(TIME_FORMAT_12)) {
                timeFormat = TIME_FORMAT_GMT;
            }

        };
    }


    public static void main(String[] args) {
        new SimpleClock();
    }
}
