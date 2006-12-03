import java.io.PrintStream;

/*
 * Created on Mar 4, 2006
 * Mar 4, 2006 11:47:16 PM
 */

public class AsmException extends Exception
{



    public AsmException(Error error)
    {      
        fError = error;
        fToken = null;
        fLine = 0;
    }
    public AsmException(Error error, Lexer lex)
    {      
        fError = error;
        fToken = null;
        fLine = (lex == null) ? 0 : lex.Line();        
    }    
    public AsmException(Error error, Token t)
    {
        fError = error;
        fToken = t;
        fLine = (t == null) ? 0 : t.Line();
    }
    
    public void SetLine(int line)
    {
        fLine = line;
    }
    
    public void print(PrintStream s)
    {
        s.println(toString());
    }
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        
        if (fLine != 0) s.append(" Line " + fLine + " ");
        
        switch (fError)
        {
        case E_ALIGN:
            s.append("Invalid ALIGN value");
            break;
        case E_UNTERM_STRING:
            s.append("Unterminated string");
            break;
        case E_EXPRESSION:
            s.append("Expression too complicated.");
            break;
        case E_UNEXPECTED:
            s.append("Unexpected ");
            switch (fToken.Type())
            {
            case Token.EOF:
                s.append("end of file");
                break;
            case Token.EOL:
                s.append("end of line");
                break;
            case Token.NUMBER:
                s.append("number");
                s.append(" -- " + fToken.Value());
                break;
            case Token.SPACE:
                s.append("space");
                break;
            case Token.STRING:
                s.append("string");
                s.append(" -- " + fToken.toString());
                break;
            case Token.SYMBOL:
                s.append("symbol");
                s.append(" -- " + fToken.toString());
                break;
            default:
                s.append((char)fToken.Value());
            
            }
            break;
        }
        return s.toString();
    }
    
    private static final long serialVersionUID = 1L;
    private Error fError;
    private Token fToken;
    private int fLine;
}