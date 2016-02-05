#FileAnalyzer

FileAnalyzer will find all the number fields in the specified file, it will calculate the number count and the number sum, and the analyzing result will be printed to the output. 
  

## Pre-Condition
Please make sure that the following tools are installed in your machine:
* maven
* JRE1.8
* Python

## Usage
To launch the FileAnalyzer, please type the following commands in the command line:
```
$ ./fileAnalyzer.py -h
usage:  [Options]
 -e <arg>   file encoding
 -f <arg>   file name
 -h         print help message

$ ./fileAnalyzer.py -f /tmp/test.1
Start to analyze file /tmp/test.1
Analyze done!
74992 numbers found, total amount is 20856492.034912109375000

```

## Test Cases
The following test cases will be involved:

### Happy cases
1. Regular existing file
2. Empty file
3. Small file that can be handled in the memory
4. Big file that can't be handled in the memory


### Edge Cases
1. Invalid file path
2. Not existing file
3. Existing directory, not regular file
4. The customer specifies the wrong char-encoding
5. Permission Denied
6. The user can specify the charset, if the charset is not correct, the application should provide some helpful messages

