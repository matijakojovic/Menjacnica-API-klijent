package rs.ac.bg.fon.ai.dodatna.kojovic.matija.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xalan.internal.xsltc.dom.AbsoluteIterator;

import rs.ac.bg.fon.ai.dodatna.kojovic.matija.util.Sistem;

public class MenjacnicaGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzValuteZemlja;
	private JLabel lblUValutuZemlje;
	private JComboBox comboBoxIz;
	private JComboBox comboBoxU;
	private JLabel lblIznos;
	private JLabel lblIznos_1;
	private JTextField textFieldIz;
	private JTextField textFieldU;
	private JButton btnKonvertuj;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sistem.ucitajULst();
					MenjacnicaGUI frame = new MenjacnicaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MenjacnicaGUI(){
		initGUI();
	}

	private void initGUI() {
		setTitle("Menjacnica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblIzValuteZemlja());
		contentPane.add(getLblUValutuZemlje());
		contentPane.add(getComboBoxIz());
		contentPane.add(getComboBoxU());
		contentPane.add(getLblIznos());
		contentPane.add(getLblIznos_1());
		contentPane.add(getTextFieldIz());
		contentPane.add(getTextFieldU());
		contentPane.add(getBtnKonvertuj());
		setLocationRelativeTo(null);
	}

	private JLabel getLblIzValuteZemlja() {
		if (lblIzValuteZemlja == null) {
			lblIzValuteZemlja = new JLabel("Iz valute zemlja:");
			lblIzValuteZemlja.setBounds(39, 30, 110, 16);
		}
		return lblIzValuteZemlja;
	}
	private JLabel getLblUValutuZemlje() {
		if (lblUValutuZemlje == null) {
			lblUValutuZemlje = new JLabel("U valutu zemlje:");
			lblUValutuZemlje.setBounds(210, 30, 110, 16);
		}
		return lblUValutuZemlje;
	}
	private JComboBox getComboBoxIz() {
		if (comboBoxIz == null) {
			comboBoxIz = new JComboBox();
			comboBoxIz.setModel(new DefaultComboBoxModel<>(Sistem.imenaZemalja()));
			comboBoxIz.setBounds(39, 66, 110, 22);
		}
		return comboBoxIz;
	}
	private JComboBox getComboBoxU() {
		if (comboBoxU == null) {
			comboBoxU = new JComboBox();
			comboBoxU.setModel(new DefaultComboBoxModel<>(Sistem.imenaZemalja()));
			comboBoxU.setBounds(210, 66, 110, 22);
		}
		return comboBoxU;
	}
	private JLabel getLblIznos() {
		if (lblIznos == null) {
			lblIznos = new JLabel("Iznos:");
			lblIznos.setBounds(39, 117, 110, 16);
		}
		return lblIznos;
	}
	private JLabel getLblIznos_1() {
		if (lblIznos_1 == null) {
			lblIznos_1 = new JLabel("Iznos:");
			lblIznos_1.setBounds(210, 117, 110, 16);
		}
		return lblIznos_1;
	}
	private JTextField getTextFieldIz() {
		if (textFieldIz == null) {
			textFieldIz = new JTextField();
			textFieldIz.setBounds(39, 152, 110, 22);
			textFieldIz.setColumns(10);
		}
		return textFieldIz;
	}
	private JTextField getTextFieldU() {
		if (textFieldU == null) {
			textFieldU = new JTextField();
			textFieldU.setEditable(false);
			textFieldU.setBounds(210, 152, 110, 22);
			textFieldU.setColumns(10);
		}
		return textFieldU;
	}
	private JButton getBtnKonvertuj() {
		if (btnKonvertuj == null) {
			btnKonvertuj = new JButton("Konvertuj");
			btnKonvertuj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					izracunajKurs();			
				}
			});
			btnKonvertuj.setBounds(130, 206, 97, 25);
		}
		return btnKonvertuj;
	}
	private void izracunajKurs() {
		double iznos = Double.parseDouble(textFieldIz.getText());
		if(iznos <= 0){
			JOptionPane.showMessageDialog(contentPane, "Ne mozete konvertovati negativan iznos!", "Greska!", JOptionPane.ERROR_MESSAGE);
		}
		double kurs = Sistem.kurs(comboBoxIz.getSelectedItem().toString(), comboBoxU.getSelectedItem().toString());
		if(kurs <= 0){
			JOptionPane.showMessageDialog(contentPane, "Ne mozete izvrsiti konvertovanje izmedju ove dve valute!", "Greska!", JOptionPane.ERROR_MESSAGE);
		}
		textFieldU.setText("" + iznos * kurs);
		Sistem.konverzija(comboBoxIz.getSelectedItem().toString(), comboBoxU.getSelectedItem().toString(), kurs);
	}
}
