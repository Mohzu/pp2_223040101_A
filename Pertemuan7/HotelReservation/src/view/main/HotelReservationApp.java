package Pertemuan7.HotelReservation.src.view.main;

import Pertemuan7.HotelReservation.src.view.hotelreservation.ReservationForm;
import javax.swing.*;

public class HotelReservationApp extends JFrame {
    public HotelReservationApp() {
        setTitle("Aplikasi Reservasi Hotel");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
       JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItemReset = new JMenuItem("Reset");
        JMenuItem menuItemExit = new JMenuItem("Exit");

        menuItemReset.addActionListener(e -> ((ReservationForm) getContentPane().getComponent(0)).resetForm());
        menuItemExit.addActionListener(e -> System.exit(0));

        menu.add(menuItemReset);
        menu.add(menuItemExit);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        add(new ReservationForm());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelReservationApp app = new HotelReservationApp();
            app.setVisible(true);
        });
    }
}
