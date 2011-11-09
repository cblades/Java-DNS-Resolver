import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BoxLayout;

public class DNSResolverWindow extends JFrame {
    /**
     * A button used to begin resolving a url
     */
    private JButton goButton;

    /**
     * A field containing the url to resolve.
     */
    private JTextField urlField;

    /**
     * Label for urlField
     */
    private JLabel urlLabel;

    /**
     * Checkbox to indicate if a recursive lookup is desired.
     */
    private JCheckBox recursiveCheck;

    /**
     * ActionListener for urlField that will enable and disable
     * the go button depending on wether the entered text is in the
     * form of a url.
     */
    private class urlFieldListener implements DocumentListener {
        
        /**
         * Enables or disables goButton based on wether the text in
         * urlField is a valid url.
         */
        private void setState() {
            /*
            if (urlField.getText().matches(
                 "[A-z0-9]+\\.[A-z0-9]+\\.[A-z0-9]+(\\.[A-z0-9]+)*")) {
                goButton.setEnabled(true);
            } else {
                goButton.setEnabled(false);
            }
            */
            goButton.setEnabled(true);
        }

        /**
         * Invoked when text is added into urlField
         *
         * @param e the event that generated this signal
         */
        public void insertUpdate(DocumentEvent e) {
            setState();
        }

        /**
         * Invoked when text is removed from urlField
         *
         * @param e the event that generated this signal
         */
        public void removeUpdate(DocumentEvent e) {
            setState();
        }

        /**
         * Invoked when text is changed in urlField
         *
         * @param e the event that generated this signal
         */
        public void changedUpdate(DocumentEvent e) {
            setState();
        }
    }

    private class GoButtonListener implements ActionListener {
        /**
         * Called when goButton is pressed.
         *
         * @param e the event that cause this call
         */
        public void actionPerformed(ActionEvent e) {
            boolean recursion = recursiveCheck.isSelected();
            DNSResolver resolver = new DNSResolver(urlField.getText(),
                                                   recursion);
        }
    }

    /**
     * Constructs a new DNSResolverWindow with the given title.
     *
     * @param title the title of the window
     */
    public DNSResolverWindow(String title) {
        super(title);
        
        // container will eventually be added to this, used only because
        // you can't pass a JFrame as a target to BoxLayout
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        //
        // construct the upper part of the window
        urlLabel = new JLabel("URL:");
        urlField = new JTextField(30);
        urlField.getDocument().addDocumentListener(new urlFieldListener());
        goButton = new JButton("Resolve");
        goButton.setEnabled(false);
        goButton.addActionListener(new GoButtonListener());
         
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.LINE_AXIS));
        upperPanel.add(urlLabel);
        upperPanel.add(urlField);
        upperPanel.add(goButton);

        container.add(upperPanel);
        
        //
        //add bottom part of window
        //
        recursiveCheck = new JCheckBox("Recursive Desired");
        
        container.add(recursiveCheck);
        
        // add container
        this.add(container);

        // misc. initialization
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        BoxLayout layout = (BoxLayout)container.getLayout();
        this.setSize(
                (int)layout.preferredLayoutSize(container).getWidth(),
                  74);
        this.setResizable(false);
        this.setVisible(true);
    }
}
