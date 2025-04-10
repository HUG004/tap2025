package com.example.tap2025.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EmpleadoDAO {
    private int id_empleado;
    private String nombre;
    private String apellido;
    private String curp;
    private String rfc;
    private String nss;
    private BigDecimal sueldo;
    private String puesto;
    private String tel_empleado;
    private String horario;
    private LocalDate fecha_ingreso;

    public int getId_empleado(){return id_empleado;    }
    public void setId_empleado(int id_empleado){this.id_empleado = id_empleado;}
    public String getNombre(){return nombre;}
    public void  setNombre(String nombre){this.nombre = nombre;}

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public BigDecimal getSueldo() {return sueldo;    }

    public void setSueldo(BigDecimal sueldo) {this.sueldo = sueldo;}

    public String getPuesto() {return puesto;}

    public void setPuesto(String puesto){this.puesto = puesto;}

    public String getTel_empleado(){return tel_empleado ;}

    public void setTel_empleado(String tel_empleado){this.tel_empleado = tel_empleado;}

    public String getHorario() {return horario;}

    public void setHorario(String horario){this.horario = horario;}

    public LocalDate getFecha_ingreso(){return fecha_ingreso;}

    public void setFecha_ingreso(LocalDate fecha_ingreso) {this.fecha_ingreso = fecha_ingreso;}

    public void INSERT(){
        String query = "INSERT INTO empleado(nombre,apellido,curp, rfc, nss, sueldo, puesto, tel_empleado, horario)"
                + "values('"+nombre+"','"+apellido+"', '"+curp+"', '"+rfc+"', '"+nss+"', '"+sueldo+"','"+puesto+"','"+tel_empleado+"','"+horario+"') ";
        try{
            Statement stmt =  conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE empleado SET nombre = '"+nombre+"', apellido = '"+apellido+"', curp = '"+curp+"'," +
                " rfc = '"+rfc+"', nss = '"+nss+"', sueldo = '"+sueldo+"', puesto = '"+puesto+"', tel_empleado = '"+tel_empleado+"', horario = '"+horario+"' WHERE id_empleado = "+id_empleado;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void DELETE(){
        String query = "DELETE FROM empleado WHERE id_empleado = " +id_empleado;
        try{
            Statement stmt = conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public ObservableList<EmpleadoDAO> SELECT(){
        String query = "SELECT * FROM empleado";
        ObservableList<EmpleadoDAO> listaE = FXCollections.observableArrayList();
        EmpleadoDAO objE;
        try{
            Statement stmt = conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objE = new EmpleadoDAO();
                objE.setId_empleado(res.getInt("id_empleado"));
                objE.setNombre(res.getString("nombre"));
                objE.setApellido(res.getString("apellido"));
                objE.setCurp(res.getString("curp"));
                objE.setRfc(res.getString("rfc"));
                objE.setNss(res.getString("nss"));
                objE.setSueldo(res.getBigDecimal("sueldo"));
                objE.setPuesto(res.getString("puesto"));
                objE.setTel_empleado(res.getString("tel_empleado"));
                objE.setHorario(res.getString("horario"));
                objE.setFecha_ingreso(res.getDate("fecha_ingreso") != null ? res.getDate("fecha_ingreso").toLocalDate() : null);
                listaE.add(objE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaE;
    }
}
