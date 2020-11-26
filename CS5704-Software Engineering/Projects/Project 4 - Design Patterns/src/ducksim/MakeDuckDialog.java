package ducksim;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MakeDuckDialog extends JDialog {

    // Fields

    // Duck panel
    private final JPanel duckPanel = new JPanel();
    private final JLabel duckLabel = new JLabel("Duck");
    private final String[] duckStrings = { "Mallard", "Redhead", "Rubber",
            "Decoy", "Goose" };
    private final JComboBox duckOptions = new JComboBox(duckStrings);

    // Bling panel
    JPanel blingPanel = new JPanel();
    private final JLabel starTitleLbl = new JLabel("Star");
    private final JLabel starCountLbl = new JLabel("0");
    private final JButton starPlusBtn = new JButton("+");
    private final JButton starMinusBtn = new JButton("-");

    private final JLabel moonTitleLbl = new JLabel("Moon");
    private final JLabel moonCountLbl = new JLabel("0");
    private final JButton moonPlusBtn = new JButton("+");
    private final JButton moonMinusBtn = new JButton("-");

    private final JLabel crossTitleLbl = new JLabel("Cross");
    private final JLabel crossCountLbl = new JLabel("0");
    private final JButton crossPlusBtn = new JButton("+");
    private final JButton crossMinusBtn = new JButton("-");

    // Button panel
    private final JPanel buttonPanel = new JPanel();
    private final JButton okayButton = new JButton("Okay");
    private final JButton cancelButton = new JButton("Cancel");

    // Stored Data
    private String duckType = "Mallard";
    private int crossCount = 0;
    private int starCount = 0;
    private int moonCount = 0;

    // Constructor
    public MakeDuckDialog(DuckSimModel model, DuckSimView view) {

        this.getContentPane().setLayout(
                new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // add duck panel
        duckPanel.add(duckLabel);
        duckOptions.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            duckType = (String) cb.getSelectedItem();
        });
        duckPanel.add(duckOptions);
        this.add(duckPanel);

        // add bling panel
        blingPanel.setLayout(new GridLayout(3, 4));
        blingPanel.add(starTitleLbl);
        blingPanel.add(starCountLbl);
        blingPanel.add(starPlusBtn);
        blingPanel.add(starMinusBtn);
        blingPanel.add(moonTitleLbl);
        blingPanel.add(moonCountLbl);
        blingPanel.add(moonPlusBtn);
        blingPanel.add(moonMinusBtn);
        blingPanel.add(crossTitleLbl);
        blingPanel.add(crossCountLbl);
        blingPanel.add(crossPlusBtn);
        blingPanel.add(crossMinusBtn);
        starPlusBtn.addActionListener(e -> {
            if (isBlingSumLessThanThree())
            {
                starCount++;
                starCountLbl.setText(Integer.toString(starCount));
            }
        });
        starMinusBtn.addActionListener(e -> {
            if (isBlingCountGreaterThan0(starCount))
            {
                starCount--;
                starCountLbl.setText(Integer.toString(starCount));
            }
        });
        moonPlusBtn.addActionListener(e -> {
            if (isBlingSumLessThanThree())
            {
                moonCount++;
                moonCountLbl.setText(Integer.toString(moonCount));
            }
        });
        moonMinusBtn.addActionListener(e -> {
            if (isBlingCountGreaterThan0(moonCount))
            {
                moonCount--;
                moonCountLbl.setText(Integer.toString(moonCount));
            }
        });
        crossPlusBtn.addActionListener(e -> {
            if (isBlingSumLessThanThree())
            {
                crossCount++;
                crossCountLbl.setText(Integer.toString(crossCount));
            }
        });
        crossMinusBtn.addActionListener(e -> {
            if (isBlingCountGreaterThan0(crossCount))
            {
                crossCount--;
                crossCountLbl.setText(Integer.toString(crossCount));
            }
        });
        this.add(blingPanel);

        // add button panel
        cancelButton.addActionListener(e -> {
            this.dispose();
        });
        buttonPanel.add(cancelButton);
        okayButton.addActionListener(e -> {
            // makeDuckDialog
            Duck duck = DuckFactory.getInstance().createDuck(duckType,
                    starCount, moonCount, crossCount);
            if (duck != null)
            {
                model.addNewDuck(duck);
            }

            view.repaint();
            this.dispose();
        });
        buttonPanel.add(okayButton);
        this.add(buttonPanel);
    }

    // Return true if bling sum < 3
    private boolean isBlingSumLessThanThree() {
        return (starCount + moonCount + crossCount) < 3;
    }

    // Return true if bling count > 0
    private boolean isBlingCountGreaterThan0(int blingCount) {
        return blingCount > 0;
    }

    // Public Methods
    public String getDuckType() {
        return duckType;
    }
}
