import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MostrarEventosTest {

    @Test
    void testLeerEvento() {
        MostrarEventos evento = new MostrarEventos("2");
        assertEquals(evento.txt_Descripcion.getText() , "Carrera en la Concha");
    }

}
