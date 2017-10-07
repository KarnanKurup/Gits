/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mca.bookbank;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import mca.bookbank.dbconnections.DbConnect;

/**
 *
 * @author Hare Krishna
 */
public class Report extends javax.swing.JInternalFrame {

    /**
     * Creates new form Report
     */
    public Report() {
        initComponents();
        String qr="select distinct(b.bname),s.subname,s.semid,(select count(bname) from books where bname=b.bname) as total,"+
                "(select count(bname) from books where bname=b.bname and status='I') as issuecount,(select count(bname) "+
                "from books where bname=b.bname and status='D') as damaged from books b join subject s on b.subid=s.subid";
        populateTable(qr);
        fillSem();
        fillBook();
        fillSubject();
    }

    private void populateTable(String qry){
        try{
            ResultSet rs= new DbConnect().dbSelect(qry);
            ResultSetMetaData rsmeta=rs.getMetaData();
            ArrayList rows=new ArrayList();
            String[] colhead=new String [] {
                "Book Name", "Subject", "Semester", "Total Available", "Total Issued","Total Damaged"
            };
            int cols=rsmeta.getColumnCount();
            
            while(rs.next()){
                ArrayList<String> newRow=new ArrayList<String>();
                for(int i=1;i<=cols;i++){
                    newRow.add(rs.getString(i));   
                }
                rows.add(newRow);
            }
            TableModel mytab=new DefaultTableModel(colhead, rows.size()){
                boolean[] canEdit = new boolean [] {false, false, false, false, false};
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            };
            
            TableRowSorter sorter=new TableRowSorter(mytab);
            jTable1.setModel(mytab);
            jTable1.setRowSorter(sorter);
            
            for(int i=0;i<rows.size();i++){
                ArrayList<String> getrow=(ArrayList<String>)rows.get(i);
                for(int j=0;j<getrow.size();j++){
                    jTable1.setValueAt(getrow.get(j), i, j);
                }
            }
            if(rows.isEmpty()){
                jTable1.setModel(new DefaultTableModel(new Object[][]{
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                },new String[]{
                    "Book Name", "Subject", "Semester", "Total Available", "Total Issued"
                }));
            }
            
        }catch(Exception e){
            MsgBox.errorBox(this, e);
        }
    }
    private void fillSem(){
        try {
            ResultSet rs=new DbConnect().dbSelect("select * from semester");
            while(rs.next()){
                jComboBox1.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            MsgBox.errorBox(this, e);
        }
    }
    ArrayList<String> subids=new ArrayList<>();
    private void fillSubject(){
        try {
            ResultSet rs=new DbConnect().dbSelect("select subname,subid from subject");
            while(rs.next()){
                jComboBox2.addItem(rs.getString(1));
                subids.add(rs.getString(2));
            }
        } catch (Exception e) {
            MsgBox.errorBox(this, e);
        }
    }
    private void fillBook(){
        try {
            ResultSet rs=new DbConnect().dbSelect("select distinct(bname) from books");
            while(rs.next()){
                jComboBox3.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            MsgBox.errorBox(this, e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Report");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/mca/bookbank/rajagiri2.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter"));

        jLabel1.setText("Semester");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Subject");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Book Name");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox3, 0, 228, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(154, 154, 154))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Book Name", "Subject", "Semester", "Total Available", "Total Issued", "Total Damaged"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        filter();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        filter();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        filter();
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void filter(){
        boolean isFirst=true;
        String qr="select distinct(b.bname),s.subname,s.semid,"+
                    "(select count(bname) from books where bname=b.bname) as total,(select count(bname)"+
                    " from books where bname=b.bname and status='I') as issuecount,"
                + "(select count(bname) from books where bname=b.bname and status='D') as damaged "
                + "from books b join subject s on b.subid=s.subid";
        if(jComboBox1.getSelectedIndex()>0){
            if(isFirst){
                qr+=" where ";
                isFirst=false;
            }
            qr+="s.semid='"+jComboBox1.getSelectedItem().toString()+"' and ";
        }
        if(jComboBox2.getSelectedIndex()>0){
            if(isFirst){
                qr+=" where ";
                isFirst=false;
            }
            qr+="b.subid="+ subids.get(jComboBox2.getSelectedIndex()-1)+" and ";
        }
        if(jComboBox3.getSelectedIndex()>0){
            if(isFirst){
                qr+=" where ";
                isFirst=false;
            }
            qr+="b.bname='"+jComboBox3.getSelectedItem().toString()+"' and ";
        }
        if(!isFirst){
            qr=qr.substring(0, qr.length()-5);
        }
        populateTable(qr);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}