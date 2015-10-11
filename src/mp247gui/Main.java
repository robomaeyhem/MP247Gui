package mp247gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author The_Chef1337
 */
public class Main {

    public static GUI g;
    public static ChatBot chat;
    public static String VERSION = "0.2b";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        g = new GUI();
        boolean chatConnected = false;
        boolean fileExists = false;
        File config = new File("config.cfg");
        fileExists = config.exists();
        try {
            chatConnected = chat.isConnected();
        } catch (NullPointerException ex) {
            chatConnected = false;
        }
        final StringBuilder BOT_NAME = new StringBuilder("");
        final StringBuilder oAuth = new StringBuilder("");
        if (fileExists) {
            try {
                Scanner scan = new Scanner(config);
                boolean canContinue = false;
                String firstLine = scan.nextLine();
                if (firstLine.startsWith("[MP247GUI]")) {
                    canContinue = true;
                }
                while (scan.hasNextLine() && canContinue) {
                    String line = scan.nextLine();
                    if(line.startsWith("name=")){
                        BOT_NAME.append(line.split("name=",2)[1].split("\n",2)[0].split(" ",2)[0]);
                        continue;
                    }
                    if(line.startsWith("oauth=")){
                        oAuth.append(line.split("oauth=",2)[1].split("\n",2)[0].split(" ",2)[0]);
                    }
                }
                chat = new ChatBot(BOT_NAME.toString(), oAuth.toString());
                Thread.sleep(1000);
                chatConnected = chat.isConnected();
            } catch (Exception ex) {

            }
        }
        while (!chatConnected) {
            JDialog j = new JDialog();
            JLabel nameLabel = new JLabel("Name: ");
            JLabel oauthLabel = new JLabel("OAuth: ");
            JButton ok = new JButton("OK");
            final JTextField nameField = new JTextField("", 36);
            final JTextField oauthField = new JTextField("", 36);
            ok.addActionListener((ActionEvent ee) -> {
                BOT_NAME.append(nameField.getText());
                oAuth.append(oauthField.getText());
                j.setVisible(false);
                j.dispose();
            });
            JButton cancel = new JButton("Close");
            cancel.addActionListener((ActionEvent e) -> {
                System.exit(0);
            });
            JLabel info = new JLabel("You can get your OAuth from here: ");
            JTextField infoField = new JTextField("https://twitchapps.com/tmi/  ");
            infoField.setEditable(false);
            JLabel info2 = new JLabel("(Copy the ENTIRE OAuth, including the oauth: part!!)");
            JPanel bottom = new JPanel();
            JPanel main = new JPanel();
            j.setTitle("Information Needed");
            j.setLayout(new BorderLayout());
            bottom.setLayout(new FlowLayout());
            main.setLayout(new FlowLayout());
            main.add(nameLabel);
            main.add(nameField);
            main.add(oauthLabel);
            main.add(oauthField);
            main.add(info);
            main.add(infoField);
            main.add(info2);
            bottom.add(ok);
            j.add(main, BorderLayout.CENTER);
            j.add(bottom, BorderLayout.SOUTH);
            j.setSize(500, 170);
            j.setResizable(false);
            j.setModal(true);
            j.setVisible(true);
            j.getRootPane().setDefaultButton(ok);
            chat = new ChatBot(BOT_NAME.toString(), oAuth.toString());
            try {
                Thread.sleep(1000);
                chatConnected = chat.isConnected();
            } catch (NullPointerException | InterruptedException ex) {
                chatConnected = false;
            }
        }
        if (chatConnected) {
            if (fileExists) {
                try (PrintWriter writer = new PrintWriter(config)) {
                    writer.print("");
                } catch (Exception ex) {

                }
            }
            try (PrintWriter pw = new PrintWriter(config)) {
                pw.println("[MP247GUI]");
                pw.println("name=" + BOT_NAME.toString());
                pw.println("oauth=" + oAuth.toString());
                pw.println("version=" + VERSION);
            } catch (Exception ex) {
            }
        }

    }
}
