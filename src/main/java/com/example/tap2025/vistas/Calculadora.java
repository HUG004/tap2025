package com.example.tap2025.vistas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Calculadora extends Stage{

    private Scene escena;
    private TextField txtPantalla;
    private VBox vBox;
    private GridPane gdpTeclado;
    private Button[][] arBtn;
    private String strTeclas[] = {"7", "8", "9", "*", "4", "5", "6", "/", "1", "2", "3", "+", "0", ".", "=", "-"};
    private double num1 = 0; //guarda el primer valor
    private String operador =""; //guarda el operador
    private boolean start = true;
    private Button btnClean;

    public void CrearUI(){
        CrearTeclado();
        txtPantalla = new TextField("0");
        txtPantalla.setEditable(false);
        txtPantalla.setPrefSize(70,50);
        txtPantalla.setAlignment(Pos.BASELINE_RIGHT);
        btnClean = new Button("Clear");
        btnClean.setId("front button");
        btnClean.setOnAction(actionEvent -> clearPantalla()); //limpia la pantalla
        vBox = new VBox(txtPantalla, gdpTeclado,btnClean);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        escena = new Scene(vBox, 230,230);
        escena.getStylesheets().add(getClass().getResource("/styles/calculadora.CSS").toString());

    }

    public void CrearTeclado(){
        arBtn = new Button[4][4];
        gdpTeclado = new GridPane();
        gdpTeclado.setHgap(5);
        gdpTeclado.setVgap(5);
        int pos = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arBtn[i][j] = new Button(strTeclas[pos]); //instancia de los objetos en el arreglo
                if(strTeclas[pos].equals("*") || strTeclas[pos].equals("/")
                        || strTeclas[pos].equals("+") || strTeclas[pos].equals("-") ||strTeclas[pos].equals("=")){
                    arBtn[i][j].setId("fontButton");
                    arBtn[i][j].setStyle("-fx-background-color: orange");
                }
                int finalpos = pos;
                arBtn[i][j].setOnAction(actionEvent -> detectarTecla(strTeclas[finalpos]));
                arBtn[i][j].setPrefSize(50, 50);
                gdpTeclado.add(arBtn[i][j],j,i);
                pos++;
            }
        }
    }

    private void detectarTecla(String strTecla){
        //si en la pantalla muestra un error hay que reiniciar antes de cualquier operacion
        if(txtPantalla.getText().equals("Error")){
            clearPantalla(); //limpia la pantalla si hay error
        }
        if (strTecla.matches("[0-9]")) { // Si es un número
            if(start){
                txtPantalla.clear(); //limpia la pantalla si es el inicio de una nueva entrada
                start= false; //indica que se a comenzado a escribir un numero
            }
            txtPantalla.appendText(strTecla); // añade el numero a la pantalla
        } else if(strTecla.equals(".")){ //si es un punto decimal
            if(start){
                txtPantalla.setText("0."); //si al inicio se preciona el punto
                start= false;
            }else if(!txtPantalla.getText().contains(".")){ //solo añade el punto si no existe uno ya
                txtPantalla.appendText(".");
            }
        }else if(strTecla.matches("[\\+\\-\\*/]")){ //si es un operador
            if (!operador.isEmpty() && !start) { // Realiza la operación intermedia si ya hay un operador
                double num2 = Double.parseDouble(txtPantalla.getText());
                num1 = calcular(num1, num2, operador); // Realiza la operación intermedia
                txtPantalla.setText(String.valueOf(num1)); // Muestra el resultado intermedio
            } else {
                num1 = Double.parseDouble(txtPantalla.getText()); // Convierte el texto en la pantalla a un número y lo guarda en `num1`
            }
            operador = strTecla; // Almacena el operador que se presionó
            start = true; // Indica que se espera la entrada del segundo número
        }else if(strTecla.equals("=")){ //si es =
            if (!operador.isEmpty()) { // Solo realiza la operación si hay un operador definido
                double num2 = Double.parseDouble(txtPantalla.getText()); // Convierte el texto en la pantalla al segundo número (`num2`)
                double resultado = calcular(num1, num2, operador); // Llama al metodo para calcular el obtener resultado
                if (Double.isNaN(resultado)) { // Si el resultado es NaN, muestra un error
                    txtPantalla.setText("Error");
                } else {
                    txtPantalla.setText(String.valueOf(resultado)); // Muestra el resultado en la pantalla
                }
                start = true; // Prepara para una nueva operación
                operador = ""; // Resetea el operador después de la operación
            }
        }
    }
    public Calculadora(){
        CrearUI();
        this.setScene(escena);
        this.setTitle("Calculadora");
        this.show();

    }
    private void clearPantalla(){
        txtPantalla.setText("0"); //reicinia la pantalla a 0
        start = true; // indica que esta listo para la nueva entrada
        operador = ""; // limpia el operador indicado
        num1 = 0;
    }
    private double calcular(double num1, double num2, String operador){
        switch (operador){
            case "+":
                return num1 + num2; //suma
            case "-":
                return num1 - num2; //resta
            case "*":
                return num1 * num2; // Multiplicación
            case "/":
                if (num2 != 0) {
                    return num1 / num2; // División (si `num2` no es 0)
                } else {
                    return Double.NaN; // Retorna NaN para indicar un error en la división por cero
                }
            default:
                return Double.NaN; // Retorna NaN si el operador es desconocido
        }
    }
}

