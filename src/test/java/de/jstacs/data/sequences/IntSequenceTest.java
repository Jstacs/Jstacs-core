package de.jstacs.data.sequences;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.jstacs.data.AlphabetContainer;
import de.jstacs.data.alphabets.DNAAlphabet;

public class IntSequenceTest {

    @Test
    public void parsesDnaString() throws Exception {
        AlphabetContainer container = new AlphabetContainer(DNAAlphabet.SINGLETON);
        IntSequence sequence = new IntSequence(container, "ACGT");

        assertEquals(4, sequence.getLength());
        assertEquals(0, sequence.discreteVal(0));
        assertEquals(1, sequence.discreteVal(1));
        assertEquals(2, sequence.discreteVal(2));
        assertEquals(3, sequence.discreteVal(3));
    }

    @Test
    public void subSequenceKeepsAlphabetAndLength() throws Exception {
        AlphabetContainer container = new AlphabetContainer(DNAAlphabet.SINGLETON);
        IntSequence sequence = new IntSequence(container, "ACGT");

        Sequence sub = sequence.getSubSequence(1, 2);
        assertEquals(2, sub.getLength());
        assertEquals(container.getType(), sub.getAlphabetContainer().getType());
    }
}
