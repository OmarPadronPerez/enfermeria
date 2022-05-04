package auxiliar;

import java.text.DecimalFormat;

public class auxiliares {
    
    public static float calcularIMC(float talla, float peso){
        DecimalFormat df = new DecimalFormat("#.00");
        String imc=df.format((float) (peso/Math.pow(talla,2)));
        return Float.valueOf(imc);
    }
    
}
