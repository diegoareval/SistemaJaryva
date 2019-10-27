
package com.devs.vistas;




import com.devs.auxiliar.SesionUsuarios;
import entities.Usuarios;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import paneles.CambiaPanel;


public class Principal extends javax.swing.JFrame {
    SesionUsuarios sesionusu=new SesionUsuarios();
    Usuarios usuario=null;
 
    int x, y;
   
    
    public Principal() {
        
        initComponents();
        //jmenubar();
     
         Animacion.Animacion.mover_izquierda(0, -264, 2, 2, pnlMenu);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(this);
        this.uno.setSelected(true);
        cargarconfiguraciones();
        new CambiaPanel(pnlPrincipal, new paneles.pnlHome());
        
    }
    public void setUsuario(Usuarios usuarios) {
        usuario=usuarios;
        lblusuario.setText(usuarios.getNivelAcceso());
        
        
       // setPrivilegios(usuario.getPrivilegios());
    }
    
//    public void popup(){
//      JPopupMenu popup=new JPopupMenu();
//      popup.setBorderPainted(true);
//     
//      JMenuItem item=new JMenuItem("Eliminar");
//      
//      JMenuItem item2=new JMenuItem("Modificar");
//      
//      item.addActionListener(new ActionListener() {
//          @Override
//          public void actionPerformed(ActionEvent e) {
//              //eliminar();
//              
//          }
//      }
//      );
//      item2.addActionListener(new ActionListener() {
//          @Override
//          public void actionPerformed(ActionEvent e) {
//              //modificar();
//              
//          }
//      }
//      );
//      
//      
//      popup.add(item);
//      popup.add(item2);
//      dos.setComponentPopupMenu(popup);
//  }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        uno = new rsbuttom.RSButtonMetro();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tres = new rsbuttom.RSButtonMetro();
        dos = new rsbuttom.RSButtonMetro();
        cinco = new rsbuttom.RSButtonMetro();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ocho = new rsbuttom.RSButtonMetro();
        siete = new rsbuttom.RSButtonMetro();
        seis = new rsbuttom.RSButtonMetro();
        cuatro = new rsbuttom.RSButtonMetro();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        rSLabelHora1 = new rojeru_san.RSLabelHora();
        jLabel6 = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        pnlCentro = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlPrincipal = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jmbanco = new javax.swing.JMenuItem();
        jmcargos = new javax.swing.JMenuItem();
        jmdepartamentos = new javax.swing.JMenuItem();
        jmautorizacion = new javax.swing.JMenuItem();
        jmprogramacion = new javax.swing.JMenuItem();
        jmafp = new javax.swing.JMenuItem();
        jmsalones = new javax.swing.JMenuItem();
        jmeventos = new javax.swing.JMenuItem();
        jmusuarios = new javax.swing.JMenuItem();
        jmplanillas = new javax.swing.JMenuItem();
        Jmtipo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu4 = new javax.swing.JMenu();
        jmcontrato = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        pnlMenu.setBackground(new java.awt.Color(239, 238, 244));
        pnlMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(239, 238, 244)));
        pnlMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlMenuMouseExited(evt);
            }
        });

        uno.setForeground(new java.awt.Color(128, 128, 131));
        uno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/beginn.png"))); // NOI18N
        uno.setText("Inicio");
        uno.setColorHover(new java.awt.Color(0, 0, 0));
        uno.setColorNormal(new java.awt.Color(204, 204, 204));
        uno.setColorPressed(new java.awt.Color(204, 204, 204));
        uno.setColorTextNormal(new java.awt.Color(128, 128, 131));
        uno.setColorTextPressed(new java.awt.Color(128, 128, 131));
        uno.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        uno.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        uno.setIconTextGap(25);
        uno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                unoMousePressed(evt);
            }
        });
        uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unoActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(239, 238, 244));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(128, 128, 131));
        jLabel1.setText("Informacion");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        tres.setBackground(new java.awt.Color(239, 238, 244));
        tres.setForeground(new java.awt.Color(128, 128, 131));
        tres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/adm.png"))); // NOI18N
        tres.setText("Administracion");
        tres.setColorHover(new java.awt.Color(0, 0, 0));
        tres.setColorNormal(new java.awt.Color(239, 238, 244));
        tres.setColorPressed(new java.awt.Color(204, 204, 204));
        tres.setColorTextNormal(new java.awt.Color(128, 128, 131));
        tres.setColorTextPressed(new java.awt.Color(128, 128, 131));
        tres.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tres.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        tres.setIconTextGap(19);
        tres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tresMousePressed(evt);
            }
        });
        tres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresActionPerformed(evt);
            }
        });

        dos.setBackground(new java.awt.Color(239, 238, 244));
        dos.setForeground(new java.awt.Color(128, 128, 131));
        dos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/programaciona.jpg"))); // NOI18N
        dos.setText("Programacion");
        dos.setColorHover(new java.awt.Color(0, 0, 0));
        dos.setColorNormal(new java.awt.Color(239, 238, 244));
        dos.setColorPressed(new java.awt.Color(204, 204, 204));
        dos.setColorTextNormal(new java.awt.Color(128, 128, 131));
        dos.setColorTextPressed(new java.awt.Color(128, 128, 131));
        dos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        dos.setIconTextGap(25);
        dos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dosMousePressed(evt);
            }
        });
        dos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosActionPerformed(evt);
            }
        });

        cinco.setBackground(new java.awt.Color(239, 238, 244));
        cinco.setForeground(new java.awt.Color(128, 128, 131));
        cinco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/empleado.png"))); // NOI18N
        cinco.setText("Empleados");
        cinco.setColorHover(new java.awt.Color(0, 0, 0));
        cinco.setColorNormal(new java.awt.Color(239, 238, 244));
        cinco.setColorPressed(new java.awt.Color(204, 204, 204));
        cinco.setColorTextNormal(new java.awt.Color(128, 128, 131));
        cinco.setColorTextPressed(new java.awt.Color(128, 128, 131));
        cinco.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cinco.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cinco.setIconTextGap(19);
        cinco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cincoMousePressed(evt);
            }
        });
        cinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cincoActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(239, 238, 244));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(128, 128, 131));
        jLabel3.setText("Ventas");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        ocho.setBackground(new java.awt.Color(239, 238, 244));
        ocho.setForeground(new java.awt.Color(128, 128, 131));
        ocho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/categorias.png"))); // NOI18N
        ocho.setText("Mov. Programacion");
        ocho.setColorHover(new java.awt.Color(0, 0, 0));
        ocho.setColorNormal(new java.awt.Color(239, 238, 244));
        ocho.setColorPressed(new java.awt.Color(204, 204, 204));
        ocho.setColorTextNormal(new java.awt.Color(128, 128, 131));
        ocho.setColorTextPressed(new java.awt.Color(128, 128, 131));
        ocho.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ocho.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ocho.setIconTextGap(19);
        ocho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ochoMousePressed(evt);
            }
        });
        ocho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ochoActionPerformed(evt);
            }
        });

        siete.setBackground(new java.awt.Color(239, 238, 244));
        siete.setForeground(new java.awt.Color(128, 128, 131));
        siete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/acercade.jpg"))); // NOI18N
        siete.setText("A Cerca De");
        siete.setColorHover(new java.awt.Color(0, 0, 0));
        siete.setColorNormal(new java.awt.Color(239, 238, 244));
        siete.setColorPressed(new java.awt.Color(204, 204, 204));
        siete.setColorTextNormal(new java.awt.Color(128, 128, 131));
        siete.setColorTextPressed(new java.awt.Color(128, 128, 131));
        siete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        siete.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        siete.setIconTextGap(19);
        siete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sieteMousePressed(evt);
            }
        });
        siete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sieteActionPerformed(evt);
            }
        });

        seis.setBackground(new java.awt.Color(239, 238, 244));
        seis.setForeground(new java.awt.Color(128, 128, 131));
        seis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/planes.png"))); // NOI18N
        seis.setText("Gestion Personal");
        seis.setColorHover(new java.awt.Color(0, 0, 0));
        seis.setColorNormal(new java.awt.Color(239, 238, 244));
        seis.setColorPressed(new java.awt.Color(204, 204, 204));
        seis.setColorTextNormal(new java.awt.Color(128, 128, 131));
        seis.setColorTextPressed(new java.awt.Color(128, 128, 131));
        seis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        seis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        seis.setIconTextGap(25);
        seis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                seisMousePressed(evt);
            }
        });
        seis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seisActionPerformed(evt);
            }
        });

        cuatro.setBackground(new java.awt.Color(239, 238, 244));
        cuatro.setForeground(new java.awt.Color(128, 128, 131));
        cuatro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/planillasm.jpg"))); // NOI18N
        cuatro.setText("Planillas");
        cuatro.setColorHover(new java.awt.Color(0, 0, 0));
        cuatro.setColorNormal(new java.awt.Color(239, 238, 244));
        cuatro.setColorPressed(new java.awt.Color(204, 204, 204));
        cuatro.setColorTextNormal(new java.awt.Color(128, 128, 131));
        cuatro.setColorTextPressed(new java.awt.Color(128, 128, 131));
        cuatro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cuatro.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cuatro.setIconTextGap(19);
        cuatro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cuatroMousePressed(evt);
            }
        });
        cuatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuatroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uno, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dos, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tres, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuatro, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cinco, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seis, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(siete, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ocho, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(uno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(dos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tres, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cuatro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cinco, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(seis, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(siete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ocho, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 8.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jPanel1.add(pnlMenu, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sistema Gestion Eventos");

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Usuario:");

        lblusuario.setForeground(new java.awt.Color(255, 255, 255));
        lblusuario.setText("jLabel7");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(95, 95, 95)
                .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(lblusuario)
                .addContainerGap(301, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(lblusuario))
                            .addGap(13, 13, 13))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.2;
        jPanel1.add(jPanel2, gridBagConstraints);

        pnlCentro.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(128, 128, 131));
        jLabel4.setText("Sistema de Gestion De Eventos - Jaryva");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(375, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(null);

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setLayout(new javax.swing.BoxLayout(pnlPrincipal, javax.swing.BoxLayout.LINE_AXIS));

        jToolBar1.setRollover(true);
        pnlPrincipal.add(jToolBar1);

        jScrollPane1.setViewportView(pnlPrincipal);

        javax.swing.GroupLayout pnlCentroLayout = new javax.swing.GroupLayout(pnlCentro);
        pnlCentro.setLayout(pnlCentroLayout);
        pnlCentroLayout.setHorizontalGroup(
            pnlCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        pnlCentroLayout.setVerticalGroup(
            pnlCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentroLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(pnlCentro, gridBagConstraints);

        jLabel5.setText("jLabel5");

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenuBar1.setMargin(new java.awt.Insets(10, 10, 10, 10));

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Inicio.png"))); // NOI18N
        jMenu2.setText("Inicio");
        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/empleados.png"))); // NOI18N
        jMenu3.setText("Administracion");
        jMenu3.setToolTipText("Administracion del Sistema");

        jmbanco.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jmbanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bancos.png"))); // NOI18N
        jmbanco.setText("Bancos");
        jmbanco.setToolTipText("Gestion de Registros de Bancos");
        jmbanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmbancoActionPerformed(evt);
            }
        });
        jMenu3.add(jmbanco);

        jmcargos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jmcargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargos_1.jpg"))); // NOI18N
        jmcargos.setText("Cargos");
        jmcargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmcargosActionPerformed(evt);
            }
        });
        jMenu3.add(jmcargos);

        jmdepartamentos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jmdepartamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/deps_1.png"))); // NOI18N
        jmdepartamentos.setText("Departamentos");
        jmdepartamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmdepartamentosActionPerformed(evt);
            }
        });
        jMenu3.add(jmdepartamentos);

        jmautorizacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jmautorizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/auto_1.jpg"))); // NOI18N
        jmautorizacion.setText("Autorizacion");
        jmautorizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmautorizacionActionPerformed(evt);
            }
        });
        jMenu3.add(jmautorizacion);

        jmprogramacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jmprogramacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/prog_1.jpg"))); // NOI18N
        jmprogramacion.setText("Programacion");
        jmprogramacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmprogramacionActionPerformed(evt);
            }
        });
        jMenu3.add(jmprogramacion);

        jmafp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jmafp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/afp.png"))); // NOI18N
        jmafp.setText("AFP");
        jmafp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmafpActionPerformed(evt);
            }
        });
        jMenu3.add(jmafp);

        jmsalones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jmsalones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salon_1.png"))); // NOI18N
        jmsalones.setText("Salones");
        jmsalones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmsalonesActionPerformed(evt);
            }
        });
        jMenu3.add(jmsalones);

        jmeventos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jmeventos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eventos_1.png"))); // NOI18N
        jmeventos.setText("Eventos");
        jmeventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmeventosActionPerformed(evt);
            }
        });
        jMenu3.add(jmeventos);

        jmusuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jmusuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuarios_1.png"))); // NOI18N
        jmusuarios.setText("Usuarios");
        jmusuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmusuariosActionPerformed(evt);
            }
        });
        jMenu3.add(jmusuarios);

        jmplanillas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jmplanillas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/planillas_1.png"))); // NOI18N
        jmplanillas.setText("Planillas");
        jmplanillas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmplanillasActionPerformed(evt);
            }
        });
        jMenu3.add(jmplanillas);

        Jmtipo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        Jmtipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/collection.png"))); // NOI18N
        Jmtipo.setText("Tipo");
        Jmtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmtipoActionPerformed(evt);
            }
        });
        jMenu3.add(Jmtipo);
        jMenu3.add(jSeparator1);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargos_1.jpg"))); // NOI18N
        jMenu4.setText("Contratos");

        jmcontrato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cargos_1.jpg"))); // NOI18N
        jmcontrato.setText("Contrato");
        jmcontrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmcontratoActionPerformed(evt);
            }
        });
        jMenu4.add(jmcontrato);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void cargarconfiguraciones(){
   
//    jLabel2.setText(configuracion.getNombresistema());
   // jLabel4.setText(configuracion.getNombresistema());
}



  
    
    
    private void unoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unoActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlHome());
        if(this.uno.isSelected()){
            this.uno.setColorNormal(new Color(204,204,204));
            this.uno.setColorHover(new Color(0,0,0));
            this.uno.setColorPressed(new Color(204,204,204));
            
            this.dos.setColorNormal(new Color(239,238,244));
            this.dos.setColorHover(new Color(0,0,0));
            this.dos.setColorPressed(new Color(204,204,204));
            
            this.tres.setColorNormal(new Color(239,238,244));
            this.tres.setColorHover(new Color(0,0,0));
            this.tres.setColorPressed(new Color(204,204,204));
            
            this.cuatro.setColorNormal(new Color(239,238,244));
            this.cuatro.setColorHover(new Color(0,0,0));
            this.cuatro.setColorPressed(new Color(204,204,204));
            
            this.cinco.setColorNormal(new Color(239,238,244));
            this.cinco.setColorHover(new Color(0,0,0));
            this.cinco.setColorPressed(new Color(204,204,204));
            
            this.seis.setColorNormal(new Color(239,238,244));
            this.seis.setColorHover(new Color(0,0,0));
            this.seis.setColorPressed(new Color(204,204,204));
            
            this.siete.setColorNormal(new Color(239,238,244));
            this.siete.setColorHover(new Color(0,0,0));
            this.siete.setColorPressed(new Color(204,204,204));
            
            this.ocho.setColorNormal(new Color(239,238,244));
            this.ocho.setColorHover(new Color(0,0,0));
            this.ocho.setColorPressed(new Color(204,204,204));
        }else{
            this.uno.setColorNormal(new Color(239,238,244));
            this.uno.setColorHover(new Color(0,0,0));
            this.uno.setColorPressed(new Color(204,204,204));
        }
       
    }//GEN-LAST:event_unoActionPerformed

    private void unoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unoMousePressed
        this.uno.setSelected(true);
        this.dos.setSelected(false);
        this.tres.setSelected(false);
        this.cuatro.setSelected(false);
        this.cinco.setSelected(false);
        this.seis.setSelected(false);
        this.siete.setSelected(false);
        this.ocho.setSelected(false);
    }//GEN-LAST:event_unoMousePressed

    private void tresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tresActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlChat());
        if(this.tres.isSelected()){
            this.tres.setColorNormal(new Color(204,204,204));
            this.tres.setColorHover(new Color(0,0,0));
            this.tres.setColorPressed(new Color(204,204,204));
            
            this.uno.setColorNormal(new Color(239,238,244));
            this.uno.setColorHover(new Color(0,0,0));
            this.uno.setColorPressed(new Color(204,204,204));
            
            this.dos.setColorNormal(new Color(239,238,244));
            this.dos.setColorHover(new Color(0,0,0));
            this.dos.setColorPressed(new Color(204,204,204));
            
            this.cuatro.setColorNormal(new Color(239,238,244));
            this.cuatro.setColorHover(new Color(0,0,0));
            this.cuatro.setColorPressed(new Color(204,204,204));
            
            this.cinco.setColorNormal(new Color(239,238,244));
            this.cinco.setColorHover(new Color(0,0,0));
            this.cinco.setColorPressed(new Color(204,204,204));
            
            this.seis.setColorNormal(new Color(239,238,244));
            this.seis.setColorHover(new Color(0,0,0));
            this.seis.setColorPressed(new Color(204,204,204));
            
            this.siete.setColorNormal(new Color(239,238,244));
            this.siete.setColorHover(new Color(0,0,0));
            this.siete.setColorPressed(new Color(204,204,204));
            
            this.ocho.setColorNormal(new Color(239,238,244));
            this.ocho.setColorHover(new Color(0,0,0));
            this.ocho.setColorPressed(new Color(204,204,204));
        }else{
            this.tres.setColorNormal(new Color(239,238,244));
            this.tres.setColorHover(new Color(0,0,0));
            this.tres.setColorPressed(new Color(204,204,204));
        }
    }//GEN-LAST:event_tresActionPerformed

    
    
    
    
    private void tresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tresMousePressed
        this.uno.setSelected(false);
        this.dos.setSelected(false);
        this.tres.setSelected(true);
        this.cuatro.setSelected(false);
        this.cinco.setSelected(false);
        this.seis.setSelected(false);
        this.siete.setSelected(false);
        this.ocho.setSelected(false);
       
    }//GEN-LAST:event_tresMousePressed

    private void dosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosMousePressed
        this.uno.setSelected(false);
        this.dos.setSelected(true);
        this.tres.setSelected(false);
        this.cuatro.setSelected(false);
        this.cinco.setSelected(false);
        this.seis.setSelected(false);
        this.siete.setSelected(false);
        this.ocho.setSelected(false);
    }//GEN-LAST:event_dosMousePressed

    private void dosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosActionPerformed

        new CambiaPanel(pnlPrincipal, new paneles.mipanel());
        if(this.dos.isSelected()){
            this.dos.setColorNormal(new Color(204,204,204));
            this.dos.setColorHover(new Color(0,0,0));
            this.dos.setColorPressed(new Color(204,204,204));
            
            this.uno.setColorNormal(new Color(239,238,244));
            this.uno.setColorHover(new Color(0,0,0));
            this.uno.setColorPressed(new Color(204,204,204));
            
            this.tres.setColorNormal(new Color(239,238,244));
            this.tres.setColorHover(new Color(0,0,0));
            this.tres.setColorPressed(new Color(204,204,204));
            
            this.cuatro.setColorNormal(new Color(239,238,244));
            this.cuatro.setColorHover(new Color(0,0,0));
            this.cuatro.setColorPressed(new Color(204,204,204));
            
            this.cinco.setColorNormal(new Color(239,238,244));
            this.cinco.setColorHover(new Color(0,0,0));
            this.cinco.setColorPressed(new Color(204,204,204));
            
            this.seis.setColorNormal(new Color(239,238,244));
            this.seis.setColorHover(new Color(0,0,0));
            this.seis.setColorPressed(new Color(204,204,204));
            
            this.siete.setColorNormal(new Color(239,238,244));
            this.siete.setColorHover(new Color(0,0,0));
            this.siete.setColorPressed(new Color(204,204,204));
            
            this.ocho.setColorNormal(new Color(239,238,244));
            this.ocho.setColorHover(new Color(0,0,0));
            this.ocho.setColorPressed(new Color(204,204,204));
        }else{
            this.dos.setColorNormal(new Color(239,238,244));
            this.dos.setColorHover(new Color(0,0,0));
            this.dos.setColorPressed(new Color(204,204,204));
        }
    }//GEN-LAST:event_dosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int posicion = pnlMenu.getX();
        if(posicion > -1){
            Animacion.Animacion.mover_izquierda(0, -264, 2, 2, pnlMenu);
        }else{
            Animacion.Animacion.mover_derecha(-264, 0, 2, 2, pnlMenu);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ochoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ochoMousePressed
        this.uno.setSelected(false);
        this.dos.setSelected(false);
        this.tres.setSelected(false);
        this.cuatro.setSelected(false);
        this.cinco.setSelected(false);
        this.seis.setSelected(false);
        this.siete.setSelected(false);
        this.ocho.setSelected(true);
    }//GEN-LAST:event_ochoMousePressed

    private void ochoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ochoActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlWeb());
        if(this.ocho.isSelected()){
            this.uno.setColorNormal(new Color(239,238,244));
            this.uno.setColorHover(new Color(0,0,0));
            this.uno.setColorPressed(new Color(204,204,204));
            
            this.dos.setColorNormal(new Color(239,238,244));
            this.dos.setColorHover(new Color(0,0,0));
            this.dos.setColorPressed(new Color(204,204,204));
            
            this.tres.setColorNormal(new Color(239,238,244));
            this.tres.setColorHover(new Color(0,0,0));
            this.tres.setColorPressed(new Color(204,204,204));
            
            this.cuatro.setColorNormal(new Color(239,238,244));
            this.cuatro.setColorHover(new Color(0,0,0));
            this.cuatro.setColorPressed(new Color(204,204,204));
            
            this.cinco.setColorNormal(new Color(239,238,244));
            this.cinco.setColorHover(new Color(0,0,0));
            this.cinco.setColorPressed(new Color(204,204,204));
            
            this.seis.setColorNormal(new Color(239,238,244));
            this.seis.setColorHover(new Color(0,0,0));
            this.seis.setColorPressed(new Color(204,204,204));
            
            this.siete.setColorNormal(new Color(239,238,244));
            this.siete.setColorHover(new Color(0,0,0));
            this.siete.setColorPressed(new Color(204,204,204));
            
            this.ocho.setColorNormal(new Color(204,204,204));
            this.ocho.setColorHover(new Color(0,0,0));
            this.ocho.setColorPressed(new Color(204,204,204));
        }else{
            this.ocho.setColorNormal(new Color(239,238,244));
            this.ocho.setColorHover(new Color(0,0,0));
            this.ocho.setColorPressed(new Color(204,204,204));
        }
    }//GEN-LAST:event_ochoActionPerformed

    private void sieteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sieteMousePressed
        this.uno.setSelected(false);
        this.dos.setSelected(false);
        this.tres.setSelected(false);
        this.cuatro.setSelected(false);
        this.cinco.setSelected(false);
        this.seis.setSelected(false);
        this.siete.setSelected(true);
        this.ocho.setSelected(false);
    }//GEN-LAST:event_sieteMousePressed

    private void sieteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sieteActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlIOS());
        if(this.siete.isSelected()){
            this.uno.setColorNormal(new Color(239,238,244));
            this.uno.setColorHover(new Color(0,0,0));
            this.uno.setColorPressed(new Color(204,204,204));
            
            this.dos.setColorNormal(new Color(239,238,244));
            this.dos.setColorHover(new Color(0,0,0));
            this.dos.setColorPressed(new Color(204,204,204));
            
            this.tres.setColorNormal(new Color(239,238,244));
            this.tres.setColorHover(new Color(0,0,0));
            this.tres.setColorPressed(new Color(204,204,204));
            
            this.cuatro.setColorNormal(new Color(239,238,244));
            this.cuatro.setColorHover(new Color(0,0,0));
            this.cuatro.setColorPressed(new Color(204,204,204));
            
            this.cinco.setColorNormal(new Color(239,238,244));
            this.cinco.setColorHover(new Color(0,0,0));
            this.cinco.setColorPressed(new Color(204,204,204));
            
            this.seis.setColorNormal(new Color(239,238,244));
            this.seis.setColorHover(new Color(0,0,0));
            this.seis.setColorPressed(new Color(204,204,204));
            
            this.siete.setColorNormal(new Color(204,204,204));
            this.siete.setColorHover(new Color(0,0,0));
            this.siete.setColorPressed(new Color(204,204,204));
            
            this.ocho.setColorNormal(new Color(239,238,244));
            this.ocho.setColorHover(new Color(0,0,0));
            this.ocho.setColorPressed(new Color(204,204,204));
        }else{
            this.siete.setColorNormal(new Color(239,238,244));
            this.siete.setColorHover(new Color(0,0,0));
            this.siete.setColorPressed(new Color(204,204,204));
        }
    }//GEN-LAST:event_sieteActionPerformed

    private void seisMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seisMousePressed
        this.uno.setSelected(false);
        this.dos.setSelected(false);
        this.tres.setSelected(false);
        this.cuatro.setSelected(false);
        this.cinco.setSelected(false);
        this.seis.setSelected(true);
        this.siete.setSelected(false);
        this.ocho.setSelected(false);
    }//GEN-LAST:event_seisMousePressed

    private void seisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seisActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlAndroid());
        if(this.seis.isSelected()){
            this.uno.setColorNormal(new Color(239,238,244));
            this.uno.setColorHover(new Color(0,0,0));
            this.uno.setColorPressed(new Color(204,204,204));
            
            this.dos.setColorNormal(new Color(239,238,244));
            this.dos.setColorHover(new Color(0,0,0));
            this.dos.setColorPressed(new Color(204,204,204));
            
            this.tres.setColorNormal(new Color(239,238,244));
            this.tres.setColorHover(new Color(0,0,0));
            this.tres.setColorPressed(new Color(204,204,204));
            
            this.cuatro.setColorNormal(new Color(239,238,244));
            this.cuatro.setColorHover(new Color(0,0,0));
            this.cuatro.setColorPressed(new Color(204,204,204));
            
            this.cinco.setColorNormal(new Color(239,238,244));
            this.cinco.setColorHover(new Color(0,0,0));
            this.cinco.setColorPressed(new Color(204,204,204));
            
            this.seis.setColorNormal(new Color(204,204,204));
            this.seis.setColorHover(new Color(0,0,0));
            this.seis.setColorPressed(new Color(204,204,204));
            
            this.siete.setColorNormal(new Color(239,238,244));
            this.siete.setColorHover(new Color(0,0,0));
            this.siete.setColorPressed(new Color(204,204,204));
            
            this.ocho.setColorNormal(new Color(239,238,244));
            this.ocho.setColorHover(new Color(0,0,0));
            this.ocho.setColorPressed(new Color(204,204,204));
        }else{
            this.seis.setColorNormal(new Color(239,238,244));
            this.seis.setColorHover(new Color(0,0,0));
            this.seis.setColorPressed(new Color(204,204,204));
        }
    }//GEN-LAST:event_seisActionPerformed

    private void cincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cincoActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlMarket());
        if(this.cinco.isSelected()){
            this.uno.setColorNormal(new Color(239,238,244));
            this.uno.setColorHover(new Color(0,0,0));
            this.uno.setColorPressed(new Color(204,204,204));
            
            this.dos.setColorNormal(new Color(239,238,244));
            this.dos.setColorHover(new Color(0,0,0));
            this.dos.setColorPressed(new Color(204,204,204));
            
            this.tres.setColorNormal(new Color(239,238,244));
            this.tres.setColorHover(new Color(0,0,0));
            this.tres.setColorPressed(new Color(204,204,204));
            
            this.cuatro.setColorNormal(new Color(239,238,244));
            this.cuatro.setColorHover(new Color(0,0,0));
            this.cuatro.setColorPressed(new Color(204,204,204));
            
            this.cinco.setColorNormal(new Color(204,204,204));
            this.cinco.setColorHover(new Color(0,0,0));
            this.cinco.setColorPressed(new Color(204,204,204));
            
            this.seis.setColorNormal(new Color(239,238,244));
            this.seis.setColorHover(new Color(0,0,0));
            this.seis.setColorPressed(new Color(204,204,204));
            
            this.siete.setColorNormal(new Color(239,238,244));
            this.siete.setColorHover(new Color(0,0,0));
            this.siete.setColorPressed(new Color(204,204,204));
            
            this.ocho.setColorNormal(new Color(239,238,244));
            this.ocho.setColorHover(new Color(0,0,0));
            this.ocho.setColorPressed(new Color(204,204,204));
        }else{
            this.cinco.setColorNormal(new Color(239,238,244));
            this.cinco.setColorHover(new Color(0,0,0));
            this.cinco.setColorPressed(new Color(204,204,204));
        }
    }//GEN-LAST:event_cincoActionPerformed

    private void cincoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cincoMousePressed
        this.uno.setSelected(false);
        this.dos.setSelected(false);
        this.tres.setSelected(false);
        this.cuatro.setSelected(false);
        this.cinco.setSelected(true);
        this.seis.setSelected(false);
        this.siete.setSelected(false);
        this.ocho.setSelected(false);
    }//GEN-LAST:event_cincoMousePressed

    private void cuatroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuatroMousePressed
        this.uno.setSelected(false);
        this.dos.setSelected(false);
        this.tres.setSelected(false);
        this.cuatro.setSelected(true);
        this.cinco.setSelected(false);
        this.seis.setSelected(false);
        this.siete.setSelected(false);
        this.ocho.setSelected(false);
    }//GEN-LAST:event_cuatroMousePressed

    private void cuatroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuatroActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlFeeBack());
        if(this.cuatro.isSelected()){
            this.uno.setColorNormal(new Color(239,238,244));
            this.uno.setColorHover(new Color(0,0,0));
            this.uno.setColorPressed(new Color(204,204,204));
            
            this.dos.setColorNormal(new Color(239,238,244));
            this.dos.setColorHover(new Color(0,0,0));
            this.dos.setColorPressed(new Color(204,204,204));
            
            this.tres.setColorNormal(new Color(239,238,244));
            this.tres.setColorHover(new Color(0,0,0));
            this.tres.setColorPressed(new Color(204,204,204));
            
            this.cuatro.setColorNormal(new Color(204,204,204));
            this.cuatro.setColorHover(new Color(0,0,0));
            this.cuatro.setColorPressed(new Color(204,204,204));
            
            this.cinco.setColorNormal(new Color(239,238,244));
            this.cinco.setColorHover(new Color(0,0,0));
            this.cinco.setColorPressed(new Color(204,204,204));
            
            this.seis.setColorNormal(new Color(239,238,244));
            this.seis.setColorHover(new Color(0,0,0));
            this.seis.setColorPressed(new Color(204,204,204));
            
            this.siete.setColorNormal(new Color(239,238,244));
            this.siete.setColorHover(new Color(0,0,0));
            this.siete.setColorPressed(new Color(204,204,204));
            
            this.ocho.setColorNormal(new Color(239,238,244));
            this.ocho.setColorHover(new Color(0,0,0));
            this.ocho.setColorPressed(new Color(204,204,204));
        }else{
            this.cuatro.setColorNormal(new Color(239,238,244));
            this.cuatro.setColorHover(new Color(0,0,0));
            this.cuatro.setColorPressed(new Color(204,204,204));
        }
    }//GEN-LAST:event_cuatroActionPerformed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        Point mueve = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(mueve.x - x, mueve.y - y);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void pnlMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenuMouseExited
 
           
           
    }//GEN-LAST:event_pnlMenuMouseExited

    private void pnlMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenuMouseEntered
        
    }//GEN-LAST:event_pnlMenuMouseEntered

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
       
           
        
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        
    }//GEN-LAST:event_jButton1MouseExited

    private void jmbancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmbancoActionPerformed
      frmBanco banco=new frmBanco();
banco.setVisible(true);
    }//GEN-LAST:event_jmbancoActionPerformed

    private void jmautorizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmautorizacionActionPerformed
               frmAutorizacion au=new frmAutorizacion();
               au.setVisible(true);

    }//GEN-LAST:event_jmautorizacionActionPerformed

    private void jmcargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmcargosActionPerformed
        frmCargos car=new frmCargos();
        car.setVisible(true);
    }//GEN-LAST:event_jmcargosActionPerformed

    private void jmprogramacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmprogramacionActionPerformed
        frmProgramacion prog=new frmProgramacion();
       prog.setVisible(true);
    }//GEN-LAST:event_jmprogramacionActionPerformed

    private void jmafpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmafpActionPerformed
       frmAfp afp=new frmAfp();
afp.setVisible(true);
    }//GEN-LAST:event_jmafpActionPerformed

    private void jmsalonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmsalonesActionPerformed
       frmSalones salones=new frmSalones();
     salones.setVisible(true);
    }//GEN-LAST:event_jmsalonesActionPerformed

    private void jmeventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmeventosActionPerformed
         frmEventos evento=new frmEventos();
       evento.setVisible(true);
    }//GEN-LAST:event_jmeventosActionPerformed

    private void jmusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmusuariosActionPerformed
      frmUsuarios usu=new frmUsuarios();
usu.setVisible(true);
    }//GEN-LAST:event_jmusuariosActionPerformed

    private void JmtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmtipoActionPerformed
      frmTipoPlanilla tipo=new frmTipoPlanilla();
tipo.setVisible(true);
    }//GEN-LAST:event_JmtipoActionPerformed

    private void jmplanillasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmplanillasActionPerformed
      frmPlanilla pl=new frmPlanilla();
      pl.setVisible(true);
    }//GEN-LAST:event_jmplanillasActionPerformed

    private void jmdepartamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmdepartamentosActionPerformed
       frmDepartamento deps=new frmDepartamento();
       deps.setVisible(true);
    }//GEN-LAST:event_jmdepartamentosActionPerformed

    private void jmcontratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmcontratoActionPerformed
       frmContrato frm=new frmContrato();
       frm.setVisible(true);
        //Usuarios usu=sesionusu.getUsuarios();
//        if(usuario==null){
//            System.out.println("El usuario es nulo");
//        }else{
//             System.out.println("El usuario no es nulo");
//             System.out.println("usuario "+usuario.getNombre());
//        }
    }//GEN-LAST:event_jmcontratoActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    new Principal().setVisible(true);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Jmtipo;
    private rsbuttom.RSButtonMetro cinco;
    private rsbuttom.RSButtonMetro cuatro;
    private rsbuttom.RSButtonMetro dos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem jmafp;
    private javax.swing.JMenuItem jmautorizacion;
    private javax.swing.JMenuItem jmbanco;
    private javax.swing.JMenuItem jmcargos;
    private javax.swing.JMenuItem jmcontrato;
    private javax.swing.JMenuItem jmdepartamentos;
    private javax.swing.JMenuItem jmeventos;
    private javax.swing.JMenuItem jmplanillas;
    private javax.swing.JMenuItem jmprogramacion;
    private javax.swing.JMenuItem jmsalones;
    private javax.swing.JMenuItem jmusuarios;
    private javax.swing.JLabel lblusuario;
    private rsbuttom.RSButtonMetro ocho;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlPrincipal;
    private rojeru_san.RSLabelHora rSLabelHora1;
    private rsbuttom.RSButtonMetro seis;
    private rsbuttom.RSButtonMetro siete;
    private rsbuttom.RSButtonMetro tres;
    private rsbuttom.RSButtonMetro uno;
    // End of variables declaration//GEN-END:variables
}
