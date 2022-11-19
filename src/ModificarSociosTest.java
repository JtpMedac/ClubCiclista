import static org.junit.Assert.*;

import org.junit.Test;

public class ModificarSociosTest {

    @Test
    public void testLeerSocio() {
        ModificarSocios socios = new ModificarSocios("77491310D");
        assertEquals(socios.txt_Nombre.getText(), "Jaime");
    }
}
