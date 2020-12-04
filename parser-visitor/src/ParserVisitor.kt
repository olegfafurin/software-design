import java.util.*

class ParserVisitor : TokenVisitor {

    val ops: Deque<Token> = ArrayDeque()
    val polishExpr: MutableList<Token> = mutableListOf()

    override fun visit(token: NumberToken) {
        polishExpr.add(token)
    }

    override fun visit(token: Brace) {
        when (token) {
            is LeftBrace -> ops.addLast(token)
            is RightBrace -> {
                while (ops.isNotEmpty() and (ops.peekLast() !is LeftBrace)) {
                    polishExpr.add(ops.pollLast())
                }
                if (ops.isEmpty())
                    throw IllegalStateException("Unbalanced parentheses found")
                ops.pollLast()
            }
        }
    }

    override fun visit(token: Operation) {
        while (ops.isNotEmpty()) {
            val op = ops.peekLast()
            if (op is Operation) {
                if (op.priority >= token.priority) {
                    polishExpr.add(ops.pollLast())
                    continue
                }
            }
            break
        }
        ops.addLast(token)
    }

    fun visit(tokens: List<Token>): List<Token> {
        for (elem in tokens)
            elem.accept(this)
        while (ops.isNotEmpty()) {
            val op = ops.pollLast()
            if (op !is Operation)
                throw IllegalStateException("Unbalanced operations/operands")
            polishExpr.add(op)
        }
        if (polishExpr.count { it is NumberToken } - polishExpr.count { it is Operation } != 1)
            throw IllegalStateException("Unbalanced operations/operands")
        return polishExpr
    }

}