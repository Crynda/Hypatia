package gui.status;

public final class GestorSesion {

    private static final EstadoSesion sesion = new EstadoSesion();

    public static EstadoSesion getEstado() {
        return sesion;
    }
}
