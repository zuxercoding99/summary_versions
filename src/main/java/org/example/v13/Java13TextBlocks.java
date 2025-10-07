package org.example.v13;

public class Java13TextBlocks {
    public static void main(String[] args) {

        // ========================
        // 🔹 Text Blocks (Java 13+)
        // ========================
        String html = """
                <html>
                    <body>
                        <h1>Hola</h1>
                    </body>
                </html>
                """;

        String json = """
                {
                    "usuario": "Ken",
                    "activo": true
                }
                """;

        String sql = """
                SELECT id, nombre
                FROM usuarios
                WHERE activo = 1
                ORDER BY nombre;
                """;

        // ✅ Se comportan como String normales
        System.out.println("HTML length: " + html.length());
        System.out.println("JSON contiene 'usuario'? " + json.contains("usuario"));
        System.out.println("Consulta SQL:\n" + sql);
    }
}
