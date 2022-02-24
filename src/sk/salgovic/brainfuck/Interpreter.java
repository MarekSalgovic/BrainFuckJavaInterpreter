package sk.salgovic.brainfuck;

import sk.salgovic.brainfuck.exceptions.BrainFuckLexerException;
import sk.salgovic.brainfuck.exceptions.BrainFuckRuntimeException;
import sk.salgovic.brainfuck.lexer.Lexer;
import sk.salgovic.brainfuck.runtime.Runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Interpreter {

    private final Lexer lexer;
    private final Runtime runtime;
    private final Reader reader;
    private final Writer writer;

    private short index;
    private int loopScopesCount;

    Interpreter(Reader reader, Writer writer, Lexer lexer, Runtime runtime) {
        this.runtime = runtime;
        this.lexer = lexer;
        this.reader = reader;
        this.writer = writer;
        this.index = 0;
        this.loopScopesCount = 0;
    }


    public void run() throws IOException, BrainFuckRuntimeException, BrainFuckLexerException {
        while (true) {
            Instruction i = lexer.getInstruction();
            lexer.moveToNext();
            if (i == Instruction.EOF) {
                return;
            }
            execute(i);
        }
    }

    private void execute(Instruction i) throws BrainFuckRuntimeException, IOException, BrainFuckLexerException {
        switch (i) {
            case INCREMENT_PTR -> doIncrementPtr();
            case DECREMENT_PTR -> doDecrementPtr();
            case INCREMENT_DATA -> doIncrementData();
            case DECREMENT_DATA -> doDecrementData();
            case INPUT -> doInput();
            case OUTPUT -> doOutput();
            case START_LOOP -> doStartLoop();
            case END_LOOP -> doEndLoop();
            default -> {
            }
        }
    }

    private void doIncrementPtr() {
        index++;
    }

    private void doDecrementPtr() {
        index--;
    }

    private void doIncrementData() throws BrainFuckRuntimeException {
        byte data = (byte) (runtime.getByte(index) + 1);
        runtime.setByte(index, data);
    }

    private void doDecrementData() throws BrainFuckRuntimeException {
        byte data = (byte) (runtime.getByte(index) - 1);
        runtime.setByte(index, data);
    }

    private void doInput() throws IOException, BrainFuckRuntimeException {
        int input = reader.read();
        if (input < 0 || input > 255) {
            input = 0;
        }
        byte data = (byte) input;
        runtime.setByte(index, data);
    }

    private void doOutput() throws BrainFuckRuntimeException, IOException {
        writer.write((char) runtime.getByte(index));
        writer.flush();
    }


    private void doStartLoop() throws BrainFuckRuntimeException, BrainFuckLexerException {
        if (runtime.getByte(index) != (byte) 0) {
            return;
        }
        while (true) {
            Instruction i = lexer.getInstruction();
            lexer.moveToNext();
            if (i == Instruction.START_LOOP) {
                loopScopesCount++;
            }
            if (i == Instruction.END_LOOP) {
                if (loopScopesCount == 0) {
                    return;
                }
                loopScopesCount--;
            }
        }
    }

    private void doEndLoop() throws BrainFuckRuntimeException, BrainFuckLexerException {
        if (runtime.getByte(index) == (byte) 0) {
            return;
        }
        lexer.moveToPrevious();
        lexer.moveToPrevious();
        while (true) {
            Instruction i = lexer.getInstruction();
            lexer.moveToPrevious();
            if (i == Instruction.END_LOOP) {
                loopScopesCount++;
            }
            if (i == Instruction.START_LOOP) {
                if (loopScopesCount == 0) {
                    lexer.moveToNext();
                    lexer.moveToNext();
                    return;
                }
                loopScopesCount--;
            }
        }
    }
}
