package presentacion.bienvenida;

import javax.swing.*;
import java.awt.*;

public class LaminaBienvenida extends JPanel {

    private JButton[] botones;

    public LaminaBienvenida() {
        
        setBackground(new Color(220, 220, 220));
        setLayout(new GridLayout(6, 1, 15, 15));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        botones = new JButton[6];

        botones[0] = new JButton("Alta nueva categoría de producto");
        botones[1] = new JButton("Alta de nuevo producto");
        botones[2] = new JButton("Alta de nuevo almacén");
        botones[3] = new JButton("Listado del inventario de un almacén");
        botones[4] = new JButton("Aplicar descuento por categoría");
        botones[5] = new JButton("Traspaso y cierre de un almacén");

        for (JButton boton : botones) {

            boton.setBackground(new Color(245, 245, 245));
            boton.setFocusPainted(false);

            boton.setFont(new Font("Tahoma", Font.BOLD, 12));

            add(boton);
        }
    }

    public JButton[] getBotones() {
        return botones;
    }
}
