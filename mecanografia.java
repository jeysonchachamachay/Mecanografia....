java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TecladoVirtual extends JFrame implements ActionListener {
    private JButton[] teclas;
    private JTextArea texto;
    private String[] pangramas;
    private int pulsacionesCorrectas;
    private int pulsacionesIncorrectas;
    private String teclasDificultad;

    public TecladoVirtual() {
        // Configuración de la ventana
        setTitle("Teclado Virtual");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creación del teclado virtual
        teclas = new JButton[26];
        JPanel panelTeclado = new JPanel(new GridLayout(2, 13));
        for (int i = 0; i < 26; i++) {
            char letra = (char) (65 + i);
            teclas[i] = new JButton(String.valueOf(letra));
            teclas[i].addActionListener(this);
            panelTeclado.add(teclas[i]);
        }
        
        // Creación del área de texto
        texto = new JTextArea();
        texto.setEditable(false);
        
        // Composición de la interfaz
        setLayout(new BorderLayout());
        add(panelTeclado, BorderLayout.CENTER);
        add(new JScrollPane(texto), BorderLayout.SOUTH);
        
        // Carga de los pangramas desde el archivo de texto plano
        cargarPangramas();
        
        // Inicialización de las variables
        pulsacionesCorrectas = 0;
        pulsacionesIncorrectas = 0;
        teclasDificultad = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        
        // Resaltar el botón correspondiente en la GUI
        boton.setBackground(Color.YELLOW);
        try {
            Thread.sleep(200); // Retraso para el efecto visual
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        boton.setBackground(null);
        
        // Agregar el carácter al área de texto
        String caracter = boton.getText();
        texto.append(caracter);
        
        // Verificar la precisión del usuario
        if (caracter.equals(pangramas[0].substring(texto.getText().length() - 1, texto.getText().length()))) {
            pulsacionesCorrectas++;
        } else {
            pulsacionesIncorrectas++;
            if (!teclasDificultad.contains(caracter)) {
                teclasDificultad += caracter + " ";
            }
        }
        
        // Mostrar un pangrama aleatorio en la pantalla
        mostrarPangramaAleatorio();
    }
    
    private void cargarPangramas() {
        // Código para cargar los pangramas desde un archivo de texto plano
        // y almacenarlos en el arreglo pangramas[]
    }
    
    private void mostrarPangramaAleatorio() {
        // Código para mostrar un pangrama aleatorio en la pantalla
    }
    
    public void generarInforme() {
        // Código para generar y mostrar el informe con las teclas de dificultad
    }

    public static void main(String[] args) {
        TecladoVirtual tecladoVirtual = new TecladoVirtual();
        tecladoVirtual.setSize(500, 300);
        tecladoVirtual.setVisible(true);
    }
}

