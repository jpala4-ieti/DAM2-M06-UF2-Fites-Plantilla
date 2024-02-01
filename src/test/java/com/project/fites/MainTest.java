package com.project.fites;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent, true));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainOutput() {
        Main.main(new String[]{});
        String expectedOutput = "Ciutats:\r\n" + //
                "Ciutat{id=1, nom='Barcelona', aeroports=Aeroport de Barcelona-El Prat, Aeroport de Girona-Costa Brava}\r\n" + //
                "Ciutat{id=2, nom='Madrid', aeroports=Aeroport Adolfo Suárez Madrid-Barajas, Aeroport de Torrejón}\r\n" + //
                "Aeroports:\r\n" + //
                "Aeroport{id=1, nom='Aeroport de Barcelona-El Prat', ciutat=Barcelona}\r\n" + //
                "Aeroport{id=2, nom='Aeroport de Girona-Costa Brava', ciutat=Barcelona}\r\n" + //
                "Aeroport{id=3, nom='Aeroport Adolfo Suárez Madrid-Barajas', ciutat=Madrid}\r\n" + //
                "Aeroport{id=4, nom='Aeroport de Torrejón', ciutat=Madrid}\r\n" + //
                "Rutes:\r\n" + //
                "Ruta{id=1, nom='R1', origen=Aeroport de Barcelona-El Prat, desti=Aeroport Adolfo Suárez Madrid-Barajas}\r\n" + //
                "Ruta{id=2, nom='R2', origen=Aeroport de Barcelona-El Prat, desti=Aeroport de Torrejón}\r\n" + //
                "Ruta{id=3, nom='R3', origen=Aeroport de Girona-Costa Brava, desti=Aeroport Adolfo Suárez Madrid-Barajas}\r\n" + //
                "Ruta{id=4, nom='R4', origen=Aeroport de Girona-Costa Brava, desti=Aeroport de Torrejón}\r\n" + //
                "Ruta{id=5, nom='R5', origen=Aeroport Adolfo Suárez Madrid-Barajas, desti=Aeroport de Barcelona-El Prat}\r\n" + //
                "Ruta{id=6, nom='R6', origen=Aeroport Adolfo Suárez Madrid-Barajas, desti=Aeroport de Girona-Costa Brava}\r\n" + //
                "Ruta{id=7, nom='R7', origen=Aeroport de Torrejón, desti=Aeroport de Barcelona-El Prat}\r\n" + //
                "Ruta{id=8, nom='R8', origen=Aeroport de Torrejón, desti=Aeroport de Girona-Costa Brava}";

        // Normalitzar els salts de linia entre el resultat esperat i el resultat obtingut
        String normalizedExpectedOutput = normalizeNewlines(expectedOutput);
        String normalizedActualOutput = normalizeNewlines(outContent.toString().trim());

        // Guardar la sortida esperada i la sortida real
        String baseDir = System.getProperty("user.dir");
        saveToFile(normalizedExpectedOutput, baseDir + File.separator + "data" + File.separator + "test-MainTest-SortidaEsperada.txt");
        saveToFile(normalizedActualOutput, baseDir + File.separator + "data" + File.separator + "test-MainTest-SortidaObtinguda.txt");

        // Comparar els resultats normalitzats
        assertEquals(normalizedExpectedOutput, normalizedActualOutput);
    }

    private String normalizeNewlines(String input) {
        return input.replace("\r\n", "\n").replace("\r", "\n");
    }

    private void saveToFile(String content, String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Create directories if they do not exist
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
                writer.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
