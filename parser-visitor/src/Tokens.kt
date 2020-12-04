interface Brace : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}

class LeftBrace : Brace

class RightBrace : Brace

class NumberToken(val value: Int) : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}

interface Operation : Token {
    val priority: Int

    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }

    fun op(arg1: Int, arg2: Int): Int
}

class Plus : Operation {
    override val priority: Int = 1

    override fun op(arg1: Int, arg2: Int): Int = arg1 + arg2
}

class Minus : Operation {
    override val priority: Int = 1

    override fun op(arg1: Int, arg2: Int): Int = arg1 - arg2
}

class Mult : Operation {
    override val priority: Int = 2

    override fun op(arg1: Int, arg2: Int): Int = arg1 * arg2
}

class Div : Operation {
    override val priority: Int = 2

    override fun op(arg1: Int, arg2: Int): Int = arg1 / arg2
}