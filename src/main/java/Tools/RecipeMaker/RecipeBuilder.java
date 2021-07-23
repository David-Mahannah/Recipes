package Tools.RecipeMaker;

import Tools.RecipeMaker.IngredientBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipeBuilder extends Frame
{
    private Label lblName;
    private Label lblRegion;
    private TextField tfCount;
    private TextField tfscd;
    private Button btnCount;
    private JDialog d;

    public RecipeBuilder()
    {

        // INGREDIENT DIALOG
        d = new IngredientBuilder();
        setLayout(new GridLayout(3, 1));

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

        add(top);
        add(second);
        btnCount = new Button("Add Ingredient");   // construct the Button component
        btnCount.addActionListener(new AddIngredientListener());
        add(btnCount);

        this.setTitle("Recipe Maker");
        this.setSize(250, 150);
        this.setResizable(false);
        this.setVisible(true);
    }

    private class AddIngredientListener implements ActionListener {
        // ActionEvent handler - Called back upon button-click.
        @Override
        public void actionPerformed(ActionEvent evt) {
            d.setVisible(true);
        }
    }
}
