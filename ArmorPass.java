
package armorpass;


import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox;         ;
import javafx.stage.Stage;


public class ArmorPass extends Application {
    //private variables: user input and new password
    private TextField passwordTextField; 
    private TextField resultTextField; 

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Armor Pass"); 
        primaryStage.setHeight(790);
        primaryStage.setWidth(600);
        primaryStage.setResizable(false);
        ProgressBar pb = new ProgressBar(0); //creating progress bar
        pb.setVisible(false); 
        
        //labels for form
        Label promptPassword = new Label("Enter your current password: "); 
        Label resultPassword = new Label("Your new generated password: ");
        Label pbLabel = new Label("");
         
        //creates textfield for current and generated password 
        passwordTextField = new TextField(); 
        resultTextField = new TextField(); 
        resultTextField.setEditable(false); 
        
        //generate,strengthen and exit button for form
        Button generateBtn = new Button();
        Button strengthenBtn = new Button();
        Button closeBtn = new Button();
        
         // gives function to generate button 
        generateBtn.setText("Generate");
        generateBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //label and progress bar reset 
                pbLabel.setText(""); 
                pb.setProgress(0);
                String tempPassword = " "; 
                tempPassword = generatePass(tempPassword); // calls generatePass method
                resultTextField.setText(tempPassword);//sets temppassword to resutl textfield
                pb.setVisible(true);
                pbLabel.setText("Strong and mighty!"); 
                pbLabel.setStyle("-fx-text-fill: green; -fx-font: normal bold 20px 'serif';"); 
                pb.setProgress(0.99);     
            }
        });
        
       // gives function to strengthen button 
        strengthenBtn.setText("Strengthen");
        strengthenBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                pbLabel.setText(""); 
                pb.setProgress(0);
                String tempPassword = passwordTextField.getText(); 
                String strengthenedPassword = ""; 
                strengthenedPassword = strengthenPass(tempPassword); //calls strengthenPassword method
                resultTextField.setText(strengthenedPassword);
                
                //if-else-if statements to deteminine strength of password 
                if (strengthenedPassword.length() - tempPassword.length() >= 10)  
                {
                    pb.setVisible(true);
                    pbLabel.setText("Strong! Ready for battle!"); 
                    pbLabel.setStyle("-fx-text-fill: green; -fx-font: normal bold 20px 'serif';"); 
                     pb.setProgress(0.99);
                }
                
                else if (strengthenedPassword.length() - tempPassword.length()  < 10 && strengthenedPassword.length() - tempPassword.length() > 3 )
                {
                    pb.setVisible(true);
                    pbLabel.setText("Almost there!"); 
                    pbLabel.setStyle("-fx-text-fill: orange; -fx-font: normal bold 20px 'serif';"); 
                    pb.setProgress(0.75);
                }
                
                else if (strengthenedPassword.length() - tempPassword.length() <= 3)
                {
                    pb.setVisible(true);
                    pbLabel.setText("Still sharpening your sword"); 
                    pbLabel.setStyle("-fx-text-fill: red; -fx-font: normal bold 20px 'serif';"); 
                    pb.setProgress(0.60);
                }

            }
        });
        
        closeBtn.setText("Exit");
        closeBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               System.exit(0);  
            };
        });
        
       //graphic design of form 
        HBox hboxbtns = new HBox (15, generateBtn,strengthenBtn, closeBtn); 
        hboxbtns.setPadding(new Insets(20)); 
        hboxbtns.setAlignment(Pos.CENTER);
        
        VBox vboxInner = new VBox (15, promptPassword, passwordTextField, resultPassword, resultTextField, hboxbtns, pbLabel, pb); 
        vboxInner.setAlignment(Pos.CENTER);
        vboxInner.setPadding(new Insets(25,25,25,25));
        vboxInner.setStyle(
         "-fx-background-color: rgba(255, 255, 255, .95);" +
        "-fx-background-size: 1500 700;" + "-fx-background-position: center center;"+
        "-fx-border-color: black;\n" +   "-fx-border-width: 3;\n" +
        "-fx-border-style: solid;\n");
        
        VBox vboxOuter = new VBox (30,  vboxInner); 
        vboxOuter.setStyle("-fx-background-image: url('https://image.freepik.com/free-vector/knights-armor-badge_31492-58.jpg');" +
        
        "-fx-background-position: center center;" + "-fx-background-size: 900,300;");

        vboxOuter.setAlignment(Pos.CENTER);
        vboxOuter.setPadding(new Insets(50,50,-100,50));
   
        Scene scene = new Scene(vboxOuter);
        primaryStage.setTitle("ArmorPass");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    //generates a random password from passwordChars array and returns it.   
    public static String generatePass(String generatedPassword) 
      {
         generatedPassword= ""; 
         String current; 
         String[] passwordChars= new String[] {"1", "2","3", "4", "5", "6", "7", "8", "9", "0", "a", "b",
                                               "c", "d","e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                                               "o", "p","q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                                                "!", "@","#", "$", "&", "*" };  
         
         for (int i = 0; i <12; i++) 
         {
            Random rand = new Random(); 
            int randNum; 
            randNum = rand.nextInt(42); 
            current = passwordChars[randNum]; 
            generatedPassword += current; 
         }
         return generatedPassword; 
      } 

        //takes users password and strengthens it 
      public static String strengthenPass(String strongerPassword) 
      { 
          String current =  strongerPassword;
          Random randomNum = new Random(); 
          //array of numbers and symbols to strengthen password
          String[] symbolsNums= new String[] {  "1", "2","3", "4", "5", "6", "7", "8", "9", "0",
                                                   "!", "@","#", "$", "&", "*", "_", "%", "!", "@","#", "$", "&", "*", "_", "%"}; 
          String [] randomWord = new String[] //array of words to strengthen password
            { 
               "rainstorm", "honey", "giants", "drink", "property", "weight", "knowledge", "sleet", "cabbage", "riddle", "bite", "rose", "wire", "believe",
                "sheep", "mountain", "gate", "grade", "rifle", "fang", "dinosaurs", "hole", "grape", "stitch", "payment", "sleep", "transport", "decision",
                "boot", "pail", "moon", "fire", "control", "power", "table", "magic", "camera", "scale", "fowl", "run", "school", "rest", "heat", "yoke",
                "pin", "cakes", "bridge", "match", "sneeze", "meat", "fall", "purpose", "wash", "trade", "oranges", "door", "play", "distribution", "badge",
                "bird", "bushes", "linen", "salt", "waves", "walk", "health", "bath", "drop", "dime", "dolls", "peace", "face", "cherry", "writer", "room",
                "division", "recess", "steam", "shoe", "tank", "mine", "scene", "celery", "notebook", "farm", "committee", "wall", "digestion", "volcano",
                "support", "space", "trains", "pollution", "fog", "arithmetic", "belief", "wave", "thought", "bat", "verse", "way", "degree", "horses",
                "ladybug", "channel", "sisters", "care", "wheel", "pipe", "bear", "quiet", "page", "quartz", "night", "soap", "stone", "discussion",
                "reward", "credit", "stem", "rate", "jelly", "wound", "feeling", "angle", "appliance", "swing", "hospital", "doll", "plant", "winter",
                "locket", "trees", "force", "bulb", "tray", "push", "bit", "twist", "statement", "juice", "fact", "oil", "invention", "pot", "curtain",
                "stick", "side", "price", "position", "self", "end", "donkey", "cows", "knee", "eye", "mom", "lake", "behavior", "nest", "memory",
                "group", "example", "motion", "tomatoes", "tent", "smile", "sister", "woman", "tax", "money", "milk", "slip", "trouble", "scissors",
                "protest", "harbor", "guitar", "anger", "day", "zoo", "volleyball", "voyage", "hose", "wind", "snails", "field", "texture", "produce", 
                "hydrant", "laugh", "neck", "haircut", "friction", "gold", "stage", "fork", "chickens", "whistle", "ring", "harmony", "chance", "hook",
                "pan", "dock", "son", "apparel", "basket", "note", "van", "advertisement", "army", "ducks", "zephyr", "basin", "vegetable", "size",
                "partner", "bee", "hand", "teeth", "shape", "crime", "egg", "month", "line", "question", "secretary", "team", "tail", "bucket", 
                "representative", "uncle", "hammer", "grain", "calendar", "quicksand", "wish", "record", "thunder", "aftermath", "eggnog", "low", 
                "sticks", "fish", "agreement", "trip", "front", "crowd", "impulse", "dad", "coat", "country", "condition", "cemetery", "animal",
                "minute", "taste", "shake", "roof", "title", "yam", "thing", "quarter", "offer", "paper", "duck", "playground", "week", "bed",
                "cracker", "camp", "argument", "furniture", "limit", "north", "brake", "judge", "writing", "glass", "stranger", "mark", "history",
                "horse", "letters", "kittens", "smash", "hobbies", "rabbits", "roll", "plough", "afterthought", "silver", "boundary", "base",
                "seed", "eyes", "club", "twig", "actor", }; 
        
         
         /** The strengthenPass method has three options to strengthen the password. A random class and if -else-if decide what strength options is chosen. 
             * strengthenMethod triggered when "Strengthen" button is clicked      
             *  Option 1 is adding 3 symbols or numbers to password
             *  Option 2 is lengthening password with words
             *  Option 3 is doing both option 1 and option 2 
             *  @param user password that is given from user input and used in strength method
             *  @return returns the stronger password after it has passed through strengthening options 
             */
         
          int option; 
          option = randomNum.nextInt(3)+1 ; 
          
          //adds numbers and symbols at the end 
          if (option == 1 )        
          {         
            for (int i = 0; i <3; i++) 
            {
               Random rand = new Random(); 
               int randNum; 
               randNum = rand.nextInt(26); 
               current += symbolsNums[randNum];
            }    
          } 
          
          else if (option ==2 )
          {
           for (int j = 0; j <2; j++) 
            {
               Random rand = new Random(); 
               int randNum; 
               randNum = rand.nextInt(300); 
               current += randomWord[randNum];                
            }              
            }
          
          else if (option ==3)       
          {
               for (int j = 0; j <2; j++) 
            {
               //random word 
               Random rand = new Random(); 
               int randNum; 
               randNum = rand.nextInt(300); 
               current += randomWord[randNum]; 
               
               //random character
                Random rand2 = new Random(); 
               int randNum2; 
               randNum2 = rand2.nextInt(16); 
               current += symbolsNums[randNum2];
            }
          }
          
          else if ((option != 3 || option !=2) && option != 1)
          {
                current = "ERROR"; 
          }
         return current; 
        }
            
}

  