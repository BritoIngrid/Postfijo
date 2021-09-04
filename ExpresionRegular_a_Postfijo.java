package lya2;
/*04-SEP-2021     idbb*/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;

public class ExpresionRegular_a_Postfijo {
    public static void main(String[] args) {
        //DECLARACIÓN DE PILAS Y ARRAYS
        String[] arrayOriginal;
        List<String> arrayPostfijo = new ArrayList<>();
        Stack <String> pilaAuxiliar = new Stack <> ();    
        
        //ENTRADA DE DATOS
        System.out.println("Escribe una expresión algebraica: ");
        Scanner entrada = new Scanner(System.in);
        String ExpresionEntrada = entrada.nextLine();
        
        //SEPARAMOS CADA CARACTER EN UN ARRAY
        arrayOriginal = ExpresionEntrada.split("");
        //System.out.println(Arrays.toString(arrayOriginal));
        
        //EMPEZAMOS A PASAR LA EXPRESION A POSTFIJO
        for(int i = 0; i <= arrayOriginal.length-1; i++){
            int caracter = Jerarquia(arrayOriginal[i]);
            switch(caracter){
                case 1:
                    pilaAuxiliar.push(arrayOriginal[i]);
                    break;
                case 2:
                    while(!pilaAuxiliar.peek().equals("(")){
                        arrayPostfijo.add(pilaAuxiliar.pop());//REVISAR
                    }
                    pilaAuxiliar.pop();
                    break;
                case 3:
                case 4:
                case 5:
                    if(!pilaAuxiliar.isEmpty()){
                        try{
                            while(Jerarquia(pilaAuxiliar.peek()) >= caracter) {
                                arrayPostfijo.add(pilaAuxiliar.pop());
                            }
                        }catch(Exception ex){ 
                            //System.out.println("Hubo un errorsillo"); //La Pila esta Vacia
                        }
                    }   
                    pilaAuxiliar.push(arrayOriginal[i]);
                    break; 
                default:
                    arrayPostfijo.add(arrayOriginal[i]);
            }
            //ME MUESTRA CADA MOVIMIENTO QUE SE VA REALIZANDO
            System.out.println("Postfijo: "+arrayPostfijo);
            System.out.println("Pila: "+pilaAuxiliar);
        }
        
        //Anexamos al arrayPostfijo los ultimos caracteres que quedaron en la pila
        while(!pilaAuxiliar.isEmpty()){
            arrayPostfijo.add(pilaAuxiliar.pop());
        }
        
        //Concatenar el arrayPostfijo
        String ExpresionSalida = "";
        for(int i = 0; i <= arrayPostfijo.size()-1; i++){
            ExpresionSalida +=arrayPostfijo.get(i);
        }
        
        //Imprimir el resultado
        System.out.println("Expresion Postfija: " + ExpresionSalida);
    }
    
    //Jerarquia de los operadores
  private static int Jerarquia(String caracter) {
    int bandera = 0;    
    switch (caracter) {
        case "(":
            bandera = 1;
            break;
        case ")":
            bandera = 2;
            break;
        case "+":
        case "-":
            bandera = 3;
            break;
        case "*":
        case "/":
            bandera = 4;
            break;
        case "^":
            bandera = 5;
            break;       
        default:
            break;
    }
    return bandera;
  }
}
