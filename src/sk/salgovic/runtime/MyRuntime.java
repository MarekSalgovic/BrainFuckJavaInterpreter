package sk.salgovic.runtime;

import sk.salgovic.exceptions.BrainFuckRuntimeException;

public class MyRuntime implements Runtime {

    private final byte[] memory;
    private final short size;

    public MyRuntime(short size) {
        this.size = size;
        this.memory = new byte[size];
    }

    @Override
    public byte getByte(short index) throws BrainFuckRuntimeException {
        if (index > size - 1 || index < 0) {
            throw new BrainFuckRuntimeException("index out of memory bounds");
        }
        return this.memory[index];
    }

    @Override
    public void setByte(short index, byte value) throws BrainFuckRuntimeException {
        if (index > size - 1 || index < 0) {
            throw new BrainFuckRuntimeException("index out of memory bounds");
        }
        this.memory[index] = value;
    }
}
