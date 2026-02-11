package de.jstacs.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import de.jstacs.data.AlphabetContainer.AlphabetContainerType;
import de.jstacs.data.alphabets.ContinuousAlphabet;

public class AlphabetContainerTest {

    @Test
    public void xmlRoundTripContinuousAlphabet() throws Exception {
        AlphabetContainer container = new AlphabetContainer(new ContinuousAlphabet());
        assertEquals(AlphabetContainerType.CONTINUOUS, container.getType());

        StringBuffer xml = container.toXML();
        AlphabetContainer roundTrip = new AlphabetContainer(xml);

        assertEquals(container.getType(), roundTrip.getType());
        assertEquals(container.getNumberOfAlphabets(), roundTrip.getNumberOfAlphabets());
    }
}
