package sk.salgovic;

public enum Instruction {
    INCREMENT_PTR('>'),  // >
    DECREMENT_PTR('<'),  // <
    INCREMENT_DATA('+'), // +
    DECREMENT_DATA('-'), // -
    INPUT(','),          // ,
    OUTPUT('.'),         // .
    START_LOOP('['),     // [
    END_LOOP(']'),       // ]
    EOF('\0'),
    ;

    private final char instruction;

    Instruction(char instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return Character.toString(this.instruction);
    }
}
