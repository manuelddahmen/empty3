package one.empty3.library.lang;

import java.util.*;


public class Node {

    Node parent;
    List<Node> children = new ArrayList();

    public static final int declaration = 1;
    public static final int instruction = 2;
    public static final int assignement = 4:
    TokenType tt;
    public enum TokenType {Name, Keyword,  Comment, JavadocComment
        Literal};
    public enum Literal {StringLiteral,
        FloatLiteral, DoubleLiteral, CharLiteral };
    public enum InstructionBlock { Unnamed, For, While, Do, Method };
    public enum Declaration {Package, Imports, Classes, Interfaces, MethodMember, VarMember, Variable, Param};
    
    public Node(TokenType tt, Literal l, 
            String InstructionBlock ib,
            Declaration d) {
        this.tt = tt;
    }
}
