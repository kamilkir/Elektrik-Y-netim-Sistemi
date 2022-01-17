import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Random;


public class AboneUyelik extends JFrame {
    private JButton btn_uyelik;
    private JTextField tb_ad;
    private JTextField tb_soyad;
    private JTextField tb_tcno;
    private JTextField tb_telefon;
    private JComboBox cb_abonetip;
    private JPanel panel2;
    private JPasswordField tb_uye_sifre;
    private JLabel lbl_ad;
    private JLabel lbl_soyad;
    private JLabel lbl_tc;
    private JButton btn_geri;
    private JPasswordField tb_uye_sifre_tekrar;

    public static int set_musteri_no;

    public static String set_sifre;
    public static String set_ad;
    public static String set_soyad;
    public static String set_tcno;
    public static String set_telno;
    public static String set_musteri_tip;
    public static String set_uyelik_tarih;

    public static int musterino_random;
    public static int musterino_randomk;
    public static String SQL_select;
    public static String SQL_ekle;


    public AboneUyelik() {



        add(panel2);
        cb_abonetip.addItem("BIREYSEL");
        cb_abonetip.addItem("KURUMSAL");


        btn_uyelik.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                set_sifre = tb_uye_sifre.getText();
                set_ad = tb_ad.getText();
                set_soyad = tb_soyad.getText();
                set_tcno = tb_tcno.getText();
                set_telno = tb_tcno.getText();
                set_musteri_tip = (String) cb_abonetip.getSelectedItem();

                set_uyelik_tarih = "0";

                if (set_ad.isEmpty() == false || set_sifre.isEmpty() == false || set_soyad.isEmpty() == false || set_tcno.isEmpty() == false || set_telno.isEmpty() == false || set_musteri_tip.isEmpty() == false)
                {

                    Random r=new Random(); //random sınıfı
                     musterino_random =r.nextInt(100000001,199999999);

                     SQL_select = "SELECT count(*) FROM musteriler WHERE musteri_no ='"+musterino_random+"'";
                    try {
                        Statement statement = GirisForm.con.createStatement();
                        ResultSet rs = statement.executeQuery(SQL_select);
                        int SQL_Row = 0;
                        while(rs.next()){
                            SQL_Row = rs.getInt("count(*)");
                        }
                        if (SQL_Row != 1)
                        {
                            if (set_musteri_tip == "BIREYSEL")
                            {


                                java.util.Date tarih = new java.util.Date();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");

                                SQL_ekle = "INSERT INTO musteriler(musteri_no, sifre, ad, soyad, tc_no, telefon_no, musteri_tip,uyelik_tarih) VALUES(?,?,?,?,?,?,?,?)";
                                PreparedStatement preparedStmt = GirisForm.con.prepareStatement(SQL_ekle);
                                preparedStmt.setInt (1, musterino_random);
                                preparedStmt.setString (2, tb_uye_sifre.getText());
                                preparedStmt.setString   (3, tb_ad.getText());
                                preparedStmt.setString(4,  tb_soyad.getText());
                                preparedStmt.setString   (5, tb_tcno.getText());
                                preparedStmt.setString(6, tb_telefon.getText());
                                preparedStmt.setInt(7, 1);
                                preparedStmt.setString(8, formatter.format(tarih) );
                                preparedStmt.execute();
                                String kopyala_m;
                                kopyala_m = String.valueOf(musterino_random);
                                StringSelection stringSelection = new StringSelection (kopyala_m);
                                Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
                                clpbrd.setContents (stringSelection, null);
                                JOptionPane.showMessageDialog(null,"Üyeliğiniz başarıyla oluşturulmuştur. Abone numaranız: "+musterino_random + "\n\n ABONELİK NUMARANIZ KOPYALANMIŞTIR!");
                            }
                            else if (set_musteri_tip == "KURUMSAL")
                            {

                                java.util.Date tarih = new java.util.Date();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");

                                Random r2 =new Random(); //random sınıfı
                                musterino_randomk =r2.nextInt(200000010,299999999);

                                SQL_ekle = "INSERT INTO k_musteriler(musteri_no, sifre, firma, firma_yetkili, vergi_no, telefon_no, musteri_tip,uyelik_tarih) VALUES(?,?,?,?,?,?,?,?)";
                                PreparedStatement preparedStmt = GirisForm.con.prepareStatement(SQL_ekle);
                                preparedStmt.setInt (1, musterino_randomk);
                                preparedStmt.setString (2, tb_uye_sifre.getText());
                                preparedStmt.setString   (3, tb_ad.getText());
                                preparedStmt.setString(4,  tb_soyad.getText());
                                preparedStmt.setString   (5, tb_tcno.getText());
                                preparedStmt.setString(6, tb_telefon.getText());
                                preparedStmt.setInt(7, 2);
                                preparedStmt.setString(8, formatter.format(tarih) );
                                preparedStmt.execute();
                                String kopyala_m;
                                kopyala_m = String.valueOf(musterino_randomk);
                                StringSelection stringSelection = new StringSelection (kopyala_m);
                                Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
                                clpbrd.setContents (stringSelection, null);
                                JOptionPane.showMessageDialog(null,"Üyeliğiniz başarıyla oluşturulmuştur. Abone numaranız: "+musterino_randomk + "\n\n ABONELİK NUMARANIZ KOPYALANMIŞTIR!");


                            }


                        }



                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }


                }
                else if (set_ad.isEmpty() == true || set_sifre.isEmpty() == true || set_soyad.isEmpty() == true || set_tcno.isEmpty() == true || set_telno.isEmpty() == true || set_musteri_tip.isEmpty() == true)
                {
                    JOptionPane.showMessageDialog(null,"Üyelik alanları boş bırakılamaz.");
                }
                }



        });
        cb_abonetip.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (cb_abonetip.getSelectedItem() == "BIREYSEL")
                {
                    lbl_ad.setText("Ad: ");
                    lbl_soyad.setText("Soyad : ");
                    lbl_tc.setText("TC No: ");

                }
                else if (cb_abonetip.getSelectedItem() == "KURUMSAL")
                {
                    lbl_ad.setText("Firma Adı: ");
                    lbl_soyad.setText("Firma Yetkili Ad - Soyad : ");
                    lbl_tc.setText("Firma Vergi No:");
                }
            }
        });
        btn_geri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GirisForm grs = new GirisForm();
                grs.setVisible(true);
                dispose();
            }
        });
    }
}

