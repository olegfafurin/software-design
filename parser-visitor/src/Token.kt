interface Token {
    fun accept(visitor: TokenVisitor)
}