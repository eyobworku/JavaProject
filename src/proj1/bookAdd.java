package proj1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class bookAdd {
    private boolean imageImported = false;
    private FileChooser fileChooser;
    private File filePath;
    private String uploaded_image;
    private Image image;
    
    Queries queries = new Queries();
    @FXML
    private TextField authorNameTF;

    @FXML
    private TextField bookNameTF;

    @FXML
    private TextField generTF;

    @FXML
    private ImageView importBookIV;

    @FXML
    private TextField quantityTF;

    @FXML
    void addBookClicked(ActionEvent event) {
        if(imageImported){
            String bookName = bookNameTF.getText();
            String author = authorNameTF.getText();
            String gener = generTF.getText();
            int quantity = Integer.parseInt(quantityTF.getText());
            if(bookName != "" && author != "" && gener != "" && quantity != 0){
                System.out.println("image:"+uploaded_image);
                queries.createBook(new Book(0,bookName,author,gener,quantity,uploaded_image));
                displayAlert(Alert.AlertType.INFORMATION, "Book Added", "Book added successfully");
                bookNameTF.clear();
                authorNameTF.clear();
                quantityTF.clear();
                generTF.clear();
                importBookIV.setImage(null);
            }else{
                System.out.println("Enter data");
            }
        }else{
            System.out.println("Import image");
        }
    }
    
    @FXML
    void importImageClicked(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open image");

        List<String> permited = Arrays.asList("jpg", "jpeg", "png");
        String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        File userDirectory = new File(userDirectoryString);

        if(!userDirectory.canRead()){
            userDirectory = new File("c:/");}

        fileChooser.setInitialDirectory(userDirectory);//open file selection dialoge

        this.filePath = fileChooser.showOpenDialog(stage);
        String stringFilePath = filePath.toString();
        String[] div = stringFilePath.split("\\\\");
        String fileName = div[div.length - 1].toLowerCase();//get file name

        String[] div1 = fileName.split("\\.");
        String file_ext = div1[div1.length - 1].toLowerCase();

        String classPath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String[] div2 = classPath.split("\\/");
        String newClassPath = "/";
        for(int i = 2;i < div2.length-1;i++){
            newClassPath+=(div2[i]+"/");
        }
        newClassPath += "uploads/";
        
        File uploadFolder = new File(newClassPath);
        if (!uploadFolder.exists()) {
            if (uploadFolder.mkdir()) {
                System.out.println("Folder created successfully");
            } else {
                System.out.println("Failed to create folder");
            }
        }
        String unique_image = generateUniqueImageName() + "." + file_ext;

        if (!permited.contains(file_ext)) {
            System.out.println("Only " + String.join(", ", permited) + " are allowed");
        } else {
            uploaded_image = uploadFolder + "\\" + unique_image;
            Files.copy(filePath.toPath(), new File(uploaded_image).toPath());
            //Files.deleteIfExists(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(uploaded_image));
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            importBookIV.setImage(image);
            imageImported=true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    }

    private void displayAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static String generateUniqueImageName() throws NoSuchAlgorithmException {
        long time = System.currentTimeMillis();
        String text = String.valueOf(time);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(text.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();
        return hash.substring(0, 10);
    }

}

