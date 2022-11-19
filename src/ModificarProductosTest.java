import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModificarProductosTest {

    @Test
    void testLeerProducto() {
        ModificarProductos producto = new ModificarProductos("12");
        assertEquals(producto.txt_Precio.getText(), "21");
    }
}
