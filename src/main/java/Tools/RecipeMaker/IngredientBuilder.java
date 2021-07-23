package Tools.RecipeMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngredientBuilder extends JDialog
{
    private Label lblName;
    private Label lblRegion;
    private Label lblUnit;
    private TextField tfCount;
    private TextField tfscd;
    private TextField tfthd;
    private Button btnCount;

    public IngredientBuilder()
    {

        // INGREDIENT DIALOG
        setTitle("New Ingredient");
        setModal(true);
        //d = new JDialog(this, "Dialog Example", true);
        setLayout(new GridLayout(4, 1));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        JButton b = new JButton ("ADD");
        JButton c = new JButton ("CANCEL");

        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        b.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                setVisible(false);
            }
        });


        setLayout(new GridLayout(4, 1));

        // TOP
        Panel top = new Panel();
        top.setLayout(new GridLayout(1, 2));
        top.add(new Label("Name: "));

        tfCount = new TextField();
        tfCount.setEditable(true);
        top.add(tfCount);

        // SECOND
        Panel second = new Panel();
        second.setLayout(new GridLayout(1, 2));
        lblRegion = new Label("Region ");
        second.add(lblRegion);
        tfscd = new TextField();
        tfscd.setEditable(true);
        second.add(tfscd);

        // THIRD
        Panel third = new Panel();
        third.setLayout(new GridLayout(1, 2));
        lblUnit = new Label("Unit");
        third.add(lblUnit);
        tfthd = new TextField();
        tfthd.setEditable(true);
        third.add(tfthd);

        add(top);
        add(second);
        add(third);

        buttonsPanel.add(c);
        buttonsPanel.add(b);
        add(buttonsPanel);
        setSize(300,300);

//        this.setTitle("Recipe Maker");
//        this.setSize(250, 150);
//        this.setResizable(false);
//        this.setVisible(true);
    }

    private class AddIngredient implements ActionListener {
        // ActionEvent handler - Called back upon button-click.
        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }
}

