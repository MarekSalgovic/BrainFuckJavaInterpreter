package sk.salgovic.brainfuck.lexer;

import sk.salgovic.brainfuck.Instruction;
import sk.salgovic.brainfuck.exceptions.BrainFuckLexerException;

public interface Lexer {
    Instruction getInstruction();

    void moveToPrevious() throws BrainFuckLexerException;

    void moveToNext() throws BrainFuckLexerException;
}
