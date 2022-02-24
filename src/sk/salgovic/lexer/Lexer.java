package sk.salgovic.lexer;

import sk.salgovic.Instruction;
import sk.salgovic.exceptions.BrainFuckLexerException;

public interface Lexer {
    Instruction getInstruction();

    void moveToPrevious() throws BrainFuckLexerException;

    void moveToNext() throws BrainFuckLexerException;
}
