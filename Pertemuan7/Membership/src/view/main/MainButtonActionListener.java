/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pertemuan7.Membership.src.view.main;

/**
 *
 * @author Moch Zuhdi Maulana N
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButtonActionListener implements ActionListener {
    private MainFrame mainFrame;

    public MainButtonActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFrame.getButtonJenisMember()) {
            // Logika untuk ketika tombol Jenis Member ditekan
            mainFrame.showJenisMemberFrame();
        } else {
            // Logika untuk ketika tombol Member ditekan
            mainFrame.showMemberFrame();
        }
    }
}

