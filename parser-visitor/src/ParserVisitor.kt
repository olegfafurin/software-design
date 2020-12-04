import java.util.*

class ParserVisitor : TokenVisitor {

    val ops: Deque<Token> = ArrayDeque()
    val polishExpr: MutableList<Token> = mutableListOf()

    override fun visit(token: NumberToken) {
        polishExpr.add(token)
    }

    override fun visit(token: Brace) {
        when (token) {
            is LeftBrace -> ops.push(token)
            is RightBrace -> {
                while (ops.isNotEmpty() and (ops.peek() !is LeftBrace)) {
                    polishExpr.add(ops.pop())
                }
                if (ops.isEmpty())
                    throw IllegalStateException("Unbalanced parentheses found")
                ops.pop()
            }
        }
    }

    override fun visit(token: Operation) {
        while (ops.isNotEmpty()) {
            val op = ops.peek()
            if (op is Operation) {
                if (op.priority >= token.priority) {
                    polishExpr.add(ops.pop())
                    continue
                }
            }
            break
        }
        ops.push(token)
    }

    fun visit(tokens: List<Token>): List<Token> {
        for (elem in tokens)
            elem.accept(this)
        while (ops.isNotEmpty()) {
            val op = ops.pop()
            if (op !is Operation)
                throw IllegalStateException("Unbalanced operations/operands")
            polishExpr.add(op)
        }
        if (polishExpr.count { it is NumberToken } - polishExpr.count { it is Operation } != 1)
            throw IllegalStateException("Unbalanced operations/operands")
        return polishExpr
    }

}