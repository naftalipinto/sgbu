/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package template.main.containers;

import classes.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.*;

/**
 *
 * @author naftalipinto
 */
public final class GcirculacaoPanel extends javax.swing.JPanel {

    /**
     * Creates new form GutilizadorPanel
     */
    ObraService obra = new ObraService();
    UtilizadorService user = new UtilizadorService();

    public GcirculacaoPanel() {
        initComponents();
        initializeTable();
        initializeTablebiblio();
        fillTable(null);
        fillTablebiblio(null);
    }

    void fillTable(String filter) {
        try {
            ReservaService service = new ReservaService();
            List<Reserva> list = null;
            list = service.readAll();

            DefaultTableModel model = (DefaultTableModel) table3.getModel();
            model.setRowCount(0);  // Limpa apenas as linhas, mantém as colunas

            // Converte o filtro para minúsculas uma única vez
            String lowerFilter = (filter == null || filter.trim().isEmpty())
                    ? null : filter.toLowerCase();

            for (Reserva r : list) {
                // Se oranão há filtro ou se algum campo corresponde ao filtro
                if (lowerFilter == null
                        || r.getDataReserva().toString().toLowerCase().contains(lowerFilter)
                        || r.getStatus().contains(lowerFilter)) {

                    addRowToModel(model, r);
                }
            }
        } catch (Exception ex) {
        }
    }

    private void addRowToModel(DefaultTableModel model, Reserva r) {
        try {
            model.addRow(new Object[]{
                r.getId(),
                obra.read(r.getObraId()).getTitulo(),
                r.getDataReserva(),
                r.getPosicaoFila(),
                r.getStatus()

            });
        } catch (Exception ex) {
        }
    }

    public void initializeTable() {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    // Defina os tipos de cada coluna conforme necessário
                    if (columnIndex == 0) {
                        return Integer.class; // ID
                    }
                    return String.class;
                }
            };

            // Defina as colunas na ordem CORRETA que corresponda à sua classe worker
            model.setColumnIdentifiers(new String[]{
                "ID",
                "Obra",
                "Data",
                "Posição",
                "Estado"
            });

            table3.setModel(model);
            table3.getColumnModel().getColumn(0).setMinWidth(0);
            table3.getColumnModel().getColumn(0).setMaxWidth(0);
            table3.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception ex) {
        }
    }

    void fillTablebiblio(String filter) {
        try {
            ReservaService service = new ReservaService();
            List<Reserva> list = null;
            list = service.readAll();

            DefaultTableModel model = (DefaultTableModel) table4.getModel();
            model.setRowCount(0);  // Limpa apenas as linhas, mantém as colunas

            // Converte o filtro para minúsculas uma única vez
            String lowerFilter = (filter == null || filter.trim().isEmpty())
                    ? null : filter.toLowerCase();

            for (Reserva r : list) {
                // Se oranão há filtro ou se algum campo corresponde ao filtro
                if (lowerFilter == null
                        || r.getDataReserva().toString().toLowerCase().contains(lowerFilter)
                        || r.getStatus().contains(lowerFilter)) {

                    addRowToModelbiblio(model, r);
                }
            }
        } catch (Exception ex) {
        }
    }

    private void addRowToModelbiblio(DefaultTableModel model, Reserva r) {
        try {
            model.addRow(new Object[]{
                r.getId(),
                user.read(r.getUtilizadorId()).getNomeCompleto(),
                obra.read(r.getObraId()).getTitulo(),
                r.getDataReserva(),
                r.getPosicaoFila(),
                r.getStatus()

            });
        } catch (Exception ex) {
        }
    }

    public void initializeTablebiblio() {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    // Defina os tipos de cada coluna conforme necessário
                    if (columnIndex == 0) {
                        return Integer.class; // ID
                    }
                    return String.class;
                }
            };

            // Defina as colunas na ordem CORRETA que corresponda à sua classe worker
            model.setColumnIdentifiers(new String[]{
                "ID",
                "Utilizador",
                "Obra",
                "Data",
                "Posição",
                "Estado"
            });

            table4.setModel(model);
            table4.getColumnModel().getColumn(0).setMinWidth(0);
            table4.getColumnModel().getColumn(0).setMaxWidth(0);
            table4.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception ex) {
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        tf_pesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table4 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        tf_pesquisar1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Obra", "Data", "Posição", "Estado"
            }
        ));
        table3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table3);

        jButton1.setText("Adicionar");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        tf_pesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_pesquisarKeyTyped(evt);
            }
        });

        jLabel1.setText("Pesquisar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(tf_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_pesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reserva", jPanel1);

        table4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Utlizador", "Obra", "Data", "Posição", "Estado"
            }
        ));
        table4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table4MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(table4);

        jButton2.setText("Adicionar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        tf_pesquisar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_pesquisar1KeyTyped(evt);
            }
        });

        jLabel2.setText("Pesquisar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(tf_pesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_pesquisar1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Biblio", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Leitor");
    }// </editor-fold>//GEN-END:initComponents

    private void table3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table3MouseClicked
        try {
            DefaultTableModel model = (DefaultTableModel) table3.getModel();
            int selectedRow = table3.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma linha primeiro!");
                return;
            }
            long id = (long) model.getValueAt(selectedRow, 0);
            EditReserva r = new EditReserva();
            r.cb_user.setVisible(false);
            r.lb_user.setVisible(false);
            r.starter((int) id);
            r.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }

    }//GEN-LAST:event_table3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            addReserva add = new addReserva();
            add.cb_user.setVisible(false);
            add.cb_user.setEnabled(false);
            add.lb_user.setVisible(false);
            add.setVisible(true);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tf_pesquisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_pesquisarKeyTyped
        // TODO add your handling code here:
        fillTable(tf_pesquisar.getText());
    }//GEN-LAST:event_tf_pesquisarKeyTyped

    private void table4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table4MouseClicked
        // TODO add your handling code here:
        try {
            DefaultTableModel model = (DefaultTableModel) table4.getModel();
            int selectedRow = table4.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma linha primeiro!");
                return;
            }
            long id = (long) model.getValueAt(selectedRow, 0);
            EditReserva r = new EditReserva();
            r.starter((int) id);
            r.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }
    }//GEN-LAST:event_table4MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            addReserva add = new addReserva();
            add.setVisible(true);
        } catch (Exception ex) {
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tf_pesquisar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_pesquisar1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_pesquisar1KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable table3;
    private javax.swing.JTable table4;
    private javax.swing.JTextField tf_pesquisar;
    private javax.swing.JTextField tf_pesquisar1;
    // End of variables declaration//GEN-END:variables
}
