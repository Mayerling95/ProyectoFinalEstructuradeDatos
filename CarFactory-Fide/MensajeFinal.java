
package Interfaz_gráfica;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
import java.awt.Color;

public class MensajeFinal extends javax.swing.JFrame {


    public MensajeFinal() {
        initComponents();
    }
    
    public MensajeFinal(int cantCarros){//Mensaje final segun resultado del juego
        initComponents();
        if (cantCarros != 0){
            mensaje1.setText("Ganador!");
            mensaje2.setText("Cantidad de Vehiculos Construidos: " + cantCarros);
            mensaje1.setForeground(Color.green);
            mensaje2.setForeground(Color.green);
        }else{
            mensaje1.setText("Ha Perdido!");
        }
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mensaje1 = new javax.swing.JLabel();
        mensaje2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mensaje1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        mensaje1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(mensaje1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 340, 40));

        mensaje2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        mensaje2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(mensaje2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 340, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MensajeFinal().setVisible(true);//visibilidad del panel
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel mensaje1;
    private javax.swing.JLabel mensaje2;
    // End of variables declaration//GEN-END:variables
}
