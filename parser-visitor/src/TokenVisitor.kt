interface TokenVisitor {
    fun visit(token: NumberToken)

    fun visit(token: Brace)

    fun visit(token: Operation)
}