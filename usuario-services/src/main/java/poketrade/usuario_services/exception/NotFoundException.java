package poketrade.usuario_services.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String mensaje){
        super(mensaje);
    }
}
