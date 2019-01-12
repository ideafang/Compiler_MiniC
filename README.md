# Compiler_MiniC
use JavaCC plugin in eclipse and implement lexical analysis, grammar analysis, semantic analysis

Implementation content:
- Complete lexical analysis according to the rules of word formation specified in the Token.
- Construct the grammar of MiniC, print a syntax tree, and complete parsing.
- Guide translation based on attribute grammar, print quaternion, and implement semantic analysis.
- Ability to translate declarations, assignments, conditions, and loop statements. Complete nested analysis and translation of MathExpression, BoolExpression, if-else, switch-case, for, while, do-while
- Define a symbol table that checks for duplicate declarations of variables and undefined errors when used.
- Add interface to read, save source and analyze output

使用JavaCC插件，在eclipse中编程，实现了MiniC语法的词法分析，语法分析和语义分析。实现了算术表达式、布尔表达式的分析。

实现内容：
- 根据Token中规定的构词规则，完成词法分析。
- 构造MiniC的递归下降文法，输出语法树，完成语法分析。
- 根据属性文法制导翻译，输出四元式，实现语义分析。
- 能够翻译声明、赋值、条件、循环语句。完成MathExpression, BoolExpression, if-else, switch-case, for, while, do-while等语句的嵌套分析与翻译
- 定义符号表，能检查变量的重复声明和使用时未定义错误。
- 添加界面，可以读取、保存源程序并分析输出