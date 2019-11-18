
package ExcelUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.PersonalDao;
import entities.CatPersonal;
public class DataProvider {
   public static PersonalDao perdao = new PersonalDao();
  
    
   //creando las cabeceras de la tabla excel
    public static List<String> getTableHeaders() {
        List<String> tableHeader = new ArrayList<String>();
        //TITULOS DE LAS COLUMNAS
        tableHeader.add("Cod.Prog");
        tableHeader.add("Cod.Jaryva");
        tableHeader.add("1er.NOMBRE");
        tableHeader.add("2do.NOMBRE");
        tableHeader.add("1er.APELLIDO");
        tableHeader.add("2do.APELLIDO");
        tableHeader.add("DIRECCION");
        tableHeader.add("GENERO");
        tableHeader.add("E.CIVIL");
        tableHeader.add("TELEFONO1");
        tableHeader.add("TELEFONO2");
        tableHeader.add("TELEFONO3");
        tableHeader.add("F.INGRESO");
        tableHeader.add("F.NACIMNTO");
        tableHeader.add("DEPTO");
        tableHeader.add("DESCRIPCION");
        tableHeader.add("CARGO");
        tableHeader.add("DESCRIPCION");
        tableHeader.add("DUI");
        tableHeader.add("NOMBRE S/DUI");
        tableHeader.add("N.I.T");
        tableHeader.add("NOMBRE S/N.I.T");
        tableHeader.add("ISSS");
        tableHeader.add("NOMBRE S/ISSS");
        tableHeader.add("NUP");
        tableHeader.add("AFP");
        tableHeader.add("C.BANCARIA");
        tableHeader.add("BANCO");
        tableHeader.add("OBSERBACION1");
        tableHeader.add("OBSERBACION2");
        tableHeader.add("OBSERBACION3");
     

        return tableHeader;
    }
   
     
    //Retorna la lista de todo el personal existente en la base de datos
   public  static List<List<String>> getTableContent(int numberOfRows) {
        try {
            if (numberOfRows <= 0) {
                throw new IllegalArgumentException("The number of rows must be a positive integer");
            }
               
                List<CatPersonal> lista = perdao.listAll();
               if(lista.size()>0){
                   System.out.println("no se encontro ningun dato");
               }
            List<List<String>> tableContent = new ArrayList<List<String>>();

            List<String> row = null;
            int i = 0;
            for (int j = 0; j < lista.size(); j++) {
                tableContent.add(row = new ArrayList<String>());
                 CatPersonal p = lista.get(i);

                 
                 //se tendra que dividir el nombre en dos nombres
                 String firstname=p.getNombres();
                 String secondname=p.getNombres();
                 
                 //corregir el apellido y dividirlos en dos
                 String apellido1=p.getApellidos();
                 String apellido2=p.getApellidos();
                 
                  //modificando simbologia de  genero de acuerdo al genero del objeto
                 String Genero;
                 if(p.getGenero().equalsIgnoreCase("Masculino")){
                     Genero="M";
                 }else{
                     Genero="F";
                 }
                 
                 //modificando simbologia de estado civil de acuerdo al estado civil
                 String EstadoCivil;
                 if(p.getEstadoCivil().equalsIgnoreCase("Soltero(a)")){
                     EstadoCivil="S";
                 }else if(p.getEstadoCivil().equalsIgnoreCase("Casado(a)")){
                      EstadoCivil="C";
                 }else if(p.getEstadoCivil().equalsIgnoreCase("viudo(a)")){
                      EstadoCivil="V";
                 }else if(p.getEstadoCivil().equalsIgnoreCase("Divorciado(a)")){
                      EstadoCivil="D";
                 }else if(p.getEstadoCivil().equalsIgnoreCase("Acompañado(a)")){
                     EstadoCivil="A";
                 }else{
                     EstadoCivil="";
                 }
         
       
                  //retornand diferentes formatos para mostrar el nombre completo         
                 String NsegunDui=p.getNombres()+ " "+p.getApellidos();
                 String NsegunNit=p.getApellidos()+ " "+p.getNombres();
                 String NsegunIss=p.getApellidos()+ " "+p.getNombres();
                 
                 //agregando atributos a la fila
                 row.add(p.getProgramacion().getCodProgramacion().toString());
                 row.add(p.getCodJaryva());
                 
                 //*****
                 row.add(firstname);
                  row.add(secondname);
                 //**************
                 row.add(apellido1);
                 row.add(apellido2);
                 
                 row.add(p.getDireccion());
                 row.add(Genero);
                 row.add(EstadoCivil);
                 row.add(p.getTelefono1());
                 row.add(p.getTelefono2());
                 row.add(" ");
                 row.add(p.getFechaIngreso().toString());
                 row.add(p.getFechaNacimiento().toString());
                 row.add(p.getDepartamentos().getCodDepartamento().toString());
                 row.add(p.getDepartamentos().getDescripcion());
                 
                 row.add(p.getCargos().getCodCargo().toString());
                 row.add(p.getCargos().getDescripcion());
                 row.add(p.getDui());
                 row.add(NsegunDui);
                 row.add(p.getNit());
                 row.add(NsegunNit);
                 row.add(p.getIsss());
                 row.add(NsegunIss);
                 row.add(p.getNup());
                 row.add(p.getAfp().getDescripcion());
                 row.add(p.getCtaBancaria());
                 row.add(p.getBancos().getDescripcion());
                 row.add(p.getObserbaciones());
                 
                 //obserbaciones vacias
                 row.add(" ");
                 row.add(" ");
                 
                
                 i++;
            }

            
            
           
            return tableContent;
        } catch (Exception ex) {
            Logger.getLogger(DataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
