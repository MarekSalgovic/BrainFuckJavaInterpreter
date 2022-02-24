package sk.salgovic.runtime;

import sk.salgovic.exceptions.BrainFuckRuntimeException;

public interface Runtime {
    byte getByte(short index) throws BrainFuckRuntimeException;
    void setByte(short index, byte value) throws BrainFuckRuntimeException;
}
