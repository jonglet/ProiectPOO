import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PanelCasier extends JPanel {
	private Chitanta chitanta;
	private MyCardLayout cardLayout;
	private JFormattedTextField tSerieCIClientNou;
	private JFormattedTextField tNrCIClientNou;
	private JFormattedTextField tNrTelCientNou;
	private JTextField tNrAbonament, tNrImprumut, tNumeClientNou, tPrenumeClientNou;
	private JButton bCautaImprumut, bInapoiContNou, bInapoiRezRetur, bFinalizeazaRetur, bAltImprumut, bCreezaContNou, bRezRetur, bCreeazaCont, bLogOut;
	private JCheckBox[] cbFilmeImprumutate;
	private JComboBox<String> cmbTipAbonament;
	private FereastraPrincipala fereastraPrincipala;
	private JPanel pAranjeazaClientNou, pAranjeazaCont, pAranjeazaImprumut, pAranjeazaReturnare;
	private Ascultator ab;
	private ListaFilme listaFilme;
	private ListaImprumut listaImprumut;
	private Imprumut imprumut;

	PanelCasier(FereastraPrincipala fereastraPrincipala) {
		super();

		this.fereastraPrincipala = fereastraPrincipala;
		listaFilme = new ListaFilme();
		listaImprumut = new ListaImprumut();

		cardLayout = new MyCardLayout();
		imprumut = new Imprumut();
		ab = new Ascultator();
		bLogOut = new JButton("LogOut");

		bLogOut.addActionListener(ab);

		setLayout(cardLayout);

		initPanelContCasier();
		initPanelCreeazaContClient();
		initPanelCautaImprumut();

	}

	private void initPanelContCasier() {
		bRezRetur = new JButton("Rezolvati un retur");
		bCreeazaCont = new JButton("Creati un cont");

		JPanel pContCasier = new JPanel();
		pContCasier.add(bLogOut);
		pContCasier.add(bRezRetur);
		pContCasier.add(bCreeazaCont);

		bRezRetur.addActionListener(ab);
		bCreeazaCont.addActionListener(ab);

		pAranjeazaCont = new JPanel();
		pAranjeazaCont.add(pContCasier);

		add(pAranjeazaCont, "Principal");
	}

	private void initPanelCreeazaContClient() {
		try {
			MaskFormatter formatterSerieCI = new MaskFormatter("UU");
			tSerieCIClientNou = new JFormattedTextField(formatterSerieCI);
			tSerieCIClientNou.setColumns(10);

			MaskFormatter formatterNrCI = new MaskFormatter("######");
			tNrCIClientNou = new JFormattedTextField(formatterNrCI);
			tNrCIClientNou.setColumns(10);

			MaskFormatter formatterNrTel = new MaskFormatter("07########");
			tNrTelCientNou = new JFormattedTextField(formatterNrTel);
			tNrTelCientNou.setColumns(10);

		} catch (ParseException e) {
			e.getStackTrace();
		}

		tNumeClientNou = new JTextField(10);
		tPrenumeClientNou = new JTextField(10);
		bCreezaContNou = new JButton("Creati cont");
		cmbTipAbonament = new JComboBox<>();
		JLabel lNume = new JLabel("Nume:");
		JLabel lPrenume = new JLabel("Prenume:");
		JLabel lSerieCI = new JLabel("Serie CI:");
		JLabel lNrCI = new JLabel("Numar CI:");
		JLabel lNrTelefon = new JLabel("Numar telefon:");
		JLabel lTipAbonament = new JLabel("Durata abonament:");

		bInapoiContNou = new JButton("Inapoi");

		cmbTipAbonament.addItem("8 luni");
		cmbTipAbonament.addItem("12 luni");

		bInapoiContNou.addActionListener(ab);
		bCreezaContNou.addActionListener(ab);

		JPanel pCreeazaContClient = new JPanel(new GridLayout(6, 2));
		pCreeazaContClient.add(lNume);
		pCreeazaContClient.add(tNumeClientNou);
		pCreeazaContClient.add(lPrenume);
		pCreeazaContClient.add(tPrenumeClientNou);
		pCreeazaContClient.add(lSerieCI);
		pCreeazaContClient.add(tSerieCIClientNou);
		pCreeazaContClient.add(lNrCI);
		pCreeazaContClient.add(tNrCIClientNou);
		pCreeazaContClient.add(lNrTelefon);
		pCreeazaContClient.add(tNrTelCientNou);
		pCreeazaContClient.add(lTipAbonament);
		pCreeazaContClient.add(cmbTipAbonament);

		JPanel pButoane = new JPanel();
		pButoane.add(bInapoiContNou);
		pButoane.add(bCreezaContNou);

		pAranjeazaClientNou = new JPanel(new BorderLayout());
		pAranjeazaClientNou.add(pCreeazaContClient, BorderLayout.CENTER);
		pAranjeazaClientNou.add(pButoane, BorderLayout.SOUTH);

		add(pAranjeazaClientNou, "ContNou");
	}

	private void initPanelCautaImprumut() {
		//Folosit sa mearga la fereastra cu cele 3 butoane
		JLabel lIDAbonament = new JLabel("Introduceti id-ul abonamentului: ");
		JLabel lIDImprumut = new JLabel("Introduceti id-ul imprumutului: ");
		bCautaImprumut = new JButton("Cautati imprumutul");
		bInapoiRezRetur = new JButton("Inapoi");
		tNrAbonament = new JTextField(4);
		tNrImprumut = new JTextField(4);

		bInapoiRezRetur.addActionListener(ab);
		bCautaImprumut.addActionListener(ab);

		JPanel pAlegeClient = new JPanel(new GridLayout(2, 2));
		pAlegeClient.add(lIDAbonament);
		pAlegeClient.add(tNrAbonament);
		pAlegeClient.add(lIDImprumut);
		pAlegeClient.add(tNrImprumut);


		JPanel pButoane = new JPanel();
		pButoane.add(bInapoiRezRetur);
		pButoane.add(bCautaImprumut);

		pAranjeazaImprumut = new JPanel(new BorderLayout());
		pAranjeazaImprumut.add(pAlegeClient, BorderLayout.CENTER);
		pAranjeazaImprumut.add(pButoane, BorderLayout.SOUTH);

		add(pAranjeazaImprumut, "CautImprumut");
	}

	private void initPanelRezolvaRetur() {
		int n = imprumut.gerNrFilmeImprumutate();

		JLabel lText = new JLabel("Daca un film nu a fost returnat de client, va rugam sa debifati casuta respectiva:");
		bFinalizeazaRetur = new JButton("Finalizati returul");
		bAltImprumut = new JButton("Inapoi");
		cbFilmeImprumutate = new JCheckBox[n];

		bAltImprumut.addActionListener(ab);
		bFinalizeazaRetur.addActionListener(ab);

		JPanel pFilmeImprumutate = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pFilmeImprumutate.setPreferredSize(new Dimension(200, 150));
		for (int i = 0; i < n; i++) {
			cbFilmeImprumutate[i] = new JCheckBox(imprumut.getNumeFilmSiTip(i));
			cbFilmeImprumutate[i].addActionListener(ab);
			cbFilmeImprumutate[i].setSelected(true);
			pFilmeImprumutate.add(cbFilmeImprumutate[i]);
		}

		JPanel pButoane = new JPanel();
		pButoane.add(bAltImprumut);
		pButoane.add(bFinalizeazaRetur);

		chitanta = new Chitanta();

		pAranjeazaReturnare = new JPanel(new BorderLayout());
		pAranjeazaReturnare.add(lText, BorderLayout.NORTH);
		pAranjeazaReturnare.add(pFilmeImprumutate, BorderLayout.WEST);
		pAranjeazaReturnare.add(chitanta, BorderLayout.EAST);
		pAranjeazaReturnare.add(pButoane, BorderLayout.SOUTH);

		add(pAranjeazaReturnare, "Retur");
	}

	class Chitanta extends JPanel {
		private final int latime, inaltime;
		private long zileInceputInchiriere;
		private int[] filmeReturnate;

		Chitanta() {
			setBackground(Color.WHITE);
			zileInceputInchiriere = ChronoUnit.DAYS.between(imprumut.getInceputInchiriere(), LocalDate.now());
			latime = 350;
			inaltime = 400;
			filmeReturnate = new int[imprumut.gerNrFilmeImprumutate()];
			Arrays.fill(filmeReturnate, 1);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			double pretIntarziere = 0;
			double pretPierdere = 0;
			for (int i = 0; i < imprumut.gerNrFilmeImprumutate(); i++) {
				g.setColor(Color.BLACK);
				if (cbFilmeImprumutate[i].isSelected()) {
					g.drawString(imprumut.getNumeFilmSiTip(i), 5, i * 20 + 20);
					g.setColor(Color.GREEN);
					g.drawString("RETURNAT", 280, i * 20 + 20);
				} else {
					filmeReturnate[i] = 0;
					g.drawString(imprumut.getNumeFilmSiTip(i), 5, i * 20 + 20);
					g.setColor(Color.RED);
					g.drawString("PIERDUT", 280, i * 20 + 20);
				}
				if (zileInceputInchiriere > 7) {
					if (filmeReturnate[i] == 1) {
						pretIntarziere += imprumut.getFilm(i).getPretIntarziere();
					}
				}
				if (filmeReturnate[i] == 0) {
					pretPierdere += imprumut.getFilm(i).getPretPierdereFilm();
				}
			}
			g.setColor(Color.BLACK);
			if (zileInceputInchiriere > 7) {
				pretIntarziere = pretIntarziere * (zileInceputInchiriere - 7);
				g.drawString("Pret intarziere: " + pretIntarziere + " RON", 5, getHeight() - 20);
			}
			if (pretPierdere > 0) {
				g.drawString("Pret pierdere filme: " + pretPierdere + " RON", 5, getHeight() - 40);
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(latime, inaltime);
		}

	}


	class Ascultator implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bLogOut) {
				fereastraPrincipala.iesiDinContCurent();
			}
			if (e.getSource() == bInapoiContNou || e.getSource() == bInapoiRezRetur) {
				cardLayout.show(PanelCasier.this, "Principal");
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bCreeazaCont) {
				cardLayout.show(PanelCasier.this, "ContNou");
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bCreezaContNou) {
				if (contNouCreat()) {
					tNumeClientNou.setText("");
					tPrenumeClientNou.setText("");
					tSerieCIClientNou.setValue(null);
					tNrCIClientNou.setValue(null);
					tNrTelCientNou.setValue(null);

					cardLayout.show(PanelCasier.this, "Principal");
					fereastraPrincipala.pack();
				}
				return;
			}
			if (e.getSource() == bRezRetur) {
				cardLayout.show(PanelCasier.this, "CautImprumut");
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bCautaImprumut) {
				int nrAbonat = Integer.parseInt(tNrAbonament.getText());
				int nrImprumut = Integer.parseInt(tNrImprumut.getText());
				imprumut = listaImprumut.getImprumut(nrAbonat, nrImprumut);
				if (imprumut != null) {
					initPanelRezolvaRetur();

					tNrAbonament.setText("");
					tNrImprumut.setText("");

					cardLayout.show(PanelCasier.this, "Retur");
					fereastraPrincipala.pack();
					return;
				}
				afiseazaMesajEroare("Nu s-a gasit imprumutul cautat");
				return;
			}
			if (e.getSource() == bFinalizeazaRetur) {
				List<Film> filmeReturnate = new ArrayList<>();
				for (int i = 0; i < imprumut.gerNrFilmeImprumutate(); i++) {
					if (cbFilmeImprumutate[i].isEnabled()) {
						filmeReturnate.add(imprumut.getFilm(i));
					}
				}
				listaFilme.adaugaStoc(filmeReturnate);
				listaFilme.actualizeazaStoc();
				listaImprumut.stergeImprumut(imprumut);
				listaImprumut.actualizeazaImprumuturi();
				afiseazaMesajInformare("Returul a fost realizat cu succes");
				cardLayout.show(PanelCasier.this, "Principal");
				fereastraPrincipala.pack();
				return;
			}
			if (e.getSource() == bAltImprumut) {
				cardLayout.show(PanelCasier.this, "CautImprumut");
				fereastraPrincipala.pack();
				return;
			}
			for (int i = 0; i < imprumut.gerNrFilmeImprumutate(); i++) {
				if (e.getSource() == cbFilmeImprumutate[i]) {
					int alegere = JOptionPane.showConfirmDialog(PanelCasier.this,
					                                            "Sunteti sigur ca clientul a pierdut filmul?");
					// 0 inseamna ca s-a apasat YES
					if (alegere == 0) {
						cbFilmeImprumutate[i].setEnabled(false);
					} else {
						cbFilmeImprumutate[i].setSelected(true);
					}
					fereastraPrincipala.pack();
					chitanta.repaint();
					return;
				}
			}
		}

		private boolean contNouCreat() {
			if (tNumeClientNou.getText().matches("[A-Z][a-z]*")) {
				// Prenumele trebuie sa fie de forma:
				// [A-Z]                    - trebuie sa inceapa cu litera mare
				// [a-z]*                   - orice numar de litere
				// ?-(\\p{Upper}[a-z]*)?    - optional, urmatorul prenume trebuie sa inceapa cu litera mare si
				//                          - intre cele doua sa existe cratima
				if (tPrenumeClientNou.getText().matches("[A-Z][a-z]*?(-[A-Z][a-z]*)?")) {
					// Campurile cu serieCI, numarCI si nrTelefon trebuie sa fie completate
					// Se completeaza automat cu spatiu cand sunt create cu MaskFormatter
					if (!tSerieCIClientNou.getText().equals("") && !tNrCIClientNou.getText().equals("") &&
					    tNrTelCientNou.getText().matches("07[0-9]*")) {
						int durataAbonament;
						if (cmbTipAbonament.getSelectedIndex() == 0) {
							durataAbonament = 8;
						} else {
							durataAbonament = 12;
						}
						ContUtilizator temp = new ContUtilizator(tSerieCIClientNou.getText(), tNrCIClientNou.getText(),
						                                         tNumeClientNou.getText(), tPrenumeClientNou.getText(),
						                                         tNrTelCientNou.getText(), durataAbonament);
						ManagerConturi.scrieCont(temp);
						afiseazaMesajInformare(temp.toString());
						return true;

					} else {
						afiseazaMesajEroare("Toate campurile trebuie completate");
						return false;
					}
				} else {
					afiseazaMesajEroare("Prenumele trebuie introdus sub forma:\n Marian-George");
					return false;
				}
			} else {
				afiseazaMesajEroare("Numele trebuie introdus sub forma:\nPopescu");
				return false;
			}
		}

		private void afiseazaMesajInformare(String mesaj) {
			JOptionPane.showMessageDialog(PanelCasier.this, mesaj, "Contul a fost creat",
			                              JOptionPane.INFORMATION_MESSAGE);
		}

		private void afiseazaMesajEroare(String mesaj) {
			JOptionPane.showMessageDialog(PanelCasier.this, mesaj, "Eroare", JOptionPane.ERROR_MESSAGE);
		}

	}

}
