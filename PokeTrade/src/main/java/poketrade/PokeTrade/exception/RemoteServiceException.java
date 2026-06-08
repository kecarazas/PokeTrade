package poketrade.PokeTrade.exception;

public class RemoteServiceException extends RuntimeException {
    public RemoteServiceException(String mensaje){
        super(mensaje);
    }
}
