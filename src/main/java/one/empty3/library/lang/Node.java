package one.empty3.library.lang;

public class Node {
    public static final int declaration = 0;
    public static final int instruction = 1;
    public static final int assignement = 2:

    public enum TokenType {Name, Keyword,  Comment, JavadocComment
       };
    public enum Literal {StringLiteral,
       FloatLiteral, DoubleLiteral, CharLiteral };
    public enum InstructionBlock { Unnamed, For, While, Do, Method };
    public enum Declaration {Package, Imports, Classes, Interfaces, MethodMember, VarMember, Variable, Param};
    
}
