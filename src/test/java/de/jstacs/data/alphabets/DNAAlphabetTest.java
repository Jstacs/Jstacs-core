package de.jstacs.data.alphabets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import de.jstacs.data.WrongAlphabetException;

public class DNAAlphabetTest {

    @Test
    public void codesAndComplements() throws WrongAlphabetException {
        DNAAlphabet dna = DNAAlphabet.SINGLETON;

        assertEquals(0, dna.getCode("A"));
        assertEquals(1, dna.getCode("C"));
        assertEquals(2, dna.getCode("G"));
        assertEquals(3, dna.getCode("T"));
        assertEquals(0, dna.getCode("a"));
        assertEquals(1, dna.getCode("c"));
        assertEquals(2, dna.getCode("g"));
        assertEquals(3, dna.getCode("t"));

        assertEquals(3, dna.getComplementaryCode(0));
        assertEquals(2, dna.getComplementaryCode(1));
        assertEquals(1, dna.getComplementaryCode(2));
        assertEquals(0, dna.getComplementaryCode(3));
    }

    @Test
    public void rejectsUnknownSymbol() {
        DNAAlphabet dna = DNAAlphabet.SINGLETON;
        assertThrows(WrongAlphabetException.class, () -> dna.getCode("N"));
    }
}
