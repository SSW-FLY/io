package itcode;


import itcode.antlr.impl.itcode.antlr.ArithmeticLexer;
import itcode.antlr.impl.itcode.antlr.ArithmeticParser;
import itcode.antlr.impl.itcode.antlr.CalVisitor;
import org.antlr.v4.runtime.*;
import org.apache.commons.collections.bag.HashBag;

import java.util.ArrayList;
import java.util.List;


/**
 * @author imp
 * @date 2020/12/24
 */
public class Application {


    public static void main(String[] args) throws Exception {
        HashBag hashBag = new HashBag();
    }

    private static void t1() {
        ANTLRInputStream input = new ANTLRInputStream("((1.1 + 2)*3) * 3 +11 -22");
        ArithmeticLexer lexer = new ArithmeticLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArithmeticParser parser = new ArithmeticParser(tokens);
        ArithmeticParser.ProgContext expr = parser.prog();
        CalVisitor visitor = new CalVisitor();
        Double visit = visitor.visit(expr);
        System.out.println(visit);
    }


}
