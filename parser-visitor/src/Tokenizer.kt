import java.io.InputStream
import java.io.InputStreamReader
import kotlin.system.exitProcess

class Tokenizer(s: InputStream) {
    private var state: TokenizerState = StartState
    private var tokens: MutableList<Token> = mutableListOf()
    private val r: InputStreamReader = s.reader()
    var sym: Char = 0.toChar()

    fun run(): List<Token> {
        next()
        while (state is StartState || state is NumberState) {
            state.process(this)
        }
        when (state) {
            is ErrorState -> {
                println((state as ErrorState).err)
                exitProcess(-1)
            }
            else -> return tokens
        }
    }

    fun setState(newState: TokenizerState) {
        state = newState
    }

    fun addToken(tok: Token) {
        tokens.add(tok)
        next()
    }

    fun addAndStay(tok: Token) {
        tokens.add(tok)
    }

    fun next() {
        sym = r.read().toChar()
    }

}