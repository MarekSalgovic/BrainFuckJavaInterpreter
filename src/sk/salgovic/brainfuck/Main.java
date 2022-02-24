package sk.salgovic.brainfuck;

import sk.salgovic.brainfuck.Interpreter;
import sk.salgovic.brainfuck.exceptions.BrainFuckLexerException;
import sk.salgovic.brainfuck.exceptions.BrainFuckRuntimeException;
import sk.salgovic.brainfuck.lexer.Lexer;
import sk.salgovic.brainfuck.lexer.MyLexer;
import sk.salgovic.brainfuck.runtime.MyRuntime;
import sk.salgovic.brainfuck.runtime.Runtime;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.exit(1);
        }
        try {
            File file = new File(System.getProperty("user.dir") + "/" + args[0]);
            Reader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Runtime runtime = new MyRuntime((short) 1024);
            Lexer lexer = new MyLexer(fr);
            Interpreter interpreter = new Interpreter(runtime, br, lexer);

            interpreter.run();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        } catch (BrainFuckRuntimeException e) {
            e.printStackTrace();
            System.exit(3);
        } catch (BrainFuckLexerException e) {
            e.printStackTrace();
            System.exit(4);
        }
    }
}
