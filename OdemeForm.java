import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class OdemeForm extends JFrame {
    private JPanel panel1;
    private JFormattedTextField tb_kart;
    private JTextField tb_kart_c;
    private JButton btn_odeme;
    private JLabel lbl_faturano;
    private JLabel lbl_tuketim;
    private JLabel lbl_birim;
    private JLabel lbl_vergi;
    private JLabel lbl_tarihkesim;
    private JLabel lbl_faturason;
    private JLabel lbl_bagis;
    private JLabel lbl_tutar;
    private JTextField tb_date2;
    private JTextField tb_date1;
    private JButton btn_geri;


    public static Double toplam_tutar = 0.0;

    private static Double kdv_kurumsal = 1.15;
    private static Double kdv_bireysel = 1.18;

    public static Double kurumsal_bagis = 0.05;
    public static Double bireysel_bagis = 0.01;

    public static int kart;
    public static int date1 ;
    public static int date2;
    public static int cvv;
    public static int kontrol;

    public OdemeForm() {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {

                try {

                    if (GirisForm.con.isClosed() != true) {
                        System.out.println("Kapalı değil OdemeForm");
                    }

                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, "Bir hata oluştu: Hata ayrıntıları; \n" + ee.toString());
                }
                lbl_birim.setText(String.valueOf(AboneForm.fatura_birim) + " TL");
                lbl_faturano.setText(String.valueOf(AboneForm.fatura_id));
              //  lbl_bagis.setText(String.valueOf(AboneForm.fatura_bagis) + "%");
                lbl_faturason.setText(String.valueOf(AboneForm.fatura_son));
                lbl_tarihkesim.setText(String.valueOf(AboneForm.fatura_kesim));
                lbl_vergi.setText(String.valueOf(AboneForm.fatura_vergi));
               // lbl_tutar.setText(String.valueOf(AboneForm.fatura_tutar) + " TL");
                lbl_tuketim.setText(String.valueOf(AboneForm.fatura_tuketim) + " kWh");

                if (GirisForm.n == 1) {
                        toplam_tutar = (double) ((AboneForm.fatura_tuketim * AboneForm.fatura_birim) * (kdv_bireysel));
                        lbl_tutar.setText(toplam_tutar.toString() + " TL");
                }
                else if (GirisForm.n == 2) {
                    toplam_tutar = (double) ((AboneForm.fatura_tuketim * AboneForm.fatura_birim) * (kdv_kurumsal));
                     lbl_tutar.setText(toplam_tutar.toString() + " TL");
                }


            } //


        });


        add(panel1);


        btn_odeme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    if (tb_kart.getText().isEmpty() == false || tb_date1.getText().isEmpty() == false || tb_date2.getText().isEmpty() == false || tb_kart_c.getText().isEmpty() == false)
                    {
                        PreparedStatement st = null;
                        PreparedStatement st2 = null;
                        String sql2;
                        java.util.Date tarih = new java.util.Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");

                        try {
                            st = GirisForm.con.prepareStatement("UPDATE faturalar SET odenme_durum = 1, tutar='"+ toplam_tutar+"'"+ "WHERE musteri_no='"+ GirisForm.get_aboneno+"' AND id='"+ lbl_faturano.getText() +"'");
                            st.executeUpdate();


                            sql2 = "INSERT INTO odenmis_faturalar(musteri_no,tutar,tuketim,odeme_tarihi,gecikme_bedeli,odeme_son_tarihi,kesim_tarihi) VALUES(?,?,?,?,?,?,?)";
                            PreparedStatement preparedStmt = GirisForm.con.prepareStatement(sql2);
                            preparedStmt.setInt (1, Integer.parseInt(GirisForm.get_aboneno));
                            preparedStmt.setDouble (2, toplam_tutar);
                            preparedStmt.setInt   (3, AboneForm.fatura_tuketim);
                            preparedStmt.setString(4, formatter.format(tarih) );
                            preparedStmt.setDouble    (5, 0.0);
                            preparedStmt.setString(6, lbl_faturason.getText());
                            preparedStmt.setString(7, lbl_tarihkesim.getText());


                            preparedStmt.execute();


                            System.out.println();

                            JOptionPane.showMessageDialog(null,"Faturanız başarıyla ödenmiştir.");
                            kontrol = 1;
                            dispose();



                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }


                        String SQL_update = "";

                    }
                    else if (tb_kart.getText().isEmpty() == true || tb_date1.getText().isEmpty() == true || tb_date2.getText().isEmpty() == true || tb_kart_c.getText().isEmpty() == true)
                    {

                    }






            }
        });

        btn_geri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);

            }
        });
    }

}
