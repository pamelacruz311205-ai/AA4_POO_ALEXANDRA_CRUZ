import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cuenta {
    
    private String codCuenta;
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    private static int cantCuentasCreadas = 0;

    public Cuenta(double pSaldo) {
        this.saldo = pSaldo;
        this.nombreCuentaHabiente =  "";
        
        this.cantDepositosRealizados = 0;
        this.cantRetirosExitososRealizados = 0;
        this.fechaCreacion = determinarFechaCreacion();
        
        codCuenta = "cta-" + cantCuentasCreadas;
        cantCuentasCreadas += 1;
        

    }

    public Cuenta(String nombreCuentaHabiente, double pSaldo) {
        this(pSaldo);
        this.nombreCuentaHabiente= nombreCuentaHabiente;

    }
    
    private String determinarFechaCreacion() {
        Date fecha = new Date(System.currentTimeMillis());
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return formatoFecha.format(fecha);
    }
    
    public void setNombreCuentaHabiente(String pNombreCuentaHabiente) {
        this.nombreCuentaHabiente = pNombreCuentaHabiente;
    }
    
    public String getCodCuenta() {
        return codCuenta;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public double depositar(double monto) {
        if (monto>0) {
            this.saldo += monto;
            this.cantDepositosRealizados++;
            return this.saldo;   
        } else {
            return this.saldo;
        }
    }
    
    public double retirar (double monto) {
        if (validarRetiro(monto)) {
            this.saldo -= monto;
            this.cantRetirosExitososRealizados++;
            return this.saldo;   
        } else
            return this.saldo;
    }
    
    public boolean validarRetiro(double monto) {
        if (monto > 0) {
            if (monto <= saldo) {
            return true;
            } else 
                return false;
        }else
            return false;
    }
    
    public int getCantCuentasCreadas() {
        return cantCuentasCreadas;
    }
    
    public String getNombreCuentaHabiente() {
        return nombreCuentaHabiente;
    }
    
    public String toString() {
        String msg = "";
    
        msg += "========== Información de la cuenta ==========\n";
        msg += "Código: " + codCuenta + "\n";
        msg += "Nombre: " + nombreCuentaHabiente + "\n";
        msg += "Saldo: " + saldo + "\n";
        msg += "Cantidad depositos realizados: " + cantDepositosRealizados + "\n";
        msg += "Cantidad retiros exitosos realizados: " + cantRetirosExitososRealizados + "\n";
        msg += "=====================================\n\n";
        return msg;
    }  
}
