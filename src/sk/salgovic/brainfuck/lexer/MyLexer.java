package sk.salgovic.brainfuck.lexer;

import sk.salgovic.brainfuck.Instruction;
import sk.salgovic.brainfuck.exceptions.BrainFuckLexerException;


import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

public class MyLexer implements Lexer {
    private final Reader r;

    private final LinkedList<Instruction> instructions;
    private int index;

    public MyLexer(Reader r) throws IOException {
        this.r = r;
        this.instructions = new LinkedList<>();
        this.index = 0;
        loadInstructions();
    }

    private void loadInstructions() throws IOException {
        while (true) {
            byte instruction = (byte) r.read();
            switch (instruction) {
                case '>' -> instructions.add(Instruction.INCREMENT_PTR);
                case '<' -> instructions.add(Instruction.DECREMENT_PTR);
                case '+' -> instructions.add(Instruction.INCREMENT_DATA);
                case '-' -> instructions.add(Instruction.DECREMENT_DATA);
                case '.' -> instructions.add(Instruction.OUTPUT);
                case ',' -> instructions.add(Instruction.INPUT);
                case '[' -> instructions.add(Instruction.START_LOOP);
                case ']' -> instructions.add(Instruction.END_LOOP);
                case -1 -> {
                    instructions.add(Instruction.EOF);
                    return;
                }
                default -> {
                }
            }
        }
    }

    @Override
    public Instruction getInstruction() {
        return instructions.get(index);
    }

    @Override
    public void moveToNext() throws BrainFuckLexerException {
        index++;
        if (index > instructions.size()) {
            throw new BrainFuckLexerException("lexer out of right bounds");
        }
    }

    @Override
    public void moveToPrevious() throws BrainFuckLexerException {
        index--;
        if (index < 0) {
            throw new BrainFuckLexerException("lexer out of left bounds");
        }
    }
}
