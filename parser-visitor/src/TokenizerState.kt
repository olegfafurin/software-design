interface TokenizerState {
    fun process(tokenizer: Tokenizer)
}

object StartState : TokenizerState {
    override fun process(tokenizer: Tokenizer) {
        when (tokenizer.sym) {
            in (0..9).map { '0' + it } -> tokenizer.setState(NumberState())
            '(' -> tokenizer.addToken(LeftBrace())
            ')' -> tokenizer.addToken(RightBrace())
            '+' -> tokenizer.addToken(Plus())
            '-' -> tokenizer.addToken(Minus())
            '*' -> tokenizer.addToken(Mult())
            '/' -> tokenizer.addToken(Div())
            ' ' -> tokenizer.next()
            (-1).toChar() -> tokenizer.setState(EndState)
//            null -> tokenizer.setState(EndState)
            else -> tokenizer.setState(ErrorState("No parse: ${tokenizer.sym}"))
        }
    }
}

class NumberState(val value: Int = 0) : TokenizerState {
    override fun process(tokenizer: Tokenizer) {
        when (tokenizer.sym) {
//            (-1).toChar() -> tokenizer.setState(EndState)
            in (0..9).map { '0' + it } -> {
                tokenizer.setState(NumberState(this.value * 10 + (tokenizer.sym - '0')))
                tokenizer.next()
            }
            else -> {
                tokenizer.addAndStay(NumberToken(this.value))
                tokenizer.setState(StartState)
            }
        }
    }
}

object EndState : TokenizerState {
    override fun process(tokenizer: Tokenizer) {}
}

class ErrorState(val err: String) : TokenizerState {
    override fun process(tokenizer: Tokenizer) {}
}