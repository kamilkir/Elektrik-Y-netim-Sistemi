import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class AboneForm extends JFrame{
    private JPanel panel1;
    private JButton faturaÖdeButton;
    private JButton geçmişFaturalarButton;
    private JButton bizeUlaşınButton;
    private JLabel lbl_musteri;
    public  JTable table1;
    private JLabel lbl_faturayok;
    private JButton btn_guncel_faturalar;
    private JButton oturumuKapatButton;
    public String musterino;
    public Date q = null;
    public  Statement tabloDoldur;
    public  ResultSet tablors;

    public static int table_secili;

    public static int fatura_sayi;

    /*  FATURA ÖDEME EKRANINA VERİ AKTARMA */

    public static int fatura_id;
    public static int fatura_tuketim;
    public static int fatura_birim;
    public static int fatura_vergi;
    public static Double fatura_tutar;
    public static Date fatura_kesim;
    public static Date fatura_son;
    public static Double fatura_bagis;



    public static int get_table_id;

   public static PreparedStatement ps;

    public AboneForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {

                btn_guncel_faturalar.setEnabled(false);
                System.out.println("open form");
                lbl_musteri.setText(GirisForm.aboneForm_lbl_hosgeldiniz);

                try {

                    if(GirisForm.con.isClosed() != true)
                    {
                        System.out.println("Kapalı değil aboneform");
                    }

                }
                catch(Exception ee)
                {
                    JOptionPane.showMessageDialog(null,"Bir hata oluştu: Hata ayrıntıları; \n"+ee.toString());
                }

                try {
                    tablodoldur();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } //



        });

            add(panel1);


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                get_table_id = (int) table1.getValueAt(table1.getSelectedRow(),0);
                fatura_tuketim = (int) table1.getValueAt(table1.getSelectedRow(),1);
                fatura_birim = (int) table1.getValueAt(table1.getSelectedRow(),2);
                fatura_vergi = (int) table1.getValueAt(table1.getSelectedRow(),3);
                fatura_kesim = (Date) table1.getValueAt(table1.getSelectedRow(),4);
                fatura_son = (Date) table1.getValueAt(table1.getSelectedRow(),5);
                fatura_tutar = (Double) table1.getValueAt(table1.getSelectedRow(),6);

                table_secili = 1;

                super.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });
        faturaÖdeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (fatura_sayi == 1)
                {
                    if (table_secili == 1)
                    {
                        OdemeForm frameOdeme = new OdemeForm();
                        frameOdeme.setVisible(true);
                        frameOdeme.setSize(800,500);
                        frameOdeme.setTitle("FATURA ÖDEME EKRANI");
                        //frame.add(panel);
                        fatura_id = get_table_id;





                    }
                    else if (table_secili == 0)
                    {
                        JOptionPane.showMessageDialog(null,"Ödemek istediğiniz faturayı seçin ve 'FATURAYI ÖDE' butonuna tıklayınız.");
                    }

                }
                else if (fatura_sayi == 0)
                {
                    JOptionPane.showMessageDialog(null,"Ödenecek faturanız bulunmamaktadır.");
                }

            }
        });

        geçmişFaturalarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelim2 =  new DefaultTableModel();
                table1.setFocusable(false);
                table1.setRowSelectionAllowed(true);
                table1.setModel(modelim2);
                try {
                    tablodoldurgecmis();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                btn_guncel_faturalar.setEnabled(true);
                faturaÖdeButton.setEnabled(false);
            }
        });
        btn_guncel_faturalar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tablodoldur();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                faturaÖdeButton.setEnabled(true);
            }
        });
        oturumuKapatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GirisForm grs = new GirisForm();
                grs.setVisible(true);
                dispose();
            }
        });
        bizeUlaşınButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iletisimForm iletisim = new iletisimForm();
                iletisim.setSize(750,160);
                iletisim.setTitle("BİZE ULAŞIN");
                iletisim.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    public void tablodoldur() throws Exception {
        Object []baslik={"FATURA  NO","Tüketim","Birim Fiyat","Vergi Oranı","Fatura Kesim Tarihi","Fatura Son Ödeme Tarihi","TUTAR"};
        Object [][]veri;
        String sorgu="SELECT id,tuketim,birim_fiyat,vergi_orani,fatura_kesim,fatura_son_odeme,tutar FROM  faturalar WHERE musteri_no='"+GirisForm.get_aboneno+"' AND " +
                "odenme_durum=false";

        String sorgu2="SELECT count(*) FROM  faturalar WHERE musteri_no='"+GirisForm.get_aboneno+"' AND " +
                "odenme_durum=false";

        try {
            Statement statementa = GirisForm.con.createStatement();
            var rs_query = statementa.executeQuery(sorgu2);
            int SQL_kurumsal_row = 0;
            while(rs_query.next()){

                SQL_kurumsal_row = rs_query.getInt("count(*)");

            }
            if (SQL_kurumsal_row >= 1 )
            {
                fatura_sayi = 1;
            }
            else
            {

                fatura_sayi = 0;
               JOptionPane.showMessageDialog(null,"Ödenecek faturanız bulunmamaktadır.");
            }
        }
        catch (Exception sorgu2ex)
        {

        }

        PreparedStatement st=GirisForm.con.prepareStatement(sorgu);
        ResultSet set=st.executeQuery();
        int count=0;
        set.last();
        count=set.getRow();
        veri=new Object [count][7];
        set.first();

        for(int i=0;i<count;i++){
            for(int j=0;j<7;j++)
            {
                veri[i][j]=set.getObject(j+1);
            }

            set.next();
        }



        System.out.println(get_table_id);





        DefaultTableModel modelim =  new DefaultTableModel(veri,baslik);
        table1.setFocusable(false);
        table1.setRowSelectionAllowed(true);
        table1.setModel(modelim);






        //TableColumn lastColumn = table1.getColumnModel().getColumn(0);
        //table1.removeColumn(lastColumn);
      //  set.close();





    }
    public void tablodoldurgecmis() throws Exception {
         table1.removeAll();
        Object []baslik={"FATURA  NO","Tüketim","Tutar","Ödeme Tarihi","Fatura Son Ödeme Tarihi","Kesim Tarihi"};
        Object [][]veri;
        String sorgu="SELECT id,tuketim,tutar,odeme_tarihi,odeme_son_tarihi,kesim_tarihi FROM  odenmis_faturalar WHERE musteri_no='"+GirisForm.get_aboneno+"'";

        String sorgu2="SELECT count(*) FROM  odenmis_faturalar WHERE musteri_no='"+GirisForm.get_aboneno+"'";

        try {
            Statement statementa = GirisForm.con.createStatement();
            var rs_query = statementa.executeQuery(sorgu2);
            int SQL_kurumsal_row = 0;
            while(rs_query.next()){

                SQL_kurumsal_row = rs_query.getInt("count(*)");

            }
            if (SQL_kurumsal_row >= 1 )
            {
                fatura_sayi = 1;
            }
            else
            {

                fatura_sayi = 0;
                JOptionPane.showMessageDialog(null,"Ödenmiş faturanız bulunmamaktadır.");
            }
        }
        catch (Exception sorgu2ex)
        {

        }

        PreparedStatement st=GirisForm.con.prepareStatement(sorgu);
        ResultSet set=st.executeQuery();
        int count=0;
        set.last();
        count=set.getRow();
        veri=new Object [count][6];
        set.first();

        for(int i=0;i<count;i++){
            for(int j=0;j<6;j++)
            {
                veri[i][j]=set.getObject(j+1);
            }

            set.next();
        }



        System.out.println(get_table_id);





        DefaultTableModel modelim =  new DefaultTableModel(veri,baslik);
        table1.setFocusable(false);
        table1.setRowSelectionAllowed(true);
        table1.setModel(modelim);






        //TableColumn lastColumn = table1.getColumnModel().getColumn(0);
        //table1.removeColumn(lastColumn);
        //  set.close();





    }

}
