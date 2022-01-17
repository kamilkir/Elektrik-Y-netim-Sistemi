import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GirisForm extends JFrame{
    private JPanel panel1;
    private JTextField tb_abone;
    private JPasswordField tb_sifre;
    private JButton girisYapButton;
    private JButton kayıtOlButton;

    public static String get_aboneno;
    public static String get_sifre;
    static Connection con;

    public static String SQL_abonesorgu = "";
    public static String SQL_kurumsal = "";
    public static String SQL_kurumsal_bilgi = "";
    public static String SQL_abone_tip = "";
    public static AboneUyelik aboneUyelikForm2 = new AboneUyelik();

    public static String SQL_bireyselbilgi;

    public static String aboneForm_lbl_hosgeldiniz;
    public static String AboneForm_kurumsal_sirket;
    public static String AboneForm_kurumsal_yetkili;
    public static String AboneForm_kurumsal_musterino;
    public static String get_sirket;
    public static String get_yetkili_adsoyad;

    public static String get_bad;
    public static String get_badsoyad;

    public static String AboneForm_bireysel_adsoyad;
    public static String AboneForm_bireysel_musterino;

    public static int n;

    public GirisForm()
    {

        add(panel1);
        setSize(400,200);
       // setMaximumSize();
        setTitle("KIR Enerji Dağıtım - Abonelik Giriş Ekranı");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/elektrikproje","root","");
            if(con.isClosed() != true)
            {
                System.out.println("Kapalı değil");
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Bir hata oluştu: Hata ayrıntıları; \n"+e.toString());
        }



        girisYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               try {
                   n = Integer.parseInt(tb_abone.getText());
               }
               catch (Exception convertHata)
               {
                  JOptionPane.showMessageDialog(null,"Abonelik numaranız rakamlardan oluşmalıdır. Detaylı hata: "+convertHata.toString());
               }


              while (n >= 10) {
                  n /= 10;

              }
              if (n == 1)
              {

                  get_aboneno = tb_abone.getText();
                  get_sifre = tb_sifre.getText();

                  if (get_aboneno.isEmpty() == false && get_sifre.isEmpty() == false)
                  {
                      SQL_abonesorgu = "SELECT count(*) FROM musteriler WHERE musteri_no="+get_aboneno+" AND sifre='"+get_sifre+"'";
                      try(Statement statement = con.createStatement())
                      {
                          ResultSet rs = statement.executeQuery(SQL_abonesorgu);
                          int SQL_Row = 0;
                          while(rs.next()){
                              SQL_Row = rs.getInt("count(*)");
                          }

                          if (SQL_Row == 1)
                          {

                                  try {

                                      Statement statementa = con.createStatement();
                                      Statement statementkb = con.createStatement();
                                      SQL_bireyselbilgi = "SELECT ad,soyad FROM musteriler WHERE musteri_no='"+get_aboneno+"'";
                                      var rs_kb = statementa.executeQuery(SQL_bireyselbilgi);
                                      String SQL_bireyselgad = "";
                                      String SQL_bireyselgsoyad = "";
                                      while (rs_kb.next())
                                      {
                                          SQL_bireyselgad = rs_kb.getString("ad");
                                          SQL_bireyselgsoyad = rs_kb.getString("soyad");
                                          get_bad = SQL_bireyselgad;
                                          get_badsoyad = SQL_bireyselgsoyad;
                                      }
                                  }
                                  catch (Exception krm)
                                  {

                                  }

                                  aboneForm_lbl_hosgeldiniz = "Hoşgeldiniz, "+get_bad+" "+get_badsoyad+"";
                                  AboneForm aboneframe = new AboneForm();
                                  aboneframe.setVisible(true);
                                  aboneframe.setSize(650,400);
                                  aboneframe.setTitle("ABONE FATURA ÖDEME EKRANI - BIREYSEL ");
                                  setVisible(false);


                          }
                          else
                          {
                              JOptionPane.showMessageDialog(null,"Bireysel abonelik bilgileri yanlış");
                          }

                      }
                      catch (Exception eSQL_Abonesorgu)
                      {
                          JOptionPane.showMessageDialog(null,"Üyelik SQL hatası: "+eSQL_Abonesorgu.toString());
                      }
                  }
                  else if ((get_aboneno.isEmpty() == true && get_sifre.isEmpty() == true) || ((get_aboneno.isEmpty() == true || get_sifre.isEmpty() == true) ))
                  {
                      JOptionPane.showMessageDialog(null,"Abone no veya şifre alanı boş olamaz.");
                      //System.out.println("Test");
                  }
              }
              else if (n == 2)
              {
                  get_aboneno = tb_abone.getText();
                  get_sifre = tb_sifre.getText();
                  SQL_kurumsal = "SELECT count(*) FROM k_musteriler WHERE musteri_no='"+get_aboneno+"' AND sifre='"+get_sifre+"'";
                  try(Statement statementa = con.createStatement())
                  {
                      var rsim = statementa.executeQuery(SQL_kurumsal);
                      int SQL_kurumsal_row = 0;
                      boolean start = true;
                      while(rsim.next()){

                          SQL_kurumsal_row = rsim.getInt("count(*)");
                          System.out.println(SQL_kurumsal_row);
                      }

                      if (SQL_kurumsal_row == 1)
                      {
                          try {
                              Statement statementkb = con.createStatement();
                              SQL_kurumsal_bilgi = "SELECT firma,firma_yetkili FROM k_musteriler WHERE musteri_no='"+get_aboneno+"'";
                              var rs_kb = statementa.executeQuery(SQL_kurumsal_bilgi);
                              String SQL_kurumsal_kb = "";
                              String SQL_kurumsal_kb_yt = "";
                              while (rs_kb.next())
                              {
                                  SQL_kurumsal_kb = rs_kb.getString("firma");
                                  SQL_kurumsal_kb_yt = rs_kb.getString("firma_yetkili");
                                  get_sirket = SQL_kurumsal_kb;
                                  get_yetkili_adsoyad = SQL_kurumsal_kb_yt;
                              }
                          }
                          catch (Exception krm)
                          {

                          }

                          aboneForm_lbl_hosgeldiniz = "Hoşgeldiniz, "+get_sirket+". \n Firma Yetkilisi: "+ get_yetkili_adsoyad;
                          AboneForm aboneframe = new AboneForm();
                          aboneframe.setVisible(true);
                          aboneframe.setSize(650,400);
                          aboneframe.setTitle("ABONE FATURA ÖDEME EKRANI - KURUMSAL");
                          setVisible(false);


                      }
                      else
                      {
                          JOptionPane.showMessageDialog(null,"KURUMSAL abonelik bilgileri yanlış");
                      }

                  }
                  catch (Exception eSQL_Abonesorgu)
                  {
                      JOptionPane.showMessageDialog(null,"Üyelik SQL hatası: "+eSQL_Abonesorgu.toString());
                  }



              }



              }


        });
       kayıtOlButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AboneUyelik frame = new AboneUyelik();
            frame.setVisible(true);
            frame.setSize(400,400);
            frame.setTitle("Abone Üyelik Formu");
            //frame.add(panel);

            setVisible(false);



        }
    });
    }


}
