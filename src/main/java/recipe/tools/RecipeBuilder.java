package recipe.tools;
import recipe.classifiers.DietRestriction;
import recipe.classifiers.Flavors;
import recipe.util.Ingredient;
import recipe.util.Recipe;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeBuilder extends Frame
{
    // Frame Elements
    private JLabel lblName, lblRegion, lblIngredients, lblDiet, lblPrepTime, lblFlavors, lblServing;
    private TextField tfName, tfRegion, tfDiet, tfPrep, tfFlavors, tfServing;
    private JTextArea jtDirections;

    private JButton btnCount;
    private JDialog d;

    private int rows;
    private int cols;

    // Data
    private ArrayList<Ingredient> ingredients;
    private DataHandler datahandler;

    // Temporary info store
    private String region, name, diet, prepTime, flavor, serving;

    public RecipeBuilder(DataHandler datahandler) throws IOException {
        this.datahandler = datahandler;
//        this.setIconImage(ImageIO.read(new File("res/icon.png")));
        ingredients = new ArrayList<Ingredient>();

        // INGREDIENT DIALOG
        d = new IngredientBuilder(this);
        rows = 9;
        cols = 1;

        setLayout(new GridLayout(rows, cols, 10, 10));
        drawDefault();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void addIngredient(Ingredient i)
    {
        removeAll();
        ingredients.add(i);
        this.setLayout(new GridLayout(rows+=1, cols));
        drawDefault();
    }

    public void drawDefault()
    {
        // TOP
        Panel top = new Panel();
        top.setLayout(new GridLayout(1, 2));
        lblName = new JLabel("Name: ");
        lblName.setHorizontalAlignment(JLabel.CENTER);
        top.add(lblName);

        tfName = new TextField();
        tfName.setEditable(true);
        top.add(tfName);

        // SECOND
        Panel second = new Panel();
        second.setLayout(new GridLayout(1, 2));
        lblRegion = new JLabel("Region: ");
        lblRegion.setHorizontalAlignment(JLabel.CENTER);
        second.add(lblRegion);

        tfRegion = new TextField();
        tfRegion.setEditable(true);
        second.add(tfRegion);

        // THIRD
        Panel third = new Panel();
        third.setLayout(new GridLayout(1, 2));
        lblDiet = new JLabel("Diet: ");
        lblDiet.setHorizontalAlignment(JLabel.CENTER);
        third.add(lblDiet);

        tfDiet = new TextField();
        tfDiet.setEditable(true);
        third.add(tfDiet);

        // FOURTH
        Panel fourth = new Panel();
        fourth.setLayout(new GridLayout(1, 2));
        lblPrepTime = new JLabel("Prep Time (mins): ");
        lblPrepTime.setHorizontalAlignment(JLabel.CENTER);
        fourth.add(lblPrepTime);

        tfPrep = new TextField();
        tfPrep.setEditable(true);
        fourth.add(tfPrep);

        // FIFTH - FLAVORS
        Panel five = new Panel();
        five.setLayout(new GridLayout(1, 2));
        lblFlavors = new JLabel("RecipeTools.Classifiers.Flavors: ");
        lblFlavors.setHorizontalAlignment(JLabel.CENTER);
        five.add(lblFlavors);

        tfFlavors = new TextField();
        tfFlavors.setEditable(true);
        five.add(tfFlavors);

        // SIXTH = SERVING SIZE
        Panel six = new Panel();
        six.setLayout(new GridLayout(1, 2));
        lblServing = new JLabel("Servings: ");
        lblServing.setHorizontalAlignment(JLabel.CENTER);
        six.add(lblServing);

        tfServing = new TextField();
        tfServing.setEditable(true);
        six.add(tfServing);

        // Reset cleared values in textFields
        tfName.setText(name);
        tfRegion.setText(region);
        tfDiet.setText(diet);
        tfPrep.setText(prepTime);
        tfServing.setText(serving);
        tfFlavors.setText(flavor);

        // Ingredients list
        lblIngredients = new JLabel("Ingredients:");
        lblIngredients.setHorizontalAlignment(JLabel.CENTER);

        // Directions
        jtDirections = new JTextArea(10, 10);

        TitledBorder title;
        title = BorderFactory.createTitledBorder("Directions");
        title.setTitleJustification(TitledBorder.LEFT);
        jtDirections.setBorder(title);

        add(top);
        add(second);
        add(third);
        add(fourth);
        add(five);
        add(six);
        add(jtDirections);
        add(lblIngredients);

        boolean every_other = true;
        for (Ingredient ing : ingredients)
        {
            Panel another = new Panel();
            if (every_other) { another.setBackground(Color.GRAY); every_other=false; }
            else {every_other=true;}
            another.setLayout(new GridLayout(1, 3));
            another.add(new Label(ing.getName()));
            another.add(new Label(Double.toString(ing.getAmount())));
            another.add(new Label(ing.getUnit_of_measurement()));
            add(another);
        }

        Panel fynal = new Panel();
        fynal.setLayout(new GridLayout(1, 3));

        JButton b = new JButton ("Done");
        btnCount = new JButton("Add RecipeTools.util.Ingredient");   // construct the Button component
        btnCount.addActionListener(new AddIngredientListener());

        b.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                try {
                    // ArrayList<String> diets = RecipeTools.Classifiers.DietRestriction.processList(tfDiet.getText());
                    Recipe r = new Recipe(tfName.getText(),
                            tfRegion.getText(),
                            -1.0,
                            Integer.parseInt(tfServing.getText()),
                            Integer.parseInt(tfPrep.getText()),
                            DietRestriction.processList(tfDiet.getText()),
                            Flavors.processList(tfFlavors.getText()),
                            ingredients,
                            jtDirections.getText()
                    );

                    //RecipeTools.util.Recipe r = new RecipeTools.util.Recipe(tfName.getText(), ingredients, jtDirections.getText());
                    Document d = r.documentify();
                    InsertOneResult result = datahandler.addRecipe(d);
                    System.out.println("SUCCESS:" + result.wasAcknowledged());
                    setVisible(false);
                } catch (Exception ex) {
                    System.out.println(ex);
                    System.out.println("Broke :/");
                    setVisible(false);
                }
            }
        });

        fynal.add(b);
        fynal.add(btnCount);
        add(fynal);

        this.setTitle("RecipeTools.util.Recipe Maker");
        this.setSize(300, 250+(15*rows));
        this.setResizable(false);
        this.setVisible(true);
    }

    private class AddIngredientListener implements ActionListener {
        // ActionEvent handler - Called back upon button-click.
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Store values before gui refresh
            region = tfRegion.getText();
            name = tfName.getText();
            diet = tfDiet.getText();
            prepTime = tfPrep.getText();
            flavor = tfFlavors.getText();
            serving = tfServing.getText();

            d.setSize(250, 150);
            d.setResizable(false);
            d.setVisible(true);
        }
    }
}
