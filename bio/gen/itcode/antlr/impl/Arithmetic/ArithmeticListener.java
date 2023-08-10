// Generated from /Users/fly/Public/code/io/bio/src/main/java/itcode/antlr/impl/Arithmetic/Arithmetic.g4 by ANTLR 4.12.0
package itcode.antlr.impl.Arithmetic;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ArithmeticParser}.
 */
public interface ArithmeticListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ArithmeticParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ArithmeticParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArithmeticParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ArithmeticParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArithmeticParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(ArithmeticParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArithmeticParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(ArithmeticParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArithmeticParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ArithmeticParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArithmeticParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ArithmeticParser.ExprContext ctx);
}