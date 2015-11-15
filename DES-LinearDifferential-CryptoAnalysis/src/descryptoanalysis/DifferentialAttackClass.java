/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package descryptoanalysis;

import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class DifferentialAttackClass extends javax.swing.JFrame {
    
    int K1,K2;
    /**
     * Creates new form DifferentialAttackClass
     */
    public DifferentialAttackClass() {
        initComponents();
           
    }

     public List<BitSet[]> createCipherPairs(BitSet[] subRoundKeys,int noPairs) {
        Random r = new Random();
        SBoxEncClass sBox = new SBoxEncClass();
        List<BitSet[]> sets = new ArrayList<BitSet[]>();
        for (int i = 0; i < noPairs; i++) {
        // create first plain-cipher 
        BitSet p1 = HelperClass.toBitSet(r.nextInt(65536), 16);
        BitSet c1 = sBox.sBoxEncBlock(p1, subRoundKeys);

        // choose another plain-cipher satisfy expression [0000 1011 0000 0000]
        BitSet p2 = HelperClass.getCopy(p1, 16);
        p2.xor(HelperClass.toBitSet(2816, 16));
        BitSet c2 = sBox.sBoxEncBlock(p2, subRoundKeys);
        sets.add(new BitSet[] { c1, c2 });
   }
        return sets;
  }
     
     public List<BitSet[]> createCipherPairsWithDifferentExpression(BitSet[] subRoundKeys,int noPairs) {
        Random r = new Random();
        SBoxEncClass sBox = new SBoxEncClass();
        List<BitSet[]> sets = new ArrayList<BitSet[]>();
        for (int i = 0; i < noPairs; i++) {
        // create first plain-cipher 
        BitSet p1 = HelperClass.toBitSet(r.nextInt(65536), 16);
        BitSet c1 = sBox.sBoxEncBlock(p1, subRoundKeys);

        // choose another plain-cipher satisfy expression [0000 0101 0000 0101]
        BitSet p2 = HelperClass.getCopy(p1, 16);
        p2.xor(HelperClass.toBitSet(2720, 16));
        BitSet c2 = sBox.sBoxEncBlock(p2, subRoundKeys);

        sets.add(new BitSet[] { c1, c2 });
      
     }
        return sets;
  }
    
      public List<BitSet[]> createCipherPairsPrevRound(BitSet[] subRoundKeys,int noPairs,BitSet discoveriedSBits,int roundNo) {
        Random r = new Random();
        SBoxEncClass sBox = new SBoxEncClass();
        List<BitSet[]> sets = new ArrayList<BitSet[]>();
        for (int i = 0; i < noPairs; i++) {
        // create first plain-cipher 
        BitSet p1 = HelperClass.toBitSet(r.nextInt(65536), 16);
        BitSet c1 = sBox.sBoxEncBlock(p1, subRoundKeys);
        
        //backtrack the last round
         BitSet backTrackC1=sBox.sBoxDecRound(c1,discoveriedSBits);//(c1,discoveriedSBits,roundNo);

        // choose another plain-cipher satisfy expression [0000 1011 0000 0000]
        BitSet p2 = HelperClass.getCopy(p1, 16);
        p2.xor(HelperClass.toBitSet(2816, 16));
        BitSet c2 = sBox.sBoxEncBlock(p2, subRoundKeys);
        
         //backtrack the last round
         BitSet backTrackC2=sBox.partialSBoxDecBlock(c2,discoveriedSBits,roundNo);//.sBoxDecRound(c2, discoveriedSBits);
         
        sets.add(new BitSet[] { backTrackC1, backTrackC2 });
      
        }
      return sets;
  }
     
    public BitSet partialSubkeyAssumption(BitSet partialc, BitSet partialk) {
        partialc = HelperClass.getCopy(partialc, 4);
        partialc.xor(partialk);
        return new SBoxEncClass().getSBoxInvValue(partialc);
   }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Differential Attack on Simple S-Box");

        jLabel1.setText("Enter SubRound Keys :");

        jTextField1.setText("FE12");

        jTextField2.setText("A9C5");

        jTextField3.setText("B678");

        jTextField4.setText("34DF");

        jLabel2.setText("No Plain txt :");

        jTextField5.setText("FDE3");

        jButton1.setLabel("Apply Attack");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField6.setText("5000");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Hex");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "x/y", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(102, 102, 102));
        jTable1.setSelectionBackground(new java.awt.Color(102, 153, 255));
        jScrollPane2.setViewportView(jTable1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Table present the number of appearnce & probabilities of the choosen plaintext");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCheckBox1)))))
                        .addGap(0, 118, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

       ImageViewer.main("/Diff.png","Differential Expression",false);
        
        int radix=10;
        if(jCheckBox1.isSelected())
            radix=16;
        
        int srK1=Integer.parseInt(jTextField1.getText(),radix);
        int srK2=Integer.parseInt(jTextField2.getText(),radix);
        int srK3=Integer.parseInt(jTextField3.getText(),radix);
        int srK4=Integer.parseInt(jTextField4.getText(),radix);
        int srK5=Integer.parseInt(jTextField5.getText(),radix);
        BitSet[] subRoundKeys = new BitSet[] { HelperClass.toBitSet(srK1, 16),
                 HelperClass.toBitSet(srK2, 16), HelperClass.toBitSet(srK3, 16),
                 HelperClass.toBitSet(srK4, 16), HelperClass.toBitSet(srK5, 16) };
       int noPair=Integer.parseInt(jTextField6.getText());
        
      
       List<BitSet[]> cipherPairs = createCipherPairs(subRoundKeys,noPair);
      
       int[][] myCounts=new int[16][16];
       for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
               myCounts[i][j]=0;
           }
        }

       
    for (BitSet[] cPair : cipherPairs) {
         BitSet c1 = cPair[0];
      BitSet c2 = cPair[1];

      for (int i = 0; i < 16; i++) {
        for (int j = 0; j < 16; j++) {
          // partial subkey assumption for key bits 4 to 8
          BitSet pSubkeyA = HelperClass.toBitSet(i, 4);
          // partial subkey assumption for key bits 12 to 16
          BitSet pSubkeyB = HelperClass.toBitSet(j, 4);
         
          // check dA bits 4 to 8
          BitSet pCipherA1 = c1.get(4, 8);
          BitSet a1Assumption = partialSubkeyAssumption(pCipherA1,
              pSubkeyA);
          BitSet pCipherA2 = c2.get(4, 8);
          BitSet a2Assumption = partialSubkeyAssumption(pCipherA2,
              pSubkeyA);
          BitSet deltaA = HelperClass.getCopy(a1Assumption, 4);
          deltaA.xor(a2Assumption);

          // check dB bits 12-16
          BitSet pCipherB1 = c1.get(12, 16);
          BitSet b1Assumption = partialSubkeyAssumption(pCipherB1,
              pSubkeyB);
          BitSet pCipherB2 = c2.get(12, 16);
          BitSet b2Assumption = partialSubkeyAssumption(pCipherB2,
              pSubkeyB);
          BitSet deltaB =  HelperClass.getCopy(b1Assumption, 4);
          deltaB.xor(b2Assumption);

          // check the assumpted key bits [0000 0110 0000 0110]
          if ((!deltaA.get(0)) && deltaA.get(1) && deltaA.get(2) && (!deltaA.get(3))
             && (!deltaB.get(0)) && deltaB.get(1) && deltaB.get(2) & (!deltaB.get(3))) {
              myCounts[i][j]++;
           }
          
        }
      }
    }
 
   
    double highProb = 0;
    int k1 = 0;
    int k2 = 0;
    for (int i = 0; i < 16; i++) {
      jTable1.getModel().setValueAt(Integer.toHexString(i),i,0); 
      for (int j = 0; j < 16; j++) {
          int temp=myCounts[i][j];
          double prob=temp/ ((double) noPair);
          jTable1.getModel().setValueAt(temp+" - "+ prob, j, i+1);
          if (prob > highProb) {
            highProb= prob;
            k1 = i;
            k2 = j;
        }   
      }
    }
    
    String conclusion="Real round subKey is "+jTextField5.getText()+"\n\nBest Assumption for subKey [4-8] is "+Integer.toHexString(k1)+" ,  Best Assumption for subKey [12-16] is "+Integer.toHexString(k2)+
            "\n\nNo of appearance = "+myCounts[k1][k2]+" , with Probability = "+highProb;
   jTextArea1.setText(conclusion);
    
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DifferentialAttackClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DifferentialAttackClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DifferentialAttackClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DifferentialAttackClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DifferentialAttackClass().setVisible(true);
            }
        });
        
         
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
