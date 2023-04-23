package Interfaz_gráfica;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
import Código_juego.Auto;
import Código_juego.ListaMaterial;
import Código_juego.NodoMesa;
import Código_juego.NodoMaterial;
import Código_juego.ColaOrdenes;
import Código_juego.ColaBanda;
import Código_juego.Material;
import Código_juego.ListaAuto;
import Código_juego.NodoOrden;
import Código_juego.ListaMesa;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class CarFactory extends javax.swing.JFrame {
    private ListaMaterial listaMaterial = new ListaMaterial();//Iteración la lista materiales
    private ListaAuto listaAuto = new ListaAuto();//Iteración la lista Auto
    private int cantEspacios = 3;//Espacios totales para construir
    private ColaBanda colaBanda = new ColaBanda();//iteracion clase colabanda
    private int presupuesto = 15000;//Presupuesto inicial
    private JLabel[] espacios = new JLabel[6];//espacios para la interfaz
    private Material seleccionado = null;
    private int[] costoEspacios = {70000, 80000, 90000};//Costo de espacios extra
    
    //Mesas de trabajo
    private ListaMesa mesa1 = new ListaMesa();
    private ListaMesa mesa2 = new ListaMesa();
    private ListaMesa mesa3 = new ListaMesa();
    
    private boolean inicio = false;//inicio del juego
    private int tiempotras = 480000;//8 minutos en segundos
    
    //atributos de tiempo
    private int segundos = 0;
    private int minutos = 0;
    private int horas = 0;
    private String segundos_string;
    private String minutos_string;
    private String horas_string;
    private ColaOrdenes ordenes = new ColaOrdenes();//iteracion de ordenes de trabajo
    private int conteoOrden = 0; // Cuenta 15 segundos para añadir otra orden

    Timer timer = new Timer(1000,new ActionListener(){
        public void actionPerformed(ActionEvent e){
           //Generar ordenes aleatorias cada 15 segundos después de iniciar el 
           //juego.
            tiempotras = tiempotras - 1000;
            horas = (tiempotras/3600000);
            minutos = (tiempotras/60000)%60;
            segundos = (tiempotras/1000)%60;
            segundos_string = String.format("%02d",segundos);
            minutos_string = String.format("%02d",minutos);
            horas_string = String.format("%02d",horas);
            lsegundos.setText(horas_string);
            lhoras.setText(minutos_string);
            lminutos.setText(segundos_string);
            conteoOrden++;
            if(conteoOrden == 15 && tiempotras > 14000){
                conteoOrden = 0;
                generarOrden();
            }
            if(segundos == 0 && minutos == 0 && horas == 0){
                tiempotras = 480000;
                pIniciar.setBackground(new Color(73,126,118));
                lIniciar.setForeground(new Color(255,255,255));
                horas = (tiempotras/3600000);
                minutos = (tiempotras/60000)%60;
                segundos = (tiempotras/1000)%60;
                segundos_string = String.format("%02d",segundos);
                minutos_string = String.format("%02d",minutos);
                horas_string = String.format("%02d",horas);
                lsegundos.setText(horas_string);
                lhoras.setText(minutos_string);
                lminutos.setText(segundos_string);
                inicio = false;
                timer.stop();
                verificarWin();
                resetGame();
            }
        }
    });
    
    private void resetGame(){
        //Cada que inicia el juego se resetea y el presupuesto y espacios vuelven
        //a su estado original
        presupuesto = 15000;
        cantEspacios = 3;
        seleccionado = null;
        while(ordenes.inicio != null){
            ordenes.remover();
        }
        while(colaBanda.cabeza != null){
            colaBanda.remover();
        }
        for(int i = 0;i<6;i++){
            espacios[i].setIcon(null);
        }
        mesa1.limpiar();
        mesa2.limpiar();
        mesa3.limpiar();
    }
    
    private void verificarWin(){
        //Si no hay ordenes el jugador gana de lo contrario pierde
        boolean ganador = true;
        if(presupuesto < 0 || ordenes.inicio != null){
            ganador = false;
        }
        if(ganador == false){
            MensajeFinal winMessage = new MensajeFinal(0);
            winMessage.setVisible(true);
        }else{
            MensajeFinal winMessage = new MensajeFinal(ordenes.total - 1);
            winMessage.setVisible(true);
        }
    }
    
    private void rellenarBanda(){
        //rellenar materiales en la banda para la selección
        //los materiales ingresan de forma aleatoria
        if(cantEspacios-colaBanda.tamanio == 3){
            Material random;
            JLabel label;
            while(colaBanda.tamanio < cantEspacios){
                label = espacios[colaBanda.tamanio];
                random = listaMaterial.buscar((int)(Math.random() * 5) + 1);
                ImageIcon icono = new ImageIcon(random.imagen);
                label.setIcon(icono);
                colaBanda.agregar(random);
            }
        }
    }
    
    private void moverBanda(){
        //Movimientos de la cintatrasportadora
        int index = 0;
        NodoMaterial actual = colaBanda.cabeza;
        JLabel label;
        for (int i = 0; i < 6; i++){
            espacios[i].setIcon(null);
        }
        while(actual != null){
            label = espacios[index];
            ImageIcon icono = new ImageIcon(actual.material.imagen);
            label.setIcon(icono);
            index++;
            actual = actual.next;
        }
    }
    
    private void colocarMaterial(ListaMesa mesa){
        //sseleccion de material para confeccionar el automovil
        if(inicio && seleccionado != null){
            NodoMesa actual = mesa.primero;
            while(actual != null){
                if(actual.material == null){
                    ImageIcon icono = new ImageIcon(seleccionado.imagen);
                    actual.label.setIcon(icono);
                    colaBanda.remover();
                    moverBanda();
                    mesa.tamanioreal ++;
                    actual.material = seleccionado;
                    seleccionado = null;
                    lMaterialSelect.setText("Material seleccionado: ");
                    rellenarBanda();
                    revisarMesa(mesa);
                    return;
                }
                actual = actual.next;
            }
        }
    }
    
    private void revisarMesa(ListaMesa mesa){
        //segun lo que haya en la mesa valida la información con las ordenes
        //crea el auto y aumenta la ganancia
        if(ordenes.traerprimero() != null){
            Auto auto = ordenes.traerprimero();
            System.out.println(auto.materiales.tamanio + "" + mesa.tamanioreal);
            if(auto.materiales.tamanio != mesa.tamanioreal)
                return;
            NodoMesa actual = mesa.primero;
            boolean flag;

            while(actual != null){
                if(actual.material != null && auto.materiales.buscar(actual.material.tipo) == null)
                    return;
                actual = actual.next;
            }
            
            mesa.limpiar();

            presupuesto += auto.ganancia;
            lPresupuesto.setText("Presupuesto: "+presupuesto);

            ordenes.remover();
            rellenarTaOrdenes();
        }
    }
    
    private void rellenarTaOrdenes(){
        //confeccion de autos segun las ordenes
        taOrdenes.setText("");
        NodoOrden aux = ordenes.inicio;
        while(aux != null){
            taOrdenes.setText(taOrdenes.getText() + aux.index + ". " +aux.Vehiculo.nombreAuto + " \n");
            aux = aux.next;
        }
    }
    
    private void generarOrden(){
        double random = Math.random();  // genera un número aleatorio entre 0 y 1
        int ordenRandom = (int) Math.round(random * 2) + 1;  // escala y redondea al rango deseado

        switch(ordenRandom){
            case 1 -> ordenes.agregar(listaAuto.primero.auto);
            case 2 -> ordenes.agregar(listaAuto.primero.next.auto);
            case 3 -> ordenes.agregar(listaAuto.primero.next.next.auto);
        }

        rellenarTaOrdenes();
    };
    
    public CarFactory() {
        initComponents();
        // Ingreso de materiales
        Material material = new Material("Motor especial deportivo", "src\\images\\motor_1.png", 1);
        listaMaterial.insertar(material);
        material = new Material("Carroceria", "src\\images\\auto.png", 2);
        listaMaterial.insertar(material);
        material = new Material("Motor de carga", "src\\images\\motor_2.png", 3);
        listaMaterial.insertar(material);
        material = new Material("Carroceria especial", "src\\images\\coche.png", 4);
        listaMaterial.insertar(material);
        material = new Material("Llantas unicas de trabajo", "src\\images\\rueda.png", 5);
        listaMaterial.insertar(material);
        
        // Ingreso de autos
        //Confeccion de autos
        ListaMaterial materialXAuto = new ListaMaterial();
        materialXAuto.insertar(listaMaterial.buscar(1));
        materialXAuto.insertar(listaMaterial.buscar(2));
        Auto auto = new Auto("Superauto deportivo", 15000, materialXAuto);
        listaAuto.insertar(auto);
        
        materialXAuto = new ListaMaterial();
        materialXAuto.insertar(listaMaterial.buscar(3));
        materialXAuto.insertar(listaMaterial.buscar(2));
        auto = new Auto("Auto de carga de alta gama", 10000, materialXAuto);
        listaAuto.insertar(auto);
        
        materialXAuto = new ListaMaterial();
        materialXAuto.insertar(listaMaterial.buscar(3));
        materialXAuto.insertar(listaMaterial.buscar(4));
        materialXAuto.insertar(listaMaterial.buscar(5));
        auto = new Auto("Maquinaria de ultima tecnologia para trabajos pesados", 18000, materialXAuto);
        listaAuto.insertar(auto);
        
        espacios[0] = lEspacio6;
        espacios[1] = lEspacio5;
        espacios[2] = lEspacio4;
        espacios[3] = lEspacio3;
        espacios[4] = lEspacio2;
        espacios[5] = lEspacio1;
        
        mesa1.insertar(lm1Mat1, null);
        mesa1.insertar(lm1Mat2, null);
        mesa1.insertar(lm1Mat3, null);
        mesa2.insertar(lm2Mat1, null);
        mesa2.insertar(lm2Mat2, null);
        mesa2.insertar(lm2Mat3, null);
        mesa3.insertar(lm3Mat1, null);
        mesa3.insertar(lm3Mat2, null);
        mesa3.insertar(lm3Mat3, null);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        background = new javax.swing.JPanel();
        pOrdenes = new javax.swing.JPanel();
        lOrdenes = new javax.swing.JLabel();
        spOrdenes = new javax.swing.JScrollPane();
        taOrdenes = new javax.swing.JTextArea();
        pBanda = new javax.swing.JPanel();
        lEspacio1 = new javax.swing.JLabel();
        lEspacio2 = new javax.swing.JLabel();
        lEspacio3 = new javax.swing.JLabel();
        lEspacio4 = new javax.swing.JLabel();
        lEspacio5 = new javax.swing.JLabel();
        lEspacio6 = new javax.swing.JLabel();
        lTitle = new javax.swing.JLabel();
        pIniciar = new javax.swing.JPanel();
        lIniciar = new javax.swing.JLabel();
        pPresupuesto = new javax.swing.JPanel();
        lPresupuesto = new javax.swing.JLabel();
        lMesa1 = new javax.swing.JLabel();
        lMesa2 = new javax.swing.JLabel();
        lMesa3 = new javax.swing.JLabel();
        pMesa1 = new javax.swing.JPanel();
        lm1Mat1 = new javax.swing.JLabel();
        lm1Mat2 = new javax.swing.JLabel();
        lm1Mat3 = new javax.swing.JLabel();
        pMesa2 = new javax.swing.JPanel();
        lm2Mat1 = new javax.swing.JLabel();
        lm2Mat2 = new javax.swing.JLabel();
        lm2Mat3 = new javax.swing.JLabel();
        pMesa3 = new javax.swing.JPanel();
        lm3Mat1 = new javax.swing.JLabel();
        lm3Mat2 = new javax.swing.JLabel();
        lm3Mat3 = new javax.swing.JLabel();
        pAddSpace = new javax.swing.JPanel();
        lAddSpace = new javax.swing.JLabel();
        lBasurero = new javax.swing.JLabel();
        pTimer = new javax.swing.JPanel();
        lsegundos = new javax.swing.JLabel();
        lpuntos1 = new javax.swing.JLabel();
        lhoras = new javax.swing.JLabel();
        lpuntos2 = new javax.swing.JLabel();
        lminutos = new javax.swing.JLabel();
        lMaterialSelect = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pOrdenes.setBackground(new java.awt.Color(0, 179, 71));

        lOrdenes.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lOrdenes.setForeground(new java.awt.Color(255, 255, 255));
        lOrdenes.setText("ORDENES");

        taOrdenes.setBackground(new java.awt.Color(0, 179, 71));
        taOrdenes.setColumns(20);
        taOrdenes.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        taOrdenes.setForeground(new java.awt.Color(255, 255, 255));
        taOrdenes.setRows(5);
        taOrdenes.setEnabled(false);
        spOrdenes.setViewportView(taOrdenes);

        javax.swing.GroupLayout pOrdenesLayout = new javax.swing.GroupLayout(pOrdenes);
        pOrdenes.setLayout(pOrdenesLayout);
        pOrdenesLayout.setHorizontalGroup(
            pOrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pOrdenesLayout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(lOrdenes)
                .addGap(66, 66, 66))
            .addGroup(pOrdenesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pOrdenesLayout.setVerticalGroup(
            pOrdenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pOrdenesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lOrdenes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spOrdenes, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                .addContainerGap())
        );

        background.add(pOrdenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 0, 240, 550));

        pBanda.setBackground(new java.awt.Color(169, 169, 169));

        lEspacio1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lEspacio1.setPreferredSize(new java.awt.Dimension(64, 64));

        lEspacio2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lEspacio2.setPreferredSize(new java.awt.Dimension(64, 64));

        lEspacio3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lEspacio3.setPreferredSize(new java.awt.Dimension(64, 64));

        lEspacio4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lEspacio4.setPreferredSize(new java.awt.Dimension(64, 64));

        lEspacio5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lEspacio5.setPreferredSize(new java.awt.Dimension(64, 64));

        lEspacio6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lEspacio6.setPreferredSize(new java.awt.Dimension(64, 64));
        lEspacio6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lEspacio6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pBandaLayout = new javax.swing.GroupLayout(pBanda);
        pBanda.setLayout(pBandaLayout);
        pBandaLayout.setHorizontalGroup(
            pBandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBandaLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lEspacio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(lEspacio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(lEspacio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(lEspacio4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(lEspacio5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lEspacio6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        pBandaLayout.setVerticalGroup(
            pBandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBandaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pBandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lEspacio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lEspacio5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lEspacio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lEspacio4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lEspacio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lEspacio6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        background.add(pBanda, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 650, 80));

        lTitle.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lTitle.setText("CAR FACTORY FIDE");
        background.add(lTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        pIniciar.setBackground(new java.awt.Color(73, 126, 118));
        pIniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pIniciarMouseClicked(evt);
            }
        });

        lIniciar.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lIniciar.setForeground(new java.awt.Color(255, 255, 255));
        lIniciar.setText("Iniciar");
        lIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lIniciarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pIniciarLayout = new javax.swing.GroupLayout(pIniciar);
        pIniciar.setLayout(pIniciarLayout);
        pIniciarLayout.setHorizontalGroup(
            pIniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pIniciarLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lIniciar)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        pIniciarLayout.setVerticalGroup(
            pIniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pIniciarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lIniciar)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        background.add(pIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 570, 140, 40));

        pPresupuesto.setBackground(new java.awt.Color(244, 223, 118));

        lPresupuesto.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lPresupuesto.setText("PRESUPUESTO: ");

        javax.swing.GroupLayout pPresupuestoLayout = new javax.swing.GroupLayout(pPresupuesto);
        pPresupuesto.setLayout(pPresupuestoLayout);
        pPresupuestoLayout.setHorizontalGroup(
            pPresupuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPresupuestoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lPresupuesto)
                .addContainerGap(241, Short.MAX_VALUE))
        );
        pPresupuestoLayout.setVerticalGroup(
            pPresupuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPresupuestoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lPresupuesto)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        background.add(pPresupuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 650, 370, 40));

        lMesa1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lMesa1.setText("Mesa 1");
        background.add(lMesa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        lMesa2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lMesa2.setText("Mesa 2");
        background.add(lMesa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, -1, -1));

        lMesa3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lMesa3.setText("Mesa 3");
        background.add(lMesa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 220, -1, -1));

        pMesa1.setBackground(new java.awt.Color(168, 140, 79));
        pMesa1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pMesa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pMesa1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pMesa1Layout = new javax.swing.GroupLayout(pMesa1);
        pMesa1.setLayout(pMesa1Layout);
        pMesa1Layout.setHorizontalGroup(
            pMesa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMesa1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lm1Mat1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(lm1Mat2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(pMesa1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(lm1Mat3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pMesa1Layout.setVerticalGroup(
            pMesa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMesa1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(pMesa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lm1Mat1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lm1Mat2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(lm1Mat3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        background.add(pMesa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 250, 180));

        pMesa2.setBackground(new java.awt.Color(168, 140, 79));
        pMesa2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pMesa2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pMesa2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pMesa2Layout = new javax.swing.GroupLayout(pMesa2);
        pMesa2.setLayout(pMesa2Layout);
        pMesa2Layout.setHorizontalGroup(
            pMesa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMesa2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lm2Mat1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(lm2Mat2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMesa2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lm2Mat3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        pMesa2Layout.setVerticalGroup(
            pMesa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMesa2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pMesa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lm2Mat2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lm2Mat1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(lm2Mat3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        background.add(pMesa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, 250, 180));

        pMesa3.setBackground(new java.awt.Color(168, 140, 79));
        pMesa3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pMesa3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pMesa3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pMesa3Layout = new javax.swing.GroupLayout(pMesa3);
        pMesa3.setLayout(pMesa3Layout);
        pMesa3Layout.setHorizontalGroup(
            pMesa3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMesa3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lm3Mat1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(lm3Mat2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(pMesa3Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(lm3Mat3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pMesa3Layout.setVerticalGroup(
            pMesa3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMesa3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pMesa3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lm3Mat2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lm3Mat1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(lm3Mat3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        background.add(pMesa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, 250, 180));

        pAddSpace.setBackground(new java.awt.Color(182, 129, 137));
        pAddSpace.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pAddSpace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pAddSpaceMouseClicked(evt);
            }
        });

        lAddSpace.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lAddSpace.setForeground(new java.awt.Color(255, 255, 255));
        lAddSpace.setText("Agregar espacio a la banda");
        lAddSpace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lAddSpaceMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pAddSpaceLayout = new javax.swing.GroupLayout(pAddSpace);
        pAddSpace.setLayout(pAddSpaceLayout);
        pAddSpaceLayout.setHorizontalGroup(
            pAddSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pAddSpaceLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(lAddSpace)
                .addGap(22, 22, 22))
        );
        pAddSpaceLayout.setVerticalGroup(
            pAddSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAddSpaceLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lAddSpace)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        background.add(pAddSpace, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 300, 60));

        lBasurero.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lBasurero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/contenedor-de-basura.png"))); // NOI18N
        lBasurero.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lBasurero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lBasureroMouseClicked(evt);
            }
        });
        background.add(lBasurero, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 620, 64, 64));

        pTimer.setBackground(new java.awt.Color(255, 255, 255));
        pTimer.setLayout(null);

        lsegundos.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        lsegundos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lsegundos.setText("00");
        pTimer.add(lsegundos);
        lsegundos.setBounds(160, 10, 40, 30);

        lpuntos1.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        lpuntos1.setText(":");
        pTimer.add(lpuntos1);
        lpuntos1.setBounds(140, 6, 20, 30);

        lhoras.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        lhoras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lhoras.setText("00");
        pTimer.add(lhoras);
        lhoras.setBounds(20, 10, 40, 30);

        lpuntos2.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        lpuntos2.setText(":");
        pTimer.add(lpuntos2);
        lpuntos2.setBounds(70, 6, 20, 30);

        lminutos.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        lminutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lminutos.setText("08");
        pTimer.add(lminutos);
        lminutos.setBounds(90, 10, 40, 30);

        background.add(pTimer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 640, 220, 50));

        lMaterialSelect.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lMaterialSelect.setText("Material seleccionado: ");
        background.add(lMaterialSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lIniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lIniciarMouseClicked
        //inicio del juego
        if(!inicio){
            pIniciar.setBackground(Color.decode("#6c6767"));
            lIniciar.setForeground(Color.decode("#b9abab"));
            inicio = true;
            generarOrden();
            timer.start(); 
            rellenarBanda();
            lPresupuesto.setText("Presupuesto: "+presupuesto);
        }
    }//GEN-LAST:event_lIniciarMouseClicked

    private void pIniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pIniciarMouseClicked
        // TODO add your handling code here:
        lIniciarMouseClicked(evt);
    }//GEN-LAST:event_pIniciarMouseClicked

    private void lEspacio6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lEspacio6MouseClicked
        // TODO add your handling code here:
       //selección de materiales
        if(inicio){
            seleccionado = colaBanda.traerPrimero();
            lMaterialSelect.setText("Material seleccionado: "+seleccionado.nombre);
        }
    }//GEN-LAST:event_lEspacio6MouseClicked

    private void pAddSpaceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pAddSpaceMouseClicked
        // TODO add your handling code here:
        lAddSpaceMouseClicked(evt);
    }//GEN-LAST:event_pAddSpaceMouseClicked

    private void lAddSpaceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lAddSpaceMouseClicked
        // TODO add your handling code here:
        //disminucion de presupuesto en caso de agregar espacios a la banda
        if(inicio){
            presupuesto -= costoEspacios[cantEspacios-3];
            lPresupuesto.setText("Presupuesto: "+presupuesto);
            cantEspacios++;
            rellenarBanda();
        }
    }//GEN-LAST:event_lAddSpaceMouseClicked

    private void lBasureroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lBasureroMouseClicked
        // TODO add your handling code here:
        //Elimina el material de la banda
        if(inicio && seleccionado != null){
            colaBanda.remover();
            moverBanda(); 
            seleccionado = null;
            lMaterialSelect.setText("Material seleccionado: ");            
            rellenarBanda();
        }
    }//GEN-LAST:event_lBasureroMouseClicked

    private void pMesa2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMesa2MouseClicked
        // TODO add your handling code here:
        colocarMaterial(mesa2);//Iniciación de la mesa
    }//GEN-LAST:event_pMesa2MouseClicked

    private void pMesa3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMesa3MouseClicked
        // TODO add your handling code here:
        colocarMaterial(mesa3);//Iniciación de la mesa
    }//GEN-LAST:event_pMesa3MouseClicked

    private void pMesa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pMesa1MouseClicked
        // TODO add your handling code here:
        colocarMaterial(mesa1);//Iniciación de la mesa
    }//GEN-LAST:event_pMesa1MouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CarFactory().setVisible(true);//Visibilidad de la interfaz
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lAddSpace;
    private javax.swing.JLabel lBasurero;
    private javax.swing.JLabel lEspacio1;
    private javax.swing.JLabel lEspacio2;
    private javax.swing.JLabel lEspacio3;
    private javax.swing.JLabel lEspacio4;
    private javax.swing.JLabel lEspacio5;
    private javax.swing.JLabel lEspacio6;
    private javax.swing.JLabel lIniciar;
    private javax.swing.JLabel lMaterialSelect;
    private javax.swing.JLabel lMesa1;
    private javax.swing.JLabel lMesa2;
    private javax.swing.JLabel lMesa3;
    private javax.swing.JLabel lOrdenes;
    private javax.swing.JLabel lPresupuesto;
    private javax.swing.JLabel lTitle;
    private javax.swing.JLabel lhoras;
    private javax.swing.JLabel lm1Mat1;
    private javax.swing.JLabel lm1Mat2;
    private javax.swing.JLabel lm1Mat3;
    private javax.swing.JLabel lm2Mat1;
    private javax.swing.JLabel lm2Mat2;
    private javax.swing.JLabel lm2Mat3;
    private javax.swing.JLabel lm3Mat1;
    private javax.swing.JLabel lm3Mat2;
    private javax.swing.JLabel lm3Mat3;
    private javax.swing.JLabel lminutos;
    private javax.swing.JLabel lpuntos1;
    private javax.swing.JLabel lpuntos2;
    private javax.swing.JLabel lsegundos;
    private javax.swing.JPanel pAddSpace;
    private javax.swing.JPanel pBanda;
    private javax.swing.JPanel pIniciar;
    private javax.swing.JPanel pMesa1;
    private javax.swing.JPanel pMesa2;
    private javax.swing.JPanel pMesa3;
    private javax.swing.JPanel pOrdenes;
    private javax.swing.JPanel pPresupuesto;
    private javax.swing.JPanel pTimer;
    private javax.swing.JScrollPane spOrdenes;
    private javax.swing.JTextArea taOrdenes;
    // End of variables declaration//GEN-END:variables
}
