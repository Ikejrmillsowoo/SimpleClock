//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class SimpleClock extends JFrame {
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        JButton militaryTimeButton;
        JButton localGMTButton;
        String time;
        String day;
        String date;


        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 20,10));
            this.setSize(420, 300);
            this.setResizable(false);

    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 65));
            timeLabel.setBackground(Color.CYAN);
            timeLabel.setForeground(Color.BLACK);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,40));
            dayLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,36));
            dateLabel.setLayout(new FlowLayout(FlowLayout.CENTER,0,40));

            militaryTimeButton = new JButton("24 hour");
            militaryTimeButton.addActionListener(setMilitaryTimeButton());
            militaryTimeButton.setLayout(new FlowLayout(FlowLayout.LEADING));


            localGMTButton = new JButton("Local/ GMT");
            localGMTButton.addActionListener(setMilitaryTimeButton());
            localGMTButton.setLayout(new FlowLayout(FlowLayout.TRAILING));
    
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);

            this.add(militaryTimeButton);
            this.add(localGMTButton);
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

        public ActionListener setMilitaryTimeButton(){
            return e -> {
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); // 24-hour format
                time = timeFormat.format(GregorianCalendar.getInstance().getTime());
                timeLabel.setText(time);
            };
            }

        public static void main(String[] args) {
            new SimpleClock();
        }
    }
