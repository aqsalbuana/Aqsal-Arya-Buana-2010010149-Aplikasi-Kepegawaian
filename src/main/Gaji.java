package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Gaji extends javax.swing.JInternalFrame {

    public Gaji() {
        initComponents();
        clear();
        tampil();
        setNIP();
        setGol();
    }
    
    public final Connection conn = new Koneksi().getKoneksi();
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    DefaultTableModel tabMode;
    
    public void tampil(){
        Object [] baris = {"NO","kode_gaji", "NIP", "kode_golongan", "tunjangan_kesehatan" ,"tunjangan_keluarga", "total_gaji"};
        tabMode = new DefaultTableModel(null, baris);
        tableGaji.setModel(tabMode);
        try {
            String sql = "SELECT * FROM gaji";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            int no = 0;
            while (rs.next()){
                    no++;
                    
                    
                    String kode_gaji = rs.getString("kode_gaji");
                    String nip = rs.getString("nip");
                    String kode_golongan = rs.getString("kode_golongan");
                    String tunjangan_kesehatan = rs.getString("tunjangan_kesehatan");
                    String tunjangan_keluarga = rs.getString("tunjangan_keluarga");
                    String total_gaji = rs.getString("total_gaji");

                    Object [] data = {no,kode_gaji,nip,kode_golongan,tunjangan_kesehatan,tunjangan_keluarga,total_gaji};
                    tabMode.addRow(data);
            }
        } catch (Exception e){
                System.out.println(e.toString());
        }
    }
    
    public String kodeOtomatis(){
        String kode = "";
        try {
            int kodeLama;
            pst = conn.prepareStatement("SELECT kode_gaji FROM gaji ORDER BY kode_gaji DESC");
            rs = pst.executeQuery();
            if (!rs.next()){
                kode = "GAJI0001";
            } else {
                kodeLama=Integer.parseInt(rs.getString(1).substring(4))+1;
                if(kodeLama<10){
                    kode = "GAJI000"+kodeLama;
                }
                else if(kodeLama >= 10 && kodeLama<100){
                    kode = "GAJI00"+kodeLama;
                }
                else if(kodeLama >= 100 && kodeLama<1000){
                    kode = "GAJI0"+kodeLama;
                }
                else{
                    kode = "GAJI-"+kodeLama;
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return kode;
    }
    
    public void clear(){
        tKodeGaji.setText(kodeOtomatis());
        cNIP.setSelectedItem(null);
        tNama.setText("");
        cGol.setSelectedItem(null);
        tJabatan.setText("");
        tGajiJabatan.setText("");
        rPunya.setSelected(false);
        rTidak.setSelected(false);
        tTunjanganKesehatan.setText("");
        cKeluarga.setSelectedItem(null);
        tTunjanganKeluarga.setText("");
        tTotal.setText("");
    }
    
    public void setNIP(){
        try {
            String sql = "SELECT * FROM karyawan";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                cNIP.addItem(rs.getString("nip"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void setGol(){
        try {
            String sql = "SELECT * FROM golongan";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
                cGol.addItem(rs.getString("kode_golongan"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tKodeGaji = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cNIP = new javax.swing.JComboBox<>();
        tNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cGol = new javax.swing.JComboBox<>();
        tGajiJabatan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tJabatan = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        rPunya = new javax.swing.JRadioButton();
        rTidak = new javax.swing.JRadioButton();
        tTunjanganKesehatan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cKeluarga = new javax.swing.JComboBox<>();
        tTunjanganKeluarga = new javax.swing.JTextField();
        bHitung = new javax.swing.JButton();
        tTotal = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tableGaji = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        bCetak = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));
        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Kode Gaji");

        tKodeGaji.setEditable(false);

        jLabel2.setText("NIP");

        cNIP.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cNIPPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        tNama.setEditable(false);

        jLabel3.setText("Nama");

        jLabel4.setText("Kode Golongan");

        cGol.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cGolPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        tGajiJabatan.setEditable(false);

        jLabel5.setText("Jabatan");

        jLabel6.setText("Gaji Pokok");

        tJabatan.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tKodeGaji)
                            .addComponent(tNama)
                            .addComponent(cNIP, 0, 252, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cGol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tGajiJabatan)
                            .addComponent(tJabatan))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tKodeGaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cNIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cGol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tGajiJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("BPJS");

        buttonGroup1.add(rPunya);
        rPunya.setText("Punya");
        rPunya.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rPunyaMouseClicked(evt);
            }
        });

        buttonGroup1.add(rTidak);
        rTidak.setText("Tidak");
        rTidak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rTidakMouseClicked(evt);
            }
        });

        tTunjanganKesehatan.setEditable(false);

        jLabel9.setText("Tunjangan Keluarga");

        cKeluarga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Menikah", "Sudah Menikah Belum Punya Anak", "Sudah Menikah Punya Anak" }));
        cKeluarga.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cKeluargaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        tTunjanganKeluarga.setEditable(false);

        bHitung.setText("Hitung");
        bHitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHitungActionPerformed(evt);
            }
        });

        tTotal.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tTunjanganKesehatan)
                    .addComponent(cKeluarga, 0, 313, Short.MAX_VALUE)
                    .addComponent(tTunjanganKeluarga)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rPunya)
                                .addGap(18, 18, 18)
                                .addComponent(rTidak))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(bHitung)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tTotal))
                .addGap(60, 60, 60))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rPunya)
                    .addComponent(rTidak))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tTunjanganKesehatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cKeluarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tTunjanganKeluarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bHitung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        scroll.setBackground(new java.awt.Color(0, 204, 255));

        tableGaji.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableGaji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGajiMouseClicked(evt);
            }
        });
        scroll.setViewportView(tableGaji);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        bCetak.setText("Cetak");
        bCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bCetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                                .addGap(46, 46, 46)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(btnKeluar)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnClear)
                    .addComponent(btnKeluar))
                .addGap(18, 18, 18)
                .addComponent(bCetak)
                .addGap(13, 13, 13)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cNIPPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cNIPPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM karyawan WHERE nip = '"+cNIP.getSelectedItem()+"' ";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()){
                tNama.setText(rs.getString("nama"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_cNIPPopupMenuWillBecomeInvisible

    private void cGolPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cGolPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM golongan WHERE kode_golongan = '"+cGol.getSelectedItem()+"' ";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()){
                tJabatan.setText(rs.getString("jenis_golongan"));
                tGajiJabatan.setText(rs.getString("gaji"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_cGolPopupMenuWillBecomeInvisible

    private void cKeluargaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cKeluargaPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if (cKeluarga.getSelectedItem().equals("Belum Menikah")){
            tTunjanganKeluarga.setText("10");
        } else if (cKeluarga.getSelectedItem().equals("Sudah Menikah Belum Punya Anak")){
            tTunjanganKeluarga.setText("20");
        } else if (cKeluarga.getSelectedItem().equals("Sudah Menikah Punya Anak")){
            tTunjanganKeluarga.setText("35");
        }
    }//GEN-LAST:event_cKeluargaPopupMenuWillBecomeInvisible

    private void rPunyaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rPunyaMouseClicked
        // TODO add your handling code here:
        if (rPunya.isSelected()){
            tTunjanganKesehatan.setText("50");
        }
    }//GEN-LAST:event_rPunyaMouseClicked

    private void rTidakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rTidakMouseClicked
        // TODO add your handling code here:
        if (rTidak.isSelected()){
            tTunjanganKesehatan.setText("0");
        }
    }//GEN-LAST:event_rTidakMouseClicked

    private void bHitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHitungActionPerformed
        // TODO add your handling code here:
        int gajiJab = Integer.parseInt(tGajiJabatan.getText());
        int kesehatan = Integer.parseInt(tTunjanganKesehatan.getText());
        int keluarga = Integer.parseInt(tTunjanganKeluarga.getText());
        
        int gatot = gajiJab + kesehatan + keluarga;
        
        tTotal.setText(String.valueOf(gatot));
        
    }//GEN-LAST:event_bHitungActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        String nip = cNIP.getSelectedItem().toString();
        String kode_golongan = cGol.getSelectedItem().toString();

        try {
           int s;
           String sql = "UPDATE gaji SET nip = '"+nip+"', kode_golongan = '"+kode_golongan+"'  , tunjangan_kesehatan = '"+tTunjanganKesehatan.getText()+"', tunjangan_keluarga = '"+tTunjanganKeluarga.getText()+"', total_gaji = '"+tTotal.getText()+"' WHERE kode_gaji = '"+tKodeGaji.getText()+"' ";
           st = conn.createStatement();
           s = st.executeUpdate(sql);
           if (s == 1){
            JOptionPane.showMessageDialog(null, "Sukses");
            clear();
            tampil();
           }
       } catch (Exception e){
            System.out.println(e.toString());
       }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String nip = cNIP.getSelectedItem().toString();
        String kode_golongan = cGol.getSelectedItem().toString();

        try {
           int s;
           String sql = "INSERT INTO gaji VALUES('"+tKodeGaji.getText()+"','"+nip+"','"+kode_golongan+"'  , '"+tTunjanganKesehatan.getText()+"','"+tTunjanganKeluarga.getText()+"', '"+tTotal.getText()+"')";
           st = conn.createStatement();
           s = st.executeUpdate(sql);
           if (s == 1){
            JOptionPane.showMessageDialog(null, "Sukses");
            clear();
            tampil();
           }
       } catch (Exception e){
            System.out.println(e.toString());
       } 
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tableGajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGajiMouseClicked
        // TODO add your handling code here:
        int baris = tableGaji.getSelectedRow();
	tKodeGaji.setText(tableGaji.getValueAt(baris, 1).toString());
	cNIP.setSelectedItem(tableGaji.getValueAt(baris, 2).toString());
        cGol.setSelectedItem(tableGaji.getValueAt(baris, 3).toString());
	tTunjanganKesehatan.setText(tableGaji.getValueAt(baris, 4).toString());
        tTunjanganKeluarga.setText(tableGaji.getValueAt(baris, 5).toString());
        tTotal.setText(tableGaji.getValueAt(baris, 6).toString());
    }//GEN-LAST:event_tableGajiMouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        try {
           int s;
           String sql = "DELETE FROM gaji WHERE kode_gaji = '"+tKodeGaji.getText()+"' ";
           st = conn.createStatement();
           s = st.executeUpdate(sql);
           if (s == 1){
            JOptionPane.showMessageDialog(null, "Sukses");
            clear();
            tampil();
           }
       } catch (Exception e){
            System.out.println(e.toString());
       }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void bCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCetakActionPerformed
        // TODO add your handling code here:
        JasperReport reports;

        String path=".\\src\\laporan\\reportGaji.jasper";
        try {
            reports = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
            System.out.println("Masuk try");
        } catch (JRException ex) {
            System.out.println("ini report error"+ex.toString());
        }
    }//GEN-LAST:event_bCetakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCetak;
    private javax.swing.JButton bHitung;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cGol;
    private javax.swing.JComboBox<String> cKeluarga;
    private javax.swing.JComboBox<String> cNIP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton rPunya;
    private javax.swing.JRadioButton rTidak;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField tGajiJabatan;
    private javax.swing.JTextField tJabatan;
    private javax.swing.JTextField tKodeGaji;
    private javax.swing.JTextField tNama;
    private javax.swing.JTextField tTotal;
    private javax.swing.JTextField tTunjanganKeluarga;
    private javax.swing.JTextField tTunjanganKesehatan;
    private javax.swing.JTable tableGaji;
    // End of variables declaration//GEN-END:variables
}
