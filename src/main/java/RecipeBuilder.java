import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeBuilder extends Frame
{
    private Label lblName;
    private JLabel lblRegion;
    private JLabel lblIngredients;
    private JLabel lblInstructions;
    private TextField tfCount;
    private TextField tfscd;
    private JButton btnCount;
    private JDialog d;
    private JTextArea jt;
    private int rows;
    private int cols;
    private ArrayList<Ingredient> ingredients;
    private DataHandler datahandler;

    // Temporary info store
    private String region;
    private String name;

    public void addIngredient(Ingredient i)
    {
        removeAll();
        ingredients.add(i);
        this.setLayout(new GridLayout(rows+=1, cols));

        drawDefault();

//        // TOP
//        Panel top = new Panel();
//        top.setLayout(new GridLayout(1, 2));
//        JLabel lblname = new JLabel("Name: ");
//        lblname.setHorizontalAlignment(JLabel.CENTER);
//        top.add(lblname);
//
//        tfCount = new TextField();
//        tfCount.setEditable(true);
//        top.add(tfCount);
//
//        // SECOND
//        Panel second = new Panel();
//        second.setLayout(new GridLayout(1, 2));
//        lblRegion = new JLabel("Region: ");
//        lblRegion.setHorizontalAlignment(JLabel.CENTER);
//        second.add(lblRegion);
//
//        tfscd = new TextField();
//        tfscd.setEditable(true);
//        second.add(tfscd);
//
//        tfCount.setText(name);
//        tfscd.setText(region);
//        lblIngredients = new JLabel("Ingredients:");
//        lblIngredients.setHorizontalAlignment(JLabel.CENTER);
//
//        JTextArea jt = new JTextArea(10, 10);
//
//        TitledBorder title;
//        title = BorderFactory.createTitledBorder("Instructions");
//        title.setTitleJustification(TitledBorder.LEFT);
//        jt.setBorder(title);
//
//        add(top);
//        add(second);
//
//        add(jt);
//        add(lblIngredients);
//
//        boolean every_other = true;
//        for (Ingredient ing : ingredients)
//        {
//            Panel third = new Panel();
//            if (every_other) { third.setBackground(Color.GRAY); every_other=false; }
//            else {every_other=true;}
//            third.setLayout(new GridLayout(1, 3));
//            third.add(new Label(ing.getName()));
//            third.add(new Label(Double.toString(ing.getAmount())));
//            third.add(new Label(ing.getUnit_of_measurement()));
//            add(third);
//        }
//
//        Panel fourth = new Panel();
//        fourth.setLayout(new GridLayout(1, 3));
//
//        JButton b = new JButton ("Done");
//        btnCount = new JButton("Add Ingredient");   // construct the Button component
//        btnCount.addActionListener(new AddIngredientListener());
//
//
//        b.addActionListener ( new ActionListener()
//        {
//            public void actionPerformed( ActionEvent e )
//            {
//                try {
//                    Recipe r = new Recipe(tfCount.getText(), ingredients, jt.getText());
//                    Document d = r.documentify();
//
//                    InsertOneResult rdzc = datahandler.addRecipe(d);
//                    System.out.println("SUCCESS:" + rdzc.wasAcknowledged());
//                    setVisible(false);
//                } catch (Exception ex) {
//                    System.out.println("Broke :/");
//                    setVisible(false);
//                }
//            }
//        });
//
//        fourth.add(b);
//        fourth.add(btnCount);
//
//        add(fourth);
//
//        this.setTitle("Recipe Maker");
//        this.setSize(300, 200+(15*rows));
//        this.setResizable(false);
//        this.setVisible(true);
    }

    public void drawDefault()
    {
        // TOP
        Panel top = new Panel();
        top.setLayout(new GridLayout(1, 2));
        JLabel name = new JLabel("Name: ");
        name.setHorizontalAlignment(JLabel.CENTER);
        top.add(name);

        tfCount = new TextField();
        tfCount.setEditable(true);
        top.add(tfCount);

        // SECOND
        Panel second = new Panel();
        second.setLayout(new GridLayout(1, 2));
        lblRegion = new JLabel("Region: ");
        lblRegion.setHorizontalAlignment(JLabel.CENTER);
        second.add(lblRegion);

        tfscd = new TextField();
        tfscd.setEditable(true);
        second.add(tfscd);

        lblIngredients = new JLabel("Ingredients:");
        lblIngredients.setHorizontalAlignment(JLabel.CENTER);

        Panel third = new Panel();
        third.setSize(300, 100);
        third.setLayout(new GridLayout(1, 2));
        lblInstructions = new JLabel("Instructions");
        lblInstructions.setHorizontalAlignment(JLabel.CENTER);

        JTextArea jt = new JTextArea(10, 10);

        TitledBorder title;
        title = BorderFactory.createTitledBorder("Instructions");
        title.setTitleJustification(TitledBorder.LEFT);
        jt.setBorder(title);

        add(top);
        add(second);
        add(jt);

        Panel fourth = new Panel();
        fourth.setLayout(new GridLayout(1, 3));

        JButton b = new JButton ("Done");
        btnCount = new JButton("Add Ingredient");   // construct the Button component
        btnCount.addActionListener(new AddIngredientListener());

        b.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                Recipe r = new Recipe(tfCount.getText(), ingredients, jt.getText());
                Document d = r.documentify();

                InsertOneResult rdzc = datahandler.addRecipe(d);
                System.out.println("SUCCESS:" + rdzc.wasAcknowledged());
                setVisible(false);
            }
        });

        fourth.add(b);
        fourth.add(btnCount);

        add(fourth);

        this.setTitle("Recipe Maker");
        this.setSize(300, 300);
        this.setResizable(false);
        this.setVisible(true);
    }

    public RecipeBuilder(DataHandler datahandler) throws IOException {
        this.datahandler = datahandler;
        this.setIconImage(ImageIO.read(new File("res/icon.png")));
        ingredients = new ArrayList<Ingredient>();

        // INGREDIENT DIALOG
        d = new IngredientBuilder(this);
        rows = 4;
        cols = 1;
        setLayout(new GridLayout(rows, cols, 10, 10));
        rows++;

        drawDefault();
//        // TOP
//        Panel top = new Panel();
//        top.setLayout(new GridLayout(1, 2));
//        JLabel name = new JLabel("Name: ");
//        name.setHorizontalAlignment(JLabel.CENTER);
//        top.add(name);
//
//        tfCount = new TextField();
//        tfCount.setEditable(true);
//        top.add(tfCount);
//
//        // SECOND
//        Panel second = new Panel();
//        second.setLayout(new GridLayout(1, 2));
//        lblRegion = new JLabel("Region: ");
//        lblRegion.setHorizontalAlignment(JLabel.CENTER);
//        second.add(lblRegion);
//
//        tfscd = new TextField();
//        tfscd.setEditable(true);
//        second.add(tfscd);
//
//        lblIngredients = new JLabel("Ingredients:");
//        lblIngredients.setHorizontalAlignment(JLabel.CENTER);
//
//        Panel third = new Panel();
//        third.setSize(300, 100);
//        third.setLayout(new GridLayout(1, 2));
//        lblInstructions = new JLabel("Instructions");
//        lblInstructions.setHorizontalAlignment(JLabel.CENTER);
//
//        JTextArea jt = new JTextArea(10, 10);
//
//        TitledBorder title;
//        title = BorderFactory.createTitledBorder("Instructions");
//        title.setTitleJustification(TitledBorder.LEFT);
//        jt.setBorder(title);
//
//        add(top);
//        add(second);
//        add(jt);
//
//        Panel fourth = new Panel();
//        fourth.setLayout(new GridLayout(1, 3));
//
//        JButton b = new JButton ("Done");
//        btnCount = new JButton("Add Ingredient");   // construct the Button component
//        btnCount.addActionListener(new AddIngredientListener());
//
//        b.addActionListener ( new ActionListener()
//        {
//            public void actionPerformed( ActionEvent e )
//            {
//                Recipe r = new Recipe(tfCount.getText(), ingredients, jt.getText());
//                Document d = r.documentify();
//
//                InsertOneResult rdzc = datahandler.addRecipe(d);
//                System.out.println("SUCCESS:" + rdzc.wasAcknowledged());
//                setVisible(false);
//            }
//        });
//
//        fourth.add(b);
//        fourth.add(btnCount);
//
//        add(fourth);
//
//        this.setTitle("Recipe Maker");
//        this.setSize(300, 300);
//        this.setResizable(false);
//        this.setVisible(true);
    }


    private class AddIngredientListener implements ActionListener {
        // ActionEvent handler - Called back upon button-click.
        @Override
        public void actionPerformed(ActionEvent evt) {
            //default icon, custom title
//            IngredientBuilder ib = new IngredientBuilder();
//            ib.setTitle("Recipe Maker");

            region = tfscd.getText();
            name = tfCount.getText();
            d.setSize(250, 150);
            d.setResizable(false);
            d.setVisible(true);
        }
    }
}
