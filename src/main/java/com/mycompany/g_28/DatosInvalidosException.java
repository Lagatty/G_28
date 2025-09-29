/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.g_28;

/**
 *
 * @author franc
 */
public class DatosInvalidosException extends ReservaException {
    private String campoInvalido;
    private String valorIngresado;
    private String formatoEsperado;
    
    public DatosInvalidosException(String mensaje) {
        super(mensaje);
    }
    
    public DatosInvalidosException(String mensaje, String campoInvalido, String valorIngresado) {
        super(mensaje);
        this.campoInvalido = campoInvalido;
        this.valorIngresado = valorIngresado;
    }
    
    public DatosInvalidosException(String mensaje, String campoInvalido, String valorIngresado, String formatoEsperado) {
        super(mensaje);
        this.campoInvalido = campoInvalido;
        this.valorIngresado = valorIngresado;
        this.formatoEsperado = formatoEsperado;
    }
    
    public String getCampoInvalido() {
        return campoInvalido;
    }
    
    public String getValorIngresado() {
        return valorIngresado;
    }
    
    public String getFormatoEsperado() {
        return formatoEsperado;
    }
    
    @Override
    public String getMessage() {
        StringBuilder mensaje = new StringBuilder(super.getMessage());
        
        if (campoInvalido != null) {
            mensaje.append("\nCampo inválido: ").append(campoInvalido);
        }
        
        if (valorIngresado != null) {
            mensaje.append("\nValor ingresado: ").append(valorIngresado);
        }
        
        if (formatoEsperado != null) {
            mensaje.append("\nFormato esperado: ").append(formatoEsperado);
        }
        
        return mensaje.toString();
    }
}
