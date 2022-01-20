import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;

public class FereastraCard extends JFrame {
	private final AscultatorButoane ab;
	// Componente folosite de toate ferestrele
	private JButton bLogIn, bExit;
	// Componente folosite in fereastra de logIn
	private JFormattedTextField tSerieCI, tNrCI;
	private JButton bIntraCont;
	// Componente  folosite la alegerea filmelor de inchiriat
	private JButton bInchiriazaFilme;
	private JTextField tAnProd;
	private JRadioButton rbGen, rbAnProd;
	private JComboBox<CategorieFilm> cbGen;
	private TabelFilme tabelFilme;
	// Componente  folosite la creearea unui nou cont
	private JFormattedTextField tSerieCIClientNou, tNrCIClientNou, tNrTelCientNou;
	private JTextField tNumeClientNou, tPrenumeClientNou;
	private JButton bCreezaContNou;
	// Componente  folosite la cautarea unui imprumut
	private JTextField tNrAbonament, tImprumutID;
	private JButton bCautaImprumut;
	// Componente folosite la rezolvarea unui retur
	private JButton bContinuaImprumut, bAltImprumut;
	private JCheckBox[] cbFilmeImprumutate;
	// Componente  folosite la ultimul pas din procesul de inchiriere,
	// cand se afiseaza chitanta
	private JButton bPlatiti, bReturnati, bInchiriazaFilmeAlese;
	// Componente folosite in fereastra casierului
	private JButton bRezRetur, bCreeazaCont;
	// Componente folosite in fereastra administratoului
	private JButton bSalveaza, bAdaugaRand, bEliminaRand;
	private PanelUtilizator panelUtilizator;
	private PanelCasier panelCasier;
	private PanelAdministrator panelAdministrator;

	FereastraCard(String titlu) {
		super(titlu);
		ab = new AscultatorButoane();

		getContentPane().add(new PanelAdministrator(this));
		//initPanelLogIn();

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void initPanelLogIn() {
		try {
			// Pot fi introduse doar litere si minusculele vor fi
			// automat transformate in majuscule
			MaskFormatter formatterLitere = new MaskFormatter("UU");
			tSerieCI = new JFormattedTextField(formatterLitere);
			formatterLitere.setPlaceholderCharacter(' ');
			tSerieCI.setColumns(10);

			// Doar 6 cifre pot fi puse in textField
			MaskFormatter formatterCifre = new MaskFormatter("######");
			tNrCI = new JFormattedTextField(formatterCifre);
			formatterCifre.setPlaceholderCharacter(' ');
			tNrCI.setColumns(10);

		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}

		JLabel lSerieCI = new JLabel("Serie CI:");
		JLabel lNrCI = new JLabel("Numar CI:");

		bLogIn = new JButton("Intrati in cont");
		bLogIn.addActionListener(ab);
		bExit = new JButton("Exit");

		JPanel pLogIn = new JPanel(new GridLayout(3, 2, 5, 5));
		pLogIn.add(lSerieCI);
		pLogIn.add(tSerieCI);
		pLogIn.add(lNrCI);
		pLogIn.add(tNrCI);
		pLogIn.add(new JButton("Exit"));
		pLogIn.add(bLogIn);

		getContentPane().removeAll();
		getContentPane().add(pLogIn);

		pack();
	}

	private void initPanelContCasier() {

		bRezRetur = new JButton("Rezolvati un retur");
		bCreeazaCont = new JButton("Creati un cont");

		JPanel pContCasier = new JPanel();
		pContCasier.add(bRezRetur);
		pContCasier.add(bCreeazaCont);

		bRezRetur.addActionListener(ab);
		bCreeazaCont.addActionListener(ab);

		getContentPane().removeAll();
		getContentPane().add(pContCasier);

		pack();
	}

	private void initPanelRezolvaRetur(Imprumut imprumut) {
		int n = imprumut.getNrFilme();

		JLabel lText = new JLabel("Selectati filmele care au fost returnate de catre client:");
		bContinuaImprumut = new JButton("Contiunua");
		bAltImprumut = new JButton("Cautati alt imprumut");
		cbFilmeImprumutate = new JCheckBox[n];

		bAltImprumut.addActionListener(ab);
		bContinuaImprumut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initPanelTerminaRetur(imprumut);
			}
		});

		JPanel pFilmeImprumutate = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pFilmeImprumutate.setPreferredSize(new Dimension(200, 150));
		for (int i = 0; i < n; i++) {
			cbFilmeImprumutate[i] = new JCheckBox(imprumut.getNumeFilmTip(i));
			pFilmeImprumutate.add(cbFilmeImprumutate[i]);
		}

		JPanel pButoane = new JPanel();
		pButoane.add(bAltImprumut);
		pButoane.add(bContinuaImprumut);

		getContentPane().removeAll();
		getContentPane().add(lText, BorderLayout.NORTH);
		getContentPane().add(pFilmeImprumutate, BorderLayout.WEST);
		getContentPane().add(pButoane, BorderLayout.SOUTH);
		pack();
	}

	private void initPanelTerminaRetur(Imprumut imprumut) {
		//cele care nu au fost selectate se trimit aici
	}

	private void initPanelCreeazaContClient() {
		try {
			MaskFormatter formatterSerieCI = new MaskFormatter("UU");
			formatterSerieCI.setPlaceholderCharacter(' ');
			tSerieCIClientNou = new JFormattedTextField(formatterSerieCI);
			tSerieCI.setColumns(10);

			MaskFormatter formatterNrCI = new MaskFormatter("######");
			formatterNrCI.setPlaceholderCharacter(' ');
			tNrCIClientNou = new JFormattedTextField(formatterNrCI);
			tNrCIClientNou.setColumns(10);

			MaskFormatter formatterNrTel = new MaskFormatter("07########");
			formatterNrTel.setPlaceholderCharacter(' ');
			tNrTelCientNou = new JFormattedTextField(formatterNrTel);
			tNrTelCientNou.setColumns(10);

		} catch (ParseException ignored) {
		}

		tNumeClientNou = new JTextField(10);
		tPrenumeClientNou = new JTextField(10);
		bCreezaContNou = new JButton("Creati cont");
		JLabel lNume = new JLabel("Nume:");
		JLabel lPrenume = new JLabel("Prenume:");
		JLabel lSerieCI = new JLabel("Serie CI:");
		JLabel lNrCI = new JLabel("Numar CI:");
		JLabel lNrTelefon = new JLabel("Numar telefon:");

		bCreezaContNou.addActionListener(ab);

		JPanel pCreeazaContClient = new JPanel(new GridLayout(5, 2));
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

		JPanel pButoane = new JPanel();
		pButoane.add(bCreezaContNou);

		getContentPane().removeAll();
		getContentPane().add(pCreeazaContClient, BorderLayout.CENTER);
		getContentPane().add(pButoane, BorderLayout.SOUTH);

		pack();
	}

	private void initPanelCautaImprumut() {
		JLabel lIDAbonament = new JLabel("Introduceti id-ul abonamentului: ");
		JLabel lIDImprumut = new JLabel("Introduceti id-ul imprumutului: ");
		bCautaImprumut = new JButton("Cautati imprumutul");
		tNrAbonament = new JTextField(4);
		tImprumutID = new JTextField(4);

		bCautaImprumut.addActionListener(ab);

		JPanel pAlegeClient = new JPanel(new GridLayout(2, 2));
		pAlegeClient.add(lIDAbonament);
		pAlegeClient.add(tNrAbonament);
		pAlegeClient.add(lIDImprumut);
		pAlegeClient.add(tImprumutID);


		JPanel pCautaClient = new JPanel();
		pCautaClient.add(bExit);
		pCautaClient.add(bCautaImprumut);

		getContentPane().removeAll();
		getContentPane().add(pAlegeClient, BorderLayout.CENTER);
		getContentPane().add(pCautaClient, BorderLayout.SOUTH);

		pack();
	}

	private void initPanelAdministrator() {
		//editati filme
		String[] numeColoane = {"Nume film", "An productie", "Nr. copii", "Gen", "Tip film"};
		Object[][] dateTabel = ListaFilme.getInstance().getListaFilmeAdministrator();
		tabelFilme = new TabelFilme(true);
		JScrollPane sp = new JScrollPane(tabelFilme);
		bSalveaza = new JButton("Salvati tabel");
		bAdaugaRand = new JButton("Adaugati rand");
		bEliminaRand = new JButton("Sterge rand");

		bAdaugaRand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabelFilme.adaugaRand();
			}
		});
		bEliminaRand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabelFilme.eliminaRand();
			}
		});
		bSalveaza.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabelFilme.salveazaTabel();
			}
		});

		JPanel pFilm = new JPanel();
		pFilm.add(sp);

		JPanel pButoaneTabel = new JPanel();
		pButoaneTabel.add(bAdaugaRand);
		pButoaneTabel.add(bEliminaRand);

		JPanel pButon = new JPanel();
		pButon.add(bSalveaza);

		getContentPane().removeAll();
		getContentPane().add(pFilm, BorderLayout.CENTER);
		getContentPane().add(pButoaneTabel, BorderLayout.EAST);
		getContentPane().add(pButon, BorderLayout.SOUTH);

		pack();
	}

	private class PanelChitanta extends JPanel {
		PanelChitanta() {
			super();
			setBackground(Color.WHITE);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (int i = 0; i < ListaFilme.getInstance().getNrFilmeDisponibile(); i++) {
				Film film = ListaFilme.getInstance().getFilmSelectat(i);
				g.drawString(film.toString(), 5, i * 20 + 20);
				g.drawString((film.getPretFilm()) + " Ron/ZI", 280, i * 20 + 20);
			}
			g.drawString("Data returnarii: " + LocalDate.now().plusDays(7), 5, 360);
			g.drawString("Total plata: " + ListaFilme.getInstance().getCostZilnicFilme() * 7 + " RON", 5, 380);
		}

		public Dimension getPreferredSize() {
			return new Dimension(330, 400);
		}

	}


	private class PanelInformatiiRetur extends JPanel {
		PanelInformatiiRetur() {
			super();
			setBackground(Color.WHITE);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

		}

	}


	private class AscultatorButoane implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Se iese din program cand se apasa pe buton
			if (e.getSource() == bExit) {
				FereastraCard.this.dispose();
				return;
			}

			// Se intra intr-un cont si se incarca urmatoarea fereastra
			if (e.getSource() == bLogIn) {
				String serie = tSerieCI.getText();
				String nrCI = tNrCI.getText();
				if (serie.equals("OV") && nrCI.equals("111111")) {
					if (panelAdministrator == null) {
						panelAdministrator = new PanelAdministrator(FereastraCard.this);
					}
					getContentPane().removeAll();
					getContentPane().add(panelAdministrator);
					pack();
					return;
				} else {
					int id = Cont.getIdCont(serie, nrCI);
					if (id > 1000) {
						System.out.println(id);
					} else if (id > 100) {
						initPanelContCasier();
					}
				}
				return;
			}

			// Se afiseaza fereastra cu alegerea filmelor
			if (e.getSource() == bInchiriazaFilmeAlese) {
				return;
			}

			// Afiseaza un messageDialog ca doar casierii pot face un retur
			if (e.getSource() == bReturnati) {
				afiseazaMesajInformatie("Pentru a returna filmele, va rugam sa va adresati unui casier",
				                        "Informatii retur");
				return;
			}
			// Se afiseaza Chitanta daca utilizatorul alege intre 1 si 5 filme
			if (e.getSource() == bInchiriazaFilme) {
				// Se verifica daca utilizatorul a ales mai mult de 5 filme pe care
				// sa le inchirieze
				int nrFilmeAlese = 0;
				for (int i = 0; i < tabelFilme.getRowCount(); i++) {
					// Cast la boolean pentru ca ultima coloana este mereu un checkBox
					// ce este True sau False
					if ((Boolean)tabelFilme.getValueAt(i, tabelFilme.getNR_ULTIM_COLOANA())) {
						nrFilmeAlese++;
					}
				}
				if (nrFilmeAlese == 0) {
					afiseazaMesajEroare("Va rugam sa alegeti cel putin 1 film pe care doriti sa il inchiriati.",
					                    "Numar invalid de filme alese");
					return;
				}
				if (nrFilmeAlese > 5) {
					afiseazaMesajEroare("Va rugam sa alegeti cel mult 5 filme pe care doriti sa le inchiriati.",
					                    "Numar invalid de filme alese");
					return;
				}
				// Se creeaza o lista cu filmele selectate pentru a le folosi in chitanta
				for (int i = 0; i < tabelFilme.getRowCount(); i++) {
					if ((Boolean)tabelFilme.getValueAt(i, tabelFilme.getNR_ULTIM_COLOANA())) {
						String nume = (String)tabelFilme.getValueAt(i, 0);
						String tipFilm = (String)tabelFilme.getValueAt(i, 3);
						ListaFilme.getInstance().adaugaFilmSelectat(nume, tipFilm);
					}
				}
			}

			// Se sorteaza tabelul dupa genul filmului ales
			if ((e.getSource() == rbGen || e.getSource() == cbGen) && rbGen.isSelected()) {
				tabelFilme.sorteazaTabel(cbGen.getItemAt(cbGen.getSelectedIndex()));
				return;
			}

			// Se sorteaza tabelul dupa anul productiei
			if ((e.getSource() == rbAnProd || e.getSource() == tAnProd) && rbAnProd.isSelected()) {
				tabelFilme.sorteazaTabel(tAnProd.getText());
				return;
			}

			// Dupa ce utilizatorul plateste se actualizeaza stocul cu filme
			if (e.getSource() == bPlatiti) {
				//ListaFilme.getInstance().actualizeazaStoc();
				Cont.getContCurent().realizeazaImprumut(ListaFilme.getInstance().getFilmeSelectate());
				afiseazaMesajInformatie("Ati inchiriat filmele cu succes", "Operatiune reusita");
				return;
			}

			if (e.getSource() == bRezRetur) {
				initPanelCautaImprumut();
				return;
			}

			if (e.getSource() == bCreeazaCont) {
				initPanelCreeazaContClient();
				return;
			}

			if (e.getSource() == bCreezaContNou) {
				if (tNumeClientNou.getText().matches("[A-Z][a-z]*")) {
					// Prenumele trebuie sa fie de forma:
					// [A-Z]                    - trebuie sa inceapa cu litera mare
					// [a-z]*                   - orice numar de litere
					// ?-(\\p{Upper}[a-z]*)?    - optional, urmatorul prenume trebuie sa inceapa cu litera mare si
					//                          - intre cele doua sa existe cratima
					if (tPrenumeClientNou.getText().matches("[A-Z][a-z]*?(-[A-Z][a-z]*)?")) {
						// Campurile cu serie, numar si nrTelefon trebuie sa fie completate
						if (!tSerieCIClientNou.getText().equals("  ") && !tNrCIClientNou.getText().equals("  ") &&
						    tNrTelCientNou.getText().matches("07[0-9]*")) {
							Cont temp = new Cont(tNumeClientNou.getText(),
							                     tPrenumeClientNou.getText(),
							                     tSerieCIClientNou.getText(),
							                     tNrCIClientNou.getText(),
							                     tNrTelCientNou.getText());
							temp.scrie(temp);
							afiseazaMesajInformatie(temp.toString(), "Contul a fost creat");
						} else {
							afiseazaMesajEroare("Toate campurile trebuie completate", "Eroare");
						}
					} else {
						afiseazaMesajEroare("Prenumele trebuie introdus sub forma:\n Marian-George", "Eroare");
					}
				} else {
					afiseazaMesajEroare("Numele trebuie introdus sub forma:\nPopescu", "Eroare");
				}
				// TODO: Panel nou in care se afiseaza informatii despre noul cont
				initPanelContCasier();
				return;
			}

			if (e.getSource() == bCautaImprumut) {
				Imprumut temp = Cont.getClientImprumut(tNrAbonament.getText(), tImprumutID.getText());
				if (temp != null) {
					initPanelRezolvaRetur(temp);
				} else {
					System.out.println("nu s-a gasit");
				}
				return;
			}

			if (e.getSource() == bContinuaImprumut) {
				//initPanelTerminaRetur();
				return;
			}

		}

		public void afiseazaMesajInformatie(String mesaj, String titlu) {
			JOptionPane.showMessageDialog(FereastraCard.this, mesaj, titlu, JOptionPane.INFORMATION_MESSAGE);
		}

		public void afiseazaMesajEroare(String mesaj, String titlu) {
			JOptionPane.showMessageDialog(FereastraCard.this, mesaj, titlu, JOptionPane.ERROR_MESSAGE);
		}

	}

}