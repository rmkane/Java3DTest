import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BoundSample {
  public static void main(String args[]) {
    JFrame frame = new JFrame("Button Sample");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    final JButton button1 = new JButton("Select Me");
    final JButton button2 = new JButton("No Select Me");

    ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        JButton button = (JButton) actionEvent.getSource();
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        button.setBackground(new Color(red, green, blue));
      }
    };

    PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        String property = propertyChangeEvent.getPropertyName();
        System.out.println(property);
        if ("background".equals(property)) {
          button2.setBackground((Color) propertyChangeEvent.getNewValue());
        }
      }
    };

    button1.addActionListener(actionListener);
    button1.addPropertyChangeListener(propertyChangeListener);
    button2.addActionListener(actionListener);

    Container contentPane = frame.getContentPane();
    contentPane.add(button1, BorderLayout.NORTH);
    contentPane.add(button2, BorderLayout.SOUTH);
    frame.setSize(300, 100);
    frame.setVisible(true);
  }
}