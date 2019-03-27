package sg.edu.nus.comp.cs4218.impl.util;

import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.util.LinkedList;

public class ArgumentResolverObserver {
    private String commandResult;
    private String commandString;
    private LinkedList<RegexArgument> list = new LinkedList<>();
    private RegexArgument regax;

    public void testEvaluateSubCommand() throws ShellException {
        ArgumentResolver.testEvaluationSubCommand(this);
    }

    public void testAppendParsedArgIntoSegment() {
        ArgumentResolver.testAppendParsedArgIntoSegment(this);
    }

    public void setCommandResult(String commandResult) {this.commandResult = commandResult;}

    public String getCommandResult() {return commandResult;}

    public void setCommandString(String commandString) {this.commandString = commandString;}

    public String getCommandString() {return commandString;}

    public void addRegaxToList(RegexArgument regexArgument) {this.list.add(regexArgument);}

    public LinkedList<RegexArgument> getRegaxList() {return list;}

    public void setRegex(RegexArgument regax) {this.regax = regax;}

    public RegexArgument getRegax() {return regax;}
}
