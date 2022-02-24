package sk.salgovic.brainfuck.runtime;

import sk.salgovic.brainfuck.exceptions.BrainFuckRuntimeException;

public interface Runtime {
    byte getByte(short index) throws BrainFuckRuntimeException;
    void setByte(short index, byte value) throws BrainFuckRuntimeException;
}
