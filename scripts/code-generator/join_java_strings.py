# converts lines of strings into java code snippet:
# String.join(StringUtils.NEW_LINE, new String[]{ .... });

import sys
filename = sys.argv[1]
with open(filename) as file:
    lines = file.readlines()
    sanitized = [s.replace("\n", "") for s in lines]
    var_name = sanitized[0]
    sanitized = sanitized[1:]
    wrapped = [ '"{}"'.format(s) for s in sanitized]
    joined = ",".join(wrapped)
    output = 'static String {} = String.join(StringUtils.STRING_NEWLINE, new String[]{{{}}});'.format(var_name, joined)
    print(output)