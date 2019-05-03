import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MTax {
    
    public MTax(){
        
    }
    
    public static List<String> validate(List<X_Tax> xTaxList) {
        
        List<String> errorList = new ArrayList<>();
        
//      List<String> taxCategoryList = MInfoTaxCategory.getTaxCategoryStringList();
        // Eliminar la condición de is null debido a que si es smayor a 0 evidentemente no es nula
        
        if(xTaxList.size() > 0) {
            List<String> validIds = new ArrayList<>();
            int cont = 0;
            
            for (X_Tax tax : xTaxList) {
            
                if(tax.getId() != null){
            
                    validIds.add(tax.getId().toString());
            
                }
//              if(tax.getAmount() == null) {
//                   errorList.add("El importe es obligatorio");
//              }

                if(tax.getTax() == null) {
                    errorList.add("El impuesto es obligatorio");
                }
//              else if(!taxCategoryList.contains(tax.getTax())) {
//                    errorList.add("El impuesto no es un dato valido");
//              }

//              if(tax.isLocal()){
//                    if(tax.isTrasladado() && tax.getTaxAmount() == null ) {
//                        errorList.add("El importe es obligatorio");
//                    }
//              } 
//              else{
//                  if(tax.getTaxAmount() == null ) {
//                       errorList.add("El importe es obligatorio");
//                   }
//              }
    
                if(!tax.isLocal()){
                    cont++;
                }
            }
            if(cont<=0){
                errorList.add("Debe de incluir al menos una tasa no local");
            }
            if(validIds.size() > 0){

                /*No se vacía el arreglo validIds en el nuevo arreglo xt debido 
                a que la conidcion del if verifica que si los arreglos son del mismo 
                tamaño y se ejecuta la instruccion, eso no es necesario ya que
                se tiene una if con la condición de que si el arreglo validIds es mayor a 0
                y si validIds es mayor a 0 quiere decir que xt tambien lo seria y se ejecuta la instruccion
                y es mejor verificar si validIds es mayor a 0 */
                    
                   /* List<X_Tax> xt = TaxsByListId(validIds, false);
                    if(xt.size() != validIds.size()){
                        errorList.add("Existen datos no guardados previamente");
                    }else{*/
                        HashMap<String, X_Tax> map_taxs = new HashMap<String, X_Tax>();

                        /*Se eliminó el arreglo xt ya no se usará en el for se reemplazará con 
                        el arreglo xTaxList y se buscara hacer un ciclo con el for usando la variable Tax
                        y se igualaría con la variable tax que ya tenía los datos del xTaxList*/


                        for(X_Tax tax: /*xt*/xTaxList){
                            map_taxs.put(tax.getId().toString(), tax);
                        }
                        for(int i = 0; i < xTaxList.size(); i++){
                            if(xTaxList.get(i).getId() != null){
                                xTaxList.get(i).setCreated(
                                        map_taxs.get(xTaxList.get(i).getId().toString())
                                                .getCreated());
                            }
                        }
                    //}      
            }else{
                errorList.add("Existen datos no guardados previamente");
            }
        }
//        else {
//            errorList.add("El documento no tiene tasas");
//        }
        
        return errorList;
    }
    
}
