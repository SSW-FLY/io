package itcode.antlr.impl.itcode.antlr;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;

import java.util.List;

public class CalVisitor extends ArithmeticBaseVisitor<Double> {

    @Override
    public Double visitPrintExpr(ArithmeticParser.PrintExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Double visitMulDiv(ArithmeticParser.MulDivContext ctx) {
        Double v1 = visit(ctx.expr(0));
        Double v2 = visit(ctx.expr(1));
        if (ctx.op.getType() == ArithmeticParser.MUL) {
            return v1 * v2;
        } else {
            return v1 / v2;
        }
    }

    @Override
    public Double visitAddSub(ArithmeticParser.AddSubContext ctx) {
        Double v1 = visit(ctx.expr(0));
        Double v2 = visit(ctx.expr(1));
        if (ctx.op.getType() == ArithmeticParser.ADD) {
            return v1 + v2;
        } else {
            return v1 - v2;
        }
    }

    @Override
    public Double visitInt(ArithmeticParser.IntContext ctx) {
        return Double.valueOf(ctx.INT().getText());
    }


    @Override
    public Double visitFloat(ArithmeticParser.FloatContext ctx) {
        return Double.valueOf(ctx.FLOAT().getText());
    }

    @Override
    public Double visitBracket(ArithmeticParser.BracketContext ctx) {
        return visit(ctx.expr());
    }
}
