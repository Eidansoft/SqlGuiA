package com.eidansoft.sqlguia.formularios;

import com.eidansoft.sqlguia.ConfiguracionApp;
import com.eidansoft.sqlguia.ConfiguracionBD;
import com.eidansoft.sqlguia.FuncionesComunesDeArchivos;
import com.eidansoft.sqlguia.consulta.Campo;
import com.eidansoft.sqlguia.consulta.Consulta;
import com.eidansoft.sqlguia.consulta.Tabla;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.TabableView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author alorente
 */
public class SQLGuiA extends javax.swing.JFrame {

    private final int TABLA = 1;
    private final int CAMPO = 2;
    /**
     * Variables globales
     */
    private static Logger log = Logger.getLogger(SQLGuiA.class.getName());
    private Connection conn = null;
    private String archivoConfApp = "./configuracionApp.xml";
    private Consulta consultaActual;
    private Properties sentencias = new Properties();
    
    //Limite de resultados maximos a mostrar de una consulta
    ConfiguracionApp confApp = null;
    ConfiguracionBD confBDs = null;
    
    /**
     * Creates new form SQLGuiA
     */
    public SQLGuiA() {
        initComponents();
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Logger.getLogger(SQLGuiA.class.getName()).log(Level.INFO, "Cerrrando conexion");
                    if (conn != null)
                        conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SQLGuiA.class.getName()).log(Level.SEVERE, "No se pudo cerrar la conexion correctamente", ex);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        //Cargo las configuraciones (si existe el archivo)
        try {
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("configuracionApp", ConfiguracionApp.class);
            File xmlFile = new File(archivoConfApp);
            confApp = (ConfiguracionApp) xstream.fromXML(new FileInputStream(xmlFile));
        } catch (StreamException | FileNotFoundException e){
            log.info("Archivo de configuracion NO cargado");
            log.info("Creo archivo de configuracion por defecto");
            confApp = new ConfiguracionApp();
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("configuracionApp", ConfiguracionApp.class);
            String xml = xstream.toXML(confApp);
            StringBuilder sb = new StringBuilder();
            try {
                sb.append("<?xml version=\"1.0\"?>\n".getBytes("UTF-8")); //write XML header, as XStream doesn't do that for us
                sb.append(xml.getBytes("UTF-8"));
                FuncionesComunesDeArchivos.guardaArchivo(archivoConfApp, sb.toString());
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SQLGuiA.class.getName()).log(Level.SEVERE, "Error al guardar el archivo de configuracion en UTF-8", ex);
            }
            
        }
        
        //Cargo el ultimo sql usado (si lo hay)
        String sql = new String(FuncionesComunesDeArchivos.cargaArchivo(confApp.getRutaArchivoSql()));
        txtSql.setText(sql);

        // configuro el arbol de tablas por defecto
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("No hay conexion");
        treeTablas.setModel(new DefaultTreeModel(root));
        
        try {
            //Cargo las SQLs
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream is = loader.getResourceAsStream("sqls.properties");
            sentencias.load(is);
        } catch (FileNotFoundException ex) {
            String msg = "Se ha producido un error al cargar el archivo de SQLs";
            Logger.getLogger(SQLGuiA.class.getName()).log(Level.SEVERE, msg, ex);
            throw new RuntimeException(msg);
        } catch (IOException ex) {
            String msg = "Se ha producido un error al cargar el archivo de SQLs";
            Logger.getLogger(SQLGuiA.class.getName()).log(Level.SEVERE, msg, ex);
            throw new RuntimeException(msg);
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

        lblInfo = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btnConecta = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        treeTablas = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSql = new javax.swing.JTextArea();
        btnEjecuta = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        gridTablaResultados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblInfo.setText("No conectado");

        jToolBar1.setRollover(true);

        btnConecta.setText("Conexion");
        btnConecta.setFocusable(false);
        btnConecta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConecta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnConecta);

        treeTablas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                treeTablasMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(treeTablas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel1);

        txtSql.setColumns(20);
        txtSql.setRows(5);
        txtSql.setText("SELECT owner, table_name FROM all_tables");
        jScrollPane1.setViewportView(txtSql);

        btnEjecuta.setText("Ejecuta!");
        btnEjecuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutaActionPerformed(evt);
            }
        });

        gridTablaResultados.setModel(new javax.swing.table.DefaultTableModel(
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
        gridTablaResultados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(gridTablaResultados);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(btnEjecuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEjecuta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInfo))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEjecutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutaActionPerformed
        if (conn != null){
            try{
                Statement stmt = conn.createStatement();
                String sql = txtSql.getText();
                log.info("Ejecutando: " + sql);
                ResultSet rset = stmt.executeQuery(sql);
                muestraResultados(rset);
                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "No se pudo ejecutar la SQL : " + e.getMessage());
            }
        } else {
            lblInfo.setText("No conectado");
            JOptionPane.showMessageDialog(null, "No estas conectado");
        }
    }//GEN-LAST:event_btnEjecutaActionPerformed

    private void btnConectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectaActionPerformed
        JFrame configuraBD = new ConfiguracionesBD() {
            @Override
            public void configuracionLista(ConfiguracionBD configuracionSeleccionada) {
                confBDs = configuracionSeleccionada;
                try {
                    if (confBDs.getTipo() == ConfiguracionBD.ORACLE){
                        DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
                        Logger.getLogger(SQLGuiA.class.getName()).log(Level.INFO, "Conectando... jdbc:oracle:thin:@" + confBDs.getIp() + ":" + confBDs.getPuerto() + ":" + confBDs.getSID());
                        conn = DriverManager.getConnection("jdbc:oracle:thin:@" + confBDs.getIp() + ":" + confBDs.getPuerto() + ":" + confBDs.getSID() + "", confBDs.getUsuario(), confBDs.getPw());
                    } else if (confBDs.getTipo() == ConfiguracionBD.MYSQL){
                        DriverManager.registerDriver (new com.mysql.jdbc.Driver());
                        Logger.getLogger(SQLGuiA.class.getName()).log(Level.INFO, "Conectando... jdbc:mysql://" + confBDs.getIp() + ":" + confBDs.getPuerto() + "/" + confBDs.getSID());
                        conn = DriverManager.getConnection("jdbc:mysql://" + confBDs.getIp() + ":" + confBDs.getPuerto() + "/" + confBDs.getSID(), confBDs.getUsuario(), confBDs.getPw());
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al conectar: " + e.getMessage());
                }
                lblInfo.setText("Conectado");
                muestraTablas();
                setVisible(true);
            }
        };
        
        //Display the window. 
        configuraBD.setAlwaysOnTop(true);
        configuraBD.pack();  
        configuraBD.setVisible(true); 
    }//GEN-LAST:event_btnConectaActionPerformed

    private void treeTablasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeTablasMousePressed
        int selRow = treeTablas.getRowForLocation(evt.getX(), evt.getY());
        TreePath selPath = treeTablas.getPathForLocation(evt.getX(), evt.getY());
        if(selRow != -1) {
            if(evt.getClickCount() == 1) {
                // No hago nada
            }
            else if(evt.getClickCount() == 2) {
                //myDoubleClick(selRow, selPath);
                //JOptionPane.showMessageDialog(null, "Doble clic: " + selPath.toString());
                if (tipoElementoSeleccionado(selPath) == TABLA){
                    Tabla t = getTabla(selPath);
                    consultaActual = new Consulta(t);
                    txtSql.setText(consultaActual.toString());
                    cargaCamposDeTabla(t, selPath);
                } else if (tipoElementoSeleccionado(selPath) == CAMPO){
                    Campo c = getCampo(selPath);
                    consultaActual.getCampos().add(c);
                    txtSql.setText(consultaActual.toString());
                }
            }
        }
    }//GEN-LAST:event_treeTablasMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConecta;
    private javax.swing.JButton btnEjecuta;
    private javax.swing.JTable gridTablaResultados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JTree treeTablas;
    private javax.swing.JTextArea txtSql;
    // End of variables declaration//GEN-END:variables

    private void muestraResultados(ResultSet rset) throws SQLException{
        int numeroCol = rset.getMetaData().getColumnCount();
        int numeroFilas = confApp.getMaxRows();
        
        String cabeceras[] = new String[numeroCol];
        for (int i = 1; i<=numeroCol; i++) {
            cabeceras[i-1] = rset.getMetaData().getColumnName(i);
        }
        
        String resultados[][] = new String[numeroFilas][numeroCol];
        int indice = 0;
        while (rset.next() && indice<numeroFilas){
            //System.out.println (rset.getString(1) + " " + rset.getString(2));
            for (int col=1; col <= numeroCol; col++){
                resultados[indice][col-1] = rset.getString(col);
            }
            indice++;
        }
        
        DefaultTableModel dtm = new DefaultTableModel(resultados, cabeceras);
        gridTablaResultados.setModel(dtm);
        gridTablaResultados.updateUI();
        resizeColumnWidth(gridTablaResultados);
        gridTablaResultados.updateUI();
    }
    
    private void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    private void muestraTablas() {
        try{
            Statement stmt = conn.createStatement();
            String sql = getSqlTodasTablas();
            log.info("Ejecutando: " + sql);
            ResultSet rset = stmt.executeQuery(sql);
            
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(confBDs.getUsuario());
            //create the child nodes
            DefaultMutableTreeNode nodoTablas = new DefaultMutableTreeNode("Tablas");
            root.add(nodoTablas);
            
            while (rset.next()){
                //System.out.println (rset.getString(1) + " " + rset.getString(2));
                DefaultMutableTreeNode nuevaTabla = new DefaultMutableTreeNode(rset.getString(1));
                nodoTablas.add(nuevaTabla);
            }
            stmt.close();
            
            treeTablas.removeAll();
            treeTablas.setModel(new DefaultTreeModel(root));
            treeTablas.updateUI();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la SQL : " + e.getMessage());
        }
    }
    
    public void cierraConexiones(){
        lblInfo.setText("No conectado");
        try {
            conn.close();
        } catch (SQLException ex) {
            log.info("Error al intentar cerrar la conexion.");
        }
    }

    private int tipoElementoSeleccionado(TreePath selPath) {
        int res = -1;
        if (selPath.getPathCount() == 3){
            res = TABLA;
        } else if (selPath.getPathCount() == 4){
            res = CAMPO;
        }
        return res;
    }

    private Tabla getTabla(TreePath selPath) {
        String res = "";
        if (tipoElementoSeleccionado(selPath) == TABLA){
            res = selPath.getPath()[selPath.getPathCount()-1].toString();
        } else if (tipoElementoSeleccionado(selPath) == CAMPO){
            res = selPath.getPath()[selPath.getPathCount()-2].toString();
        } else {
            throw new IllegalArgumentException("No se ha seleccionado un elemento valido para obtener la tabla");
        }
        return new Tabla(res);
    }

    private Campo getCampo(TreePath selPath) {
        String res = "";
        if (tipoElementoSeleccionado(selPath) == CAMPO){
            res = selPath.getPath()[selPath.getPathCount()-1].toString();
        } else {
            throw new IllegalArgumentException("No se ha seleccionado un elemento valido para obtener el campo");
        }
        return new Campo(res);
    }

    private void cargaCamposDeTabla(Tabla t, TreePath arbol) {
        try{
            Statement stmt = conn.createStatement();
            String sql = getSqlTodosCampos(t);
            log.info("Ejecutando: " + sql);
            ResultSet rset = stmt.executeQuery(sql);
            
            DefaultTreeModel model = (DefaultTreeModel) treeTablas.getModel();
            MutableTreeNode tabla = (DefaultMutableTreeNode) arbol.getLastPathComponent();
            while (rset.next()){
                //System.out.println (rset.getString(1) + " " + rset.getString(2));
                DefaultMutableTreeNode nuevoCampo = new DefaultMutableTreeNode(rset.getString(1));
                model.insertNodeInto(nuevoCampo, tabla, tabla.getChildCount());
            }
            stmt.close();
            
            //treeTablas.removeAll();
            //treeTablas.setModel(new DefaultTreeModel(root));
            treeTablas.updateUI();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la SQL : " + e.getMessage());
        }
    }

    private String getSqlTodasTablas() {
        String sql = null;
        if (confBDs.getTipo() == ConfiguracionBD.ORACLE){
            sql = sentencias.getProperty("oracle.sql.getTodasTablas");
            sql = MessageFormat.format(sql, confBDs.getUsuario());
        } else if (confBDs.getTipo() ==  ConfiguracionBD.MYSQL){
            sql = sentencias.getProperty("mysql.sql.getTodasTablas");
            sql = MessageFormat.format(sql, confBDs.getSID());
        }
        return sql;
    }

    private String getSqlTodosCampos(Tabla t) {
        String sql = null;
        if (confBDs.getTipo() == ConfiguracionBD.ORACLE){
            sql = sentencias.getProperty("oracle.sql.getTodosCampos");
            sql = MessageFormat.format(sql, t.getNombre(), confBDs.getUsuario());
        } else if (confBDs.getTipo() ==  ConfiguracionBD.MYSQL){
            sql = sentencias.getProperty("mysql.sql.getTodosCampos");
            sql = MessageFormat.format(sql, confBDs.getSID(), t.getNombre());
        }
        return sql;
    }
}
